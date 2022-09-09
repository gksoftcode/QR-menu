import { IRestorant } from 'app/shared/model/restorant.model';

export interface ICountry {
  id?: string;
  countryName?: string | null;
  restorants?: IRestorant[] | null;
}

export const defaultValue: Readonly<ICountry> = {};
