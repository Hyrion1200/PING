// import { app, BrowserWindow } from 'electron';
const { app, BrowserWindow } = require('electron');
const { spawn } = require('node:child_process');
const path = require('path');

const backend_version = '1.0.0';
if (process.env.NODE_ENV !== 'production') {
  process.resourcesPath = path.join(__dirname, '/resources');
  console.debug('Resources path: ' + process.resourcesPath);
}

function createWindow() {
  const win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
    }
  })

  win.loadFile('dist/index.html')
}

async function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function spawnBackend() {
  const getPort = (await import('get-port'));
  const port = await getPort.default();

  const backend = spawn('java', ['-jar', `${process.resourcesPath}/ping_backend-${backend_version}.jar`, `--server.port=${port}`]);
  backend.on('error', (err) => {
    console.error('Make sure to have java 17+ installed.');
    console.error('Failed to start backend: ' + err);
    process.exit(2);
  });

  // TODO - better await than checking stdout
  let backend_ready = false;
  let backend_stdout = '';

  // console.time('backend_startup');
  // console.time('total_await');
  backend.on('spawn', () => {
    backend.stdout.on('data', function handler(data) {
      backend_stdout += data;
      if (backend_stdout.includes('Started')) {
        // console.timeEnd('backend_startup');
        backend_ready = true;
        backend.stdout.removeListener('data', handler);
      }
    });
  });

  while (!backend_ready) {
    await sleep(150);
  }
  // console.timeEnd('total_await');

  process.env.BASE_URL = 'http://localhost:' + port;
  console.debug('Backend available at: ' + process.env.BASE_URL);

  return backend;
}

app.whenReady().then(async () => {
  if (process.env.DEBUG === undefined)
    await spawnBackend();
  else
    process.env.BASE_URL = 'http://localhost:8080';

  createWindow()

  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindow()
    }
  })
})

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})
