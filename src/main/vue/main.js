

import '@quasar/extras/material-icons/material-icons.css'
import 'quasar/src/css/index.sass'


import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { Quasar, Notify } from 'quasar'
import App from './App.vue';
import router from './router/router.js'
import LoadScript from 'vue-plugin-load-script';


import { library } from '@fortawesome/fontawesome-svg-core';



createApp(App)
    .use(router)
    .use(createPinia())
    .use(LoadScript)
    .use(Quasar, {
        plugins: {Notify}, // import Quasar plugins and add here
        config: {
            notify: {}
        }
    })
    .mount('#app')
