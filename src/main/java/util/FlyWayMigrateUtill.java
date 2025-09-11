package util;

import org.flywaydb.core.Flyway;
import org.hibernate.cfg.Configuration;

public class FlyWayMigrateUtill {

    public static void applyMigrations() {
        try {
            Configuration hiberConfig = new Configuration().configure();

            String url = hiberConfig.getProperty("hibernate.connection.url");
            String user = hiberConfig.getProperty("hibernate.connection.username");
            String password = hiberConfig.getProperty("hibernate.connection.password");

            Flyway flyway = Flyway.configure()
                                  .dataSource(url, user, password)
                    .locations("classpath:db/migration")
                    .baselineOnMigrate(true)
                                  .baselineVersion("0")
                                  .load();

            flyway.migrate();
            System.out.println("Миграции Flyway успешно применены");
        } catch (Exception e) {
            System.out.println("Ошибка в Flyway: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
