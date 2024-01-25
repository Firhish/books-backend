package com.firhish.books;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<List<Book>>(bookService.allBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getSingleBook(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Book>>(bookService.singleBook(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Map<String,String> payload){
        return new ResponseEntity<Book>(bookService.addBook(payload.get("title"),payload.get("author"),payload.get("genre"), Integer.parseInt(payload.get("year"))),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSingleBook(@PathVariable ObjectId id){
        boolean deleted = bookService.deleteSingleBook(id);
        if(deleted){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Book>> updateTitle(@RequestBody Map<String,String> payload,@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Book>>(bookService.updateTitle(payload.get("title"),id),HttpStatus.OK);
    }

}
