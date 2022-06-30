<script>
	import Editor from "./components/Editor.svelte";
	import Header from "./components/Header.svelte";
	import Path from "./components/Path.svelte";
	import Spotify from "./components/Spotify.svelte";
	import Tree from "./components/Tree.svelte";
	import Git from "./components/Git.svelte";
</script>

<main>
	<Tree />

	<div>
		<Header />
		<Path />
		<Editor />
		<Git />
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
