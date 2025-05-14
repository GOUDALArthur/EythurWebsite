<script setup lang="ts">

</script>

<template>
  <header>
    <p id="header-title">Eythur</p>
    <nav>
      <router-link to="/">Accueil</router-link>
      <router-link to="/ratio">RATIØ²</router-link>
      <router-link to="/pickem">Pick'em</router-link>
      <router-link to="/pcm/results">PCM</router-link>
    </nav>
  </header>
</template>

<style scoped>
  header {
    width: 100%;
    display: flex;
    align-items: center;
    background-color: #151515;
    border-bottom: darkgoldenrod 3px solid;
    padding-top: 1vh;
    padding-bottom: 1.5vh;
  }

  #header-title {
    font-size: 2em;
    font-weight: bold;
    margin: auto 8vw auto 2vw;
    color: darkgoldenrod;
  }

  nav {
    display: flex;
    gap: 2vw;
  }

  nav a {
    color: darkgoldenrod;
    text-decoration: none;
    font-size: 1.5em;
  }

  nav a:hover {
    text-decoration: underline;
  }
</style>