import {Component} from '@angular/core';

@Component({
  selector: 'app-flight-booker',
  templateUrl: './flight-booker.component.html',
  styleUrls: ['./flight-booker.component.css']
})
export class FlightBookerComponent {

  type: string = "one-way"
  from: string = "27.03.2014";
  to: string = "27.03.2014";
  fromValid: boolean = true
  toValid: boolean = true

  private dateRegexp: RegExp = /^\d{2}\.\d{2}\.\d{4}$/

  book(): void {
    if (this.type === "one-way") {
      alert("You have booked a one-way flight on " + this.from)
    } else {
      alert("You have booked a return flight from " + this.from + " to " + this.to)
    }
  }

  isDateValid(date: string): boolean {
    return this.dateRegexp.test(date)
  }

  isFromValid(): Object {
    return {
      'bg-red-400': !this.fromValid
    }
  }

  isToValid(): Object {
    return {
      'bg-red-400': !this.toValid
    }
  }

  onToChange($event: Event): void {
    this.to = ($event.target as HTMLInputElement).value;
    this.toValid = this.isDateValid(this.to)
  }

  onFromChange($event: Event): void {
    this.from = ($event.target as HTMLInputElement).value;
    this.fromValid = this.isDateValid(this.from)
  }

  doDatesMatch(): boolean {
    // convert from 27.03.2014 to 2014-03-27
    const fromDate: Date = new Date(this.from.split('.').reverse().join('-'));
    const toDate: Date = new Date(this.to.split('.').reverse().join('-'));
    return toDate.getTime() >= fromDate.getTime()
  }

  bookDisabled(): boolean {
    if (this.type === "one-way") {
      return !this.fromValid;
    }
    // return flight
    return !(this.toValid && this.doDatesMatch())
  }
}
