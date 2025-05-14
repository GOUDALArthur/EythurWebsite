<script setup lang="ts">
export interface ScheduleRow {
  position: number;
  country: string;
  name: string;
  category: string;
  dateStart: string;
  dateFinish: string;
  stageCount: number;
}

defineProps<{
  content: ScheduleRow[];
}>();
</script>

<template>
  <div class="table-container table-schedule-container">
    <table class="table-schedule">
      <thead>
        <tr>
          <th>Pos.</th>
          <th></th>
          <th>Course</th>
          <th>Catégorie</th>
          <th>Date(s)</th>
          <th>Étape(s)</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in content" :key="row.position">
          <td class="pos-col">{{ row.position }}</td>
          <td class="country-col">
            <img :src="`/assets/flags/${row.country}.png`" alt="Country Flag" class="country-flag" />
          </td>
          <td class="race-col">{{ row.name }}</td>
          <td class="cat-col">{{ row.category }}</td>
          <td class="date-col">
            {{ new Date(row.dateStart).toLocaleDateString("fr-FR", {
               year: "numeric",
               month: "2-digit",
               day: "2-digit",
            }) }}
            <span v-if="row.dateStart !== row.dateFinish">
              - {{ new Date(row.dateFinish).toLocaleDateString("fr-FR", {
                   year: "numeric",
                   month: "2-digit",
                   day: "2-digit" }) }}
            </span>
          </td>
          <td class="stages-col">{{ row.stageCount }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>