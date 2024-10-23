import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'; // Import HttpClient
import { CommonModule } from '@angular/common';
import { Projet, ProjectStatus } from '../../model/Projet';

@Component({
  selector: 'app-addprojet',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './addprojet.component.html',
  styleUrls: ['./addprojet.component.scss'] // Fixed typo from styleUrl to styleUrls
})
export class AddprojetComponent implements OnInit {
  projetForm!: FormGroup;
  projectStatuses = Object.values(ProjectStatus);  
  private apiUrl = 'http://localhost:8055/projet'; 

  constructor(
    private fb: FormBuilder,
    private http: HttpClient, // Inject HttpClient
    private router: Router
  ) {}

  ngOnInit(): void {
    this.projetForm = this.fb.group({
      nomProjet: ['', Validators.required],
      budget: [0, [Validators.required, Validators.min(1)]],
      deadline: ['', Validators.required],
      status: [ProjectStatus.PLANNED, Validators.required],  
      budgetDepasse: [false],
      enRetard: [false]
    });
  }

  onSubmit(): void {
    if (this.projetForm.valid) {
      const formValues = this.projetForm.value;
      this.http.post(this.apiUrl, formValues) // Use the API URL here
        .subscribe({
          next: (response) => {
            console.log('Projet added successfully:', response);
            this.router.navigate(['/listprojet']); // Navigate to project list after success
          },
          error: (err) => {
            console.error('Error adding projet:', err);
            this.router.navigate(['/listprojet']); // Navigate even if there’s an error
          }
        });
    }
  }
}
