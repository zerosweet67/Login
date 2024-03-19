package Login.Dao;

import java.util.List;
import java.util.Optional;

import Login.Bean.User;

public interface UserDao {

	// 新增User

	int addUser(User user);

	// 找到所有User
	List<User> findAllUsers();

	// 用Username找User
	Optional<User> findUserByUsername(String username);

	// 用Username找密碼
	Optional<User> findPasswordByUsername(String username);

	// 用信箱找到使用者
	Optional<User> findUserByEmail(String email);

	// 用信箱找密碼
	Optional<User> findEmailByUsername(String username);

	// 根據使用者 Email 修改密碼 (使用者忘記密碼用)
	int updatePasswordByEmail(String email, String newPassword);
	
	//找目前最大的userid
	Optional<User> findMaxUserId();
	
	//修改使用者驗證狀態
	int updateUsersVerifystate(String email);

}
