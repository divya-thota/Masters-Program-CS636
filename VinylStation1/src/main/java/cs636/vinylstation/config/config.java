package cs636.vinylstation.config;

import cs636.vinylstation.dao.loginDAO;
import cs636.vinylstation.dao.customerDAO;
import cs636.vinylstation.dao.employeeDAO;
import cs636.vinylstation.dao.bandDAO;
import cs636.vinylstation.dao.invoiceDAO;
import cs636.vinylstation.dao.recordDAO;
import cs636.vinylstation.dao.recordtrackDAO;
import cs636.vinylstation.dao.trackDAO;
import cs636.vinylstation.service.loginService;
import cs636.vinylstation.service.customerService;
import cs636.vinylstation.service.employeeService;
import cs636.vinylstation.service.bandService;

import java.sql.Connection;

public class config
{
    private static loginService loginService;
    private static customerService customerService;
    private static employeeService employeeService;
    private static bandService bandService;
    private static loginDAO loginDAO;
    private static customerDAO customerDAO;
    private static employeeDAO employeeDAO;
    private static bandDAO bandDAO;
    private static invoiceDAO invoiceDAO;
    private static recordDAO recordDAO;
    private static recordtrackDAO recordtrackDAO;
    private static trackDAO trackDAO;
    private static dbConfig db;
    
    public static loginService getloginService() {
        return config.loginService;
    }
    public static customerService getcustomerService() {
        return config.customerService;
    }
    public static employeeService getemployeeService() {
        return config.employeeService;
    }
    public static bandService getbandService() {
        return config.bandService;
    }
    
    public static void configureServices() throws Exception {
        try {
            (config.db = new dbConfig()).init();
            Connection dbconnection = config.db.getConnection();
            config.loginDAO = new loginDAO(dbconnection);
            config.customerDAO = new customerDAO(dbconnection);
            config.employeeDAO = new employeeDAO(dbconnection);
            config.invoiceDAO = new invoiceDAO(dbconnection);
            config.recordDAO = new recordDAO(dbconnection);
            config.recordtrackDAO = new recordtrackDAO(dbconnection);
            config.trackDAO = new trackDAO(dbconnection);
            config.bandDAO = new bandDAO(dbconnection);
       
            config.loginService = new loginService(config.loginDAO);
            config.customerService = new customerService(config.customerDAO, config.trackDAO, config.invoiceDAO, config.recordDAO, config.recordtrackDAO);
            config.employeeService = new employeeService(config.employeeDAO, config.recordtrackDAO, config.recordDAO, config.invoiceDAO);
            config.bandService = new bandService(config.bandDAO, config.trackDAO);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Problem with contacting DB: " + e);
            throw e;
        }
    }
}