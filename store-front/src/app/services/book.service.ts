import { Injectable } from '@angular/core';
import { AppConstants } from '../constants/app-constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient) { }

  getBookList() {
  	let url = AppConstants.serverPath+"/book/bookList";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.get<any>(url, {headers: tokenHeader});
  }

  getBook(id:number) {
  	let url = AppConstants.serverPath+"/book/"+id;

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.get<any>(url, {headers: tokenHeader});
  }

  searchBook(keyword:string) {
  	let url = AppConstants.serverPath+"/book/searchBook";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post<any>(url, keyword, {headers: tokenHeader});
  }

}
