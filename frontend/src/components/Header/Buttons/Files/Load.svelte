<script>
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";
    // @ts-ignore
    import { loadProject } from "/src/scripts/files.js";

    let popup;

    async function handleLoad() {
        await loadProject(popup.answer);
    }

    function askProjectPath() {
        popup.prompt(handleLoad);
    }
</script>

<Popup bind:this={popup} sentence="Project path: " />

<button id="open" on:click={askProjectPath}>Load Project</button>

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
