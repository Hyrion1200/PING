import { writable } from "svelte/store";


export const consoleStore = writable("# user @ login.epita in ~ ");
export const outputStore = writable("");