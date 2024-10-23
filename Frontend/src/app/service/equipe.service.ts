// src/app/services/equipe.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Equipe } from '../model/Equipe';

@Injectable({
  providedIn: 'root'
})
export class EquipeService {
  private baseUrl = 'http://localhost:8222/equipe';

  constructor(private http: HttpClient) {}

  getEquipes(): Observable<Equipe[]> {
    return this.http.get<Equipe[]>(`${this.baseUrl}/retrieve-all-equipes`);
  }

  getEquipe(idEquipe: number): Observable<Equipe> {
    return this.http.get<Equipe>(`${this.baseUrl}/retrieve-equipe/${idEquipe}`);
  }

  addEquipe(equipe: Equipe): Observable<Equipe> {
    return this.http.post<Equipe>(`${this.baseUrl}/add-equipe`, equipe);
  }

  updateEquipe(idEquipe: number, equipe: Equipe): Observable<Equipe> {
    return this.http.put<Equipe>(`${this.baseUrl}/update-equipe/${idEquipe}`, equipe);
  }

  deleteEquipe(idEquipe: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/remove-equipe/${idEquipe}`);
  }

  // New method to calculate the efficiency of a team
  calculerEfficacite(idEquipe: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/efficacite/${idEquipe}`);
  }
}
