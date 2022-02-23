package visao;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ViewBuscaLargura extends javax.swing.JFrame implements Puzzle {

    public ViewBuscaLargura() {
        initComponents();
        setTitle("Ballsort Puzzle - Busca Largura");
    }

    @Override
    public void toggleView(boolean stage) {
        this.setVisible(stage);
    }
    
    public void setTextPosicaoLabel(String s){
        posicaoLarguraLabel.setText(s);
    }

    public void togglePrimeiroEstado(boolean b) {
        primeiroEstado.setEnabled(b);
        anterior.setEnabled(b);
    }

    public void toggleUltimoEstado(boolean b) {
        ultimoEstado.setEnabled(b);
        proximo.setEnabled(b);
    }

    public void addActionPrimeiroEstado(ActionListener action) {
        primeiroEstado.addActionListener(action);
    }

    public void addActionAnterior(ActionListener action) {
        anterior.addActionListener(action);
    }

    public void addActionProximo(ActionListener action) {
        proximo.addActionListener(action);
    }

    public void addActionUltimoEstado(ActionListener action) {
        ultimoEstado.addActionListener(action);
    }

    @Override
    public void clearMainPanel() {
        for (Component component : painelPrincipal.getComponents()) {
            painelPrincipal.remove(component);
        }
    }

    @Override
    public void addBall(String background, int x, int y) {

        int width = 40;
        int height = 40;

        JLabel label = new JLabel();
        painelPrincipal.add(label);
        label.setText("");
        label.setIcon(new ImageIcon(getClass().getResource("../img/" + background + ".png")));
        label.setBounds(x, y, width, height);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        painelPrincipal = new javax.swing.JPanel();
        primeiroEstado = new javax.swing.JButton();
        proximo = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        ultimoEstado = new javax.swing.JButton();
        posicaoLarguraLabel = new javax.swing.JLabel();

        painelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        painelPrincipal.setMaximumSize(new java.awt.Dimension(560, 160));
        painelPrincipal.setMinimumSize(new java.awt.Dimension(560, 160));
        painelPrincipal.setPreferredSize(new java.awt.Dimension(560, 160));
        painelPrincipal.setRequestFocusEnabled(false);

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        primeiroEstado.setText("|<<");

        proximo.setText(">>");

        anterior.setText("<<");

        ultimoEstado.setText(">>|");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(primeiroEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ultimoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proximo)
                    .addComponent(ultimoEstado)
                    .addComponent(anterior)
                    .addComponent(primeiroEstado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(posicaoLarguraLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(posicaoLarguraLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anterior;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JLabel posicaoLarguraLabel;
    private javax.swing.JButton primeiroEstado;
    private javax.swing.JButton proximo;
    private javax.swing.JButton ultimoEstado;
    // End of variables declaration//GEN-END:variables
}
