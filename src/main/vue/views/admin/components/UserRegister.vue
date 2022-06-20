<template>
    <q-page padding>
    <q-page-container>
        <q-btn label="Button" @click="onSubmit()" color="primary"/>
        <h1>

        </h1>
        <h2></h2>
        <div class="q-pa-md" style="max-width: 400px">
            <!-- Form to set up Name , Email , Role or faculties. -->
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
        </div>
    </q-page-container>
    </q-page>
</template>


<script>

import UserService from "../../../api/services/UserService";

export default {
    name: "UserRegister",
    data() {
        return {
            user: {
                twitch: '',
                discord: '',
                psn: '',
                steam: '',
                password: 'test',
                email: '',
            },
        }
    },
    methods: {

        /**
         * Method to save Edits after click on Submit Button.
         * @param evt event after click SubmitButton
         */
        async onSubmit(evt) {
            UserService.signUpUser(this.user).then(response => {
                 console.log(response.data)
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
