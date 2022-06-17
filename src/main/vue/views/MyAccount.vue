<template>
    <q-page padding>
        <q-card class="my-card">
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
        <div>{{currentLastGames}}</div>
    </q-page>
</template>

<script>
import {ref} from "vue";
import {useStore} from "../store/users"


export default {
    name: "MyAccount",

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
            currentLastGames: []

        }
    },
    async created() {
        await this.retrieveUser();


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
                console.log(this.currentUser.club)

            })
        }
    }


}
</script>

<style scoped>
.my-card {
    width: 100%;
    max-width: 300px;

}
</style>
