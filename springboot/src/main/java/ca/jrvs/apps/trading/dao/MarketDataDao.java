package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.modal.config.MarketDataConfig;
import ca.jrvs.apps.trading.modal.domain.IexQuote;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;


public class MarketDataDao implements CrudRepository<IexQuote,String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;
    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    public MarketDataDao(MarketDataConfig marketDataConfig, HttpClientConnectionManager httpClientConnectionManager) {
        this.IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
        this.httpClientConnectionManager = httpClientConnectionManager;
    }

    /**
     * @param s
     * @param <S>
     * @return
     */
    @Override
    public <S extends IexQuote> S save(S s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param iterable
     * @param <S>
     * @return
     */
    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param s 
     * @return
     */
    @Override
    public Optional<IexQuote> findById(String s) {
        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(s));

        if (quotes.size() == 0) {
            return Optional.empty();
        } else if (quotes.size() == 1) {
            iexQuote = Optional.of(quotes.get(0));
        } else {
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }
        return iexQuote;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @return 
     */
    @Override
    public Iterable<IexQuote> findAll() {
        return null;
    }

    /**
     * @param iterable
     * @return
     */
    @Override
    public List<IexQuote> findAllById(Iterable<String> iterable) {
        ObjectMapper m = new ObjectMapper();
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        List<IexQuote> iexQuote = new ArrayList<IexQuote>();
        String stringTicker = String.join(",",iterable);
        final String uri = String.format(IEX_BATCH_URL,stringTicker);
        try {
            String response = executeHttpGet(uri)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

            JSONObject json = new JSONObject(response);

            for (String key:iterable) {
                if (json.has(key)) {
                    JSONObject jsonObject = json.getJSONObject(key);
                    iexQuote.add(m.readValue(jsonObject.get("quote").toString(), IexQuote.class));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return iexQuote;
    }

    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(httpClientConnectionManager)
                .setConnectionManagerShared(true).build();
    }

    private Optional<String> executeHttpGet (String url) throws IOException {
        Optional<String> responseBody;

        HttpClient httpClient = getHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() == 200) {
            responseBody = Optional.of(EntityUtils.toString(response.getEntity()));
        } else {
            throw new IllegalArgumentException("Ticker Invalid!");
        }

        return responseBody;
    }

    /**
     * @return
     */
    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param s
     */
    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param iexQuote
     */
    @Override
    public void delete(IexQuote iexQuote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @param iterable
     */
    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     */
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
