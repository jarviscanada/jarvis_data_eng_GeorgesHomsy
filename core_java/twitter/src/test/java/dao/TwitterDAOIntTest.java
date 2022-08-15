package dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.example.JsonParser;
import helper.TwitterHttpHelper;
import modal.Coordinates;
import modal.Tweet;
import org.junit.Before;
import org.junit.Test;

public class TwitterDAOIntTest {
  private TwitterDAO dao;
  private Tweet t;
  private TwitterHttpHelper th;

  @Before
  public void setup() {
    String consumerKey = System.getenv("CONSUMER_KEY");
    String consumerSecret = System.getenv("CONSUMER_SECRET");
    String access_token = System.getenv("ACCESS_TOKEN");
    String token_secret = System.getenv("TOKEN_SECRET");

    System.out.println(consumerKey);
    System.out.println(consumerSecret);
    System.out.println(access_token);
    System.out.println(token_secret);

    th = new TwitterHttpHelper(consumerKey,consumerSecret,access_token,token_secret);
    dao = new TwitterDAO(th);
    t = new Tweet();

    String mTwitter = "Hellootest";
    double lat = 40.05701649;
    double lon = -75.14310264;

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon,lat});

    t.setText(mTwitter);
    t.setCoordinates(coordinates);

  }

  @Test
  public void create() {

    Tweet nTweet = dao.create(t);

    System.out.println(nTweet.getText());
    assertEquals(nTweet.getText(),t.getText());
//    assertEquals(2, nTweet.getCoordinates().getCoordinates().length);
//    assertEquals(nTweet.getCoordinates().getCoordinates()[0],t.getCoordinates().getCoordinates()[0],0.01);
//    assertEquals(nTweet.getCoordinates().getCoordinates()[1],t.getCoordinates().getCoordinates()[1],0.01);
    assertTrue(nTweet.getText().contains("test"));

  }

  @Test
  public void findById() {
    Tweet result = dao.findById("1559293366415904772");
    assertEquals(result.getText(), t.getText());
  }

  @Test
  public void deleteById() {
    Tweet result = dao.deleteById("1559239963450941440");
    assertNotNull(result.getText());
  }
}