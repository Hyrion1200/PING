<link rel="stylesheet" href="./node_modules/xterm/css/xterm.css" />
<script src="./node_modules/xterm/lib/xterm.js">
    import {show_terminal} from "../../stores/OutputStore"
    import { Terminal } from 'xterm';

    let show = false;
    let Prompt = "/home/hugo ~ Prompt";

    show_terminal.subscribe((value) => {
        show = value;
    })

    //var term = new Terminal();
</script>


{#if show}
    <div>
        <p id="Terminal">
            {Prompt}
        </p>
    </div>
{/if}

<style>
    div {
        display: flex;
        position: absolute;
        height: 20%;
        width: 71.4%;
    }
    p {
        width: 100%;
        font-size: 15px;
        border: none;
        resize: none;
        margin: 0;
        background-color: #2d2d2d;
        color: white;
        outline: none;
    }
</style>
