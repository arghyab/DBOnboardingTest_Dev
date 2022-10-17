package com.acme.mytrader.strategy;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.execution.ExecutionService
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Matchers.*;
import org.mockito.junit.MockitoJunitRunner;

@RunWith(MockitoJunitRunner.class)
public class TradingStrategyTest {
    TradingStrategy tradingStrategy;
   @Mock PriceSource priceSource
   @Mock ExecutionService broker;

   @Before
   public void init() {
	tradingStartegy = new tradingStrategy(broker,1000);
        tradingStartegy.setBuyThreshold("ABC", 550.5);
        tradingStartegy.setSellThreshold("ABC", 1550.5);
  }
    @Test
    public void testBuy() {
    tradingStartegy.priceUpdate("ABC", 560.0);
    Mockito.verify(broker,Mockito.times(0)).buy(anyString(), anyDouble(), anyInt());
    tradingStartegy.priceUpdate("ABC", 540.0);
    Mockito.verify(broker,Mockito.times(1)).buy("ABC", 540.0, 1000);
    }

     @Test
    public void testSell() {
    tradingStartegy.priceUpdate("ABC", 540.0);
    Mockito.verify(broker,Mockito.times(0)).sell(anyString(), anyDouble(), anyInt());
    tradingStartegy.priceUpdate("ABC", 560.0);
    Mockito.verify(broker,Mockito.times(1)).sell("ABC", 560.0, 1000);
    }

}
