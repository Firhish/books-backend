package com.firhish.books;

import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> singleBook(ObjectId id){return bookRepository.findById(id);}

    public Book addBook(String title, String author, String genre, int year){
        return bookRepository.insert(new Book(title,author,genre,year));
    }

    public boolean deleteSingleBook(ObjectId id){
        Optional<Book> bookToDelete = bookRepository.findById(id);
        if(bookToDelete.isPresent()){
            bookRepository.delete(bookToDelete.get());
            return true;
        }else {
            return false;
        }
    }

    public Optional<Book> updateTitle(String newTitle, ObjectId id){
        Query query =  new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("title",newTitle);

        UpdateResult result = mongoTemplate.updateFirst(query,update,Book.class);

        if(result.getModifiedCount() > 0){
            return Optional.ofNullable(mongoTemplate.findById(id,Book.class));
        } else {
            return Optional.empty();
        }
    }

}
