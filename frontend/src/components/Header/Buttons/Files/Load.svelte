<script>
    import { editorStore } from "../../../Editor/EditorStore";
    import { project } from "/src/stores/project.js";
    import { TabConfig, tabStore, addTab } from "../../Tabs/TabStore.js";
    let path;

    async function handleLoad() {
        path = prompt("Enter a project path: ");
        if (path === undefined)
            return;
        //let url = window.BASE_URL + "/ide/files/open?path=" + path;
        let url = "http://localhost:8080/ide/load?path=" + path;
        console.log(url)
        console.log(editorStore)
        const resp = await fetch(url).then(function(response){ return response.json();}).then(
            function(data)
            {
                if (data.status == "SUCCESS")
                {
                    project.set(data.content)
                }
                console.log(data.content)
            });
    }
</script>


<button id="open" on:click={handleLoad}>Load Project</button>

<style>
    button {
        cursor: pointer;
        border: none;
        background-color: #2d2d2d;
        color: white;
        padding: 10px;
    }

    button:hover {
        background-color: #3d3d3d;
    }
</style>

