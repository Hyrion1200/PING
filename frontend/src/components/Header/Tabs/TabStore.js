import { writable } from "svelte/store";
import { editorStore } from "../../Editor/EditorStore.js";

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
    console.log(a.path + " and " + b.path);
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
        return tabs.filter(elt => !tabEqual(elt, tab));
    })
}

export function setTabOn(tab) {
    tabStore.update(tabs => {
        return tabs.map(t => {
            t.on = false;
            if (tabEqual(t, tab))
                t.on = true;
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