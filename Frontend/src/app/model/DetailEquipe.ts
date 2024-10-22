import { Equipe } from "./Equipe";


export class DetailEquipe {
  idDetailEquipe: number;
  salle: number;
  thematique: string;
  equipe?: Equipe; 

  constructor(idDetailEquipe: number, salle: number, thematique: string, equipe?: Equipe) {
    this.idDetailEquipe = idDetailEquipe;
    this.salle = salle;
    this.thematique = thematique;
    this.equipe = equipe;
  }
}
