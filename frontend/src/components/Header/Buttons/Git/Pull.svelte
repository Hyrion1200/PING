<script>
    // @ts-ignore
    import Popup from "/src/components/Popup/Popup.svelte";

    let text = "default";
    let usernamePopup;
    let passwordPopup;

    async function handlePull() {
        let user = usernamePopup.answer;
        let password = passwordPopup.answer;
        // @ts-ignore
        let url = `${window.BASE_URL}/ide/git/pull?user=${user}&password=${password}`;
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

    function askUsername() {
        usernamePopup.prompt(askPassword);
    }

    function askPassword() {
        passwordPopup.prompt(handlePull);
    }
</script>

<Popup bind:this={usernamePopup} sentence="Git username: " />
<Popup bind:this={passwordPopup} sentence="Git password: " />
<button id="pull" on:click={askUsername}> Git pull </button>

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
