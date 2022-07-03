<script>
    import File from "./File.svelte";

    export let expanded = false;
    export let name;
    export let files;

    function toggle() {
        expanded = !expanded;
    }
</script>

<!-- TODO crop text depending on size -->
<span class:expanded on:click={toggle}>{name}</span>

{#if expanded}
    <ul>
        {#each files as file}
            <li>
                {#if file.files}
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
        padding: 0 0 0 1.5em;
        background: url(/src/assets/themes/default/icons/folder.svg) 0 0.1em
            no-repeat;
        background-size: 1em 1em;
        font-weight: bold;
        cursor: pointer;
    }

    .expanded {
        background-image: url(/src/assets/themes/default/icons/opened-folder.svg);
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
