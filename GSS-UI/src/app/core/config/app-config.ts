import { environment } from "src/environments/environment";

export const oktaConfig = {
  oidc: {
    clientId: environment.CLIENT_ID,
    issuer: environment.ISSUER,
    redirectUri : 'https://localhost:4200/login/callback',
    scopes: ['openid', 'profile', 'email'],
    useClassicEngine: environment.USE_CLASSIC_ENGINE
  },
};
