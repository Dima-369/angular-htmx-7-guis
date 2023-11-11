import {Component} from '@angular/core';
import env from "../../assets/env.json";

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html'
})
export class InfoComponent {
  url: string = env.serverUrl + "info"
}
