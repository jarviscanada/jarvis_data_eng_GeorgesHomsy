package helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

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
  }

  @Test
  public void httpPost() throws Exception {
    HttpResponse httpResponse =  th.httpPost( new URI("https://api.twitter.com/1.1/statuses/update.json?status=HELOO"));

    System.out.println(EntityUtils.toString(httpResponse.getEntity()));
  }

  @Test
  public void httpGet() throws Exception{
    HttpResponse httpResponse =  th.httpGet( new URI("https://api.twitter.com/1.1/statuses/show.json?id=1557798993828143104"));

    System.out.println(EntityUtils.toString(httpResponse.getEntity()));
  }
}