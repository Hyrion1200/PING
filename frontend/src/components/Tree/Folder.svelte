<script>
    import File from "./File.svelte";

    export let expanded = false;
    export let name;
    export let children;

    function toggle() {
        expanded = !expanded;
    }
</script>

<!-- TODO crop text depending on size -->
<span class:expanded on:click={toggle}>{name}</span>

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

<!-- TODO background image use variables -->
<style>
    span {
        color: rgb(198, 196, 196);
        padding: 0 0 0 1.5em;
        background: url(assets/themes/default/icons/folder.svg) 0 0.1em
            no-repeat;
        background-size: 1em 1em;
        font-weight: bold;
        cursor: pointer;
    }

    .expanded {
        background-image: url(assets/themes/default/icons/opened-folder.svg);
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
