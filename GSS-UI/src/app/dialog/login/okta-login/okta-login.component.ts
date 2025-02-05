import { Component, Inject, OnInit } from '@angular/core';
import { OKTA_AUTH } from '@okta/okta-angular';
import OktaAuth, { Tokens } from '@okta/okta-auth-js';
import { OktaSignIn } from '@okta/okta-signin-widget';
import { oktaConfig } from '../../../core/config/app-config';
const DEFAULT_ORIGINAL_URI = window.location.origin;

@Component({
  selector: 'app-okta-login',
  templateUrl: './okta-login.component.html',
  styleUrls: ['./okta-login.component.css'],
})
export class OktaLoginComponent implements OnInit {
  oktaSignin: any;
  constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth) {}

  widget = new OktaSignIn({
    logo: 'assets/logo/logo.png',
    baseUrl: oktaConfig.oidc.issuer.split('/oauth2')[0],
    clientId: oktaConfig.oidc.clientId,
    redirectUri: oktaConfig.oidc.redirectUri,
    // authParams: {},
    useClassicEngine: oktaConfig.oidc.useClassicEngine, //currently use classic engine
    features: { registration: true }  // Kích hoạt tính năng đăng ký
  });

  ngOnInit() {
    const originalUri = this.oktaAuth.getOriginalUri();
    if (!originalUri || originalUri === DEFAULT_ORIGINAL_URI) {
      this.oktaAuth.setOriginalUri('/');
    }
    this.widget
      .showSignInToGetTokens({
        el: '#okta-sign-in-widget',
      })
      .then((tokens: Tokens) => {
        this.widget.remove();
        this.oktaAuth.handleLoginRedirect(tokens);
      })
      .catch((err: any) => {
        throw err;
      });
  }

  ngOnDestroy(): void {
    this.widget.remove();
  }
}
