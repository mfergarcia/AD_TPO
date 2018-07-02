package hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.ArticuloEnStockEntity;
import entities.ArticuloEntity;
import entities.ClienteEmpresaEntity;
import entities.ClienteEntity;
import entities.ClientePersonaEntity;
import entities.CtaCteEntity;
import entities.DireccionEntity;
import entities.FacturaEntity;
import entities.ItemArticuloEntity;
import entities.ItemOCEntity;
import entities.MovStockAjusteEntity;
import entities.MovStockCompraEntity;
import entities.MovStockMantenimientoEntity;
import entities.MovStockVentaEntity;
import entities.MovimientoStockEntity;
import entities.OrdenDeCompraEntity;
import entities.OrdenPedidoRepoEntity;
import entities.PagoEntity;
import entities.PedidoEntity;
import entities.RemitoEntity;
import entities.StockEntity;
import entities.UsuarioClienteEntity;
import entities.UsuarioEmpleadoEntity;


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
        	 config.addAnnotatedClass(ClienteEntity.class);
        	 config.addAnnotatedClass(CtaCteEntity.class);
             config.addAnnotatedClass(DireccionEntity.class);
             config.addAnnotatedClass(ArticuloEnStockEntity.class);
             config.addAnnotatedClass(ArticuloEntity.class);
             config.addAnnotatedClass(FacturaEntity.class);
             config.addAnnotatedClass(ItemArticuloEntity.class);
             config.addAnnotatedClass(MovimientoStockEntity.class);
             config.addAnnotatedClass(MovStockVentaEntity.class);
             config.addAnnotatedClass(MovStockCompraEntity.class);
             config.addAnnotatedClass(MovStockAjusteEntity.class);
             config.addAnnotatedClass(MovStockMantenimientoEntity.class);
             config.addAnnotatedClass(OrdenDeCompraEntity.class);
             config.addAnnotatedClass(PagoEntity.class);
             config.addAnnotatedClass(PedidoEntity.class);
             config.addAnnotatedClass(RemitoEntity.class);
             config.addAnnotatedClass(StockEntity.class);
             config.addAnnotatedClass(OrdenPedidoRepoEntity.class);
             config.addAnnotatedClass(ItemOCEntity.class);
             config.addAnnotatedClass(UsuarioClienteEntity.class);
             config.addAnnotatedClass(UsuarioEmpleadoEntity.class);
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
