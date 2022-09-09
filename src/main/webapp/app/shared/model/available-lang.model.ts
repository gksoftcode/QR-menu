import { IRestorant } from 'app/shared/model/restorant.model';

export interface IAvailableLang {
  code?: string | null;
  restorant?: IRestorant | null;
}

export const defaultValue: Readonly<IAvailableLang> = {};
