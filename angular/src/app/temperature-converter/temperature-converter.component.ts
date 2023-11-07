import {Component} from '@angular/core';
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-temperature-converter',
  templateUrl: './temperature-converter.component.html',
  styleUrls: ['./temperature-converter.component.css']
})
export class TemperatureConverterComponent {
  celsius: number = 5;
  fahrenheit: number = 41;

  adjustCelsius($event: Event): void {
    const value: string = ($event.target as HTMLInputElement).value;

    if (AppComponent.isNumber(value)) {
      this.celsius = parseFloat(value);
      this.fahrenheit = this.round(this.celsius * (9 / 5) + 32);
    }
  }

  adjustFahrenheit($event: Event): void {
    const value: string = ($event.target as HTMLInputElement).value;
    if (AppComponent.isNumber(value)) {
      this.fahrenheit = parseFloat(value);
      this.celsius = this.round((this.fahrenheit - 32) * (5 / 9));
    }
  }

  round(num: number): number {
    return Math.round(num * 100) / 100;
  }
}
