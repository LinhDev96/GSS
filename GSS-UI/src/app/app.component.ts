import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { DarkmodeComponent } from '../features/darkmode/darkmode.component';
import { of } from 'rxjs';
import { MatTableModule } from '@angular/material/table';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  selectedLanguage : any;
  darkMode: boolean = false;
  isDarkModeObservable = of(this.darkmode.isDarkMode);
  isDarkMode: boolean = false;

  showHome: boolean = true;

  constructor(
    private router: Router,
    private darkmode : DarkmodeComponent,
    public dialog: MatDialog,
    private translate: TranslateService) {
    translate.setDefaultLang('en');
    translate.use('en');
  }
  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateView(event.url);
      }
    });
  }

  updateView(url: string) {
    this.showHome = !url.includes('/admin');
  }

  openLoginDialog(): void {

  }
  buttonClick(){

  }
  changeLanguage(){
    this.translate.use(this.selectedLanguage);
  }

  toggleDarkMode2(event: boolean) {
    this.isDarkMode = event;
    console.log("run in AppComponent");
    console.log(this.isDarkMode);
  }

  toggleDarkMode() {
    this.darkmode.toggleDarkMode();
    this.isDarkModeObservable = of(this.darkmode.isDarkMode);
    console.log("abc");
  }
}
