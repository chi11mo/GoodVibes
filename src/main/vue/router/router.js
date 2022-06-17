
import {createRouter, createWebHistory} from "vue-router";
import {useStore} from "../store/users"
import NotFound from "../views/NotFound.vue";
import UserManagement from "../views/admin/UserManagement.vue";
import GameManagement from "../views/admin/GameManagement.vue";
import UserView from "../views/UserView.vue";
import Login from "../views/Login.vue";
import RulesView from "../views/RulesView.vue";
import HomeView from "../views/HomeView.vue";
import MyAccount from "../views/MyAccount.vue";



let routes = [
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: NotFound,
        meta: {requiresAuth: false}
    },
    {
        path: '/admin/user',
        name: 'UserManagement',
        component: UserManagement,
        meta: {requiresAuth: true}
    },
    {
        path: '/admin/gameSchedule',
        name: 'GameManagement',
        component: GameManagement,
        meta: {requiresAuth: true}
    },
    {
        path: '/clubs',
        name: 'UserView',
        component: UserView,
        meta: {requiresAuth: false}
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
        meta: {requiresAuth: false}
    },
    {
        path: '/myAccount',
        name: 'MyAccount',
        component: MyAccount,
        meta: {requiresAuth: false}
    },
    {
        path: '/logout',
        name: 'logout',
        redirect: to => {
            useStore().logout()
            return '/login'
        },
        meta: {requiresAuth: false}
    },
    {
        path: '/regelwerk',
        name: 'RulesView',
        component: RulesView,
        meta: {requiresAuth: false}
    },
    {
        path: '/home',
        name: 'HomeView',
        component: HomeView,
        meta: {requiresAuth: false}
    },
]

 const router = createRouter({
    history: createWebHistory(),
    routes
})


export default router;
