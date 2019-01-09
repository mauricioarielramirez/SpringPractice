package ar.com.taskmanager.service;

import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.dataAccess.UserDao;
import ar.com.taskmanager.model.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by ArielRamirez on 9/1/2019.
 */
@Service
public class UserService implements IService<User>{
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    };

    @Override
    public User getEntity(Long id) throws CustomException {
        return userDao.get(id);
    }

    @Override
    public Boolean addEntity(User entity) throws CustomException {
        userDao.add(entity);
        return true;
    }

    @Override
    public Boolean modifyEntity(User entity) throws CustomException {
        userDao.modify(entity);
        return true;
    }

    @Override
    public Boolean deleteEntity(User entity) throws CustomException {
        userDao.delete(entity.getUserId());
        return true;
    }

    @Override
    public Boolean deleteEntity(Long id) throws CustomException {
        return true;
    }

    public Boolean verifyUser(User user) throws CustomException {
        return (getEntity(user.getUserId())!=null?true:false);
    }
}
