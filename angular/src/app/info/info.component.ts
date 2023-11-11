import { Component } from '@angular/core';
import env from "../../assets/env.json";

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent {
  url: string = env.serverUrl + "info"
}
