<script>
    import {
        editorStore,
        languageStore,
        // @ts-ignore
    } from "/src/stores/EditorStore";

    // @ts-ignore
    import { EditorState, Compartment } from "@codemirror/state";
    // @ts-ignore
    import { EditorView, keymap } from "@codemirror/view";
    // @ts-ignore
    import { basicSetup } from "codemirror";
    // @ts-ignore
    import { indentWithTab } from "@codemirror/commands";
    // @ts-ignore
    import { syntaxHighlighting, indentUnit } from "@codemirror/language";
    // @ts-ignore
    import { vim } from "@replit/codemirror-vim";
    // @ts-ignore
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
