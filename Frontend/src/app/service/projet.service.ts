// src/app/services/projet.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Projet } from '../model/Projet';

@Injectable({
    providedIn: 'root'
})
export class ProjetService {
    private baseUrl = 'http://localhost:8222/projet';

    constructor(private http: HttpClient) {}

    // Create a new project
    createProjet(projet: Projet): Observable<Projet> {
        return this.http.post<Projet>(this.baseUrl, projet);
    }

    // Get a project by ID
    getProjetById(idProjet: string): Observable<Projet> {
        return this.http.get<Projet>(`${this.baseUrl}/${idProjet}`);
    }

    // Get all projects
    getAllProjets(): Observable<Projet[]> {
        return this.http.get<Projet[]>(this.baseUrl);
    }

    // Update an existing project
    updateProjet(idProjet: string, projet: Projet): Observable<Projet> {
        return this.http.put<Projet>(`${this.baseUrl}/${idProjet}`, projet);
    }

    // Delete a project
    deleteProjet(idProjet: string): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${idProjet}`);
    }
}
