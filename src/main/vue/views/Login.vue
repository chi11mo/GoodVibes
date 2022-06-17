<template>
    <q-page
        class="window-height window-width row justify-center items-center"
    >
        <q-card>
            <q-card-section>
                <div class="text-h2">Login</div>
                <div class="text-subtitle2"></div>
            </q-card-section>
            <q-card-section>
                <q-form class="q-gutter-md" @submit.prevent="true">
                    <q-input label="Email" v-model="username"
                             :rules="[val => !!val || 'Eingabe erforderlich']">
                    </q-input>
                    <q-input label="Passwort" type="password" v-model="password"
                             :rules="[val => !!val || 'Eingabe erforderlich']">
                    </q-input>
                </q-form>
            </q-card-section>
            <q-card-section>
                <div class="row">
                    <div class="col text-center">
                        <q-btn size="sm" primary color="blue"
                               @click="login">
                            Anmelden
                        </q-btn>
                    </div>
                    <div class="col text-center">
                        <q-btn size="sm" secondary
                               @click="$router.push('/signup')">
                            Registrieren
                        </q-btn>
                    </div>
                    <div class="col text-center">
                        <q-btn size="sm" accent
                               @click="$router.push('/password-reset')">
                            Vergessen
                        </q-btn>
                    </div>
                </div>
            </q-card-section>
            <q-inner-loading :showing="loading">
                <q-spinner-gears size="50px" color="primary"/>
            </q-inner-loading>
        </q-card>
    </q-page>
</template>


<script>
import {ref} from 'vue'
import {useStore} from "../store/users";
import {useQuasar} from 'quasar'
import {useRouter} from 'vue-router'

export default {
    name: 'Login',
    setup() {
        const $q = useQuasar()
        const router = useRouter()

        const username = ref('')
        const password = ref('')

        const loading = ref(false)

        const userStore = useStore()

        async function login() {
            loading.value = true
            await userStore.authenticate(username.value, password.value)
            loading.value = false
            if (userStore.authenticated) {
                await router.push('/myAccount')
                console.log("Succesful")
            } else {
                $q.notify({
                    type: 'negative',
                    message: 'Login fehlgeschlagen',
                    caption: 'Falsches Passwort oder Benutzername'
                })
            }
        }

        return {username, password, loading, login}
    }
}

</script>


<style scoped>
.col {
    padding: 0.3em;
}
</style>
