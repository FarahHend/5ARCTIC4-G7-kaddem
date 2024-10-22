import { Routes } from '@angular/router';
import { AddteamComponent } from './component/addteam/addteam.component';
import { EquipeListComponent } from './component/listteam/listteam.component';

export const routes: Routes = [
  { path: 'addteam', component: AddteamComponent },
  { path: 'listteam', component: EquipeListComponent }, // Route for the team list
  { path: '', redirectTo: '/listteam', pathMatch: 'full' } // Redirect to the list team by default
];
