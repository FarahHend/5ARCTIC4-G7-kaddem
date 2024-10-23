import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Equipe } from '../../model/Equipe';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { EquipeModalComponent } from '../equipe-modal/equipe-modal.component';
import { EquipeefficaciteComponent } from '../equipeefficacite/equipeefficacite.component'; 
import { EquipeService } from '../../service/equipe.service';

@Component({
    selector: 'app-listteam',
    templateUrl: './listteam.component.html',
    standalone: true,
    imports: [CommonModule],
    styleUrls: ['./listteam.component.scss']
})
export class EquipeListComponent implements OnInit {
    equipes: Equipe[] = [];
    private apiUrl = 'http://localhost:8222/equipe/retrieve-all-equipes';
    private deleteUrl = 'http://localhost:8050/equipe/remove-equipe';
    private updateUrl = 'http://localhost:8050/equipe/update-equipe';

    constructor(private http: HttpClient, private router: Router, private dialog: MatDialog, private equipeService: EquipeService) {}

    ngOnInit(): void {
        this.loadEquipes();
    }

    loadEquipes(): void {
        this.http.get<Equipe[]>(this.apiUrl).subscribe({
            next: (data) => {
                this.equipes = data;
            },
            error: (err) => {
                console.error('Error fetching equipes:', err);
            }
        });
    }

    deleteEquipe(idEquipe: number): void {
        this.http.delete<void>(`${this.deleteUrl}/${idEquipe}`).subscribe({
            next: () => {
                this.loadEquipes();
            },
            error: (err) => {
                console.error('Error deleting equipe:', err.message || err);
            }
        });
    }

    updateEquipe(equipe: Equipe): void {
        const dialogRef = this.dialog.open(EquipeModalComponent, {
            data: { id: equipe.idEquipe, nomEquipe: equipe.nomEquipe, niveau: equipe.niveau },
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.http.put<void>(`${this.updateUrl}/${equipe.idEquipe}`, result).subscribe({
                    next: () => {
                        this.loadEquipes(); // Refresh the list after updating
                    },
                    error: (err) => {
                        console.error('Error updating equipe:', err);
                    }
                });
            }
        });
    }

    // New method to calculate efficiency
    calculerEfficacite(idEquipe: number): void {
        this.equipeService.calculerEfficacite(idEquipe).subscribe({
            next: (efficacite) => {
                const dialogRef = this.dialog.open(EquipeefficaciteComponent, {
                    data: { idEquipe, efficacite },
                });
                
                dialogRef.afterClosed().subscribe();
            },
            error: (err) => {
                console.error('Error calculating efficiency:', err);
            }
        });
    }
}
