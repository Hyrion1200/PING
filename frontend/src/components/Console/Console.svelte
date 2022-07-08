<script>
    import {
        showOutput,
        consoleStore,
        outputStore,
        // @ts-ignore
    } from "/src/stores/ConsoleStore.js";

    function switchConsole() {
        $showOutput = false;
    }

    function switchOutput() {
        $showOutput = true;
    }
</script>

<div>
    <nav>
        {#if !$showOutput}
            <button
                style:background-color="#bcb086"
                style:color="white"
                on:click={switchConsole}
            >
                Console
            </button>
        {:else}
            <button on:click={switchConsole}> Console </button>
        {/if}

        {#if $showOutput}
            <button
                style:background-color="#bcb086"
                style:color="white"
                on:click={switchOutput}
            >
                Output
            </button>
        {:else}
            <button on:click={switchOutput}> Output </button>
        {/if}
    </nav>

    {#if !$showOutput}
        <p>{$consoleStore}</p>
    {:else}
        <p>{@html $outputStore}</p>
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
