<script>
    import Git from "./Git/Git.svelte";
    import Files from "./Files/Files.svelte";
    import { output_content } from "../../../stores/OutputStore";
    import { pathStore } from "../../../stores/PathStore";

    var path = "path";
    pathStore.subscribe((string) => {
        path = string.substring(6);
    });

    let git = false;
    let files = false;
    let maven = false;

    function displayGit() {
        git = !git;
        files = false;
        maven = false;
    }

    function displayFiles() {
        files = !files;
        git = false;
        maven = false;
    }

    function displayMaven() {
        maven = !maven;
        files = false;
        git = false;
    }

    async function run() {
        //get the current file path
        var Content_header = "Executed file at " + path.substring(6) + ":\n";
        // @ts-ignore
        fetch(`${window.BASE_URL}/ide/files/exec?path=${path}`)
            .then((response) => response.json())
            .then((data) => {
                if (data) output_content.set(Content_header + data.content);
                else
                    output_content.set(
                        "Couldn't execute file at " + path + "<br/>"
                    );
            });
    }
</script>

<div id="main">
    <button on:click={displayFiles}>
        <img src="images/directory.png" alt="directory" />
    </button>
    <button on:click={displayMaven}
        ><img src="images/maven.png" alt="maven" /></button
    >
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
