package ar.com.taskmanager.service;


import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.dataAccess.TaskDao;
import ar.com.taskmanager.model.domain.Task;
import ar.com.taskmanager.model.domain.User;
import ar.com.taskmanager.model.dto.IdentifierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ArielRamirez on 7/1/2019.
 */
@Service
public class TaskService implements IService<Task>{
    private TaskDao taskDao;
    private UserService userService;
    public static final String LINK = "link";
    public static final String UNLINK = "unlink";


    @Autowired
    public TaskService(TaskDao taskDao, UserService userService) {
        this.taskDao = taskDao;
        this.userService = userService;
    }

    @Override
    public Task getEntity(Long id) throws CustomException {
        return taskDao.get(id);
    }

    @Override
    public Boolean addEntity(Task entity) throws CustomException {
        //entity.setTaskDateCreate(Calendar.getInstance().getTime());
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

    public boolean verifyTask(Long id) throws CustomException {
        return (getEntity(id).getId()!=null?true:false);
    }

    // para vincular verifico que se hayan ingresado id válidos de task y user
    public Boolean linkUserToTask(IdentifierDTO identifierDTO, String typeOperation) throws CustomException {
        if ((userService.verifyUser( Long.valueOf(identifierDTO.getMapping().get("userId")))) &&
                verifyTask(Long.valueOf(identifierDTO.getMapping().get("taskId"))) ) {
                    Task task = getEntity(Long.valueOf(identifierDTO.getMapping().get("taskId")));
                    User user = userService.getEntity(Long.valueOf(identifierDTO.getMapping().get("userId")));
                    if (typeOperation.equals(TaskService.LINK)) {
                        task.getOwners().add(user);
                        modifyEntity(task);
                    } else {
                        unLinkUserToTask(task,user);
                    }
            return true;
        } else {
            return false;
        }
    }

    private void unLinkUserToTask(Task task, User user) throws CustomException {
        Set<User> users = new HashSet<User>();
        users = task.getOwners();
        users.remove(user);
        modifyEntity(task);
    }
}
