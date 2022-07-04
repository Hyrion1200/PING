import { writable } from "svelte/store";

export const tabStore = writable([]);

function tabEqual(a, b) {
    return a.path === b.path;
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