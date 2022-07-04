import App from './App.svelte'

if (window.BASE_URL === undefined) {
  window.BASE_URL = 'http://localhost:8080';
}

const app = new App({
  target: document.getElementById('app')
})

export default app
