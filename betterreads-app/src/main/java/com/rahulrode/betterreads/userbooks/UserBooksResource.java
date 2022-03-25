package com.rahulrode.betterreads.userbooks;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.security.identity.SecurityIdentity;

@Path("/userbooks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserBooksResource {

  @Inject
  UserBooksService userBooksService;

  @Inject
  SecurityIdentity securityIdentity;

  @POST()
  @Path("save")
  public void addBookForUser(UserBooksDTO userBooksDTO) {
    if (securityIdentity.isAnonymous())
      return;

    userBooksService.addBook(userBooksDTO, securityIdentity.getPrincipal().getName());
  }

}
