package testdata;

import rs.codecentric.meetup.diioc.annotations.Component;
import rs.codecentric.meetup.diioc.annotations.Inject;

@Component
public class ChildClass extends ClassToExtend {

    @Inject
    private ParentClass parent;

}
