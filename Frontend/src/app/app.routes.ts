import { Routes } from '@angular/router';
import { AddteamComponent } from './component/addteam/addteam.component';
import { EquipeListComponent } from './component/listteam/listteam.component';
import { AddprojetComponent } from './component/addprojet/addprojet.component';
import { ListprojetComponent } from './component/listprojet/listprojet.component';

export const routes: Routes = [
  { path: 'addteam', component: AddteamComponent },
  { path: 'listteam', component: EquipeListComponent }, 
  //{ path: '', redirectTo: '/listteam', pathMatch: 'full' } 
  { path: 'addprojet', component: AddprojetComponent },
  { path: 'listprojet', component:  ListprojetComponent },
];
