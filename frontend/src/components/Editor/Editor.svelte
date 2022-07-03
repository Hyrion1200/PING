<script>
    import { afterUpdate } from "svelte";
    import { editorContent } from "./EditorStores.js"
    let content = ""
    editorContent.subscribe(value => { content = value });
    let lines = "";

    let numberArea;
    let editorArea;

    $: {
        lines = "";
        for (let i = 1; i < content.split("\n").length + 1; i++)
            lines += i + "\n";
    }

    afterUpdate(() => {
        numberArea.scrollTop = editorArea.scrollTop;
    });

    function handleTab(event) {
        if (event.key !== "Tab") return;

        event.preventDefault();

        content += "    ";
    }

</script>

<div>
    <textarea id="number" bind:this={numberArea} bind:value={lines} readonly />
    <textarea
        id="editor"
        bind:this={editorArea}
        bind:value={content}
        on:keydown={handleTab}
        spellcheck="false"
    />
</div>

<style>
    div {
        display: flex;
        height: 100%;
    }

    textarea {
        font-size: 15px;
        border: none;
        resize: none;
        margin: 0;
        background-color: #2d2d2d;
        color: white;
        outline: none;
    }

    #editor {
        padding: 5px 20px 5px 20px;
        width: 100%;
    }

    #number {
        overflow: hidden;
        padding: 5px;
        width: 3em;
        text-align: center;
        border-right: 1px solid grey;
    }
</style>
