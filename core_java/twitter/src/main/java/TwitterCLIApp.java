import ca.jrvs.apps.twitter.example.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import controller.Controller;
import controller.TwitterControler;
import dao.TwitterDAO;
import helper.TwitterHttpHelper;
import modal.Tweet;
import service.Service;
import service.TwitterService;

public class TwitterCLIApp {

  public static final String USAGE = "USAGE: TwitterApp post|show|delete [other parameters]";
  private Controller controller;

  public TwitterCLIApp(Controller controller) {
    this.controller = controller;
  }

  public static void main(String[] args) {

    args = new String[]{"delete", "1560360575355453447"};

    String consumerKey = System.getenv("CONSUMER_KEY");
    String consumerSecret = System.getenv("CONSUMER_SECRET");
    String access_token = System.getenv("ACCESS_TOKEN");
    String token_secret = System.getenv("TOKEN_SECRET");

    System.out.println(consumerKey);
    System.out.println(consumerSecret);
    System.out.println(access_token);
    System.out.println(token_secret);

    TwitterHttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, access_token, token_secret);

    TwitterDAO dao = new TwitterDAO(httpHelper);
    Service service = new TwitterService(dao);
    Controller controller = new TwitterControler(service);
    TwitterCLIApp app = new TwitterCLIApp(controller);

    app.run(args);
  }

  public void run(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException(USAGE);
    }
    switch (args[0].toLowerCase()) {
      case "post":
        printTweet(controller.postTweet(args));
        break;
      case "show":
        printTweet(controller.showTweet(args));
        break;
      case "delete":
        controller.deleteTweet(args).forEach(this::printTweet);
        break;
      default:
        throw new IllegalArgumentException(USAGE);
    }
  }

  public void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonParser.toJson(tweet, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Not able to convert object to Json string", e);
    }
  }

}
