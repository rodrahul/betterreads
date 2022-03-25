package com.rahulrode.betterreadsloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahulrode.betterreadsloader.author.Author;
import com.rahulrode.betterreadsloader.author.AuthorService;
import com.rahulrode.betterreadsloader.book.Book;
import com.rahulrode.betterreadsloader.book.BookService;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class LoaderApplication {

  @ConfigProperty(name = "datadump.location.author")
  String authorDumpLocation;

  @ConfigProperty(name = "datadump.location.works")
  String worksDumpLocation;

  @Inject
  AuthorService authorService;
  @Inject
  BookService bookService;

  private static final Logger LOGGER = Logger.getLogger(LoaderApplication.class);
  private ObjectMapper om = new ObjectMapper();

  void onStart(@Observes StartupEvent event) {
    LOGGER.info("APPLICATION STARTED");
    LOGGER.info("Author DUMP location: " + authorDumpLocation);
    LOGGER.info("Works DUMP location: " + worksDumpLocation);
    // initAuthors();
    initWorks();
  }

  private void initAuthors() {
    Path path = Paths.get(authorDumpLocation);
    try (Stream<String> lines = Files.lines(path)) {
      // For each line
      // 1. Reach and parse the line
      // 2. Consturct the author object
      // 3. Persist author

      lines.forEach(l -> {
        String jsonString = l.substring(l.indexOf('{'));
        var jsonObject = new JsonObject(jsonString);
        Author author = new Author();
        author.setName(jsonObject.getString("name"));
        author.setPersonalName(jsonObject.getString("personal_name"));
        author.setId(jsonObject.getString("key").replace("/authors/", ""));

        authorService.create(author);
        System.out.println("Saving Author: " + author.getName());
      });
      System.out.println("Authors Saved");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void initWorks() {
    Path path = Paths.get(worksDumpLocation);
    try (Stream<String> lines = Files.lines(path)) {
      // For each line
      // 1. Reach and parse the line
      // 2. Consturct the Book object
      // 3. Persist book

      lines.forEach(l -> {
        try {
          String jsonString = l.substring(l.indexOf('{'));
          var jsonObject = new JsonObject(jsonString);
          Book book = new Book();
          book.setId(jsonObject.getString("key").replace("/works/", ""));
          book.setName(jsonObject.getString("title"));

          JsonObject dateObj = jsonObject.getJsonObject("created");
          if (dateObj != null) {
            var date = LocalDate.parse(dateObj.getString("value"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            book.setPublishedDate(date);
          }

          // Set coverids
          JsonArray coverJsonArr = jsonObject.getJsonArray("covers", null);
          List<String> coverIds = new ArrayList<>();
          for (int i = 0; coverJsonArr != null && i < coverJsonArr.size(); i++)
            coverIds.add(coverJsonArr.getString(i));

          book.setCoverids(coverIds);

          // Set author names
          JsonArray authorJsonArr = jsonObject.getJsonArray("authors", null);
          if (authorJsonArr != null) {
            List<String> authorNames = new ArrayList<>();
            List<String> authorIds = new ArrayList<>();
            for (int i = 0; i < authorJsonArr.size(); i++) {
              String authorId = authorJsonArr.getJsonObject(i)
                  .getJsonObject("author")
                  .getString("key")
                  .replace("/authors/", "");

              authorIds.add(authorId);
              var auth = authorService.findById(authorId);
              authorNames.add(auth != null ? auth.getName() : "Unknown Author");
            }

            book.setAuthorIds(authorIds);
            book.setAuthorNames(authorNames);
          }

          //
          JsonObject descObj = jsonObject.getJsonObject("description", null);
          if (descObj != null)
            book.setDescription(descObj.getString("value"));

          bookService.create(book);
          System.out.println("Saving Book: " + book.getName());
        } catch (Exception e) {
          System.out.println("Failed to create book " + l);
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
