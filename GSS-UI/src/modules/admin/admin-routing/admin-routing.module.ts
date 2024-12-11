import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from '../admin.component';

const routes: Routes = [
  { path: '', component: AdminComponent ,
    // children: [
    //   { path: '', component: AdminComponent }, // outlet mặc định
    //   // { path: '', component: LeftBarComponent, }, // outlet phụ tên là "aux"
    // ],
  },
  // { path: 'cart', component: CartComponent ,
  //   children: [
  //     { path: '', component: CartComponent }, // outlet mặc định
  //     { path: '', component: LeftBarComponent, outlet: 'leftBar' }, // outlet phụ tên là "aux"
  //   ],
  // },
  // { path: 'cart', component: CartComponent },
  // { path: 'cart', component: CartComponent },
  // { path: '**', redirectTo: 'admin', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
