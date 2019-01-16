package ar.com.taskmanager.dataAccess;

import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.model.domain.Task;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;


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

    @Override
    public List<Task> listEntities(Integer lastPage, Integer rangePagination) throws CustomException  {
        try {
            verifySessionAndTransaction();
            Criteria cr = session.createCriteria(Task.class);
            cr.setFirstResult(lastPage);
            cr.setMaxResults(rangePagination);
            return cr.list();
        }catch (Exception ex){
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }
    }

    @Override
    public List<Task> findByExample(Task task,Integer lastPage, Integer rangePagination) throws CustomException {
        try {
            verifySessionAndTransaction();
            Criteria cr = session.createCriteria(Task.class);
            cr.add(Example.create(task));
            cr.setFirstResult(lastPage);
            cr.setMaxResults(rangePagination);
            return cr.list();
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }

    }

    @Override
    public List<Task> findLikeByExample(Task task,Integer lastPage, Integer rangePagination) throws CustomException {
        try {
            verifySessionAndTransaction();
            Criteria cr = session.createCriteria(Task.class);
            if (task.getTaskName() != null)
                cr.add(Restrictions.ilike("taskName",task.getTaskName(), MatchMode.ANYWHERE));
            if (task.getTaskDescription() != null)
                cr.add(Restrictions.ilike("taskDescription",task.getTaskDescription(), MatchMode.ANYWHERE));
            if (task.getTaskDateCreate()!=null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateAsText = sdf.format(task.getTaskDateCreate());
                cr.add(Restrictions.ilike("taskDateCreated",dateAsText,MatchMode.ANYWHERE));

            }
            cr.setFirstResult(lastPage);
            cr.setMaxResults(rangePagination);
            return cr.list();
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
            throw customException;
        }

    }
}
