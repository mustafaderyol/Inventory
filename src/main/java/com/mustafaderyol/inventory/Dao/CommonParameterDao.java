package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.CommonParameter;
import com.mustafaderyol.inventory.idao.ICommonParameterDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MstfDryl
 */
@Repository
@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
public class CommonParameterDao implements ICommonParameterDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(CommonParameter object) {
        try
        {
            em.persist(object);
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public List<CommonParameter> allFunc() {
        Query request = em.createNamedQuery("allCommonParameter");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(CommonParameter.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(CommonParameter object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public CommonParameter findByIdFunc(Long id) {
        
        CommonParameter p = new CommonParameter();
        try
        {
            p =em.find(CommonParameter.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
