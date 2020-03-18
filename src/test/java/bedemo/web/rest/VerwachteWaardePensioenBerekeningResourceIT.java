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
import bedemo.repository.VerwachteWaardePensioenBerekeningRepository;
import bedemo.service.VerwachteWaardePensioenBerekeningService;
import bedemo.service.domain.VerwachteWaardePensioenBerekening;
import lombok.extern.slf4j.Slf4j;

/**
 * Integration tests for the {@link VerwachteWaardePensioenenResource} REST
 * controller.
 */
@Slf4j
@SpringBootTest(classes = BeDemoApplication.class)
public class VerwachteWaardePensioenBerekeningResourceIT {

	private static final double UPDATED_JAARLIJKSRENDEMENTBELEGGINGEN = 3d;

	private static final double UPDATED_JAARLIJKSEPREMIESTORTING = 1220.05;

	private static final double UPDATED_HUIDIGEWAARDEBELEGGINGEN = 100d;

	private static final int UPDATED_GEWENSTEPENSIOENLEEFTIJDDEELNEMER = 68;

	private static final int UPDATED_HUIDIGELEEFTIJDDEELNEMER = 35;

	private static final double DEFAULT_JAARLIJKSRENDEMENTBELEGGINGEN = 3d;

	private static final double DEFAULT_JAARLIJKSEPREMIESTORTING = 434.38255000000004;

	private static final double DEFAULT_HUIDIGEWAARDEBELEGGINGEN = 100d;

	private static final int DEFAULT_GEWENSTEPENSIOENLEEFTIJDDEELNEMER = 68;

	private static final int DEFAULT_HUIDIGELEEFTIJDDEELNEMER = 35;

	private static final double DEFAULT_VERWACHTEWAARDEPENSIOENRESULTAAT = 24548.959464041167;

	private static final double UPDATED_VERWACHTEWAARDEPENSIOENRESULTAAT = 68470.91957947443;

	@Autowired
	private VerwachteWaardePensioenBerekeningRepository verwachteWaardePensioenBerekeningRepository;

	@Autowired
	private VerwachteWaardePensioenBerekeningService verwachteWaardePensioenBerekeningServiceImpl;

	private MockMvc mockMvc;

	private VerwachteWaardePensioenBerekening verwachteWaardePensioenBerekening;

	private VerwachteWaardePensioenBerekening verwachteWaardePensioenUpdated;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		final VerwachteWaardePensioenBerekeningResource verwachteWaardePensioenBerekeningResource = new VerwachteWaardePensioenBerekeningResource(
				verwachteWaardePensioenBerekeningServiceImpl, verwachteWaardePensioenBerekeningRepository);
		this.mockMvc = MockMvcBuilders.standaloneSetup(verwachteWaardePensioenBerekeningResource).build();
	}


	public static VerwachteWaardePensioenBerekening createEntity() {
		VerwachteWaardePensioenBerekening verwachteWaardePensioenBerekening = new VerwachteWaardePensioenBerekening();
		verwachteWaardePensioenBerekening.setHuidigeLeeftijdDeelnemer(DEFAULT_HUIDIGELEEFTIJDDEELNEMER);
		verwachteWaardePensioenBerekening.setGewenstePensioenLeeftijdDeelnemer(DEFAULT_GEWENSTEPENSIOENLEEFTIJDDEELNEMER);

		verwachteWaardePensioenBerekening.setHuidigeWaardeBeleggingen(DEFAULT_HUIDIGEWAARDEBELEGGINGEN);
		verwachteWaardePensioenBerekening.setJaarlijksePremieStorting(DEFAULT_JAARLIJKSEPREMIESTORTING);
		verwachteWaardePensioenBerekening.setJaarlijksRendementBeleggingen(DEFAULT_JAARLIJKSRENDEMENTBELEGGINGEN);

		return verwachteWaardePensioenBerekening;
	}


	public static VerwachteWaardePensioenBerekening createUpdatedEntity() {
		VerwachteWaardePensioenBerekening verwachteWaardePensioenBerekening = new VerwachteWaardePensioenBerekening();
		verwachteWaardePensioenBerekening.setHuidigeLeeftijdDeelnemer(UPDATED_HUIDIGELEEFTIJDDEELNEMER);
		verwachteWaardePensioenBerekening.setGewenstePensioenLeeftijdDeelnemer(UPDATED_GEWENSTEPENSIOENLEEFTIJDDEELNEMER);

		verwachteWaardePensioenBerekening.setHuidigeWaardeBeleggingen(UPDATED_HUIDIGEWAARDEBELEGGINGEN);
		verwachteWaardePensioenBerekening.setJaarlijksePremieStorting(UPDATED_JAARLIJKSEPREMIESTORTING);
		verwachteWaardePensioenBerekening.setJaarlijksRendementBeleggingen(UPDATED_JAARLIJKSRENDEMENTBELEGGINGEN);

		return verwachteWaardePensioenBerekening;
	}

	@BeforeEach
	public void initTest() {
		verwachteWaardePensioenBerekening = createEntity();
		verwachteWaardePensioenUpdated = createUpdatedEntity();
	}

	@Test
	@Transactional
	public void createVerwachteWaardePensioenBerekening() throws Exception {
		int databaseSizeBeforeCreate = verwachteWaardePensioenBerekeningRepository.findAll().size();

		// Create the VerwachteWaardePensioenBerekening

		mockMvc.perform(post("/api/verwachtewaardepensioenberekeningen").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(verwachteWaardePensioenBerekening))).andExpect(status().isCreated())
				.andDo(print());

		// Validate the VerwachteWaardePensioenBerekening in the database
		List<VerwachteWaardePensioenBerekening> verwachteWaardePensioenBerekening = verwachteWaardePensioenBerekeningRepository.findAll();
		assertThat(verwachteWaardePensioenBerekening).hasSize(databaseSizeBeforeCreate + 1);
		VerwachteWaardePensioenBerekening testVerwachteWaardePensioen = verwachteWaardePensioenBerekening
				.get(verwachteWaardePensioenBerekening.size() - 1);
		log.debug("verwachteWaardePensioenBerekening {}", verwachteWaardePensioenBerekening);
		assertThat(testVerwachteWaardePensioen.getGewenstePensioenLeeftijdDeelnemer())
				.isEqualTo(DEFAULT_GEWENSTEPENSIOENLEEFTIJDDEELNEMER);
		assertThat(testVerwachteWaardePensioen.getHuidigeLeeftijdDeelnemer())
				.isEqualTo(DEFAULT_HUIDIGELEEFTIJDDEELNEMER);
		assertThat(testVerwachteWaardePensioen.getHuidigeWaardeBeleggingen())
				.isEqualTo(DEFAULT_HUIDIGEWAARDEBELEGGINGEN);
		assertThat(testVerwachteWaardePensioen.getJaarlijksePremieStorting())
				.isEqualTo(DEFAULT_JAARLIJKSEPREMIESTORTING);
		assertThat(testVerwachteWaardePensioen.getJaarlijksRendementBeleggingen())
				.isEqualTo(DEFAULT_JAARLIJKSRENDEMENTBELEGGINGEN);

		assertThat(testVerwachteWaardePensioen.getVerwachteWaardePensioenResultaat())
				.isEqualTo(DEFAULT_VERWACHTEWAARDEPENSIOENRESULTAAT);

	}

	@Test
	@Transactional
	public void createVerwachteWaardePensioenBerekeningUpdated() throws Exception {
		int databaseSizeBeforeCreate = verwachteWaardePensioenBerekeningRepository.findAll().size();

		// Create the VerwachteWaardePensioenBerekening

		mockMvc.perform(post("/api/verwachtewaardepensioenberekeningen").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(verwachteWaardePensioenUpdated)))
				.andExpect(status().isCreated()).andDo(print());

		// Validate the VerwachteWaardePensioenBerekening in the database
		List<VerwachteWaardePensioenBerekening> verwachteWaardePensioenBerekening = verwachteWaardePensioenBerekeningRepository.findAll();
		assertThat(verwachteWaardePensioenBerekening).hasSize(databaseSizeBeforeCreate + 1);
		VerwachteWaardePensioenBerekening testVerwachteWaardePensioen = verwachteWaardePensioenBerekening
				.get(verwachteWaardePensioenBerekening.size() - 1);
		log.debug("verwachteWaardePensioenBerekening {}", verwachteWaardePensioenBerekening);
		assertThat(testVerwachteWaardePensioen.getGewenstePensioenLeeftijdDeelnemer())
				.isEqualTo(UPDATED_GEWENSTEPENSIOENLEEFTIJDDEELNEMER);
		assertThat(testVerwachteWaardePensioen.getHuidigeLeeftijdDeelnemer())
				.isEqualTo(UPDATED_HUIDIGELEEFTIJDDEELNEMER);
		assertThat(testVerwachteWaardePensioen.getHuidigeWaardeBeleggingen())
				.isEqualTo(UPDATED_HUIDIGEWAARDEBELEGGINGEN);
		assertThat(testVerwachteWaardePensioen.getJaarlijksePremieStorting())
				.isEqualTo(UPDATED_JAARLIJKSEPREMIESTORTING);
		assertThat(testVerwachteWaardePensioen.getJaarlijksRendementBeleggingen())
				.isEqualTo(UPDATED_JAARLIJKSRENDEMENTBELEGGINGEN);

		assertThat(testVerwachteWaardePensioen.getVerwachteWaardePensioenResultaat())
				.isEqualTo(UPDATED_VERWACHTEWAARDEPENSIOENRESULTAAT);

	}

	@Test
	@Transactional
	public void createVerwachteWaardePensioenBerekeningWithExistingId() throws Exception {
		int databaseSizeBeforeCreate = verwachteWaardePensioenBerekeningRepository.findAll().size();

		// Create the Employee with an existing ID
		verwachteWaardePensioenBerekening.setId(1L);

		// An entity with an existing ID cannot be created, so this API call must fail
		mockMvc.perform(post("/api/verwachtewaardepensioenberekeningen").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(verwachteWaardePensioenBerekening)))
				.andExpect(status().isBadRequest());

		// Validate the Employee in the database
		List<VerwachteWaardePensioenBerekening> verwachteWaardePensioenBerekenings = verwachteWaardePensioenBerekeningRepository.findAll();
		assertThat(verwachteWaardePensioenBerekenings).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	public void createVerwachteWaardePensioenBerekeningWithExistingVerwachteWaardePensioenResultaa() throws Exception {
		int databaseSizeBeforeCreate = verwachteWaardePensioenBerekeningRepository.findAll().size();

		// Create the Employee with an existing ID
		verwachteWaardePensioenBerekening.setVerwachteWaardePensioenResultaat(1d);

		// An entity with an existing ID cannot be created, so this API call must fail
		mockMvc.perform(post("/api/verwachtewaardepensioenberekeningen").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(verwachteWaardePensioenBerekening)))
				.andExpect(status().isBadRequest());

		// Validate the Employee in the database
		List<VerwachteWaardePensioenBerekening> verwachteWaardePensioenBerekenings = verwachteWaardePensioenBerekeningRepository.findAll();
		assertThat(verwachteWaardePensioenBerekenings).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	public void getVerwachteWaardePensioenBerekening() throws Exception {
		// Initialize the database
		verwachteWaardePensioenBerekening.setVerwachteWaardePensioenResultaat(DEFAULT_VERWACHTEWAARDEPENSIOENRESULTAAT);
		verwachteWaardePensioenBerekeningRepository.saveAndFlush(verwachteWaardePensioenBerekening);

		// Get the verwachteWaardePensioenBerekening
		mockMvc.perform(get("/api/verwachtewaardepensioenberekeningen/{id}", verwachteWaardePensioenBerekening.getId()))
				.andExpect(status().isOk()).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(verwachteWaardePensioenBerekening.getId().intValue()))
				.andExpect(jsonPath("$.gewenstePensioenLeeftijdDeelnemer")
						.value(DEFAULT_GEWENSTEPENSIOENLEEFTIJDDEELNEMER))
				.andExpect(jsonPath("$.huidigeLeeftijdDeelnemer").value(DEFAULT_HUIDIGELEEFTIJDDEELNEMER))
				.andExpect(jsonPath("$.huidigeWaardeBeleggingen").value(DEFAULT_HUIDIGEWAARDEBELEGGINGEN))
				.andExpect(jsonPath("$.jaarlijksePremieStorting").value(DEFAULT_JAARLIJKSEPREMIESTORTING))
				.andExpect(jsonPath("$.jaarlijksRendementBeleggingen").value(DEFAULT_JAARLIJKSRENDEMENTBELEGGINGEN))
				.andExpect(
						jsonPath("$.verwachteWaardePensioenResultaat").value(DEFAULT_VERWACHTEWAARDEPENSIOENRESULTAAT));

	}

	@Test
	@Transactional
	public void getAllVerwachteWaardePensioenBerekeningen() throws Exception {
		// Initialize the database
		verwachteWaardePensioenBerekening.setVerwachteWaardePensioenResultaat(DEFAULT_VERWACHTEWAARDEPENSIOENRESULTAAT);

		verwachteWaardePensioenBerekeningRepository.saveAndFlush(verwachteWaardePensioenBerekening);

		// Get all the verwachteWaardePensioenList
		mockMvc.perform(get("/api/verwachtewaardepensioenberekeningen")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[*].id").value(verwachteWaardePensioenBerekening.getId().intValue()))
				.andExpect(jsonPath("$[*].gewenstePensioenLeeftijdDeelnemer")
						.value(DEFAULT_GEWENSTEPENSIOENLEEFTIJDDEELNEMER))
				.andExpect(jsonPath("$[*].huidigeLeeftijdDeelnemer").value(DEFAULT_HUIDIGELEEFTIJDDEELNEMER))
				.andExpect(jsonPath("$[*].huidigeWaardeBeleggingen").value(DEFAULT_HUIDIGEWAARDEBELEGGINGEN))
				.andExpect(jsonPath("$[*].jaarlijksePremieStorting").value(DEFAULT_JAARLIJKSEPREMIESTORTING))
				.andExpect(jsonPath("$[*].jaarlijksRendementBeleggingen").value(DEFAULT_JAARLIJKSRENDEMENTBELEGGINGEN))
				.andExpect(jsonPath("$[*].verwachteWaardePensioenResultaat")
						.value(DEFAULT_VERWACHTEWAARDEPENSIOENRESULTAAT));

	}

}
