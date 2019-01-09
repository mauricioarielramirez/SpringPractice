package ar.com.taskmanager.dataAccess;

import ar.com.taskmanager.customException.CustomException;
import org.hibernate.Session;

import java.util.concurrent.ExecutionException;

/**
 * Created by ArielRamirez on 8/1/2019.
 * Genero la clase Dao para practicidad de manejo de los objetos Session y Transaction
 */
public class Dao {
    protected Session session;

    /**
     *Operación de apertura
     */
    public void verifySessionAndTransaction() throws CustomException{
        try {
            if (session == null || session.getTransaction().isActive()==false) {
                session=HibernateUtil.getSession();
                if (session.getTransaction().isActive() == false) {
                    session.getTransaction().begin();
                }
            }
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
        }

    }

    /**
     * Cierra la transaccion y luego la sesion
     * @param
     */
    public void closeSessionAndTransaction () throws CustomException{
        try {
            if (session!=null) {
                if (session.getTransaction().isActive() == true ) {
                    session.getTransaction().commit();
                    session.close();
                }
            }
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
        }

    }
}
