<script>
	import Editor from "./components/Editor/Editor.svelte";
	import Header from "./components/Header/Header.svelte";
	import Path from "./components/Path/Path.svelte";
	import Spotify from "./components/Spotify/Spotify.svelte";
	import Tree from "./components/Tree/Tree.svelte";
</script>

<main>
	<Tree />

	<div>
		<Header />
		<Path />
		<Editor />
	</div>

	<Spotify />
</main>

<style>
	main {
		height: 100vh;
		width: 100vw;
		display: flex;
	}

	div {
		display: flex;
		flex-direction: column;
		height: 100%;
		width: 100%;
	}
</style>
