package modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hashtags",
    "user_mentions"
})

public class Entities {
  @JsonProperty("hashtags")
  private Hashtags[] hashtags;
  @JsonProperty("userMention")
  private UserMention[] userMention;

  public Hashtags[] getHashtags() {
    return hashtags;
  }

  public void setHashtags(Hashtags[] hashtags) {
    this.hashtags = hashtags;
  }

  public UserMention[] getUserMention() {
    return userMention;
  }

  public void setUserMention(UserMention[] userMention) {
    this.userMention = userMention;
  }
}
