
public class Meowth extends Pokemon {
    public Meowth(String name, int hp, int power,int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Normal";
    }

    @Override
    public int maxAttack() {
        return 22000;
    }

    @Override
    public int maxHp() {
        return 32000;
    }
}
