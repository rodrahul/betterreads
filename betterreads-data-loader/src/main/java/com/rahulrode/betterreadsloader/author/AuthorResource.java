package com.rahulrode.betterreadsloader.author;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

  @Inject
  AuthorService authorService;

  @POST
  public void add(Author author) {
    authorService.create(author);
  }

  @GET
  @Path("/all")
  public List<Author> getAll() {
    return authorService.getAll();
  }

  @GET
  @Path("{id}")
  public Author get(@PathParam("id") String id) {
    return authorService.findById(id);
  }

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") String id) {
    authorService.delete(id);
  }

}
