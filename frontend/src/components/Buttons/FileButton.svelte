<script>
    import Open from "../FilesButtons/Open.svelte";
    async function run(){
    //get the current file path
    var path = "/home/hugo/test/test.py";

    var Content_header = "Executed file at " + path + ":<br/>";

    var output_console = document.getElementById("Output");
    fetch("http://localhost:8080/ide/files/exec?path=" + path)
    .then(response => response.json())
    .then(data => output_console.innerHTML = Content_header + data.content)
    }
</script>
<div id="files">
    <Open />
    <button>Close</button>
    <button id="run" on:click={run}> Run </button>
</div>

<style>
    button {
    cursor: pointer;
    border: none;
    background-color: #2d2d2d;
    color: white;
    padding: 10px;
    border-bottom: 1px solid #2d2d2d;
    }

    button:hover {
        background-color: #3d3d3d;
    }

    #run {
        background-color: #0cc182;
    }

    #run:hover {
        color: #424949;
    }

</style>