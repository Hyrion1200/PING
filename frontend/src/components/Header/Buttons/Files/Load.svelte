<script>
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";
    // @ts-ignore
    import { editorStore } from "/src/stores/EditorStore";
    // @ts-ignore
    import { project } from "/src/stores/project.js";

    let popup;

    async function handleLoad() {
        let path = popup.answer;
        //let url = window.BASE_URL + "/ide/files/open?path=" + path;
        // @ts-ignore
        let url = `${window.BASE_URL}/ide/load?path=${path}`;
        console.log(url);
        console.log(editorStore);
        const resp = await fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                if (data.status == "SUCCESS") {
                    project.set(data.content);
                }
                console.log(data.content);
            });
    }

    function askProjectPath() {
        popup.prompt(handleLoad);
    }
</script>

<Popup bind:this={popup} sentence="Project path: " />

<button id="open" on:click={askProjectPath}>Load Project</button>

<style>
    button {
        cursor: pointer;
        border: none;
        background-color: #202020;
        color: white;
        padding: 10px;
    }

    button:hover {
        background-color: #3d3d3d;
    }
</style>
