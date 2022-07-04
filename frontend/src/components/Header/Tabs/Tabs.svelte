<script>
    import Tab from "./Tab.svelte";
    import { tabStore, addTab } from "./TabStore.js";

    class TabConfig {
        constructor(name, path, content, on = false) {
            this.name = name;
            this.path = path;
            this.content = content;
            this.on = on;
        }
    }

    let tabs = [];

    addTab(new TabConfig("main.py", "src/main.py", "Salut Baptiste", true));
    addTab(new TabConfig("utils.py", "src/utils.py", "Salut Nico"));
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
