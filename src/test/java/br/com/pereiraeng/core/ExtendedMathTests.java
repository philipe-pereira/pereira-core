package br.com.pereiraeng.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExtendedMathTests {

	@Test
	void testFatorial() {
		long result = ExtendedMath.fatorial(0);
		assertEquals(1L, result);
		result = ExtendedMath.fatorial(5);
		assertEquals(120L, result);
	}

	@Test
	void testMDC() {
		assertEquals(1, ExtendedMath.mdc(12, 5));
		assertEquals(15, ExtendedMath.mdc(60, 15));
		assertEquals(9, ExtendedMath.mdc(72, 36, 45));
		assertEquals(1, ExtendedMath.mdc(27, 15, 21, 2));
	}

	@Test
	void testMMC() {
		assertEquals(60, ExtendedMath.mmc(12, 5));
		assertEquals(60, ExtendedMath.mmc(60, 15));
		assertEquals(360, ExtendedMath.mmc(72, 36, 45));
		assertEquals(1890, ExtendedMath.mmc(27, 15, 21, 2));
	}

	@Test
	void testInverseHiperbolic() {
		// reference values from WolframAlpha

		// asinh(1.5) = 1.1947632172871093041119308285190905235361620751530054292706
		assertEquals(1.1947632172871092, ExtendedMath.asinh(1.5), 1e-15);
		// acosh(1.5) = 0.9624236501192068949955178268487368462703686687713210393220
		assertEquals(.9624236501192069, ExtendedMath.acosh(1.5), 1e-15);
		// atanh(0.7) = 0.8673005276940531944271446904753004154703562273814976677812
		assertEquals(.8673005276940532, ExtendedMath.atanh(0.7), 1e-15);
	}

	@Test
	void testGamma() {
		// reference values from WolframAlpha

		// gamme(2.7) = 1.5446858458505937649605937031918458251631279929591960350267
		assertEquals(1.5446858458505939, ExtendedMath.gama(2.7), 1e-14);
		// gamme(pi) = 2.28803779534003241795958890906023392288968815335622244119938
		assertEquals(2.2880377953400326, ExtendedMath.gama(Math.PI), 1e-14);
	}
}