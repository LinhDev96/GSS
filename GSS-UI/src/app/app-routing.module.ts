import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from '../modules/admin/admin.component';
import { OktaLoginComponent } from './dialog/login/okta-login/okta-login.component';
import { OktaCallbackComponent } from '@okta/okta-angular';
import { TestComponent } from 'src/common/component/test/test.component';
import { CommonModule } from '@angular/common';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () =>
      import('../modules/home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('../modules/admin/admin.module').then(m => m.AdminModule)
  },
  { path: 'login/callback', component: OktaCallbackComponent, pathMatch: 'full' },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
