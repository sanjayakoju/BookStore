import { Component, OnInit } from '@angular/core';
import { AppConstants } from 'src/app/constants/app-constants';
import { Book } from 'src/app/models/book';
import { CartItem } from 'src/app/models/cart-item';
import { ShoppingCart } from 'src/app/models/shopping-cart';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  private serverPath = AppConstants.serverPath;
	private selectedBook: Book;
	private cartItemList: CartItem[] = [];
	private cartItemNumber: number;
	private shoppingCart: ShoppingCart = new ShoppingCart();
	private cartItemUpdated: boolean;
	private emptyCart: boolean;
	private notEnoughStock: boolean;

  constructor(
  		private router:Router,
  		private cartService: CartService
  	) { }

  onSelect(book:Book) {
  	this.selectedBook = book;
  	this.router.navigate(['/bookDetail', this.selectedBook.id]);
  }

  onRemoveCartItem(cartItem: CartItem) {
  	this.cartService.removeCartItem(cartItem.id).subscribe(
  		res => {
  			console.log(res.text());
  			this.getCartItemList();
  			this.getShoppingCart();
  		},
  		error => {
  			console.log(error.text());
  		}
  	);
  }

  onUpdateCartItem(cartItem: CartItem) {
  	this.cartService.updateCartItem(cartItem.id, cartItem.qty).subscribe(
  		res => {
  			console.log(res.text());
  			this.cartItemUpdated=true;
  			this.getShoppingCart();
  		},
  		error => {
  			console.log(error.text());
  		}
  	)
  }

  getCartItemList()  {
  	this.cartService.getCartItemList().subscribe(
  		res => {
  			this.cartItemList=res.json();
  			this.cartItemNumber = this.cartItemList.length;
  		},
  		error => {
  			console.log(error.text());
  		}
  	)
  }

  getShoppingCart() {
  	this.cartService.getShoppingCart().subscribe(
  		res => {
  			console.log(res.json());
        this.shoppingCart=res.json();
  		},
  		error => {
  			console.log(error.text());
  		}
  	)
  }

  onCheckout() {
  	if(this.cartItemNumber==0) {
  		this.emptyCart=true;
  	} else {
  		for (let item of this.cartItemList) {
  			if (item.qty > item.book.inStockNumber) {
  				console.log("not enough stock on some item");
  				this.notEnoughStock=true;
  				return;
  			}
  		}

			// this.router.navigate('[/order]');
  	}
  }

  ngOnInit() {
  	this.getCartItemList();
  	this.getShoppingCart();
  }

}
