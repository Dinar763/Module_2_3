import util.FlyWayMigrateUtill;
import view.MainView;

public class App {
    public static void main(String[] args) {
        FlyWayMigrateUtill.applyMigrations();
        new MainView().start();
    }
}
