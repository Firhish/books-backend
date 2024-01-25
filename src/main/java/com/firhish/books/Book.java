package com.firhish.books;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year =year;
    }

    // all field names in this model must be the same with the data base
    @Id
    @JsonSerialize(using = ToStringSerializer.class) //change from ObjectId to String
    private ObjectId _id;
    private String title;
    private String author;
    private String genre;
    private int year;



    public void setTitle(String title) {
        this.title = title;
    }
}
