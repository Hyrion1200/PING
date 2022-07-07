// @ts-ignore
import { addOutput } from "/src/stores/ConsoleStore";

export async function gitAdd(path) {
    // @ts-ignore
    let url = `${window.BASE_URL}/ide/git/add?path=${path}`;

    const resp = await fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            if (data.status === "ERROR") {
                addOutput("Couldn't add: " + data.message);
            }
        });
}

export async function gitCommit(msg) {
    if (msg === null) return;

    // @ts-ignore
    let url = `${window.BASE_URL}/ide/git/commit?message=${msg}`;
    const resp = await fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            if (data.status === "ERROR") {
                addOutput("Couldn't commit: " + data.message);
            }
        });
}

export async function gitPull(user, password) {
    // @ts-ignore
    let url = `${window.BASE_URL}/ide/git/pull?user=${user}&password=${password}`;
    const resp = await fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            if (data.status === "ERROR") {
                addOutput("Couldn't pull: " + data.message);
            }
        });
}

export async function gitPush(user, password) {
    // @ts-ignore
    let url = `${window.BASE_URL}/ide/git/push?user=${user}&password=${password}`;
    const resp = await fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            if (data.status === "ERROR") {
                addOutput("Couldn't push: " + data.message);
            }
        });
}