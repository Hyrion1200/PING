<script>

    let selected;
    function handleChange() {
        let git = document.getElementById("git")
        let files = document.getElementById("files")
        if (selected === "git") {
            files.style.display="none";
            git.style.display="block"

        }
        if (selected === "files") {
            git.style.display="none";
            files.style.display="block";
        }
    }
</script>

<select id="SelectButton" bind:value={selected} on:change={handleChange}> Chose
    <option id="Files" value="files">Files</option>
    <option id="Git" value="git">Git</option>
</select>

<style>
    select {
        align-self: right;
        cursor: pointer;
        border: 1px;
        border-color: aliceblue;
        background-color: #2d2d2d;
        color: white;
        padding: 10px;
    }

    select {
        display: block;
    }

</style>