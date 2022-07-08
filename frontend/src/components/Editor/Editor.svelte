<script>
    import {
        editorStore,
        languageStore,
        // @ts-ignore
    } from "/src/stores/EditorStore";

    import { EditorState, Compartment } from "@codemirror/state";
    import { EditorView, keymap } from "@codemirror/view";
    import { basicSetup } from "codemirror";
    import { indentWithTab } from "@codemirror/commands";
    import { syntaxHighlighting, indentUnit } from "@codemirror/language";
    import { vim } from "@replit/codemirror-vim";
    import { oneDarkTheme, oneDarkHighlightStyle } from "./theme";

    import { onMount } from "svelte";

    $languageStore = new Compartment();

    let editorState = EditorState.create({
        extensions: [
            $languageStore.of([]),
            vim(),
            basicSetup,
            keymap.of([indentWithTab]),
            oneDarkTheme,
            syntaxHighlighting(oneDarkHighlightStyle),
            indentUnit.of("    "),
        ],
    });

    let editorParent;

    onMount(() => {
        $editorStore = new EditorView({
            state: editorState,
            parent: editorParent,
        });
    });
</script>

<div bind:this={editorParent} />

<style>
    div {
        height: calc(100% - 200px - 85px);
        background-color: black;
    }
</style>
