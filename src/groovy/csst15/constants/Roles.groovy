package csst15.constants

/**
 * Created by Emil Matevosyan
 * Date: 2/13/15.
 */
enum Roles {
    ADMIN('ADMIN'),
    USER('USER'),
    PUBLIC('PUBLIC')

    private final String name;

    Roles(String name) {
        this.name = name
    }

    String getName() {
        return name
    }
}