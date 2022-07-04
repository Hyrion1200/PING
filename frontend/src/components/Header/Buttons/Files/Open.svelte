<script>
    import { editorStore } from "../../../Editor/EditorStore";
    let path;

    async function handleOpen() {
        //let url = window.BASE_URL + "/ide/files/open?path=" + path;
        let url = "http://localhost:8080/ide/files/open?path=" + path;
        console.log(url)
        console.log(editorStore)
        const resp = await fetch(url).then(function(response){ return response.json();}).then(
            function(data)
            {
                console.log(data.content)
                if (data.content != null)
                    editorStore.update(value => value = data.content);
            });
    }
</script>


<input type="text" bind:value={path}>
<button id="open" on:click={handleOpen}>Open</button>

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
