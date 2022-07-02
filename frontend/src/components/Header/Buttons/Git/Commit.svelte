<script>
    import Modal from "./Modal.svelte";

    let isOpenModal = false;
    let text = "default";
    let commitOpen = false;

    async function handleCommit() {
        isOpenModal = false;
        if (commitOpen) {
            return;
        }

        let msg = window.prompt("Commit message: ");

        if (msg === null) return;

        let url = "http://localhost:8080/ide/git/commit?message=" + msg;
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

<button id="commit" on:click={handleCommit}> Git commit </button>

<Modal {isOpenModal} {text} />

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

    ::placeholder {
        color: black;
    }
</style>
