package fauna.creature;

import fauna.Animal;

import java.util.HashMap;

public class Pet extends Animal {
    private final String nickname;

    private int intelligence;
    private final HashMap<String, String>commands = new HashMap<>();

    private Pet(Animal animal, String nickname) {
        super(animal.getMaxEnergy());
        this.nickname = nickname;
        this.intelligence = 0;
    }

    public static Pet tame (Animal animal, String nickname){
        if (animal == null || nickname == null || nickname.isEmpty() || nickname.isBlank()){
            return null;
        }else {
            return new Pet(animal, nickname);
        }
    }

    public static Pet tame (Animal animal, String nickname, int intelligence){
        if (animal == null || nickname == null || nickname.isEmpty() || nickname.isBlank()){
            return null;
        }else {
            Pet pet = new Pet(animal, nickname);
            pet.setIntelligence(Math.max(intelligence, 0));
            return pet;
        }
    }

    public String getNickname(){
        return nickname;
    }

    private void setIntelligence(int intelligence){
        this.intelligence = intelligence;
    }

    @Override
    public void eat() {
        increaseEnergy(getMaxEnergy()/2);
    }

    @Override
    public void sleep() {
        increaseEnergy(getMaxEnergy()/2);
    }

    public boolean learnCommand(String name, String action){
        return false;
    }
}
