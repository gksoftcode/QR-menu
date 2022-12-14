import { IRestorantName } from 'app/shared/model/restorant-name.model';
import { IAvailableLang } from 'app/shared/model/available-lang.model';
import { ICountry } from 'app/shared/model/country.model';
import { IClientAccount } from 'app/shared/model/client-account.model';

export interface IRestorant {
  id?: string;
  lat?: string | null;
  lang?: string | null;
  isActive?: boolean | null;
  isDeleted?: boolean | null;
  logoUrl?: string | null;
  fbUrl?: string | null;
  instaUrl?: string | null;
  twitterUrl?: string | null;
  youtubeUrl?: string | null;
  googleUrl?: string | null;
  restorantNames?: IRestorantName[] | null;
  availableLangs?: IAvailableLang[] | null;
  country?: ICountry | null;
  account?: IClientAccount | null;
}

export const defaultValue: Readonly<IRestorant> = {
  isActive: false,
  isDeleted: false,
};
