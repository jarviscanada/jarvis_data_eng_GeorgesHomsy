package service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import dao.TwitterDAO;
import helper.TwitterHttpHelper;
import modal.Coordinates;
import modal.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  TwitterDAO dao;
  @InjectMocks
  TwitterService ts;


  @Test
  public void postTweet() {
    Tweet t = new Tweet();
    String mTwitter = "Hellootest" + System.currentTimeMillis();
    double lat = 40.05701649;
    double lon = -75.14310264;

    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon,lat});

    t.setText(mTwitter);
    t.setCoordinates(coordinates);

    when(dao.create(any())).thenReturn(t);

    Tweet expected = ts.postTweet(t);

    assertNotNull(expected);
    assertTrue(expected.getText().contains("Hellootest"));
  }

  @Test
  public void showTweet() {
    Tweet testTweet = new Tweet();
    testTweet.setText("TestHello");
    when(dao.findById(any())).thenReturn(testTweet);

    Tweet expected = ts.showTweet("12312312",new String[]{"text"});

    assertNotNull(expected);
    assertEquals("TestHello",expected.getText());

  }

  @Test
  public void deleteTweets() {
    when(dao.deleteById(any())).thenReturn(new Tweet());
  }
}