import { ContentHomeComponent } from './../../../common/content/content-home/content-home.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../home.component';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from '../cart/cart.component';
import { LeftBarComponent } from '../left-bar/left-bar.component';
import { CheckoutComponent } from '../checkout/checkout.component';
import { PaymentComponent } from '../../../pages/payment/payment.component';


const routes: Routes = [
  { path: '', component: HomeComponent ,
    children: [
      { path: '', component: LeftBarComponent, outlet: "leftBar", pathMatch: 'full' },
      { path: '', component: ContentHomeComponent, outlet: "primary",pathMatch: 'full'},
    ],
  },
  { path: 'cart', component: HomeComponent ,
    children: [
      { path: '', component: CartComponent, outlet: "primary" ,pathMatch: 'full'}, // outlet mặc định
      { path: '', component: LeftBarComponent, outlet: "leftBar" ,pathMatch: 'full' }, // outlet phụ tên là "aux"
      { path: 'checkout', component: CheckoutComponent, outlet: "primary" ,pathMatch: 'full'},
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
