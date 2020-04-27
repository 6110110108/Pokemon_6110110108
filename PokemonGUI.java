import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.NullPointerException;
import java.text.DecimalFormat;
import java.util.Random;

public class PokemonGUI extends JFrame implements ActionListener {
    private JMenuItem rank, item, exit, medium, high, developer, reference;
    private int pressCount = 0, check = 0, resultDmg = 0, levelTrainer = 1, levelCheck = 0, hpDrop = 5, atkDrop = 5;
    private String getStringRank;
    private JLabel bg;
    private JPanel p1;
    private Container c;
    public PokemonTrainer obj = new PokemonTrainer();

    public PokemonGUI() {
        super("Pokémon");
        System.out.println("Start PokemonGUI class");
        obj.setAtkDrop(atkDrop);
        obj.setHpDrop(hpDrop);
        bg = new JLabel((new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_background.gif")));
        Dimension sizeDefault = new Dimension(1290,722);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_iconbar.png");
        p1 = new JPanel();
        p1.setBorder(BorderFactory.createEmptyBorder(0, 14, 250, 14));
        p1.setLayout(new FlowLayout());
        JButton jb1 = new JButton("Start");
        p1.add(jb1);
        JButton jb2 = new JButton("Exit");
        jb2.setPreferredSize( jb1.getPreferredSize() );
        p1.add(jb2);
        c = getContentPane();
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
        item = new JMenuItem("Item in Bag");
        item.addActionListener(this);
        pokemonMenu.add(item);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        pokemonMenu.add(exit);
        medium =  new JMenuItem("Medium - Recommend");
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

    public void setRankToString() {
        obj.setRank(resultDmg);
        if(obj.getRank() == 1){
            getStringRank = "Bronze";
        }
        if(obj.getRank() == 2){
            getStringRank = "Silver";
        }
        if(obj.getRank() == 3){
            getStringRank = "Gold";
        }
        if(obj.getRank() == 4){
            getStringRank = "Diamond";
        }
        if(obj.getRank() == 5){
            getStringRank = "Elite";
        }
        if(obj.getRank() == 6){
            getStringRank = "Master";
        }
        if(obj.getRank() == 7){
            getStringRank = "Conqueror";
        }
    }

    public void setLevelTrainer() {
        if(levelCheck >= levelTrainer) {
            levelTrainer = levelCheck;
            obj.setLevel(levelTrainer);
        }
    }

    public void attackWin() {
        //-------------------------------- GET ITEM -----------------------------
        JLabel itd = new JLabel();
        itd.setFont(new Font("Courier New" , Font.BOLD,20));
        double rand = Math.random();
        rand = rand * 100;
        String randStr  = new DecimalFormat("0.00").format(rand);
        System.out.println("Percent of item will be get = " + randStr + " %");
        obj.itemDrop(rand);
        hpDrop = obj.getHpDrop();
        atkDrop = obj.getAtkDrop();
        if(rand >= 0 && rand < 30) {
            itd.setText("No item drop");
        }
        if(rand >= 30 && rand < 55.0) {
            itd.setText("You got: HP Booster");
        }
        if(rand >= 55.0 && rand < 80.0) {
            itd.setText("You got: Attack Booster");
        }
        if(rand >= 80.0) {
            itd.setText("You got: Attack and HP Booster");
        }
        //-------------------------------- GET ITEM ------------------------------
        JFrame jfl = new JFrame("Attack result");
        JPanel jpl = new JPanel();
        JLabel jll = new JLabel("You Win ! ");
        JButton okl = new JButton("OK");
        jpl.setLayout(new FlowLayout());
        jll.setFont(new Font("Courier New" , Font.BOLD,30));
        jpl.add(jll);
        jpl.add(okl);
        jpl.add(itd); //-------------------------- GET ITEM
        jfl.add(jpl);
        okl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfl.setVisible(false);
            }
        });
        jpl.setBackground(Color.GREEN);
        jfl.setSize(400,110);
        jfl.setLocationRelativeTo(null);
        jfl.setResizable(false);
        jfl.setVisible(true);
    }

    public void attackLose() {
        JFrame jfl = new JFrame("Attack result");
        JPanel jpl = new JPanel();
        JLabel jll = new JLabel("You lose ! ");
        JButton okl = new JButton("OK");
        jpl.setLayout(new FlowLayout());
        jll.setFont(new Font("Courier New" , Font.BOLD,30));
        jpl.add(jll);
        jpl.add(okl);
        jfl.add(jpl);
        okl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfl.setVisible(false);
            }
        });
        jpl.setBackground(Color.RED);
        jfl.setSize(300,80);
        jfl.setLocationRelativeTo(null);
        jfl.setResizable(false);
        jfl.setVisible(true);
    }

    public void itemDrop() {
        JFrame ftd = new JFrame("Item in Bag");
        JPanel ptd1 = new JPanel();
        JPanel ptd2 = new JPanel();
        JLabel hpic = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_HP_icon.png"));
        JLabel atkic = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Attack_icon.png"));
        JLabel lhp1 = new JLabel("HP Booster.");
        JLabel latk1 = new JLabel("Attack Booster.");
        lhp1.setFont(new Font("Courier New" , Font.BOLD,30));
        latk1.setFont(new Font("Courier New" , Font.BOLD,30));
        JLabel lhp2 = new JLabel();
        JLabel latk2 = new JLabel();
        lhp2.setText("You have: " + hpDrop);
        latk2.setText("You have: " + atkDrop);
        lhp2.setFont(new Font("Courier New" , Font.BOLD,20));
        latk2.setFont(new Font("Courier New" , Font.BOLD,20));

        lhp1.setBounds(130,25,300,50);
        lhp2.setBounds(130,65,300,50);

        latk1.setBounds(130,165,300,50);
        latk2.setBounds(130,205,300,50);

        hpic.setBounds(20,25,100,100);
        atkic.setBounds(20,165,100,100);

        ptd1.setBounds(10,10,447,130);
        ptd2.setBounds(10,150,447,130);

        ftd.add(lhp2);
        ftd.add(lhp1);
        ftd.add(latk1);
        ftd.add(latk2);
        ftd.add(hpic);
        ftd.add(atkic);
        ftd.getContentPane().add(ptd1);
        ftd.getContentPane().add(ptd2);
        ptd1.setBackground(Color.LIGHT_GRAY);
        ptd2.setBackground(Color.LIGHT_GRAY);
        ftd.getContentPane().setBackground(Color.DARK_GRAY);
        ftd.setLayout(null);
        ftd.setSize(480,330);
        ftd.setLocationRelativeTo(null);
        ftd.setResizable(false);
        ftd.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        int ch = 0;
        Object src = event.getSource();
        JFrame jf = new JFrame();
        if(src == rank){
            try{
                setRankToString();
                setLevelTrainer();
                ch = obj.startCheck(check);
                if(ch >= 1){
                    ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("1_trainer.gif"));
                    JOptionPane.showMessageDialog(jf,"Username: " + obj.getName() +"\n" + "Level: " + obj.getLevel() +"\n"
                            + "Rank: " + getStringRank,"Trainer",JOptionPane.INFORMATION_MESSAGE,icon);
                }
                 else
                     JOptionPane.showMessageDialog(jf,"You must start the game before beginning this.","Warning", JOptionPane.WARNING_MESSAGE);
            }catch (NullPointerException e) {
                System.out.println("There's something wrong.");
                JOptionPane.showMessageDialog(jf,"There's something wrong.","Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(src == item) {
            ch = obj.startCheck(check);
            if(ch >= 1)
                itemDrop();
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
        else if(src == medium)
            setSize(1290,722);
        else if(src == high)
            setExtendedState(MAXIMIZED_BOTH);
        else if(src == developer){
            try{
                ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("1_developer.png"));
                JOptionPane.showMessageDialog(jf,"SEOULCHA RATMUMAD \nSTUDENT ID: 6110110108 ","Developer",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("There's something wrong.");
                JOptionPane.showMessageDialog(jf,"There's something wrong.","Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(src == reference){
            try{
                ImageIcon icon = new ImageIcon(PokemonGUI.class.getResource("1_reference.png"));
                JOptionPane.showMessageDialog(jf,"240-210 Programing Techniques\nhttps://stackoverflow.com","Reference",JOptionPane.INFORMATION_MESSAGE,icon);
            }catch (NullPointerException e) {
                System.out.println("There's something wrong.");
                JOptionPane.showMessageDialog(jf,"There's something wrong.","Warning", JOptionPane.WARNING_MESSAGE);
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
                if(lengthNameCheck <= 0 || lengthNameCheck > 20){
                    JOptionPane.showMessageDialog(jf,"You must enter more than 1 letter and less than 20 letter.","Warning",JOptionPane.ERROR_MESSAGE);
                }
                else {
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
                        c.setVisible(false);
                        statusPokemon();
                    }
                }
            }
        });
    }

    public void statusPokemon() {
        statusPikachu();
        statusCharmander();
        statusEkans();
        statusWartortle();
        statusSquirtle();
        statusDiglett();
        statusPidgey();
        statusRattata();
        statusMeowth();
        statusJigglypuff();
        c.setBackground(Color.DARK_GRAY);
        c.setLayout(null);
        c.setVisible(true);
    }

    public void statusPikachu() {
        // Pikachu
        String s = "Attack\nEnemy";
        Pikachu pk = new Pikachu("Pikachu",2500,2400,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(pk.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + pk.getLevel());
        JLabel l4 = new JLabel("Attack: " + pk.getAttackPower());
        JLabel l5 = new JLabel("HP: " + pk.getHp());
        JLabel l6 = new JLabel("EXP: " + pk.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_pikachu.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(75,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(10,10,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(pk.hp < pk.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                pk.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(pk.hp < pk.maxHp())
                                    l5.setText("HP: " + pk.getHp());
                                else
                                    l5.setText("HP: " + pk.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(pk.attackPower < pk.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                pk.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(pk.attackPower < pk.maxAttack())
                                    l4.setText("Attack: " + pk.getAttackPower());
                                else
                                    l4.setText("Attack: " + pk.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.feedPokemon("Berries");
                        l6.setText("EXP: " + pk.getExp() + "/1000");
                        l3.setText("Level: " + pk.getLevel());
                        l4.setText("Attack: " + pk.getAttackPower());
                        l5.setText("HP: " + pk.getHp());
                        System.out.println("Attack cmd: " + pk.getAttackPower());
                        levelCheck = pk.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.feedPokemon("Poffin");
                        l6.setText("EXP: " + pk.getExp() + "/1000");
                        l3.setText("Level: " + pk.getLevel());
                        l4.setText("Attack: " + pk.getAttackPower());
                        l5.setText("HP: " + pk.getHp());
                        System.out.println("Attack cmd: " + pk.getAttackPower());
                        levelCheck = pk.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.feedPokemon("Honey");
                        l6.setText("EXP: " + pk.getExp() + "/1000");
                        l3.setText("Level: " + pk.getLevel());
                        l4.setText("Attack: " + pk.getAttackPower());
                        l5.setText("HP: " + pk.getHp());
                        System.out.println("Attack cmd: " + pk.getAttackPower());
                        levelCheck = pk.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                pa.setBackground(Color.lightGray);
                fr.add(pa);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.sleep(1);
                        l5.setText("HP: " + pk.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.sleep(2);
                        l5.setText("HP: " + pk.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.sleep(3);
                        l5.setText("HP: " + pk.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.sleep(4);
                        l5.setText("HP: " + pk.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.sleep(5);
                        l5.setText("HP: " + pk.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                pk.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pk.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pk.getExp() + "/1000");
                                    l3.setText("Level: " + pk.getLevel());
                                    l4.setText("Attack: " + pk.getAttackPower());
                                    l5.setText("HP: " + pk.getHp());
                                    resultDmg = pk.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pk.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pk.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pk.getExp() + "/1000");
                                    l3.setText("Level: " + pk.getLevel());
                                    l4.setText("Attack: " + pk.getAttackPower());
                                    l5.setText("HP: " + pk.getHp());
                                    resultDmg = pk.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pk.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pk.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pk.getExp() + "/1000");
                                    l3.setText("Level: " + pk.getLevel());
                                    l4.setText("Attack: " + pk.getAttackPower());
                                    l5.setText("HP: " + pk.getHp());
                                    resultDmg = pk.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pk.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pk.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pk.getExp() + "/1000");
                                    l3.setText("Level: " + pk.getLevel());
                                    l4.setText("Attack: " + pk.getAttackPower());
                                    l5.setText("HP: " + pk.getHp());
                                    resultDmg = pk.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pk.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pk.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pk.getExp() + "/1000");
                                    l3.setText("Level: " + pk.getLevel());
                                    l4.setText("Attack: " + pk.getAttackPower());
                                    l5.setText("HP: " + pk.getHp());
                                    resultDmg = pk.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pk.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pk.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pk.getExp() + "/1000");
                                    l3.setText("Level: " + pk.getLevel());
                                    l4.setText("Attack: " + pk.getAttackPower());
                                    l5.setText("HP: " + pk.getHp());
                                    resultDmg = pk.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pk.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        l2.setForeground(Color.lightGray);
        l3.setForeground(Color.lightGray);
        l4.setForeground(Color.lightGray);
        l5.setForeground(Color.lightGray);
        l6.setForeground(Color.lightGray);
        l1.setForeground(Color.ORANGE);
        p1.setBackground(Color.GRAY);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void statusCharmander() {
        // Charmander
        String s = "Attack\nEnemy";
        Charmander cha = new Charmander("Charmander",2490,2390,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(cha.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + cha.getLevel());
        JLabel l4 = new JLabel("Attack: " + cha.getAttackPower());
        JLabel l5 = new JLabel("HP: " + cha.getHp());
        JLabel l6 = new JLabel("EXP: " + cha.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_charmender.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(70,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(264,10,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(cha.hp < cha.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                cha.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(cha.hp < cha.maxHp())
                                    l5.setText("HP: " + cha.getHp());
                                else
                                    l5.setText("HP: " + cha.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(cha.attackPower < cha.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                cha.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(cha.attackPower < cha.maxAttack())
                                    l4.setText("Attack: " + cha.getAttackPower());
                                else
                                    l4.setText("Attack: " + cha.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.feedPokemon("Berries");
                        l6.setText("EXP: " + cha.getExp() + "/1000");
                        l3.setText("Level: " + cha.getLevel());
                        l4.setText("Attack: " + cha.getAttackPower());
                        l5.setText("HP: " + cha.getHp());
                        System.out.println("Attack cmd: " + cha.getAttackPower());
                        levelCheck = cha.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.feedPokemon("Poffin");
                        l6.setText("EXP: " + cha.getExp() + "/1000");
                        l3.setText("Level: " + cha.getLevel());
                        l4.setText("Attack: " + cha.getAttackPower());
                        l5.setText("HP: " + cha.getHp());
                        System.out.println("Attack cmd: " + cha.getAttackPower());
                        levelCheck = cha.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.feedPokemon("Honey");
                        l6.setText("EXP: " + cha.getExp() + "/1000");
                        l3.setText("Level: " + cha.getLevel());
                        l4.setText("Attack: " + cha.getAttackPower());
                        l5.setText("HP: " + cha.getHp());
                        System.out.println("Attack cmd: " + cha.getAttackPower());
                        levelCheck = cha.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.sleep(1);
                        l5.setText("HP: " + cha.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.sleep(2);
                        l5.setText("HP: " + cha.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.sleep(3);
                        l5.setText("HP: " + cha.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.sleep(4);
                        l5.setText("HP: " + cha.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cha.sleep(5);
                        l5.setText("HP: " + cha.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = cha.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + cha.getExp() + "/1000");
                                    l3.setText("Level: " + cha.getLevel());
                                    l4.setText("Attack: " + cha.getAttackPower());
                                    l5.setText("HP: " + cha.getHp());
                                    resultDmg = cha.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = cha.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = cha.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + cha.getExp() + "/1000");
                                    l3.setText("Level: " + cha.getLevel());
                                    l4.setText("Attack: " + cha.getAttackPower());
                                    l5.setText("HP: " + cha.getHp());
                                    resultDmg = cha.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = cha.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = cha.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + cha.getExp() + "/1000");
                                    l3.setText("Level: " + cha.getLevel());
                                    l4.setText("Attack: " + cha.getAttackPower());
                                    l5.setText("HP: " + cha.getHp());
                                    resultDmg = cha.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = cha.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = cha.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + cha.getExp() + "/1000");
                                    l3.setText("Level: " + cha.getLevel());
                                    l4.setText("Attack: " + cha.getAttackPower());
                                    l5.setText("HP: " + cha.getHp());
                                    resultDmg = cha.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = cha.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = cha.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + cha.getExp() + "/1000");
                                    l3.setText("Level: " + cha.getLevel());
                                    l4.setText("Attack: " + cha.getAttackPower());
                                    l5.setText("HP: " + cha.getHp());
                                    resultDmg = cha.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = cha.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = cha.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + cha.getExp() + "/1000");
                                    l3.setText("Level: " + cha.getLevel());
                                    l4.setText("Attack: " + cha.getAttackPower());
                                    l5.setText("HP: " + cha.getHp());
                                    resultDmg = cha.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = cha.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                cha.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.darkGray);
        l3.setForeground(Color.darkGray);
        l4.setForeground(Color.darkGray);
        l5.setForeground(Color.darkGray);
        l6.setForeground(Color.darkGray);
        l1.setForeground(Color.RED);
        p1.setBackground(Color.lightGray);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void statusEkans() {
        // Ekans
        String s = "Attack\nEnemy";
        Ekans ek = new Ekans("Ekans",2485,2385,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(ek.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + ek.getLevel());
        JLabel l4 = new JLabel("Attack: " + ek.getAttackPower());
        JLabel l5 = new JLabel("HP: " + ek.getHp());
        JLabel l6 = new JLabel("EXP: " + ek.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_ekan.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(75,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(518,10,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(ek.hp < ek.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                ek.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(ek.hp < ek.maxHp())
                                    l5.setText("HP: " + ek.getHp());
                                else
                                    l5.setText("HP: " + ek.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(ek.attackPower < ek.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                ek.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(ek.attackPower < ek.maxAttack())
                                    l4.setText("Attack: " + ek.getAttackPower());
                                else
                                    l4.setText("Attack: " + ek.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ek.feedPokemon("Berries");
                        l6.setText("EXP: " + ek.getExp() + "/1000");
                        l3.setText("Level: " + ek.getLevel());
                        l4.setText("Attack: " + ek.getAttackPower());
                        l5.setText("HP: " + ek.getHp());
                        System.out.println("Attack cmd: " + ek.getAttackPower());
                        levelCheck = ek.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ek.feedPokemon("Poffin");
                        l6.setText("EXP: " + ek.getExp() + "/1000");
                        l3.setText("Level: " + ek.getLevel());
                        l4.setText("Attack: " + ek.getAttackPower());
                        l5.setText("HP: " + ek.getHp());
                        System.out.println("Attack cmd: " + ek.getAttackPower());
                        levelCheck = ek.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ek.feedPokemon("Honey");
                        l6.setText("EXP: " + ek.getExp() + "/1000");
                        l3.setText("Level: " + ek.getLevel());
                        l4.setText("Attack: " + ek.getAttackPower());
                        l5.setText("HP: " + ek.getHp());
                        System.out.println("Attack cmd: " + ek.getAttackPower());
                        levelCheck = ek.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ek.sleep(1);
                        l5.setText("HP: " + ek.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ek.sleep(2);
                        l5.setText("HP: " + ek.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ek.sleep(3);
                        l5.setText("HP: " + ek.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ek.sleep(4);
                        l5.setText("HP: " + ek.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ek.sleep(5);
                        l5.setText("HP: " + ek.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = ek.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + ek.getExp() + "/1000");
                                    l3.setText("Level: " + ek.getLevel());
                                    l4.setText("Attack: " + ek.getAttackPower());
                                    l5.setText("HP: " + ek.getHp());
                                    resultDmg = ek.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = ek.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = ek.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + ek.getExp() + "/1000");
                                    l3.setText("Level: " + ek.getLevel());
                                    l4.setText("Attack: " + ek.getAttackPower());
                                    l5.setText("HP: " + ek.getHp());
                                    resultDmg = ek.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = ek.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = ek.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + ek.getExp() + "/1000");
                                    l3.setText("Level: " + ek.getLevel());
                                    l4.setText("Attack: " + ek.getAttackPower());
                                    l5.setText("HP: " + ek.getHp());
                                    resultDmg = ek.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = ek.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = ek.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + ek.getExp() + "/1000");
                                    l3.setText("Level: " + ek.getLevel());
                                    l4.setText("Attack: " + ek.getAttackPower());
                                    l5.setText("HP: " + ek.getHp());
                                    resultDmg = ek.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = ek.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = ek.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + ek.getExp() + "/1000");
                                    l3.setText("Level: " + ek.getLevel());
                                    l4.setText("Attack: " + ek.getAttackPower());
                                    l5.setText("HP: " + ek.getHp());
                                    resultDmg = ek.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = ek.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = ek.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + ek.getExp() + "/1000");
                                    l3.setText("Level: " + ek.getLevel());
                                    l4.setText("Attack: " + ek.getAttackPower());
                                    l5.setText("HP: " + ek.getHp());
                                    resultDmg = ek.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = ek.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                ek.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.lightGray);
        l3.setForeground(Color.lightGray);
        l4.setForeground(Color.lightGray);
        l5.setForeground(Color.lightGray);
        l6.setForeground(Color.lightGray);
        l1.setForeground(Color.magenta);
        p1.setBackground(Color.GRAY);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void statusWartortle() {
        // Wartortle
        String s = "Attack\nEnemy";
        Wartortle wt = new Wartortle("Wartortle",2480,2380,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(wt.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + wt.getLevel());
        JLabel l4 = new JLabel("Attack: " + wt.getAttackPower());
        JLabel l5 = new JLabel("HP: " + wt.getHp());
        JLabel l6 = new JLabel("EXP: " + wt.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_wartortle.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(70,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(772,10,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(wt.hp < wt.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                wt.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(wt.hp < wt.maxHp())
                                    l5.setText("HP: " + wt.getHp());
                                else
                                    l5.setText("HP: " + wt.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(wt.attackPower < wt.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                wt.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(wt.attackPower < wt.maxAttack())
                                    l4.setText("Attack: " + wt.getAttackPower());
                                else
                                    l4.setText("Attack: " + wt.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wt.feedPokemon("Berries");
                        l6.setText("EXP: " + wt.getExp() + "/1000");
                        l3.setText("Level: " + wt.getLevel());
                        l4.setText("Attack: " + wt.getAttackPower());
                        l5.setText("HP: " + wt.getHp());
                        System.out.println("Attack cmd: " + wt.getAttackPower());
                        levelCheck = wt.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wt.feedPokemon("Poffin");
                        l6.setText("EXP: " + wt.getExp() + "/1000");
                        l3.setText("Level: " + wt.getLevel());
                        l4.setText("Attack: " + wt.getAttackPower());
                        l5.setText("HP: " + wt.getHp());
                        System.out.println("Attack cmd: " + wt.getAttackPower());
                        levelCheck = wt.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wt.feedPokemon("Honey");
                        l6.setText("EXP: " + wt.getExp() + "/1000");
                        l3.setText("Level: " + wt.getLevel());
                        l4.setText("Attack: " + wt.getAttackPower());
                        l5.setText("HP: " + wt.getHp());
                        System.out.println("Attack cmd: " + wt.getAttackPower());
                        levelCheck = wt.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wt.sleep(1);
                        l5.setText("HP: " + wt.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wt.sleep(2);
                        l5.setText("HP: " + wt.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wt.sleep(3);
                        l5.setText("HP: " + wt.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wt.sleep(4);
                        l5.setText("HP: " + wt.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wt.sleep(5);
                        l5.setText("HP: " + wt.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = wt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + wt.getExp() + "/1000");
                                    l3.setText("Level: " + wt.getLevel());
                                    l4.setText("Attack: " + wt.getAttackPower());
                                    l5.setText("HP: " + wt.getHp());
                                    resultDmg = wt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = wt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = wt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + wt.getExp() + "/1000");
                                    l3.setText("Level: " + wt.getLevel());
                                    l4.setText("Attack: " + wt.getAttackPower());
                                    l5.setText("HP: " + wt.getHp());
                                    resultDmg = wt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = wt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = wt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + wt.getExp() + "/1000");
                                    l3.setText("Level: " + wt.getLevel());
                                    l4.setText("Attack: " + wt.getAttackPower());
                                    l5.setText("HP: " + wt.getHp());
                                    resultDmg = wt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = wt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = wt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + wt.getExp() + "/1000");
                                    l3.setText("Level: " + wt.getLevel());
                                    l4.setText("Attack: " + wt.getAttackPower());
                                    l5.setText("HP: " + wt.getHp());
                                    resultDmg = wt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = wt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = wt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + wt.getExp() + "/1000");
                                    l3.setText("Level: " + wt.getLevel());
                                    l4.setText("Attack: " + wt.getAttackPower());
                                    l5.setText("HP: " + wt.getHp());
                                    resultDmg = wt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = wt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = wt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + wt.getExp() + "/1000");
                                    l3.setText("Level: " + wt.getLevel());
                                    l4.setText("Attack: " + wt.getAttackPower());
                                    l5.setText("HP: " + wt.getHp());
                                    resultDmg = wt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = wt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                wt.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.darkGray);
        l3.setForeground(Color.darkGray);
        l4.setForeground(Color.darkGray);
        l5.setForeground(Color.darkGray);
        l6.setForeground(Color.darkGray);
        l1.setForeground(Color.BLUE);
        p1.setBackground(Color.lightGray);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void statusSquirtle() {
        // Squirtle
        String s = "Attack\nEnemy";
        Squirtle sq = new Squirtle("Squirtle",2470,2370,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(sq.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + sq.getLevel());
        JLabel l4 = new JLabel("Attack: " + sq.getAttackPower());
        JLabel l5 = new JLabel("HP: " + sq.getHp());
        JLabel l6 = new JLabel("EXP: " + sq.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_squirtle.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(70,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(1026,10,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sq.hp < sq.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                sq.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(sq.hp < sq.maxHp())
                                    l5.setText("HP: " + sq.getHp());
                                else
                                    l5.setText("HP: " + sq.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sq.attackPower < sq.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                sq.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(sq.attackPower < sq.maxAttack())
                                    l4.setText("Attack: " + sq.getAttackPower());
                                else
                                    l4.setText("Attack: " + sq.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sq.feedPokemon("Berries");
                        l6.setText("EXP: " + sq.getExp() + "/1000");
                        l3.setText("Level: " + sq.getLevel());
                        l4.setText("Attack: " + sq.getAttackPower());
                        l5.setText("HP: " + sq.getHp());
                        System.out.println("Attack cmd: " + sq.getAttackPower());
                        levelCheck = sq.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sq.feedPokemon("Poffin");
                        l6.setText("EXP: " + sq.getExp() + "/1000");
                        l3.setText("Level: " + sq.getLevel());
                        l4.setText("Attack: " + sq.getAttackPower());
                        l5.setText("HP: " + sq.getHp());
                        System.out.println("Attack cmd: " + sq.getAttackPower());
                        levelCheck = sq.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sq.feedPokemon("Honey");
                        l6.setText("EXP: " + sq.getExp() + "/1000");
                        l3.setText("Level: " + sq.getLevel());
                        l4.setText("Attack: " + sq.getAttackPower());
                        l5.setText("HP: " + sq.getHp());
                        System.out.println("Attack cmd: " + sq.getAttackPower());
                        levelCheck = sq.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sq.sleep(1);
                        l5.setText("HP: " + sq.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sq.sleep(2);
                        l5.setText("HP: " + sq.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sq.sleep(3);
                        l5.setText("HP: " + sq.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sq.sleep(4);
                        l5.setText("HP: " + sq.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sq.sleep(5);
                        l5.setText("HP: " + sq.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sq.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sq.getExp() + "/1000");
                                    l3.setText("Level: " + sq.getLevel());
                                    l4.setText("Attack: " + sq.getAttackPower());
                                    l5.setText("HP: " + sq.getHp());
                                    resultDmg = sq.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sq.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sq.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sq.getExp() + "/1000");
                                    l3.setText("Level: " + sq.getLevel());
                                    l4.setText("Attack: " + sq.getAttackPower());
                                    l5.setText("HP: " + sq.getHp());
                                    resultDmg = sq.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sq.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sq.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sq.getExp() + "/1000");
                                    l3.setText("Level: " + sq.getLevel());
                                    l4.setText("Attack: " + sq.getAttackPower());
                                    l5.setText("HP: " + sq.getHp());
                                    resultDmg = sq.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sq.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sq.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sq.getExp() + "/1000");
                                    l3.setText("Level: " + sq.getLevel());
                                    l4.setText("Attack: " + sq.getAttackPower());
                                    l5.setText("HP: " + sq.getHp());
                                    resultDmg = sq.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sq.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sq.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sq.getExp() + "/1000");
                                    l3.setText("Level: " + sq.getLevel());
                                    l4.setText("Attack: " + sq.getAttackPower());
                                    l5.setText("HP: " + sq.getHp());
                                    resultDmg = sq.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sq.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sq.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sq.getExp() + "/1000");
                                    l3.setText("Level: " + sq.getLevel());
                                    l4.setText("Attack: " + sq.getAttackPower());
                                    l5.setText("HP: " + sq.getHp());
                                    resultDmg = sq.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sq.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                sq.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.lightGray);
        l3.setForeground(Color.lightGray);
        l4.setForeground(Color.lightGray);
        l5.setForeground(Color.lightGray);
        l6.setForeground(Color.lightGray);
        l1.setForeground(Color.CYAN);
        p1.setBackground(Color.GRAY);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void statusDiglett() {
        // Diglett
        String s = "Attack\nEnemy";
        Diglett dl = new Diglett("Diglett",2460,2360,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(dl.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + dl.getLevel());
        JLabel l4 = new JLabel("Attack: " + dl.getAttackPower());
        JLabel l5 = new JLabel("HP: " + dl.getHp());
        JLabel l6 = new JLabel("EXP: " + dl.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Diglett.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(75,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(10,336,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(dl.hp < dl.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                dl.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(dl.hp < dl.maxHp())
                                    l5.setText("HP: " + dl.getHp());
                                else
                                    l5.setText("HP: " + dl.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(dl.attackPower < dl.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                dl.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(dl.attackPower < dl.maxAttack())
                                    l4.setText("Attack: " + dl.getAttackPower());
                                else
                                    l4.setText("Attack: " + dl.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dl.feedPokemon("Berries");
                        l6.setText("EXP: " + dl.getExp() + "/1000");
                        l3.setText("Level: " + dl.getLevel());
                        l4.setText("Attack: " + dl.getAttackPower());
                        l5.setText("HP: " + dl.getHp());
                        System.out.println("Attack cmd: " + dl.getAttackPower());
                        levelCheck = dl.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dl.feedPokemon("Poffin");
                        l6.setText("EXP: " + dl.getExp() + "/1000");
                        l3.setText("Level: " + dl.getLevel());
                        l4.setText("Attack: " + dl.getAttackPower());
                        l5.setText("HP: " + dl.getHp());
                        System.out.println("Attack cmd: " + dl.getAttackPower());
                        levelCheck = dl.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dl.feedPokemon("Honey");
                        l6.setText("EXP: " + dl.getExp() + "/1000");
                        l3.setText("Level: " + dl.getLevel());
                        l4.setText("Attack: " + dl.getAttackPower());
                        l5.setText("HP: " + dl.getHp());
                        System.out.println("Attack cmd: " + dl.getAttackPower());
                        levelCheck = dl.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dl.sleep(1);
                        l5.setText("HP: " + dl.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dl.sleep(2);
                        l5.setText("HP: " + dl.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dl.sleep(3);
                        l5.setText("HP: " + dl.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dl.sleep(4);
                        l5.setText("HP: " + dl.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dl.sleep(5);
                        l5.setText("HP: " + dl.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = dl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + dl.getExp() + "/1000");
                                    l3.setText("Level: " + dl.getLevel());
                                    l4.setText("Attack: " + dl.getAttackPower());
                                    l5.setText("HP: " + dl.getHp());
                                    resultDmg = dl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = dl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = dl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + dl.getExp() + "/1000");
                                    l3.setText("Level: " + dl.getLevel());
                                    l4.setText("Attack: " + dl.getAttackPower());
                                    l5.setText("HP: " + dl.getHp());
                                    resultDmg = dl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = dl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = dl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + dl.getExp() + "/1000");
                                    l3.setText("Level: " + dl.getLevel());
                                    l4.setText("Attack: " + dl.getAttackPower());
                                    l5.setText("HP: " + dl.getHp());
                                    resultDmg = dl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = dl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = dl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + dl.getExp() + "/1000");
                                    l3.setText("Level: " + dl.getLevel());
                                    l4.setText("Attack: " + dl.getAttackPower());
                                    l5.setText("HP: " + dl.getHp());
                                    resultDmg = dl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = dl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = dl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + dl.getExp() + "/1000");
                                    l3.setText("Level: " + dl.getLevel());
                                    l4.setText("Attack: " + dl.getAttackPower());
                                    l5.setText("HP: " + dl.getHp());
                                    resultDmg = dl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = dl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = dl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + dl.getExp() + "/1000");
                                    l3.setText("Level: " + dl.getLevel());
                                    l4.setText("Attack: " + dl.getAttackPower());
                                    l5.setText("HP: " + dl.getHp());
                                    resultDmg = dl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = dl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                dl.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.darkGray);
        l3.setForeground(Color.darkGray);
        l4.setForeground(Color.darkGray);
        l5.setForeground(Color.darkGray);
        l6.setForeground(Color.darkGray);
        l1.setForeground(Color.BLACK);
        p1.setBackground(Color.lightGray);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void statusPidgey() {
        // Pidgey
        String s = "Attack\nEnemy";
        Pidgey pg = new Pidgey("Pidgey",2400,2300,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(pg.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + pg.getLevel());
        JLabel l4 = new JLabel("Attack: " + pg.getAttackPower());
        JLabel l5 = new JLabel("HP: " + pg.getHp());
        JLabel l6 = new JLabel("EXP: " + pg.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Pidgey.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(80,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(264,336,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(pg.hp < pg.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                pg.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(pg.hp < pg.maxHp())
                                    l5.setText("HP: " + pg.getHp());
                                else
                                    l5.setText("HP: " + pg.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(pg.attackPower < pg.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                pg.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(pg.attackPower < pg.maxAttack())
                                    l4.setText("Attack: " + pg.getAttackPower());
                                else
                                    l4.setText("Attack: " + pg.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pg.feedPokemon("Berries");
                        l6.setText("EXP: " + pg.getExp() + "/1000");
                        l3.setText("Level: " + pg.getLevel());
                        l4.setText("Attack: " + pg.getAttackPower());
                        l5.setText("HP: " + pg.getHp());
                        System.out.println("Attack cmd: " + pg.getAttackPower());
                        levelCheck = pg.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pg.feedPokemon("Poffin");
                        l6.setText("EXP: " + pg.getExp() + "/1000");
                        l3.setText("Level: " + pg.getLevel());
                        l4.setText("Attack: " + pg.getAttackPower());
                        l5.setText("HP: " + pg.getHp());
                        System.out.println("Attack cmd: " + pg.getAttackPower());
                        levelCheck = pg.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pg.feedPokemon("Honey");
                        l6.setText("EXP: " + pg.getExp() + "/1000");
                        l3.setText("Level: " + pg.getLevel());
                        l4.setText("Attack: " + pg.getAttackPower());
                        l5.setText("HP: " + pg.getHp());
                        System.out.println("Attack cmd: " + pg.getAttackPower());
                        levelCheck = pg.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pg.sleep(1);
                        l5.setText("HP: " + pg.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pg.sleep(2);
                        l5.setText("HP: " + pg.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pg.sleep(3);
                        l5.setText("HP: " + pg.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pg.sleep(4);
                        l5.setText("HP: " + pg.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pg.sleep(5);
                        l5.setText("HP: " + pg.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pg.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pg.getExp() + "/1000");
                                    l3.setText("Level: " + pg.getLevel());
                                    l4.setText("Attack: " + pg.getAttackPower());
                                    l5.setText("HP: " + pg.getHp());
                                    resultDmg = pg.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pg.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pg.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pg.getExp() + "/1000");
                                    l3.setText("Level: " + pg.getLevel());
                                    l4.setText("Attack: " + pg.getAttackPower());
                                    l5.setText("HP: " + pg.getHp());
                                    resultDmg = pg.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pg.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pg.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pg.getExp() + "/1000");
                                    l3.setText("Level: " + pg.getLevel());
                                    l4.setText("Attack: " + pg.getAttackPower());
                                    l5.setText("HP: " + pg.getHp());
                                    resultDmg = pg.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pg.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pg.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pg.getExp() + "/1000");
                                    l3.setText("Level: " + pg.getLevel());
                                    l4.setText("Attack: " + pg.getAttackPower());
                                    l5.setText("HP: " + pg.getHp());
                                    resultDmg = pg.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pg.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pg.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pg.getExp() + "/1000");
                                    l3.setText("Level: " + pg.getLevel());
                                    l4.setText("Attack: " + pg.getAttackPower());
                                    l5.setText("HP: " + pg.getHp());
                                    resultDmg = pg.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pg.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = pg.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + pg.getExp() + "/1000");
                                    l3.setText("Level: " + pg.getLevel());
                                    l4.setText("Attack: " + pg.getAttackPower());
                                    l5.setText("HP: " + pg.getHp());
                                    resultDmg = pg.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = pg.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                pg.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.lightGray);
        l3.setForeground(Color.lightGray);
        l4.setForeground(Color.lightGray);
        l5.setForeground(Color.lightGray);
        l6.setForeground(Color.lightGray);
        l1.setForeground(Color.ORANGE);
        p1.setBackground(Color.GRAY);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void statusRattata() {
        // Snorlax
        String s = "Attack\nEnemy";
        Snorlax  sl = new Snorlax ("Snorlax ",2395,2295,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(sl.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + sl.getLevel());
        JLabel l4 = new JLabel("Attack: " + sl.getAttackPower());
        JLabel l5 = new JLabel("HP: " + sl.getHp());
        JLabel l6 = new JLabel("EXP: " + sl.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_snorlax.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(75,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(518,336,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sl.hp < sl.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                sl.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(sl.hp < sl.maxHp())
                                    l5.setText("HP: " + sl.getHp());
                                else
                                    l5.setText("HP: " + sl.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sl.attackPower < sl.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                sl.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(sl.attackPower < sl.maxAttack())
                                    l4.setText("Attack: " + sl.getAttackPower());
                                else
                                    l4.setText("Attack: " + sl.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sl.feedPokemon("Berries");
                        l6.setText("EXP: " + sl.getExp() + "/1000");
                        l3.setText("Level: " + sl.getLevel());
                        l4.setText("Attack: " + sl.getAttackPower());
                        l5.setText("HP: " + sl.getHp());
                        System.out.println("Attack cmd: " + sl.getAttackPower());
                        levelCheck = sl.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sl.feedPokemon("Poffin");
                        l6.setText("EXP: " + sl.getExp() + "/1000");
                        l3.setText("Level: " + sl.getLevel());
                        l4.setText("Attack: " + sl.getAttackPower());
                        l5.setText("HP: " + sl.getHp());
                        System.out.println("Attack cmd: " + sl.getAttackPower());
                        levelCheck = sl.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sl.feedPokemon("Honey");
                        l6.setText("EXP: " + sl.getExp() + "/1000");
                        l3.setText("Level: " + sl.getLevel());
                        l4.setText("Attack: " + sl.getAttackPower());
                        l5.setText("HP: " + sl.getHp());
                        System.out.println("Attack cmd: " + sl.getAttackPower());
                        levelCheck = sl.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sl.sleep(1);
                        l5.setText("HP: " + sl.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sl.sleep(2);
                        l5.setText("HP: " + sl.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sl.sleep(3);
                        l5.setText("HP: " + sl.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sl.sleep(4);
                        l5.setText("HP: " + sl.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sl.sleep(5);
                        l5.setText("HP: " + sl.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sl.getExp() + "/1000");
                                    l3.setText("Level: " + sl.getLevel());
                                    l4.setText("Attack: " + sl.getAttackPower());
                                    l5.setText("HP: " + sl.getHp());
                                    resultDmg = sl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sl.getExp() + "/1000");
                                    l3.setText("Level: " + sl.getLevel());
                                    l4.setText("Attack: " + sl.getAttackPower());
                                    l5.setText("HP: " + sl.getHp());
                                    resultDmg = sl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sl.getExp() + "/1000");
                                    l3.setText("Level: " + sl.getLevel());
                                    l4.setText("Attack: " + sl.getAttackPower());
                                    l5.setText("HP: " + sl.getHp());
                                    resultDmg = sl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sl.getExp() + "/1000");
                                    l3.setText("Level: " + sl.getLevel());
                                    l4.setText("Attack: " + sl.getAttackPower());
                                    l5.setText("HP: " + sl.getHp());
                                    resultDmg = sl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sl.getExp() + "/1000");
                                    l3.setText("Level: " + sl.getLevel());
                                    l4.setText("Attack: " + sl.getAttackPower());
                                    l5.setText("HP: " + sl.getHp());
                                    resultDmg = sl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = sl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + sl.getExp() + "/1000");
                                    l3.setText("Level: " + sl.getLevel());
                                    l4.setText("Attack: " + sl.getAttackPower());
                                    l5.setText("HP: " + sl.getHp());
                                    resultDmg = sl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = sl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                sl.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.darkGray);
        l3.setForeground(Color.darkGray);
        l4.setForeground(Color.darkGray);
        l5.setForeground(Color.darkGray);
        l6.setForeground(Color.darkGray);
        l1.setForeground(Color.BLUE);
        p1.setBackground(Color.lightGray);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void statusMeowth() {
        // Meowth
        String s = "Attack\nEnemy";
        Meowth mt = new Meowth("Meowth",2390,2290,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(mt.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + mt.getLevel());
        JLabel l4 = new JLabel("Attack: " + mt.getAttackPower());
        JLabel l5 = new JLabel("HP: " + mt.getHp());
        JLabel l6 = new JLabel("EXP: " + mt.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Meowth.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(80,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(772,336,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(mt.hp < mt.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                mt.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(mt.hp < mt.maxHp())
                                    l5.setText("HP: " + mt.getHp());
                                else
                                    l5.setText("HP: " + mt.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(mt.attackPower < mt.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                mt.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(mt.attackPower < mt.maxAttack())
                                    l4.setText("Attack: " + mt.getAttackPower());
                                else
                                    l4.setText("Attack: " + mt.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mt.feedPokemon("Berries");
                        l6.setText("EXP: " + mt.getExp() + "/1000");
                        l3.setText("Level: " + mt.getLevel());
                        l4.setText("Attack: " + mt.getAttackPower());
                        l5.setText("HP: " + mt.getHp());
                        System.out.println("Attack cmd: " + mt.getAttackPower());
                        levelCheck = mt.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mt.feedPokemon("Poffin");
                        l6.setText("EXP: " + mt.getExp() + "/1000");
                        l3.setText("Level: " + mt.getLevel());
                        l4.setText("Attack: " + mt.getAttackPower());
                        l5.setText("HP: " + mt.getHp());
                        System.out.println("Attack cmd: " + mt.getAttackPower());
                        levelCheck = mt.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mt.feedPokemon("Honey");
                        l6.setText("EXP: " + mt.getExp() + "/1000");
                        l3.setText("Level: " + mt.getLevel());
                        l4.setText("Attack: " + mt.getAttackPower());
                        l5.setText("HP: " + mt.getHp());
                        System.out.println("Attack cmd: " + mt.getAttackPower());
                        levelCheck = mt.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mt.sleep(1);
                        l5.setText("HP: " + mt.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mt.sleep(2);
                        l5.setText("HP: " + mt.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mt.sleep(3);
                        l5.setText("HP: " + mt.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mt.sleep(4);
                        l5.setText("HP: " + mt.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mt.sleep(5);
                        l5.setText("HP: " + mt.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = mt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + mt.getExp() + "/1000");
                                    l3.setText("Level: " + mt.getLevel());
                                    l4.setText("Attack: " + mt.getAttackPower());
                                    l5.setText("HP: " + mt.getHp());
                                    resultDmg = mt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = mt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = mt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + mt.getExp() + "/1000");
                                    l3.setText("Level: " + mt.getLevel());
                                    l4.setText("Attack: " + mt.getAttackPower());
                                    l5.setText("HP: " + mt.getHp());
                                    resultDmg = mt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = mt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = mt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + mt.getExp() + "/1000");
                                    l3.setText("Level: " + mt.getLevel());
                                    l4.setText("Attack: " + mt.getAttackPower());
                                    l5.setText("HP: " + mt.getHp());
                                    resultDmg = mt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = mt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = mt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + mt.getExp() + "/1000");
                                    l3.setText("Level: " + mt.getLevel());
                                    l4.setText("Attack: " + mt.getAttackPower());
                                    l5.setText("HP: " + mt.getHp());
                                    resultDmg = mt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = mt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = mt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + mt.getExp() + "/1000");
                                    l3.setText("Level: " + mt.getLevel());
                                    l4.setText("Attack: " + mt.getAttackPower());
                                    l5.setText("HP: " + mt.getHp());
                                    resultDmg = mt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = mt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = mt.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + mt.getExp() + "/1000");
                                    l3.setText("Level: " + mt.getLevel());
                                    l4.setText("Attack: " + mt.getAttackPower());
                                    l5.setText("HP: " + mt.getHp());
                                    resultDmg = mt.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = mt.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                mt.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.lightGray);
        l3.setForeground(Color.lightGray);
        l4.setForeground(Color.lightGray);
        l5.setForeground(Color.lightGray);
        l6.setForeground(Color.lightGray);
        l1.setForeground(Color.ORANGE);
        p1.setBackground(Color.gray);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void statusJigglypuff() {
        // Jigglypuff
        String s = "Attack\nEnemy";
        Jigglypuff  gl = new Jigglypuff ("Jigglypuff ",2395,2295,1);
        JPanel p1 = new JPanel();
        // Text Field
        JTextField tf = new JTextField("",25);
        JFrame f = new JFrame("Rename Pokémon");
        JButton ok = new JButton("OK");
        // Text Field
        JLabel l1 = new JLabel(gl.getName());
        JLabel l2 = new JLabel("Status: ");
        JLabel l3 = new JLabel("Level: " + gl.getLevel());
        JLabel l4 = new JLabel("Attack: " + gl.getAttackPower());
        JLabel l5 = new JLabel("HP: " + gl.getHp());
        JLabel l6 = new JLabel("EXP: " + gl.getExp() + "/1000");
        JLabel i1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Jigglypuff.gif"));
        JButton t1 = new JButton("Feed");
        JButton t2 = new JButton("Sleep");
        JButton t3 = new JButton("Rename");
        JButton t4 = new JButton("<html>" + s.replaceAll("\\n", "<br>") + "</html>");
        l1.setBounds(70,1,200,50);      //Name
        l2.setBounds(10,145,100,50);    //Status -> Label
        l3.setBounds(10,175,100,50);    //Level
        l4.setBounds(10,190,150,50);    //Attack
        l5.setBounds(10,205,100,50);    //HP
        l6.setBounds(10,220,200,50);    //EXP
        i1.setBounds(75,40,100,100);    //Image
        t1.setBounds(10,265,65,40);     //BT Feed
        t2.setBounds(80,265,70,40);     //BT Sleep
        t3.setBounds(155,265,80,40);    //BT Rename
        t4.setBounds(155,200,80,50);    //BT Attack
        p1.setBounds(1026,336,244, 316);   //Panel
        l1.setFont(new Font("Courier New" , Font.BOLD,20));     //Name
        l2.setFont(new Font("Courier New", Font.BOLD,16));      //Status -> Label
        l3.setFont(new Font("Courier New", Font.BOLD,15));      //Level
        l4.setFont(new Font("Courier New", Font.BOLD,15));      //Attack
        l5.setFont(new Font("Courier New", Font.BOLD,15));      //HP
        l6.setFont(new Font("Courier New", Font.BOLD,15));      //EXP
        p1.setLayout(null);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(i1);
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(t4);
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        Color iCon = new Color(47, 255, 61); // 153,255,153
        JButton t5 = new JButton("BOOSTER");
        t5.setBounds(135,150,100,40); // x = t4 - 20, y = t4 - 50
        t5.setBackground(iCon);
        p1.add(t5);
        t5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ic1 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_HP_icon.png");
                ImageIcon ic2 = new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Menu_Attack_icon.png");
                JFrame fb = new JFrame("Select item booster");
                JPanel pb = new JPanel();
                JLabel lb1 = new JLabel("Select Item");
                JButton tb1 = new JButton("HP Booster");
                JButton tb2 = new JButton("Attack Booster");
                JButton okb = new JButton("Exit");
                JLabel lb2 = new JLabel("Remaining Items: ");
                JLabel lb3 = new JLabel();
                lb3.setText("HP Booster: " + hpDrop);
                JLabel lb4 = new JLabel();
                lb4.setText("Attack Booster: " + atkDrop);
                lb2.setFont(new Font("Courier New", Font.BOLD,15));
                lb3.setFont(new Font("Courier New", Font.BOLD,15));
                lb4.setFont(new Font("Courier New", Font.BOLD,15));
                pb.setLayout(null);
                tb1.setIcon(ic1);
                tb2.setIcon(ic2);
                lb1.setFont(new Font("Courier New", Font.BOLD,25));
                lb1.setBounds(60,10,200,30);
                lb2.setBounds(10,190,200,30);
                lb3.setBounds(10,210,200,30);
                lb4.setBounds(10,230,200,30);
                tb1.setBounds(10,50,268,60);
                tb2.setBounds(10,120,268,60);
                okb.setBounds(210,215,60,30);
                lb1.setForeground(Color.LIGHT_GRAY);
                lb2.setForeground(Color.LIGHT_GRAY);
                lb3.setForeground(Color.LIGHT_GRAY);
                lb4.setForeground(Color.LIGHT_GRAY);
                pb.setBackground(Color.darkGray);
                pb.add(lb1);
                pb.add(lb2);
                pb.add(lb3);
                pb.add(lb4);
                pb.add(tb1);
                pb.add(tb2);
                pb.add(okb);
                fb.add(pb);
                okb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gl.hp < gl.maxHp()) {
                            if(hpDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","HP Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                hpDrop--;
                                obj.setHpDrop(hpDrop);
                                gl.powerBoost("HP");
                                lb3.setText("HP Booster: " + hpDrop);
                                if(gl.hp < gl.maxHp())
                                    l5.setText("HP: " + gl.getHp());
                                else
                                    l5.setText("HP: " + gl.maxHp());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum HP.","HP Booster", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                });
                tb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gl.attackPower < gl.maxAttack()){
                            if(atkDrop <= 0) {
                                JOptionPane.showMessageDialog(null,"You don't have this item.","Attack Booster", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                atkDrop--;
                                obj.setAtkDrop(atkDrop);
                                gl.powerBoost("ATK");
                                lb4.setText("Attack Booster: " + atkDrop);
                                if(gl.attackPower < gl.maxAttack())
                                    l4.setText("Attack: " + gl.getAttackPower());
                                else
                                    l4.setText("Attack: " + gl.maxAttack());
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Your Pokemon has maximum attack power.","Attack Booster", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                okb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fb.setVisible(false);
                    }
                });
                fb.setVisible(true);
                fb.setSize(300,300);
                fb.setLocationRelativeTo(null);
                fb.setResizable(false);
            }
        });
        //------------------------------------------------ BOOST MENU --------------------------------------------------
        f.add(tf);
        f.add(ok);
        c.add(p1);
        f.setLayout(new FlowLayout());
        f.setSize(300,100);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);
        t1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Food menu ------------------------------------------------
                JFrame fr = new JFrame("Select food");
                JPanel pa = new JPanel();
                JButton jt1 = new JButton("Berries");
                JButton jt2 = new JButton("Poffin");
                JButton jt3 = new JButton("Honey");
                JButton o = new JButton("OK");
                jt1.setBounds(5,5,275,35);
                jt2.setBounds(5,45,275,35);
                jt3.setBounds(5,85,275,35);
                o.setBounds(110,125,70,35);
                jt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gl.feedPokemon("Berries");
                        l6.setText("EXP: " + gl.getExp() + "/1000");
                        l3.setText("Level: " + gl.getLevel());
                        l4.setText("Attack: " + gl.getAttackPower());
                        l5.setText("HP: " + gl.getHp());
                        System.out.println("Attack cmd: " + gl.getAttackPower());
                        levelCheck = gl.getLevel();
                    }
                });
                jt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gl.feedPokemon("Poffin");
                        l6.setText("EXP: " + gl.getExp() + "/1000");
                        l3.setText("Level: " + gl.getLevel());
                        l4.setText("Attack: " + gl.getAttackPower());
                        l5.setText("HP: " + gl.getHp());
                        System.out.println("Attack cmd: " + gl.getAttackPower());
                        levelCheck = gl.getLevel();
                    }
                });
                jt3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gl.feedPokemon("Honey");
                        l6.setText("EXP: " + gl.getExp() + "/1000");
                        l3.setText("Level: " + gl.getLevel());
                        l4.setText("Attack: " + gl.getAttackPower());
                        l5.setText("HP: " + gl.getHp());
                        System.out.println("Attack cmd: " + gl.getAttackPower());
                        levelCheck = gl.getLevel();
                    }
                });
                o.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.setVisible(false);
                    }
                });
                pa.add(jt1);
                pa.add(jt2);
                pa.add(jt3);
                pa.add(o);
                pa.setLayout(null);
                jt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                o.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fr.add(pa);
                fr.setBackground(Color.ORANGE);
                fr.setSize(300,200);
                fr.setResizable(false);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                // Food menu ------------------------------------------------
            }
        });
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sleep menu ------------------------------------------------
                JFrame jf = new JFrame("Sleep");
                JPanel jp = new JPanel();
                JButton j1 = new JButton("01:00 hr");
                JButton j2 = new JButton("02:00 hr");
                JButton j3 = new JButton("03:00 hr");
                JButton j4 = new JButton("04:00 hr");
                JButton j5 = new JButton("05:00 hr");
                JButton ok = new JButton("OK");
                jp.setLayout(new FlowLayout());
                jp.add(j1);
                jp.add(j2);
                jp.add(j3);
                jp.add(j4);
                jp.add(j5);
                jp.add(ok);
                jf.add(jp);
                j1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gl.sleep(1);
                        l5.setText("HP: " + gl.getHp());
                    }
                });
                j2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gl.sleep(2);
                        l5.setText("HP: " + gl.getHp());
                    }
                });
                j3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gl.sleep(3);
                        l5.setText("HP: " + gl.getHp());
                    }
                });
                j4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gl.sleep(4);
                        l5.setText("HP: " + gl.getHp());
                    }
                });
                j5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gl.sleep(5);
                        l5.setText("HP: " + gl.getHp());
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.setVisible(false);
                    }
                });
                j1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                j5.setCursor(new Cursor(Cursor.HAND_CURSOR));
                ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jp.setBackground(Color.lightGray);
                jf.setSize(450,105);
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setResizable(false);
                // Sleep menu ------------------------------------------------
            }
        });
        t3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attack menu ------------------------------------------------------
                JFrame fAtk = new JFrame("Attack menu");
                JPanel pnAtk = new JPanel();
                JButton rd = new JButton("Find enemy around you");
                JButton y = new JButton("Yes");
                JButton n = new JButton("No");

                JLabel img1 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Machoke.gif"));
                JLabel img2 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Geodude.gif"));
                JLabel img3 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Magnemite.gif"));
                JLabel img4 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Gengar.gif"));
                JLabel img5 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Onix.gif"));
                JLabel img6 = new JLabel(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\Pokemon_6110110108\\src\\1_Enemy_Jynx.gif"));
                JLabel lAtk1 = new JLabel("Attack Enemy");
                JLabel lAtk2 = new JLabel();
                JLabel lAtk3 = new JLabel("Do you want to attack Pokemon Enemy?");

                lAtk1.setFont(new Font("Courier New" , Font.BOLD,40));
                lAtk2.setFont(new Font("Courier New" , Font.BOLD,20));
                lAtk3.setFont(new Font("Courier New" , Font.BOLD,20));

                lAtk1.setBounds(200,10,500,50);
                lAtk1.setForeground(Color.lightGray);
                rd.setBounds(250,230,180,40);

                pnAtk.add(img1);
                pnAtk.add(img2);
                pnAtk.add(img3);
                pnAtk.add(img4);
                pnAtk.add(img5);
                pnAtk.add(img6);
                pnAtk.add(rd);
                pnAtk.add(y);
                pnAtk.add(n);
                pnAtk.add(lAtk1);
                pnAtk.add(lAtk2);
                pnAtk.add(lAtk3);

                rd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // JFrame size 700 * 500
                        rd.setVisible(false);
                        Random r = new Random();
                        int rand = (int)(Math.random()*6);
                        int rdAtk = r.nextInt(20000)+100;
                        int rdHp = r.nextInt(10000)+100;
                        if(rand == 0) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Machoke, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img1.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img1.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = gl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + gl.getExp() + "/1000");
                                    l3.setText("Level: " + gl.getLevel());
                                    l4.setText("Attack: " + gl.getAttackPower());
                                    l5.setText("HP: " + gl.getHp());
                                    resultDmg = gl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = gl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GREEN);
                        }
                        if(rand == 1) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Geodude, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img2.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img2.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = gl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + gl.getExp() + "/1000");
                                    l3.setText("Level: " + gl.getLevel());
                                    l4.setText("Attack: " + gl.getAttackPower());
                                    l5.setText("HP: " + gl.getHp());
                                    resultDmg = gl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = gl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 2) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img3.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img3.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = gl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + gl.getExp() + "/1000");
                                    l3.setText("Level: " + gl.getLevel());
                                    l4.setText("Attack: " + gl.getAttackPower());
                                    l5.setText("HP: " + gl.getHp());
                                    resultDmg = gl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = gl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 3) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Gengar, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img4.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img4.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = gl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + gl.getExp() + "/1000");
                                    l3.setText("Level: " + gl.getLevel());
                                    l4.setText("Attack: " + gl.getAttackPower());
                                    l5.setText("HP: " + gl.getHp());
                                    resultDmg = gl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = gl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.magenta);
                        }
                        if(rand == 4) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Onix, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img5.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img5.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = gl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + gl.getExp() + "/1000");
                                    l3.setText("Level: " + gl.getLevel());
                                    l4.setText("Attack: " + gl.getAttackPower());
                                    l5.setText("HP: " + gl.getHp());
                                    resultDmg = gl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = gl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.GRAY);
                        }
                        if(rand == 5) {
                            System.out.println("Random number: " + rand);
                            lAtk2.setText("Found: Jynx, Attack power: " + rdAtk + ", HP: " + rdHp);
                            lAtk2.setVisible(true);
                            lAtk3.setVisible(true);
                            img6.setVisible(true);
                            n.setVisible(true);
                            y.setVisible(true);
                            lAtk2.setBounds(100,300,600,50);
                            lAtk3.setBounds(60,350,440,30);
                            y.setBounds(500,350,70,30);
                            n.setBounds(575,350,70,30);
                            img6.setBounds(250,110,200,200);
                            y.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You attack Pokemon enemy");
                                    String s = gl.attackEnemy(rdAtk, rdHp);
                                    l6.setText("EXP: " + gl.getExp() + "/1000");
                                    l3.setText("Level: " + gl.getLevel());
                                    l4.setText("Attack: " + gl.getAttackPower());
                                    l5.setText("HP: " + gl.getHp());
                                    resultDmg = gl.cumulativeDamage(rdAtk); //-------------- Cumulative Power Attack
                                    System.out.println("Cumulative damage total = " + resultDmg);
                                    levelCheck = gl.getLevel(); //------------------------------------- Level
                                    if(s.equals("Lose")) {
                                        attackLose();
                                        fAtk.setVisible(false);
                                    }
                                    if(s.equals("Win")) {
                                        attackWin();
                                        fAtk.setVisible(false);
                                    }
                                }
                            });
                            n.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("You don't attack Pokemon enemy");
                                    fAtk.setVisible(false);
                                }
                            });
                            lAtk1.setForeground(Color.BLACK);
                            lAtk2.setForeground(Color.BLACK);
                            lAtk3.setForeground(Color.BLACK);
                            pnAtk.setBackground(Color.RED);
                        }
                    }
                });
                lAtk2.setVisible(false);
                lAtk3.setVisible(false);
                n.setVisible(false);
                y.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                n.setCursor(new Cursor(Cursor.HAND_CURSOR));
                y.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                fAtk.add(pnAtk);
                pnAtk.setLayout(null);
                pnAtk.setBackground(Color.DARK_GRAY);
                fAtk.setVisible(true);
                fAtk.setSize(700,500);
                fAtk.setResizable(false);
                fAtk.setLocationRelativeTo(null);
                // Attack menu ------------------------------------------------------
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Warning");
                String get = tf.getText();
                System.out.println(get);
                gl.setName(get);
                int lengthNameCheck = get.length();
                if(lengthNameCheck <= 0 || lengthNameCheck > 10 ) {
                    JOptionPane.showMessageDialog(jf, "You must enter more than 1 letter and less than 10 letter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    l1.setText(get);
                    f.setVisible(false);
                    tf.setText("");
                }
            }
        });
        l2.setForeground(Color.darkGray);
        l3.setForeground(Color.darkGray);
        l4.setForeground(Color.darkGray);
        l5.setForeground(Color.darkGray);
        l6.setForeground(Color.darkGray);
        l1.setForeground(Color.magenta);
        p1.setBackground(Color.lightGray);
    }
}