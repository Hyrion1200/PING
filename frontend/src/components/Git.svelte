<script>
    import Path from "./Path.svelte";
    function handleAdd() {
        const req = new XMLHttpRequest();
        var path = "test";
        req.open("GET", "http://localhost:8080/ide/git/add?path="+ path);
        req.send();
    }

    function handleCommit() {
        var commitInput = document.createElement("input");
        commitInput.type="text";
        commitInput.id="commitInput";
        commitInput.placeholder="Enter commit message here";
        commitInput.size=40;
        commitInput.addEventListener("keydown", function(event) {
            if (event.key === "Enter") {
                var msg = commitInput.value;
                commitInput.parentElement.removeChild(commitInput);
                console.log(msg);

                const req = new XMLHttpRequest();
                req.open("GET", "http://localhost:8080/ide/git/commit?message=" + msg);
                req.send();
            }
        });
        var git = document.getElementById("git");
        git.append(commitInput);
    }

    function handlePush() {
        const req = new XMLHttpRequest();
        req.open("GET", "http://localhost:8080/ide/git/push");
        req.send();
    }

    function handlePull() {
        const req = new XMLHttpRequest();
        req.open("GET", "http://localhost:8080/ide/git/pull");
        req.send();
    }

</script>

<div id="git">
    <button id="add" on:click={handleAdd}>
        Git add
    </button>
    <button id="commit" on:click={handleCommit}>
        Git commit 
    </button>
    <button id="push" on:click={handlePush}>
        Git push 
    </button>
    <button id="pull" on:click={handlePull}>
        Git pull 
    </button>
</div>

<style>
    div {
        background-color: #2d2d2d;
    }
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
</style>