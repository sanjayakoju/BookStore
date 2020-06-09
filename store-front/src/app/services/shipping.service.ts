import { Injectable } from '@angular/core';
import { AppConstants } from '../constants/app-constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserShipping } from '../models/user-shipping';

@Injectable({
  providedIn: 'root'
})
export class ShippingService {

  private serverPath: string = AppConstants.serverPath;

  constructor(private http : HttpClient) { }

  newShipping(shipping: UserShipping) {
  	let url = this.serverPath+"/shipping/add";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post(url, JSON.stringify(shipping), {headers: tokenHeader});
  }

  getUserShippingList() {
  	let url = this.serverPath+"/shipping/getUserShippingList";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.get<any>(url, {headers: tokenHeader});
  }

  removeShipping(id:number){
  	let url = this.serverPath+"/shipping/remove";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post(url, id, {headers: tokenHeader});
  }

  setDefaultShipping(id:number){
  	let url = this.serverPath+"/shipping/setDefault";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post(url, id, {headers: tokenHeader});
  }
}
