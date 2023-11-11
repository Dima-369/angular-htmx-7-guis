import {Component} from '@angular/core';
import env from "../../assets/env.json";

@Component({
  selector: 'app-counter-htmx',
  templateUrl: './counter-htmx.component.html'
})
export class CounterHtmxComponent {
  url: string = env.serverUrl + "counter"
}
