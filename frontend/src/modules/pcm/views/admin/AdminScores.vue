<script setup lang="ts">
import {computed, onMounted, ref} from 'vue';
import { getCyclists, getRaces, addScore } from "../../api/apiPcm.ts";
import type { Cyclist } from "../../types/cyclist.ts";
import type { Race } from "../../types/race.ts";
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";

const formData = ref({
  raceId: 0,
  cyclistId: null,
  points: null,
});
const cyclists = ref<Cyclist[]>([]);
const races = ref<Race[]>([]);

type Option = { label: string; value: number };
const reduceOption = (option: Option) => option.value;
const cyclistOptions = computed(() =>
  cyclists.value.map((cyclist) => ({
    label: cyclist.fullName,
    value: cyclist.id,
  }))
);

onMounted(async () => {
  try {
    cyclists.value = await getCyclists();
    races.value = await getRaces();
  } catch (error) {
    console.error('Error fetching datas:', error);
  }
});

const handleSubmit = async () => {
  try {
    if (!formData.value.raceId || !formData.value.cyclistId || !formData.value.points) {
      console.error('All fields are required');
    }
    else {
      await addScore({
        raceId: formData.value.raceId,
        cyclistId: formData.value.cyclistId,
        points: formData.value.points,
      });
    }
  } catch (error) {
    console.error('Error adding score:', error);
  }
};
</script>

<template>
  <div id="add-score-container" class="container mt-4">
    <h2>Ajouter un score</h2>
    <form @submit.prevent="handleSubmit">
      <div class="mt-4 mb-4">
        <label for="raceId" class="form-label">Course</label>
        <select v-model="formData.raceId" id="raceId" class="form-select">
          <option value="0" selected disabled hidden>Sélectionner une course</option>
          <option v-for="race in races" :key="race.id" :value="race.id">
            {{race.name}}
          </option>
        </select>
      </div>
      <div class="mb-4">
        <label for="cyclistId" class="form-label">Coureur</label>
        <v-select
          v-model="formData.cyclistId"
          :options="cyclistOptions"
          placeholder="Rechercher un coureur"
          :reduce="reduceOption"
          class="form-control"
        />
      </div>
      <div class="mb-4">
        <label for="points" class="form-label">Points</label>
        <input
          type="number"
          v-model="formData.points"
          id="points"
          class="form-control"
          placeholder="Entrer le nombre de points"
        />
      </div>
      <button type="submit" class="btn btn-warning">Ajouter</button>
    </form>
  </div>
</template>

<style scoped>

</style>