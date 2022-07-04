import { editorStore } from "../../Editor/EditorStore.js";
import { pathStore } from "../../Path/PathStore.js"

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
    editorStore.set("");
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

function saveTabContent() {
    let content;

    let unsubscribe = editorStore.subscribe(val => content = val);

    tabStore.update(tabs => {
        let index = tabs.findIndex(t => tabOn(t));

        if (index !== -1)
            tabs[index].content = content;

        return tabs;
    })

    unsubscribe();
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
            let tabIndex = tabs.findIndex(elt => tabEqual(elt, tab));
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
    saveTabContent();

    tabStore.update(tabs => {
        setTabOn(tab);
        editorStore.set(tab.content);
        pathStore.set(tab.path);
        return tabs;
    })
}