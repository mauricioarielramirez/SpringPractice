package ar.com.taskmanager.dataAccess;

import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.model.domain.Task;
import ar.com.taskmanager.model.domain.User;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by ArielRamirez on 9/1/2019.
 */
public class UserDao extends Dao implements IDao<User> {
    @Override
    public void add(User object) throws CustomException {
        try {
            verifySessionAndTransaction();
            session.persist(object);
            closeSessionAndTransaction();
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }
    }

    @Override
    public void modify(User object) throws CustomException {
        try {
            verifySessionAndTransaction();
            session.update(object);
            closeSessionAndTransaction();
        } catch (Exception ex){
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }
    }

    @Override
    public void delete(Long id) throws CustomException {
        try {
            verifySessionAndTransaction();
            session.delete(get(id)); //Aprovecho el método que tengo y lo busco de aca
            closeSessionAndTransaction();
        }catch (Exception ex){
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }
    }

    @Override
    public User get(Long id) throws CustomException {
        try {
            verifySessionAndTransaction();
            return (session.get(User.class,id) !=null ?session.get(User.class,id):new User());
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }
    }

    @Override
    public List<User> listEntities(Integer lastPage, Integer rangePagination) throws CustomException  {
        try {
            verifySessionAndTransaction();
            Criteria cr = session.createCriteria(User.class);
            return cr.list();
        }catch (Exception ex){
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }
    }

    @Override
    public List<User> findByExample(User object,Integer lastPage, Integer rangePagination) throws CustomException {
        return null;
    }

    @Override
    public List<User> findLikeByExample(User object,Integer lastPage, Integer rangePagination) throws CustomException {
        return null;
    }
}
