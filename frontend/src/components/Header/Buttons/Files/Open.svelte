<script>
    // @ts-ignore
    import { editorStore } from "/src/stores/EditorStore";
    // @ts-ignore
    import { TabConfig, addTab } from "/src/stores/TabStore.js";
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let popup;

    async function handleOpen() {
        //let url = window.BASE_URL + "/ide/files/open?path=" + path;
        let path = popup.answer;
        // @ts-ignore
        let url = `${window.BASE_URL}/ide/files/open?path=${path}`;
        console.log(url);
        console.log(editorStore);
        const resp = await fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                if (data.status == "SUCCESS") {
                    addTab(new TabConfig(path, path, data.content));
                    editorStore.update((value) => (value = data.content));
                }
                console.log(data.content);
            });
    }

    function askFilePath() {
        popup.prompt(handleOpen);
    }
</script>

<Popup bind:this={popup} sentence="File path: " />

<button id="open" on:click={askFilePath}>Open</button>

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
