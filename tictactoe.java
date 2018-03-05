import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Write a description of class tictactoe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class tictactoe implements ActionListener
{   
    
    JButton[][] button; // 3X3 array of buttons
    private int numberOfTurns; // # of turns completed
    private boolean gameDone; // if winner found sets to true
    private BorderLayout border; // Border layout for the content pane
    private JMenuBar menuList;
    private JMenuItem reset;
    private JMenuItem quit;
    private JLabel label;// instance variables - replace the example below with your own
    private int countScore; //score of the players
    private JTextField showScore; // to show the score
    static Icon x = new ImageIcon("X.png");
    static Icon o = new ImageIcon("O.jpg");

    /**
     * Constructor for objects of class tictactoe
     */
    public tictactoe()
    {
       JFrame frame = new JFrame("TicTacToe");
       frame.setSize(600,600);
       
       
       Container content = frame.getContentPane();
       border = new BorderLayout();
       content.setLayout(border);
       
       
       
       button = new JButton[3][3];
        Icon x = new ImageIcon(getClass().getResource("X.png"));
        Icon o = new ImageIcon(getClass().getResource("O.jpg"));
        
        
        
        
       JPanel parameter = new JPanel();
       parameter.setLayout(new GridLayout(3,3));
       
       
       
       
       
   
       
       for(int i=0;i<3;i++) {
         for(int j=0; j<3; j++) {
             button[i][j] = new JButton(); // init the button one by one
             parameter.add(button[i][j]);
             button[i][j].getIcon();
            
             
             
             
             parameter.add(button[i][j]);
             button[i][j].addActionListener(this);
         }
     }
       menuList = new JMenuBar();// Menubar
       frame.setJMenuBar(menuList);
     
       
       content.add(parameter,BorderLayout.CENTER);
       
       JMenu fileMenu = new JMenu("Game Options");
       fileMenu.setFont(new Font("Serif",Font.BOLD,20));
       menuList.add(fileMenu);
       
       reset = new JMenuItem("Reset");
       reset.setFont(new Font("Serif",Font.BOLD,20));
       fileMenu.add(reset);
       
        quit = new JMenuItem("Quit");
        quit.setFont(new Font("Serif",Font.BOLD,20));
        fileMenu.add(quit);
        
     //final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); // to save typing
     //reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, SHORTCUT_MASK));
     //quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
     
     // listen for menu selections
       
       quit.addActionListener(this);
       reset.addActionListener(this);
       
     label = new JLabel();
     label.setText("Player X Begin");
     label.setFont(new Font(null, Font.BOLD, 20));
     content.add(label, BorderLayout.SOUTH); /// change to boxLayout
         
       
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // pressing the red x closes the game      
       frame.pack();
       frame.setResizable(true);
       frame.setVisible(true);
       
       
       
    }

 /**
  * Clears the board to play the game again without having to make a new game.
  * @param row of the board
  * @param column of the board
  */ 
 
 private void hasWinner(int row, int col) {
     if(numberOfTurns > 4) {
           if(button[row][0].getIcon() == (button[row][1].getIcon()) &&
              button[row][1].getIcon() == (button[row][2].getIcon())) {
                gameDone = true;
           }
           else if(button[0][col].getIcon() == (button[1][col].getIcon()) &&
                   button[1][col].getIcon() == (button[2][col].getIcon())) {
                gameDone = true;
           }               
           else if(button[0][0].getIcon() == (button[1][1].getIcon()) &&
                   button[1][1].getText() == (button[2][2].getText())) {
                gameDone = true;
           }
           else if(button[0][2].getIcon() == (button[1][1].getIcon()) &&
                   button[1][1].getIcon() == (button[2][0].getIcon())) {
                gameDone = true; 
           }
     }
  }
 
  public void actionPerformed(ActionEvent event)
   {
       Object object = event.getSource(); // find out whether a menu item or a button was clicked
       if(object instanceof JButton) {
           JButton theButton = (JButton) event.getSource();// find out which button has been pressed
           if(numberOfTurns % 2 == 0) { // Player X always starts the games
               for(int i=0; i<3; i++){                     
                   for(int j=0; j<3; j++) { // use for loops to find the button was pressed
                       if(theButton == button[i][j]) {
                           button[i][j].setIcon(x);
                           button[i][j].setDisabledIcon(x);
                           button[i][j].setEnabled(false);
                           numberOfTurns++;
                           hasWinner(i, j);                            
                           if(gameDone) {
                               disableButtons();
                               label.setText(" Game Over : X Wins");
                           }   
                           else if(numberOfTurns == 9 && !gameDone) {
                               label.setText(" Game Over : Tie");
                           }
                           else {
                               label.setText(" Game In Progress : O Player's Turn");
                           }
                       }
                   }
               }         
           }
           else {
               for(int i=0; i<3; i++){
                   for(int j=0; j<3; j++) {
                       if(theButton == button[i][j]) {
                           button[i][j].setIcon(o);
                           button[i][j].setDisabledIcon(o);
                           button[i][j].setEnabled(false);
                           numberOfTurns++;
                           hasWinner(i, j);             
                           if(gameDone) {
                               disableButtons();
                               label.setText(" Game Over : O Wins");
                           }
                           else if(numberOfTurns == 9 && !gameDone) {
                               label.setText(" Game Over : Tie");
                           }
                           else {
                               label.setText(" Game In Progress : X Player's Turn");
                           }
                       }
                   }
               }        
           }
       }   
       else { // Find out which menuItem has been clicked      
           JMenuItem element = (JMenuItem) object;
           if(element == reset) { // the player chose to reset the game
               clearBoard();            
           }
           if (element == quit) { // the player chose to quit the game
               System.exit(0);            
           }
       }
    }
    /**
  * Clears the board 
  */
 
 private void clearBoard() {
     for(int i=0;i<3;i++) {
         for(int j=0; j<3; j++) {
            button[i][j].setEnabled(true);
            numberOfTurns = 0;
            button[i][j].setIcon(null);
            gameDone = false;
        }
    }
 } 
 
 /**
  *  Disables the buttons after a winner is found;
  */
 private void disableButtons() {
     for(int i=0;i<3;i++) {
         for(int j=0; j<3; j++) {
            button[i][j].setEnabled(false);
        }
    }
 }
}
