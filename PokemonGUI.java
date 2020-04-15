import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.NullPointerException;

public class PokemonGUI extends JFrame implements ActionListener {
    private JMenuItem rank, pokemonInBag, exit, low, medium, high, developer, reference;
    private int pressCount = 0, check = 0;
    private String getStringRank;
    private JLabel bg;
    private Container c = getContentPane();
    private JPanel p1;
    private PokemonTrainer obj = new PokemonTrainer();

    public PokemonGUI() {
        super("Pokémon");
        System.out.println("Start PokemonGUI class");
        bg = new JLabel((new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_background.gif")));
        Dimension sizeDefault = new Dimension(1280,720);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_iconbar.png");
        p1 = new JPanel();
        p1.setBorder(BorderFactory.createEmptyBorder(0, 14, 250, 14));
        p1.setLayout(new FlowLayout());
        JButton jb1 = new JButton("Start");
        p1.add(jb1);
        JButton jb2 = new JButton("Exit");
        jb2.setPreferredSize( jb1.getPreferredSize() );
        p1.add(jb2);
        c.add(bg, BorderLayout.CENTER);
        c.add(p1, BorderLayout.SOUTH);
        jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressCount++;
                if(pressCount >= 1){
                    trainerSetName();
                    System.out.println("You press start");
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
        p1.setBackground(Color.WHITE);
        c.setBackground(Color.WHITE);
        setIconImage(icon);
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
        rank = new JMenuItem("Trainer Status");
        rank.addActionListener(this);
        pokemonMenu.add(rank);
        pokemonInBag = new JMenuItem("Pokemon in Bag");
        pokemonInBag.addActionListener(this);
        pokemonMenu.add(pokemonInBag);
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
        int ch = 0;
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
                ch = obj.startCheck(check);
                if(ch >= 1){
                    System.out.println("Check set name complete");
                    ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("1_trainer.gif"));
                    JOptionPane.showMessageDialog(jf,"Username: " + obj.getName() +"\n" + "Level: " + obj.getLevel() +"\n"
                            + "Rank: " + getStringRank + "\n" + "Money: " + obj.getMoney(),"Trainer",JOptionPane.INFORMATION_MESSAGE,icon);
                }
                 else
                     JOptionPane.showMessageDialog(jf,"You must start the game before beginning this.","Warning", JOptionPane.WARNING_MESSAGE);
            }catch (NullPointerException e) {
                System.out.println("Can't loading image"); // If can not find image file.
            }
        }
        else if(src == pokemonInBag) {
            ch = obj.startCheck(check);
            if(ch >= 1)
                System.out.println("Forbidden");
            else
                JOptionPane.showMessageDialog(jf,"You must start the game before beginning this.","Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if(src == exit){
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to exit Pokémon game?",
                    "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
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
                ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("1_developer.png"));
                JOptionPane.showMessageDialog(jf,"SEOULCHA RATMUMAD \nSTUDENT ID: 6110110108 ","Developer",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("Can't loading image icon"); // If can not find image file.
            }
        }
        else if(src == reference){
            try{
                ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("1_reference.png"));
                JOptionPane.showMessageDialog(jf,"240-210 Programing Techniques","Reference",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("There's something wrong."); // If can not find image file.
            }
        }
    }

    public void trainerSetName(){
        JLabel jl = new JLabel("Enter username: ");
        JTextField inputField = new JTextField(25);
        JFrame jf = new JFrame();
        JButton ok = new JButton("OK");
        p1.add(jl);
        p1.add(inputField);
        p1.add(ok);
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputField.getText();
                int lengthNameCheck = s.length();
                System.out.println("Your name length: " + lengthNameCheck);
                if(lengthNameCheck >= 1){
                    System.out.println("You press OK, username: " + s);
                    obj.setName(s);
                    check++;
                    obj.startCheck(check);
                    pressCount++;
                    if(pressCount > 1){
                        jl.setVisible(false);
                        inputField.setVisible(false);
                        ok.setVisible(false);
                        bg.setVisible(false);
                        p1.setVisible(false);
                        randomFirstPokemon();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(jf,"You must enter more than 1 letter.","Warning",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void randomFirstPokemon() {
        pressCount = 0;
        JFrame f = new JFrame("Random Pokémon");
        JButton t1 = new JButton("Random");
        JButton t2 = new JButton("Select");
        JLabel l = new JLabel("Random your first Pokémon");
        t1.setBounds(302,360,100 ,50);
        t2.setBounds(302,360,100,50);
        l.setBounds(122,10,1000,50);
        l.setForeground(Color.BLUE);
        l.setFont(new Font("Courier New" , Font.BOLD,30));
        f.getContentPane().add(l);
        f.getContentPane().add(t1);
        f.getContentPane().add(t2);
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You click Random");
                pressCount++;
                t1.setVisible(false);
                if(pressCount >= 1){
                    t2.setVisible(true);
                    t2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("You click Select");
                        }
                    });
                }
            }
        });
        t2.setVisible(false);
        f.setSize(704,480);
        f.setResizable(false);
        f.getContentPane().setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}