package br.com.pereiraeng.core;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import br.com.pereiraeng.core.ColorUtils;

class ColorUtilsTests {

	@Test
	void testInverseColor() {
		Color inverse = ColorUtils.inverse(Color.BLACK);
		assertEquals(inverse, Color.WHITE);
		
		inverse = ColorUtils.inverse(Color.RED);
		assertEquals(inverse, Color.CYAN);
		
		inverse = ColorUtils.inverse(Color.GREEN);
		assertEquals(inverse, Color.MAGENTA);
	}

}
