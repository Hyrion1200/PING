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
    import { editorSetContent } from "/src/stores/EditorStore";

    export let tabConfig;
    let li;

    if (tabConfig.on) {
        editorSetContent(tabConfig.content, tabConfig.path);
        pathStore.set(tabConfig.path);
    }

    function tabHover() {
        li.style.backgroundColor = "#3d3d3d";
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

    li:hover button {
        visibility: visible;
    }
    li:hover {
        transition: 0.1s;
    }
</style>
