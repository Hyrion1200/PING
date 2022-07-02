<script>
    import Modal from "./Modal.svelte";

    let isOpenModal = false;
    let text = "default";
    async function handleAdd() {
        var path = "test";
        let url = "http://localhost:8080/ide/git/add?path=" + path;

        const resp = await fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                if (data.status === "ERROR") {
                    console.log("Error");
                    isOpenModal = true;
                    text = data.message;
                }
                document.getElementById("editor").textContent = data.content;
            });
    }
</script>

<button id="add" on:click={handleAdd}> Git add </button>
<Modal {isOpenModal} {text} />

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
