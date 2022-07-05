<script>
import { writable } from "svelte/store";

    import { output_content } from "../../stores/OutputStore";
    import { show_output } from "../../stores/OutputStore"

    let content = "Execution output...";
    let show = true;

    output_content.subscribe((value) => {
        content = value;
    });

    show_output.subscribe((value) => {
        show = value;
    })

</script>

{#if show}
     <div>
        <p id="Output">
            {content}
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
