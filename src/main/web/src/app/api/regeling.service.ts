import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { Regeling } from '../model/regeling';

@Injectable({
	providedIn: 'root'
})
export class RegelingenService {


	private baseUrl: string;

	constructor(private http: HttpClient) {
		this.baseUrl = environment.baseUrl + '/regelingen';
	}

	public getAll(): Observable<Regeling[]> {
		return this.http.get<Regeling[]>(this.baseUrl);
	}
	public findByWerkgever(id: number): Observable<Regeling[]> {
		return this.http.get<Regeling[]>(this.baseUrl + '/search/findByWerkgever/' + id);
	}

}
