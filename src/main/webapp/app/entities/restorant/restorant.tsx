import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRestorant } from 'app/shared/model/restorant.model';
import { getEntities } from './restorant.reducer';

export const Restorant = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const restorantList = useAppSelector(state => state.restorant.entities);
  const loading = useAppSelector(state => state.restorant.loading);
  const totalItems = useAppSelector(state => state.restorant.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (location.search !== endURL) {
      navigate(`${location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  return (
    <div>
      <h2 id="restorant-heading" data-cy="RestorantHeading">
        <Translate contentKey="qrMenuApp.restorant.home.title">Restorants</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="qrMenuApp.restorant.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/restorant/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="qrMenuApp.restorant.home.createLabel">Create new Restorant</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {restorantList && restorantList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="qrMenuApp.restorant.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lat')}>
                  <Translate contentKey="qrMenuApp.restorant.lat">Lat</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lang')}>
                  <Translate contentKey="qrMenuApp.restorant.lang">Lang</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="qrMenuApp.restorant.isActive">Is Active</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('isDeleted')}>
                  <Translate contentKey="qrMenuApp.restorant.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('logoUrl')}>
                  <Translate contentKey="qrMenuApp.restorant.logoUrl">Logo Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fbUrl')}>
                  <Translate contentKey="qrMenuApp.restorant.fbUrl">Fb Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('instaUrl')}>
                  <Translate contentKey="qrMenuApp.restorant.instaUrl">Insta Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('twitterUrl')}>
                  <Translate contentKey="qrMenuApp.restorant.twitterUrl">Twitter Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('youtubeUrl')}>
                  <Translate contentKey="qrMenuApp.restorant.youtubeUrl">Youtube Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('googleUrl')}>
                  <Translate contentKey="qrMenuApp.restorant.googleUrl">Google Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="qrMenuApp.restorant.country">Country</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="qrMenuApp.restorant.account">Account</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {restorantList.map((restorant, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/restorant/${restorant.id}`} color="link" size="sm">
                      {restorant.id}
                    </Button>
                  </td>
                  <td>{restorant.lat}</td>
                  <td>{restorant.lang}</td>
                  <td>{restorant.isActive ? 'true' : 'false'}</td>
                  <td>{restorant.isDeleted ? 'true' : 'false'}</td>
                  <td>{restorant.logoUrl}</td>
                  <td>{restorant.fbUrl}</td>
                  <td>{restorant.instaUrl}</td>
                  <td>{restorant.twitterUrl}</td>
                  <td>{restorant.youtubeUrl}</td>
                  <td>{restorant.googleUrl}</td>
                  <td>{restorant.country ? <Link to={`/country/${restorant.country.id}`}>{restorant.country.countryName}</Link> : ''}</td>
                  <td>{restorant.account ? <Link to={`/client-account/${restorant.account.id}`}>{restorant.account.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/restorant/${restorant.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/restorant/${restorant.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/restorant/${restorant.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="qrMenuApp.restorant.home.notFound">No Restorants found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={restorantList && restorantList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Restorant;
