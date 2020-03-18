import { Component, OnInit } from '@angular/core';
import { JaarlijksePremieStortingBerekening } from '../model/models';
import { JaarlijksePremieStortingBerekeningService } from '../api/jaarlijkse-premie-storting-berekening.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
	selector: 'app-jaarlijkse-premie-storting-berekening-form',
	templateUrl: './jaarlijkse-premie-storting-berekening-form.component.html',
	styleUrls: ['./jaarlijkse-premie-storting-berekening-form.component.css']
})
export class JaarlijksePremieStortingBerekeningFormComponent {

	jaarlijksePremieStortingBerekening: JaarlijksePremieStortingBerekening;

	constructor(
		private route: ActivatedRoute,
		private router: Router,
		private jaarlijksePremieStortingBerekeningService: JaarlijksePremieStortingBerekeningService) {
		this.jaarlijksePremieStortingBerekening = new JaarlijksePremieStortingBerekening();
	}

	onSubmit() {
		this.jaarlijksePremieStortingBerekeningService.create(this.jaarlijksePremieStortingBerekening).subscribe(result => this.gotoUserList());
	}

	gotoUserList() {
		this.router.navigate(['/toon-alle-jaarlijksepremiestortingberekeningen']);
	}

}
