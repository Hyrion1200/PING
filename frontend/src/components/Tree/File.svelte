<script>
    import { addTab, TabConfig } from "/src/components/Header/Tabs/TabStore.js";
    export let name;
    export let path;
    export let relativePath;
    // TODO - use store for settings as the theme
    let theme = "default";
    $: type = name.slice(name.lastIndexOf(".") + 1);

    async function getContent() {
        const response = await fetch(
            "http://localhost:8080/ide/files/open?path=" + relativePath
        );
        const report = await response.json();

        return report;
    }

    async function clickFile() {
        try {
            const report = await getContent();
            if (report.status === "ERROR") throw new Error(report.message);
            addTab(new TabConfig(name, path, report.content, false));
        } catch (e) {
            console.error("Failed to load file", e);
        }
    }
</script>

<button
    on:click={clickFile}
    style="background-image: url(/src/assets/themes/{theme}/icons/{type}.svg), url(/src/assets/themes/{theme}/icons/file.svg)"
    >{name}</button
>

<style>
    button {
        all: unset;
        cursor: pointer;
        color: rgb(198, 196, 196);
        padding: 0 0 0 1.5em;
        background: 0 0.1em no-repeat;
        background-size: contain;
    }
</style>
