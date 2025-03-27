package org.entdes;

public class TaulerService {
    private static final int MIDA_TAULER = 3;
    
    private String[][] caselles;
    private boolean esTornX = true;
    private boolean gameOver = false;
    private String guanyador = "";

    public TaulerService() {
        this.caselles = new String[MIDA_TAULER][MIDA_TAULER];
        inicialitzarTauler();
    }

    public String tractarClicCasella(int fila, int columna) {
        if (esMovimentValid(fila, columna)) {
            marcarCasella(fila, columna);
            comprovarGuanyador();
            comprovarEmpat();
        }
        return caselles[fila][columna];
    }

    private boolean esMovimentValid(int fila, int columna) {
        return !gameOver && caselles[fila][columna].isEmpty();
    }

    private void marcarCasella(int fila, int columna) {
        caselles[fila][columna] = esTornX ? "X" : "O";
        esTornX = !esTornX;
    }

    private void comprovarGuanyador() {
        comprovarFiles();
        comprovarColumnes();
        comprovarDiagonals();
        
        if (!guanyador.isEmpty()) {
            gameOver = true;
        }
    }

    private void comprovarFiles() {
        for (int i = 0; i < MIDA_TAULER; i++) {
            if (comprovarLineaGuanyadora(caselles[i][0], caselles[i][1], caselles[i][2])) {
                guanyador = caselles[i][0];
                break;
            }
        }
    }

    private void comprovarColumnes() {
        for (int i = 0; i < MIDA_TAULER; i++) {
            if (comprovarLineaGuanyadora(caselles[0][i], caselles[1][i], caselles[2][i])) {
                guanyador = caselles[0][i];
                break;
            }
        }
    }

    private void comprovarDiagonals() {
        if (comprovarLineaGuanyadora(caselles[0][0], caselles[1][1], caselles[2][2])) {
            guanyador = caselles[0][0];
        } else if (comprovarLineaGuanyadora(caselles[2][0], caselles[1][1], caselles[0][2])) {
            guanyador = caselles[2][0];
        }
    }

    private boolean comprovarLineaGuanyadora(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && b.equals(c);
    }

    private void comprovarEmpat() {
        if (!gameOver && isTaulerPle()) {
            gameOver = true;
        }
    }

    private boolean isTaulerPle() {
        for (int fila = 0; fila < MIDA_TAULER; fila++) {
            for (int columna = 0; columna < MIDA_TAULER; columna++) {
                if (caselles[fila][columna].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void inicialitzarTauler() {
        for (int i = 0; i < MIDA_TAULER; i++) {
            for (int j = 0; j < MIDA_TAULER; j++) {
                caselles[i][j] = "";
            }
        }
    }

    public String getGuanyador() {
        return guanyador;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}