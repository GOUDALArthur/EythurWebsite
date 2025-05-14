import { createRouter, createWebHistory } from "vue-router";
import pickemRoutes from "../modules/pickem/routes.ts";
import pcmRoutes from "../modules/pcm/routes.ts";

const routes = [
    ...pickemRoutes,
    ...pcmRoutes
];

const router = createRouter({
    history : createWebHistory(),
    routes
});

export default router;