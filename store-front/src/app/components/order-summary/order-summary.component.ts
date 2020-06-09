import { Component, OnInit } from '@angular/core';
import { AppConstants } from 'src/app/constants/app-constants';
import { Order } from 'src/app/models/order';
import { CartItem } from 'src/app/models/cart-item';
import { CheckoutService } from 'src/app/services/checkout.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {

  private serverPath = AppConstants.serverPath;
	private order:Order = new Order();
	private estimatedDeliveryDate: string;
	private cartItemList: CartItem[] = [];

  constructor(
  	private checkoutService: CheckoutService,
  	private route: ActivatedRoute,
  	private router: Router
  	) { }

  ngOnInit() {
  	this.route.queryParams.subscribe(params => {
  		this.order = JSON.parse(params['order']);

  		let deliveryDate = new Date();

  		if(this.order.shippingMethod=="groundShipping") {
  			deliveryDate.setDate(deliveryDate.getDate()+5);
  		} else {
  			deliveryDate.setDate(deliveryDate.getDate()+3);
  		}

  		let days=["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
  		this.estimatedDeliveryDate = days[deliveryDate.getDay()]+', '+deliveryDate.getFullYear()+'/'+deliveryDate.getMonth()+'/'+deliveryDate.getDate();


  		this.cartItemList = this.order.cartItemList;
  	});
  }


}
