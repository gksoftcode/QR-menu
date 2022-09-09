import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IClientAccount } from 'app/shared/model/client-account.model';
import { getEntities as getClientAccounts } from 'app/entities/client-account/client-account.reducer';
import { IRestorant } from 'app/shared/model/restorant.model';
import { getEntity, updateEntity, createEntity, reset } from './restorant.reducer';

export const RestorantUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const clientAccounts = useAppSelector(state => state.clientAccount.entities);
  const restorantEntity = useAppSelector(state => state.restorant.entity);
  const loading = useAppSelector(state => state.restorant.loading);
  const updating = useAppSelector(state => state.restorant.updating);
  const updateSuccess = useAppSelector(state => state.restorant.updateSuccess);

  const handleClose = () => {
    navigate('/restorant' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getClientAccounts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...restorantEntity,
      ...values,
      account: clientAccounts.find(it => it.id.toString() === values.account.toString()),
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
          ...restorantEntity,
          account: restorantEntity?.account?.id,
          country: restorantEntity?.country?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="qrMenuApp.restorant.home.createOrEditLabel" data-cy="RestorantCreateUpdateHeading">
            <Translate contentKey="qrMenuApp.restorant.home.createOrEditLabel">Create or edit a Restorant</Translate>
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
                  id="restorant-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('qrMenuApp.restorant.lat')} id="restorant-lat" name="lat" data-cy="lat" type="text" />
              <ValidatedField label={translate('qrMenuApp.restorant.lang')} id="restorant-lang" name="lang" data-cy="lang" type="text" />
              <ValidatedField
                label={translate('qrMenuApp.restorant.isActive')}
                id="restorant-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('qrMenuApp.restorant.isDeleted')}
                id="restorant-isDeleted"
                name="isDeleted"
                data-cy="isDeleted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('qrMenuApp.restorant.logoUrl')}
                id="restorant-logoUrl"
                name="logoUrl"
                data-cy="logoUrl"
                type="text"
              />
              <ValidatedField
                label={translate('qrMenuApp.restorant.fbUrl')}
                id="restorant-fbUrl"
                name="fbUrl"
                data-cy="fbUrl"
                type="text"
              />
              <ValidatedField
                label={translate('qrMenuApp.restorant.instaUrl')}
                id="restorant-instaUrl"
                name="instaUrl"
                data-cy="instaUrl"
                type="text"
              />
              <ValidatedField
                label={translate('qrMenuApp.restorant.twitterUrl')}
                id="restorant-twitterUrl"
                name="twitterUrl"
                data-cy="twitterUrl"
                type="text"
              />
              <ValidatedField
                label={translate('qrMenuApp.restorant.youtubeUrl')}
                id="restorant-youtubeUrl"
                name="youtubeUrl"
                data-cy="youtubeUrl"
                type="text"
              />
              <ValidatedField
                label={translate('qrMenuApp.restorant.googleUrl')}
                id="restorant-googleUrl"
                name="googleUrl"
                data-cy="googleUrl"
                type="text"
              />
              <ValidatedField
                id="restorant-account"
                name="account"
                data-cy="account"
                label={translate('qrMenuApp.restorant.account')}
                type="select"
              >
                <option value="" key="0" />
                {clientAccounts
                  ? clientAccounts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="restorant-country"
                name="country"
                data-cy="country"
                label={translate('qrMenuApp.restorant.country')}
                type="select"
              >
                <option value="" key="0" />
                {countries
                  ? countries.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.countryName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/restorant" replace color="info">
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

export default RestorantUpdate;
