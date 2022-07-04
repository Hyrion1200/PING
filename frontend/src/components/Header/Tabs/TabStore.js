import { writable } from "svelte/store";
import { editorStore } from "../../Editor/EditorStore.js";

export const tabStore = writable([]);

function tabEqual(a, b) {
    return a.path === b.path;
}

function tabOn(tab) {
    return tab.on;
}

export function addTab(tab) {
    tabStore.update(tabs => {
        if (tabs.find(elt => { tabEqual(elt, tab) }) === undefined)
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