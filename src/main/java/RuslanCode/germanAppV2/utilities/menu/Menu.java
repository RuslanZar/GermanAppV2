package RuslanCode.germanAppV2.utilities.menu;


import java.util.ArrayList;
import java.util.List;

public class Menu {
    static List<MenuElement> menuElements = new ArrayList<>();

    public Menu() {
        menuElements.add(new MenuElement("/nouns", "Nomen", "menu-button"));
        menuElements.add(new MenuElement("/adad", "Adjective", "menu-button"));
        menuElements.add(new MenuElement("/verbs", "Verben", "menu-button"));
        menuElements.add(new MenuElement("/phrases", "Phrasen", "menu-button"));
        menuElements.add(new MenuElement("/exerciseGeneration", "Üben", "menu-button"));
        menuElements.add(new MenuElement("/profile", "Ihr Profil", "menu-button"));
        menuElements.add(new MenuElement("/logout", "Abmelden", "logout"));
    }

    public static List<MenuElement> getMenuList(){
        return menuElements;
    }

}