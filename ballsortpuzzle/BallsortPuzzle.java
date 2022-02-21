package ballsortpuzzle;

import busca.Estado;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import stack.base.ArrayStack;

/*

Cores:

B5E61D - verde fosforescente
FFF200 - amarelo
C3C3C3 - cinza
FF7F27 - laranja
ED1C24 - vermelho
A349A4 - roxo
22B14C - verde claro
00A2E8 - azul claro
FFAEC9 - rosa

B97A57 - marrom
3F48CC - azul escuro
008040 - verde escuro

 */
public class BallsortPuzzle implements Estado {

    private static class Coluna extends ArrayStack {

        public Coluna() {
        }

        public boolean podeEntrar(String bola) {
            return size() == 0 || (size() != 4 && top().equals(bola));
        }

        public boolean apenasUmaCor() {

            if (top() == null) {
                return true;
            }
            
            if (size() == 4) {
                Coluna coluna = new Coluna();
                coluna.push(this.pop());

                for (int i = 0; i < 3; i++) {
                    if (this.top().equals(coluna.top())) {
                        coluna.push(this.pop());
                    } else {
                        this.empilhar(coluna);
                        return false;
                    }
                }

                this.empilhar(coluna);
                return true;
            }
            
            return false;
        }

        public void empilhar(Coluna coluna) {
            int size = coluna.size();
            for (int i = 0; i < size; i++) {
                this.push(coluna.pop());
            }
        }

        public Coluna copiar() {
            Coluna coluna = new Coluna();
            int size = size();
            String bola[] = new String[size];
            for (int i = 0; i < size; i++) {
                bola[i] = (String) pop();
                coluna.push(bola[i]);
            }

            for (int i = size - 1; i >= 0; i--) {
                this.push(bola[i]);
            }

            Coluna colunaFinal = new Coluna();
            colunaFinal.empilhar(coluna);

            return colunaFinal;
        }

    }

    private ArrayList<Coluna> colunas;

    public BallsortPuzzle(String[] bolas) {
        colunas = new ArrayList<Coluna>();
        int colunasLength = 0;
        for (int i = 0; i < bolas.length; i++) {
            if (bolas[i].equals("brk")) {
                colunasLength++;
            }
        }

        for (int i = 0; i < colunasLength + 3; i++) {
            colunas.add(new Coluna());
        }

        int posit = 0;

        for (String r : bolas) {
            if (!r.equals("brk")) {
                colunas.get(posit).push(r);
            } else {
                posit++;
            }
        }
    }

    public BallsortPuzzle(ArrayList<Coluna> colunas) {
        this.colunas = colunas;
    }

    public String returnBalls() {
        Coluna coluna;
        String bolas = "";

        for (int i = 0; i < colunas.size(); i++) {
            coluna = new Coluna();
            int tmn = colunas.get(i).size();
            coluna.empilhar(colunas.get(i));
            for (int j = 0; j < tmn; j++) {
                bolas += coluna.top() + " ";
                colunas.get(i).push(coluna.pop());
            }
            bolas += "brk ";
        }

        return bolas;
    }

    @Override
    public String getDescricao() {
        return null;
    }

    @Override
    public boolean ehMeta() {
        for (Coluna c : colunas) {
            if (!c.apenasUmaCor()) {
                
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int custo() {
        return 1;
    }

    @Override
    public List<BallsortPuzzle> sucessores() {
        List<BallsortPuzzle> suc = new LinkedList<>();

        for (int colunaI = 0; colunaI < colunas.size(); colunaI++) {
            if (colunas.get(colunaI).top() != null) {
                for (int colunaJ = colunas.size() - 1; colunaJ >= 0; colunaJ--) {
                    if (colunaI != colunaJ && colunas.get(colunaJ).podeEntrar((String) colunas.get(colunaI).top())) {
                        if (!colunas.get(colunaI).apenasUmaCor()) {

                            ArrayList<Coluna> colunasNova = new ArrayList<Coluna>();

                            for (Coluna coluna : colunas) {
                                colunasNova.add(coluna.copiar());
                            }

                            colunasNova.get(colunaJ).push(colunasNova.get(colunaI).pop());
                            BallsortPuzzle novo = new BallsortPuzzle(colunasNova);
                            suc.add(novo);
                        }
                    }
                }
            }
        }

        return suc;
    }

    @Override
    public String toString() {
        return returnBalls();
    }

    public ArrayList<Coluna> getColunas() {
        return colunas;
    }
}
