package Login.Dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Login.Bean.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<User> rowMapper = (ResultSet rs, int rowNum) -> {
		User user = new User();
		user.setUserid(rs.getInt("userid"));
		user.setUsername(rs.getString("username"));
		user.setUserpwd(rs.getString("userpwd"));
		user.setSignupdate(rs.getString("signupdate"));
		user.setEmail(rs.getString("email"));
		user.setVerifystate(rs.getInt("verifystate"));
		return user;

	};

	@Override
	public int addUser(User user) {
		String sql = "insert into userdata (userid,username,userpwd,email) " + "values(userid.nextval,?,?,?)";
		int rowcount = jdbcTemplate.update(sql, user.getUsername(), user.getUserpwd(), user.getEmail());
		System.out.println("註冊成功");
		return rowcount;
	}

	@Override
	public List<User> findAllUsers() {
		String sql = "select userid,username,userpwd,signupdate,email,verifystate from userdata order by userid";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		String sql = "select userid,username,userpwd,signupdate,email,verifystate from userdata where username =?";
		try {
			User user = jdbcTemplate.queryForObject(sql, rowMapper, username);
			System.out.println("找到使用者 : " + username);

			return Optional.of(user);
		} catch (Exception e) {
			System.out.println("查無使用者" + username);
			return Optional.empty();

		}
	}

	@Override
	public Optional<User> findPasswordByUsername(String username) {
		String sql = "select userpwd from userdata where username =?";
		try {
			User user = jdbcTemplate.queryForObject(sql, rowMapper, username);
			return Optional.of(user);
		} catch (Exception e) {
			return Optional.empty();

		}
	}

	@Override
	public Optional<User> findEmailByUsername(String username) {
		String sql = "select email,verifystate from userdata where username =?";
		try {
			User user = jdbcTemplate.queryForObject(sql, rowMapper, username);
			return Optional.of(user);
		} catch (Exception e) {
			return Optional.empty();

		}
	}

	@Override
	public int updatePasswordByEmail(String email, String newPassword) {
		String sql = "update userdata set userpwd = ? where email = ?";
		return jdbcTemplate.update(sql, newPassword, email);

	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		String sql = "select userid,username,userpwd,signupdate,email,verifystate from userdata where email =?";
		try {
			User user = jdbcTemplate.queryForObject(sql, rowMapper, email);
			return Optional.of(user);
		} catch (Exception e) {
			return Optional.empty();

		}
	}

	@Override
	public Optional<User> findMaxUserId() {
		String sql = "select max(userid)  from userdata ";
		try {
			User user = jdbcTemplate.queryForObject(sql, rowMapper);
			return Optional.of(user);
		} catch (Exception e) {
			return Optional.empty();

		}
	}

	@Override
	public int updateUsersVerifystate(String email) {
		String sql = "update userdata set verifystate = 2  where email = ?";
		return jdbcTemplate.update(sql, email);
	}

}
