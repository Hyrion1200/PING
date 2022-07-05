<link rel="stylesheet" href="./node_modules/xterm/css/xterm.css" />
<script src="./node_modules/xterm/lib/xterm.js">
    import {show_terminal} from "../../stores/OutputStore"
    import { Terminal } from 'xterm';
    import { FitAddon } from 'xterm-addon-fit';
    import { tick } from "svelte";

    let show = false;
    let Prompt = "~ ";

    show_terminal.subscribe((value) => {
        show = value;
    })

    var command = "";
    const fitAddon = new FitAddon();
    async function create_term(){
        await tick();
        var term = new Terminal(
            {
                rendererType: "dom",
            }
        );
        term.open(document.getElementById('Terminal'));
        term.loadAddon(fitAddon);
        term.write(Prompt)
        fitAddon.fit();
        term.onData((data) => {
            if (data == "\r") 
            {
                console.log(command);
                command = "";
                term.write(`\n\r${Prompt}`);
            }
             else 
             {
                command += data;
                term.write(data);
             }
        })
    }
    create_term();
</script>


{#if show}
    <div id="Terminal">
    </div>
    {create_term()}
{/if}

<style>
    div {
        display: flex;
        position: absolute;
        height: 20%;
        width: 71.4%;
    }
</style>