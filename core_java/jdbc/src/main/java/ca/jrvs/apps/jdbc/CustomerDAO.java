package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.utils.DataAccessObject;
import ca.jrvs.apps.jdbc.utils.DataTransferObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {

  private static final String INSERT = "INSERT INTO customer (first_name, last_name," +
      "email, phone, address, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String GET_ONE = "SELECT customer_id, first_name, last_name, " +
      "email, phone, address, city, state, zipcode FROM customer WHERE customer_id=?";

  private static final String UPDATE = "UPDATE customer SET first_name = ?, last_name=?, " +
      "email = ?, phone = ?, address = ?, city = ?, state = ?, zipcode = ? WHERE customer_id = ?";

  private static final String DELETE = "DELETE FROM customer WHERE customer_id = ?";

  public CustomerDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Customer findById(long id) {
    Customer c = new Customer();
    try{
      PreparedStatement ps = connection.prepareStatement(GET_ONE);
      ps.setLong(1,id);
      ResultSet rs = ps.executeQuery();

      while(rs.next()) {
        c.setId(rs.getLong("customer_id"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setEmail(rs.getString("email"));
        c.setPhone(rs.getString("phone"));
        c.setAddress(rs.getString("address"));
        c.setCity(rs.getString("city"));
        c.setState(rs.getString("state"));
        c.setZipCode(rs.getString("zipcode"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return c;
  }

  @Override
  public List<Customer> findAll() {
    return null;
  }

  @Override
  public Customer update(Customer dto) {
    Customer c = null;
    try{
      PreparedStatement ps = connection.prepareStatement(UPDATE);
      ps.setString(1,dto.getFirstName());
      ps.setString(2, dto.getLastName());
      ps.setString(3, dto.getEmail());
      ps.setString(4, dto.getPhone());
      ps.setString(5, dto.getAddress());
      ps.setString(6, dto.getCity());
      ps.setString(7, dto.getState());
      ps.setString(8, dto.getZipCode());
      ps.setLong(9, dto.getId());

      ps.execute();

      c = findById(dto.getId());

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return c;
  }

  @Override
  public Customer create(Customer dto) {
    try{
      PreparedStatement ps = connection.prepareStatement(INSERT);
      ps.setString(1, dto.getFirstName());
      ps.setString(2, dto.getLastName());
      ps.setString(3, dto.getEmail());
      ps.setString(4, dto.getPhone());
      ps.setString(5, dto.getAddress());
      ps.setString(6, dto.getCity());
      ps.setString(7, dto.getState());
      ps.setString(8, dto.getZipCode());

      ps.execute();

      return null;

    }catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(long id) {
    try {
      PreparedStatement ps = connection.prepareStatement(DELETE);
      ps.setLong(1,id);
      ps.execute();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
