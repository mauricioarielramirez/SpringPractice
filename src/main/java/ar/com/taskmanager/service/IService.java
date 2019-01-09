package ar.com.taskmanager.service;

/**
 * Created by ArielRamirez on 9/1/2019.
 */

import ar.com.taskmanager.customException.CustomException;

/**
 * Métodos bases del servicio
 */
public interface IService <T>{
    public T getEntity (Long id) throws CustomException;
    public Boolean addEntity(T entity) throws CustomException;
    public Boolean modifyEntity(T entity) throws CustomException;
    public Boolean deleteEntity(T entity) throws CustomException;
    public Boolean deleteEntity(Long id) throws CustomException;
}
