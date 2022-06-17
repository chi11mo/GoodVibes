<template>
    <h1>
        {{ user.twitch }}
    </h1>
    <h2> zu loeschen</h2>
    <div class="q-pa-md" style="max-width: 400px">
        <!-- Form to Delete a User. -->
        <q-form
            class="q-gutter-md"
        >
            <div>
                <q-btn label="Loeschen" type="submit" @click="onSubmit()" color="primary" style=" width: 50%;"/>
                <q-btn label="Zurueck" type="back" flat class="q-ml-sm"/>
            </div>
        </q-form>
    </div>
</template>

<script>
import {ref} from "vue";
import UserService from "../../../api/services/UserService";

export default {
    props: ["toggleDelete", "userId"],
    name: "UserDelete",

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
        this.GetUser();
    },
    methods: {
        /**
         * Method to save Delete after click on Submit Button.
         * @param evt event after click SubmitButton
         */
        onSubmit(evt) {
            UserService.deleteUserById(this.userId)
                .then(response => {
                    response.data;
                   // console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                    if (e.response.status === 403) {
                        alert("Du hast nicht die Rechte Mitglieder zu Löschen")
                    }
                });
            window.location.reload()
            evt.target.submit()
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
    width: 50%;
    margin-left: auto;
    margin-right: auto;
}

h2 {
    text-align: center;
    width: 50%;
    margin-left: auto;
    margin-right: auto;
}
</style>
