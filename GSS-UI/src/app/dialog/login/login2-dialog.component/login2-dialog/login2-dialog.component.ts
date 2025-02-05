import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-login2-dialog',
  templateUrl: './login2-dialog.component.html',
  styleUrl: './login2-dialog.component.scss'
})

export class Login2DialogComponent implements OnInit {

  constructor(
    private router: Router,
    private dialogRef : MatDialogRef<Login2DialogComponent>,
  ) {}

  loginForm : any;

  async ngOnInit() {
    this.loginForm = new FormGroup({
      userName: new FormControl('', Validators.required),
      passWord: new FormControl('', Validators.required),

    });
  }

  login(){
    if(this.loginForm.get("userName").value === 'linh' &&
        this.loginForm.get("passWord").value === '123'){
      console.log("the administrator log in");
    }
  }

  goToPage(): void {
    this.router.navigate(['/login']); // Replace '/about' with your desired route
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
