<script>
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let text = "default";
    let popup;

    async function handleCommit() {
        let msg = popup.answer;

        if (msg === null) return;

        // @ts-ignore
        let url = `${window.BASE_URL}/ide/git/commit?message=${msg}`;
        const resp = await fetch(url)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                if (data.status === "ERROR") {
                    console.log("Error");
                    text = data.message;
                }
                document.getElementById("editor").textContent = data.content;
            });
    }

    function askCommitMessage() {
        popup.prompt(handleCommit);
    }
</script>

<Popup bind:this={popup} sentence="Commit message: " />

<button id="commit" on:click={askCommitMessage}> Git commit </button>

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

    ::placeholder {
        color: black;
    }
</style>
