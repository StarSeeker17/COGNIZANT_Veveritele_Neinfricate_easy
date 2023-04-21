import { Component } from '@angular/core';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent {
books: Book[] = [];
onBookAdded(book: Book) {
    this.books.push(book);
  }
}