const routes = [
    {
        path: "/pickem",
        component: () => import("./views/Home.vue"),
        children: [
            {
                path: "lec",
                name: "PickemLEC",
                component: () => import("./views/LEC.vue")
            },
            {
                path: "lck",
                name: "PickemLCK",
                component: () => import("./views/LCK.vue")
            },
            {
                path: "lpl",
                name: "PickemLPL",
                component: () => import("./views/LPL.vue")
            }
        ]
    }
];

export default routes;