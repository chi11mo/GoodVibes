<template>
    <q-page padding>
    <main>
        <UserList :users="users"/>
    </main>
    </q-page>
</template>

<script>
import UserService from "../../api/services/UserService";
import UserList from "./components/UserList.vue";


export default {
    name: "UserManagement",
    components: {UserList},

    data() {
        return {
            users: [],
            title: "",
            search: '',
        };
    },
    async mounted() {
        this.retrieveUsers();
    },
    async beforeMount() {
        this.retrieveUsers();
    },
    async created() {
        this.retrieveUsers();
    },
    methods: {
        /**
         * Method to get All existing Users from Api.¬
         */
        retrieveUsers() {
            UserService.getUser()
                .then(response => {
                    this.users = response.data;
                })
                .catch(e => {
                    console.log(e);
                });
        },
    }
};
</script>

<style scoped>

</style>
