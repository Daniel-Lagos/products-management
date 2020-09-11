package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import model.Handling;
import model.Hardware;

public class HandlingTest {
	
	private Handling handling;

	public void setupOne() {
		handling=new Handling(Hardware.compIdHardware);
		handling.addHardware("HW652", "Nvidia","Tarjeta Grafica",250_000.0, (short)15,Calendar.getInstance(), "Nueva");
		handling.addHardware("HW023", "USB","Kingston",20_000.0, (short)20,Calendar.getInstance(), " Desde Enero");
		handling.addHardware("HW123", "Disco Externo ","Toshiba",145_000.0, (short)6,Calendar.getInstance(), "ENERO	");
	}
	
	@Test
	public void testFind(){
		setupOne();
		assertEquals(2,handling.findHardware("HW652"));
		assertEquals(0,handling.findHardware("HW023"));
		assertEquals(1,handling.findHardware("HW123"));
	}
	
	@Test
	public void testAddHardware(){
		setupOne();
		assertTrue(handling.addHardware("HW5552", "Nvidia","Tarjeta Grafica",250_000.0, (short)15,Calendar.getInstance(), "Nueva"));
		assertFalse(handling.addHardware("HW652", "Nvidia","Tarjeta Grafica",250_000.0, (short)15,Calendar.getInstance(), "Nueva"));
	}
	
	@Test
	public void testGetHardware(){
		setupOne();
		assertEquals("HW123",handling.getHardware(handling.findHardware("HW123")).getIdHardware());
	}

}
