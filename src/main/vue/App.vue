<template>
    <q-layout view="hHh lpR fFf">

        <q-header elevated class="text-primary bg-white" height-hint="98">
            <q-toolbar class="text-primary" transparent="true">
                <q-btn stretch flat label="Home" @click="$router.push('home')"></q-btn>
                <q-btn stretch flat label="Tabelle" @click="$router.push('')"></q-btn>
                <q-btn stretch flat label="Liga" @click="$router.push('')"></q-btn>
                <q-btn-dropdown stretch flat label="Mitspielen">
                    <q-list>
                        <q-btn @click="$router.push('/regelwerk')">Regelwerk</q-btn>
                        <q-btn @click="$router.push('')">FHQ</q-btn>
                        <q-btn @click="$router.push('/clubs')">Teams</q-btn>
                    </q-list>
                </q-btn-dropdown>
                <q-btn stretch flat label="Mein Konto" @click="$router.push('/myaccount')"></q-btn>
                <q-toolbar-title align="center">
                    Good Vibes League
                </q-toolbar-title>
                <q-btn-dropdown stretch flat label="Admin">
                    <q-list>
                        <q-btn @click="$router.push('/admin/gameSchedule')">Schedule</q-btn>
                        <q-btn @click="$router.push('/admin/user')">User</q-btn>

                    </q-list>
                </q-btn-dropdown>
                <q-btn stretch flat label="Login" @click="$router.push('/login')"></q-btn>
                <q-btn stretch flat label="Abmelden" @click="$router.push('/logout')"></q-btn>
            </q-toolbar>

            <!---
            <router-link to="/foo" tag="button">foo</router-link>
        <q-tabs align="center">
            <q-route-tab to="/page1" label="Dashboard"/>
            <q-route-tab to="/clubs" label="Clubs"/>
            <q-route-tab to="/admin/gameSchedule" label="Admin Bereich"/>
            <q-route-tab to="/admin/user" label="Admin Bereich"/>
            <router-link to="/login" class="nav-link">
                <font-awesome-icon icon="sign-in-alt" />Login
            </router-link>
        </q-tabs>
        -->
        </q-header>

        <q-page-container>
            <router-view/>
        </q-page-container>

    </q-layout>
</template>

<style>
</style>

<script>


export default {
    name: "Start",

}
</script>
