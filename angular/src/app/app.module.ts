import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {CounterComponent} from './counter/counter.component';
import {CounterHtmxComponent} from './counter-htmx/counter-htmx.component';
import {InfoComponent} from './info/info.component';
import {TemperatureConverterComponent} from './temperature-converter/temperature-converter.component';
import {TemperatureConverterHtmxComponent} from './temperature-converter-htmx/temperature-converter-htmx.component';
import {FlightBookerComponent} from './flight-booker/flight-booker.component';
import {FormsModule} from "@angular/forms";
import { FlightBookerHtmxComponent } from './flight-booker-htmx/flight-booker-htmx.component';

@NgModule({
  declarations: [
    AppComponent,
    CounterComponent,
    CounterHtmxComponent,
    InfoComponent,
    TemperatureConverterComponent,
    TemperatureConverterHtmxComponent,
    FlightBookerComponent,
    FlightBookerHtmxComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}