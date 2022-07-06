import { writable } from "svelte/store";


export const output_content = writable("Execution ouput...");
export const show_output = writable(true);
export const show_terminal = writable(false);