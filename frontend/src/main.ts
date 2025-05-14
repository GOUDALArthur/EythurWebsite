import { createApp } from 'vue';

import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

import App from './lib/App.vue';
import router from './router/index.ts';
import './lib/styles/style.css';
import './lib/styles/buttons-style.css';

const app = createApp(App);
app.use(router);
app.mount('#app');