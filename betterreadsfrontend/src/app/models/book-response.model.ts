import { Book } from './book.model';
import { UserBook } from './userBook.model';

export interface BookResponse {
  book: Book;
  userBooks: UserBook;
}
