//**********************************************************************************************************************
// region IMPORTS
import axios from "../../../lib/api/axios.ts";
import type { Country } from "../types/country.ts";
import type { Cyclist } from "../types/cyclist.ts";
import type { Race } from "../types/race.ts";
import type { CumulativeScore, Score } from "../types/score.ts";
import type { Stage } from "../types/stage.ts";
import type { Team } from "../types/team.ts";
// endregion
//**********************************************************************************************************************

//**********************************************************************************************************************
// region CONSTANTS
const PCM_URL = "/pcm";
const COUNTRIES_URL = `${PCM_URL}/countries`;
const CYCLISTS_URL = `${PCM_URL}/cyclists`;
const RACES_URL = `${PCM_URL}/races`;
const SCORES_URL = `${PCM_URL}/scores`;
const STAGES_URL = `${PCM_URL}/stages`;
const TEAMS_URL = `${PCM_URL}/teams`;
// endregion
//**********************************************************************************************************************

//**********************************************************************************************************************
// region METHODS

// region COUNTRIES
export async function getCountries(): Promise<Country[]> {
    const response = await axios.get<Country[]>(COUNTRIES_URL);
    return response.data;
}

export async function getCountry(id: string): Promise<Country> {
    const response = await axios.get<Country>(`${COUNTRIES_URL}/${id}`);
    return response.data;
}
// endregion

// region CYCLISTS
export async function getCyclists(): Promise<Cyclist[]> {
    const response = await axios.get<Cyclist[]>(CYCLISTS_URL);
    return response.data;
}
// endregion

// region RACES
export async function getRaces(): Promise<Race[]> {
    const response = await axios.get<Race[]>(RACES_URL);
    return response.data;
}
// endregion

// region SCORES
export async function getScores(): Promise<Score[]> {
    const response = await axios.get<Score[]>(SCORES_URL);
    return response.data;
}

export async function getListCyclistCumulativeScore(raceIdMax: number): Promise<CumulativeScore[]> {
    const response = await axios.get<CumulativeScore[]>(`${SCORES_URL}/cyclists/cumulative/${raceIdMax}`);
    return response.data;
}

export async function getListTeamCumulativeScore(raceIdMax: number): Promise<CumulativeScore[]> {
    const response = await axios.get<CumulativeScore[]>(`${SCORES_URL}/teams/cumulative/${raceIdMax}`);
    return response.data;
}

export async function getListCountryCumulativeScore(raceIdMax: number): Promise<CumulativeScore[]> {
    const response = await axios.get<CumulativeScore[]>(`${SCORES_URL}/countries/cumulative/${raceIdMax}`);
    return response.data;
}

export async function addScore(score: Omit<Score, "id">): Promise<Score> {
    console.log("Adding score", score);
    const response = await axios.post<Score>(SCORES_URL, score);
    return response.data;
}
// endregion

// region STAGES
export async function getStages(): Promise<Stage[]> {
    const response = await axios.get<Stage[]>(STAGES_URL);
    return response.data;
}

export async function getStage(id: number): Promise<Stage> {
    const response = await axios.get<Stage>(`${STAGES_URL}/${id}`);
    return response.data;
}

export async function getRaceStages(raceId: number): Promise<Stage[]> {
    const response = await axios.get<Stage[]>(`${STAGES_URL}/races/${raceId}`);
    return response.data;
}
// endregion

// region TEAMS
export async function getTeams(): Promise<Team[]> {
    const response = await axios.get<Team[]>(TEAMS_URL);
    return response.data;
}
// endregion

// endregion
//**********************************************************************************************************************
