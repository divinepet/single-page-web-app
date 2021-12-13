package edu.school21.cinema.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.models.User;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Repository
public class UsersRepository extends JdbcTemplate {
    private final String tableName = "users_cinema";
    private final RowMapper<User> ROW_MAPPER;

    public UsersRepository(HikariDataSource dataSource) {
        super.setDataSource(dataSource);
        this.ROW_MAPPER = (ResultSet rs, int rowNum)
                -> new User(rs.getString("email"), rs.getString("first_name"),
                rs.getString("last_name"), rs.getString("phone_number"), rs.getString("password"), rs.getString("pic_name"), null);
    }

    public Optional<User> findByEmail(String email) {
        try {
            User user = super.queryForObject(format("SELECT * FROM %s WHERE email = '%s';", tableName, email), ROW_MAPPER);
            return (Optional.ofNullable(user));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(User user)  {
        super.update(format("INSERT INTO %s (email, first_name, last_name, phone_number, password)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s');",
                tableName, user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getPassword()));
    }

    public void setUserPic(String email, String picName) {
        super.update(format("UPDATE %s " +
                "SET pic_name = '%s' " +
                "WHERE email = '%s';", tableName, picName, email));
    }

    public void setLogInfo(String email, String logInfo) {
        super.update(format("UPDATE %s " +
                "SET loginfo = array_append(loginfo, %s) " +
                "WHERE email = '%s';", tableName, logInfo, email));
    }

    @SneakyThrows
    public List<String> getLogInfo(String email) {
        List<String> logInfo;
            logInfo = Arrays.asList((String[])super.queryForObject(format(
                    "select loginfo " + "from %s " + "where email='%s'",
                            tableName, email), Array.class)
                    .getArray());
            return (logInfo);
    }
}
