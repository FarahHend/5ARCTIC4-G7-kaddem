export interface Projet {
    idProjet: string;
    nomProjet: string;
    budget: number;
    deadline: string;
    status: string;
    budgetDepasse: boolean;
    enRetard: boolean;
  }
  
export enum ProjectStatus {
    PLANNED = 'PLANNED',
    IN_PROGRESS = 'IN_PROGRESS',
    COMPLETED = 'COMPLETED',
    ON_HOLD = 'ON_HOLD',
    CANCELLED = 'CANCELLED'
}
