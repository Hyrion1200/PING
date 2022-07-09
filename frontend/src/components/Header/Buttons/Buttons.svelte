<script>
    import Git from "./Git/Git.svelte";
    import Files from "./Files/Files.svelte";
    // @ts-ignore
    import { outputStore } from "/src/stores/ConsoleStore";
    // @ts-ignore
    import { pathStore } from "/src/stores/PathStore";
    import {
        editorSetDarkTheme,
        editorGetContent,
        // @ts-ignore
    } from "/src/stores/EditorStore";
    // @ts-ignore
    import { saveFile } from "/src/scripts/files";

    var path = "path";
    pathStore.subscribe((string) => {
        path = string;
    });

    let git = false;
    let files = false;

    function displayGit() {
        git = !git;
        files = false;
    }

    function displayFiles() {
        files = !files;
        git = false;
    }

    function toggle_light_mode() {
        window.document.body.classList.toggle("dark-mode");
        editorSetDarkTheme();
    }

    async function run() {
        //get the current file path
        if (path == "") {
            outputStore.set(
                "No file is currently opened, open a file in the editor to run it."
            );
            return;
        }

        saveFile($pathStore, editorGetContent());

        // @ts-ignore
        fetch(`${window.BASE_URL}/ide/files/exec?path=${path}`)
            .then((response) => response.json())
            .then((data) => {
                outputStore.set(data.content);
            });
    }
</script>

<div id="main">
    <button on:click={toggle_light_mode}> Theme </button>
    <button on:click={displayFiles}>
        <img src="images/directory.png" alt="directory" />
    </button>
    <button on:click={displayGit}><img src="images/git.png" alt="git" /></button
    >
    <button id="run" on:click={run}>
        <img src="images/triangle.png" alt="triangle" />
    </button>
</div>

{#if git}
    <Git />
{/if}

{#if files}
    <Files />
{/if}

<style>
    div {
        display: flex;
        flex-wrap: nowrap;
        width: 200px;
    }

    img {
        width: 25px;
        height: 25px;
    }

    button {
        cursor: pointer;
        border: none;
        color: white;
        padding: 10px;
        background-color: #5d5d5d;
        margin: 0;
    }

    button:hover {
        background-color: #9d9d9d;
        transition: 0.1s;
    }

    #run:hover {
        background-color: #0cc182;
        transition: 0.1s;
    }
</style>
