<script setup lang="ts">
import { useRouter} from "vue-router";

const props = defineProps<{
  content: string,
  color: string,
  route: string
}>()
const router = useRouter();

const handleClick = () => {
  router.push(props.route);
}

function lightenColor(color: string, amount: number): string {
  const usePound = color[0] === "#";
  let col = usePound ? color.slice(1) : color;
  const num = parseInt(col, 16);
  let r = (num >> 16) + amount * 255;
  let g = ((num >> 8) & 0x00FF) + amount * 255;
  let b = (num & 0x0000FF) + amount * 255;
  r = Math.min(255, Math.max(0, r));
  g = Math.min(255, Math.max(0, g));
  b = Math.min(255, Math.max(0, b));
  return (usePound ? "#" : "") + (r << 16 | g << 8 | b).toString(16).padStart(6, '0');
}

function darkenColor(color: string, amount: number): string {
  const usePound = color[0] === "#";
  let col = usePound ? color.slice(1) : color;
  const num = parseInt(col, 16);
  let r = (num >> 16) - amount * 255;
  let g = ((num >> 8) & 0x00FF) - amount * 255;
  let b = (num & 0x0000FF) - amount * 255;
  r = Math.min(255, Math.max(0, r));
  g = Math.min(255, Math.max(0, g));
  b = Math.min(255, Math.max(0, b));
  return (usePound ? "#" : "") + (r << 16 | g << 8 | b).toString(16).padStart(6, '0');
}
</script>

<template>
  <button type="button" class="browser-button" :style="{
    '--button-color': color,
    '--button-hover-color': lightenColor(color, 0.1),
    '--button-border-color': darkenColor(color, 0.1)
  }" @click="handleClick">
    {{ content }}
  </button>
</template>

<style scoped>
button {
  background-color: var(--button-color);
  color: white;
  border: solid 0.3em var(--button-color);
  border-radius: 1.5em;
  cursor: pointer;
}

button:hover {
  background-color: var(--button-hover-color);
  border: solid 0.3em var(--button-border-color);
}
</style>