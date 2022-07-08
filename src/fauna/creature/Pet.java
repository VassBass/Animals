package fauna.creature;

import fauna.Animal;

import java.util.HashMap;

public abstract class Pet extends Animal {
    private final int intelligence;
    private final HashMap<String, String>commands = new HashMap<>();

    public Pet(int maxEnergy, int intelligence) {
        super(maxEnergy);
        this.intelligence = intelligence;
    }

    public boolean learnCommand(String name, String action){
        if (commands.containsKey(name)){
            commands.put(name, action);
            return true;
        }else if (commands.size() < intelligence){
            commands.put(name, action);
            return true;
        }else return false;
    }

    public boolean forgetCommand(String name){
        if (commands.containsKey(name)){
            commands.remove(name);
            return true;
        }else return false;
    }

    public int getIntelligence(){
        return intelligence;
    }
}
