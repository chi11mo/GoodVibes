<template>
    <q-page padding>
        <body>
        <div class="row">
            <div class="col-2">
                <q-card>
                    <img :src="currentImg">
                    <q-list>


                        <q-item clickable>
                            <q-item-section avatar>
                                <q-icon name="img:src/main/vue/views/images/discord_icon.png"/>
                            </q-item-section>

                            <q-item-section>
                                <q-item-label>Discord</q-item-label>
                                <q-item-label caption>{{ currentDiscord }}</q-item-label>
                            </q-item-section>
                        </q-item>

                        <q-item clickable>
                            <q-item-section avatar>
                                <q-icon name="img:src/main/vue/views/images/twitch_icon.png"/>
                            </q-item-section>

                            <q-item-section>
                                <q-item-label>Twitch</q-item-label>
                                <q-item-label caption>{{ currentTwitch }}</q-item-label>
                            </q-item-section>
                        </q-item>

                        <q-item clickable>
                            <q-item-section avatar>
                                <q-icon color="amber" name="email"/>
                            </q-item-section>

                            <q-item-section>
                                <q-item-label>Email</q-item-label>
                                <q-item-label caption>{{ currentEmail }}</q-item-label>
                            </q-item-section>
                        </q-item>


                        <q-item clickable>
                            <q-item-section avatar>
                                <q-icon name="img:src/main/vue/views/images/playstation_icon.png"/>
                            </q-item-section>

                            <q-item-section>
                                <q-item-label>PlayStation Network</q-item-label>
                                <q-item-label caption>{{ currentPsn }}</q-item-label>
                            </q-item-section>
                        </q-item>

                        <q-item clickable>
                            <q-item-section avatar>
                                <q-icon name="img:src/main/vue/views/images/steam_icon.png"/>
                            </q-item-section>

                            <q-item-section>
                                <q-item-label>Steam</q-item-label>
                                <q-item-label caption>{{ currentSteam }}</q-item-label>
                            </q-item-section>
                        </q-item>

                        <q-card-section>

                        </q-card-section>

                    </q-list>
                </q-card>
            </div>
            <div class="col-10">
                <div class="gameScheduleH1">
                    <div class="headline">Spielplan</div>
                </div>
                <div class="gameSchedule">
                    <GameListPublic :currentLastGames="currentLastGames"></GameListPublic>
                </div>
            </div>
        </div>
        <div class="row">

        </div>
        </body>
    </q-page>

</template>

<script>
import {ref} from "vue";
import {useStore} from "../store/users"
import GameListPublic from "./game/components/GameListPublic.vue";
import GameService from "../api/services/GameService";

export default {
    name: "MyAccount",
    components: {GameListPublic},
    setup() {

        return {
            icon: ref(false),
            val: ref(false),
            val2: ref(false),


        }
    },
    data() {
        return {
            userStore: useStore(),
            currentUser: ref(null),
            currentImg: ref(null),
            currentDiscord: ref(null),
            currentPsn: ref(null),
            currentSteam: ref(null),
            currentTwitch: ref(null),
            currentEmail: ref(null),
            currentLastGames: [],
            games: [],

        }
    },
    async created() {
        await this.retrieveUser();
        //  await this.retrieveGames();

    },
    methods: {
        async retrieveUser() {

            await this.userStore.getCurrentUser().then(async () => {
                this.currentUser = this.userStore.currentUser
                this.currentImg = this.currentUser.imgUrl;
                this.currentDiscord = this.currentUser.discord;
                this.currentSteam = this.currentUser.steam;
                this.currentPsn = this.currentUser.psn;
                this.currentEmail = this.currentUser.email;
                this.currentTwitch = this.currentUser.twitch;

                this.currentLastGames = this.currentUser.club.game;


            })

        },
        /**
         * Method to get All existing Users from Api.¬
         */
        async retrieveGames() {
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


}
</script>

<style scoped>
.q-page {
    background-color: #333333;
}

body {

}

.row {
    border: 1px solid rgba(86, 61, 124, .2);

}



.headline {
    font-weight: bold;
    font-size: 21px;
    color: rgb(255, 255, 255);;
}

.gameScheduleH1 {
    padding: 10px 5px;
    border: 1px solid rgba(86, 61, 124, .2);
}

.gameSchedule {
    padding: 10px 15px;
    border: 1px solid rgba(86, 61, 124, .2);

}

.row.row {

}
</style>
