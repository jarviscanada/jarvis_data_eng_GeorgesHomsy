package ca.jrvs.apps.twitter.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class TwitterHttpHelper implements HttpHelper{

  private OAuthConsumer consumer;
  private HttpClient httpClient;

  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
    consumer.setTokenWithSecret(accessToken,tokenSecret);

    httpClient = HttpClientBuilder.create().build();
  }

  public TwitterHttpHelper() {
    String consumerKey = System.getenv("CONSUMER_KEY");
    String consumerSecret = System.getenv("CONSUMER_SECRET");
    String access_token = System.getenv("ACCESS_TOKEN");
    String token_secret = System.getenv("TOKEN_SECRET");

    consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
    consumer.setTokenWithSecret(access_token,token_secret);

    httpClient = HttpClientBuilder.create().build();
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    try {
      return ExecuteRequest(HttpMethod.POST,uri);
    } catch (OAuthMessageSignerException | OAuthCommunicationException | IOException |
             OAuthExpectationFailedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    try {
      return ExecuteRequest(HttpMethod.GET,uri);
    } catch (OAuthMessageSignerException | OAuthCommunicationException | IOException |
             OAuthExpectationFailedException e) {
      throw new RuntimeException(e);
    }
  }

  public HttpResponse ExecuteRequest(HttpMethod method, URI uri)
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {

    if (method == HttpMethod.GET) {
      HttpGet request = new HttpGet(uri);
      consumer.sign(request);
      return httpClient.execute(request);
    } else if (method == HttpMethod.POST) {
      HttpPost request = new HttpPost(uri);
      consumer.sign(request);
      return httpClient.execute(request);
    } else {
      throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
    }
  }
}
