package fauna;

import java.util.HashMap;

public abstract class Pet extends Animal {
    private final String name;

    /**
     * Directly Proportional to the number of commands the pet can remember
     */
    private final int intelligence;

    private final HashMap<String, String>commands = new HashMap<>();

    protected Pet(int maxEnergy, int intelligence, String name) {
        super(maxEnergy);
        this.intelligence = intelligence;
        this.name = name;
    }

    public static Pet petWithName(String name){
        return new Pet(0, 0,name) {
            @Override public void eat() {}
            @Override public void sleep() {}
        };
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

    public String doCommand(String commandName){
        return commands.get(commandName);
    }

    public int getIntelligence(){
        return intelligence;
    }

    public String getName(){
        return name;
    }
}
