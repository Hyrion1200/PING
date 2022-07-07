<script>
    // @ts-ignore
    import { openFile } from "/src/scripts/files.js";
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let popup;

    async function handleOpen() {
        await openFile(popup.answer);
    }

    function askFilePath() {
        popup.prompt(handleOpen);
    }
</script>

<Popup bind:this={popup} sentence="File path: " />

<button id="open" on:click={askFilePath}>Open</button>

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
