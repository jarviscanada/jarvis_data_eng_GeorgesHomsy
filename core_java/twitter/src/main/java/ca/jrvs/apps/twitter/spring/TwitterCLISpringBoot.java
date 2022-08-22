package ca.jrvs.apps.twitter.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter",exclude = {
    DataSourceAutoConfiguration.class, BatchAutoConfiguration.class})
public class TwitterCLISpringBoot implements CommandLineRunner{

  private TwitterCLIApp app;

  @Autowired
  public TwitterCLISpringBoot(TwitterCLIApp app) {
    this.app = app;
  }

  public static void main(String[] args) {
 //  args = new String[]{"show", "1561727969168703488"};
    SpringApplication app = new SpringApplication(TwitterCLISpringBoot.class);
    //Turn off web service
    app.setWebApplicationType(WebApplicationType.NONE);

    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    app.run(args);
  }
}

