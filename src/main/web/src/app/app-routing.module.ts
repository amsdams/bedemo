import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VerwachteWaardePensioenBerekeningListComponent } from './verwachte-waarde-pensioen-berekening-list/verwachte-waarde-pensioen-berekening-list.component';
import { VerwachteWaardePensioenBerekeningFormComponent } from './verwachte-waarde-pensioen-berekening-form/verwachte-waarde-pensioen-berekening-form.component';
import { JaarlijksePremieStortingBerekeningListComponent } from './jaarlijkse-premie-storting-berekening-list/jaarlijkse-premie-storting-berekening-list.component';
import { JaarlijksePremieStortingBerekeningFormComponent } from './jaarlijkse-premie-storting-berekening-form/jaarlijkse-premie-storting-berekening-form.component';


const routes: Routes = [
	{ path: 'toon-alle-verwachtewaardepensioenberekeningen', component: VerwachteWaardePensioenBerekeningListComponent },
	{ path: 'maak-verwachtewaardepensioenberekening', component: VerwachteWaardePensioenBerekeningFormComponent },
	{ path: 'toon-alle-jaarlijksepremiestortingberekeningen', component: JaarlijksePremieStortingBerekeningListComponent },
	{ path: 'maak-jaarlijksepremiestortingberekening', component: JaarlijksePremieStortingBerekeningFormComponent }
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
