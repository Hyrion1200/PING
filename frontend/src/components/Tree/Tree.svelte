<script>
    import { project } from "/src/stores/project.js";
    import Folder from "./Folder.svelte";

    function parseNodes(root) {
        // TODO crop names on relative path
        let name = root.path;
        let children = undefined;
        if (root.type === "FOLDER") {
            children = root.children.map((child) => {
                return parseNodes(child);
            });
        }

        return { name, children };
    }

    $: root =
        $project !== undefined ? parseNodes($project.rootNode) : undefined;

    $: console.log("root", root);

    async function load() {
        if (root === undefined) {
            let loadedProject = await fetch(
                "http://localhost:8080/ide/load?path=."
            );
            let jsonObj = await loadedProject.json();
            project.set(jsonObj.content);
        }
    }

    try {
        load();
    } catch (e) {
        console.log(e);
    }
</script>

<div class="tree">
    {#if root === undefined}
        <!-- TODO component to open a project-->
        <div id="no_project">
            <p>No project loaded</p>
        </div>
    {:else}
        <!-- TODO enable showing or not showing hidden files.-->
        <Folder name="Home" children={root.children} expanded={true} />
    {/if}
</div>

<style>
    .tree {
        padding-top: 15px;
        padding-left: 0.7%;
        height: 100%;
        width: 20%;
        background-color: #17212f;
    }

    #no_project {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 80%;
        width: 95%;
        background-color: #17212f;
    }

    p {
        color: rgb(198, 196, 196);
        font-size: 1em;
        font-weight: bold;
        text-align: center;
    }
</style>
