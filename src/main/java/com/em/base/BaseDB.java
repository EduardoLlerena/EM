package com.em.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BaseDB{
    //private String dbUrl = "jdbc:sqlserver://174.138.179.209:1433/EM_Pruebas";

    private static String serverName = "174.138.179.209";
    private static String dbPort = "1433";
    private static String dbName = "EM_Pruebas";
    private static String dbUsername = "ellerena";
    private static String dbPassword = "Ellerena$24";
    private static String dbUrl = "jdbc:sqlserver://"+ serverName + ":"+ dbPort + ";databaseName=" + dbName + ";user=" + dbUsername + ";password=" + dbPassword + ";encrypt=true;trustServerCertificate=true;";

    private static Logger log = LogManager.getLogger(BaseDB.class);


    private static List<Object[]> executeQuery(String query){
        List<Object[]> resultList = new ArrayList<>();

        try {
            log.info("Connecting to DB");
            Connection connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();

            log.info("Starting to execute query: " + query);

            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int column = resultSet.getMetaData().getColumnCount();
                Object[] rowData = new Object[column];
                for (int i =1;i<column;i++){
                    rowData[i-1] = resultSet.getObject(i);
                }
                resultList.add(rowData);
            }

            log.info("Finished executing query: "+ query);
        } catch (SQLException e) {
            log.info("Exception while executing query: "+ e.getStackTrace());
            throw new RuntimeException(e);
        }


        return resultList;
    }

    @DataProvider(name="usersDataProvider")
    public static Iterator<Object[]> getUsersTable(){
        Iterator<Object[]> iteratorOjbect =executeQuery("SELECT * FROM sysRegisteredUsers").iterator();
        return iteratorOjbect;
    }
}
