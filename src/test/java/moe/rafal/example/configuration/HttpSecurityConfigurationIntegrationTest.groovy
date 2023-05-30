package moe.rafal.example.configuration

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.session.JdbcSessionDataSourceScriptDatabaseInitializer
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration
import org.springframework.test.web.servlet.MockMvc

import static moe.rafal.example.ExampleApplicationTestData.RESOURCE_BINDING_PATH
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class HttpSecurityConfigurationIntegrationTest {

	private static final String EXAMPLE_RESOURCE_ORIGIN_PATH = "/dist/stylesheets/index.css"
	private static final String EXAMPLE_RESOURCE_REMOTE_PATH = RESOURCE_BINDING_PATH + EXAMPLE_RESOURCE_ORIGIN_PATH

	private final MockMvc mockMvc

	@Autowired
	HttpSecurityConfigurationIntegrationTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc
	}

	@WithMockUser
	@Test
	void 'call resource with authorization'() {
		'call resource and expect status code equal to 200'()
	}

	@WithAnonymousUser
	@Test
	void 'call resource with authorization as an anonymous user'() {
		'call resource and expect status code equal to 200'()
	}

	@Test
	void 'call resource without authorization'() {
		'call resource and expect status code equal to 200'()
	}

	private void 'call resource and expect status code equal to 200'() {
		mockMvc.perform(get(EXAMPLE_RESOURCE_REMOTE_PATH))
			.andDo(print())
			.andExpect(status().isOk())
	}
}
