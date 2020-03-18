import { Component, OnInit } from '@angular/core';
import { VerwachteWaardePensioenBerekening } from '../model/models';
import { VerwachteWaardePensioenBerekeningService } from '../api/verwachte-waarde-pensioen-berekening.service';

@Component({
	selector: 'app-verwachte-waarde-pensioen-berekening-list',
	templateUrl: './verwachte-waarde-pensioen-berekening-list.component.html',
	styleUrls: ['./verwachte-waarde-pensioen-berekening-list.component.css']
})
export class VerwachteWaardePensioenBerekeningListComponent implements OnInit {

	constructor(private verwachteWaardePensioenBerekeningService: VerwachteWaardePensioenBerekeningService) { }

	verwachteWaardePensioenBerekeningen: VerwachteWaardePensioenBerekening[];
	ngOnInit() {
		this.verwachteWaardePensioenBerekeningService.getAll().subscribe(data => {
			this.verwachteWaardePensioenBerekeningen = data;
		});
	}

}
