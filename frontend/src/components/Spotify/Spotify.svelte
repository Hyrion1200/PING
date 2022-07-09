<script>
    import Popup from "../Popup/Popup.svelte";
    import { Spotify } from "sveltekit-embed";

    let popup;
    let load = false;
    let spotifyHeight = window.innerHeight - 150;

    const delay = (ms) => new Promise((res) => setTimeout(res, ms));

    async function handle_switchPlaylist() {
        console.log(document.getElementsByClassName("spotify-sveltekit-embed"));
        let path = popup.answer;
        if (!path.includes("embed")) {
            // Ex : https://open.spotify.com/playlist/2XkyTxH09YORbTc8vG3POn

            // Insert "embed" after open.spotify.com/ to prevent cors error
            let arr = path.slice(0, 25) + "embed/" + path.slice(25);
            path = arr;
        }

        console.log(path);
        document
            .getElementsByClassName("spotify-sveltekit-embed")[0]
            .setAttribute("src", path);

        if (!load) {
            await delay(1000); // To prevent bad request from showing
            load = true;
        }
        document.getElementById("spotify").style.visibility = "visible";
    }

    function askPlaylistPath() {
        popup.prompt(handle_switchPlaylist);
    }
</script>

<div class="spotify-container">
    <img src="images/spotify.png" alt="spotify" />
    <button id="open" on:click={askPlaylistPath}>Change Playlist</button>
    <div id="spotify">
        <Spotify width="250" height={spotifyHeight.toString()} spotifyLink="" />
    </div>
</div>

<Popup bind:this={popup} sentence="Enter playlist link: " />

<style>
    img {
        height: 100px;
        padding: 10px;
    }

    button {
        cursor: pointer;
        border: none;
        background-color: #202020;
        color: white;
        padding: 10px;
        height: 50px;
        width: 100%;
        transition: 0.2s;
    }

    :global(body.dark-mode) button {
        background-color: darkgrey;
    }

    :global(body.dark-mode) button:hover {
        color: black;
        background-color: #00f269;
    }

    button:hover {
        background-color: #00f269;
        color: black;
    }

    #spotify {
        visibility: hidden;
        width: 250px;
    }

    .spotify-container {
        display: flex;
        flex-direction: column;
        width: 250px;
        background-color: #121212;
    }

    :global(body.dark-mode) .spotify-container {
        background-color: grey;
    }
</style>
