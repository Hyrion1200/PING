// @ts-ignore
import { TabConfig, addTab, removeAllTabs } from "/src/stores/TabStore";
// @ts-ignore
import { editorStore } from "/src/stores/EditorStore.js";
// @ts-ignore
import { project } from "/src/stores/ProjectStore";
// @ts-ignore
import { addOutput } from "/src/stores/ConsoleStore";
// @ts-ignore
import { settings } from '/src/stores/SettingsStore';

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
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            if (data.status === "ERROR") {
                addOutput("Couldn't save file '" + path + "': " + data.message);
            }
        });
}

export async function openFile(path) {
    //let url = window.BASE_URL + "/ide/files/open?path=" + path;
    // @ts-ignore
    let url = `${window.BASE_URL}/ide/files/open?path=${path}`;
    console.log(url);
    const resp = await fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            if (data.status == "SUCCESS") {
                addTab(new TabConfig(path, path, data.content));
                editorStore.update((value) => (value = data.content));
            } else {
                addOutput("Couldn't open file '" + path + "': " + data.message);
            }
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
            removeAllTabs();
            project.set(data.content);
            settings.set(data.content.settings);
            console.debug(data.content);
            console.debug(settings);
        } else {
            addOutput("Couldn't load project '" + path + "': " + data.message);
        }
    } catch (err) {
        addOutput("Error fetching load");
    }
}