import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

// match strings containing only digits, dots and commas
  static isNumber(str: string): boolean {
    if (str === "") {
      return false;
    }

    const regex: RegExp = /^-?[0-9.,]*$/;
    return regex.test(str);
  }
}
