import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OktaCallbackComponent } from '@okta/okta-angular';
import { OktaLoginComponent } from './okta-login.component';

const routes: Routes = [
  { path: 'login', component: OktaLoginComponent, pathMatch: 'full' },
  { path: 'login/callback', component: OktaCallbackComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OktaRoutingModule { }
