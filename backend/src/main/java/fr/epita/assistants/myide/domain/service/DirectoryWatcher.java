package fr.epita.assistants.myide.domain.service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryWatcher {
    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private Path root;

    public DirectoryWatcher(Path dir) throws IOException {
        watcher = dir.getFileSystem().newWatchService();
        keys = new HashMap<WatchKey, Path>();
        root = dir;

        walkAndRegisterDirectories(dir);
    }

    private void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
    }

    private void walkAndRegisterDirectories(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    void processEvents(ProjectServ projectServ) {
        for (;;) {
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                System.err.println("Watcher interrupted");
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // Context for directory entry event is the file name of entry
                @SuppressWarnings("unchecked")
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                boolean isDir = Files.isDirectory(child);

                // print out event
                // System.out.format("%s: %s\n", event.kind().name(), child);
                try {
                    String eventData = "{ \"type\": \"" + kind.name() + "\", \"path\": \"" + child.toString()
                            + "\", \"folder\": " + isDir + " }";
                    System.out.println("Sending event:" + eventData);
                    projectServ.getSseEmitter().send(eventData);
                } catch (IOException x) {
                    System.err.println("Error sending event: " + kind.name() + "/" + child + "err:" + x);
                }

                // if directory is created, and watching recursively, then register it and its
                // subdirectories
                if (kind == ENTRY_CREATE) {
                    try {
                        if (isDir) {
                            walkAndRegisterDirectories(child);
                        }
                    } catch (IOException x) {
                        System.out.println("Failed to register directory: " + child);
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    // public static void main(String[] args) throws IOException {
    // System.out.println("Starting directory watcher for " + args[0]);
    // Path dir = Paths.get(args[0]);
    // new DirectoryWatcher(dir).processEvents();
    // }
}
