<script>
    // @ts-ignore
    import { gitAdd } from "/src/scripts/git";
    // @ts-ignore
    import { pathStore } from "/src/stores/PathStore";

    async function handleAdd() {
        await gitAdd($pathStore);
    }
</script>

<button id="add" on:click={handleAdd}> Git add </button>

<style>
    button {
        cursor: pointer;
        border: none;
        background-color: #202020;
        color: white;
        padding: 10px;
    }

    button:hover {
        background-color: #3d3d3d;
    }
</style>
