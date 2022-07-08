<script>
    import Popup from "../Popup/Popup.svelte";
    import { Spotify } from "sveltekit-embed";
    let popup;
    let load = false;

    const delay = ms => new Promise(res => setTimeout(res, ms));

    async function handle_switchPlaylist()
    {
        console.log(document.getElementsByClassName("spotify-sveltekit-embed"))
        let path = popup.answer;
        if (!path.includes("embed"))
        {
            // Ex : https://open.spotify.com/playlist/2XkyTxH09YORbTc8vG3POn

            // Insert "embed" after open.spotify.com/ to prevent cors error
            let arr = path.slice(0, 25) + "embed/" + path.slice(25);
            path = arr;
        }

        console.log(path)
        document.getElementsByClassName("spotify-sveltekit-embed")[0].setAttribute("src", path);

        if (!load)
        {
            await delay(1000); // To prevent bad request from showing
            load = true;
        }
        document.getElementById("spotify").style.visibility = "visible";
    }

    function askPlaylistPath()
    {
        popup.prompt(handle_switchPlaylist);
    }
</script>

<div>
    <h2>Spotify</h2>
<div id=spotify>
<Spotify
    spotifyLink=""
    ></Spotify>
</div>
<button id="open" on:click={askPlaylistPath} >Change Playlist</button>
</div>

<Popup bind:this={popup} sentence="Enter playlist link: " />


<style>
    button {
        cursor: pointer;
        border: none;
        background-color: #202020;
        color: white;
        padding: 10px;
        width: 100%;
    }

    button:hover {
        background-color: #3d3d3d;
    }

    h2 {
        color:white;
        text-align: center;
    }
    #spotify
    {
        visibility: hidden;
    }

    div {
        min-width: 250px;
        background-color: #121212;
    }
</style>
