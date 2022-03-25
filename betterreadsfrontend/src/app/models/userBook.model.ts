// export interface UserBook {
//   bookId: string;
//   readingStatus: string;
//   startedDate: Date;
//   completedDate: Date;
//   rating: number;
// }

export interface UserBook {
  id: string;
  bookId: string;
  timeUuid: string;
  readingStatus: string;
  startedDate: Date;
  completedDate: Date;
  bookName: string;
  authorNames: string[];
  coverIds: string[];
  rating: number;
  coverUrl: string;
}
