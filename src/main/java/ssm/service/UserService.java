package ssm.service;

import ssm.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> list();
    User findById(int id);
    List<User> find(Map<String,Object> map);
    Long getTotal(Map<String,Object> map);
    User login(User user);
    void save(User user);
    void delete(int[] ids);
    int update(User user);
}
