import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Restorant from './restorant';
import RestorantDetail from './restorant-detail';
import RestorantUpdate from './restorant-update';
import RestorantDeleteDialog from './restorant-delete-dialog';

const RestorantRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Restorant />} />
    <Route path="new" element={<RestorantUpdate />} />
    <Route path=":id">
      <Route index element={<RestorantDetail />} />
      <Route path="edit" element={<RestorantUpdate />} />
      <Route path="delete" element={<RestorantDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RestorantRoutes;
