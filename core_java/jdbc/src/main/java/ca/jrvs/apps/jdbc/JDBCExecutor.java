package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

  public static void main(String[] args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
        "hplussport", "postgres", "password");
    try{
      Connection connection = dcm.getConnection();
//      Statement statement = connection.createStatement();
//      ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM CUSTOMER;");
//      while(resultSet.next()){
//        System.out.println(resultSet.getInt(1));
//      }

        CustomerDAO c = new CustomerDAO(connection);
        Customer customer = new Customer();

        OrderDAO o = new OrderDAO(connection);
//
//      customer.setFirstName("George");
//      customer.setLastName("Homsy");
//      customer.setEmail("gh@gh.gh");
//      customer.setPhone("(555) 555-6543");
//      customer.setAddress("1234 Canada ON");
//      customer.setCity("Toronto");
//      customer.setState("ON");
//      customer.setZipCode("11223344");
//
//      c.create(customer);
//      customer = c.findById(10000);
//      customer.setFirstName("Geo");
//      c.update(customer);
//      System.out.println(customer.toString());


      System.out.println(o.findById(1000).toString());
    }catch(SQLException e){
      e.printStackTrace();
    }
  }
}
