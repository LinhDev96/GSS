import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { ErrorHandlerService } from './error-handler.service';
import { environment } from 'src/environments/environment';

export interface orderParams {
  name: string;
  time?: string;
  price?: string;
}
@Injectable({
  providedIn: 'root'
})
export class CoreService {

// private baseUrl = 'http://localhost:8081/';
  private baseUrl = environment.GssApiUrl;

constructor(
  private http:HttpClient ,
  private errorHandler: ErrorHandlerService,
) { }



  getData(): Observable<any> {
    return this.http.get(this.baseUrl + `product/all`);
  }

  fetchPagedSearchResults(): Observable<any> {
    return this.http.get(this.baseUrl + `product/all`);
  }

  postData(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}product`, data);
  }

  updateData(id: string, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/data/${id}`, data);
  }

  deleteData(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}product/delete/${id}`);
  }

  // deleteMultipleProduct(ids: string[]): Observable<Object> {
  //   let ids = ids
  //   return this.http.delete(`${this.baseUrl}product/delete/${id}`,{
  //     headers
  //   });
  // }
  getProductByFilter(filters : string[],sortParams: { sort: string; pageSize: number; page: number }): Observable<any> {
    // let params = {
    //   sort: "DESC",
    //   paging: 30,
    //   page:0,
    // };
    // const params = new HttpParams({
    //   fromObject: sortParams
    // });
    return this.http
      .get(
        this.baseUrl+`product/getBy?category=${filters[0]}&time=${filters[1]}&name=${filters[2]}&price=${filters[3]}&bestseller=${filters[4]}`,
        {
          params: sortParams,
        }
      ).pipe(
        catchError(this.errorHandler.handleError)
      );
    // return this.http.get(`${this.baseUrl}product/all`);
  }

  deleteMultipleProduct(ids: string[]) {
    let params = {
      ids: ids.join(','),
    };
    return this.http
      .delete(
        `${this.baseUrl}product/delete`,
        {
          params: params,
        }
      ).pipe(
        catchError(this.errorHandler.handleError)
      );
  }
}
