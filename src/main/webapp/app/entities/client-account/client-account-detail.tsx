import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './client-account.reducer';

export const ClientAccountDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const clientAccountEntity = useAppSelector(state => state.clientAccount.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="clientAccountDetailsHeading">
          <Translate contentKey="qrMenuApp.clientAccount.detail.title">ClientAccount</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{clientAccountEntity.id}</dd>
          <dt>
            <span id="fullName">
              <Translate contentKey="qrMenuApp.clientAccount.fullName">Full Name</Translate>
            </span>
          </dt>
          <dd>{clientAccountEntity.fullName}</dd>
          <dt>
            <span id="mainAddress">
              <Translate contentKey="qrMenuApp.clientAccount.mainAddress">Main Address</Translate>
            </span>
          </dt>
          <dd>{clientAccountEntity.mainAddress}</dd>
          <dt>
            <span id="logoUrl">
              <Translate contentKey="qrMenuApp.clientAccount.logoUrl">Logo Url</Translate>
            </span>
          </dt>
          <dd>{clientAccountEntity.logoUrl}</dd>
          <dt>
            <span id="isDeleted">
              <Translate contentKey="qrMenuApp.clientAccount.isDeleted">Is Deleted</Translate>
            </span>
          </dt>
          <dd>{clientAccountEntity.isDeleted ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/client-account" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/client-account/${clientAccountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ClientAccountDetail;
