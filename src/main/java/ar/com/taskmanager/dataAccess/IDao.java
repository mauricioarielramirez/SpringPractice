package ar.com.taskmanager.dataAccess;

import ar.com.taskmanager.customException.CustomException;

import java.util.List;

/**
 * Created by ArielRamirez on 7/1/2019.
 */
public interface IDao<T> {
    public void add(T object) throws CustomException;
    public void modify(T object) throws CustomException;;
    public void delete(Long id) throws CustomException;;
    public T get(Long id) throws CustomException;
    public List<T> listEntities(Integer lastPage, Integer rangePagination) throws CustomException;
    public List<T> findByExample(T object,Integer lastPage, Integer rangePagination) throws CustomException;
    public List<T> findLikeByExample(T object,Integer lastPage, Integer rangePagination) throws CustomException;
}
