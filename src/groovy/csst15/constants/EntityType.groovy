package csst15.constants

/**
 * Created by Emil Matevosyan
 * Date: 3/1/15.
 */
enum EntityType {
    METHOD('Method'),
    FIELD('Field'),
    VENUE('Venue'),
    THEORY('Theory')

    private final String name

    EntityType(String name) {
        this.name = name
    }

    String getName() {
        return name
    }
}