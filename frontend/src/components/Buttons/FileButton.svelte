<script>
    import Open from "../FilesButtons/Open.svelte";
</script>
<div id="files">
    <Open />
    <button>Close</button>
    <button id="run"> Run </button>
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
        color: #424949;
    }

</style>