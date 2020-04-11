import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.NullPointerException;

public class PokemonGUI extends JFrame implements ActionListener {
    private JMenuItem rank, exit, low, medium, high, developer, reference;
    private JTextField jtf;
    private int pressCount = 0;
    private String getStringRank;
    Container c;
    PokemonTrainer obj;
    JLabel background = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\background.gif"));

    public PokemonGUI() {
        super("Pokémon");
        c = getContentPane();
        c.setBackground(Color.WHITE);
        Dimension sizeDefault = new Dimension(1280,720);
        makeMenuBar();
        pokemonStartButton();
        c.setLayout(new FlowLayout()); // Text in the middle
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(sizeDefault);
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
        JFrame f = new JFrame();
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
                JOptionPane.showMessageDialog(f,"Username: " + obj.getName() +"\n" + "Level: " + obj.getLevel() +"\n"
                        + "Rank: " + getStringRank,"Trainer",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("Can't loading image"); // If can not find image file.
            }
        }
        else if(src == exit){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to exit the game?",
                    "Warning",JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(f,"SEOULCHA RATMUMAD \nSTUDENT ID: 6110110108 ","Developer",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("Can't loading image"); // If can not find image file.
            }
        }
        else if(src == reference){
            try{
                ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("reference.png"));
                JOptionPane.showMessageDialog(f,"240-210 Programing Techniques","Reference",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("Can't loading image"); // If can not find image file.
            }
        }
    }

    public void pokemonStartButton() {
        obj = new PokemonTrainer();
        JPanel p = new JPanel(new GridBagLayout());
        background.setLayout(new BorderLayout());
        add(background);
        background.setLayout(new FlowLayout());
        JButton jb1 = new JButton("Start");
        JButton jb2 = new JButton("Exit");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        p.add(jb1);
        gbc.gridx = 0;
        //gbc.g
        p.add(jb2);
        background.add(p);
        //background.add(jb1);
        //background.add(jb2);
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
    }

    public void trainerSetName(){
        JLabel jl = new JLabel("Enter username: ");
        JTextField inputField = new JTextField(25);
        JButton ok = new JButton("OK");
        background.add(jl);
        background.add(inputField);
        background.add(ok);
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
                    background.setVisible(false);
                }
            }
        });
    }

}