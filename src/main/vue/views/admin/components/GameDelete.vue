<template>
    <h1>
        {{ game.id }}
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
import GameService from "../../../api/services/GameService";

export default {
    props: ["toggleDelete", "gameId"],
    name: "UserDelete",

    setup(props) {
        const game = ref({})
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
        }
    },
    async mounted() {
        if (this.gameId != null) {
            this.GetGame();
        }
        this.GetGame();

    },
    methods: {
        /**
         * Method to save Delete after click on Submit Button.
         * @param evt event after click SubmitButton
         */
        onSubmit(evt) {
            GameService.deleteGameById(this.gameId)
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
