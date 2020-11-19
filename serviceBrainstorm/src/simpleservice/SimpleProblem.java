package simpleservice;

public class SimpleProblem extends Error {
    public SimpleProblem() {
        super("resistance is futile");
    }
    public SimpleProblem(String message) {
        super(message);
    }
}
