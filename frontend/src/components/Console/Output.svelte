<script>
    import { writable } from "svelte/store";

    import { output_content } from "../../stores/OutputStore";
    import { show_output } from "../../stores/OutputStore";

    let content = "Execution output...";
    let show = true;
</script>

{#if $show_output}
    <div>
        <p id="Output">
            {$output_content}
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
