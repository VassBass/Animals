package fauna;

public interface Pet {
    void learnCommand(String name, String action);
    void forgetCommand(String name);
    void doCommand(String name);
}
