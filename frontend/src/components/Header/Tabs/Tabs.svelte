<script>
    import Tab from "./Tab.svelte";
    // @ts-ignore
    import { TabConfig, tabStore, addTab } from "/src/stores/TabStore.js";
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
