import {get, writable } from "svelte/store";

import { python } from "@codemirror/lang-python";
import { javascript } from "@codemirror/lang-javascript"

export let editorStore = writable();
export let languageStore = writable();

function editorSetHighlight(extension) {
    let language;

    if (extension === "py")
        language = python();
    else if (extension === "js")
        language = javascript();
    else
        language = [];

    editorStore.update(editor => {
        editor.dispatch(
            editor.state.update({
                effects: get(languageStore).reconfigure(language)
            })
        );

        return editor;
    })
}

export function editorSetContent(content, path) {
    editorStore.update(editor => {
        editor.dispatch(
            editor.state.update({
                changes: {
                    from: 0,
                    to: editor.state.doc.length,
                    insert: content,
                },
            })
        );

        return editor;
    });

    const extension = path.split("/").at(-1).split(".").at(-1);
    editorSetHighlight(extension);
}

export function editorGetContent() {
    let content = "";

    editorStore.update(editor => {
        content = editor.state.sliceDoc();
        return editor;
    });

    return content;
}