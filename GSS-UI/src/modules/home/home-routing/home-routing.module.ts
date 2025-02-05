import { ContentHomeComponent } from './../../../common/content/content-home/content-home.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../home.component';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from '../cart/cart.component';
import { LeftBarComponent } from '../left-bar/left-bar.component';
import { CheckoutComponent } from '../checkout/checkout.component';
import { Checkout2Component } from '../checkout2/checkout2.component';
import { PaymentComponent } from '../../../pages/payment/payment.component';
import { AuthGuard } from 'src/app/core/auth/auth-guard';
import { OktaAuthGuard } from '@okta/okta-angular';


const routes: Routes = [
  { path: '', component: HomeComponent ,
    children: [
      { path: '', component: LeftBarComponent, outlet: "leftBar", pathMatch: 'full' },
      { path: '', component: ContentHomeComponent, outlet: "primary",pathMatch: 'full'},
    ],
  },
  { path: 'cart', component: HomeComponent ,
    children: [
      { path: '', component: CartComponent, outlet: "primary" ,pathMatch: 'full'},
      { path: '', component: LeftBarComponent, outlet: "leftBar" ,pathMatch: 'full' },
      { path: 'checkout', component: Checkout2Component, outlet: "primary" ,pathMatch: 'full',canActivate: [AuthGuard]},
      // { path: 'checkout', component: Checkout2Component, outlet: "primary" ,pathMatch: 'full'},
      { path: 'payment', component: PaymentComponent, outlet: "primary" ,pathMatch: 'full'},
    ],
  },
];
@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
