<script>
    // @ts-ignore
    import { output_content } from "/src/stores/OutputStore";

    let Prompt = "/home/hugo ~ Prompt";

    function Show_Output() {
        var output = document.getElementById("Output");
        var Terminal = document.getElementById("Terminal");
        output_content.set("Execution output...");
        output.hidden = false;
        Terminal.hidden = true;
    }
    function Show_Terminal() {
        var output = document.getElementById("Output");
        var Terminal = document.getElementById("Terminal");
        Terminal.innerHTML = Prompt;
        Terminal.hidden = false;
        output.hidden = true;
    }
</script>

<div>
    <ul>
        <button on:click={Show_Output}> Output </button>
        <button on:click={Show_Terminal}> Terminal </button>
    </ul>
</div>

<style>
    div {
        height: 5%;
        width: 100%;
        display: flex;
        flex-wrap: nowrap;
        flex-direction: row;
        justify-content: space-between;
        background-color: #2d2d2d;
        border-top: 3px solid white;
        border-bottom: 3px solid white;
    }

    ul {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        margin: 0;
        padding: 0;
        list-style: none;
    }

    button {
        margin: 0;
        color: grey;
        display: inline-block;
        padding: 10px;
        border-bottom: 1px solid #2d2d2d;
    }
</style>
