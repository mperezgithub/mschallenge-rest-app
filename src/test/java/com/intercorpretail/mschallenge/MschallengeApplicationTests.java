package com.intercorpretail.mschallenge;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.AssertTrue;

@SpringBootTest
class MschallengeApplicationTests {
	@MockBean
	ModelMapper modelMapper;
	@Test
	void contextLoads() {
		Assert.assertTrue(true);
	}
}
