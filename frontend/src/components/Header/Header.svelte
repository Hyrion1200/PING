<script>
    import Buttons from "./Buttons/Buttons.svelte";
    import Tabs from "./Tabs/Tabs.svelte";

    let tabs = [
        { name: "main.c", on: true },
        { name: "main.h", on: false },
    ];
</script>

<div>
    <Tabs />
    <Buttons />
</div>

<style>
    div {
        height: 45px;
        display: flex;
        flex-wrap: nowrap;
        flex-direction: row;
        justify-content: space-between;
        background-color: #2d2d2d;
    }
</style>
