package com.mustafaderyol.inventory.resources;

import com.mustafaderyol.inventory.entity.MovementHistory;
import com.mustafaderyol.inventory.idao.IMovementHistoryDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author MstfDryl
 */
@Controller
@RequestMapping("/movementhistory")
public class MovementHistoryResource {
    
    @Autowired
    private IMovementHistoryDao iMovementHistoryDao;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<MovementHistory> getMovementHistory() {
        final List<MovementHistory> movementHistories = iMovementHistoryDao.allFunc();
        return movementHistories;

    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MovementHistory getFindById(@PathVariable Long id)
    {
        MovementHistory movementHistory = iMovementHistoryDao.findByIdFunc(id);
        return movementHistory;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Boolean deleteMovementHistory(@PathVariable Long id)
    {
        iMovementHistoryDao.deleteFunc(id);
        return true;
    }

    
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MovementHistory createMovementHistory(@RequestBody MovementHistory movementHistory)
    {
        movementHistory = iMovementHistoryDao.saveFunc(movementHistory);
        return movementHistory;
    }

    
    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MovementHistory updateMovementHistory(@RequestBody MovementHistory movementHistory)
    {
        iMovementHistoryDao.updateFunc(movementHistory);
        return movementHistory;
    }

}
