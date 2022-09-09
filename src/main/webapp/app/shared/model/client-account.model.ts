import { IRestorant } from 'app/shared/model/restorant.model';

export interface IClientAccount {
  id?: string;
  fullName?: string | null;
  mainAddress?: string | null;
  logoUrl?: string | null;
  isDeleted?: boolean | null;
  restorants?: IRestorant[] | null;
}

export const defaultValue: Readonly<IClientAccount> = {
  isDeleted: false,
};
