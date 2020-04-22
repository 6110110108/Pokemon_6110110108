
public abstract class Pokemon {
    protected String name;
    protected int hp = 0;
    protected int attackPower = 0;
    protected int level = 1;
    protected int exp = 0;
    protected int check = 0;

    public Pokemon(String name, int attackPower, int hp, int level) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
        this.level = level;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
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

    public void setExp(int exp){
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        int maxHp = maxHp();
        if(hp <= maxHp)
            return hp;
        else
            return maxHp;
    }

    public int getAttackPower() {
        check++;
        if(check <= 1) {
            check++;
            attackPokemonWSkill();
            return attackPower;

        }
        else {
            if(attackPower <= maxAttack())
                return attackPower;
            else
                return maxAttack();
        }
    }

    public int getLevel() {
        System.out.println("level (cmd): " + level);
        return level;
    }

    public int getExp() {
        return exp;
    }

    public void feedPokemon(String food) {
        foodFeed(food);
        if(exp < 1000) {
            exp = exp + 100;
        }
        else {
            exp = 0;
            level++;
            setLevel(level);
            //levelUptoAtk
        }
    }

    public void foodFeed(String food) {
        if(food.equals("Berries")){
             attackPower = attackPower + 3;
        }
        else if(food.equals("Poffin")){
             attackPower = attackPower + 2;
        }
        else if(food.equals("Honey")){
             attackPower = attackPower + 1;
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
        if(hp <= maxHp()){
            if(sleep == 1)
                hp = hp + 10;
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
        int gLv = getLevel();
        if(level >= 2) {
            attackPower += 100;
            hp += 100;
        }
    }

    public abstract String skillPokemon();
    public abstract int maxHp();
    public abstract int maxAttack();

}