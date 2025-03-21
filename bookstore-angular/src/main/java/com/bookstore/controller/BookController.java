package com.bookstore.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bookstore.models.Book;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/add")
	public Book addBook(@RequestBody Book book) {
		return bookService.save(book);
	}

	@PostMapping("/add/image")
	public ResponseEntity upload(@RequestParam long id, HttpServletRequest request, HttpServletResponse response) {
		try {
			Optional<Book> book = bookService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id + ".png";

			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + fileName)));
			stream.write(bytes);
			stream.close();

			return new ResponseEntity("Upload Success!", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/bookList")
	public List<Book> getBookList() {
		return bookService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Book> getBook(@PathVariable long id) {
		Optional<Book> book = bookService.findOne(id);
		return book;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteBookById(@PathVariable long id) throws IOException {

		bookService.removeOne(id);
		String fileName = id + ".png";

		// Delete Existing Image file
		Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));
		return new ResponseEntity("Remove Success!", HttpStatus.OK);
	}

	@PostMapping("/update")
	public Book updateBook(@RequestBody Book book) {
		return bookService.save(book);
	}

	@PostMapping("/update/image")
	public ResponseEntity updateImage(@RequestParam long id, HttpServletRequest request, HttpServletResponse response) {
		try {
			Optional<Book> book = bookService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id + ".png";

			// Delete Existing Image file
			Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));

			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + fileName)));
			stream.write(bytes);
			stream.close();

			return new ResponseEntity("Upload Image Success!", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload Image failed!", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/searchBook")
	public List<Book> searchBook(@RequestBody String keyword) {
		List<Book> bookList = bookService.blurrySearch(keyword);

		return bookList;
	}

}
