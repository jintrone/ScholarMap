package csst15.constants

/**
 * Created by Emil Matevosyan
 * Date: 2/16/15.
 */
enum Specializations {
    COMPUTER_SCIENCE('Computer Science'),
    ANTHROPOLOGY('Anthropology'),
    SOCIOLOGY('Sociology'),
    COMMUNICATIONS('Communications'),
    ECONOMICS('Economics'),
    GEOLOGY('Geology'),
    ADVERTISING('Advertising'),
    JOURNALISM('Journalism'),
    PSYCHOLOGY('Psychology'),
    SOCIAL_PSYCHOLOGY('Social Psychology'),
    INFORMATION_SCIENCE('Information Science'),
    FEMINISM('Feminism')

    private final String title;

    Specializations(String title) {
        this.title = title
    }

    String getTitle() {
        return title
    }
}