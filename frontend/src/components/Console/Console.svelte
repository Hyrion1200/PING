<script>
    import {
        showOutput,
        consoleStore,
        outputStore,
        // @ts-ignore
    } from "/src/stores/ConsoleStore.js";

    import { Terminal } from "xterm";
    import { FitAddon } from "xterm-addon-fit";
    import { afterUpdate } from "svelte";

    import "xterm/css/xterm.css";

    let termParent;
    let term = new Terminal();
    let fitAddon = new FitAddon();

    term.loadAddon(fitAddon);

    term.onKey((val) => {
        term.write(val.key);
    });

    function switchConsole() {
        $showOutput = false;
    }

    function switchOutput() {
        $showOutput = true;
    }

    afterUpdate(() => {
        if (!$showOutput) {
            term.open(termParent);
            fitAddon.fit();
        }
    });
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
        <p id="terminalParent" bind:this={termParent} />
    {:else}
        <p id="output">{@html $outputStore}</p>
    {/if}
</div>

<style>
    div {
        height: 200px;
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
        height: 30px;
        border: none;
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
        margin: 0;
        color: white;
        height: calc(100% - 30px);
    }

    #output {
        padding: 10px;
    }

    #terminalParent {
        background-color: black;
        padding: 0;
    }
</style>
