import { Injectable } from '@angular/core';
import { AppConstants } from '../constants/app-constants';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ShippingAddress } from '../models/shipping-address';
import { BillingAddress } from '../models/billing-address';
import { Payment } from '../models/payment';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private http: HttpClient) { }

  checkout(
  	shippingAddress: ShippingAddress,
  	billingAddress: BillingAddress,
  	payment:Payment,
  	shippingMethod:string
  	){
		let url = AppConstants.serverPath+"/checkout/checkout";
		let order ={
			"shippingAddress" : shippingAddress,
			"billingAddress" : billingAddress,
			"payment" : payment,
			"shippingMethod" : shippingMethod
		}

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post<any>(url, order, {headers: tokenHeader});
  }

  getUserOrder() {
  	let url = AppConstants.serverPath+"/checkout/getUserOrder";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.get(url, {headers: tokenHeader});

  }

}
