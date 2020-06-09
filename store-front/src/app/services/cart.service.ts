import { Injectable } from '@angular/core';
import { AppConstants } from '../constants/app-constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http:HttpClient) { }

  addItem(id:number, qty: number) {
  	let url = AppConstants.serverPath+"/cart/add";
  	let cartItemInfo = {
  		"bookId" : id,
  		"qty" : qty
  	}
  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post<any>(url, cartItemInfo, {headers: tokenHeader});
  }

  getCartItemList() {
  	let url = AppConstants.serverPath+"/cart/getCartItemList";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.get<any>(url, {headers: tokenHeader});
  }

  getShoppingCart() {
  	let url = AppConstants.serverPath+"/cart/getShoppingCart";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.get<any>(url, {headers: tokenHeader});
  }

  updateCartItem(cartItemId: number, qty: number) {
  	let url = AppConstants.serverPath+"/cart/updateCartItem";
  	let cartItemInfo = {
  		"cartItemId" : cartItemId,
  		"qty" : qty
  	}
  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post<any>(url, cartItemInfo, {headers: tokenHeader});
  }

  removeCartItem(id: number) {
  	let url = AppConstants.serverPath+"/cart/removeItem";

  	let tokenHeader = new HttpHeaders({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem("xAuthToken")
  	});
  	return this.http.post<any>(url, id, {headers: tokenHeader});
  }

}
