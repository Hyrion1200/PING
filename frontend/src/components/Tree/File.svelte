<script>
    // @ts-ignore
    import { addTab, TabConfig } from "/src/stores/TabStore.js";
    export let name;
    export let path;
    export let relativePath;
    // TODO - use store for settings as the theme
    let theme = "default";
    $: type = name.slice(name.lastIndexOf(".") + 1);

    async function getContent() {
        const response = await fetch(
            // @ts-ignore
            `${window.BASE_URL}/ide/files/open?path=${relativePath}`
        );
        const report = await response.json();

        return report;
    }

    async function clickFile() {
        try {
            const report = await getContent();
            if (report.status === "ERROR") throw new Error(report.message);
            addTab(new TabConfig(name, path, report.content));
        } catch (e) {
            console.error("Failed to load file", e);
        }
    }
</script>

<button
    on:click={clickFile}
    style="background-image: url(assets/themes/{theme}/icons/{type}.svg), url(assets/themes/{theme}/icons/file.svg)"
    >{name}</button
>

<style>
    button {
        all: unset;
        cursor: pointer;
        color: #ffffe0;
        padding: 0 0 0 1.5em;
        background: 0 0.1em no-repeat;
        background-size: contain;
    }
</style>
