package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.Inventory;
import com.mustafaderyol.inventory.entity.InventoryRelationship;
import com.mustafaderyol.inventory.idao.IInventoryRelationshipDao;
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
public class InventoryRelationshipDao implements IInventoryRelationshipDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(InventoryRelationship object) {
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
    public List<InventoryRelationship> allFuncLeft(Inventory inventory) {
        Query request = em.createNamedQuery("allInventoryRelationshipByInvetory1").setParameter("inventory1", inventory);
        return request.getResultList();
        
    }

    @Override
    public List<InventoryRelationship> allFuncRight(Inventory inventory) {
        Query request = em.createNamedQuery("allInventoryRelationshipByInvetory2").setParameter("inventory2", inventory);
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(InventoryRelationship.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(InventoryRelationship object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public InventoryRelationship findByIdFunc(Long id) {
        
        InventoryRelationship p = new InventoryRelationship();
        try
        {
            p =em.find(InventoryRelationship.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }
    
}
