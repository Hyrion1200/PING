<script>
    // @ts-ignore
    import { saveFile } from "/src/scripts/files.js";
    // @ts-ignore
    import { editorStore } from "/src/stores/EditorStore";
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let popup;
    async function handleSave() {
        await saveFile(popup.answer, $editorStore);
    }

    function askFilePath() {
        popup.prompt(handleSave);
    }
</script>

<Popup bind:this={popup} sentence="Path of the file to save: " />

<button id="save" on:click={askFilePath}>Save</button>

<style>
    button {
        cursor: pointer;
        border: none;
        background-color: #202020;
        color: white;
        padding: 10px;
    }

    button:hover {
        background-color: #3d3d3d;
    }
</style>
