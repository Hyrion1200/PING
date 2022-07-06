<script>
    import Add from "./Add.svelte";
    import Commit from "./Commit.svelte";
    import Pull from "./Pull.svelte";
    import Push from "./Push.svelte";
</script>

<div id="git">
    <Add />
    <Commit />
    <Pull />
    <Push />
</div>

<style>
    div {
        display: flex;
        flex-direction: column;
        position: absolute;
        right: 14.3%;
        top: 48.5px;
        width: 135px;
        margin: 0;
        padding: 0;
    }
</style>
