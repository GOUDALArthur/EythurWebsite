<script setup lang="ts">
export interface ResultRow {
  position: string;
  country: string;
  name: string;
  team: string | null;
  score: number;
}

const { content, tableType } = defineProps<{
  content: ResultRow[];
  tableType: string;
}>();

let label;

if (tableType === "cyclist") {
  label = "Coureur";
} else if (tableType === "country") {
  label = "Pays";
} else if (tableType === "team") {
  label = "Équipe";
} else {
  label = "";
}
</script>

<template>
  <div class="table-container table-results-container">
    <table class="table-results">
      <thead>
        <tr>
          <th>Pos.</th>
          <th></th>
          <th>{{ label }}</th>
          <th v-if="tableType == 'cyclist'">Équipe</th>
          <th>Score</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in content" :key="row.position">
          <td class="pos-col">{{ row.position }}</td>
          <td class="country-col">
            <img :src="`/assets/flags/${row.country}.png`" alt="Country Flag" class="country-flag" />
          </td>
          <td class="cyclist-col">{{ row.name }}</td>
          <td v-if="tableType == 'cyclist'" class="team-col">{{ row.team || "" }}</td>
          <td class="score-col">{{ row.score }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.cyclist-col,
.team-col {
  text-align: left;
}
</style>