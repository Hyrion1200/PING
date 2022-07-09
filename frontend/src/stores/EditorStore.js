import {get, writable } from "svelte/store";

import { python } from "@codemirror/lang-python";
import { javascript } from "@codemirror/lang-javascript"
import { solarizedLight } from "cm6-theme-solarized-light";
import { gruvboxDark } from "cm6-theme-gruvbox-dark";

export let editorStore = writable();
export let languageStore = writable();
export let themeStore = writable();

export let darktheme = writable(true);

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

export function editorSetDarkTheme() {
    let theme;

    darktheme.set(!get(darktheme));

    if (get(darktheme)) {
        theme = gruvboxDark;
    } else {
        theme = solarizedLight;
    }

    editorStore.update(editor => {
        editor.dispatch(
            editor.state.update({
                effects: get(themeStore).reconfigure(theme)
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