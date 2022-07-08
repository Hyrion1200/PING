import { editorSetContent, editorGetContent } from "./EditorStore.js";
import { pathStore } from "./PathStore.js"
// @ts-ignore
import { saveFile } from "/src/scripts/files.js";

import { writable } from "svelte/store";

export const tabStore = writable([]);

export class TabConfig {
    constructor(name, path, content) {
        this.name = name;
        this.path = path;
        this.content = content;
        this.on = false;
    }
}

function resetTabs() {
    editorSetContent("", "");
    pathStore.set("");
}

function tabEqual(a, b) {
    return a.path === b.path;
}

function tabOn(tab) {
    return tab.on;
}

function setTabOn(tab) {
    tabStore.update(tabs => {
        return tabs.map(t => {
            t.on = false;

            if (tabEqual(t, tab))
                t.on = true;

            return t;
        })
    })
}

export function removeAllTabs() {
    tabStore.update(tabs => {
        tabs.map(t => removeTab(t));
        return [];
    });

    resetTabs();
}

export function saveTabContent() {
    let content = editorGetContent();

    tabStore.update(tabs => {
        let index = tabs.findIndex(t => tabOn(t));

        if (index !== -1) {
            tabs[index].content = content;
            saveFile(tabs[index].path, tabs[index].content)
        }

        return tabs;
    })
}

export function addTab(tab) {
    tabStore.update(tabs => {
        if (tabs.find(elt => tabEqual(elt, tab)) === undefined)
            tabs.push(tab);

        switchTab(tab);

        return tabs;
    })
}

export function removeTab(tab) {
    tabStore.update(tabs => {
        if (tab.on) {
            let tabIndex = tabs.findIndex(t => tabEqual(t, tab));
            let newTabIndex = tabIndex + 1;

            if (newTabIndex === tabs.length)
                newTabIndex = tabIndex - 1;

            if (newTabIndex === -1)
                resetTabs();
            else
                switchTab(tabs[newTabIndex]);
        }

        return tabs.filter(elt => !tabEqual(elt, tab));
    })
}

export function switchTab(tab) {
    tabStore.update(tabs => {
        tab = tabs.find(t => tabEqual(tab, t));

        saveTabContent();
        setTabOn(tab);

        editorSetContent(tab.content, tab.path);
        pathStore.set(tab.path);

        return tabs;
    })
}