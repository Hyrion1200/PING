<script>
    export let name;
    // TODO - use store for settings as the theme
    let theme = "default";
    $: type = name.slice(name.lastIndexOf(".") + 1);
</script>

<span
    style="background-image: url(/src/assets/themes/{theme}/icons/{type}.svg), url(/src/assets/themes/{theme}/icons/file.svg)"
    >{name}</span
>

<style>
    span {
        color: rgb(198, 196, 196);
        padding: 0 0 0 1.5em;
        background: 0 0.1em no-repeat;
        background-size: 1em 1em;
    }
</style>
