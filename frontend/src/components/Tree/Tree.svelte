<script>
    // @ts-ignore
    import { project } from "/src/stores/ProjectStore.js";
    import Folder from "./Folder.svelte";

    function parseNodes(root, i = 0) {
        let name = root.path;
        if (name.endsWith("/")) {
            console.log(name);
            name = name.substring(0, name.length - 1);
            console.log(name);
        }
        const nameSplitted = name.split("/");
        name = nameSplitted[nameSplitted.length - 1];
        let relativePath = nameSplitted
            .slice(nameSplitted.length - i - 1)
            .join("/");

        let children = undefined;
        if (root.type === "FOLDER") {
            children = root.children.map((child) => {
                return parseNodes(child, i + 1);
            });
            return { name, children };
        }

        return { name, path: root.path.slice(7), relativePath }; // Remove file:// before absolute path
    }

    $: root =
        $project !== undefined ? parseNodes($project.rootNode) : undefined;

    $: console.log("root", root);
</script>

<div class="container">
    <div class="project-name">
        <h2>
            {#if root === undefined} No project {:else} {root.name} {/if}
        </h2>
    </div>
    {#if root === undefined}
        <!-- TODO component to open a project-->
        <div id="no_project">
            <p>No project loaded</p>
        </div>
    {:else}
        <!-- TODO enable showing or not showing hidden files.-->
        <div class="tree">
            <Folder name={root.name} children={root.children} expanded={true} />
        </div>
    {/if}
</div>

<style>
    /* TODO propper gestion of resizing and simply scrolling */
    .container {
        background-color: #17212f;
        min-width: 250px;
        max-width: 250px;
        max-height: 100%;
        align-content: inherit;
    }
    :global(body.dark-mode) .container{
        background-color: lightgray;
        color: black;
    }

    .tree {
        max-height: calc(100% - 100px);
        overflow: scroll;
        padding-top: 15px;
        padding-left: 10%;
        background-color: #17212f;
    }

    :global(body.dark-mode) .tree{
        background-color: lightgray;
        color: black;
    }

    .tree::-webkit-scrollbar {
        width: 10px;
        height: 10px;
    }

    .tree::-webkit-scrollbar-corner {
        background: rgba(0, 0, 0, 0);
    }

    .tree::-webkit-scrollbar-thumb {
        background-color: #bcb086;
        border-radius: 6px;
        border: 4px solid rgba(0, 0, 0, 0);
        background-clip: content-box;
        min-width: 32px;
        min-height: 32px;
    }

    .tree::-webkit-scrollbar-track {
        background-color: rgba(0, 0, 0, 0);
    }

    .project-name {
        display: flex;
        align-items: center;
        justify-content: center;
        padding-left: 0.7%;
        height: 45px;
        border-bottom: 1px solid #ffff;
        background-color: #17212f;
    }
    
    :global(body.dark-mode) .project-name{
        background-color: lightgray;
        color: black;
        border-bottom: 1px solid black;
    }

    #no_project {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 95%;
        background-color: #17212f;
    }

    :global(body.dark-mode) #no_project{
        background-color: lightgray;
        color: black;
    }



    p {
        color: #ffffe0;
        font-size: 1em;
        font-weight: bold;
        text-align: center;
    }

    :global(body.dark-mode) p{
        background-color: lightgray;
        color: black;
    }

    h2 {
        color: #ffffe0;
        margin: 0;
        font-size: 12;
        text-align: center;
    }

    :global(body.dark-mode) h2{
        background-color: lightgray;
        color: black;
    }


</style>
