<script>
    // @ts-ignore
    import { gitPush } from "/src/scripts/git";
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let usernamePopup;
    let passwordPopup;

    function askUsername() {
        usernamePopup.prompt(askPassword);
    }

    function askPassword() {
        passwordPopup.prompt(gitPush);
    }
</script>

<Popup bind:this={usernamePopup} sentence="Git username: " />
<Popup bind:this={passwordPopup} sentence="Git password: " />

<button id="push" on:click={askUsername}> Git push </button>

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
