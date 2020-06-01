package com.jingyang.accesscontrol;

import com.jingyang.accesscontrol.domain.Team;
import com.jingyang.accesscontrol.domain.UserInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredControllerTest {

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private SecurityController securityController;

	private MockMvc mvc;

	@BeforeAll
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	// These tests verifies the integration with OPA (i.e. correct information is sent to OPA and OPA's response is interpreted correctly)
	// Further policy testing to be done on OPA

	@Test
	public void testHasAccessToPINFO() throws Exception {
		UserInfo aquaGalacticGrunt = UserInfo.builder()
				.teams(Arrays.asList(
						Team.mockTeamAqua(), Team.mockTeamGalactic()
				))
				.roles(Arrays.asList(
						"USER"
				)).build();
		mvc.perform(get("/api/v1/p/1").with(user(aquaGalacticGrunt))).andExpect(status().isOk());
	}

	@Test
	public void testNoAccessToPINFO() throws Exception {
		UserInfo aquaGrunt = UserInfo.builder()
				.teams(Arrays.asList(
						Team.mockTeamAqua()
				))
				.roles(Arrays.asList(
						"USER"
				)).build();
		mvc.perform(get("/api/v1/p/2").with(user(aquaGrunt))).andExpect(status().isForbidden());
	}

	@Test
	public void testHasAccessToAINFO() throws Exception {
		mvc.perform(get("/api/v1/p/1")).andExpect(status().isOk());
	}

	@Test
	public void testNoAccessToAINFO() throws Exception {
		mvc.perform(get("/api/v1/p/1")).andExpect(status().isForbidden());
	}

	// Why use RequestPostProcessor? Because we can mock the user object without retrieving it using a UserDetailsService
}
