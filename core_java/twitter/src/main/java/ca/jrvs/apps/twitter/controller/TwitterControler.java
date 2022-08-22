package ca.jrvs.apps.twitter.controller;

import java.util.Arrays;
import java.util.List;
import ca.jrvs.apps.twitter.modal.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterControler implements Controller{

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  @Autowired
  public TwitterControler(Service service) {
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {

    if(args.length != 3) {
      throw new IllegalArgumentException("3 arguments: post -> Tweet text -> latitude:longitude");
    }

    String tweet_txt = args[1];
    String coord = args[2];
    String[] coordArray = coord.split(COORD_SEP);

    if (tweet_txt == "" || coordArray.length != 2) {
      throw new IllegalArgumentException("Invalid arguments!");
    }

    Tweet postTweet = new Tweet();
    postTweet.setText(tweet_txt);


    return service.postTweet(postTweet);
  }

  @Override
  public Tweet showTweet(String[] args) {

    if(args.length != 2) {
      throw new IllegalArgumentException("2 arguments: show -> id");
    }

    String id= args[1];
    String[] zones = Arrays.copyOfRange(args,2,args.length);
    Tweet showTweet = service.showTweet(id,zones);

    return showTweet;
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if(args.length != 2) {
      throw new IllegalArgumentException("2 arguments: delete -> id");
    }

    String[] ids = Arrays.copyOfRange(args,1,args.length);
    return service.deleteTweets(ids);
  }
}
