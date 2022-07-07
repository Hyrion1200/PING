<script>
    // @ts-ignore
    import { consoleStore, outputStore } from "/src/stores/ConsoleStore.js";

    let Prompt = "/home/hugo ~ Prompt";
    let console = true;

    function switchConsole() {
        console = true;
    }

    function switchOutput() {
        console = false;
    }
</script>

<div>
    <nav>
        <button on:click={switchConsole}> Console </button>
        <button on:click={switchOutput}> Output </button>
    </nav>

    {#if console}
        <p>{$consoleStore}</p>
    {:else}
        <p>{$outputStore}</p>
    {/if}
</div>

<style>
    div {
        min-height: 200px;
        background-color: #252525;
        margin: 0;
        padding: 0;
    }

    nav {
        display: flex;
        flex-direction: row;
        margin: 0;
        padding: 0;
    }

    button {
        border: none;
        padding: 5px;
        width: 50%;
        margin: 0;
        background-color: #e0d6b1;
        cursor: pointer;
    }

    button:hover {
        background-color: #bcb086;
        color: white;
        transition: 0.3s;
    }

    p {
        padding: 10px;
        margin: 0;
        color: white;
    }
</style>
