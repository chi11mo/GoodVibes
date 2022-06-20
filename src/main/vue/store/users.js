import {defineStore} from 'pinia'
import axios from "axios"
import {ref} from 'vue'
import UserService from "../api/services/UserService";

export const useStore = defineStore('users', () => {
    const authenticated = ref(localStorage.getItem('token') !== null)
    const resetMailsent = ref(false);
    const users = ref([])
    const currentUser = ref(null)

    function persistAuthentication(token) {
        if (token !== null) {
            authenticated.value = true
            localStorage.setItem('token', token);
            axios.defaults.headers['Authorization'] = token
        } else {
            authenticated.value = false
        }
    }

    async function authenticate(username, password) {
        const credentials = new URLSearchParams();
        credentials.append('username', username);
        credentials.append('password', password);
        try {
            const response = await axios.post('/api/player/authenticate', credentials)
            if (response.data !== '') {
                persistAuthentication(response.data)
            }
            localStorage.setItem('email', username)
            localStorage.setItem('password', password)
        } catch (e) {
            authenticated.value = false
        }
    }

    function logout() {
        authenticated.value = false
        localStorage.removeItem('token')
        axios.defaults.headers['Authorization'] = ""
        localStorage.clear();
    }

    function getAllUsers() {

        return new Promise((resolve, reject) => {
            UserService.getUser().then(res => {
                users.value = res.data
                resolve()
            }).catch(() => {
                users.value = []
                reject()
            })
        })
    }

    function getCurrentUser() {
        return new Promise((resolve, reject) => {
            UserService.getUserByEmail(localStorage.getItem('email')).then(res => {
                currentUser.value = res.data
                localStorage.setItem('username', currentUser.value.twitch)
                localStorage.setItem('role', currentUser.value.role)

                resolve()
            }).catch(() => {
                currentUser.value = []
                reject()
            })
        })
    }

    function updateUser(user) {
        UserService.updateUser(user).then(res => {
            resolve()
        }).catch(() => {
            reject()
        })
    }

    function sendResetMail(emailAddress) {
        const credentials = new URLSearchParams();
        credentials.append('emailAddress', emailAddress);

        axios.put('/api/sendMail/reset', credentials)
        resetMailsent.value = true;
    }

    async function resetPassword(token, newPassword) {
        const credentials = new URLSearchParams();
        credentials.append('token', token);
        credentials.append('newPassword', newPassword);
        try {
            const res = await axios.put('/api/user/reset/password', credentials)

            return res.data

        } catch (err) {
            return "FAIL"
        }
    }

    function logout() {
        authenticated.value = false
        localStorage.removeItem('token')
        axios.defaults.headers['Authorization'] = ""
    }

    return {
        authenticated,
        resetMailsent,
        logout,
        authenticate,
        sendResetMail,
        resetPassword,
        getAllUsers,
        users,
        currentUser,
        getCurrentUser,
        updateUser
    }
})
axios.defaults.headers['Authorization'] = localStorage.getItem('token')
