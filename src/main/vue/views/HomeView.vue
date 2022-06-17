<template>

        <div class="q-pa-md">
            <q-carousel
                animated
                v-model="slide"
                arrows
                navigation
                infinite
            >
                <q-carousel-slide :name="1"> <twitch-stream channel="PesCoach"></twitch-stream></q-carousel-slide>
                <q-carousel-slide :name="2" img-src="https://cdn.quasar.dev/img/parallax1.jpg" />
                <q-carousel-slide :name="3" img-src="https://cdn.quasar.dev/img/parallax2.jpg" />
                <q-carousel-slide :name="4" img-src="https://cdn.quasar.dev/img/quasar.jpg" />
            </q-carousel>
        </div>
</template>

<script>

import 'twitch-stream-embed';
import { ref } from 'vue'
export default {
    name: "HomeView",

    data() {
        return {
            channel: 'tserkov',
        };
    },
    setup () {
        return {
            slide: ref(1)
        }
    }
}
</script>

<style scoped>

</style>
