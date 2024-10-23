import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-equipeefficacite',
  standalone: true,
  imports: [],
  template: `
        <h1 mat-dialog-title>Efficiencité de l'Équipe</h1>
        <div mat-dialog-content>
            <p>Équipe ID: {{ data.idEquipe }}</p>
            <p>Efficiencité: {{ data.efficacite }}%</p>
        </div>
        <div mat-dialog-actions>
            <button mat-button (click)="onClose()">Fermer</button>
        </div>
    `,
  styleUrl: './equipeefficacite.component.scss'
})
export class EquipeefficaciteComponent {

  constructor(
    public dialogRef: MatDialogRef<EquipeefficaciteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { idEquipe: number; efficacite: number }
) {}

onClose(): void {
    this.dialogRef.close();
}
}
