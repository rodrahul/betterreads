import { Injectable } from '@angular/core';
import { Book } from '../models/book.model';

function isValid(obj): boolean {
  return obj !== null && obj !== undefined;
}

@Injectable({
  providedIn: 'root',
})
export class PhotoService {
  readonly COVER_URL = 'https://covers.openlibrary.org/b/id/';
  readonly AUTHOR_PHOTO_URL = 'https://covers.openlibrary.org/a/olid/';

  getCoverUrl(book: Book): string {
    if (isValid(book.coverIds) && book.coverIds.length > 0)
      return this.COVER_URL + book.coverIds[0] + '-M.jpg';

    return '';
  }

  getAuthorPhoto(authorId: string): string {
    return this.AUTHOR_PHOTO_URL + authorId + '-S.jpg';
  }
}
