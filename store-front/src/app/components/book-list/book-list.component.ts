import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { AppConstants } from 'src/app/constants/app-constants';
import { BookService } from 'src/app/services/book.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  public filterQuery = "";
	public rowsOnPage = 5;

	private selectedBook: Book;
	private bookList: Book[];
  private serverPath = AppConstants.serverPath;
  
  constructor(
    private bookService:BookService,
    private router:Router,
		private http:HttpClient,
		private route:ActivatedRoute
  ) { }

  onSelect(book: Book) {
		this.selectedBook = book;
		this.router.navigate(['/bookDetail', this.selectedBook.id]);
	}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
			if(params['bookList']) {
				console.log("filtered book list");
				this.bookList = JSON.parse(params['bookList']);
			} else {
				this.bookService.getBookList().subscribe(
					res => {
						console.log(res.json());
						this.bookList = res.json();
					},
					err => {
						console.log(err);
					}
					);
			}
		});

	}

}
