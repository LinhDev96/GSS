import { NgModule } from '@angular/core';
import { OktaAuthModule, OKTA_CONFIG, OktaAuthStateService } from '@okta/okta-angular';
import { oktaConfig } from '@config/app-config';
import OktaAuth from '@okta/okta-auth-js';
import { OktaRoutingModule } from './okta-login/okta-routing.module';
import { OktaLoginComponent } from './okta-login/okta-login.component';
import { TestComponent } from 'src/common/component/test/test.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';


const oktaConf = oktaConfig.oidc;
const oktaAuth = new OktaAuth(oktaConf);

@NgModule({
  declarations: [
    OktaLoginComponent,
    TestComponent
  ],
  imports: [
    OktaAuthModule,
    OktaRoutingModule,
    MatFormFieldModule,
    MatInputModule,

  ],
  providers: [
    {
      provide: OKTA_CONFIG,
      useValue: { oktaAuth, ...oktaConfig.oidc },
    },
    // {
    //   provide: OKTA_CONFIG, useValue: oktaConfig.oidc
    // },
    OktaAuthStateService,
  ],
})
export class AppOktaAuthModule {}
