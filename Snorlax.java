
public class Snorlax  extends Pokemon {
    public Snorlax (String name, int hp, int power, int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Normal";
    }

    @Override
    public int maxAttack() {
        return 23000;
    }

    @Override
    public int maxHp() {
        return 33000;
    }
}
