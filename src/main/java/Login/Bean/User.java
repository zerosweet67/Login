package Login.Bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {
	
	int userid; //流水號
	
	@NotEmpty(message = "{user.username.notempty}")
	@Size(min = 3, max = 15, message = "{user.username.size}")
	String username; 
	
	@NotEmpty(message = "{user.password.notempty}")
	@Size(min=1,max = 16, message = "{user.password.size}")
	String userpwd; // User 密碼
	String signupdate; // 註冊日期
	
    @NotBlank(message = "{user.email.notblank}")
    @Email(message =  "{user.email.Email}")
	String email;   // 使用者信箱
    
    Integer verifystate;

}
