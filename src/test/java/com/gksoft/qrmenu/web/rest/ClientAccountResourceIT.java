package com.gksoft.qrmenu.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gksoft.qrmenu.IntegrationTest;
import com.gksoft.qrmenu.domain.ClientAccount;
import com.gksoft.qrmenu.repository.ClientAccountRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link ClientAccountResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientAccountResourceIT {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MAIN_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_URL = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String ENTITY_API_URL = "/api/client-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Autowired
    private MockMvc restClientAccountMockMvc;

    private ClientAccount clientAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientAccount createEntity() {
        ClientAccount clientAccount = new ClientAccount()
            .fullName(DEFAULT_FULL_NAME)
            .mainAddress(DEFAULT_MAIN_ADDRESS)
            .logoUrl(DEFAULT_LOGO_URL)
            .isDeleted(DEFAULT_IS_DELETED);
        return clientAccount;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientAccount createUpdatedEntity() {
        ClientAccount clientAccount = new ClientAccount()
            .fullName(UPDATED_FULL_NAME)
            .mainAddress(UPDATED_MAIN_ADDRESS)
            .logoUrl(UPDATED_LOGO_URL)
            .isDeleted(UPDATED_IS_DELETED);
        return clientAccount;
    }

    @BeforeEach
    public void initTest() {
        clientAccountRepository.deleteAll();
        clientAccount = createEntity();
    }

    @Test
    void createClientAccount() throws Exception {
        int databaseSizeBeforeCreate = clientAccountRepository.findAll().size();
        // Create the ClientAccount
        restClientAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientAccount)))
            .andExpect(status().isCreated());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeCreate + 1);
        ClientAccount testClientAccount = clientAccountList.get(clientAccountList.size() - 1);
        assertThat(testClientAccount.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testClientAccount.getMainAddress()).isEqualTo(DEFAULT_MAIN_ADDRESS);
        assertThat(testClientAccount.getLogoUrl()).isEqualTo(DEFAULT_LOGO_URL);
        assertThat(testClientAccount.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    void createClientAccountWithExistingId() throws Exception {
        // Create the ClientAccount with an existing ID
        clientAccount.setId("existing_id");

        int databaseSizeBeforeCreate = clientAccountRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientAccount)))
            .andExpect(status().isBadRequest());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClientAccounts() throws Exception {
        // Initialize the database
        clientAccountRepository.save(clientAccount);

        // Get all the clientAccountList
        restClientAccountMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientAccount.getId())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].mainAddress").value(hasItem(DEFAULT_MAIN_ADDRESS)))
            .andExpect(jsonPath("$.[*].logoUrl").value(hasItem(DEFAULT_LOGO_URL)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }

    @Test
    void getClientAccount() throws Exception {
        // Initialize the database
        clientAccountRepository.save(clientAccount);

        // Get the clientAccount
        restClientAccountMockMvc
            .perform(get(ENTITY_API_URL_ID, clientAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientAccount.getId()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.mainAddress").value(DEFAULT_MAIN_ADDRESS))
            .andExpect(jsonPath("$.logoUrl").value(DEFAULT_LOGO_URL))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    void getNonExistingClientAccount() throws Exception {
        // Get the clientAccount
        restClientAccountMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingClientAccount() throws Exception {
        // Initialize the database
        clientAccountRepository.save(clientAccount);

        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();

        // Update the clientAccount
        ClientAccount updatedClientAccount = clientAccountRepository.findById(clientAccount.getId()).get();
        updatedClientAccount
            .fullName(UPDATED_FULL_NAME)
            .mainAddress(UPDATED_MAIN_ADDRESS)
            .logoUrl(UPDATED_LOGO_URL)
            .isDeleted(UPDATED_IS_DELETED);

        restClientAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedClientAccount.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedClientAccount))
            )
            .andExpect(status().isOk());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
        ClientAccount testClientAccount = clientAccountList.get(clientAccountList.size() - 1);
        assertThat(testClientAccount.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testClientAccount.getMainAddress()).isEqualTo(UPDATED_MAIN_ADDRESS);
        assertThat(testClientAccount.getLogoUrl()).isEqualTo(UPDATED_LOGO_URL);
        assertThat(testClientAccount.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    void putNonExistingClientAccount() throws Exception {
        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();
        clientAccount.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientAccount.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClientAccount() throws Exception {
        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();
        clientAccount.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClientAccount() throws Exception {
        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();
        clientAccount.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientAccountMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(clientAccount)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClientAccountWithPatch() throws Exception {
        // Initialize the database
        clientAccountRepository.save(clientAccount);

        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();

        // Update the clientAccount using partial update
        ClientAccount partialUpdatedClientAccount = new ClientAccount();
        partialUpdatedClientAccount.setId(clientAccount.getId());

        partialUpdatedClientAccount.fullName(UPDATED_FULL_NAME).logoUrl(UPDATED_LOGO_URL).isDeleted(UPDATED_IS_DELETED);

        restClientAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientAccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientAccount))
            )
            .andExpect(status().isOk());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
        ClientAccount testClientAccount = clientAccountList.get(clientAccountList.size() - 1);
        assertThat(testClientAccount.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testClientAccount.getMainAddress()).isEqualTo(DEFAULT_MAIN_ADDRESS);
        assertThat(testClientAccount.getLogoUrl()).isEqualTo(UPDATED_LOGO_URL);
        assertThat(testClientAccount.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    void fullUpdateClientAccountWithPatch() throws Exception {
        // Initialize the database
        clientAccountRepository.save(clientAccount);

        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();

        // Update the clientAccount using partial update
        ClientAccount partialUpdatedClientAccount = new ClientAccount();
        partialUpdatedClientAccount.setId(clientAccount.getId());

        partialUpdatedClientAccount
            .fullName(UPDATED_FULL_NAME)
            .mainAddress(UPDATED_MAIN_ADDRESS)
            .logoUrl(UPDATED_LOGO_URL)
            .isDeleted(UPDATED_IS_DELETED);

        restClientAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientAccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientAccount))
            )
            .andExpect(status().isOk());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
        ClientAccount testClientAccount = clientAccountList.get(clientAccountList.size() - 1);
        assertThat(testClientAccount.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testClientAccount.getMainAddress()).isEqualTo(UPDATED_MAIN_ADDRESS);
        assertThat(testClientAccount.getLogoUrl()).isEqualTo(UPDATED_LOGO_URL);
        assertThat(testClientAccount.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    void patchNonExistingClientAccount() throws Exception {
        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();
        clientAccount.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientAccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClientAccount() throws Exception {
        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();
        clientAccount.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClientAccount() throws Exception {
        int databaseSizeBeforeUpdate = clientAccountRepository.findAll().size();
        clientAccount.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientAccountMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(clientAccount))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientAccount in the database
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClientAccount() throws Exception {
        // Initialize the database
        clientAccountRepository.save(clientAccount);

        int databaseSizeBeforeDelete = clientAccountRepository.findAll().size();

        // Delete the clientAccount
        restClientAccountMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientAccount.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClientAccount> clientAccountList = clientAccountRepository.findAll();
        assertThat(clientAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
