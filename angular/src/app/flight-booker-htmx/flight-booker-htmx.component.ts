import {Component} from '@angular/core';
import env from "../../assets/env.json";

@Component({
  selector: 'app-flight-booker-htmx',
  templateUrl: './flight-booker-htmx.component.html'
})
export class FlightBookerHtmxComponent {
  url: string = env.serverUrl + "flight-booker-update"
}
