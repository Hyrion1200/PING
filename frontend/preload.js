// Preload Script
const { contextBridge, ipcRenderer } = require('electron');

// TODO - add the spawn of the backend process here if we want to have one
//      per window.

// Expose some variables to the renderer process via contextBridge
contextBridge.exposeInMainWorld('BASE_URL', process.env.BASE_URL);

contextBridge.exposeInMainWorld(
    'tamere',
    (channel, func) => {
        ipcRenderer.on(channel, (...args) => func(...args))
    }
)

contextBridge.exposeInMainWorld(
    'ipcTamere',
    ipcRenderer
)