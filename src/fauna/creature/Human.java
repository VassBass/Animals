package fauna.creature;

import exception.NotEnoughEnergyException;
import fauna.Animal;
import fauna.Predator;
import fauna.limb.Legs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Human extends Predator implements Legs {
    private final String name, surname;

    private final ArrayList<Pet>pets = new ArrayList<>();

    private Human(String name, String surname, int maxEnergy) {
        super(maxEnergy);
        this.name = name;
        this.surname = surname;
    }

    public static Human create(String name, String surname, int maxEnergy){
        if (name == null || surname == null || maxEnergy <= 0
                || name.isEmpty() || name.isBlank() || surname.isEmpty() || surname.isBlank()){
            return null;
        }else {
            return new Human(name, surname, maxEnergy);
        }
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public void addPet(Pet pet){
        pets.add(pet);
    }

    public Pet getPet(String nickname){
        if (nickname == null || nickname.isEmpty() || nickname.isBlank()) return null;

        for (Pet pet : pets){
            if (pet.getNickname().equalsIgnoreCase(nickname)){
                return pet;
            }
        }

        return null;
    }

    @Override
    public void eat() {
        increaseEnergy(getMaxEnergy()/2);
    }

    @Override
    public void sleep() {
        increaseEnergy(getMaxEnergy()/2);
    }

    @Override
    public void walk(int distance) throws NotEnoughEnergyException {
        if (distance < 200){
            decreaseEnergy(1);
        }else {
            decreaseEnergy(distance/100);
        }
    }

    @Override
    public void run(int distance) throws NotEnoughEnergyException {
        if (distance < 100){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/50);
        }
    }

    @Override
    public boolean jump(double height) throws NotEnoughEnergyException {
        int successChance;
        if (height < 1D) {
            decreaseEnergy(1);
            successChance = ThreadLocalRandom.current().nextInt(60, 101);
        }else if (height < 2D) {
            decreaseEnergy(2);
            successChance = ThreadLocalRandom.current().nextInt(40, 101);
        }else if (height < 4) {
            decreaseEnergy(5);
            successChance = ThreadLocalRandom.current().nextInt(20, 101);
        }else {
            decreaseEnergy(10);
            successChance = ThreadLocalRandom.current().nextInt(0, 101);
            if (successChance < 80){
                if (!survive(40)){
                    this.died();
                    System.out.println("Oooops... " + this + " unsuccessfully fell and die...");
                }
                return false;
            }
        }

        return successChance >= 80;
    }

    @Override
    public boolean hunt(Animal prey) {
        return !prey.survive(90);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (obj == this) return true;

        Human human = (Human) obj;
        return name.equals(human.getName()) && surname.equals(human.getSurname());
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}