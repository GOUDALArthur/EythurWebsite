<script setup lang="ts">
import TableSchedule from "./tables/TableSchedule.vue";
import type { ScheduleRow } from "./tables/TableSchedule.vue";
import { ref, onMounted } from "vue";
import { getRaces, getRaceStages } from "../api/apiPcm.ts";
import type { Race } from "../types/race.ts";
import type { Stage } from "../types/stage.ts";

const scheduleData = ref<ScheduleRow[]>([]);
const races = ref<Race[]>([]);
const stages = ref<Stage[]>([]);

const loading = ref<boolean>(true);
const error = ref<string | null>(null);

onMounted(async () => {
  try {
    races.value = await getRaces();
    for (let i = 0; i < races.value.length; i++) {
      const race = races.value[i];
      stages.value = await getRaceStages(race.id);
      scheduleData.value.push({
        position: i + 1,
        country: race.country.isoCode,
        name: race.name,
        category: race.raceCategory.code,
        dateStart: stages.value[0].date,
        dateFinish: stages.value[stages.value.length - 1].date,
        stageCount: stages.value.length,
      });
    }
  } catch(err) {
    error.value = "Une erreur s'est produite lors du chargement des données.";
    console.error(err);
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <div id="schedule-container">
    <h1>Calendrier</h1>
    <div v-if="error" class="error-message">{{ error }}</div>
    <div v-else-if="!loading" class="table-schedule-parent mt-4">
      <TableSchedule :content="scheduleData" />
    </div>
  </div>
</template>

<style scoped>
.table-schedule-parent {
  display: flex;
  justify-content: center;
  width: 100%;
}
</style>