package tacos.messaging;

import tacos.UserVO;

public interface UserMessagingService {

  void sendUser(UserVO user);
  
}
