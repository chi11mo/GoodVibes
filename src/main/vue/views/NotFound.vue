<template>
    <q-page class="window-height window-width row justify-center items-center">
        <q-card>
            <q-card-section>
                <div class="text-center">
                    <q-icon name="warning" color="warning" size="15rem"/>
                </div>
            </q-card-section>
            <q-card-section>
                <div class="text-h3">Seite nicht gefunden</div>
            </q-card-section>
            <q-card-section class="text-center">
                <q-btn @click="$router.push('/')">Zur Startseite</q-btn>
            </q-card-section>
        </q-card>
    </q-page>
</template>
