package ar.com.taskmanager.dataAccess;

import ar.com.taskmanager.customException.CustomException;

/**
 * Created by ArielRamirez on 7/1/2019.
 */
public interface IDao<T> {
    public void add(T object) throws CustomException;
    public void modify(T object) throws CustomException;;
    public void delete(Long id) throws CustomException;;
    public T get(Long id) throws CustomException;;
}
