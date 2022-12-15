package space_travel.database_flyway_service;

import org.flywaydb.core.Flyway;
import space_travel.preferences.PropertiesFileReader;

import java.io.IOException;

public class DatabaseFlyWayService {
    public void initFlyWayDb() throws IOException {
        String url = new PropertiesFileReader().getPath();

        Flyway flyway = Flyway.configure()
                .dataSource(url,null,null)
                .load();
        flyway.migrate();
    }

    public static void main(String[] args) throws IOException {
        new DatabaseFlyWayService().initFlyWayDb();
    }
}
