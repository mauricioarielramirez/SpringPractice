package ar.com.taskmanager.dataAccess;

import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.model.domain.Task;
import org.springframework.stereotype.Repository;


/**
 * Created by ArielRamirez on 7/1/2019.
 */
@Repository
public class TaskDao extends Dao implements IDao<Task> {

    @Override
    public void add(Task object) throws CustomException {
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
    public void modify(Task object) throws CustomException {
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
    public Task get(Long id) throws CustomException {
        try {
            verifySessionAndTransaction();
            return (session.get(Task.class,id) !=null ?session.get(Task.class,id):new Task());
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }
    }
}
