<template>
    <q-page-container>
        <!--q-btn label="Button"  @click="onSubmit()" color="primary"/-->
        <h1>

        </h1>
        <h2></h2>
        <div class="q-pa-md" style="max-width: 400px">
            <!-- Form to set up Name , Email , Role or faculties. -->
            <q-form
                class="q-gutter-md"
            >
                <q-select v-model="game.clubNameOne" :options="clubsNames" :option-value="'team'" :option-label="'team'"
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
                <q-select v-model="game.clubNameTwo" :options="clubsNames" :option-value="'team'" :option-label="'team'"
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
    </q-page-container>
</template>

<script>
import ClubService from "../../../api/services/ClubService";
import GameService from "../../../api/services/GameService";
import Formatter from "../../../controller/Formatter";
import {ref} from "vue";

export default {
    name: "GameRegister",

    data() {
        return {
            game: {
                clubNameOne: '',
                clubNameTwo: '',
                day: '',
                month: '',
                year: '',
            },
            clubsNames: [],
            clubs: [],
        }
    },
    setup() {

        const date = ref('2022/06/01');
        const proxyDate = ref('2022/06/01')

        return {
            date,
            proxyDate,
        }
    },

    async mounted() {

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
            GameService.registerGame(this.game).then(response => {
                console.log(response.data)
                ClubService.registerGame(response.data);
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
