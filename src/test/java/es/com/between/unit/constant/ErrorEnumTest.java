package es.com.between.unit.constant;

import es.com.between.constant.ErrorEnum;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErrorEnumTest {

	@Test
	public void testGetMessage() {
		Assert.assertEquals("error.not.found.price", ErrorEnum.NOT_FOUND_PRICE.getMessage());
	}
}