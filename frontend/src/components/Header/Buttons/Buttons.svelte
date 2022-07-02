<script>
    import Git from "./Git/Git.svelte";

    let git = false;
</script>

<div id="main">
    <button>
        <img src="../../../../images/directory.png" alt="directory" />
    </button>
    <button><img src="../../../../images/maven.png" alt="maven" /></button>
    <button
        on:mousemove={() => {
            git = true;
        }}><img src="../../../../images/git.png" alt="git" /></button
    >
    <button id="run">
        <img src="../../../../images/triangle.png" alt="triangle" />
    </button>
</div>

{#if git}
    <Git />
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
