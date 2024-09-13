package RuslanCode.germanAppV2.utilities.menu;

public class MenuElement {
    String endpoint;
    String displayName;
    String htmlClass;

    public MenuElement(String endpoint, String displayName, String htmlClass) {
        this.endpoint = endpoint;
        this.displayName = displayName;
        this.htmlClass = htmlClass;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getHtmlClass() {
        return htmlClass;
    }
}
