<script>
    async function handleAdd() {
        var path = "test";
        let url = "http://localhost:8080/ide/git/add?path="+ path;
        const resp = await fetch(url, { mode: 'no-cors'})
        console.log(resp);
    }
</script>

<button id="add" on:click={handleAdd} >
    Git add
</button>

<style>
    button {
        cursor: pointer;
        border: none;
        background-color: #2d2d2d;
        border-radius: 2px;
        color: white;
        padding: 10px;
        border-bottom: 1px solid #2d2d2d;
    }

    button:hover {
        background-color: #3d3d3d;
    }
</style>