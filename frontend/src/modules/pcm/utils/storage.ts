import { loadAndCacheResource } from "../../../lib/utils/storage.ts";
import { getCyclists, getCountries, getTeams } from "../api/apiPcm.ts";

export async function loadAndCachePcmResource() {
    await Promise.all([
        loadAndCacheResource('cyclists', getCyclists),
        loadAndCacheResource('countries', getCountries),
        loadAndCacheResource('teams', getTeams),
    ]);
}