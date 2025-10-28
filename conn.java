import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

public class conn {
    Connection c;
    Statement s;

    public conn() {
        try {
            // Load credentials from .env or config file
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties"));

            String url = props.getProperty("DB_URL");
            String user = props.getProperty("DB_USER");
            String pass = props.getProperty("DB_PASS");

            c = DriverManager.getConnection(url, user, pass);
            s = c.createStatement();

            System.out.println("✅ Connected to database successfully.");
        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e);
        }
    }
}
