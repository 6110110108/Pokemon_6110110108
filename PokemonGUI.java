import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.NullPointerException;

public class PokemonGUI extends JFrame implements ActionListener {
    private JMenuItem rank, exit, low, medium, high, developer, reference;
    private int pressCount = 0;
    private String getStringRank;
    private JLabel bg;
    private JPanel p1;
    private PokemonTrainer obj;

    public PokemonGUI() {
        super("Pokémon");
        Container c = getContentPane();
        obj = new PokemonTrainer();
        bg = new JLabel((new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\background.gif")));
        Dimension sizeDefault = new Dimension(1280,720);
        p1 = new JPanel();
        p1.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        p1.setLayout(new FlowLayout());
        JButton jb1 = new JButton("Start");
        p1.add(Box.createRigidArea(new Dimension(0, 10)));
        p1.add(jb1);
        p1.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton jb2 = new JButton("Exit");
        jb2.setPreferredSize( jb1.getPreferredSize() );
        p1.add(jb2);
        c.add(bg, BorderLayout.CENTER);
        c.add(p1, BorderLayout.WEST);
        jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressCount++;
                if(pressCount >= 1){
                    trainerSetName();
                    System.out.println("You press OK");
                    jb1.setVisible(false);
                    jb2.setVisible(false);
                }
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
        p1.setBackground(Color.orange);
        c.setBackground(Color.WHITE);
        makeMenuBar();
        setSize(sizeDefault);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void makeMenuBar() {
        // Make menu bar
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu pokemonMenu = new JMenu("Pokémon");
        menubar.add(pokemonMenu);
        JMenu displayResolution = new JMenu("Resolution");
        menubar.add(displayResolution);
        JMenu credit = new JMenu("Credit");
        menubar.add(credit);
        // Make menu item
        rank = new JMenuItem("Trainer");
        rank.addActionListener(this);
        pokemonMenu.add(rank);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        pokemonMenu.add(exit);
        low =  new JMenuItem("Low");
        low.addActionListener(this);
        displayResolution.add(low);
        medium =  new JMenuItem("Medium - Default");
        medium.addActionListener(this);
        displayResolution.add(medium);
        high =  new JMenuItem("High");
        high.addActionListener(this);
        displayResolution.add(high);
        developer = new JMenuItem("Developer");
        developer.addActionListener(this);
        credit.add(developer);
        reference = new JMenuItem("Reference");
        reference.addActionListener(this);
        credit.add(reference);


    }

    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        JFrame jf = new JFrame();
        if(src == rank){
            try{
                if(obj.getRank() == 1){
                    getStringRank = "Bronze";
                }
                else if(obj.getRank() == 2){
                    getStringRank = "Silver";
                }
                else if(obj.getRank() == 3){
                    getStringRank = "Gold";
                }
                else if(obj.getRank() == 4){
                    getStringRank = "Diamond";
                }
                else if(obj.getRank() == 5){
                    getStringRank = "Elit";
                }
                else if(obj.getRank() == 6){
                    getStringRank = "Master";
                }
                ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("trainer.gif"));
                JOptionPane.showMessageDialog(jf,"Username: " + obj.getName() +"\n" + "Level: " + obj.getLevel() +"\n"
                        + "Rank: " + getStringRank,"Trainer",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("Can't loading image"); // If can not find image file.
            }
        }
        else if(src == exit){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to exit the game?",
                    "Warning", JOptionPane.OK_CANCEL_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        else if(src == low)
            setSize(704, 480);
        else if(src == medium)
            setSize(1280,720);
        else if(src == high)
            setExtendedState(MAXIMIZED_BOTH);
        else if(src == developer){
            try{
                ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("developer.png"));
                JOptionPane.showMessageDialog(jf,"SEOULCHA RATMUMAD \nSTUDENT ID: 6110110108 ","Developer",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("Can't loading image"); // If can not find image file.
            }
        }
        else if(src == reference){
            try{
                ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("reference.png"));
                JOptionPane.showMessageDialog(jf,"240-210 Programing Techniques","Reference",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("There's something wrong."); // If can not find image file.
            }
        }
    }

    public void trainerSetName(){
        JLabel jl = new JLabel("Enter username: ");
        JTextField inputField = new JTextField(25);
        JButton ok = new JButton("OK");
        p1.add(jl);
        p1.add(inputField);
        p1.add(ok);
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputField.getText();
                System.out.println("You press OK, username: " + s);
                obj.setName(s);
                pressCount++;
                if(pressCount > 1){
                    jl.setVisible(false);
                    inputField.setVisible(false);
                    ok.setVisible(false);
                    bg.setVisible(false);
                    p1.setVisible(false);
                }
            }
        });
    }
}