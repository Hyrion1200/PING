<script>
    // @ts-ignore
    import { editorStore } from "/src/stores/EditorStore";
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let popup;

    async function handleSave() {
        //let url = window.BASE_URL + "/ide/files/open?path=" + path;
        let path = popup.answer;
        // @ts-ignore
        let url = `${window.BASE_URL}/ide/files/save?path=${path}`;
        console.log(url);
        console.log($editorStore);
        const resp = await fetch(url, {
            method: "POST",
            body: $editorStore,
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                console.log(data);
            });
    }

    function askFilePath() {
        popup.prompt(handleSave);
    }
</script>

<Popup bind:this={popup} sentence="Path of the file to save: " />

<button id="save" on:click={askFilePath}>Save</button>

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
