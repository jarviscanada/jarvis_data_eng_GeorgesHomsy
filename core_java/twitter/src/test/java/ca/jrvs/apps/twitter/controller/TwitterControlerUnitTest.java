package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.controller.TwitterControler;
import java.util.ArrayList;
import java.util.List;
import ca.jrvs.apps.twitter.modal.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ca.jrvs.apps.twitter.service.TwitterService;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControlerUnitTest {

  @Mock
  TwitterService ts;

  @InjectMocks
  TwitterControler tc;

  @Test
  public void postTweet() {

    Tweet t = new Tweet();
    t.setText("hellolololololo");
    when(ts.postTweet(any())).thenReturn(t);

    Tweet expectedTweet = tc.postTweet(new String[]{"post", t.getText(), "49:73"});

    assertNotNull(expectedTweet);
    assertEquals("hellolololololo",expectedTweet.getText());

  }

  @Test
  public void showTweet() {
    Tweet t = new Tweet();
    t.setText("hellolololololo");
    when(ts.showTweet(any(),any())).thenReturn(t);

    Tweet expectedTweet = tc.showTweet(new String[]{"show", "123456789"});

    assertNotNull(expectedTweet);
    assertEquals("hellolololololo",expectedTweet.getText());
  }

  @Test
  public void deleteTweet() {
    Tweet t = new Tweet();
    t.setText("hellolololololo");

    List<Tweet> tweetList = new ArrayList<Tweet>();
    tweetList.add(t);

    when(ts.deleteTweets(any())).thenReturn(tweetList);

    List<Tweet> expectedTweetList = tc.deleteTweet(new String[]{"delete", "123456789"});

    assertNotNull(expectedTweetList);
    assertEquals(expectedTweetList.get(0).getText(),tweetList.get(0).getText());

  }
}