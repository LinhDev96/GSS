import { Component, EventEmitter, Injectable, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-darkmode',
  templateUrl: './darkmode.component.html',
  styleUrls: ['./darkmode.component.scss']
})
@Injectable({
  providedIn: 'root'
})
export class DarkmodeComponent implements OnInit {
  @Output() darkModeToggled = new EventEmitter<boolean>();
  constructor() { }
  isDarkMode : boolean = false;
  ngOnInit() {
  }

  toggleDarkMode() {

    console.log("toggleDarkModeinDarkmodecomponent");
    this.isDarkMode = !this.isDarkMode;
    console.log(this.isDarkMode);
    this.darkModeToggled.emit(this.isDarkMode);
    console.log("finished in Darkmodecomponent");
  }

}
