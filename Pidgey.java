
public class Pidgey extends Pokemon {
    public Pidgey(String name, int hp, int power, int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Normal";
    }

    @Override
    public int maxAttack() {
        return 24000;
    }

    @Override
    public int maxHp() {
        return 34000;
    }
}

