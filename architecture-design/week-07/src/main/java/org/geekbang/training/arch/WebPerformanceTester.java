package org.geekbang.training.arch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.concurrent.*;

/**
 * Web性能测试
 * */
public class WebPerformanceTester implements Tester {
    private  ExecutorService executors = null;
    private  HttpRequest[] requests = null;
    private  Future<Long>[] results = null;
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public WebPerformanceTester(String url, int requestCount, int concurrencyCount){
        executors = Executors.newFixedThreadPool(concurrencyCount);
        requests = new HttpRequest[requestCount];
        results = new Future[requestCount];
        for(int i=0;i<requestCount;i++){
            requests[i] = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                    .build();
        }
    }

    public void test() {
        /*启动多线程，并行发出请求**/
        for(int i=0;i<requests.length;i++){
              Callable<Long> task = new HttpTask(httpClient,requests[i],i);
              results[i] = executors.submit(task);
        }
        executors.shutdown();
        System.out.println("平均请求时间(毫秒)；"+TimeUnit.NANOSECONDS.toMillis(CalculateAvgResponseTime()));
        System.out.println("95%平均请求时间(毫秒)；"+TimeUnit.NANOSECONDS.toMillis(CalculateAvgResponseTimeByPercent(95)));
    }
    private Long CalculateAvgResponseTime(){
        Long sum=0L;
        for(Future<Long> result:results){
            try {
                sum+=result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum/(results.length);
    }

    private Long CalculateAvgResponseTimeByPercent(int number){
        Long[] resultArray = new Long[results.length];
        for(int i=0;i<resultArray.length;i++){
            try {
                resultArray[i] = results[i].get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        /***排序*/
        for(int i=0;i<resultArray.length;i++){
            for(int j=0;j<resultArray.length-i-1;j++){
                if(resultArray[j]>resultArray[j+1]){
                    Long temp=resultArray[j];
                    resultArray[j]=resultArray[j+1];
                    resultArray[j+1]=temp;
                }
            }
        }
        Long sum=0L;
        double ceilByPercent = Math.ceil(resultArray.length*(number/100.0));
        for(int i=0;i<ceilByPercent;i++){
            sum+=resultArray[i];
        }
        return (long)(sum/ceilByPercent);
    }
}
