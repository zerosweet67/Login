package Util;

import org.springframework.jdbc.core.JdbcTemplate;

public class Test {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void testDatabaseConnection() {
        try {
            int result = jdbcTemplate.queryForObject("SELECT username FROM uderdata", Integer.class);
            System.out.println("Database connection successful. Result: " + result);
        } catch (Exception e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
    }
}
