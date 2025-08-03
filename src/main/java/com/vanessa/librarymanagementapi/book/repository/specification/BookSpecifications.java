package com.vanessa.librarymanagementapi.book.repository.specification;

import com.vanessa.librarymanagementapi.book.model.Book;
import com.vanessa.librarymanagementapi.book.model.BookGenre;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> isbnEqual(String isbn){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Book> authorNameLike(String authorName){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("author").get("name")), "%" + authorName.toUpperCase() + "%");
    }

    public static Specification<Book> genreEqual(BookGenre genre){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre"), genre);
    }

    public static Specification<Book> publicationYearEqual(Integer publicationYear){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.function("to_char", String.class, root.get("publicationDate"), criteriaBuilder.literal("YYYY")), publicationYear.toString());
    }
}
