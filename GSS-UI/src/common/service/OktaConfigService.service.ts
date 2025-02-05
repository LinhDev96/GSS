import { Injectable } from '@angular/core';
import { oktaConfig } from '@config/app-config';
import { environment } from 'src/environments/environment';

@Injectable()
export class OktaConfigService {

constructor() {}

  getOktaConfig() {
    return {
      clientId: environment.CLIENT_ID,
      issuer: environment.ISSUER,
      redirectUri: oktaConfig.oidc.redirectUri,
      scopes: oktaConfig.oidc.scopes,
      pkce: true
    };
  }
}
