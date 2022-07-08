<script>
    import File from "./File.svelte";

    export let expanded = false;
    export let name;
    export let children;
    let theme = "default";
    let folder;
    $: folder = expanded ? "opened-folder.svg" : "folder.svg";

    function toggle() {
        expanded = !expanded;
    }
</script>

<!-- TODO crop text depending on size -->
<span
    class:expanded
    style="background-image: url(assets/themes/{theme}/icons/{folder});"
    on:click={toggle}
>
    {name}</span
>

{#if expanded}
    <ul>
        {#each children as file}
            <li>
                {#if file.children}
                    <svelte:self {...file} />
                {:else}
                    <File {...file} />
                {/if}
            </li>
        {/each}
    </ul>
{/if}

<style>
    span {
        color: #ffffe0;
        padding: 0 0 0 1.5em;
        background: 0 0.1em no-repeat;
        background-size: 1em 1em;
        font-weight: bold;
        cursor: pointer;
    }

    ul {
        padding: 0.2em 0 0 0.5em;
        margin: 0 0 0 0.5em;
        list-style: none;
    }

    li {
        padding: 0.2em 0;
    }
</style>
