import { Component, OnInit } from '@angular/core';
import { VerwachteWaardePensioenBerekeningService } from '../api/verwachte-waarde-pensioen-berekening.service';
import { VerwachteWaardePensioenBerekening } from '../model/models';
import { Router, ActivatedRoute } from '@angular/router';
import { WerkgeversService } from '../api/werkgever.service';
import { RegelingenService } from '../api/regeling.service';
import { WerknemersService } from '../api/werknemer.service';
import { Werkgever } from '../model/werkgever';
import { Werknemer } from '../model/werknemer';
import { Regeling } from '../model/regeling';

@Component({
	selector: 'app-verwachte-waarde-pensioen-berekening-form',
	templateUrl: './verwachte-waarde-pensioen-berekening-form.component.html',
	styleUrls: ['./verwachte-waarde-pensioen-berekening-form.component.css']
})
export class VerwachteWaardePensioenBerekeningFormComponent implements OnInit {

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private verwachteWaardePensioenBerekeningService: VerwachteWaardePensioenBerekeningService,
		private werkgeversService: WerkgeversService,
		private regelingenService: RegelingenService,
		private werknemersService: WerknemersService, ) {
		this.verwachteWaardePensioenBerekening = new VerwachteWaardePensioenBerekening();
	}

	verwachteWaardePensioenBerekening: VerwachteWaardePensioenBerekening;

	werkgevers: Werkgever[];
	regelingen: Regeling[];
	werknemers: Werknemer[];
	selectedWerkgever: Werkgever;
	selectedRegeling: Regeling;
	selectedWerknemer: Werknemer;

	ngOnInit(): void {
		this.werkgeversService.getAll().subscribe(data => {
			this.werkgevers = data;
			console.log(this.werkgevers);

			// asuming we have 0
			// this.selectedWerkgever = this.werkgevers[0];
   			// this.onChangeSelectedWerkgever(this.selectedWerkgever );

		});


	}

	onSubmit() {
		this.verwachteWaardePensioenBerekeningService.create(this.verwachteWaardePensioenBerekening).subscribe(result => this.gotoList());
	}

	gotoList() {
		this.router.navigate(['/toon-alle-verwachtewaardepensioenberekeningen']);
	}

	onChangeSelectedWerkgever(newValue: Werkgever) {
		if (!newValue) {
			return;
		}

		this.selectedWerkgever = newValue;  // don't forget to update the model here

		this.regelingenService.findByWerkgever(this.selectedWerkgever.id).subscribe(data => {
			this.regelingen = data;
			console.log(this.regelingen);

			// asuming we have 0
			if (this.regelingen.length > 0) {
				this.selectedRegeling = this.regelingen[0];
			}
		});

		this.werknemersService.findByWerkgever(this.selectedWerkgever.id).subscribe(data => {
			this.werknemers = data;
			console.log(this.werknemers);

			// asuming we have 0
			if (this.werknemers.length > 0) {
				this.selectedWerknemer = this.werknemers[0];
			}
		});



	}
	onChangeSelectedRegeling(newValue: Regeling) {
		console.log(newValue);
		// this.verwachteWaardePensioenBerekening.ol = newValue.voltijdFranchise;
		this.verwachteWaardePensioenBerekening.jaarlijksRendementBeleggingen = newValue.jaarlijksRendementBeleggingen;


	}

	onChangeSelectedWerknemer(newValue: Werknemer) {
		console.log(newValue);
		const now = Date.now();
		const past = new Date(newValue.geboorteDatum);


		const diff =  new Date(now).getFullYear() - past.getFullYear();
		console.log(diff);

		this.verwachteWaardePensioenBerekening.huidigeLeeftijdDeelnemer = diff;
		// this.verwachteWaardePensioenBerekening.voltijdSalaris = newValue.voltijdSalaris;


	}

}
