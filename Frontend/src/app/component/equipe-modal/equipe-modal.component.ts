import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
    selector: 'app-equipe-modal',
    imports: [ReactiveFormsModule],
    standalone: true,
    templateUrl: './equipe-modal.component.html',
    styleUrls: ['./equipe-modal.component.scss']
})
export class EquipeModalComponent implements OnInit {
    equipeForm!: FormGroup;

    constructor(
        private fb: FormBuilder,
        public dialogRef: MatDialogRef<EquipeModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: { id: number; nomEquipe: string; niveau: string }
    ) {}

    ngOnInit(): void {
        this.equipeForm = this.fb.group({
            nomEquipe: [this.data.nomEquipe],
            niveau: [this.data.niveau]
        });
    }

    closeModal(): void {
        this.dialogRef.close();
    }

    onSubmit(): void {
        if (this.equipeForm.valid) {
            this.dialogRef.close(this.equipeForm.value);
        }
    }
}
