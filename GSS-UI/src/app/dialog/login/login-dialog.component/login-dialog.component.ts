import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrl: './login-dialog.component.scss',
})
export class LoginDialogComponent implements OnInit {
  loginForm!: FormGroup;
  userName: any;
  passWord: any;
  skillForm: FormGroup;

    constructor(private fb: FormBuilder) {
      this.skillForm = this.fb.group({
        userName: [''], 
        passWord: ['']  
      });
    }

  async ngOnInit() {

  }
  login(){
    const userNameForm = this.skillForm.get('userName')?.value;
    const passWordForm = this.skillForm.get('passWord')?.value;
    if(userNameForm === "linh" && passWordForm === "123"){
      console.log("admin checked");
    }
  }

  closeLoginDialog() {
  }

}
