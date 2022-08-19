package ca.jrvs.apps.twitter.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpTest {

  public static void main(String[] args) {
    String consumerKey = System.getenv("CONSUMER_KEY");
    String consumerSecret = System.getenv("CONSUMER_SECRET");
    String access_token = System.getenv("ACCESS_TOKEN");
    String token_secret = System.getenv("TOKEN_SECRET");

    System.out.println(consumerKey);
    System.out.println(consumerSecret);
    System.out.println(access_token);
    System.out.println(token_secret);

    try {
      TwitterHttpHelper th = new TwitterHttpHelper(consumerKey,consumerSecret,access_token,token_secret);

      HttpResponse httpResponse =  th.httpPost( new URI("https://api.twitter.com/1.1/statuses/update.json?status=HELOO"));

      System.out.println(EntityUtils.toString(httpResponse.getEntity()));
    } catch (Exception e) {
      System.out.println(e.fillInStackTrace());
    }
  }
}
