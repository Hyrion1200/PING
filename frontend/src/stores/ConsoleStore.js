import { writable } from "svelte/store";

export const consoleStore = writable("# user @ login.epita in ~ ");
export const outputStore = writable("");
export const showOutput = writable(false);

export function addOutput(content) {
    outputStore.update((val) => val = val + content + "<br>");
    showOutput.set(true);
}