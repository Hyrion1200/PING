<script>
    // @ts-ignore
    import { project } from "/src/stores/ProjectStore.js";
    import Folder from "./Folder.svelte";
    // @ts-ignore
    import { updateSettings } from "/src/scripts/files.js";

    function parseNodes(root, i = 0) {
        let name = root.path;
        if (name.endsWith("/")) {
            console.log(name);
            name = name.substring(0, name.length - 1);
            console.log(name);
        }
        const nameSplitted = name.split("/");
        name = nameSplitted[nameSplitted.length - 1];
        let relativePath = nameSplitted
            .slice(nameSplitted.length - i - 1)
            .join("/");

        let children = undefined;
        if (root.type === "FOLDER") {
            children = root.children.map((child) => {
                return parseNodes(child, i + 1);
            });
            return { name, children, expanded: false };
        }

        return { name, path: root.path.slice(7), relativePath }; // Remove file:// before absolute path
    }

    let root = undefined;

    // Add element to the root, and all necessary intermediate directories
    function addToRoot(path, isDir) {
        if (root === undefined) return;

        let current = root;
        let pathSplitted = path
            .split(root.name)[1]
            .replace(/^\/+|\/+$/g, "")
            .split("/");
        for (let i = 0; i < pathSplitted.length - 1; i++) {
            let name = pathSplitted[i];
            if (current.children) {
                // On a Folder
                let child = current.children.find(
                    (child) => child.name === name
                );
                if (child === undefined) {
                    child = {
                        name,
                        children: [],
                    };
                    current.children.push(child);
                }
                current = child;
            } else {
                // Not supposed to happen, it means that a file became a directory
                console.debug("File became a directory (AddToRoot)");
            }
        }

        // All the path has been either checked or created up to the final file / directory to create
        if (current.children) {
            // On a Folder
            let child = current.children.find(
                (child) => child.name === pathSplitted[pathSplitted.length - 1]
            );
            if (child === undefined) {
                if (isDir) {
                    child = {
                        name: pathSplitted[pathSplitted.length - 1],
                        children: [],
                    };
                } else {
                    child = {
                        name: pathSplitted[pathSplitted.length - 1],
                        path: path,
                        relativePath: pathSplitted.join("/"),
                    };
                }
                console.debug("Created child file:", child);
                current.children.push(child);
            } else {
                console.debug("File already existed (AddToRoot)");
            }
        } else {
            // Not supposed to happen, it means that a file became a directory
            console.debug("File became a directory (AddToRoot end)");
        }
        root = root;
    }

    function removeFromRoot(path) {
        if (root === undefined) return;

        let current = root;
        let pathSplitted = path
            .split(root.name)[1]
            .replace(/^\/+|\/+$/g, "")
            .split("/");
        for (let i = 0; i < pathSplitted.length - 1; i++) {
            let name = pathSplitted[i];
            if (current.children) {
                // On a Folder
                let child = current.children.find(
                    (child) => child.name === name
                );
                if (child === undefined) {
                    child = {
                        name,
                        children: [],
                    };
                    current.children.push(child);
                }
                current = child;
            } else {
                // Not supposed to happen, it means that a file became a directory
                console.debug("File became a directory (RemoveFromRoot)");
            }
        }

        const index = current.children.findIndex(
            (child) => child.name === pathSplitted[pathSplitted.length - 1]
        );
        if (current.children && index !== -1) {
            current.children.splice(index, 1);
        } else {
            // Not supposed to happen, it means that a file became a directory
            console.debug(
                "Last directory of path does not exists (RemoveFromRoot end)"
            );
        }

        root = root;
    }

    let watcher = undefined;

    async function watchRoot() {
        if (watcher !== undefined) return;
        // @ts-ignore
        watcher = new EventSource(`${window.BASE_URL}/ide/watch`);

        watcher.onmessage = (event) => {
            const data = JSON.parse(event.data);
            console.debug(data);
            // FIXME : backend does not detect subdirectory on creation
            // (mkdir -p a/b/c => CREATE a)
            // (mv fullDir . => CREATE fullDir)
            if (data.type === "ENTRY_MODIFY") {
                // TODO : handle file modification on disk
                console.debug("ENTRY_MODIFY", data);
                if (
                    !data.folder &&
                    data.path.split("/").pop() === ".pingsettings"
                ) {
                    updateSettings();
                }
            } else if (data.type === "ENTRY_CREATE") {
                const fullPath = data.path;
                const isDir = data.folder;
                addToRoot(fullPath, isDir);
                if (
                    !data.folder &&
                    data.path.split("/").pop() === ".pingsettings"
                ) {
                    updateSettings();
                }
            } else if (data.type === "ENTRY_DELETE") {
                const fullPath = data.path;
                removeFromRoot(fullPath);
            } else {
                console.debug("Ignoring event:", data);
            }
        };
    }

    function wrapParseNodes(rootNode, toUndefined = false) {
        console.log("called: " + toUndefined);
        if (toUndefined) {
            root = undefined;
            return;
        }
        root = parseNodes(rootNode);
        watchRoot();
    }

    $: {
        if ($project !== undefined) {
            wrapParseNodes($project.rootNode);
        } else {
            wrapParseNodes(undefined, true);
        }
    }

    $: console.log("root", root);
</script>

<div class="container">
    <div class="project-name">
        <h2>
            {#if root === undefined} No project {:else} {root.name} {/if}
        </h2>
    </div>
    {#if root === undefined}
        <!-- TODO component to open a project-->
        <div id="no_project">
            <p>No project loaded</p>
        </div>
    {:else}
        <!-- TODO enable showing or not showing hidden files.-->
        <div class="tree">
            <Folder name={root.name} children={root.children} expanded={true} />
        </div>
    {/if}
</div>

<style>
    /* TODO propper gestion of resizing and simply scrolling */
    .container {
        background-color: #17212f;
        min-width: 250px;
        max-width: 250px;
        max-height: 100%;
        align-content: inherit;
    }
    :global(body.dark-mode) .container{
        background-color: lightgray;
        color: black;
    }

    .tree {
        max-height: calc(100% - 100px);
        overflow: scroll;
        padding-top: 15px;
        padding-left: 10%;
        background-color: #17212f;
    }

    :global(body.dark-mode) .tree{
        background-color: lightgray;
        color: black;
    }

    .tree::-webkit-scrollbar {
        width: 10px;
        height: 10px;
    }

    .tree::-webkit-scrollbar-corner {
        background: rgba(0, 0, 0, 0);
    }

    .tree::-webkit-scrollbar-thumb {
        background-color: #bcb086;
        border-radius: 6px;
        border: 4px solid rgba(0, 0, 0, 0);
        background-clip: content-box;
        min-width: 32px;
        min-height: 32px;
    }

    .tree::-webkit-scrollbar-track {
        background-color: rgba(0, 0, 0, 0);
    }

    .project-name {
        display: flex;
        align-items: center;
        justify-content: center;
        padding-left: 0.7%;
        height: 45px;
        border-bottom: 1px solid #ffff;
        background-color: #17212f;
    }
    
    :global(body.dark-mode) .project-name{
        background-color: lightgray;
        color: black;
        border-bottom: 1px solid black;
    }

    #no_project {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 95%;
        background-color: #17212f;
    }

    :global(body.dark-mode) #no_project{
        background-color: lightgray;
        color: black;
    }



    p {
        color: #ffffe0;
        font-size: 1em;
        font-weight: bold;
        text-align: center;
    }

    :global(body.dark-mode) p{
        background-color: lightgray;
        color: black;
    }

    h2 {
        color: #ffffe0;
        margin: 0;
        font-size: 12;
        text-align: center;
    }

    :global(body.dark-mode) h2{
        background-color: lightgray;
        color: black;
    }


</style>
