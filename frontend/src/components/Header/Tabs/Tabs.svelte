<script>
    import Tab from "./Tab.svelte";
    import { TabConfig, tabStore, addTab } from "./TabStore.js";

    let tabs = [];

    addTab(new TabConfig("main.py", "src/main.py", "Salut Baptiste", true));
    addTab(new TabConfig("utils.py", "src/utils.py", "Salut Nico"));
    addTab(new TabConfig("main.py", "src/main.py", "Salut Baptiste", true));
</script>

<ul>
    {#each $tabStore as tab}
        <Tab tabConfig={tab} />
    {/each}
</ul>

<style>
    ul {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        margin: 0;
        padding: 0;
        list-style: none;
    }
</style>
