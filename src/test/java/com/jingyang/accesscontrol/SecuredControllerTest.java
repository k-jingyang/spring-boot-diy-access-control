package com.jingyang.accesscontrol;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

	//TODO withUserDetails

	@WithMockUser(username = "test", roles = {"USER"})
	@Test
	public void testHasAccessToPINFO() throws Exception {
		mvc.perform(get("/api/v1/kmo/p/1")).andExpect(status().isOk());
	}

	@WithMockUser(username = "test", roles = {"USER"})
	@Test
	public void testNoAccessToPINFO() throws Exception {
		mvc.perform(get("/api/v1/kmo/p/1")).andExpect(status().isForbidden());
	}

	@WithMockUser(username = "test", roles = {"USER"})
	@Test
	public void testHasAccessToAINFO() throws Exception {
		mvc.perform(get("/api/v1/kmo/p/1")).andExpect(status().isOk());
	}

	@WithMockUser(username = "test", roles = {"USER"})
	@Test
	public void testNoAccessToAINFO() throws Exception {
		mvc.perform(get("/api/v1/kmo/p/1")).andExpect(status().isForbidden());
	}

}
