package io.github.menubu.application.web.rest;

import io.github.menubu.application.MenubuApp;

import io.github.menubu.application.domain.LANGUAGE;
import io.github.menubu.application.repository.LANGUAGERepository;
import io.github.menubu.application.repository.search.LANGUAGESearchRepository;
import io.github.menubu.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.menubu.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LANGUAGEResource REST controller.
 *
 * @see LANGUAGEResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MenubuApp.class)
public class LANGUAGEResourceIntTest {

    private static final String DEFAULT_C_ODE = "AAAAAAAAAA";
    private static final String UPDATED_C_ODE = "BBBBBBBBBB";

    private static final String DEFAULT_N_AME = "AAAAAAAAAA";
    private static final String UPDATED_N_AME = "BBBBBBBBBB";

    private static final Instant DEFAULT_C_REATEDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_C_REATEDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_C_REATEUSER = "AAAAAAAAAA";
    private static final String UPDATED_C_REATEUSER = "BBBBBBBBBB";

    private static final Instant DEFAULT_U_PDATEDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_U_PDATEDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_U_PDATEUSER = "AAAAAAAAAA";
    private static final String UPDATED_U_PDATEUSER = "BBBBBBBBBB";

    private static final String DEFAULT_U_SABLEINEMAIL = "AAAAAAAAAA";
    private static final String UPDATED_U_SABLEINEMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_T_ROYACODE = "AAAAAAAAAA";
    private static final String UPDATED_T_ROYACODE = "BBBBBBBBBB";

    @Autowired
    private LANGUAGERepository lANGUAGERepository;

    @Autowired
    private LANGUAGESearchRepository lANGUAGESearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLANGUAGEMockMvc;

    private LANGUAGE lANGUAGE;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LANGUAGEResource lANGUAGEResource = new LANGUAGEResource(lANGUAGERepository, lANGUAGESearchRepository);
        this.restLANGUAGEMockMvc = MockMvcBuilders.standaloneSetup(lANGUAGEResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LANGUAGE createEntity(EntityManager em) {
        LANGUAGE lANGUAGE = new LANGUAGE()
            .cODE(DEFAULT_C_ODE)
            .nAME(DEFAULT_N_AME)
            .cREATEDATE(DEFAULT_C_REATEDATE)
            .cREATEUSER(DEFAULT_C_REATEUSER)
            .uPDATEDATE(DEFAULT_U_PDATEDATE)
            .uPDATEUSER(DEFAULT_U_PDATEUSER)
            .uSABLEINEMAIL(DEFAULT_U_SABLEINEMAIL)
            .tROYACODE(DEFAULT_T_ROYACODE);
        return lANGUAGE;
    }

    @Before
    public void initTest() {
        lANGUAGESearchRepository.deleteAll();
        lANGUAGE = createEntity(em);
    }

    @Test
    @Transactional
    public void createLANGUAGE() throws Exception {
        int databaseSizeBeforeCreate = lANGUAGERepository.findAll().size();

        // Create the LANGUAGE
        restLANGUAGEMockMvc.perform(post("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lANGUAGE)))
            .andExpect(status().isCreated());

        // Validate the LANGUAGE in the database
        List<LANGUAGE> lANGUAGEList = lANGUAGERepository.findAll();
        assertThat(lANGUAGEList).hasSize(databaseSizeBeforeCreate + 1);
        LANGUAGE testLANGUAGE = lANGUAGEList.get(lANGUAGEList.size() - 1);
        assertThat(testLANGUAGE.getcODE()).isEqualTo(DEFAULT_C_ODE);
        assertThat(testLANGUAGE.getnAME()).isEqualTo(DEFAULT_N_AME);
        assertThat(testLANGUAGE.getcREATEDATE()).isEqualTo(DEFAULT_C_REATEDATE);
        assertThat(testLANGUAGE.getcREATEUSER()).isEqualTo(DEFAULT_C_REATEUSER);
        assertThat(testLANGUAGE.getuPDATEDATE()).isEqualTo(DEFAULT_U_PDATEDATE);
        assertThat(testLANGUAGE.getuPDATEUSER()).isEqualTo(DEFAULT_U_PDATEUSER);
        assertThat(testLANGUAGE.getuSABLEINEMAIL()).isEqualTo(DEFAULT_U_SABLEINEMAIL);
        assertThat(testLANGUAGE.gettROYACODE()).isEqualTo(DEFAULT_T_ROYACODE);

        // Validate the LANGUAGE in Elasticsearch
        LANGUAGE lANGUAGEEs = lANGUAGESearchRepository.findOne(testLANGUAGE.getId());
        assertThat(lANGUAGEEs).isEqualToIgnoringGivenFields(testLANGUAGE);
    }

    @Test
    @Transactional
    public void createLANGUAGEWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lANGUAGERepository.findAll().size();

        // Create the LANGUAGE with an existing ID
        lANGUAGE.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLANGUAGEMockMvc.perform(post("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lANGUAGE)))
            .andExpect(status().isBadRequest());

        // Validate the LANGUAGE in the database
        List<LANGUAGE> lANGUAGEList = lANGUAGERepository.findAll();
        assertThat(lANGUAGEList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLANGUAGES() throws Exception {
        // Initialize the database
        lANGUAGERepository.saveAndFlush(lANGUAGE);

        // Get all the lANGUAGEList
        restLANGUAGEMockMvc.perform(get("/api/languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lANGUAGE.getId().intValue())))
            .andExpect(jsonPath("$.[*].cODE").value(hasItem(DEFAULT_C_ODE.toString())))
            .andExpect(jsonPath("$.[*].nAME").value(hasItem(DEFAULT_N_AME.toString())))
            .andExpect(jsonPath("$.[*].cREATEDATE").value(hasItem(DEFAULT_C_REATEDATE.toString())))
            .andExpect(jsonPath("$.[*].cREATEUSER").value(hasItem(DEFAULT_C_REATEUSER.toString())))
            .andExpect(jsonPath("$.[*].uPDATEDATE").value(hasItem(DEFAULT_U_PDATEDATE.toString())))
            .andExpect(jsonPath("$.[*].uPDATEUSER").value(hasItem(DEFAULT_U_PDATEUSER.toString())))
            .andExpect(jsonPath("$.[*].uSABLEINEMAIL").value(hasItem(DEFAULT_U_SABLEINEMAIL.toString())))
            .andExpect(jsonPath("$.[*].tROYACODE").value(hasItem(DEFAULT_T_ROYACODE.toString())));
    }

    @Test
    @Transactional
    public void getLANGUAGE() throws Exception {
        // Initialize the database
        lANGUAGERepository.saveAndFlush(lANGUAGE);

        // Get the lANGUAGE
        restLANGUAGEMockMvc.perform(get("/api/languages/{id}", lANGUAGE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lANGUAGE.getId().intValue()))
            .andExpect(jsonPath("$.cODE").value(DEFAULT_C_ODE.toString()))
            .andExpect(jsonPath("$.nAME").value(DEFAULT_N_AME.toString()))
            .andExpect(jsonPath("$.cREATEDATE").value(DEFAULT_C_REATEDATE.toString()))
            .andExpect(jsonPath("$.cREATEUSER").value(DEFAULT_C_REATEUSER.toString()))
            .andExpect(jsonPath("$.uPDATEDATE").value(DEFAULT_U_PDATEDATE.toString()))
            .andExpect(jsonPath("$.uPDATEUSER").value(DEFAULT_U_PDATEUSER.toString()))
            .andExpect(jsonPath("$.uSABLEINEMAIL").value(DEFAULT_U_SABLEINEMAIL.toString()))
            .andExpect(jsonPath("$.tROYACODE").value(DEFAULT_T_ROYACODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLANGUAGE() throws Exception {
        // Get the lANGUAGE
        restLANGUAGEMockMvc.perform(get("/api/languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLANGUAGE() throws Exception {
        // Initialize the database
        lANGUAGERepository.saveAndFlush(lANGUAGE);
        lANGUAGESearchRepository.save(lANGUAGE);
        int databaseSizeBeforeUpdate = lANGUAGERepository.findAll().size();

        // Update the lANGUAGE
        LANGUAGE updatedLANGUAGE = lANGUAGERepository.findOne(lANGUAGE.getId());
        // Disconnect from session so that the updates on updatedLANGUAGE are not directly saved in db
        em.detach(updatedLANGUAGE);
        updatedLANGUAGE
            .cODE(UPDATED_C_ODE)
            .nAME(UPDATED_N_AME)
            .cREATEDATE(UPDATED_C_REATEDATE)
            .cREATEUSER(UPDATED_C_REATEUSER)
            .uPDATEDATE(UPDATED_U_PDATEDATE)
            .uPDATEUSER(UPDATED_U_PDATEUSER)
            .uSABLEINEMAIL(UPDATED_U_SABLEINEMAIL)
            .tROYACODE(UPDATED_T_ROYACODE);

        restLANGUAGEMockMvc.perform(put("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLANGUAGE)))
            .andExpect(status().isOk());

        // Validate the LANGUAGE in the database
        List<LANGUAGE> lANGUAGEList = lANGUAGERepository.findAll();
        assertThat(lANGUAGEList).hasSize(databaseSizeBeforeUpdate);
        LANGUAGE testLANGUAGE = lANGUAGEList.get(lANGUAGEList.size() - 1);
        assertThat(testLANGUAGE.getcODE()).isEqualTo(UPDATED_C_ODE);
        assertThat(testLANGUAGE.getnAME()).isEqualTo(UPDATED_N_AME);
        assertThat(testLANGUAGE.getcREATEDATE()).isEqualTo(UPDATED_C_REATEDATE);
        assertThat(testLANGUAGE.getcREATEUSER()).isEqualTo(UPDATED_C_REATEUSER);
        assertThat(testLANGUAGE.getuPDATEDATE()).isEqualTo(UPDATED_U_PDATEDATE);
        assertThat(testLANGUAGE.getuPDATEUSER()).isEqualTo(UPDATED_U_PDATEUSER);
        assertThat(testLANGUAGE.getuSABLEINEMAIL()).isEqualTo(UPDATED_U_SABLEINEMAIL);
        assertThat(testLANGUAGE.gettROYACODE()).isEqualTo(UPDATED_T_ROYACODE);

        // Validate the LANGUAGE in Elasticsearch
        LANGUAGE lANGUAGEEs = lANGUAGESearchRepository.findOne(testLANGUAGE.getId());
        assertThat(lANGUAGEEs).isEqualToIgnoringGivenFields(testLANGUAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingLANGUAGE() throws Exception {
        int databaseSizeBeforeUpdate = lANGUAGERepository.findAll().size();

        // Create the LANGUAGE

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLANGUAGEMockMvc.perform(put("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lANGUAGE)))
            .andExpect(status().isCreated());

        // Validate the LANGUAGE in the database
        List<LANGUAGE> lANGUAGEList = lANGUAGERepository.findAll();
        assertThat(lANGUAGEList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLANGUAGE() throws Exception {
        // Initialize the database
        lANGUAGERepository.saveAndFlush(lANGUAGE);
        lANGUAGESearchRepository.save(lANGUAGE);
        int databaseSizeBeforeDelete = lANGUAGERepository.findAll().size();

        // Get the lANGUAGE
        restLANGUAGEMockMvc.perform(delete("/api/languages/{id}", lANGUAGE.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean lANGUAGEExistsInEs = lANGUAGESearchRepository.exists(lANGUAGE.getId());
        assertThat(lANGUAGEExistsInEs).isFalse();

        // Validate the database is empty
        List<LANGUAGE> lANGUAGEList = lANGUAGERepository.findAll();
        assertThat(lANGUAGEList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchLANGUAGE() throws Exception {
        // Initialize the database
        lANGUAGERepository.saveAndFlush(lANGUAGE);
        lANGUAGESearchRepository.save(lANGUAGE);

        // Search the lANGUAGE
        restLANGUAGEMockMvc.perform(get("/api/_search/languages?query=id:" + lANGUAGE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lANGUAGE.getId().intValue())))
            .andExpect(jsonPath("$.[*].cODE").value(hasItem(DEFAULT_C_ODE.toString())))
            .andExpect(jsonPath("$.[*].nAME").value(hasItem(DEFAULT_N_AME.toString())))
            .andExpect(jsonPath("$.[*].cREATEDATE").value(hasItem(DEFAULT_C_REATEDATE.toString())))
            .andExpect(jsonPath("$.[*].cREATEUSER").value(hasItem(DEFAULT_C_REATEUSER.toString())))
            .andExpect(jsonPath("$.[*].uPDATEDATE").value(hasItem(DEFAULT_U_PDATEDATE.toString())))
            .andExpect(jsonPath("$.[*].uPDATEUSER").value(hasItem(DEFAULT_U_PDATEUSER.toString())))
            .andExpect(jsonPath("$.[*].uSABLEINEMAIL").value(hasItem(DEFAULT_U_SABLEINEMAIL.toString())))
            .andExpect(jsonPath("$.[*].tROYACODE").value(hasItem(DEFAULT_T_ROYACODE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LANGUAGE.class);
        LANGUAGE lANGUAGE1 = new LANGUAGE();
        lANGUAGE1.setId(1L);
        LANGUAGE lANGUAGE2 = new LANGUAGE();
        lANGUAGE2.setId(lANGUAGE1.getId());
        assertThat(lANGUAGE1).isEqualTo(lANGUAGE2);
        lANGUAGE2.setId(2L);
        assertThat(lANGUAGE1).isNotEqualTo(lANGUAGE2);
        lANGUAGE1.setId(null);
        assertThat(lANGUAGE1).isNotEqualTo(lANGUAGE2);
    }
}
