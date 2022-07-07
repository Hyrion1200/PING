<script>
    // @ts-ignore
    import { gitPull } from "/src/scripts/git";
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let usernamePopup;
    let passwordPopup;

    function askUsername() {
        usernamePopup.prompt(askPassword);
    }

    function askPassword() {
        passwordPopup.prompt(gitPull);
    }
</script>

<Popup bind:this={usernamePopup} sentence="Git username: " />
<Popup bind:this={passwordPopup} sentence="Git password: " />
<button id="pull" on:click={askUsername}> Git pull </button>

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
