package com.lrh.common.spring.util;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/11/10 16:34
 */
public class HttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    //自定义常量
    private static final String UTF_8 = "UTF-8";
    private static final Charset DEFAULT_CHARSET = Charset.forName(UTF_8);
    private static final String DEFAULT_CONTENT_TYPE = "application/json;charset=utf8";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final int SOCKET_TIMEOUT = 30 * 1000;
    private static final int CONNECTION_TIMEOUT = 5 * 1000;
    private static final int DEFAULT_MAX_PER_ROUTE = 50;
    private static final int MAX_CONNECTION = 10 * DEFAULT_MAX_PER_ROUTE;
    //自定义空集合 减少GC
    private static final List<NameValuePair> EMPTY_PARAM = new ArrayList<>();
    private static final Header[] EMPTY_HEADER = new Header[0];
    private static final Map<String, String> DEFAULT_POST_HEADER_MAP = new HashMap<>();
    private static RequestConfig requestConfig;
    private static PoolingHttpClientConnectionManager connectionManager;
    private static HttpClientBuilder httpBuilder;

    static {
        //设置请求头
        DEFAULT_POST_HEADER_MAP.put(CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        //设置http的状态参数
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
                .build();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", trustAllHttpsCertificates()).build();
        connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(MAX_CONNECTION);//客户端总并行链接最大数
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);//每个主机的最大并行链接数
        httpBuilder = HttpClients.custom();
        httpBuilder.setConnectionManager(connectionManager);
        httpBuilder.setDefaultRequestConfig(requestConfig);//默认配置
        httpBuilder.setRetryHandler(new MyHttpRequestRetryHandler());//重试机制


    }


    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doGet(String url, Map<String, String> paramsMap) {
        return doGet(url, paramsMap, null);
    }

    public static String doGet(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        CloseableHttpClient httpclient = httpBuilder.build();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet();
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setCharset(DEFAULT_CHARSET);
            uriBuilder.setParameters(setHttpFormParams(paramsMap));//设置请求参数
            httpGet.setHeaders(setHttpHeaders(headerMap));//设置请求头
            httpGet.setURI(uriBuilder.build());
            System.out.println(httpGet.getURI().toString());
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, UTF_8);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            // 关闭连接,释放资源
            close(httpclient, response);
        }
        return null;
    }

    public static String doPostForm(String url) {
        return doPostForm(url, null);
    }

    public static String doPostForm(String url, Map<String, String> paramsMap) {
        return doPostForm(url, paramsMap, null);
    }

    public static String doPostForm(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        CloseableHttpClient httpclient = httpBuilder.build();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost();
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setCharset(DEFAULT_CHARSET);
            uriBuilder.setParameters(setHttpFormParams(paramsMap));//设置请求参数
            httpPost.setHeaders(setHttpHeaders(headerMap));//设置请求头
            httpPost.setURI(uriBuilder.build());
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, UTF_8);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            // 关闭连接,释放资源
            close(httpclient, response);
        }
        return null;
    }

    public static String doPostBody(String url, String body) {
        return doPostBody(url, body, DEFAULT_POST_HEADER_MAP);
    }

    public static String doPostBody(String url, String body, Map<String, String> headerMap) {
        CloseableHttpClient httpclient = httpBuilder.build();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeaders(setHttpHeaders(headerMap));//设置请求头
            httpPost.setEntity(new StringEntity(body, UTF_8));
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, UTF_8);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            // 关闭连接,释放资源
            close(httpclient, response);
        }
        return null;
    }

    /**
     * 设置请求参数
     *
     * @param
     * @return
     */
    private static List<NameValuePair> setHttpFormParams(Map<String, String> paramMap) {
        if (paramMap == null || paramMap.isEmpty()) {
            return EMPTY_PARAM;
        }
        List<NameValuePair> formparams = new ArrayList<>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formparams;
    }

    /**
     * 设置请求头
     *
     * @param
     * @return
     */
    private static Header[] setHttpHeaders(Map<String, String> headerMap) {
        if (headerMap == null || headerMap.isEmpty()) {
            return EMPTY_HEADER;
        }
        Header[] headers = new Header[headerMap.size()];
        Set<Map.Entry<String, String>> set = headerMap.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : set) {
            Header header = new BasicHeader(entry.getKey(), entry.getValue());
            headers[i++] = header;
        }
        return headers;
    }

    /**
     * 关闭流
     *
     * @param closeables
     */
    private static void close(Closeable... closeables) {
        if (closeables != null) {
            for (int i = 0; i < closeables.length; ++i) {
                Closeable closeable = closeables[i];
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class MyHttpRequestRetryHandler implements HttpRequestRetryHandler {
        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
            if (executionCount >= 3) {// 如果已经重试了3次，就放弃
                return false;
            }
            if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof SSLException) {// SSL握手异常
                return false;
            }

            if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof ConnectTimeoutException) {// 连接超时
                return true;
            }
            if (exception instanceof InterruptedIOException) {// 超时
                return true;
            }
            if (exception instanceof UnknownHostException) {// 目标服务器不可达
                return true;
            }

            HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        }
    }

    private static SSLConnectionSocketFactory trustAllHttpsCertificates() {
        SSLConnectionSocketFactory socketFactory = null;
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");//sc = SSLContext.getInstance("TLS")
            sc.init(null, trustAllCerts, null);
            socketFactory = new SSLConnectionSocketFactory(sc, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            logger.error("", e);
        }
        return socketFactory;
    }

    /**
     * HTTPS跳过SSL证书验证
     */
    static class miTM implements TrustManager, X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) {
            //don't check
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) {
            //don't check
        }
    }

    public static void main(String[] args) {
        String url = "https://blog.csdn.net/u010800970/article/details/79996698";

        String body = doGet(url);
        System.out.println(body);
    }
}
