<script>
    async function handleOpen() {
        var path = "README.md";
        let url = "http://localhost:8080/ide/files/open?path=" + path;
        console.log(url);
        const resp = await fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                console.log(data.content);
                document.getElementById("editor").textContent = data.content;
            });
    }
</script>

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
