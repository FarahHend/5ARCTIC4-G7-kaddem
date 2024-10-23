import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Projet } from '../../model/Projet'; // Adjust the path as needed
import { ProjetModalComponent } from '../projet-modal/projet-modal.component'; // Your project modal component

@Component({
  selector: 'app-listprojet',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './listprojet.component.html',
  styleUrls: ['./listprojet.component.scss']
})
export class ListprojetComponent implements OnInit {
  projets: Projet[] = [];
  private apiUrl = 'http://localhost:8222/projet';
  private deleteUrl = 'http://localhost:8055/projet';
  private updateUrl = 'http://localhost:8055/projet';

  constructor(private http: HttpClient, private router: Router, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadProjets();
  }

  loadProjets(): void {
    this.http.get<Projet[]>(this.apiUrl).subscribe({
      next: (data) => {
        this.projets = data;
      },
      error: (err) => {
        console.error('Error fetching projets:', err);
      }
    });
  }

  deleteProjet(idProjet: string): void {
    this.http.delete<void>(`${this.deleteUrl}/${idProjet}`).subscribe({
      next: () => {
        this.loadProjets();
      },
      error: (err) => {
        console.error('Error deleting projet:', err.message || err);
      }
    });
  }

  updateProjet(projet: Projet): void {
    const dialogRef = this.dialog.open(ProjetModalComponent, {
      data: { id: projet.idProjet, nomProjet: projet.nomProjet, budget: projet.budget, deadline: projet.deadline, status: projet.status }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.http.put<void>(`${this.updateUrl}/${projet.idProjet}`, result).subscribe({
          next: () => {
            this.loadProjets(); // Refresh the list after updating
          },
          error: (err) => {
            console.error('Error updating projet:', err);
          }
        });
      }
    });
  }
}
