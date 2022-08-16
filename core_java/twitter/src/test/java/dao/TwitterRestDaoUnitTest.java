package dao;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.example.JsonParser;
import helper.HttpHelper;
import java.io.IOException;
import modal.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterRestDaoUnitTest {
  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDAO dao;


  private String tweetJsonStr = "{\n"
      + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
      + "   \"id\":1097607853932564480,\n"
      + "   \"id_str\":\"1097607853932564480\",\n"
      + "   \"text\":\"test with loc223\",\n"
      + "   \"entities\":{\n"
      + "        \"hashtags\":[],\n"
      + "        \"user_mentions\":[]\n"
      + "   },\n"
      + "   \"coordinates\":null,\n"
      + "   \"retweet_count\":0,\n"
      + "   \"favorite_count\":0,\n"
      + "   \"favorited\":false,\n"
      + "   \"retweeted\":false\n"
      + "}";

  @Test
  public void showTweet() throws IOException {
    String text = "Hi" + System.currentTimeMillis();

    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock test"));
    Tweet t = new Tweet();
    try {
      t.setText(text);
      dao.create(t);
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);

    TwitterDAO spyDAO = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr,Tweet.class);

    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());
    Tweet tweet = spyDAO.create(t);
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void findById() throws IOException {
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock test"));
    try {
      dao.findById("778899");
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    ///////////////////////////////////////////

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDAO spyDAO = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr,Tweet.class);
    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());
    Tweet tweet = spyDAO.findById("7788989");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void deleteById() throws IOException {
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock test"));
    try {
      dao.deleteById("778899");
    } catch (RuntimeException e) {
      assertTrue(true);
    }

       ///////////////////////////////////////////

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDAO spyDAO = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr,Tweet.class);
    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());
    Tweet tweet = spyDAO.deleteById("7788989");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }
}
