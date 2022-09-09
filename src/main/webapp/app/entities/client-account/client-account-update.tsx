import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IClientAccount } from 'app/shared/model/client-account.model';
import { getEntity, updateEntity, createEntity, reset } from './client-account.reducer';

export const ClientAccountUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const clientAccountEntity = useAppSelector(state => state.clientAccount.entity);
  const loading = useAppSelector(state => state.clientAccount.loading);
  const updating = useAppSelector(state => state.clientAccount.updating);
  const updateSuccess = useAppSelector(state => state.clientAccount.updateSuccess);

  const handleClose = () => {
    navigate('/client-account' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...clientAccountEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...clientAccountEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="qrMenuApp.clientAccount.home.createOrEditLabel" data-cy="ClientAccountCreateUpdateHeading">
            <Translate contentKey="qrMenuApp.clientAccount.home.createOrEditLabel">Create or edit a ClientAccount</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="client-account-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('qrMenuApp.clientAccount.fullName')}
                id="client-account-fullName"
                name="fullName"
                data-cy="fullName"
                type="text"
              />
              <ValidatedField
                label={translate('qrMenuApp.clientAccount.mainAddress')}
                id="client-account-mainAddress"
                name="mainAddress"
                data-cy="mainAddress"
                type="text"
              />
              <ValidatedField
                label={translate('qrMenuApp.clientAccount.logoUrl')}
                id="client-account-logoUrl"
                name="logoUrl"
                data-cy="logoUrl"
                type="text"
              />
              <ValidatedField
                label={translate('qrMenuApp.clientAccount.isDeleted')}
                id="client-account-isDeleted"
                name="isDeleted"
                data-cy="isDeleted"
                check
                type="checkbox"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/client-account" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ClientAccountUpdate;
