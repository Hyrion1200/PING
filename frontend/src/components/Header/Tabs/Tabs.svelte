<script>
    import { onMount } from "svelte";

    import Tab from "./Tab.svelte";
    // @ts-ignore
    import { tabStore } from "/src/stores/TabStore.js";

    let tabs;

    onMount(() => {
        console.log(tabs);

        tabs.addEventListener("wheel", (evt) => {
            evt.preventDefault();
            tabs.scrollLeft += evt.deltaY;
        });
    });
</script>

<ul bind:this={tabs}>
    {#each $tabStore as tab}
        <Tab tabConfig={tab} />
    {/each}
</ul>

<style>
    ul {
        width: calc(100% - 135px);
        max-width: calc(100% - 135px);
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        margin: 0;
        padding: 0;
        list-style: none;
        overflow: scroll;
        scrollbar-width: none;
    }
</style>
