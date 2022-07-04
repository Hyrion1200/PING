<script>
    import Open from "./Open.svelte";
    import Save from "./Save.svelte";
    import Load from "./Load.svelte";
</script>

<div>
    <Open />
    <Save />
    <Load />
</div>

<style>
    div {
        display: flex;
        flex-direction: column;
        position: absolute;
        right: 14.3%;
        top: 48.5px;
        width: 180px;
    }
</style>
