<script>
    // @ts-ignore
    import { gitCommit } from "/src/scripts/git";

    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let popup;

    async function handleCommit() {
        await gitCommit(popup.answer);
    }

    function askCommitMessage() {
        popup.prompt(handleCommit);
    }
</script>

<Popup bind:this={popup} sentence="Commit message: " />

<button id="commit" on:click={askCommitMessage}> Git commit </button>

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

    ::placeholder {
        color: black;
    }
</style>
