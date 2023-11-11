import {Component} from '@angular/core';
import env from "../../assets/env.json";

@Component({
  selector: 'app-temperature-converter-htmx',
  templateUrl: './temperature-converter-htmx.component.html'
})
export class TemperatureConverterHtmxComponent {
  url: string = env.serverUrl + "temperature-converter-init"
}
