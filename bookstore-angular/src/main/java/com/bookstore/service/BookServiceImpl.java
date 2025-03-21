package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.models.Book;
import com.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();

		List<Book> activeBookList = new ArrayList<>();
		for (Book book : bookList) {
			if (book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}

	@Override
	public Optional<Book> findOne(long id) {

		return bookRepository.findById(id);
	}

	@Override
	public Book save(Book book) {

		return bookRepository.save(book);
	}

	@Override
	public List<Book> blurrySearch(String keyword) {
		List<Book> bookList = (List<Book>) bookRepository.findByTitleContaining(keyword);

		List<Book> activeBookList = new ArrayList<>();
		for (Book book : bookList) {
			if (book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}

	@Override
	public void removeOne(long id) {
		bookRepository.deleteById(id);
	}

}
