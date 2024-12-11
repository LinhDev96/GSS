import { Injectable } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { BehaviorSubject, filter } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RouterStateService {

  private hasOutletComponent = new BehaviorSubject<boolean>(false);
  hasOutletComponent$ = this.hasOutletComponent.asObservable();

  constructor(private router: Router) {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      const outlet = this.router.routerState.root.children[0];
      this.hasOutletComponent.next(outlet && outlet.outlet === 'primary');
    });
  }
}
