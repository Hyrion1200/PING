<script>
    // @ts-ignore
    import { saveFile } from "/src/scripts/files";
    // @ts-ignore
    import { saveTabContent } from "/src/stores/TabStore";
    // @ts-ignore
    import { editorStore } from "/src/stores/EditorStore.js";
    // @ts-ignore
    import { pathStore } from "/src/stores/PathStore.js";
    // @ts-ignore
    import { removeTab, switchTab } from "/src/stores/TabStore";

    export let tabConfig;
    let li;

    if (tabConfig.on) {
        editorStore.set(tabConfig.content);
        pathStore.set(tabConfig.path);
    }

    function tabHover() {
        li.style.backgroundColor = "#3d3d3d";
    }

    function saveHover() {
        li.style.backgroundColor = "#51AC71";
        li.style.color = "black";
    }

    function closeHover() {
        li.style.backgroundColor = "#EB4747";
        li.style.color = "black";
    }

    function hoverLeave() {
        li.style.backgroundColor = "#2d2d2d";
        li.style.color = "grey";
    }

    function tabClick() {
        switchTab(tabConfig);
    }

    function closeClick(event) {
        handleSave(event);
        removeTab(tabConfig);
        event.stopPropagation();
    }

    async function handleSave(event) {
        saveTabContent(tabConfig);
        saveFile(tabConfig.path, tabConfig.content);
        event.stopPropagation();
    }

    $: {
        if (li) {
            if (tabConfig.on) {
                li.style.borderColor = "white";
            } else {
                li.style.borderColor = "#2d2d2d";
            }
        }
    }
</script>

<li
    bind:this={li}
    on:click={tabClick}
    on:mouseenter={tabHover}
    on:mouseleave={hoverLeave}
>
    <button
        id="save"
        on:click={handleSave}
        on:mouseenter={saveHover}
        on:mouseleave={hoverLeave}
        ><img src="images/save.png" alt="save" /></button
    >

    {tabConfig.name}

    <button
        id="close"
        on:click={closeClick}
        on:mouseenter={closeHover}
        on:mouseleave={hoverLeave}
    >
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
        border: none;
        background-color: inherit;
        padding: 0;
        cursor: pointer;
    }

    #close {
        right: -5px;
    }

    #save {
        left: -5px;
    }

    li {
        display: flex;
        margin: 0;
        color: grey;
        height: 45px;
        border-bottom: 1px solid #2d2d2d;
        padding: 10px;
        cursor: pointer;
    }

    li:hover button {
        visibility: visible;
    }
    li:hover {
        transition: 0.1s;
    }
</style>
