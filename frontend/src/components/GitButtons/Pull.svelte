<script>
    async function handlePull() {
        let url = "http://localhost:8080/ide/git/pull";
        const resp = await fetch(url, { mode: 'no-cors'})
        console.log(resp);
    }
</script>

<button id="pull" on:click={handlePull}>
    Git pull 
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