import {Component} from '@angular/core';

@Component({
  selector: 'app-counter',
  templateUrl: './counter.component.html',
  styleUrls: ['./counter.component.css']
})
export class CounterComponent {

  protected count: number = 0

  increaseCount() {
    this.count++;
  }

  getCount() {
    return this.count;
  }
}
