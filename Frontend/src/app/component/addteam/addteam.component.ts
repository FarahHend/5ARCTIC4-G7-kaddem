import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { EquipeService } from '../../service/equipe.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-addteam',
  standalone: true,
  imports: [ReactiveFormsModule, MatInputModule, MatSelectModule, MatButtonModule, CommonModule],
  templateUrl: './addteam.component.html',
  styleUrls: ['./addteam.component.scss']
})
export class AddteamComponent {
  teamForm: FormGroup;
  niveaux = ['JUNIOR', 'SENIOR', 'EXPERT'];

  constructor(
    private fb: FormBuilder,
    private equipeService: EquipeService,
    private router: Router,
    private http: HttpClient
  ) {
    this.teamForm = this.fb.group({
      nomEquipe: ['', Validators.required],
      niveau: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.teamForm.valid) {
      const formValues = this.teamForm.value;
      this.http.post('http://localhost:8050/equipe/add-equipe', formValues) 
        .subscribe({
          next: (response) => {
            console.log('Equipe added successfully:', response);
            this.router.navigate(['/listteam']);
          },
          error: (err) => {
            console.error('Error adding equipe:', err);
            this.router.navigate(['/listteam']);
          }
        });
    }
  }

}
