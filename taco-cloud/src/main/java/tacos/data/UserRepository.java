package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * Spring Data JPA会在运行时自动生成这个接口的实现(DSL语言)。所以，我们现在就可以编写使用该存储库的自定义用户详情服务了。
	 * 
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

}
