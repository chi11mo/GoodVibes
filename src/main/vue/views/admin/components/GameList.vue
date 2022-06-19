<template>

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
        <q-input class="search-input" outlined v-model="search" label="Suche" v-on:keyup="filteredGames"/>
        <!-- Table to show up Users with Name Email and Role  --->

        <q-table class="user-table"
                 title="Schedule"
                 :rows="filteredGames"
                 :data="filteredGames"
                 :columns="columns"
                 row-key="name"
                 :rowsPerPage=0
                 rows-per-page-label ="Zeilen Pro Seite"
                 :rows-per-page-options="[10, 20,40]">


            <template v-slot:top-right>
                <q-input borderless dense debounce="300" v-model="filter" placeholder="Search">
                    <template v-slot:append>
                        <q-icon name="search"/>
                    </template>
                </q-input>
            </template>


            <template v-slot:body-cell-action="props">
                <q-td :props="props">
                    <!-- Button to Edit Users  --->
                    <q-btn
                        color="blue"
                        icon-right="edit"
                        no-caps
                        flat
                        dense
                        @click="()=> toggleForm(filteredGames.indexOf(props.row))"
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
                        @click="()=> toggleDelete(filteredGames.indexOf(props.row))"
                    />
                </q-td>

            </template>
        </q-table>
    </div>

    <!-- Form to delete a User if activeDelete is true-->
    <GameDelete v-if="activeDelete" :toggle-form="toggleDelete" :gameId="gameId"/>
    <!-- Form to edit a User if activeForm is true-->
    <GameForm v-if="activeForm" :toggle-form="toggleForm" :gameId="gameId"/>

    <!-- Form to register a User if activeRegister is true-->
    <GameRegister v-if="activeRegister" :toggle-form="toggleRegister" :gameId="gameId"/>

</template>


<script>
import GameForm from "./GameForm.vue";
import GameDelete from "./GameDelete.vue";
import GameRegister from "./GameRegister.vue";
import {ref} from "vue";
import GameService from "../../../api/services/GameService";

const columns = [
    {
        name: 'id',
        required: true,
        label: 'Id',
        align: 'left',
        field: row => row.id,
        format: val => `${val}`,
        sortable: true
    },
    {name: 'Club', label: 'Club', field: 'clubNameOne'},
    {name: 'Tore', label: 'Tore', field: 'scoreTeamOne'},
    {name: 'Club', label: 'Club', field: 'clubNameTwo'},
    {name: 'Tore', label: 'Tore', field: 'scoreTeamTwo'},
    {name: 'Tag', label: 'Tag', field: 'day', sortable: true},
    {name: 'Monat', label: 'Monat', field: 'month', sortable: true},
    {name: 'Jahr', label: 'Jahr', field: 'year', sortable: true},
    {name: 'Gespielt', label: 'Gespielt', field: 'played', sortable: true},
    {name: 'action', label: 'Optionen', field: 'action'},


]


export default {
    components: {GameForm, GameDelete,GameRegister},
    props: ["games", "toggleForm", "toggleDelete","toggleRegister"],
    name: "GameList",
    setup(props) {
        const activeDelete = ref(false)
        const activeForm = ref(false)
        const activeRegister = ref(false)
        const gameId = ref(false)
        /**
         * When Click Edit Button activeForm get set to true.
         * @param id from editable user.
         */
        const toggleForm = (id = false) => {
            activeForm.value = !activeForm.value;
            gameId.value = props.games[id].id;

        }
        /**
         * When Click Delete Button activeDelete get set to true.
         * @param id from deletable user.
         */
        const toggleDelete = (id = false) => {
            activeDelete.value = !activeDelete.value;
            gameId.value = props.games[id].id;

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
            gameId,
            toggleForm,
            toggleDelete,
            toggleRegister
        }
    },
    data() {
        return {
            filteredGames: this.games,
            title: "",
            search: '',
            filter: ref(''),
            game: ref(null),
        };
    },
    async mounted() {
        this.filterGame();
    },
    async beforeMount() {
        this.filterGame();
    },
    async created() {
        this.filterGame();
    },
    async beforeUpdate() {
        this.filterGame();
    },
    methods: {
        findClubById(id) {

            GameService.getGameById(id)
                .then(response => {
                    this.game = response.data;

                    console.log(this.game)
                })
                .catch(e => {
                    console.log(e);
                })
        },

        onAddUser() {

        },
        /**
         * Method to search users with input and add it to variable filteredUsers.
         */
        filterGame() {
            this.filteredGames = []
            if (this.search === '') {
                this.filteredGames = this.games;
                return;
            }
            this.games.filter(game => {
                if (game.clubNameOne.toLowerCase().includes(this.search.toLowerCase()) || game.clubNameTwo.toLowerCase().includes(this.search.toLowerCase())) {
                    this.filteredGames.push(game)
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
