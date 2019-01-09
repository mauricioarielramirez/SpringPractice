package ar.com.taskmanager.model.interfaces;

import ar.com.taskmanager.model.domain.User;


/**
 * Created by ArielRamirez on 3/1/2019.
 */
public interface ITask {
    public Boolean linkTask(User user);
    public Boolean unlinkTask(User user);
}
