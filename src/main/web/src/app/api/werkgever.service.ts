import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { Werkgever } from '../model/werkgever';

@Injectable({
	providedIn: 'root'
})
export class WerkgeversService {


	private baseUrl: string;

	constructor(private http: HttpClient) {
		this.baseUrl = environment.baseUrl + '/werkgevers';
	}

	public getAll(): Observable<Werkgever[]> {
		return this.http.get<Werkgever[]>(this.baseUrl);
	}


}
