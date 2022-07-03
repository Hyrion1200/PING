<script>
    export let isOpenModal;
    export let text;
    function handleClose() {
        isOpenModal = false;
    }
</script>

<div id="error" style="--display: {isOpenModal ? 'block' : 'none'};">
    <p>{text}</p>
    <button id="close" on:click={handleClose}>Close</button>
</div>

<style>
    #error {
        display: var(--display);
        position: fixed;
        z-index: 2;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: gray;
        color: aliceblue;
        border: none;
        filter: drop-shadow(0 0 20px #333);
    }

    button {
        cursor: pointer;
        border: none;
        background-color: #4d4d4d;
        color: white;
        padding: 10px;
    }

    button:hover {
        background-color: #6d6d6d;
    }
</style>
