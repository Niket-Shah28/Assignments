package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {
	Cell cell;
	Board board;
	
	@BeforeEach
	void init() {
		cell = new Cell();
	}

	@Test
	void testGetMark() {
		assertEquals("", cell.getMark());
	}

	@Test
	void testSetMark() {
		cell.setMark("X");
		assertEquals("X", cell.getMark());
		assertThrows(IllegalStateException.class, ()->cell.setMark("O"));
	}

	@Test
	void testGetMarkStatus() {
		assertEquals(false, cell.getMarkStatus());
	}
}
