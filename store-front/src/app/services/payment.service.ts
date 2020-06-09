import { Injectable } from '@angular/core';
import { AppConstants } from '../constants/app-constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserPayment } from '../models/user-payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private serverPath: string = AppConstants.serverPath;

  constructor(private http : HttpClient) { }

  newPayment(payment: UserPayment) {
  	let url = this.serverPath+"/payment/add";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post(url, JSON.stringify(payment), {headers: tokenHeader});
  }

  getUserPaymentList() {
  	let url = this.serverPath+"/payment/getUserPaymentList";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.get<any>(url,  {headers: tokenHeader});
  }

  removePayment(id: number) {
  	let url = this.serverPath+"/payment/remove";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post(url, id, {headers: tokenHeader});
  }

  setDefaultPayment (id: number) {
  	let url = this.serverPath+"/payment/setDefault";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post(url, id, {headers: tokenHeader});
  }
}
