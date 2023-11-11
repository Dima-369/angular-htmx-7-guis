import {Component} from '@angular/core';
import env from '../../assets/env.json';

@Component({
  selector: 'app-crud-htmx',
  templateUrl: './crud-htmx.component.html'
})
export class CrudHtmxComponent {
  url: string = env.serverUrl + "crud"
}
