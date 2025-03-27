package org.entdes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaulerServiceTest {

    private TaulerService taulerService;

    @BeforeEach
    void setUp() {
        taulerService = new TaulerService();
    }

    @Test
    void testComprovarGuanyadorX() {
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(0, 1); // O
        taulerService.tractarClicCasella(0, 2); // X
        taulerService.tractarClicCasella(1, 1); // O
        taulerService.tractarClicCasella(1, 0); // X
        taulerService.tractarClicCasella(1, 2); // O
        taulerService.tractarClicCasella(2, 0); // X

        assertTrue(taulerService.isGameOver());
        assertEquals("X", taulerService.getGuanyador());
    }

    @Test
    void testComprovarGuanyadorO() {
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(1, 0); // O
        taulerService.tractarClicCasella(0, 1); // X
        taulerService.tractarClicCasella(1, 1); // O
        taulerService.tractarClicCasella(2, 2); // X
        taulerService.tractarClicCasella(1, 2); // O

        assertTrue(taulerService.isGameOver());
        assertEquals("O", taulerService.getGuanyador());
    }

    @Test
    void testGuanyadorColumna() {
        // Victòria de "O" en la columna 0
        taulerService.tractarClicCasella(0, 1); // X
        taulerService.tractarClicCasella(0, 0); // O
        taulerService.tractarClicCasella(1, 1); // X
        taulerService.tractarClicCasella(1, 0); // O
        taulerService.tractarClicCasella(2, 2); // X
        taulerService.tractarClicCasella(2, 0); // O

        assertTrue(taulerService.isGameOver());
        assertEquals("O", taulerService.getGuanyador());
    }

    @Test
    void testGuanyadorDiagonalPrincipal() {
        // Victòria de "X" en la diagonal principal (0,0 -> 1,1 -> 2,2)
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(0, 1); // O
        taulerService.tractarClicCasella(1, 1); // X
        taulerService.tractarClicCasella(1, 0); // O
        taulerService.tractarClicCasella(2, 2); // X

        assertTrue(taulerService.isGameOver());
        assertEquals("X", taulerService.getGuanyador());
    }

    @Test
    void testGuanyadorDiagonalSecundaria() {
        // Victòria de "O" en la diagonal secundària (0,2 -> 1,1 -> 2,0)
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(0, 2); // O
        taulerService.tractarClicCasella(1, 0); // X
        taulerService.tractarClicCasella(1, 1); // O
        taulerService.tractarClicCasella(2, 1); // X
        taulerService.tractarClicCasella(2, 0); // O

        assertTrue(taulerService.isGameOver());
        assertEquals("O", taulerService.getGuanyador());
    }

    @Test
    void testComprovarEmpat() {
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(0, 1); // O
        taulerService.tractarClicCasella(0, 2); // X
        taulerService.tractarClicCasella(1, 0); // O
        taulerService.tractarClicCasella(1, 1); // X
        taulerService.tractarClicCasella(1, 2); // O
        taulerService.tractarClicCasella(2, 0); // X
        taulerService.tractarClicCasella(2, 1); // O
        taulerService.tractarClicCasella(2, 2); // X

        assertTrue(taulerService.isGameOver());
        assertNotNull(taulerService.getGuanyador());
    }

    @Test
    void testCasellaOcupada() {
        taulerService.tractarClicCasella(0, 0); // X
        String result = taulerService.tractarClicCasella(0, 0); // Intentar marcar la mateixa casella
        assertEquals("X", result); // La casella ja està ocupada per X
    }
}