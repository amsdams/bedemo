import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VerwachteWaardePensioenBerekeningListComponent } from './verwachte-waarde-pensioen-berekening-list/verwachte-waarde-pensioen-berekening-list.component';
import { JaarlijksePremieStortingBerekeningListComponent } from './jaarlijkse-premie-storting-berekening-list/jaarlijkse-premie-storting-berekening-list.component';
import { JaarlijksePremieStortingBerekeningFormComponent } from './jaarlijkse-premie-storting-berekening-form/jaarlijkse-premie-storting-berekening-form.component';
import { VerwachteWaardePensioenBerekeningFormComponent } from './verwachte-waarde-pensioen-berekening-form/verwachte-waarde-pensioen-berekening-form.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    VerwachteWaardePensioenBerekeningListComponent,
    JaarlijksePremieStortingBerekeningListComponent,
    JaarlijksePremieStortingBerekeningFormComponent,
    VerwachteWaardePensioenBerekeningFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
