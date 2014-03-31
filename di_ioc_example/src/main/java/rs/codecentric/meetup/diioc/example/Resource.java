package rs.codecentric.meetup.diioc.example;


public class Resource implements Resourcable {

    @Override
    public boolean isResourceful() {
        return true;
    }

    @Override
    public void becomeResourceful() {
        throw new RuntimeException("I am already resourceful!");
    }

}
