import { SocialComponent } from './components/social/social.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: 'home', component:HomeComponent},
  {path: 'profile', component:ProfileComponent},
  {path: 'login', component:SocialComponent},
  {path: '**', redirectTo: '/home', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
