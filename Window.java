package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Window extends javax.swing.JFrame {
    private final JButton[] keyboardkeys;
    private final JTextField guesses;
    private final JButton newgamebtn;
    private final JTextField gameword;
    private final Panel gamearea;
    private final JPanel keyboardpanel;
    private final JPanel northpanel;
    private int chances = 5;
    private int status = 0;
    private boolean gameover = true;
    Font myFont = new Font("Helvetica", 1, 50);
    
    private class OnClick implements ActionListener{
         private OnClick(){
        }
        @Override
        public void actionPerformed(ActionEvent var1){
            JButton button = (JButton)var1.getSource();
            if(button == Window.this.newgamebtn){
                JButton[] buttonArray = Window.this.keyboardkeys;
                int k = buttonArray.length;
                int i;
                for(i = 0; i<k; i++){
                    JButton var6 = buttonArray[i];
                    var6.setEnabled(true);
                }
                String words = Word.getWord();
                Window.this.gameword.setText(words);
                StringBuilder guesstxt = new StringBuilder();
                Random random = new Random();
                for(i=0;i<words.length();i++){
                    if(random.nextBoolean()){
                        guesstxt.append(words.charAt(i));
                    }
                    else{
                        guesstxt.append(".");
                    }
                }
                Window.this.guesses.setText(guesstxt.toString());
                Window.this.chances = 6;
                Window.this.status = 0;
                Window.this.gameover = false;
            }
            else{
                button.setEnabled(false);
                char guesschar = button.getText().charAt(0);
                char[] userchar = Window.this.guesses.getText().toCharArray();
                String text = Window.this.gameword.getText();
                boolean match = false;
                for(int i=0;i<text.length();i++){
                    if(text.charAt(i) == guesschar){
                        userchar[i] = guesschar;
                        match = true;
                    }
                }
                if(match){
                    Window.this.guesses.setText(new String(userchar));
                    if(!Window.this.guesses.getText().contains(".")){
                        Window.this.status = 1;
                        Window.this.gameover = true;
                    }
                    
                }
                else{
                    --Window.this.chances;
                    if(Window.this.chances == 0){
                        Window.this.status = 2;
                        Window.this.gameover = true;
                    }
                }
            }
            Window.this.repaint();
        }
    }
    
    private class Panel extends JPanel{

        private Panel(){
            setBackground(new Color(255,255,204));
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            if(!Window.this.gameover){
                g.setColor(Color.BLACK);
                g.setFont(new Font("Helvetica", 1, 20));
                if(Window.this.chances == 6){
                    g.drawString("6 chances left", 365, 250);
                    g2.setStroke(new BasicStroke(4));
                    g.drawLine(150, 100, 150, 400);
                    g.drawLine(120,400,180,400);
                    g.drawLine(150, 100, 300, 100);
                    g.drawLine(300, 100, 300, 150);
                }
                else if(Window.this.chances == 5){
                    g.drawString("5 chances left", 365, 250);
                    g2.setStroke(new BasicStroke(4));
                    g.drawLine(150, 100, 150, 400);
                    g.drawLine(120,400,180,400);
                    g.drawLine(150, 100, 300, 100);
                    g.drawLine(300, 100, 300, 150);
                    g.drawOval(275, 150, 50, 50);
                }
                else if(Window.this.chances == 4){
                    g.drawString("4 chances left", 365, 250);
                    g2.setStroke(new BasicStroke(4));
                    g.drawLine(150, 100, 150, 400);
                    g.drawLine(120,400,180,400);
                    g.drawLine(150, 100, 300, 100);
                    g.drawLine(300, 100, 300, 150);
                    g.drawOval(275, 150, 50, 50);
                    g.drawLine(300, 200, 300, 300);
                }
                else if(Window.this.chances == 3){
                    g.drawString("3 chances left", 365, 250);
                    g2.setStroke(new BasicStroke(4));
                    g.drawLine(150, 100, 150, 400);
                    g.drawLine(120,400,180,400);
                    g.drawLine(150, 100, 300, 100);
                    g.drawLine(300, 100, 300, 150);
                    g.drawOval(275, 150, 50, 50);
                    g.drawLine(300, 200, 300, 300);
                    g.drawLine(250, 240, 300, 200);
                }
                else if(Window.this.chances == 2){
                    g.drawString("2 chances left", 365, 250);
                    g2.setStroke(new BasicStroke(4));
                    g.drawLine(150, 100, 150, 400);
                    g.drawLine(120,400,180,400);
                    g.drawLine(150, 100, 300, 100);
                    g.drawLine(300, 100, 300, 150);
                    g.drawOval(275, 150, 50, 50);
                    g.drawLine(300, 200, 300, 300);
                    g.drawLine(250, 240, 300, 200);
                    g.drawLine(350, 240, 300, 200);
                }
                else if(Window.this.chances == 1){
                    g.drawString("1 chances left", 365, 250);
                    g2.setStroke(new BasicStroke(4));
                    g.drawLine(150, 100, 150, 400);
                    g.drawLine(120,400,180,400);
                    g.drawLine(150, 100, 300, 100);
                    g.drawLine(300, 100, 300, 150);
                    g.drawOval(275, 150, 50, 50);
                    g.drawLine(300, 200, 300, 300);
                    g.drawLine(250, 240, 300, 200);
                    g.drawLine(350, 240, 300, 200);
                    g.drawLine(250, 350, 300, 300);
                }
            }
            else{
                g.setColor(Color.red);
                g.setFont(new Font("Helvetica", 1, 20));
                g.drawString("Press New to start", 180, 460);
            }
            
            JButton[] buttonArray;
            switch(Window.this.status){
                case 1 -> {
                    g.setColor(Color.BLACK);
                    g2.setStroke(new BasicStroke(4));
                    g.drawLine(150, 100, 150, 400);
                    g.drawLine(120,400,180,400);
                    g.drawLine(150, 100, 300, 100);
                    g.drawLine(300, 100, 300, 150);
                    g.drawOval(275, 200, 50, 50);
                    g.drawLine(300, 250, 300, 350);
                    g.drawLine(250, 240, 300, 300);
                    g.drawLine(350, 240, 300, 300);    
                    g.drawLine(250, 400, 300, 350);
                    g.drawLine(350, 400, 300, 350);
                    g.setColor(Color.red);
                    g.drawString("Congrats you win!", 180, 55);
                    buttonArray = Window.this.keyboardkeys;
                }
                case 2 -> {
                    g.setColor(Color.BLACK);
                    g2.setStroke(new BasicStroke(4));
                    g.drawLine(150, 100, 150, 400);
                    g.drawLine(120,400,180,400);
                    g.drawLine(150, 100, 300, 100);
                    g.drawLine(300, 100, 300, 150);
                    g.drawOval(275, 150, 50, 50);
                    g.drawLine(300, 200, 300, 300);
                    g.drawLine(250, 240, 300, 200);
                    g.drawLine(350, 240, 300, 200);    
                    g.drawLine(250, 350, 300, 300);
                    g.drawLine(350, 350, 300, 300);
                    g.setColor(Color.blue);
                    g.drawString("You lose :(", 215, 45);
                    g.drawString("The word was: " + Window.this.gameword.getText(), 155, 73);
                }      
            }
        }
    }
    
    public Window() {
        Container contentPane = this.getContentPane();
        this.keyboardpanel = new JPanel();
        this.keyboardpanel.setLayout(new GridLayout(3,9));
        this.keyboardkeys = new JButton[26];
        OnClick action = new OnClick();
        int k = 0;
        for(char i = 'a'; i <= 'z'; i++){
            this.keyboardkeys[k] = new JButton(String.valueOf(i));
            this.keyboardkeys[k].addActionListener(action);
            this.keyboardkeys[k].setEnabled(false);
            this.keyboardkeys[k].setFocusable(true);
            this.keyboardpanel.add(this.keyboardkeys[k]);
            k++;
        }
        
        this.newgamebtn = new JButton("New");
        this.newgamebtn.addActionListener(action);
        this.keyboardpanel.add(this.newgamebtn);
        this.gameword = new JTextField();
        this.gameword.setForeground(Color.red);
        this.gameword.setEditable(false);
        this.guesses = new JTextField();
        this.guesses.setEditable(false);
        this.guesses.setFont(myFont);
        this.guesses.setHorizontalAlignment(JTextField.CENTER);
        this.northpanel = new JPanel();
        this.northpanel.setLayout(new GridLayout(1,1));
        this.northpanel.setPreferredSize(new Dimension(500,100));
//        this.northpanel.add(this.gameword);
        this.northpanel.add(this.guesses);
        this.gamearea = new Panel();
        this.gamearea.setPreferredSize(new Dimension(500,500));
        contentPane.add(this.northpanel, "North");
        contentPane.add(this.keyboardpanel, "South");
        contentPane.add(this.gamearea, "Center");
        this.setTitle("Hangman");
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
