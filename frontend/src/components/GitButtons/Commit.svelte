<script>
    var commitOpen = false;

    function handleCommit() {
        if (commitOpen) {
            return;
        }
        commitOpen = true;
        var commitInput = document.createElement("input")
        commitInput.type="text";
        commitInput.id="commitInput";
        commitInput.placeholder="Enter commit message here";
        commitInput.size=40;

        commitInput.style.border = "none";
        commitInput.style.color = "black";
        commitInput.style.padding = "5px";

        commitInput.style.borderRadius = "3px";

        commitInput.addEventListener("keydown", async function(event) {
            if (event.key === "Enter") {
                var msg = commitInput.value;
                commitInput.parentElement.removeChild(commitInput);
                abortCommit.parentElement.removeChild(abortCommit);
                console.log(msg);

                let url = "http://localhost:8080/ide/git/commit?message=" + msg;
                const resp = await fetch(url,{ mode: 'no-cors'});
                console.log(resp);
                commitOpen = false;
            }
        });

        var abortCommit = document.createElement("button");
        abortCommit.innerHTML = "Abort Commit";
        abortCommit.style.cursor = "pointer";
        abortCommit.style.border = "none";
        abortCommit.style.backgroundColor = "#2d2d2d";
        abortCommit.style.borderRadius = "2px";
        abortCommit.style.color = "white";
        abortCommit.style.padding = "10px";
        abortCommit.style.borderBottom = "1px solid #2d2d2d"
        abortCommit.addEventListener('mouseover', function() {
            abortCommit.style.backgroundColor = "#3d3d3d";
        })
        abortCommit.addEventListener('mouseout', function() {
            abortCommit.style.backgroundColor = "#2d2d2d";
        })

        abortCommit.addEventListener('click', function() {
            abortCommit.parentElement.removeChild(abortCommit);
            commitInput.parentElement.removeChild(commitInput);
            commitOpen = false;
        })
        var git = document.getElementById("git");
        git.append(commitInput);
        git.append(abortCommit);
    }
</script>

<button id="commit" on:click={handleCommit}>
    Git commit 
</button>

<style>
    button {
        cursor: pointer;
        border: none;
        background-color: #2d2d2d;
        border-radius: 2px;
        color: white;
        padding: 10px;
        border-bottom: 1px solid #2d2d2d;
    }

    button:hover {
        background-color: #3d3d3d;
    }

    ::placeholder {
        color:black;
    }
</style>