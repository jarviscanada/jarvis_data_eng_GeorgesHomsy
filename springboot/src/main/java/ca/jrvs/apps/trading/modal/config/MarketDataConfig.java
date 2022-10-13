package ca.jrvs.apps.trading.modal.config;

public class MarketDataConfig {
    private String host;
    private String token;

    public MarketDataConfig(String host, String token) {
        this.host = host;
        this.token = token;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}