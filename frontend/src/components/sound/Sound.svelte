<script>
    let saxophone = new Audio("sounds/saxophone.mp3");
    let trumpet = new Audio("sounds/trompette.mp3");

    document.onkeydown = async function(e) {
        if (e.key == "a") {
            trumpet.play();
        }
        if (e.key == "p") {
            saxophone.play();
        }
        if (e.key.match(/^[a-z]$/i)) {
            let path = "sounds/" + e.key + ".mp3";
            let sound = new Audio(path);
            sound.play();
        }
    }
</script>
