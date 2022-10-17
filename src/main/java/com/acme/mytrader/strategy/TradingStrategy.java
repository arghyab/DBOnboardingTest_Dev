package com.acme.mytrader.strategy;
import java.util.HashMap;
import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

   private Integer lot;
   private ExecutionService myBroker;
   private HashMap<String,Double> buyThresholds;
   private HashMap<String,Double> sellThresholds;
   private static Integer defaultLot = 1000;

   public TradingStrategy(ExecutionService broker, Integer lot) 
   {
       this.myBroker = broker;
       this.lot = lot;
       this.buyThresholds = new HashMap<String,Double>();
       this.sellThresholds = new HashMap<String,Double>();
   }
  
   public TradingStrategy(ExecutionService broker) 
   {
       this(broker,defaultLot);
   }

   public void priceUpdate(String security, double price)
   {
       meanReverseionStrategy(security, price);
   }


  private void meanReverseionStrategy(String security, double price)
  {
  Double buyThreshold = buyThresholds.get(security);
  Double sellThreshold = sellThresholds.get(security);
  if(buyThreshold != null && price < buyThreshold){
      myBroker.buy(security, price, getLot());
  }
  if(sellThreshold != null && price> sellThreshold){
      myBroker.sell(security, price, getLot());
  }

}

  public void setSellThreshold(String security, Double sellThreshold){
    sellThresholds.put(security, sellThreshold);

 }
  
  public void setBuyThreshold(String security, Double sellThreshold){
    buyThresholds.put(security, sellThreshold);

 }

  public void setLot(Integer lot){
   this.lot = lot;
  }


  public Integer getLot(){

    return lot;
  }
 

}
