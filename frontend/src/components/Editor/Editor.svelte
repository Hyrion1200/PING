<script>
    // @ts-ignore
    import { editorStore, editorAdd } from "/src/stores/EditorStore";

    let lines = "";

    let numberArea;
    let editorArea;

    $: {
        lines = "";
        for (let i = 1; i < $editorStore.split("\n").length + 1; i++)
            lines += i + "\n";
    }

    function handleScroll() {
        numberArea.scrollTop = editorArea.scrollTop;
    }

    function handleTab(event) {
        //editorContent.update(value => value = event.currentTarget.value);
        if (event.key !== "Tab") return;

        event.preventDefault();

        editorAdd("    ");
    }
</script>

<div>
    <textarea id="number" bind:this={numberArea} bind:value={lines} readonly />
    <textarea
        id="editor"
        bind:this={editorArea}
        bind:value={$editorStore}
        on:keydown={handleTab}
        on:scroll={handleScroll}
        spellcheck="false"
    />
</div>

<style>
    div {
        display: flex;
        height: 70%;
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
