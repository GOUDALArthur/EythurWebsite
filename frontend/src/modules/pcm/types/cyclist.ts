export class Cyclist {
    id: number;
    firstName: string;
    lastName: string;
    teamId: number;
    countryId: number;

    constructor(id: number, firstName: string, lastName: string, teamId: number, countryId: number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamId = teamId;
        this.countryId = countryId;
    }

    get fullName(): string {
        return `${this.firstName} ${this.lastName}`;
    }
}