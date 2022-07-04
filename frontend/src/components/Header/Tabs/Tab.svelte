<script>
    import { editorStore } from "../../Editor/EditorStore.js";
    import { removeTab, saveTabContent, setTabOn } from "./TabStore";
    export let tabConfig;
    let li;

    if (tabConfig.on) {
        editorStore.set(tabConfig.content);
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
        saveTabContent($editorStore);
        editorStore.set(tabConfig.content);
        setTabOn(tabConfig);
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

<li bind:this={li} on:click={tabClick}>
    <span>
        {tabConfig.name}
        <button
            on:click={() => removeTab(tabConfig)}
            on:mouseenter={btnHoverEnter}
            on:mouseleave={btnHoverLeave}
        >
            <img src="../../../images/cross.png" alt="cross" />
        </button>
    </span>
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
        top: -5px;
        border: none;
        background-color: inherit;
        padding: 0;
    }

    button:hover {
        cursor: pointer;
    }

    li {
        margin: 0;
        color: grey;
        height: 25px;
        border-bottom: 1px solid #2d2d2d;
        padding: 10px 10px 10px 30px;
    }

    li:hover {
        border-color: white;
        transition: 0.1s;
        cursor: default;
        background-color: #3d3d3d;
    }

    li:hover button {
        visibility: visible;
    }
</style>
