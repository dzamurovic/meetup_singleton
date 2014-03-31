package rs.codecentric.meetup.diioc.example;

public interface Resourcable {

    boolean isResourceful();

    void becomeResourceful() throws ResourceAlreadyResourcefulException;

}
