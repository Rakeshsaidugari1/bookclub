package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Configuration;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Repository
public class RestBookDao implements BookDao {

    public Object getBooksDoc(String isbnString) {
        String openLibraryUrl = "https://openlibrary.org/api/books";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        URI uri = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "data")
                .build()
                .toUri();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, entity, String.class);

        String jsonBooklist = response.getBody();
        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBooklist);
    }

    @Override
    public List<Book> list() {
        String[] isbnKeys = {"ISBN:0451526538", "ISBN:9780140449136", "ISBN:9780141182803"};
        String isbnParam = String.join(",", isbnKeys);

        Object jsonResponse = getBooksDoc(isbnParam);
        List<Book> books = new ArrayList<>();

        for (String isbnKey : isbnKeys) {
            try {
                Map<String, Object> bookData = JsonPath.read(jsonResponse, "$['" + isbnKey + "']");

                String isbn = isbnKey.replace("ISBN:", "");
                String title = bookData.getOrDefault("title", "N/A").toString();
                String infoUrl = bookData.getOrDefault("url", "#").toString();
                int pages = (bookData.containsKey("number_of_pages"))
                        ? (Integer) bookData.get("number_of_pages")
                        : 0;

                String description = "No description available.";
                if (bookData.containsKey("description")) {
                    Object desc = bookData.get("description");
                    if (desc instanceof String) {
                        description = desc.toString();
                    } else if (desc instanceof Map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> descMap = (Map<String, Object>) desc;
                        description = descMap.getOrDefault("value", description).toString();
                    }
                }

                Book book = new Book();
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setInfoUrl(infoUrl);
                book.setNumOfPages(pages);
                book.setDescription(description);

                books.add(book);
            } catch (Exception e) {
                System.err.println("Error parsing book " + isbnKey + ": " + e.getMessage());
            }
        }

        return books;
    }

    @Override
    public Book find(String isbn) {
        String isbnKey = "ISBN:" + isbn;

        Object jsonResponse = getBooksDoc(isbnKey);

        try {
            Map<String, Object> bookData = JsonPath.read(jsonResponse, "$['" + isbnKey + "']");

            String title = bookData.getOrDefault("title", "N/A").toString();
            String infoUrl = bookData.getOrDefault("url", "#").toString();
            int pages = (bookData.containsKey("number_of_pages"))
                    ? (Integer) bookData.get("number_of_pages")
                    : 0;

            String description = "No description available.";
            if (bookData.containsKey("description")) {
                Object desc = bookData.get("description");
                if (desc instanceof String) {
                    description = desc.toString();
                } else if (desc instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> descMap = (Map<String, Object>) desc;
                    description = descMap.getOrDefault("value", description).toString();
                }
            }

            Book book = new Book();
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setInfoUrl(infoUrl);
            book.setNumOfPages(pages);
            book.setDescription(description);

            return book;
        } catch (Exception e) {
            System.err.println("Error fetching book for ISBN " + isbn + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Book book) {
        throw new UnsupportedOperationException("Save operation not supported in RestBookDao");
    }

    @Override
    public void update(Book book) {
        throw new UnsupportedOperationException("Update operation not supported in RestBookDao");
    }

    @Override
    public void delete(String isbn) {
        throw new UnsupportedOperationException("Delete operation not supported in RestBookDao");
    }
}
