<script>
    import Modal from "./Modal.svelte";

    let isOpenModal = false;
    let text = "default";

    async function handlePull() {
        let url = "http://localhost:8080/ide/git/pull";
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

<button id="pull" on:click={handlePull}> Git pull </button>
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
