import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  imgUrls: any;
constructor() { }

private dataSubject = new BehaviorSubject<any>(null);

// Observable để các component có thể đăng ký nhận dữ liệu
data$ = this.dataSubject.asObservable();

// Hàm cập nhật dữ liệu
sendData(data: any) {
  this.dataSubject.next(data);
}

}
