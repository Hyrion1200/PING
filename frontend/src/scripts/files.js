// @ts-ignore
import { TabConfig, addTab } from "/src/stores/TabStore";
// @ts-ignore
import { editorStore } from "/src/stores/EditorStore.js";
// @ts-ignore
import { project } from "/src/stores/ProjectStore";

export async function saveFile(path, content) {
    //let url = window.BASE_URL + "/ide/files/open?path=" + path;
    // @ts-ignore
    let url = `${window.BASE_URL}/ide/files/save?path=${path}`;
    console.log(url);
    console.log(content);
    const resp = await fetch(url, {
            method: "POST",
            body: content,
        })
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            console.log(data);
        });
}

export async function openFile(path) {
    //let url = window.BASE_URL + "/ide/files/open?path=" + path;
    // @ts-ignore
    let url = `${window.BASE_URL}/ide/files/open?path=${path}`;
    console.log(url);
    const resp = await fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            if (data.status == "SUCCESS") {
                addTab(new TabConfig(path, path, data.content));
                editorStore.update((value) => (value = data.content));
            }
            console.log(data.content);
        });
}

export async function loadProject(path) {
    //let url = window.BASE_URL + "/ide/files/open?path=" + path;
    // @ts-ignore
    let url = `${window.BASE_URL}/ide/load?path=${path}`;
    try {
        const resp = await fetch(url);
        const data = await resp.json();
        if (data.status == "SUCCESS") {
            project.set(data.content);
            console.log('Load successful', data.content);
        } else {
            console.error('Load failed: content = ' + data.content);
        }
    } catch (err) {
        console.error('Error fetching load:', err);
    }
}