package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.utils.DataAccessObject;
import java.awt.geom.RectangularShape;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {

  private static final String GET_BY_ID = "SELECT c.first_name as cfn, c.last_name as cln, c.email as ce, o.order_id as ooi, o.creation_date as ocd, " +
      "o.total_due as otd, o.status as os, s.first_name as sfn , s.last_name as sln, s.email as se, ol.quantity as olq, p.code as pc, p.name as pn, p.size as ps, " +
      "p.variety as pv, p.price as pp FROM orders o JOIN customer c on o.customer_id = c.customer_id JOIN salesperson s " +
      "on o.salesperson_id = s.salesperson_id JOIN order_item ol on ol.order_id = o.order_id JOIN product p " +
      "on ol.product_id = p.product_id where o.order_id = ?";

  public OrderDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Order findById(long id) {
    Order o = new Order();
    OrderItem oi = new OrderItem();
    try {
      PreparedStatement ps = connection.prepareStatement(GET_BY_ID);
      ps.setLong(1,id);

      ResultSet rs = ps.executeQuery();
      List<OrderItem> loi = new ArrayList<>();

      while (rs.next()){
        o.setCustomerFirstName(rs.getString("cfn"));
        o.setCustomerLastLane(rs.getString("cln"));
        o.setCustomerEmail(rs.getString("ce"));
        o.setCreationDate(rs.getDate("ocd"));
        o.setId(rs.getLong("ooi"));
        o.setTotalDue(rs.getBigDecimal("otd"));
        o.setStatus(rs.getString("os"));
        o.setSalespersonEmail(rs.getString("se"));
        o.setSalespersonFirstName(rs.getString("sfn"));
        o.setSalespersonLastName(rs.getString("sln"));

        oi.setProductName(rs.getString("pn"));
        oi.setProductPrice(rs.getBigDecimal("pp"));
        oi.setProductCode(rs.getString("pc"));
        oi.setProductSize(rs.getInt("ps"));
        oi.setQuantity(rs.getInt("olq"));
        oi.setProductVariety(rs.getString("pv"));


        loi.add(oi);
      }
      o.setOrderLines(loi);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return o;
  }

  @Override
  public List<Order> findAll() {
    return null;
  }

  @Override
  public Order update(Order dto) {
    return null;
  }

  @Override
  public Order create(Order dto) {
    return null;
  }

  @Override
  public void delete(long id) {

  }
}
