<svelte:options accessors={true} />

<script>
    import { afterUpdate, onMount } from "svelte";

    const screenWidth = window.innerWidth;
    const screenHeight = window.innerHeight;
    const popUpWidth = 400;
    const popUpHeight = 120;
    const popUpPosX = screenWidth / 2 - popUpWidth / 2;
    const popUpPosY = screenHeight / 2 - popUpHeight / 2;

    export let answer = "";
    export let sentence = "";
    let ok = undefined;
    let cancel = undefined;
    let show = false;

    let input;

    function setFocus() {
        if (input) input.focus();
    }

    function handleKey(event) {
        if (event.key === "Enter") {
            onClick(ok);
        }
    }

    export function prompt(okFunc, cancelFunc = () => {}) {
        answer = "";
        show = true;
        ok = okFunc;
        cancel = cancelFunc;
    }

    async function onClick(func) {
        try {
            await func();
        } catch (error) {
            console.log(error);
        } finally {
            show = false;
        }
    }

    afterUpdate(setFocus);
</script>

{#if show}
    <div>
        <article style="top: {popUpPosY}px; left: {popUpPosX}px;">
            <p>{sentence}</p>

            <input
                id="input"
                bind:this={input}
                type="text"
                on:keypress={handleKey}
                bind:value={answer}
            />

            <nav>
                <button id="ok" on:click={() => onClick(ok)}> OK </button>
                <button id="cancel" on:click={() => onClick(cancel)}>
                    Cancel
                </button>
            </nav>
        </article>
    </div>
{/if}

<style>
    div {
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.35);
    }

    article {
        position: absolute;
        width: 400px;
        padding: 25px;
        background-color: #17212f;
        border-radius: 5px;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        color: #ffffe0;
    }

    p {
        font-weight: bold;
        font-size: 15px;
        width: 80%;
        text-align: center;
        padding: 10px;
        margin: auto;
    }

    input {
        width: 70%;
        margin: auto;
        display: block;
        margin-top: 10px;
        border: none;
        outline: none;
        padding: 10px;
        border-radius: 5px;
    }

    nav {
        display: flex;
        justify-content: space-around;
        width: 100%;
        margin: auto;
        padding: 10px;
        margin-top: 10px;
    }

    button {
        display: inline-block;
        width: 80px;
        height: 30px;
        border: none;
        border-radius: 10px;
        margin: auto;
        cursor: pointer;
        background-color: grey;
    }

    #ok:hover {
        background-color: #00ff7f;
        color: black;
        transition: 0.2s;
    }

    #cancel:hover {
        background-color: #dc143c;
        color: black;
        transition: 0.2s;
    }

    #ok {
        color: #00ff7f;
    }

    #cancel {
        color: #dc143c;
    }
</style>
