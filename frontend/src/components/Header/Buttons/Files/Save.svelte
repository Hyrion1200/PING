<script>
    // @ts-ignore
    import { saveFile } from "/src/scripts/files.js";
    // @ts-ignore
    import { editorGetContent } from "/src/stores/EditorStore";
    // @ts-ignore
    import { pathStore } from "/src/stores/PathStore.js";

    async function handleSave() {
        await saveFile($pathStore, editorGetContent());
    }
</script>

<button id="save" on:click={handleSave}>Save</button>

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
