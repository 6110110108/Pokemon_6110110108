
public abstract class Pokemon {
    protected String name;
    protected int hp = 0;
    protected int attackPower = 0;
    protected int level = 1;
    protected int exp = 0;
    protected int check = 0, atkCheck = 0;
    protected int result = 0;
    private PokemonTrainer obj = new PokemonTrainer();

    public Pokemon(String name, int hp, int attackPower, int level) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
        this.level = level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        int maxHp = maxHp();
        if(atkCheck >= 1) {
            return hp;
        }
        else {
            if(hp <= maxHp)
                return hp;
            else
                return hp = maxHp;
        }
    }

    public int getAttackPower() {
        check++;
        if(check <= 1) {
            check++;
            attackPokemonWSkill();
            return attackPower;
        }
        else {
            if(atkCheck >= 1) {
                return attackPower;
            }
            else {
                if(attackPower <= maxAttack())
                    return attackPower;
                else
                    return attackPower = maxAttack();
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public void feedPokemon(String food) {
        foodFeed(food);
        if(exp < 1000) {
            exp = exp + 500; //test
        }
        else {
            exp = 0;
            level++;
            setLevel(level);
            levelUptoAtk();
            levelUptoHp();
        }
    }

    public void foodFeed(String food) {
        atkCheck = 0;
        if(food.equals("Berries")){
             attackPower = attackPower + 3;
        }
        else if(food.equals("Poffin")){
             attackPower = attackPower + 2;
        }
        else if(food.equals("Honey")){
             attackPower = attackPower + 10000; // Test
        }
    }

    public void attackPokemonWSkill() {
        String s = skillPokemon();
        int maxAttack = maxAttack();
        if(attackPower <= maxAttack) {
            if(s.equals("Fire")){
                 attackPower = attackPower + (attackPower * 50) / 100;
            }
            else if(s.equals("Water")){
                 attackPower = attackPower + (attackPower * 40) / 100;
            }
            else if(s.equals("Ground")){
                 attackPower = attackPower + (attackPower * 30) / 100;
            }
            else if(s.equals("Electric")){
                attackPower = attackPower + (attackPower * 20) / 100;
            }
            else if(s.equals("Poison")){
                attackPower = attackPower + (attackPower * 10) / 100;
            }
        }
    }

    public void sleep(int sleep) {
        atkCheck = 0;
        if(hp <= maxHp()){
            if(sleep == 1)
                hp = hp + 10000; // Test
            else if(sleep == 2)
                hp = hp + 20;
            else if(sleep == 3)
                hp = hp + 30;
            else if(sleep == 4)
                hp = hp + 40;
            else if(sleep == 5)
                hp = hp + 50;
        }
        else {
            hp = maxHp();
        }
    }

    public void levelUptoAtk() {
        int rand = (int)(Math.random()*5);
        if(attackPower <= maxAttack()) {
            if(rand == 0) {
                attackPower += 50;
            }
            else if(rand == 1) {
                attackPower += 60;
            }
            else if(rand == 2) {
                attackPower += 70;
            }
            else if(rand == 3) {
                attackPower += 80;
            }
            else if(rand == 4) {
                attackPower += 90;
            }
        }
        else {
            attackPower = maxAttack();
        }
    }

    public void levelUptoHp() {
        int rand = (int)(Math.random()*5);
        if(hp <= maxHp()) {
            if(rand == 0) {
                hp += 50;
            }
            else if(rand == 1) {
                hp += 60;
            }
            else if(rand == 2) {
                hp += 70;
            }
            else if(rand == 3) {
                hp += 80;
            }
            else if(rand == 4) {
                hp += 90;
            }
        }
        else {
            hp = maxHp();
        }
    }

    public String attackEnemy(int rdAtk, int rdHp) {
        atkCheck++;
        if(rdAtk > getAttackPower() && rdHp > getHp()) {
            hp = hp - (( hp * 95 ) / 100);
            attackPower = attackPower - ((attackPower * 95) / 100);
            if(exp < 1000) {
                exp = exp + 20;
            }
            else {
                exp = 0;
                level++;
                setLevel(level);
                levelUptoAtk();
                levelUptoHp();
            }
            System.out.println("Attack power and HP of your Pokemon loss 95%");
            return "Lose";
        }
        if(getAttackPower() >= rdAtk && getHp() >= rdHp) {
            hp = hp - (( hp * 5 ) / 100);
            attackPower = attackPower - ((attackPower * 5) / 100);
            if(exp < 1000) {
                exp = exp + 500;
            }
            else {
                exp = 0;
                level++;
                setLevel(level);
                levelUptoAtk();
                levelUptoHp();
            }
            System.out.println("Attack power and HP of your Pokemon loss 5%");
            return "Win";
        }
        if(getAttackPower() >= rdAtk && getHp() <= rdHp) {
            if(getAttackPower() <= (rdAtk + ((rdAtk * 50)/100))){
                hp = hp - (( hp * 50 ) / 100);
                attackPower = attackPower - ((attackPower * 50) / 100);
                if(exp < 1000) {
                    exp = exp + 50;
                }
                else {
                    exp = 0;
                    level++;
                    setLevel(level);
                    levelUptoAtk();
                    levelUptoHp();
                }
                System.out.println("Attack power and HP of your Pokemon loss 50%");
                return "Lose";
            }
            else {
                hp = hp - (( hp * 10 ) / 100);
                attackPower = attackPower - ((attackPower * 10) / 100);
                if(exp < 1000) {
                    exp = exp + 250;
                }
                else {
                    exp = 0;
                    level++;
                    setLevel(level);
                    levelUptoAtk();
                    levelUptoHp();
                }
                System.out.println("Attack power and HP of your Pokemon loss 10%");
                return "Win";
            }
        }
        if(getAttackPower() <= rdAtk && getHp() >= rdHp) {
            if(getHp() <= (rdHp + ((rdHp * 50)/100))) {
                hp = hp - (( hp * 50 ) / 100);
                attackPower = attackPower - ((attackPower * 50) / 100);
                if(exp < 1000) {
                    exp = exp + 50;
                }
                else {
                    exp = 0;
                    level++;
                    setLevel(level);
                    levelUptoAtk();
                    levelUptoHp();
                }
                System.out.println("Attack power and HP of your Pokemon loss 50%");
                return "Lose";
            }
            else {
                hp = hp - (( hp * 10 ) / 100);
                attackPower = attackPower - ((attackPower * 10) / 100);
                exp = exp + 250;
                System.out.println("Attack power and HP of your Pokemon loss 10%");
                return "Win";
            }
        }
        else {
            hp = hp - (( hp * 10 ) / 100);
            attackPower = attackPower - ((attackPower * 10) / 100);
            if(exp < 1000) {
                exp = exp + 200;
            }
            else {
                exp = 0;
                level++;
                setLevel(level);
                levelUptoAtk();
                levelUptoHp();
            }
            System.out.println("Attack power and HP of your Pokemon loss 10%");
            return "Win";
        }
    }

    public int cumulativeDamage(int rAtk) {
        int cmtDmg = 0;
        if(attackPower >= rAtk) {
            cmtDmg = rAtk;
        }
        result = result + cmtDmg;
        return result;
    }

    public abstract String skillPokemon();
    public abstract int maxHp();
    public abstract int maxAttack();

}