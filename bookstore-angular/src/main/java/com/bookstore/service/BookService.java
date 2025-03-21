package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.models.Book;

public interface BookService {
	
	List<Book> findAll();
	Optional<Book> findOne(long id);
	Book save(Book book);
	List<Book> blurrySearch(String keyword);
	void removeOne(long id);

}
