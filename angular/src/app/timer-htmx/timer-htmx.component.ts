import {Component} from '@angular/core';
import env from "../../assets/env.json";

@Component({
  selector: 'app-timer-htmx',
  templateUrl: './timer-htmx.component.html'
})
export class TimerHtmxComponent {
  url: string = env.serverUrl + "timer-init"
}
