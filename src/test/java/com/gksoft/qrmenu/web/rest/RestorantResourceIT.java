package com.gksoft.qrmenu.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gksoft.qrmenu.IntegrationTest;
import com.gksoft.qrmenu.domain.Restorant;
import com.gksoft.qrmenu.repository.RestorantRepository;
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
 * Integration tests for the {@link RestorantResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RestorantResourceIT {

    private static final String DEFAULT_LAT = "AAAAAAAAAA";
    private static final String UPDATED_LAT = "BBBBBBBBBB";

    private static final String DEFAULT_LANG = "AAAAAAAAAA";
    private static final String UPDATED_LANG = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_LOGO_URL = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_FB_URL = "AAAAAAAAAA";
    private static final String UPDATED_FB_URL = "BBBBBBBBBB";

    private static final String DEFAULT_INSTA_URL = "AAAAAAAAAA";
    private static final String UPDATED_INSTA_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER_URL = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER_URL = "BBBBBBBBBB";

    private static final String DEFAULT_YOUTUBE_URL = "AAAAAAAAAA";
    private static final String UPDATED_YOUTUBE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_GOOGLE_URL = "AAAAAAAAAA";
    private static final String UPDATED_GOOGLE_URL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/restorants";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private RestorantRepository restorantRepository;

    @Autowired
    private MockMvc restRestorantMockMvc;

    private Restorant restorant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Restorant createEntity() {
        Restorant restorant = new Restorant()
            .lat(DEFAULT_LAT)
            .lang(DEFAULT_LANG)
            .isActive(DEFAULT_IS_ACTIVE)
            .isDeleted(DEFAULT_IS_DELETED)
            .logoUrl(DEFAULT_LOGO_URL)
            .fbUrl(DEFAULT_FB_URL)
            .instaUrl(DEFAULT_INSTA_URL)
            .twitterUrl(DEFAULT_TWITTER_URL)
            .youtubeUrl(DEFAULT_YOUTUBE_URL)
            .googleUrl(DEFAULT_GOOGLE_URL);
        return restorant;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Restorant createUpdatedEntity() {
        Restorant restorant = new Restorant()
            .lat(UPDATED_LAT)
            .lang(UPDATED_LANG)
            .isActive(UPDATED_IS_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED)
            .logoUrl(UPDATED_LOGO_URL)
            .fbUrl(UPDATED_FB_URL)
            .instaUrl(UPDATED_INSTA_URL)
            .twitterUrl(UPDATED_TWITTER_URL)
            .youtubeUrl(UPDATED_YOUTUBE_URL)
            .googleUrl(UPDATED_GOOGLE_URL);
        return restorant;
    }

    @BeforeEach
    public void initTest() {
        restorantRepository.deleteAll();
        restorant = createEntity();
    }

    @Test
    void createRestorant() throws Exception {
        int databaseSizeBeforeCreate = restorantRepository.findAll().size();
        // Create the Restorant
        restRestorantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(restorant)))
            .andExpect(status().isCreated());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeCreate + 1);
        Restorant testRestorant = restorantList.get(restorantList.size() - 1);
        assertThat(testRestorant.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testRestorant.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testRestorant.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRestorant.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testRestorant.getLogoUrl()).isEqualTo(DEFAULT_LOGO_URL);
        assertThat(testRestorant.getFbUrl()).isEqualTo(DEFAULT_FB_URL);
        assertThat(testRestorant.getInstaUrl()).isEqualTo(DEFAULT_INSTA_URL);
        assertThat(testRestorant.getTwitterUrl()).isEqualTo(DEFAULT_TWITTER_URL);
        assertThat(testRestorant.getYoutubeUrl()).isEqualTo(DEFAULT_YOUTUBE_URL);
        assertThat(testRestorant.getGoogleUrl()).isEqualTo(DEFAULT_GOOGLE_URL);
    }

    @Test
    void createRestorantWithExistingId() throws Exception {
        // Create the Restorant with an existing ID
        restorant.setId("existing_id");

        int databaseSizeBeforeCreate = restorantRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRestorantMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(restorant)))
            .andExpect(status().isBadRequest());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllRestorants() throws Exception {
        // Initialize the database
        restorantRepository.save(restorant);

        // Get all the restorantList
        restRestorantMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(restorant.getId())))
            .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT)))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].logoUrl").value(hasItem(DEFAULT_LOGO_URL)))
            .andExpect(jsonPath("$.[*].fbUrl").value(hasItem(DEFAULT_FB_URL)))
            .andExpect(jsonPath("$.[*].instaUrl").value(hasItem(DEFAULT_INSTA_URL)))
            .andExpect(jsonPath("$.[*].twitterUrl").value(hasItem(DEFAULT_TWITTER_URL)))
            .andExpect(jsonPath("$.[*].youtubeUrl").value(hasItem(DEFAULT_YOUTUBE_URL)))
            .andExpect(jsonPath("$.[*].googleUrl").value(hasItem(DEFAULT_GOOGLE_URL)));
    }

    @Test
    void getRestorant() throws Exception {
        // Initialize the database
        restorantRepository.save(restorant);

        // Get the restorant
        restRestorantMockMvc
            .perform(get(ENTITY_API_URL_ID, restorant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(restorant.getId()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.logoUrl").value(DEFAULT_LOGO_URL))
            .andExpect(jsonPath("$.fbUrl").value(DEFAULT_FB_URL))
            .andExpect(jsonPath("$.instaUrl").value(DEFAULT_INSTA_URL))
            .andExpect(jsonPath("$.twitterUrl").value(DEFAULT_TWITTER_URL))
            .andExpect(jsonPath("$.youtubeUrl").value(DEFAULT_YOUTUBE_URL))
            .andExpect(jsonPath("$.googleUrl").value(DEFAULT_GOOGLE_URL));
    }

    @Test
    void getNonExistingRestorant() throws Exception {
        // Get the restorant
        restRestorantMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingRestorant() throws Exception {
        // Initialize the database
        restorantRepository.save(restorant);

        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();

        // Update the restorant
        Restorant updatedRestorant = restorantRepository.findById(restorant.getId()).get();
        updatedRestorant
            .lat(UPDATED_LAT)
            .lang(UPDATED_LANG)
            .isActive(UPDATED_IS_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED)
            .logoUrl(UPDATED_LOGO_URL)
            .fbUrl(UPDATED_FB_URL)
            .instaUrl(UPDATED_INSTA_URL)
            .twitterUrl(UPDATED_TWITTER_URL)
            .youtubeUrl(UPDATED_YOUTUBE_URL)
            .googleUrl(UPDATED_GOOGLE_URL);

        restRestorantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRestorant.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRestorant))
            )
            .andExpect(status().isOk());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
        Restorant testRestorant = restorantList.get(restorantList.size() - 1);
        assertThat(testRestorant.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testRestorant.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testRestorant.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRestorant.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testRestorant.getLogoUrl()).isEqualTo(UPDATED_LOGO_URL);
        assertThat(testRestorant.getFbUrl()).isEqualTo(UPDATED_FB_URL);
        assertThat(testRestorant.getInstaUrl()).isEqualTo(UPDATED_INSTA_URL);
        assertThat(testRestorant.getTwitterUrl()).isEqualTo(UPDATED_TWITTER_URL);
        assertThat(testRestorant.getYoutubeUrl()).isEqualTo(UPDATED_YOUTUBE_URL);
        assertThat(testRestorant.getGoogleUrl()).isEqualTo(UPDATED_GOOGLE_URL);
    }

    @Test
    void putNonExistingRestorant() throws Exception {
        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();
        restorant.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRestorantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, restorant.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(restorant))
            )
            .andExpect(status().isBadRequest());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRestorant() throws Exception {
        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();
        restorant.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRestorantMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(restorant))
            )
            .andExpect(status().isBadRequest());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRestorant() throws Exception {
        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();
        restorant.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRestorantMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(restorant)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRestorantWithPatch() throws Exception {
        // Initialize the database
        restorantRepository.save(restorant);

        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();

        // Update the restorant using partial update
        Restorant partialUpdatedRestorant = new Restorant();
        partialUpdatedRestorant.setId(restorant.getId());

        partialUpdatedRestorant
            .logoUrl(UPDATED_LOGO_URL)
            .instaUrl(UPDATED_INSTA_URL)
            .youtubeUrl(UPDATED_YOUTUBE_URL)
            .googleUrl(UPDATED_GOOGLE_URL);

        restRestorantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRestorant.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRestorant))
            )
            .andExpect(status().isOk());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
        Restorant testRestorant = restorantList.get(restorantList.size() - 1);
        assertThat(testRestorant.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testRestorant.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testRestorant.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRestorant.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testRestorant.getLogoUrl()).isEqualTo(UPDATED_LOGO_URL);
        assertThat(testRestorant.getFbUrl()).isEqualTo(DEFAULT_FB_URL);
        assertThat(testRestorant.getInstaUrl()).isEqualTo(UPDATED_INSTA_URL);
        assertThat(testRestorant.getTwitterUrl()).isEqualTo(DEFAULT_TWITTER_URL);
        assertThat(testRestorant.getYoutubeUrl()).isEqualTo(UPDATED_YOUTUBE_URL);
        assertThat(testRestorant.getGoogleUrl()).isEqualTo(UPDATED_GOOGLE_URL);
    }

    @Test
    void fullUpdateRestorantWithPatch() throws Exception {
        // Initialize the database
        restorantRepository.save(restorant);

        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();

        // Update the restorant using partial update
        Restorant partialUpdatedRestorant = new Restorant();
        partialUpdatedRestorant.setId(restorant.getId());

        partialUpdatedRestorant
            .lat(UPDATED_LAT)
            .lang(UPDATED_LANG)
            .isActive(UPDATED_IS_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED)
            .logoUrl(UPDATED_LOGO_URL)
            .fbUrl(UPDATED_FB_URL)
            .instaUrl(UPDATED_INSTA_URL)
            .twitterUrl(UPDATED_TWITTER_URL)
            .youtubeUrl(UPDATED_YOUTUBE_URL)
            .googleUrl(UPDATED_GOOGLE_URL);

        restRestorantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRestorant.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRestorant))
            )
            .andExpect(status().isOk());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
        Restorant testRestorant = restorantList.get(restorantList.size() - 1);
        assertThat(testRestorant.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testRestorant.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testRestorant.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRestorant.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testRestorant.getLogoUrl()).isEqualTo(UPDATED_LOGO_URL);
        assertThat(testRestorant.getFbUrl()).isEqualTo(UPDATED_FB_URL);
        assertThat(testRestorant.getInstaUrl()).isEqualTo(UPDATED_INSTA_URL);
        assertThat(testRestorant.getTwitterUrl()).isEqualTo(UPDATED_TWITTER_URL);
        assertThat(testRestorant.getYoutubeUrl()).isEqualTo(UPDATED_YOUTUBE_URL);
        assertThat(testRestorant.getGoogleUrl()).isEqualTo(UPDATED_GOOGLE_URL);
    }

    @Test
    void patchNonExistingRestorant() throws Exception {
        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();
        restorant.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRestorantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, restorant.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(restorant))
            )
            .andExpect(status().isBadRequest());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRestorant() throws Exception {
        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();
        restorant.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRestorantMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(restorant))
            )
            .andExpect(status().isBadRequest());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRestorant() throws Exception {
        int databaseSizeBeforeUpdate = restorantRepository.findAll().size();
        restorant.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRestorantMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(restorant))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Restorant in the database
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRestorant() throws Exception {
        // Initialize the database
        restorantRepository.save(restorant);

        int databaseSizeBeforeDelete = restorantRepository.findAll().size();

        // Delete the restorant
        restRestorantMockMvc
            .perform(delete(ENTITY_API_URL_ID, restorant.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Restorant> restorantList = restorantRepository.findAll();
        assertThat(restorantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
