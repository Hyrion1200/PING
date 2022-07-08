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
        z-index: 1;
        display: flex;
        flex-direction: column;
        position: absolute;
        right: 250px;
        top: 45px;
        width: 135px;
        margin: 0;
        padding: 0;
    }
</style>
