<script>
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

    function tabHoverEnter() {
        li.style.backgroundColor = "#3d3d3d";
    }

    function tabHoverLeave() {
        li.style.backgroundColor = "#2d2d2d";
    }

    function btnHoverEnter(event) {
        li.style.backgroundColor = "#EB4747";
        li.style.color = "black";
    }

    function btnHoverLeave(event) {
        li.style.backgroundColor = "#2d2d2d";
        li.style.color = "grey";
    }

    function tabClick(event) {
        switchTab(tabConfig);
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
    on:mouseenter={tabHoverEnter}
    on:mouseleave={tabHoverLeave}
>
    {tabConfig.name}
    <button
        on:click={closeClick}
        on:mouseenter={btnHoverEnter}
        on:mouseleave={btnHoverLeave}
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
        top: -8px;
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
        padding: 10px 10px 10px 30px;
        cursor: pointer;
    }

    li:hover button {
        visibility: visible;
    }
</style>
