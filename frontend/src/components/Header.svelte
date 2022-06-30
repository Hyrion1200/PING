<script>
    let tabs = [
        { name: "main.c", on: true },
        { name: "main.h", on: false },
    ];
</script>

<div>
    <ul>
        {#each tabs as tab}
            {#if tab.on}
                <li style="border-color: #4093bd">{tab.name}</li>
            {:else}
                <li>{tab.name}</li>
            {/if}
        {/each}
    </ul>

    <nav>
        <button> Open </button>
        <button> Close </button>
        <button id="run"> Run </button>
    </nav>
</div>

<style>
    div {
        display: flex;
        flex-wrap: nowrap;
        flex-direction: row;
        justify-content: space-between;
        background-color: #2d2d2d;
    }

    button {
        cursor: pointer;
        border: none;
        background-color: #2d2d2d;
        color: white;
        padding: 10px;
        border-bottom: 1px solid #2d2d2d;
    }

    ul {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        margin: 0;
        padding: 0;
        list-style: none;
    }

    li {
        margin: 0;
        color: grey;
        display: inline-block;
        padding: 10px;
        border-bottom: 1px solid #2d2d2d;
    }

    li:hover {
        border-color: white;
        cursor: pointer;
        transition: 0.1s;
        background-color: #3d3d3d;
    }

    button:hover {
        background-color: #3d3d3d;
    }

    #run {
        background-color: #0cc182;
        color: #424949;
    }
</style>
