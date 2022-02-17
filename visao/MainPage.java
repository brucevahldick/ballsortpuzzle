package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPage extends javax.swing.JFrame implements Puzzle{

    final public JFileChooser fc = new JFileChooser();

    public MainPage() {
        initComponents();
        fc.setCurrentDirectory(new File("C:\\Users\\Bruce Vahldick\\Documents\\NetBeansProjects\\BallSortPuzzle\\src\\puzzles"));
    }

    public void toggleView(boolean stage) {
        this.setVisible(stage);
    }

    public void addActionSelectFile(ActionListener action) {
        selectFile.addActionListener(action);
    }
    
    public void addActionBuscaLargura(ActionListener action){
        buscaLargura.addActionListener(action);
    }
    
    @Override
    public void clearMainPanel(){
        painelPrincipal.removeAll();
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

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        selectFile = new javax.swing.JButton();
        buscaLargura = new javax.swing.JButton();
        buscaProfundidade = new javax.swing.JButton();
        sobre = new javax.swing.JButton();
        painelPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        selectFile.setText("Carregar");

        buscaLargura.setText("Largura");

        buscaProfundidade.setText("Profundidade");

        sobre.setText("Sobre");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(selectFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscaLargura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscaProfundidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sobre)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectFile)
                    .addComponent(buscaLargura)
                    .addComponent(buscaProfundidade)
                    .addComponent(sobre))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscaLargura;
    private javax.swing.JButton buscaProfundidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JButton selectFile;
    private javax.swing.JButton sobre;
    // End of variables declaration//GEN-END:variables
}
