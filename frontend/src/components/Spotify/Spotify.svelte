<script>
    import Popup from "../Popup/Popup.svelte";
    import { Spotify } from "sveltekit-embed";
    let popup;

    async function handle_switchPlaylist()
    {
        let path = popup.answer;
        if (!path.includes("embed"))
        {
            //https://open.spotify.com/playlist/2XkyTxH09YORbTc8vG3POn
            let arr = path.slice(0, 25) + "embed/" + path.slice(25);
            path = arr;
        }
        console.log(path)
        document.getElementsByClassName("spotify-sveltekit-embed")[0].setAttribute("src", path);
    }

    function askPlaylistPath()
    {
        popup.prompt(handle_switchPlaylist);
    }

    function disableSpotify()
    {
        document.getElementsByClassName("spotify-sveltekit-embed")[0].setAttribute("show", "false");
        console.log("Loaded");
    }
</script>
<!-- svelte-ignore a11y-missing-attribute -->
<div on:load|once={disableSpotify}>
    <h2>Spotify</h2>
<Spotify
    spotifyLink="playlist/3UDD3bVqCZWAoF6LhCwZ68"
    ></Spotify>
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

    div {
        min-width: 250px;
        background-color: #121212;
    }
</style>
