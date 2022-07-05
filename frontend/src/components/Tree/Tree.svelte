<script>
    // @ts-ignore
    import { project } from "/src/stores/project.js";
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

        return { name, path: root.path, relativePath };
    }

    $: root =
        $project !== undefined ? parseNodes($project.rootNode) : undefined;

    $: console.log("root", root);

    async function load() {
        if (root === undefined) {
            let loadedProject = await fetch(
                // @ts-ignore
                `${window.BASE_URL}/ide/load?path=.`
            );
            let jsonObj = await loadedProject.json();
            project.set(jsonObj.content);
        }
    }

    // // FIXME REMOVE ME
    // try {
    //     load();
    // } catch (e) {
    //     console.log(e);
    // }
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
        height: 100%;
        width: 20%;
        align-content: inherit;
    }

    .tree {
        padding-top: 15px;
        padding-left: 10%;
        height: 100%;
        background-color: #17212f;
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

    h2 {
        color: aliceblue;
        margin: 0;
        font-size: 12;
        text-align: center;
    }
</style>
