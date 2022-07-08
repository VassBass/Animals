package fauna.creature;

import exception.NotEnoughEnergyException;
import fauna.Animal;
import fauna.Pet;
import fauna.organ.Fangs;
import fauna.organ.Glottis;
import fauna.organ.Hands;
import fauna.organ.Legs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Human extends Pet implements Legs, Fangs, Glottis, Hands {
    private final String surname;

    private final ArrayList<Pet>pets = new ArrayList<>();

    protected Human(String name, String surname, int maxEnergy, int intelligence) {
        super(maxEnergy, intelligence, name);
        this.surname = surname;
    }

    public static Human create(String name, String surname, int maxEnergy, int intelligence){
        if (name == null || surname == null || maxEnergy <= 0 || intelligence <= 0
                || name.isEmpty() || name.isBlank() || surname.isEmpty() || surname.isBlank()){
            return null;
        }else {
            return new Human(name, surname, maxEnergy, intelligence);
        }
    }

    public String getSurname(){
        return surname;
    }
    
    public boolean addPet(Pet pet){
        if (pets.contains(pet)){
            System.out.println(this + " already has a pet named " + pet);
            return false;
        }else {
            return pets.add(pet);
        }
    }

    public Pet getPet(String name){
        if (name == null || name.isEmpty() || name.isBlank()) return null;

        for (Pet pet : pets){
            if (pet.getName().equals(name)) return pet;
        }

        return null;
    }

    public void checkPets() {
        pets.removeIf(pet -> isDead());
    }

    public boolean teachPetCommand(String petName, String commandName, String commandAction){
        if (petName == null || petName.isEmpty() || petName.isBlank()) {
            System.out.println(getName() + surname + " could not pronounce such a name");
            return false;
        }else if (commandName == null || commandName.isEmpty() || commandName.isBlank()
                || commandAction == null || commandAction.isEmpty() || commandAction.isBlank()){
            System.out.println(getName() + surname + " could not pronounce such a command");
            return false;
        } else {
            int index = pets.indexOf(Pet.petWithName(petName));
            if (index >= 0){
                if (pets.get(index).learnCommand(commandName, commandAction)){
                    System.out.println(this + " taught " + petName + " the command \"" + commandName + "\".");
                    return true;
                }else {
                    System.out.println(this + " failed to teach " + petName + " the command \"" + commandName + "\".");
                    return false;
                }
            }else {
                System.out.println(this + " called " + petName + " but none of his pets responded.");
                return false;
            }
        }
    }

    public void callCommand(String petName, String commandName){
        if (petName == null || petName.isEmpty() || petName.isBlank()) {
            System.out.println(getName() + surname + " could not pronounce such a name");
        }else if (commandName == null || commandName.isEmpty() || commandName.isBlank()){
            System.out.println(getName() + surname + " could not pronounce such a command");
        } else {
            int index = pets.indexOf(Pet.petWithName(petName));
            if (index >= 0){
                System.out.println(this + " told to " + petName + " \"" + commandName + "\"");
                String commandResult = pets.get(index).doCommand(commandName);
                if (commandResult == null){
                    System.out.println(petName + " do nothing.");
                }else {
                    System.out.println(petName + " " + commandResult);
                }
            }else {
                System.out.println(this + " called " + petName + " but none of his pets responded.");
            }
        }
    }

    public void callCommandToAll(String commandName){
        if (commandName == null || commandName.isEmpty() || commandName.isBlank()){
            System.out.println(getName() + surname + " could not pronounce such a command");
        } else {
            for (Pet p : pets) {
                System.out.println(this + " told to " + p + " \"" + commandName + "\"");
                String commandResult = p.doCommand(commandName);
                if (commandResult == null) {
                    System.out.println(p + " do nothing.");
                } else {
                    System.out.println(p + " " + commandResult);
                }
            }
        }
    }

    @Override
    public void eat() {
        increaseEnergy(getMaxEnergy()/2);
        System.out.println(this + " eats.");
    }

    @Override
    public void sleep() {
        increaseEnergy(getMaxEnergy()/2);
        System.out.println(this + " sleep.");
    }

    @Override
    public void walk(int distance) throws NotEnoughEnergyException {
        if (distance < 200){
            decreaseEnergy(1);
        }else {
            decreaseEnergy(distance/100);
        }
        System.out.println(this + " walked " + distance + " meters.");
    }

    @Override
    public void run(int distance) throws NotEnoughEnergyException {
        if (distance < 100){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/50);
        }
        System.out.println(this + " ran " + distance + " meters.");
    }

    @Override
    public boolean jump(double height) throws NotEnoughEnergyException {
        int successChance;
        if (height < 1D) {
            decreaseEnergy(1);
            successChance = ThreadLocalRandom.current().nextInt(60, 101);
        }else if (height < 2D) {
            decreaseEnergy(2);
            successChance = ThreadLocalRandom.current().nextInt(0, 101);
        }else if (height < 2.45) {
            decreaseEnergy(5);
            successChance = ThreadLocalRandom.current().nextInt(0, 91);
        }else {
            System.out.println(this + " thought about jumping " + height + " meters high, but remembered that people cannot jump to such a height.");
            return false;
        }

        if (successChance < 80){
            System.out.println(this + " tried to jump to a height of" + height + " meter, but fell");
            return false;
        }else {
            System.out.println(this + " jumped to a height of" + " meter");
            return true;
        }
    }

    @Override
    public boolean bite(Animal animal) {
        if (animal.survive(20)){
            System.out.println(this + " tried to bite the " + animal + ", but failed");
            return false;
        }else {
            System.out.println(this + " bites the " + animal);
            return true;
        }
    }

    @Override
    public void makeSound() {
        System.out.println("Hello! My name is " + getName() + " " + surname + ".");
    }

    @Override
    public boolean climb() throws NotEnoughEnergyException {
        if (ThreadLocalRandom.current().nextInt(60, 101) > 80){
            decreaseEnergy(2);
            System.out.println(this + " climbed a tree and looked around. Then he climb off.");
            return true;
        }else {
            System.out.println(this + " tries to climb a tree, but falls.");
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), surname, getIntelligence(), getMaxEnergy());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (obj == this) return true;

        Human human = (Human) obj;
        return getName().equals(human.getName()) && surname.equals(human.getSurname());
    }

    @Override
    public String toString() {
        return getName() + " " + surname;
    }
}
