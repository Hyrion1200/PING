<script>
    // let trumpet = new Audio("trompette.mp3");
    let saxophone = new Audio("../../samples/saxophone.mp3");
    let trumpet = new Audio("../../samples/trompette.mp3");

    document.onkeydown = async function(e) {
        if (e.key == "q") {
            trumpet.play();
        }
        if (e.key == "s") {
            saxophone.play();
        }
        if (e.key.match(/[a-z]/i)) {
            let path = "../../samples/" + e.key + ".mp3";
            let sound = new Audio(path);
            sound.play();
        }
    }
</script>