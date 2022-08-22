package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.controller.TwitterControler;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.helper.TwitterHttpHelper;
import java.util.List;
import ca.jrvs.apps.twitter.modal.Coordinates;
import ca.jrvs.apps.twitter.modal.Tweet;
import org.junit.Before;
import org.junit.Test;
import ca.jrvs.apps.twitter.service.TwitterService;

public class TwitterControlerIntTest {

  private TwitterDAO dao;
  private Tweet t;
  private TwitterHttpHelper th;

  private TwitterService ts;

  private TwitterControler tc;
  @Before
  public void setUp() throws Exception{
    String consumerKey = System.getenv("CONSUMER_KEY");
    String consumerSecret = System.getenv("CONSUMER_SECRET");
    String access_token = System.getenv("ACCESS_TOKEN");
    String token_secret = System.getenv("TOKEN_SECRET");

    System.out.println(consumerKey);
    System.out.println(consumerSecret);
    System.out.println(access_token);
    System.out.println(token_secret);

    th =new TwitterHttpHelper(consumerKey, consumerSecret, access_token, token_secret);

    dao =new TwitterDAO(th);

    ts =new TwitterService(dao);

    tc = new TwitterControler(ts);

    t =new Tweet();

    String mTwitter = "Hellootest" + System.currentTimeMillis();
//    String mTwitter = "HelloHelloHelloHelloHelloHellHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHellHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHellHelloHelloHello";

    double lat = 40.05701649;
    double lon = -75.14310264;

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon, lat});
    t.setText(mTwitter);
    t.setCoordinates(coordinates);
  }

  @Test
  public void postTweet() {

    String[] args = {"post","Hellllooo"+System.currentTimeMillis(),"43:79"};

    Tweet expectedTweet = tc.postTweet(args);
    assertTrue(expectedTweet.getText().contains("Hellllooo"));
  }

  @Test
  public void showTweet() {
    String[] args = {"show","1561778336359129089"};

    Tweet expectedTweet = tc.showTweet(args);
    assertEquals(args[1],expectedTweet.getId().toString());
  }

  @Test
  public void deleteTweet() {
    String[] args = {"delete","1561778336359129089"};

    List<Tweet> expectedTweetList = tc.deleteTweet(args);
    assertTrue(expectedTweetList.size()==1);
  }
}