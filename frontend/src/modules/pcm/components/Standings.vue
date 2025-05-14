<script setup lang="ts">
import BrowserButton from "../../../lib/components/BrowserButton.vue";
import TableResults from "./tables/TableResults.vue";
import type { ResultRow } from './tables/TableResults.vue';
import { ref, onMounted, watch } from 'vue';
import {
  getCyclists,
  getCountries,
  getTeams,
  getRaces,
  getListCyclistCumulativeScore,
  getListCountryCumulativeScore,
  getListTeamCumulativeScore
} from "../api/apiPcm.ts";
import { CumulativeScore } from "../types/score.ts";
import type { Race } from "../types/race.ts";

const cyclistsCumulativeScores = ref<CumulativeScore[]>([]);
const countriesCumulativeScores = ref<CumulativeScore[]>([]);
const teamsCumulativeScores = ref<CumulativeScore[]>([]);
const cyclistsStandingData = ref<ResultRow[]>([]);
const countriesStandingData = ref<ResultRow[]>([]);
const teamsStandingData = ref<ResultRow[]>([]);

const races = ref<Race[]>([]);
const currentRace = ref<number>(0);

const initialLoading = ref<boolean>(true);
const loading = ref<boolean>(true);
const isAdmin = ref<boolean>(true);
const error = ref<string | null>(null);

const loadCyclistsCumulativeScores = async () => {
  try {
    if (!sessionStorage.getItem('cyclists')) {
      const response = await getCyclists();
      sessionStorage.setItem('cyclists', JSON.stringify(response));
    }
    const cyclists = JSON.parse(sessionStorage.getItem('cyclists') || '[]');
    cyclistsCumulativeScores.value = await getListCyclistCumulativeScore(currentRace.value);
    for (let i = 0; i < cyclistsCumulativeScores.value.length; i++) {
      const score = cyclistsCumulativeScores.value[i];
      const cyclist = cyclists.find((c: any) => c.id === score.instanceId);
      if (cyclist) {
        let pos = i + 1;
        if (score.totalPoints === cyclistsStandingData.value[cyclistsStandingData.value.length - 1]?.score) {
          pos = '-';
        }
        cyclistsStandingData.value.push({
          position: pos,
          country: cyclist.country.isoCode,
          name: cyclist.fullName,
          team: cyclist.team.name,
          score: score.totalPoints,
        });
      } else {
        console.error(`Cycliste avec l'ID ${score.instanceId} introuvable`);
      }
    }
  } catch (err) {
    console.error("Erreur lors de la récupération des scores individuels", err);
    throw err;
  }
};

const loadCountriesCumulativeScores = async () => {
  try {
    if (!sessionStorage.getItem('countries')) {
      const response = await getCountries();
      sessionStorage.setItem('countries', JSON.stringify(response));
    }
    const countries = JSON.parse(sessionStorage.getItem('countries') || '[]');
    countriesCumulativeScores.value = await getListCountryCumulativeScore(currentRace.value);
    for (let i = 0; i < countriesCumulativeScores.value.length; i++) {
      const score = countriesCumulativeScores.value[i];
      const country = countries.find((c: any) => c.id === score.instanceId);
      if (country) {
        let pos = i + 1;
        if (score.totalPoints === countriesStandingData.value[countriesStandingData.value.length - 1]?.score) {
          pos = '-';
        }
        countriesStandingData.value.push({
          position: pos,
          country: country.isoCode,
          name: country.name,
          team: null,
          score: score.totalPoints,
        });
      } else {
        console.error(`Pays avec l'ID ${score.instanceId} introuvable`);
      }
    }
  } catch (err) {
    console.error("Erreur lors de la récupération des scores par pays", err);
    throw err;
  }
};

const loadTeamsCumulativeScores = async () => {
  try {
    if (!sessionStorage.getItem('teams')) {
      const response = await getTeams();
      sessionStorage.setItem('teams', JSON.stringify(response));
    }
    const teams = JSON.parse(sessionStorage.getItem('teams') || '[]');
    teamsCumulativeScores.value = await getListTeamCumulativeScore(currentRace.value);
    for (let i = 0; i < teamsCumulativeScores.value.length; i++) {
      const score = teamsCumulativeScores.value[i];
      const team = teams.find((t: any) => t.id === score.instanceId);
      if (team) {
        let pos = i + 1;
        if (score.totalPoints === teamsStandingData.value[teamsStandingData.value.length - 1]?.score) {
          pos = '-';
        }
        teamsStandingData.value.push({
          position: pos,
          country: team.country.isoCode,
          name: team.name,
          team: null,
          score: score.totalPoints,
        });
      } else {
        console.error(`Équipe avec l'ID ${score.instanceId} introuvable`);
      }
    }
  } catch (err) {
    console.error("Erreur lors de la récupération des scores par équipe", err);
    throw err;
  }
};

onMounted(async () => {
  try {
    races.value = await getRaces();
    currentRace.value =
        sessionStorage.getItem('standingsCurrentRace') ?
        parseInt(sessionStorage.getItem('standingsCurrentRace') || '0') :
        races.value[races.value.length - 1].id;
  } catch (err) {
    error.value = "Une erreur s'est produite lors du chargement des courses.";
    console.error(err);
  } finally {
    initialLoading.value = false;
  }
});

watch(currentRace, async (newRaceId) => {
  if (newRaceId) {
    try {
      loading.value = true;
      sessionStorage.setItem('standingsCurrentRace', newRaceId.toString());
      cyclistsStandingData.value = [];
      countriesStandingData.value = [];
      teamsStandingData.value = [];
      await Promise.all([
        loadCyclistsCumulativeScores(),
        loadCountriesCumulativeScores(),
        loadTeamsCumulativeScores()
      ]);
    } catch (err) {
      error.value = "Une erreur s'est produite lors du chargement des données.";
      console.error(err);
    } finally {
      loading.value = false;
    }
  }
});
</script>

<template>
  <div v-if="!initialLoading" id="standings-container">
    <h1>Classements</h1>
    <div class="buttons-container mb-3 mt-3">
      <div v-if="isAdmin" class="admin-buttons-container">
        <BrowserButton content="Ajouter scores" color="#f5f5f5" route="/pcm/admin/scores" />
      </div>
      <select v-model="currentRace" class="form-select" id="race-select">
        <option v-for="race in races" :key="race.id" :value="race.id">
          {{ race.name }}
        </option>
      </select>
    </div>
    <div v-if="error" class="text-red-500">{{ error }}</div>
    <div v-else-if="!loading" class="table-results-parent">
      <TableResults :content="countriesStandingData" :tableType="'country'" id="countries-table-results" />
      <TableResults :content="cyclistsStandingData" :tableType="'cyclist'" id="cyclists-table-results" />
      <TableResults :content="teamsStandingData" :tableType="'team'" id="teams-table-results" />
    </div>
  </div>
</template>

<style scoped>
.buttons-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.admin-buttons-container {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

#race-select {
  width: fit-content;
  margin-left: 4vw;
}

.table-results-parent {
  display: flex;
  justify-content: center;
  width: 100%;
}

.table-results-parent > * {
  width: fit-content;
  margin-left: auto;
  margin-right: auto;
  flex: 0 0 auto;
}
</style>