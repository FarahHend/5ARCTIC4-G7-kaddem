import { DetailEquipe } from "./DetailEquipe";
import { Niveau } from "./Niveau";


export class Equipe {
  idEquipe: number;
  nomEquipe: string;
  niveau: Niveau;
  detailEquipe?: DetailEquipe;
  projetIds: string[];

  constructor(
    idEquipe: number,
    nomEquipe: string,
    niveau: Niveau,
    detailEquipe?: DetailEquipe,
    projetIds: string[] = []
  ) {
    this.idEquipe = idEquipe;
    this.nomEquipe = nomEquipe;
    this.niveau = niveau;
    this.detailEquipe = detailEquipe;
    this.projetIds = projetIds;
  }
}
