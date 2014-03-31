package rs.codecentric.meetup.diioc.example;

import rs.codecentric.meetup.diioc.annotations.Component;
import rs.codecentric.meetup.diioc.annotations.Inject;

@Component
public class Manager implements ManageCapable {

    @Inject
    private Repository repository;

    @Override
    public Resourcable manage(Resourcable aResource) {
        if (!aResource.isResourceful()) {
            try {
                aResource.becomeResourceful();
            } catch (ResourceAlreadyResourcefulException e) {
                // Do nothing.
            }
        }
        return aResource;
    }

}
