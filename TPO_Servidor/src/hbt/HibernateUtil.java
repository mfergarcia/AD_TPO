package hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.ClienteEmpresaEntity;
import entities.ClientePersonaEntity;
import entities.CtaCteEntity;
import entities.DireccionEntity;


public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
        	 config.addAnnotatedClass(ClientePersonaEntity.class);
        	 config.addAnnotatedClass(ClienteEmpresaEntity.class);
        	 config.addAnnotatedClass(CtaCteEntity.class);
             config.addAnnotatedClass(DireccionEntity.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
