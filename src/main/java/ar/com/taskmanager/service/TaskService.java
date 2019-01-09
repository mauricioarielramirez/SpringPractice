package ar.com.taskmanager.service;


import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.dataAccess.TaskDao;
import ar.com.taskmanager.model.domain.Task;
import ar.com.taskmanager.model.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by ArielRamirez on 7/1/2019.
 */
@Service
public class TaskService implements IService<Task>{
    private TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task getEntity(Long id) throws CustomException {
        return taskDao.get(id);
    }

    @Override
    public Boolean addEntity(Task entity) throws CustomException {
        taskDao.add(entity);
        return true;
    }

    @Override
    public Boolean modifyEntity(Task entity) throws CustomException {
        taskDao.modify(entity);
        return true;
    }

    @Override
    public Boolean deleteEntity(Task entity) throws CustomException {
        taskDao.delete(entity.getId()); //me llevo el id, lo demás se lo derivo al dao
        return true;
    }

    @Override
    public Boolean deleteEntity(Long id) throws CustomException {
        return null;
    }

    // Quiero probar si le levanta un user service
    public Boolean linkUserToTask(Task task,User user,UserService userService) throws CustomException {
        if (userService.verifyUser(user)) {
            task.getOwners().add(user);
            System.out.print("Hola");
        }
        return true;
    }
}
