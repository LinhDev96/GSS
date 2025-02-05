import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  isLeftBarHidden = false;
  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        const currentUrl = this.router.url;
        console.log('current url:', currentUrl);
        currentUrl.includes('checkout') || currentUrl.includes('payment') ? this.isLeftBarHidden = true : this.isLeftBarHidden = false ;
      });
  }
}
