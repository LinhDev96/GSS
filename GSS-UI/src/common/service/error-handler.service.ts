import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

constructor() { }
handleError(error: HttpErrorResponse) {
  let errorMessage = 'Unknown error!';
  if (error.error instanceof ErrorEvent) {
    // Lỗi phía client hoặc mạng
    errorMessage = `Error: ${error.error.message}`;
  } else {
    // Lỗi phía server
    if (error.status === 200 && !error.ok) {
      errorMessage = `Server returned status 200 but response indicates an error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
  }
  return throwError(() => new Error(errorMessage));
}
}
