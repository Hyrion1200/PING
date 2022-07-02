<script>
    import Add from "../GitButtons/Add.svelte";
    import Commit from "../GitButtons/Commit.svelte";
    import Pull from "../GitButtons/Pull.svelte";
    import Push from "../GitButtons/Push.svelte";

</script>

<div id="git">
    <Add />
    <Commit />
    <Push />
    <Pull />
</div>

<style>
    div {
        background-color: #2d2d2d;
        display: none;
    }
</style>