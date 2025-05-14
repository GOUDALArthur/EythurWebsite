const routes = [
    {
        path: "/pcm",
        children: [
            {
                path: "results",
                name: "PCMResults",
                component: () => import("./views/Results.vue")
            },
            {
                path: "admin",
                children: [
                    {
                        path: "scores",
                        name: "PCMScoresAdmin",
                        component: () => import("./views/admin/AdminScores.vue")
                    }
                ]
            }
        ]
    }
];

export default routes;