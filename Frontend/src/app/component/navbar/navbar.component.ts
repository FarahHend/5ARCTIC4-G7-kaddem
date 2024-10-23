import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Import the Router

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'] 
})
export class NavbarComponent {

  constructor(private router: Router) {} 

  goToEquipe(): void {
    this.router.navigate(['/addteam']); 
  }

  goToProject(): void {
    this.router.navigate(['/addprojet']); 
  }

}
