package testdata;

import rs.codecentric.meetup.diioc.annotations.Component;
import rs.codecentric.meetup.diioc.annotations.Inject;

@Component
public class ParentClass {

    @Inject
    private ChildClass child;

    @Inject
    private ChildClass child2;

    @Inject
    private Long childLong;

}
