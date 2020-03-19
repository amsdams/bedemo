import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { Werknemer } from '../model/werknemer';

@Injectable({
	providedIn: 'root'
})
export class WerknemersService {


	private baseUrl: string;

	constructor(private http: HttpClient) {
		this.baseUrl = environment.baseUrl + '/werknemers';
	}

	public getAll(): Observable<Werknemer[]> {
		return this.http.get<Werknemer[]>(this.baseUrl);
    }

    public findByWerkgever(id: number): Observable<Werknemer[]> {
		return this.http.get<Werknemer[]>(this.baseUrl + '/search/findByWerkgever/' + id);
    }





}
