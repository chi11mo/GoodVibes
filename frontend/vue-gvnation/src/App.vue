<script setup>
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/clubs">Clubs</router-link>
    <router-link to="/table">Tabelle</router-link>
  </div>
  <router-view/>
</template>

<style>

</style>
