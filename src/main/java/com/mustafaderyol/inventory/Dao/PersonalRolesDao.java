package com.mustafaderyol.inventory.dao;

import com.mustafaderyol.inventory.entity.PersonalRoles;
import com.mustafaderyol.inventory.idao.IPersonalRolesDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MstfDryl
 */
@Repository
@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
public class PersonalRolesDao implements IPersonalRolesDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveFunc(PersonalRoles object) {
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
    public List<PersonalRoles> allFunc() {
        Query request = em.createNamedQuery("allPersonalRoles");
        return request.getResultList();
        
    }

    @Override
    public void deleteFunc(Long id) {
        try
        {
            em.remove(em.find(PersonalRoles.class,id));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void updateFunc(PersonalRoles object) {
        em.merge(object);
        em.flush();
    }

    @Override
    public PersonalRoles findByIdFunc(Long id) {
        
        PersonalRoles p = new PersonalRoles();
        try
        {
            p =em.find(PersonalRoles.class,id);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return p;
    }

    @Override
    public List<PersonalRoles> findByPersonalIdFunc(Long id) {
        Query request = em.createNamedQuery("allPersonalRolesByPersonalId").setParameter("id", id);
        return request.getResultList();
    }
    
}
