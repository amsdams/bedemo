import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VerwachteWaardePensioenBerekening } from '../model/models';
import { environment } from '../../environments/environment';

@Injectable({
	providedIn: 'root'
})
export class VerwachteWaardePensioenBerekeningService {


	private baseUrl: string;


	constructor(private http: HttpClient) {
		this.baseUrl = environment.baseUrl + '/verwachtewaardepensioenberekeningen';
	}

	public getAll(): Observable<VerwachteWaardePensioenBerekening[]> {
		return this.http.get<VerwachteWaardePensioenBerekening[]>(this.baseUrl);
	}

	public create(jaarlijksePremieStortingBerekening: VerwachteWaardePensioenBerekening) {
		return this.http.post<VerwachteWaardePensioenBerekening>(this.baseUrl, jaarlijksePremieStortingBerekening);
	}
}
