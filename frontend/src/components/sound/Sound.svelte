<script>
    import { settings } from "/src/stores/SettingsStore.js";
    let saxophone = new Audio("sounds/saxophone.mp3");
    let trumpet = new Audio("sounds/trompette.mp3");

    async function blindHandler(e) {
        if (e.key.match(/^[a-z]$/i)) {
            let path = "sounds/" + e.key.toLowerCase() + ".mp3";
            let sound = new Audio(path);
            sound.play();
        }
    }

    async function musicHandler(e) {
        if (e.key == "a") trumpet.play();
        if (e.key == "p") saxophone.play();
    }

    $: {
        if (!$settings || $settings.blindMode) {
            window.addEventListener("keydown", blindHandler);
        } else {
            window.removeEventListener("keydown", blindHandler);
        }
    }

    $: {
        if ($settings && $settings.musicMode) {
            window.addEventListener("keydown", musicHandler);
        } else {
            window.removeEventListener("keydown", musicHandler);
        }
    }
</script>
