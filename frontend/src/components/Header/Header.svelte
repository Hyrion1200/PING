<script>
    import Buttons from "./Buttons/Buttons.svelte";
    import Tab from "./Tabs/Tab.svelte";

    let tabs = [
        { name: "main.c", on: true },
        { name: "main.h", on: false },
    ];
</script>

<div>
    <ul>
        {#each tabs as tab}
            <Tab tabName={tab.name} on={tab.on} />
        {/each}
    </ul>

    <Buttons />
</div>

<style>
    div {
        display: flex;
        flex-wrap: nowrap;
        flex-direction: row;
        justify-content: space-between;
        background-color: #2d2d2d;
    }

    ul {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        margin: 0;
        padding: 0;
        list-style: none;
    }
</style>
