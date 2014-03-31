package rs.codecentric.meetup.diioc.example;

import rs.codecentric.meetup.diioc.annotations.Component;
import rs.codecentric.meetup.diioc.annotations.Inject;

@Component
public class Controller {

    @Inject
    private ManageCapable manager;

    public Resourcable doSomething(Resource aResource) {
        return manager.manage(aResource);
    }

}
