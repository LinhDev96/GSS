import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { BrowserModule } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { CarouselComponent } from '../common/component/carousel/carousel.component';
import { MainContentComponent } from '../common/content/main-content/main-content.component';
import { FooterHomeComponent } from '../common/footer/footer-home/footer-home.component';
import { HeaderHomeComponent } from '../common/header/header-home/header-home.component';
import { DarkmodeComponent } from '../features/darkmode/darkmode.component';
import { AdminModule } from '../modules/admin/admin.module';
import { CartComponent } from '../modules/home/cart/cart.component';
import { HomeModule } from '../modules/home/home.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthInterceptorService } from './core/auth/auth-interceptor.service';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

import { OKTA_CONFIG, OktaAuthModule, OktaConfig } from '@okta/okta-angular';
// import { OktaConfigService } from './services/okta-config.service';
import { MatSortModule } from '@angular/material/sort';
import { oktaConfig } from '@config/app-config';
import OktaAuth from '@okta/okta-auth-js';
import { AppOktaAuthModule } from './dialog/login/okta-auth.module';
import { MaterialModule } from './material/material.module';
import { OktaConfigService } from '../common/service/OktaConfigService.service';


const oktaAuth = new OktaAuth(oktaConfig.oidc);
const moduleConfig: OktaConfig = { oktaAuth };

export function OktaConfigFactory(oktaConfigService: OktaConfigService) {
  return oktaConfigService.getOktaConfig();
}

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
    // MatTableModule,
    HomeModule,
    AdminModule,
    MatTableModule,
    MatSortModule,
    // BrowserAnimationsModule,
    // FormsModule,//$ 2-ways blinding
    // MatDialogModule,
    AppRoutingModule,
    // ReactiveFormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
    BrowserModule,
    // BrowserAnimationsModule,
    // MatTableModule,
    // MatInputModule,
    // MatFormFieldModule,
    // BrowserModule,
    // BrowserAnimationsModule,
    // MatDialogModule,
    // MatButtonModule,
    // MatDialogModule,
    // MatButtonModule,
    // MatCheckboxModule,
    // MatPaginatorModule,
    // MatSelectModule,
    OktaAuthModule,
    AppOktaAuthModule,
    MaterialModule,
    OktaAuthModule.forRoot(moduleConfig),
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    provideAnimationsAsync(),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService, multi: true},
      // { provide: OKTA_CONFIG, useValue: oktaConfig.oidc }
      // OktaConfigService,
      { provide: OKTA_CONFIG, useFactory: OktaConfigFactory, deps: [OktaConfigService]}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
