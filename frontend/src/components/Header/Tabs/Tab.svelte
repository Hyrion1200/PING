<script>
    export let tabName;
    export let on;
</script>

{#if on}
    <li style="border-color: white;">{tabName}</li>
{:else}
    <li>{tabName}</li>
{/if}

<style>
    li {
        display: inline-block;
        margin: 0;
        color: grey;
        display: inline-block;
        height: 25px;
        padding: 10px;
        border-bottom: 1px solid #2d2d2d;
    }

    li:hover {
        border-color: white;
        cursor: pointer;
        transition: 0.1s;
        background-color: #3d3d3d;
    }
</style>
