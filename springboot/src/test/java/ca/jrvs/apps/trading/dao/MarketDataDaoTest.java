package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.modal.config.MarketDataConfig;
import ca.jrvs.apps.trading.modal.domain.IexQuote;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class MarketDataDaoTest {

    private MarketDataDao dao;

    @Before
    public void setUp() throws Exception {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(50);
        httpClientConnectionManager.setDefaultMaxPerRoute(50);

        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1");
        marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
        dao = new MarketDataDao(marketDataConfig,httpClientConnectionManager);
    }

    @Test
    public void findIexQuoteByTickers() throws Exception {
        List<IexQuote> quoteList = dao.findAllById(Arrays.asList("AAPL","FB"));
        assertEquals(2,quoteList.size());
        assertEquals("AAPL",quoteList.get(0).getSymbol());

//        try {
//            dao.findAllById(Arrays.asList("AAPL","FB2"));
//            fail();
//        } catch (IllegalArgumentException e) {
//            assertTrue(true);
//        } catch (Exception e) {
//            fail();
//        }
    }

    @Test
    public void findIexQuoteForOneTicker() {
        Optional<IexQuote> iexQuote = dao.findById("AAPL");
        assertEquals(Optional.of("AAPL"),Optional.of(iexQuote.get().getSymbol()));
    }
}