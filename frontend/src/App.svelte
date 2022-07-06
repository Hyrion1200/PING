<script>
	import Editor from "./components/Editor/Editor.svelte";
	import Header from "./components/Header/Header.svelte";
	import Path from "./components/Path/Path.svelte";
	import Spotify from "./components/Spotify/Spotify.svelte";
	import Tree from "./components/Tree/Tree.svelte";
	import Output from "./components/Console/Output.svelte";
	import Terminal from "./components/Console/Terminal.svelte";
	import ConsoleHeader from "./components/Console/ConsoleHeader.svelte";
	import Sound from "./components/sound/Sound.svelte";
</script>

<main>
	<Tree />
	<Sound />

	<div>
		<Header />
		<Path />
		<Editor />
		<ConsoleHeader />
		<div id="bottom">
			<Terminal />
			<Output />
		</div>
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
	#bottom {
		height: 20%;
		width: 100%;
	}
</style>
