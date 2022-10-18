package com.rchomczyk.example

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc

import static com.rchomczyk.example.ExampleApplicationTestData.RESOURCE_BINDING_PATH
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class ExampleApplicationSchemaIntegrationTest {

	private static final String OPENAPI_SCHEMA_ORIGIN_PATH = "/openapi/schema.yml"
	private static final String OPENAPI_SCHEMA_REMOTE_PATH = RESOURCE_BINDING_PATH + OPENAPI_SCHEMA_ORIGIN_PATH
	private final MockMvc mockMvc

	@Autowired
	ExampleApplicationSchemaIntegrationTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc
	}

	@WithMockUser
	@Test
	void 'validate if openapi schema is generated, published with authorization'() {
		'validate if openapi schema is generated, published and expect status code is equal to 200'()
	}

	@WithAnonymousUser
	@Test
	void 'validate if openapi schema is generated, published as an anonymous user'() {
		'validate if openapi schema is generated, published and expect status code is equal to 200'()
	}

	@Test
	void 'validate if openapi schema is generated, published without authorization'() {
		'validate if openapi schema is generated, published and expect status code is equal to 200'()
	}

	private void 'validate if openapi schema is generated, published and expect status code is equal to 200'() {
		mockMvc.perform(get(OPENAPI_SCHEMA_REMOTE_PATH))
			.andDo(print())
			.andExpect(status().isOk())
	}
}
