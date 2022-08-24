package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import java.util.ArrayList;
import java.util.List;
import ca.jrvs.apps.twitter.modal.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private CrdDao dao;

  private static final int TWEET_LENGTH = 140;
  private static final double LONG_MIN = -90.0;
  private static final double LONG_MAX= 90.0;
  private static final double LAT_MIN = -180.0;
  private static final double LAT_MAX = 180.0;
  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    if(validatePostTweet(tweet)) {
      return (Tweet) dao.create(tweet);
    } else {
      throw new IllegalArgumentException("Invalid tweet format!");
    }
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    if(isValidId(id)){
      Tweet result = (Tweet) dao.findById(id);
      for (String field:fields) {
        switch (field) {
          case "id":
            result.getId();
          case "id_str":
            result.getId_str();
          case "text":
            result.getText();
        }
      }
      return result;
    } else {
      throw new IllegalArgumentException("Invalid ID!");
    }
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> lTweet = new ArrayList<Tweet>();
    for(String id:ids) {
      if (isValidId(id)) {
        lTweet.add((Tweet) dao.deleteById(id));
      }
    }
    return lTweet;
  }

  public boolean validatePostTweet(Tweet t) {
//    boolean exprLong = t.getCoordinates().getCoordinates()[0]  >= LONG_MIN && t.getCoordinates().getCoordinates()[0]  <= LONG_MAX;
//    boolean exprLag = t.getCoordinates().getCoordinates()[1]  >= LAT_MIN && t.getCoordinates().getCoordinates()[1]  <= LAT_MAX;

    if (t.getText().length()  < TWEET_LENGTH) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isValidId(String id) {
    if (id.matches("\\d+")) {
      return true;
    } else {
      return false;
    }
  }
}