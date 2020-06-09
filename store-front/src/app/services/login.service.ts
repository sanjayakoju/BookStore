import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AppConstants } from '../constants/app-constants';
import { Token } from '@angular/compiler/src/ml_parser/lexer';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private serverPath:string = AppConstants.serverPath;

  constructor(private http:HttpClient, private router:Router) { }

  sendCredential(username: string, password: string) {
  	let url = this.serverPath+'/token';
  	let encodedCredentials = btoa(username+":"+password);
  	let basicHeader = "Barear "+encodedCredentials;
  	let headers = new HttpHeaders({
  		'Content-Type' : 'application/x-www-form-urlencoded',
  		'Authorization' : basicHeader
  	});

  	return this.http.get<any>(url, {headers: headers});
  }

  checkSession() {
  	let url = this.serverPath+'/checkSession';
  	let headers = new HttpHeaders({
  		'x-auth-token' : localStorage.getItem('xAuthToken')
  	});

  	return this.http.get(url, {headers: headers});
  }

  logout() {
  	let url = this.serverPath+'/user/logout';
  	let headers = new HttpHeaders({
  		'x-auth-token' : localStorage.getItem('xAuthToken')
  	});

  	return this.http.post(url, '', {headers: headers});
  }


  
}
