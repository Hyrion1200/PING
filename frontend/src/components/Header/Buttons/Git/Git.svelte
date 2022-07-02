<script>
    import Add from "./Add.svelte";
    import Commit from "./Commit.svelte";
    import Pull from "./Pull.svelte";
    import Push from "./Pull.svelte";
</script>

<div>
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
        top: 50px;
        width: 180px;
    }
</style>
