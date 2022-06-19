<template>
    <q-page-container>
        <!-- Will be entered if Form or delete page is not activated --->
        <div class="q-pa-md" v-if=!activeForm&&!activeDelete&&!activeRegister>
            <!-- Register Button -->
            <q-btn
                class="register-button"
                color="blue"
                icon-right="register"
                label="Register"
                @click="()=> toggleRegister(1)"
            />
            <!-- Search field to filter Users with text input --->
            <q-input class="search-input" outlined v-model="search" label="Suche" v-on:keyup="filterUser" color="white"/>
            <!-- Table to show up Users with Name Email and Role  --->
            <q-table class="user-table"
                     title="Mitglieder"
                     :rows="filteredUsers"
                     :data="filteredUsers"
                     :columns="columns"
                     row-key="name"
                     :rowsPerPage=0
                     rows-per-page-label ="Zeilen Pro Seite"
                     :rows-per-page-options="[10, 20,40]">

                <template v-slot:body-cell-action="props">
                    <q-td :props="props">
                        <!-- Button to Edit Users  --->
                        <q-btn
                            color="blue"
                            icon-right="edit"
                            no-caps
                            flat
                            dense
                            @click="()=> toggleForm(filteredUsers.indexOf(props.row))"
                        />
                    </q-td>
                    <q-td :props="props">
                        <!-- Button to Delete Users  --->
                        <q-btn
                            color="negative"
                            icon-right="delete"
                            no-caps
                            flat
                            dense
                            @click="()=> toggleDelete(filteredUsers.indexOf(props.row))"
                        />
                    </q-td>
                    <q-tr :props="props">
                        <q-td auto-width>
                            <q-toggle v-model="props.expand" checked-icon="add" unchecked-icon="remove"/>
                        </q-td>
                    </q-tr>

                </template>
            </q-table>
        </div>
        <!-- Form to delete a User if activeDelete is true-->
        <UserDelete v-if="activeDelete" :toggle-form="toggleDelete" :userId="userId"/>
        <!-- Form to edit a User if activeForm is true-->
        <UserForm v-if="activeForm" :toggle-form="toggleForm" :userId="userId"/>
        <!-- Form to register a User if activeRegister is true-->
        <UserRegister v-if="activeRegister" :toggle-form="toggleRegister" ::userId="userId"/>
    </q-page-container>
</template>

<script>
import UserForm from "./UserForm.vue";
import UserDelete from "./UserDelete.vue";
import {ref} from "vue";
import UserRegister from "./UserRegister.vue";

const columns = [
    {
        name: 'name',
        required: true,
        label: 'Name',
        align: 'left',
        field: row => row.twitch,
        format: val => `${val}`,
        sortable: true
    },
    {name: 'Email', label: 'Email', field: 'email', sortable: true},
    {name: 'Steam', label: 'Steam', field: 'steam', sortable: true},
    {name: 'PSN', label: 'PSN', field: 'psn', sortable: true},
    {name: 'action', label: 'Optionen', field: 'action'},


]


export default {
    components: {UserRegister, UserForm, UserDelete},
    props: ["users", "toggleForm", "toggleDelete", "toggleRegister"],
    name: "UserList",
    setup(props) {
        const activeDelete = ref(false)
        const activeForm = ref(false)
        const activeRegister = ref(false)
        const userId = ref(false)
        /**
         * When Click Edit Button activeForm get set to true.
         * @param id from editable user.
         */
        const toggleForm = (id = false) => {
            activeForm.value = !activeForm.value;
            userId.value = props.users[id].id;

        }
        /**
         * When Click Delete Button activeDelete get set to true.
         * @param id from deletable user.
         */
        const toggleDelete = (id = false) => {
            activeDelete.value = !activeDelete.value;
            userId.value = props.users[id].id;

        }
        /**
         * When Click Register Button activeRegister get set to true.
         * @param id from deletable user.
         */
        const toggleRegister = (id = false) => {
            activeRegister.value = !activeRegister.value;


        }

        return {
            columns,
            activeForm,
            activeDelete,
            activeRegister,
            userId,
            toggleForm,
            toggleDelete,
            toggleRegister
        }
    },
    data() {
        return {
            filteredUsers: this.users,
            title: "",
            search: '',
        };
    },
    async mounted() {
        this.filterUser();
    },
    async beforeMount() {
        this.filterUser();
    },
    async created() {
        this.filterUser();
    },
    async beforeUpdate() {
        this.filterUser();
    },
    methods: {
        /**
         * Method to search users with input and add it to variable filteredUsers.
         */
        filterUser() {
            this.filteredUsers = []
            if (this.search === '') {
                this.filteredUsers = this.users;
                return;
            }
            this.users.filter(user => {
                if (user.twitch.toLowerCase().includes(this.search.toLowerCase())) {
                    this.filteredUsers.push(user)
                }
            })
        }
    },
};
</script>


<style scoped>
.q-pa-md {
    margin-top: 10%;
    margin-left: auto;
    margin-right: auto;
    width: 100%;
}

.search-input {
    width: 25%;
    margin-left: auto;
    margin-right: auto;
    background-color: aliceblue;
}

.user-table {
    width: 75%;
    margin-left: auto;
    margin-right: auto;
}
.register-button{
    margin: 0 auto;
    float: left;
}

</style>
