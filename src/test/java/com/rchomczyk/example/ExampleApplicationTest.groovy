package com.rchomczyk.example

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

import static org.assertj.core.api.Assertions.assertThat

@SpringBootTest
class ExampleApplicationTest {

	private static final int EXPECTED_BEAN_DEFINITIONS_COUNT = 255

	@Test
	void 'validate if context loads'(ApplicationContext context) {
		assertThat(context)
			.isNotNull()
	}

	@Test
	void 'validate if context declares correct amount of bean definitions'(ApplicationContext context) {
		assertThat(context.beanDefinitionCount)
			.isEqualTo(EXPECTED_BEAN_DEFINITIONS_COUNT)
	}
}
