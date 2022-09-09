import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ClientAccount from './client-account';
import Restorant from './restorant';
import Country from './country';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="client-account/*" element={<ClientAccount />} />
        <Route path="restorant/*" element={<Restorant />} />
        <Route path="country/*" element={<Country />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
