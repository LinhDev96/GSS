import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { from, lastValueFrom, Observable } from 'rxjs';
import { OKTA_AUTH } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return from(this.handleAccess(req, next));
    // throw new Error('Method not implemented.');
  }

  private async handleAccess(request : HttpRequest<any>, next: HttpHandler) : Promise<HttpEvent<any>> {
    const securityEndPoint = ['http://localhost:8080/api/auth/login','http://localhost:4200/home'];
    if(securityEndPoint.some(url => request.urlWithParams.includes(url))){
      const accessToken = this.oktaAuth.getAccessToken();
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer` + accessToken
        }
      })
    }
    // throw new Error('Method not implemented.');
    return await lastValueFrom(next.handle(request));
  }
}
