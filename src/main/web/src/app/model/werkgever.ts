import { Regeling } from './regeling';
import { Werknemer } from './werknemer';



export interface Werkgever {
    id?: number;
    naam?: string;
    regelings?: Array<Regeling>;
    werknemers?: Array<Werknemer>;
}
