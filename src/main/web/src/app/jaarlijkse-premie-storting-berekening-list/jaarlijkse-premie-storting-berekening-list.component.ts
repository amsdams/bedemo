import { Component, OnInit } from '@angular/core';
import { JaarlijksePremieStortingBerekeningService } from '../api/jaarlijkse-premie-storting-berekening.service';
import { JaarlijksePremieStortingBerekening } from '../model/models';

@Component({
	selector: 'app-jaarlijkse-premie-storting-berekening-list',
	templateUrl: './jaarlijkse-premie-storting-berekening-list.component.html',
	styleUrls: ['./jaarlijkse-premie-storting-berekening-list.component.css']
})
export class JaarlijksePremieStortingBerekeningListComponent implements OnInit {

	constructor(private jaarlijksePremieStortingBerekeningService: JaarlijksePremieStortingBerekeningService) { }

	jaarlijksePremieStortingBerekeningen: JaarlijksePremieStortingBerekening[];
	ngOnInit() {
		this.jaarlijksePremieStortingBerekeningService.getAll().subscribe(data => {
			this.jaarlijksePremieStortingBerekeningen = data;
		});
	}

}
