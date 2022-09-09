import { IRestorant } from 'app/shared/model/restorant.model';

export interface IRestorantName {
  langCode?: string | null;
  name?: string | null;
  restorant?: IRestorant | null;
}

export const defaultValue: Readonly<IRestorantName> = {};
