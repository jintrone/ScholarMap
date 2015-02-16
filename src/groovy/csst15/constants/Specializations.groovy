package csst15.constants

/**
 * Created by Emil Matevosyan
 * Date: 2/16/15.
 */
enum Specializations {
    COMPUTER_SCIENCE('Computer Science'),
    ANTHROPOLOGY('Anthropology')

    private final String title;

    Specializations(String title) {
        this.title = title
    }

    String getTitle() {
        return title
    }
}