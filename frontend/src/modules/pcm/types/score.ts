export interface Score {
    id: number;
    raceId: number;
    cyclistId: number;
    points: number;
}

export interface CumulativeScore {
    instanceId: number;
    totalPoints: number;
    raceCount: number;
}