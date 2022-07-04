import { editorStore } from "../../Editor/EditorStore.js";
import { pathStore } from "../../Path/PathStore.js"

import { writable } from "svelte/store";

export const tabStore = writable([]);

export class TabConfig {
    constructor(name, path, content, on = false) {
        this.name = name;
        this.path = path;
        this.content = content;
        this.on = on;
    }
}

function tabEqual(a, b) {
    return a.path === b.path;
}

function tabOn(tab) {
    return tab.on;
}

export function addTab(tab) {
    tabStore.update(tabs => {
        if (tabs.find(elt => tabEqual(elt, tab)) === undefined)
            tabs.push(tab);
        return tabs;
    })
}

export function removeTab(tab) {
    tabStore.update(tabs => {
        if (tab.on) {
            tab.on = false;

            let tabIndex = tabs.findIndex(elt => tabEqual(elt, tab));
            let newTabIndex = tabIndex + 1;

            if (newTabIndex === tabs.length)
                newTabIndex = tabIndex - 1;

            if (newTabIndex === -1)
                editorStore.set("");
            else {
                tabs[newTabIndex].on = true;
                editorStore.set(tabs[newTabIndex].content);
                pathStore.set(tabs[newTabIndex].path);
            }
        }

        return tabs.filter(elt => !tabEqual(elt, tab));
    })
}

export function setTabOn(tab) {
    tabStore.update(tabs => {
        return tabs.map(t => {
            t.on = false;
            if (tabEqual(t, tab)) {
                t.on = true;
                pathStore.set(t.path);
            }
            return t;
        });
    })
}

export function saveTabContent(editorContent) {
    tabStore.update(tabs => {
        let index = tabs.findIndex(t => tabOn(t));
        tabs[index].content = editorContent;
        return tabs;
    })
}