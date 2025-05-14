<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import Standings from "../components/Standings.vue";
import Schedule from "../components/Schedule.vue";
import { loadAndCachePcmResource } from "../utils/storage.ts";
import "../styles/pcm.css";

const activeTab = ref<string | null>(null);

onMounted(async () => {
  await loadAndCachePcmResource();

  try {
    const storedTab = sessionStorage.getItem('activeTab');
    activeTab.value = storedTab ?? 'standings';
  } catch (error) {
    console.error("Erreur lors de la récupération de l'onglet actif", error);
  }
});

watch(activeTab, (newTab) => {
  sessionStorage.setItem('activeTab', newTab ?? 'standings');
});
</script>

<template>
  <div id="pcm-container">
    <div class="tabs">
      <button @click="activeTab = 'standings'" :class="{ active: activeTab === 'standings' }" class="tab-button">Classements</button>
      <button @click="activeTab = 'schedule'" :class="{ active: activeTab === 'schedule' }" class="tab-button">Calendrier</button>
      <button @click="activeTab = 'results'" :class="{ active: activeTab === 'results' }" class="tab-button">Résultats</button>
    </div>

    <div class="content">
      <div v-if="activeTab === 'standings'">
        <Standings />
      </div>
      <div v-else-if="activeTab === 'schedule'">
        <Schedule />
      </div>
      <div v-else-if="activeTab === 'results'">
        <h1>Résultats</h1>
      </div>
    </div>
  </div>

</template>

<style scoped>
.tabs {
  margin-top: 1vh;
  margin-left: 4vw;
  text-align: left;
}

.tab-button {
  outline: none;
  margin-right: 0.25vw;
}

.active {
  border-bottom: 2px solid goldenrod;
}
</style>