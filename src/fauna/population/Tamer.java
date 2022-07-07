package fauna.population;

import fauna.creature.Human;

public class Tamer extends Human {

    protected Tamer(String name, String surname, int maxEnergy, int intelligence) {
        super(name, surname, maxEnergy, intelligence);
    }

    public static Tamer educate(Human human){
        if (human == null){
            return null;
        }else {
            return new Tamer(human.getName(), human.getSurname(), human.getMaxEnergy(), human.getIntelligence());
        }
    }


}
