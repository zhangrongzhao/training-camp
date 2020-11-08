package org.geekbang.training.arch;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class HttpTask implements Callable<Long> {
    private HttpClient httpClient;
    private HttpRequest request;
    private int index;
    public HttpTask(HttpClient client,HttpRequest request,int index){
          this.httpClient=client;
          this.request=request;
          this.index=index;
    }

    @Override
    public Long call() throws Exception {
        try {
            long start = System.nanoTime();
            //System.out.println(index+"请求开始时间：" + start);
            CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply(HttpResponse::body).get();
            long end = System.nanoTime();
            //System.out.println(index+"请求结束时间：" + end);
            System.out.println("Thread"+Thread.currentThread().getId()+"-第"+index + "个请求响应时间(毫秒)：" + TimeUnit.NANOSECONDS.toMillis(end-start));
            return end-start;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
