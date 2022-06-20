<template>
    <q-page padding>
        <q-page-container>
            <!--q-btn label="Button" @click="onSubmit()" color="primary"/-->
            <q-card class="my-card">
                <div class="q-pa-md" style="max-width: 400px">
                    <!-- Form to set up Name , Email , Role or faculties. -->
                    <q-form
                        class="q-gutter-md"
                    >
                        <q-select v-model="game.clubNameOne" :options="clubsNames" :option-value="'team'"
                                  :option-label="'team'"
                                  label="Club"/>
                        <q-input
                            filled:value="number"
                            v-model="game.scoreTeamOne"
                            label="Tore"
                            hint="Tore"
                            model-value="number"
                            lazy-rules
                            :rules="[ val => val && val.length > 0 || 'Bitte das Feld ausfuellen']"
                        />
                        <q-select v-model="game.clubNameTwo" :options="clubsNames" :option-value="'team'"
                                  :option-label="'team'"
                                  label="Club"/>
                        <q-input
                            filled:value="number"
                            v-model="game.scoreTeamTwo"
                            label="Tore"
                            hint="Tore"
                            model-value="number"
                            lazy-rules
                            :rules="[ val => val && val.length > 0 || 'Bitte das Feld ausfuellen']"
                        />


                        <q-input filled v-model="date" mask="date" :rules="['date']">
                            <template v-slot:append>
                                <q-icon name="event" class="cursor-pointer">
                                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                                        <q-date v-model="date">
                                            <div class="row items-center justify-end">
                                                <q-btn v-close-popup label="Close" color="primary" flat/>
                                            </div>
                                        </q-date>
                                    </q-popup-proxy>
                                </q-icon>
                            </template>
                        </q-input>


                        <q-toggle v-model="game.played" label="Wurde das Spiel ausgetragen?"/>
                        <!--
                        <q-select v-model="user.faculty" :options="faculties" :option-value="'id'" :option-label="'name'"
                                  label="Fakultaet"/>
                        <q-select v-model="user.role" :options="roles" :option-value="'id'" :option-label="'name'" label="Rolle"/>
                        -->
                        <div>
                            <q-btn label="Speichern" type="submit" @click="onSubmit()" color="primary"/>
                            <q-btn label="Zurueck" type="back" color="primary" flat class="q-ml-sm"/>
                        </div>
                    </q-form>
                </div>
            </q-card>
        </q-page-container>
    </q-page>
</template>

<script>
import GameService from "../../../api/services/GameService";
import {ref} from "vue";
import ClubService from "../../../api/services/ClubService";
import Formatter from "../../../controller/formatter";

import {date} from 'quasar'


export default {
    props: ["toggleForm", "gameId"],
    name: "GameForm"
    ,
    data() {
        return {
            games: [],
            clubsNames: [],
            currentGame: null,
            clubs: [],
            currentIndex: -1,
            title: "",
        };
    },
    setup(props) {
        const game = ref({})
        const played = ref(false)
        const date = ref('2022/06/01');
        const proxyDate = ref('2022/06/01')
        const GetGame = async () => {

            GameService.getGameById(props.gameId)
                .then(response => {
                    game.value = response.data;
                })
                .catch(e => {
                    console.log(e);
                });
        }
        return {
            GetGame,
            game,
            played,
            date,
            proxyDate,
        }
    },
    async mounted() {
        if (this.gameId != null) {
            this.GetGame();
        }
        this.retrieveClubs();

    },
    async beforeMount() {
        this.retrieveClubs()
    },
    async created() {
        this.retrieveClubs()
    },
    methods: {
        /**
         * Method to get all Roles to edit user.
         */
        retrieveClubs() {
            //  console.log(this.date)
            ClubService.getClubs()
                .then(response => {
                    this.clubs = response.data;
                    for (let i = 0; i < this.clubs.length; i++) {
                        this.clubsNames[i] = this.clubs[i].team;
                    }
                    // console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        /**
         * Method to save Edits after click on Submit Button.
         * @param evt event after click SubmitButton
         */
        async onSubmit(evt) {
            Formatter.dateFormatter(new Date(this.date), this.game)
            GameService.updateGame(this.game).then(response => {
                // console.log(response.data)
            })
                .catch(e => {
                    console.log(e);
                });
            //  evt.target.submit()
        },


    },

}
</script>

<style scoped>
.my-card {

}

.q-pa-md {
    margin-top: 10%;
    margin-left: auto;
    margin-right: auto;
    width: 100%;
}

h1 {
    text-align: center;
    color: blue;
}

h2 {
    text-align: center;
}
</style>
