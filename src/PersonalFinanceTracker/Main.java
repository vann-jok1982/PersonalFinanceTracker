package PersonalFinanceTracker;

import PersonalFinanceTracker.view.View;
import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        View view=new ConsoleUI();
        view.start();
    }
}