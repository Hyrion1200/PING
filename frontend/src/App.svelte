<script>
	import Editor from "./components/Editor/Editor.svelte";
	import Header from "./components/Header/Header.svelte";
	import Path from "./components/Path/Path.svelte";
	import Spotify from "./components/Spotify/Spotify.svelte";
	import Tree from "./components/Tree/Tree.svelte";
	import Sound from "./components/sound/Sound.svelte";
	import Console from "./components/Console/Console.svelte";
</script>

<main>
	<Tree />
	<Sound />

	<div id="center">
		<Header />
		<Path />
		<Editor />
		<Console />
	</div>

	<Spotify />
</main>

<style>
	main {
		height: 100%;
		width: 100%;
		display: flex;
	}

	#center {
		display: flex;
		flex-flow: column;
		width: calc(100% - 500px);
	}
</style>
