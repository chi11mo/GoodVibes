<template>
    <q-page padding>
    <main>
        <GameList :games="games"/>
    </main>
    </q-page>
</template>

<script>
import GameService from "../../api/services/GameService";
import GameList from "./components/GameList.vue";


export default {
    name: "GameManagement",
    components: {GameList},

    data() {
        return {
            games: [],
            title: "",
            search: '',
        };
    },
    async mounted() {
        this.retrieveGames();
    },
    async beforeMount() {
        this.retrieveGames();
    },
    async created() {
        this.retrieveGames();
    },
    methods: {
        /**
         * Method to get All existing Users from Api.¬
         */
        retrieveGames() {
            GameService.getGames()
                .then(response => {
                    this.games = response.data;
                  //  console.log(this.games)
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
