<script>
    import { show_terminal } from "../../stores/OutputStore";

    let show = false;
    let Prompt = "/home/hugo ~ Prompt";
</script>

{#if $show_terminal}
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
