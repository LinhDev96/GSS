import { Injector, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../app/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { FooterHomeComponent } from '../../common/footer/footer-home/footer-home.component';
import { CarouselComponent } from '../../common/component/carousel/carousel.component';
import { Login2DialogComponent } from '../../app/dialog/login/login2-dialog.component/login2-dialog/login2-dialog.component';
import { DialogModule } from '../../app/dialog/dialog.module';
import { LeftBarComponent } from './left-bar/left-bar.component';
import { HomeRoutingModule } from './home-routing/home-routing.module';
import { HomeComponent } from './home.component';
import { ContentHomeComponent } from '../../common/content/content-home/content-home.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { PaymentComponent } from '../../pages/payment/payment.component';
import { LoginStatusComponent } from '../../app/dialog/login/login-status/login-status.component';

import { OktaAuthModule, OktaAuthStateService, OktaAuthGuard, OKTA_CONFIG } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';
import { oktaConfig} from '../../app/core/config/app-config';
import { OktaLoginComponent } from 'src/app/dialog/login/okta-login/okta-login.component';
import { TestComponent } from 'src/common/component/test/test.component';
import { Router } from '@angular/router';
import { AppOktaAuthModule } from 'src/app/dialog/login/okta-auth.module';
import { DropdownListComponent } from 'src/common/component/dropdown-list/dropdown-list.component';
import { HttpClientModule } from '@angular/common/http';
import { Checkout2Component } from './checkout2/checkout2.component';
import { CartComponent } from './cart/cart.component';
@NgModule({
  declarations: [
    HomeComponent,
    ContentHomeComponent,
    LeftBarComponent,
    DropdownListComponent,
    CheckoutComponent,
    Checkout2Component,
    PaymentComponent,
    LoginStatusComponent,
    // CartComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    TranslateModule,
    // DialogModule,
    HomeRoutingModule,
    OktaAuthModule,
    AppOktaAuthModule,
    HttpClientModule,
    // FormsModule,
  ],
  providers: [
  ],
  exports: [HomeComponent, TranslateModule],
})
export class HomeModule { }
