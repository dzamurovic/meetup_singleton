package rs.codecentric.meetup.diioc.example;

import rs.codecentric.meetup.diioc.annotations.Component;

@Component
public class Repository {

    public Resourcable save(Resourcable aResource) {
        // Here we "persist" the given resource
        // and return it like we really did it.
        return aResource;
    }
}
