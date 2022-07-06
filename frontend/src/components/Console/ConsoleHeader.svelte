<script>
    // @ts-ignore
    import { output_content } from "/src/stores/OutputStore";
    // @ts-ignore
    import { show_terminal } from "/src/stores/OutputStore";
    // @ts-ignore
    import { show_output } from "/src/stores/OutputStore";

    let Prompt = "/home/hugo ~ Prompt";

    function Show_Output() {
        output_content.set("Execution output...");
        show_output.set(true);
        show_terminal.set(false);
    }
    function Show_Terminal() {
        show_output.set(false);
        show_terminal.set(true);
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
