import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JaarlijksePremieStortingBerekening } from '../model/jaarlijkse-premie-storting-berekening';
import { environment } from '../../environments/environment';
@Injectable({
	providedIn: 'root'
})
export class JaarlijksePremieStortingBerekeningService {

	private baseUrl: string;

	constructor(private http: HttpClient) {
		this.baseUrl = environment.baseUrl + '/jaarlijksepremiestortingberekeningen';
	}

	public getAll(): Observable<JaarlijksePremieStortingBerekening[]> {
		return this.http.get<JaarlijksePremieStortingBerekening[]>(this.baseUrl);
	}

	public create(jaarlijksePremieStortingBerekening: JaarlijksePremieStortingBerekening) {
		return this.http.post<JaarlijksePremieStortingBerekening>(this.baseUrl, jaarlijksePremieStortingBerekening);
	}
}
