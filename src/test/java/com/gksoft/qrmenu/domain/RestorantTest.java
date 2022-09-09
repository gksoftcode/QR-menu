package com.gksoft.qrmenu.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.gksoft.qrmenu.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RestorantTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Restorant.class);
        Restorant restorant1 = new Restorant();
        restorant1.setId("id1");
        Restorant restorant2 = new Restorant();
        restorant2.setId(restorant1.getId());
        assertThat(restorant1).isEqualTo(restorant2);
        restorant2.setId("id2");
        assertThat(restorant1).isNotEqualTo(restorant2);
        restorant1.setId(null);
        assertThat(restorant1).isNotEqualTo(restorant2);
    }
}
