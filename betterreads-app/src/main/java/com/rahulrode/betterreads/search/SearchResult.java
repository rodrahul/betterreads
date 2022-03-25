package com.rahulrode.betterreads.search;

import java.util.List;

public class SearchResult {

  private List<SearchResultBook> docs;

  public List<SearchResultBook> getDocs() {
    return this.docs;
  }

  public void setDocs(List<SearchResultBook> docs) {
    this.docs = docs;
  }

}
