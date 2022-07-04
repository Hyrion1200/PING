<script>
    import Tab from "./Tab.svelte";
    import { TabConfig, tabStore, addTab } from "./TabStore.js";
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
