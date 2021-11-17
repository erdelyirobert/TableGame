package bead2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class JBoard {
    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;
    private String player = "piros";

    private int redPoints = 0;
    private int bluePoints = 0;
    Border border_blue = new LineBorder(Color.blue, 10, true);
    Border border_red = new LineBorder(Color.red, 10, true);

    public JBoard(int x, int y) {
        boardPanel = new JPanel();
        boardPanel.setBorder(border_red);
        board = new Board(x, y);
        boardPanel.setLayout(new GridLayout(board.getSizeOfBoard(), board.getSizeOfBoard()));
        buttons = new JButton[board.getSizeOfBoard()][board.getSizeOfBoard()];
        for (int i = 0; i < board.getSizeOfBoard(); ++i) {
            for (int j = 0; j < board.getSizeOfBoard(); ++j) {
                buttons[i][j] = new JButton("0");
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                buttons[i][j].setPreferredSize(new Dimension(80, 80));
                buttons[i][j].setBackground(Color.white);
                boardPanel.add(buttons[i][j]);
            }
        }
    }

    public boolean isEnd() {                                    //ha nem az összes mező értéke 4, akkor nincs vége a játéknak, különben vége van
        boolean l = false;
        for (int i = 0; i < board.getSizeOfBoard(); i++) {
            for (int j = 0; j < board.getSizeOfBoard(); ++j) {
                l = (Integer.parseInt(buttons[i][j].getText()) != 4);
                if (l)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void new_game() {                                            //ha új játékot szeretnénk kezdeni, akkor az összes mező háttere legyen fehér és az értékük 0
        for (int i = 0; i < board.getSizeOfBoard(); i++) {                  //továbbá az eddig megszerzett pontokat is nullázzuk le
            for (int j = 0; j < board.getSizeOfBoard(); ++j) {
                buttons[i][j].setText(String.valueOf(0));
                buttons[i][j].setBackground(Color.white);

            }
        }
        redPoints = 0;
        bluePoints = 0;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    class ButtonListener implements ActionListener {

        private int x;
        private int y;


        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void increase(int x, int y) {                    //ha az aktuális mezőérték kisebb, mint 4, akkor lehet csak növelni, továbbá ha növelés után 4 lett az érték
                                                            //az aktuális játékos színe szerint szinezzük a hátteret(ha a piros jétkos növelte 4-esre akkor piros, különben kék)
            int eredeti_ertek = Integer.parseInt(buttons[x][y].getText());

            if (eredeti_ertek < 4) {
                eredeti_ertek++;
                buttons[x][y].setText(String.valueOf(eredeti_ertek));
                if (eredeti_ertek == 4) {
                    if (player.equals("piros")) {
                        redPoints++;
                        buttons[x][y].setBackground(Color.red);
                    } else {
                        bluePoints++;
                        buttons[x][y].setBackground(Color.blue);

                    }
                }
            }
        }
        public String changePlayer(String p){       //ha a piros az aktuális játékos, akkor színezzük a panel széleit priosra,ezzel jelezve, hogy ki következik
            if(p.equals("piros")) {                 // ezután térjünk vissza a következő játékos színével
                boardPanel.setBorder(border_blue);

                return "kék";
            } else {
                boardPanel.setBorder(border_red);
                return "piros";
            }
        }

        public void gameLogic(int x, int y){           // növeljük a az aktuális elemet és a szomszédait, lekezelve azokat az eseteket, amik a tábla szélén helyezkednek el
            int boardsize = board.getSizeOfBoard()-1;

            increase(x,y);

            if(x<boardsize){
                increase(x+1, y);
            }
            if(x>0){
                increase(x-1, y);
            }

            if(y<boardsize){
                increase(x, y+1);
            }
            if(y>0){
                increase(x, y-1);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            if(!buttons[x][y].getText().equals("4")) {
                gameLogic(x,y);
                player = changePlayer(player);
            }

            if(isEnd()) {
            JFrame champion = new JFrame("Nyertes");
            JOptionPane.showMessageDialog(champion, "A nyertes " + (redPoints > bluePoints? "piros: " + redPoints + " - " + bluePoints: "kék: " + bluePoints + " - " + redPoints ));
            new_game();
            }

        }
    }
}



