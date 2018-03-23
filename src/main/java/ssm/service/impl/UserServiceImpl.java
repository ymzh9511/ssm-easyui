package ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.dao.UserMapper;
import ssm.domain.User;
import ssm.service.UserService;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    public void delete(int[] ids) {
        userMapper.delete(ids);
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public List<User> list() {
        List<User> userlist = userMapper.list();
        return userlist;
    }

    public List<User> find(Map<String, Object> map) {
        return userMapper.find(map);
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public Long getTotal(Map<String, Object> map) {
        return userMapper.getTotal(map);
    }

    public User login(User user) {
        return userMapper.findByUserName(user);
    }

    public void save(User user) {
        userMapper.save(user);
    }
}
