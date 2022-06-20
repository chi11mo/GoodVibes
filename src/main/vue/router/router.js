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
import UserRegister from "../views/admin/components/UserRegister.vue";
import {Role} from "../model/role";


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
        meta: {authorize: [Role.ADMIN]}

    },
    {
        path: '/admin/gameSchedule',
        name: 'GameManagement',
        component: GameManagement,
        meta: {authorize: [Role.ADMIN]}
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
        meta: {authorize: [Role.PLAYER]}
    },
    {
        path: '/signup',
        name: 'UserRegister',
        component: UserRegister,
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

/**
 * Check und Convert API Role to vue Role.
 * @returns {string}
 */
function checkRole() {
    if (localStorage.getItem('role') === "PLAYER") {
        return Role.PLAYER
    }
    if (localStorage.getItem('role') === "ADMIN") {
        return Role.ADMIN
    }
    if (localStorage.getItem('role') === "MODERATOR") {
        return Role.MODERATOR
    }

}


router.beforeEach((to, from, next) => {
    // redirect to login page if not logged in and trying to access a restricted page
    const {authorize} = to.meta;
    const userStore = useStore()
    const currentUser = userStore.getCurrentUser();
    console.log(localStorage.getItem('email'))
    console.log(localStorage.getItem('username'))
    console.log("Role." + localStorage.getItem('role'))
    console.log(currentUser.role)

    console.log(checkRole())

    //userStore.authenticate(localStorage.getItem('email'), localStorage.getItem('password'))
    if (authorize) {
        if (!currentUser) {
            // not logged in so redirect to login page with the return url
            return next({path: '/login', query: {returnUrl: to.path}});
        }

        // check if route is restricted by role
        if (authorize.length && !authorize.includes(checkRole())) {
            // role not authorised so redirect to home page
            return next({path: '/login'});
        }
    }

    next();
})
export default router;
