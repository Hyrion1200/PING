<script>
    import { editorContent } from "../../../Editor/EditorStores";
    let path;

    async function handleSave() {
        //let url = window.BASE_URL + "/ide/files/open?path=" + path;
        let url = "http://localhost:8080/ide/files/save?path=" + path;
        console.log(url)
        console.log($editorContent)
        const resp = await fetch(url, {
            method: 'POST',
            body: $editorContent
        }).then(function(response){ return response.json();}).then(
            function(data)
            {
                console.log(data)
            });
    }
</script>


<input type="text" bind:value={path}>
<button id="save" on:click={handleSave}>Save</button>

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

