
public class Jigglypuff  extends Pokemon {
    public Jigglypuff (String name, int hp, int power, int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Normal";
    }

    @Override
    public int maxAttack() {
        return 21000;
    }

    @Override
    public int maxHp() {
        return 31000;
    }
}
