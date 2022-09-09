import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './restorant.reducer';

export const RestorantDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const restorantEntity = useAppSelector(state => state.restorant.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="restorantDetailsHeading">
          <Translate contentKey="qrMenuApp.restorant.detail.title">Restorant</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.id}</dd>
          <dt>
            <span id="lat">
              <Translate contentKey="qrMenuApp.restorant.lat">Lat</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.lat}</dd>
          <dt>
            <span id="lang">
              <Translate contentKey="qrMenuApp.restorant.lang">Lang</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.lang}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="qrMenuApp.restorant.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDeleted">
              <Translate contentKey="qrMenuApp.restorant.isDeleted">Is Deleted</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.isDeleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="logoUrl">
              <Translate contentKey="qrMenuApp.restorant.logoUrl">Logo Url</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.logoUrl}</dd>
          <dt>
            <span id="fbUrl">
              <Translate contentKey="qrMenuApp.restorant.fbUrl">Fb Url</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.fbUrl}</dd>
          <dt>
            <span id="instaUrl">
              <Translate contentKey="qrMenuApp.restorant.instaUrl">Insta Url</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.instaUrl}</dd>
          <dt>
            <span id="twitterUrl">
              <Translate contentKey="qrMenuApp.restorant.twitterUrl">Twitter Url</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.twitterUrl}</dd>
          <dt>
            <span id="youtubeUrl">
              <Translate contentKey="qrMenuApp.restorant.youtubeUrl">Youtube Url</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.youtubeUrl}</dd>
          <dt>
            <span id="googleUrl">
              <Translate contentKey="qrMenuApp.restorant.googleUrl">Google Url</Translate>
            </span>
          </dt>
          <dd>{restorantEntity.googleUrl}</dd>
          <dt>
            <Translate contentKey="qrMenuApp.restorant.country">Country</Translate>
          </dt>
          <dd>{restorantEntity.country ? restorantEntity.country.countryName : ''}</dd>
          <dt>
            <Translate contentKey="qrMenuApp.restorant.account">Account</Translate>
          </dt>
          <dd>{restorantEntity.account ? restorantEntity.account.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/restorant" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/restorant/${restorantEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RestorantDetail;
