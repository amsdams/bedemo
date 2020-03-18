package bedemo.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import bedemo.BeDemoApplication;
import bedemo.repository.JaarlijksePremieStortingBerekeningRepository;
import bedemo.service.JaarlijksePremieStortingBeerekeningService;
import bedemo.service.domain.JaarlijksePremieStortingBerekening;

/**
 * Integration tests for the {@link JaarlijksePremieStortingBerekeningResource} REST controller.
 */
@SpringBootTest(classes = BeDemoApplication.class)
public class JaarlijksePremieStortingBerekeningResourceIT {

	private static final double DEFAULT_JAARLIJKSEPREMIESTORTINGRESULTAAT = 434.38255000000004;

	private static final double UPDATED_PREMIEPRECENTAGE = 5d;

	private static final double UPDATED_VOLTIJDFRANCHISE = 15599d;

	private static final double UPDATED_DEELTIJDPERCENTAGE = 100d;

	private static final double UPDATED_VOLTIJDSALARIS = 40000d;

	private static final double DEFAULT_PREMIEPERCENTAGE = 1.657;

	private static final double DEFAULT_VOLTIJDFRANCHISE = 13785d;

	private static final double DEFAULT_DEELTIJDPERCENTAGE = 100d;

	private static final double DEFAULT_VOLTIJDSALARIS = 40000d;

	private static final double UPDATED_JAARLIJKSEPREMIESTORTINGRESULTAAT = 1220.05;

	@Autowired
	private JaarlijksePremieStortingBerekeningRepository jaarlijksePremieStortingBerekeningRepository;

	@Autowired
	private JaarlijksePremieStortingBeerekeningService jaarlijksePremieStortingBeerekeningServiceImpl;

	private MockMvc mockMvc;

	private JaarlijksePremieStortingBerekening jaarlijksePremieStortingBerekening;

	private JaarlijksePremieStortingBerekening jaarlijksePremieStortingUpdated;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		final JaarlijksePremieStortingBerekeningResource jaarlijksePremieStortingBerekeningResource = new JaarlijksePremieStortingBerekeningResource(
				jaarlijksePremieStortingBeerekeningServiceImpl, jaarlijksePremieStortingBerekeningRepository);
		this.mockMvc = MockMvcBuilders.standaloneSetup(jaarlijksePremieStortingBerekeningResource).build();
	}


	public static JaarlijksePremieStortingBerekening createEntity() {
		JaarlijksePremieStortingBerekening jaarlijksePremieStortingBerekening = new JaarlijksePremieStortingBerekening();
		jaarlijksePremieStortingBerekening.setVoltijdSalaris(DEFAULT_VOLTIJDSALARIS);
		jaarlijksePremieStortingBerekening.setDeeltijdPercentage(DEFAULT_DEELTIJDPERCENTAGE);
		jaarlijksePremieStortingBerekening.setVoltijdFranchise(DEFAULT_VOLTIJDFRANCHISE);
		jaarlijksePremieStortingBerekening.setPremiePercentage(DEFAULT_PREMIEPERCENTAGE);

		return jaarlijksePremieStortingBerekening;
	}


	public static JaarlijksePremieStortingBerekening createUpdatedEntity() {
		JaarlijksePremieStortingBerekening jaarlijksePremieStortingBerekening = new JaarlijksePremieStortingBerekening();
		jaarlijksePremieStortingBerekening.setVoltijdSalaris(UPDATED_VOLTIJDSALARIS);
		jaarlijksePremieStortingBerekening.setDeeltijdPercentage(UPDATED_DEELTIJDPERCENTAGE);
		jaarlijksePremieStortingBerekening.setVoltijdFranchise(UPDATED_VOLTIJDFRANCHISE);
		jaarlijksePremieStortingBerekening.setPremiePercentage(UPDATED_PREMIEPRECENTAGE);

		return jaarlijksePremieStortingBerekening;
	}

	@BeforeEach
	public void initTest() {
		jaarlijksePremieStortingBerekening = createEntity();
		jaarlijksePremieStortingUpdated = createUpdatedEntity();
	}

	@Test
	@Transactional
	public void createJaarlijksePremieStortingBerekening() throws Exception {
		int databaseSizeBeforeCreate = jaarlijksePremieStortingBerekeningRepository.findAll().size();

		// Create the JaarlijksePremieStortingBerekening

		mockMvc.perform(post("/api/jaarlijksepremiestortingberekeningen").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(jaarlijksePremieStortingBerekening)))
				.andExpect(status().isCreated()).andDo(print());

		// Validate the JaarlijksePremieStortingBerekening in the database
		List<JaarlijksePremieStortingBerekening> jaarlijksePremieStortingBerekenings = jaarlijksePremieStortingBerekeningRepository.findAll();
		assertThat(jaarlijksePremieStortingBerekenings).hasSize(databaseSizeBeforeCreate + 1);
		JaarlijksePremieStortingBerekening testJaarlijksePremieStorting = jaarlijksePremieStortingBerekenings
				.get(jaarlijksePremieStortingBerekenings.size() - 1);
		assertThat(testJaarlijksePremieStorting.getVoltijdSalaris()).isEqualTo(DEFAULT_VOLTIJDSALARIS);
		assertThat(testJaarlijksePremieStorting.getDeeltijdPercentage()).isEqualTo(DEFAULT_DEELTIJDPERCENTAGE);
		assertThat(testJaarlijksePremieStorting.getVoltijdFranchise()).isEqualTo(DEFAULT_VOLTIJDFRANCHISE);
		assertThat(testJaarlijksePremieStorting.getPremiePercentage()).isEqualTo(DEFAULT_PREMIEPERCENTAGE);

		assertThat(testJaarlijksePremieStorting.getJaarlijksePremieStortingResultaat())
				.isEqualTo(DEFAULT_JAARLIJKSEPREMIESTORTINGRESULTAAT);

	}

	@Test
	@Transactional
	public void createJaarlijksePremieStortingBerekeningUpdated() throws Exception {
		int databaseSizeBeforeCreate = jaarlijksePremieStortingBerekeningRepository.findAll().size();

		// Create the JaarlijksePremieStortingBerekening

		mockMvc.perform(post("/api/jaarlijksepremiestortingberekeningen").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(jaarlijksePremieStortingUpdated)))
				.andExpect(status().isCreated()).andDo(print());

		// Validate the JaarlijksePremieStortingBerekening in the database
		List<JaarlijksePremieStortingBerekening> jaarlijksePremieStortingBerekenings = jaarlijksePremieStortingBerekeningRepository.findAll();
		assertThat(jaarlijksePremieStortingBerekenings).hasSize(databaseSizeBeforeCreate + 1);
		JaarlijksePremieStortingBerekening testJaarlijksePremieStorting = jaarlijksePremieStortingBerekenings
				.get(jaarlijksePremieStortingBerekenings.size() - 1);
		assertThat(testJaarlijksePremieStorting.getVoltijdSalaris()).isEqualTo(UPDATED_VOLTIJDSALARIS);
		assertThat(testJaarlijksePremieStorting.getDeeltijdPercentage()).isEqualTo(UPDATED_DEELTIJDPERCENTAGE);
		assertThat(testJaarlijksePremieStorting.getVoltijdFranchise()).isEqualTo(UPDATED_VOLTIJDFRANCHISE);
		assertThat(testJaarlijksePremieStorting.getPremiePercentage()).isEqualTo(UPDATED_PREMIEPRECENTAGE);

		assertThat(testJaarlijksePremieStorting.getJaarlijksePremieStortingResultaat())
				.isEqualTo(UPDATED_JAARLIJKSEPREMIESTORTINGRESULTAAT);

	}
	
	@Test
    @Transactional
    public void createJaarlijksePremieStortingBerekeningWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jaarlijksePremieStortingBerekeningRepository.findAll().size();

        // Create the JaarlijksePremieStortingBerekening with an existing ID
        jaarlijksePremieStortingBerekening.setId(1L);
        

        // An entity with an existing ID cannot be created, so this API call must fail
        mockMvc.perform(post("/api/jaarlijksepremiestortingberekeningen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jaarlijksePremieStortingBerekening)))
            .andExpect(status().isBadRequest());

        // Validate the JaarlijksePremieStortingBerekening in the database
        List<JaarlijksePremieStortingBerekening> jaarlijksePremieStortingBerekenings = jaarlijksePremieStortingBerekeningRepository.findAll();
        assertThat(jaarlijksePremieStortingBerekenings).hasSize(databaseSizeBeforeCreate);
    }
	
	@Test
    @Transactional
    public void createJaarlijksePremieStortingBerekeningWithExistingJaarlijksePremieStortingResultaa() throws Exception {
        int databaseSizeBeforeCreate = jaarlijksePremieStortingBerekeningRepository.findAll().size();

        // Create the JaarlijksePremieStortingBerekening with an existing ID
        jaarlijksePremieStortingBerekening.setJaarlijksePremieStortingResultaat(1d);
        

        // An entity with an existing ID cannot be created, so this API call must fail
        mockMvc.perform(post("/api/jaarlijksepremiestortingberekeningen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jaarlijksePremieStortingBerekening)))
            .andExpect(status().isBadRequest());

        // Validate the JaarlijksePremieStortingBerekening in the database
        List<JaarlijksePremieStortingBerekening> jaarlijksePremieStortingBerekenings = jaarlijksePremieStortingBerekeningRepository.findAll();
        assertThat(jaarlijksePremieStortingBerekenings).hasSize(databaseSizeBeforeCreate);
    }
	
	@Test
    @Transactional
    public void getJaarlijksePremieStortingBerekening() throws Exception {
        // Initialize the database
		jaarlijksePremieStortingBerekening.setJaarlijksePremieStortingResultaat(DEFAULT_JAARLIJKSEPREMIESTORTINGRESULTAAT);
		jaarlijksePremieStortingBerekeningRepository.saveAndFlush(jaarlijksePremieStortingBerekening);

        // Get the jaarlijksePremieStortingBerekening
        mockMvc.perform(get("/api/jaarlijksepremiestortingberekeningen/{id}", jaarlijksePremieStortingBerekening.getId()))
            .andExpect(status().isOk()).andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(jaarlijksePremieStortingBerekening.getId().intValue()))
            .andExpect(jsonPath("$.deeltijdPercentage").value(DEFAULT_DEELTIJDPERCENTAGE))
            .andExpect(jsonPath("$.jaarlijksePremieStortingResultaat").value(DEFAULT_JAARLIJKSEPREMIESTORTINGRESULTAAT))
            .andExpect(jsonPath("$.premiePercentage").value(DEFAULT_PREMIEPERCENTAGE))
            .andExpect(jsonPath("$.voltijdFranchise").value(DEFAULT_VOLTIJDFRANCHISE))
            .andExpect(jsonPath("$.voltijdSalaris").value(DEFAULT_VOLTIJDSALARIS));
            
    }
	
	@Test
    @Transactional
    public void getAllJaarlijksePremieStortingBerekeningen() throws Exception {
        // Initialize the database
		jaarlijksePremieStortingBerekening.setJaarlijksePremieStortingResultaat(DEFAULT_JAARLIJKSEPREMIESTORTINGRESULTAAT);

        jaarlijksePremieStortingBerekeningRepository.saveAndFlush(jaarlijksePremieStortingBerekening);

        // Get all the jaarlijksePremieStortingList
        mockMvc.perform(get("/api/jaarlijksepremiestortingberekeningen"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[*].id").value(jaarlijksePremieStortingBerekening.getId().intValue()))
            .andExpect(jsonPath("$[*].deeltijdPercentage").value(DEFAULT_DEELTIJDPERCENTAGE))
            .andExpect(jsonPath("$[*].jaarlijksePremieStortingResultaat").value(DEFAULT_JAARLIJKSEPREMIESTORTINGRESULTAAT))
            .andExpect(jsonPath("$[*].premiePercentage").value(DEFAULT_PREMIEPERCENTAGE))
            .andExpect(jsonPath("$[*].voltijdFranchise").value(DEFAULT_VOLTIJDFRANCHISE))
            .andExpect(jsonPath("$[*].voltijdSalaris").value(DEFAULT_VOLTIJDSALARIS));
    }
	
	

}
