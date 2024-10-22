// src/app/models/projet.model.ts
export interface Projet {
    idProjet?: string;        // Optional because it may not be set when creating a new project
    nomProjet: string;
    budget: number;
    deadline: string;        // Consider using 'string' or 'Date' based on your needs
    budgetDepasse: boolean;
    enRetard: boolean;
    status: ProjectStatus;   // Define this enum as well
}


export enum ProjectStatus {
    PLANNED = 'PLANNED',
    IN_PROGRESS = 'IN_PROGRESS',
    COMPLETED = 'COMPLETED',
    ON_HOLD = 'ON_HOLD',
    CANCELLED = 'CANCELLED'
}
