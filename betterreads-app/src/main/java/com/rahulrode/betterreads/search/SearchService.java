package com.rahulrode.betterreads.search;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface SearchService {

  // http://openlibrary.org/search.json?q=the+lord+of+the+rings
  @GET
  @Path("/search.json")
  SearchResult search(@QueryParam("q") String search);

}
