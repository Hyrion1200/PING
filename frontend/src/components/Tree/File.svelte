<script>
    export let name;
    // TODO - use store for settings as the theme
    export let theme = "default";
    $: type = name.slice(name.lastIndexOf(".") + 1);
</script>

<span
    style="background-image: url(/src/assets/themes/{theme}/icons/{type}.svg), url(/src/assets/themes/{theme}/icons/file.svg)"
    >{name}</span
>

<style>
    span {
        padding: 0 0 0 1.5em;
        background: 0 0.1em no-repeat;
        background-size: 1em 1em;
    }
</style>
