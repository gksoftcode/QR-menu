package com.gksoft.qrmenu.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.gksoft.qrmenu.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AvailableLangTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvailableLang.class);
    }
}
