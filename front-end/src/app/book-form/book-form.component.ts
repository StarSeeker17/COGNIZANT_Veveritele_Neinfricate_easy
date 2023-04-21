import { Component, EventEmitter, Output } from '@angular/core';
import { Book } from '../books';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent {
  @Output() bookAdded = new EventEmitter<Book>();
  title: string;
  author: string;

  onSubmit() {
    const book = new Book(this.title, this.author);
    this.bookAdded.emit(book);
    this.title = '';
    this.author = '';
  }
}
