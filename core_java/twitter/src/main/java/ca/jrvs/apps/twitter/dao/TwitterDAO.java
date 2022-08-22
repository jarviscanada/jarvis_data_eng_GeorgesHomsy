package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.helper.HttpHelper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import ca.jrvs.apps.twitter.modal.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDAO implements CrdDao<Tweet,String> {

  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";

  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  private static final int HTTP_OK = 200;

  private HttpHelper hh;

  @Autowired
  public TwitterDAO(HttpHelper hh) {
    this.hh = hh;
  }

  @Override
  public Tweet create(Tweet entity) {
    URI uri;
    HttpResponse httpResponse;
    try {
      uri = new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + entity.getText());
      httpResponse = hh.httpPost(uri);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    return parseResponseBody(httpResponse,HTTP_OK);
  }

  @Override
  public Tweet findById(String s) {
    URI uri;
    HttpResponse httpResponse;

    try {
      uri = new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s);
      httpResponse = hh.httpGet(uri);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    return parseResponseBody(httpResponse,HTTP_OK);
  }

  @Override
  public Tweet deleteById(String s) {
    URI uri;
    HttpResponse httpResponse;
    try {
      uri = new URI(API_BASE_URI + DELETE_PATH + s + ".json");
      httpResponse = hh.httpPost(uri);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    return parseResponseBody(httpResponse,HTTP_OK);
  }

  public Tweet parseResponseBody(HttpResponse httpResponse, Integer expectedStatus) {
    Tweet tweet = null;
    try {
      String jsonText;
      int status = httpResponse.getStatusLine().getStatusCode();

      if (status == expectedStatus) {
        jsonText = EntityUtils.toString(httpResponse.getEntity());
        tweet = JsonParser.toObjectFromJson(jsonText,Tweet.class);
      } else {
        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return tweet;
  }
}
