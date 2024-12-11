import { ContentHomeComponent } from './../common/content/content-home/content-home.component';
import { CUSTOM_ELEMENTS_SCHEMA, Injector, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeModule } from '../modules/home/home.module';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { DarkmodeComponent } from '../features/darkmode/darkmode.component';
import { FooterHomeComponent } from '../common/footer/footer-home/footer-home.component';
import { HeaderHomeComponent } from '../common/header/header-home/header-home.component';
import { Router, RouterModule, Routes } from '@angular/router';
import { AdminComponent } from '../modules/admin/admin.component';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { CarouselComponent } from '../common/component/carousel/carousel.component';
import { CartComponent } from '../modules/home/cart/cart.component';
import { PaymentComponent } from '../pages/payment/payment.component';
import { MainContentComponent } from '../common/content/main-content/main-content.component';
import { ConfirmDialogComponent } from './dialog/confirm/confirm-dialog/confirm-dialog.component';
import { DetailDialogComponent } from './dialog/product-detail/detail-dialog.component';
import { HomeComponent } from '../modules/home/home.component';
import { AdminModule } from '../modules/admin/admin.module';
import { LeftBarComponent } from '../modules/home/left-bar/left-bar.component';
import { AuthInterceptorService } from './core/auth/auth-interceptor.service';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

import { OktaAuthModule, OktaAuthStateService, OktaAuthGuard, OKTA_CONFIG } from '@okta/okta-angular';
import { AppOktaAuthModule } from './dialog/login/okta-auth.module';
@NgModule({
  declarations: [
    AppComponent,
    DarkmodeComponent,
    FooterHomeComponent,
    HeaderHomeComponent,
    CarouselComponent,
    CartComponent,
    MainContentComponent,
  ],
  imports: [//$ only import other module @NgModule
    MatTableModule,
    HomeModule,
    AdminModule,
    BrowserAnimationsModule,
    FormsModule,//$ 2-ways blinding
    MatDialogModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
    BrowserModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
    MatDialogModule,
    MatButtonModule,
    MatCheckboxModule,
    MatPaginatorModule,
    MatSelectModule,
    OktaAuthModule,
    AppOktaAuthModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    provideAnimationsAsync(),
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
