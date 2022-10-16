package com.rchomczyk.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExampleApplicationTests {

	@Test
	void validateIfContextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

	@Test
	void validateIfContextLoadedAllBeanDefinitions(ApplicationContext context) {
		assertThat(context.getBeanDefinitionCount()).isEqualTo(226);
	}
}
