<script>
    // @ts-ignore
    import { saveFile } from "/src/scripts/files";
    // @ts-ignore
    import { saveTabContent } from "/src/stores/TabStore";
    // @ts-ignore
    import { pathStore } from "/src/stores/PathStore.js";
    // @ts-ignore
    import { removeTab, switchTab } from "/src/stores/TabStore";
    // @ts-ignore
    import { editorSetContent, darktheme } from "/src/stores/EditorStore";

    export let tabConfig;
    let li;

    if (tabConfig.on) {
        editorSetContent(tabConfig.content, tabConfig.path);
        pathStore.set(tabConfig.path);
    }

    function tabClick() {
        switchTab(tabConfig);
        event.stopPropagation();
    }

    function closeClick(event) {
        removeTab(tabConfig);
        event.stopPropagation();
    }

    $: {
        if (li) {
            if (tabConfig.on) {
                li.style.borderColor = "white";
            } else if ($darktheme) {
                li.style.borderColor = "#2d2d2d";
            } else {
                li.style.borderColor = "darkgrey";
            }
        }
    }
</script>

<li bind:this={li} on:click={tabClick}>
    {tabConfig.name}
    <button id="close" on:click={closeClick}>
        <img src="images/cross.png" alt="cross" />
    </button>
</li>

<style>
    img {
        width: 15px;
        margin: 0;
        padding: 0;
    }

    button {
        visibility: hidden;
        position: relative;
        right: -5px;
        border: none;
        background-color: inherit;
        padding: 0;
        cursor: pointer;
    }

    li {
        display: flex;
        margin: 0;
        color: grey;
        height: 45px;
        border-bottom: 1px solid #2d2d2d;
        cursor: pointer;
        padding: 10px 10px 10px 25px;
    }

    :global(body.dark-mode) li {
        background-color: darkgrey;
        color: black;
    }

    li:hover button {
        visibility: visible;
    }

    li:hover {
        background-color: #3d3d3d;
        transition: 0.1s;
    }

    :global(body.dark-mode) li:hover {
        background-color: grey;
        transition: 0.1s;
    }
</style>
