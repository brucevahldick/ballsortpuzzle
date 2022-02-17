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
            for (int i = 0; i < 4; i++) {
                this.push(coluna.pop());
            }
        }

    }

    private ArrayList<Coluna> colunas;

    public BallsortPuzzle(String[] bolas) {
        colunas = new ArrayList<Coluna>();
        for (int i = 0; i < 14; i++) {
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
        Coluna coluna = new Coluna();
        String bolas = "";
        for (int i = 0; i < colunas.size(); i++) {
            for (int j = 0; j < colunas.get(i).size(); j++) {
                bolas += colunas.get(i).top() + "/";
                coluna.push(colunas.get(i).pop());
            }
            bolas += "brk/";
            coluna.empilhar(colunas.get(i));
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
            for (int colunaJ = 0; colunaJ < colunas.size(); colunaJ++) {
                if (colunaI != colunaJ) {
                    if (colunas.get(colunaJ).podeEntrar((String) colunas.get(colunaI).top())) {
                        ArrayList<Coluna> colunasNova = colunas;
                        colunas.get(colunaJ).push((String) colunas.get(colunaI).pop());
                        BallsortPuzzle novo = new BallsortPuzzle(colunasNova);
                        suc.add(novo);
                    }
                }
            }
        }

        return suc;
    }

    @Override
    public String toString() {
        return "BallsortPuzzle{" + "colunas=" + colunas + '}';
    }

}
