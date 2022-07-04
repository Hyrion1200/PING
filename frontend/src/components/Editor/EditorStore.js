import { writable } from "svelte/store";

export let editorStore = writable("");

export function editorAdd(str) {
    editorStore.update(editor => {
        return editor + str;
    })
}