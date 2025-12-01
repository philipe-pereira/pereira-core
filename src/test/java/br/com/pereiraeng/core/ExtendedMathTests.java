package br.com.pereiraeng.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.pereiraeng.core.ExtendedMath;

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

	@Test
	void testBessel() {
		// reference values from WolframAlpha

		// J_2(2.5) = 0.4460590584396172267359407998627412276487989956849792160356
		assertEquals(.44605905843961724, ExtendedMath.bessel(2.5, 2), 1e-14);
		// J_1(pi) = 0.2846153431797527573453105996861314057098111818494657275645536469
		assertEquals(.28461534317975273, ExtendedMath.bessel(Math.PI, 1), 1e-14);
	}

	@Test
	void testNeumann() {
		// reference values from WolframAlpha

		// Y_1(2.6) = 0.1883635443501705569913785577384153491131177987983170643564
		assertEquals(.18836354435017055, ExtendedMath.neumann(2.6, 1), 1e-8);
		// Y_2(pi) = -0.099900713929027878773490295584186552327642644463451091964357598
		assertEquals(-.09990071392902788, ExtendedMath.neumann(Math.PI, 2), 1e-8);
		// Y_1.5(e) = -0.036476180508971222560896142261142732139710418534981929625
		assertEquals(-.03647618050897122, ExtendedMath.neumann(Math.E, 1.5), 1e-8);
	}

	@Test
	void testBesselMod() {
		// reference values from WolframAlpha

		// I_2(2.5) = 1.2764661478191642824833548314081538882006437326308347860596
		assertEquals(1.2764661478191643, ExtendedMath.besselI(2.5, 2));
		// I_1(pi) = 4.4914566644303432824576741780780302949504377183855981872619236259
		assertEquals(4.491456664430343, ExtendedMath.besselI(Math.PI, 1), 1e-15);
		// I_1.5(e) = 2.3397563416912744127880314618139712509975288647250224266038
		assertEquals(2.3397563416912743, ExtendedMath.besselI(Math.E, 1.5), 1e-15);
	}

	@Test
	void testBesselModII() {
		// reference values from WolframAlpha

		// K_1(2.6) = .0652840450585314950003324112971424999327180711763751773145
		assertEquals(.0652840450585315, ExtendedMath.besselK(2.6, 1), 1e-8);
		// K_2(pi) = .0510986902537925773954079746737421503596570041337449385438914447
		assertEquals(.05109869025379258, ExtendedMath.besselK(Math.PI, 2), 1e-10);
		// K_1.5(e) = .0686160512560285402500476724118738466733914762985808398072
		assertEquals(.06861605125602854, ExtendedMath.besselK(Math.E, 1.5), 1e-14);
	}

	@Test
	void testBesselKevin() {
		// reference values from WolframAlpha

		// ber_1(e) = -1.537473928411463711249833268784461512905369931579160190103713813
		assertEquals(-1.5374739284114638, ExtendedMath.ber(Math.E, 1), 1e-11);
		// bei_1(e) = -0.154191057958648183528701094436710591051996024934536761173541978
		assertEquals(-.15419105795864818, ExtendedMath.bei(Math.E, 1), 1e-11);
		// ber_0(pi) = -0.45810326859255917953741940635278053464185103783971785757280569
		assertEquals(-.4581032685925592, ExtendedMath.ber(Math.PI, 0), 1e-11);
		// bei_0(pi) = 2.056460506589933479168028915771998935741225842157321997942494456
		assertEquals(2.0564605065899335, ExtendedMath.bei(Math.PI, 0), 1e-11);
	}
}