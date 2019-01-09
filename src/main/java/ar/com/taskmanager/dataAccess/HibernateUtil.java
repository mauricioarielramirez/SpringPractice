package ar.com.taskmanager.dataAccess;

import ar.com.taskmanager.model.domain.Task;
import ar.com.taskmanager.model.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.Mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ArielRamirez on 7/1/2019.
 */
public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;
    private static StandardServiceRegistry registry;
    private static Mapping mapping;

    static {
        try {
            Configuration configuration = new Configuration();
            //configuration.configure("hibernate.cfg.xml");
            //ourSessionFactory = configuration.buildSessionFactory();

            //Configuracion mediante código
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            Map<String,String> setting = new HashMap<>();
            setting.put(Environment.URL,"jdbc:mysql://localhost:3306/springtest?serverTimezone=UTC");
            setting.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
            setting.put(Environment.USER,"ariel");
            setting.put(Environment.PASS,"LimaHotel96*");
            setting.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");
            setting.put(Environment.AUTOCOMMIT,"true");

            setting.put(Environment.DEFAULT_ENTITY_MODE,"pojo");
            setting.put(Environment.HBM2DDL_AUTO,"update");
            setting.put(Environment.SHOW_SQL,"true");
            setting.put(Environment.FORMAT_SQL,"true");


            // Apply settings
            registryBuilder.applySettings(setting);

            // Create registry
            registry = registryBuilder.build();

            // Create MetadataSources | En el metadata cargamos el registro y cargamos las clases con anotaciones POJO
            MetadataSources sources = new MetadataSources(registry);
            sources.addAnnotatedClass(Task.class);
            sources.addAnnotatedClass(User.class);

            // Create Metadata
            Metadata metadata = sources.getMetadataBuilder().build();


            // Create SessionFactory
            ourSessionFactory = metadata.getSessionFactoryBuilder().build();



        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


}