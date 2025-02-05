import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { OKTA_AUTH } from '@okta/okta-angular';
import OktaAuth from '@okta/okta-auth-js';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private oktaAuth: OktaAuth, private router: Router) {}

  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean | UrlTree> {
    const isAuthenticated = await this.oktaAuth.isAuthenticated();
    if (!isAuthenticated) {
      // Redirect to Okta login page
      // this.oktaAuth.signInWithRedirect({ originalUri: state.url });
      this.oktaAuth.signInWithRedirect({ originalUri: '/home' });
      return false;
    }
    return true;
  }
}
