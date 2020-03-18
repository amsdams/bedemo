import { Component } from '@angular/core';
import { VerwachteWaardePensioenBerekeningService } from '../api/verwachte-waarde-pensioen-berekening.service';
import { VerwachteWaardePensioenBerekening } from '../model/models';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
	selector: 'app-verwachte-waarde-pensioen-berekening-form',
	templateUrl: './verwachte-waarde-pensioen-berekening-form.component.html',
	styleUrls: ['./verwachte-waarde-pensioen-berekening-form.component.css']
})
export class VerwachteWaardePensioenBerekeningFormComponent {

	verwachteWaardePensioenBerekening: VerwachteWaardePensioenBerekening;

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private verwachteWaardePensioenBerekeningService: VerwachteWaardePensioenBerekeningService) {
		this.verwachteWaardePensioenBerekening = new VerwachteWaardePensioenBerekening();
	}

	onSubmit() {
		this.verwachteWaardePensioenBerekeningService.create(this.verwachteWaardePensioenBerekening).subscribe(result => this.gotoList());
	}

	gotoList() {
		this.router.navigate(['/toon-alle-verwachtewaardepensioenberekeningen']);
	}

}
