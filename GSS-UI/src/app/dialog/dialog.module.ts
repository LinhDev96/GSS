import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Login2DialogComponent } from './login/login2-dialog.component/login2-dialog/login2-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DetailDialogComponent } from './product-detail/detail-dialog.component';
import { TranslateModule } from '@ngx-translate/core';
import { ConfirmDialogComponent } from './confirm/confirm-dialog/confirm-dialog.component';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  declarations: [
    Login2DialogComponent,
    DetailDialogComponent,
    ConfirmDialogComponent

  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    TranslateModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class DialogModule { }
