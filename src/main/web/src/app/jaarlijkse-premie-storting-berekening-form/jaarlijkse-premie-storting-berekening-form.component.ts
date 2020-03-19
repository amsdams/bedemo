import { Component, OnInit } from '@angular/core';
import { JaarlijksePremieStortingBerekening } from '../model/models';
import { JaarlijksePremieStortingBerekeningService } from '../api/jaarlijkse-premie-storting-berekening.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Werkgever } from '../model/werkgever';
import { WerkgeversService } from '../api/werkgever.service';
import { RegelingenService } from '../api/regeling.service';
import { WerknemersService } from '../api/werknemer.service';
import { Werknemer } from '../model/werknemer';
import { Regeling } from '../model/regeling';

@Component({
	selector: 'app-jaarlijkse-premie-storting-berekening-form',
	templateUrl: './jaarlijkse-premie-storting-berekening-form.component.html',
	styleUrls: ['./jaarlijkse-premie-storting-berekening-form.component.css']
})
export class JaarlijksePremieStortingBerekeningFormComponent implements OnInit {

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private jaarlijksePremieStortingBerekeningService: JaarlijksePremieStortingBerekeningService,
		private werkgeversService: WerkgeversService,
		private regelingenService: RegelingenService,
		private werknemersService: WerknemersService, ) {
		this.jaarlijksePremieStortingBerekening = new JaarlijksePremieStortingBerekening();
	}

	jaarlijksePremieStortingBerekening: JaarlijksePremieStortingBerekening;
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
			// this.onChangeSelectedWerkgever(this.selectedWerkgever);
		});


	}

	onSubmit() {
		this.jaarlijksePremieStortingBerekeningService.create(this.jaarlijksePremieStortingBerekening).subscribe(result => this.gotoUserList());
	}

	gotoUserList() {
		this.router.navigate(['/toon-alle-jaarlijksepremiestortingberekeningen']);
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
		this.jaarlijksePremieStortingBerekening.premiePercentage = newValue.premiePercentage;
		this.jaarlijksePremieStortingBerekening.voltijdFranchise = newValue.voltijdFranchise;


	}

	onChangeSelectedWerknemer(newValue: Werknemer) {
		console.log(newValue);
		this.jaarlijksePremieStortingBerekening.deeltijdPercentage = newValue.deeltijdPercentage;
		this.jaarlijksePremieStortingBerekening.voltijdSalaris = newValue.voltijdSalaris;
		/*const now = Date.now();
		const past = new Date(newValue.geboorteDatum);


		let diff =  new Date(now).getFullYear() - past.getFullYear();
		console.log(diff);*/

	}

}
