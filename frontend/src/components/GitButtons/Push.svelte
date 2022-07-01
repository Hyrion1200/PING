<script>
    async function handlePush() {
        let url = "http://localhost:8080/ide/git/push";
        const resp = await fetch(url, { mode: 'no-cors'})
        console.log(resp);
    }
</script>

<button id="push" on:click={handlePush}>
        Git push 
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