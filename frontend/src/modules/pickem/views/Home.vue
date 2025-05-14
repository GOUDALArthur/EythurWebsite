<script setup lang="ts">
import PickemFooter from "../components/PickemFooter.vue";
import BrowserButton from "../../../lib/components/BrowserButton.vue";
</script>

<template>
  <div id="pickem-container">
    <h1>Pick'em All</h1>
    <div id="competition-selector">
      <BrowserButton content="LEC" color="#f17314" route="/pickem/lec" />
      <BrowserButton content="LCK" color="#8574b5" route="/pickem/lck" />
      <BrowserButton content="LPL" color="#ea4335" route="/pickem/lpl" />
    </div>
  </div>
  <PickemFooter />
</template>

<style scoped>
#competition-selector {
  display: flex;
  justify-content: center;
  gap: 2vw;
  margin-top: 15vh;
}

.browser-button {
  padding: 5vh 5.5vw;
  font-size: 2.5em;
}
</style>
