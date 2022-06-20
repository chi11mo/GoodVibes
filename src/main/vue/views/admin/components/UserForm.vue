<template>
    <q-page padding>
        <q-page-container>


            <div class="q-pa-md">

                <q-card-section>
                    <q-form
                        class="q-gutter-md"
                    >
                        <q-input
                            filled:value="text"
                            v-model="user.twitch"
                            label="Twitch"
                            hint="Twitch"
                            model-value="text"
                            lazy-rules
                            color="white"
                            :rules="[ val => val && val.length > 0 || 'Bitte das Feld ausfuellen']"
                        >
                        </q-input>
                        <q-input
                            filled:value="email"
                            v-model="user.email"
                            label="Email"
                            hint="Email"
                            model-value="email"
                            lazy-rules
                            :rules="[ val => val && val.length > 0 || 'Bitte das Feld ausfuellen']"
                        />
                        <q-input
                            filled:value="text"
                            v-model="user.psn"
                            label="PlayStationNetwork"
                            hint="PlayStationNetwork"
                            model-value="text"
                            lazy-rules
                            :rules="[ val => val && val.length > 0 || 'Bitte das Feld ausfuellen (nicht vorhanden - setzen']"
                        />


                        <q-input
                            filled:value="text"
                            v-model="user.steam"
                            label="Steam"
                            hint="Steam"
                            model-value="text"
                            lazy-rules
                            :rules="[ val => val && val.length > 0 || 'Bitte das Feld ausfuellen (nicht vorhanden '-' setzen']"
                        />

                        <q-input
                            filled:value="text"
                            v-model="user.discord"
                            label="Discord"
                            hint="Discord"
                            model-value="text"
                            lazy-rules
                            :rules="[ val => val && val.length > 0 || 'Bitte das Feld ausfuellen (nicht vorhanden '-' setzen']"
                        />

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
                </q-card-section>
            </div>

        </q-page-container>
    </q-page>
</template>


<script>
import UserService from "../../../api/services/UserService";
import {ref} from "vue";
import ClubService from "../../../api/services/ClubService";

export default {
    props: ["toggleForm", "userId"],
    name: "UserForm"
    ,
    data() {
        return {
            clubs: [],
            clubsNames: [],
            currentUser: null,
            currentIndex: -1,
            title: "",
        };
    },
    setup(props) {
        const user = ref({})
        const GetUser = async () => {

            UserService.getUserById(props.userId)
                .then(response => {
                    user.value = response.data;
                })
                .catch(e => {
                    console.log(e);
                });
        }
        return {
            GetUser,
            user,
        }
    },
    async mounted() {
        if (this.userId != null) {
            this.GetUser();
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
            ClubService.getClubs()
                .then(response => {
                    this.clubs = response.data;
                    for (let i = 0; i < this.roles.length; i++) {
                        this.clubsNames[i] = this.clubs[i].name;
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
            UserService.updateUser(1, this.user).then(response => {
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

.q-pa-md {
    margin-top: 10%;
    margin-left: auto;
    margin-right: auto;
    width: 100%;
}
</style>
