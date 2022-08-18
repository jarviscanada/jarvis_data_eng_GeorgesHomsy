package service;

import static org.junit.Assert.*;

import dao.TwitterDAO;
import helper.TwitterHttpHelper;
import java.util.ArrayList;
import java.util.List;
import modal.Coordinates;
import modal.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.stylesheets.LinkStyle;

public class TwitterServiceIntTest {

  private TwitterDAO dao;
  private Tweet t;
  private TwitterHttpHelper th;

  private TwitterService ts;

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
    ts = new TwitterService(dao);
    t = new Tweet();

    String mTwitter = "Hellootest" + System.currentTimeMillis();
//    String mTwitter = "HelloHelloHelloHelloHelloHellHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHellHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHellHelloHelloHello";

    double lat = 40.05701649;
    double lon = -75.14310264;

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon,lat});

    t.setText(mTwitter);
    t.setCoordinates(coordinates);
  }

  @Test
  public void postTweet() {
    Tweet res = ts.postTweet(t);
    assertTrue(t.getText().contains("Hellootest"));
  }

  @Test
  public void showTweet() {
    String id = "1559997512374800385";
    String [] var = {"text"};
    assertEquals(ts.showTweet(id,var).getText(),"Hellootest1660767363051");
  }

  @Test
  public void deleteTweets() {
    List<Tweet> tt = new ArrayList<Tweet>();
    String[] dId = {"1559976227334676480"};
    tt = (ts.deleteTweets(dId));
    assertEquals(tt.get(0).getId_str(),dId[0]);
  }
}