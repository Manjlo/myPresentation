package myPresentation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    //atributos
    private JButton myPhoto, myHobby, myExpectations;
    private JPanel containerButtons, containerImage;
    private Listener listener;
    private Title title;
    private JLabel imageLabel;
    private JTextArea expectativesText;

    //metodos
    public GUI(){
        initGUI();

        this.setTitle("My Presentation");
        this.setSize(600, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        //Definir container y Layout del JFrame
        //Crear objetos Escucha y Control
        //Configurar JComponents
        title = new Title("My presentation", Color.BLACK);
        myPhoto = new JButton("This is me");
        myHobby = new JButton("This is my hobby");
        myExpectations = new JButton("Who I am");
        containerButtons = new JPanel();
        containerImage = new JPanel();
        listener = new Listener();
        imageLabel = new JLabel();
        expectativesText = new JTextArea(10, 12);

        containerImage.setBorder(BorderFactory.createTitledBorder(null, "About me", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.SANS_SERIF,Font.PLAIN,20), Color.BLACK));
        containerImage.add(imageLabel);

        containerButtons.add(myPhoto);
        containerButtons.add(myHobby);
        containerButtons.add(myExpectations);

        myPhoto.addActionListener(listener);
        myHobby.addActionListener(listener);
        myExpectations.addActionListener(listener);

        this.add(title, BorderLayout.NORTH);
        this.add(containerButtons, BorderLayout.SOUTH);
        this.add(containerImage, BorderLayout.CENTER);

        containerImage.setFocusable(true);
        containerImage.requestFocusInWindow();
        containerImage.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }


            @Override
            public void keyPressed(KeyEvent e) {
                imageLabel.setIcon(null);
                char keyChar = Character.toLowerCase(e.getKeyChar());
                System.out.println(keyChar);
                if (keyChar == 'm') {
                    expectativesText.setText("I am a topographical engineering student and a software development technologist  \n" +
                            "Current, I working as FullStack Developer in React and NodeJs technologies.\n" +
                            "I like play videoGames and watch movies");
                    expectativesText.setBackground(null);
                    expectativesText.setForeground(Color.BLACK);
                    containerImage.add(expectativesText);
                    revalidate();
                    repaint();
                }
            }


            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        containerImage.requestFocus();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI myGui = new GUI();
            }
        });
    }

    private class Listener implements ActionListener{
        private ImageIcon image;
        private int myHobbyClicks;
        @Override
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "Press button");
            imageLabel.setIcon(null);
            containerImage.remove(expectativesText);
            containerImage.requestFocusInWindow();
            if(e.getSource() == myPhoto){
                this.image = new ImageIcon(getClass().getResource("/resources/Me.jpg"));
                imageLabel.setIcon(image);
            }else if(e.getSource() == myHobby){
                myHobbyClicks++;
                        if (myHobbyClicks==2) {
                            this.image = new ImageIcon(getClass().getResource("/resources/Hobby.jpg"));
                            imageLabel.setIcon(image);
                            myHobbyClicks = 0;
                        }
            }
            else if(e.getSource() == myExpectations) {
                expectativesText.setText("I am a topographical engineering student and a software development technologist  \n" +
                        "Current, I working as FullStack Developer in React and NodeJs technologies.\n" +
                        "I like play videoGames and watch movies");
                expectativesText.setBackground(null);
                expectativesText.setForeground(Color.BLACK);
                containerImage.add(expectativesText);
            }
            revalidate();
            repaint();
        }

    }
}
