import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
    selector: 'app-projet-modal',
    imports: [ReactiveFormsModule],
    standalone: true,
    templateUrl: './projet-modal.component.html',
    styleUrls: ['./projet-modal.component.scss']
})
export class ProjetModalComponent implements OnInit {
    projetForm!: FormGroup;

    constructor(
        private fb: FormBuilder,
        public dialogRef: MatDialogRef<ProjetModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: { idProjet: string; nomProjet: string; budget: number; deadline: string; status: string }
    ) {}

    ngOnInit(): void {
        this.projetForm = this.fb.group({
            nomProjet: [this.data.nomProjet],
            budget: [this.data.budget],
            deadline: [this.data.deadline],
            status: [this.data.status]
        });
    }

    closeModal(): void {
        this.dialogRef.close();
    }

    onSubmit(): void {
        if (this.projetForm.valid) {
            this.dialogRef.close(this.projetForm.value);
        }
    }
}
