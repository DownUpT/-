package com.dq.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Bonree
 * http请求公共类
 */
public enum HttpUtils {
    INSTANCE;

    /**
     * 发送HTTP请求超时时间,单位:ms
     */
    private final Integer connTimeOut = 60_000;
    /**
     * 请求超时,单位:ms
     */
    private final Integer reqTimeOut = 60_000;
    /**
     * 响应传输数据超时,单位:ms
     */
    private final Integer resTimeOut = 60_000;

    /**
     * 发送httpPost请求(带参数)
     *
     * @param url    post请求的地址
     * @param header
     * @param body
     * @return 字符串结果
     */
    public String sendPost(String url, Map<String, String> header, Map<String, String> body) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        if (header != null && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey().toLowerCase(), entry.getValue());
            }
        }
        // 设置请求体
        if (body != null && !body.isEmpty()) {
            List<NameValuePair> formParams = new ArrayList<>();
            for (Map.Entry<String, String> entry : body.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, StandardCharsets.UTF_8);
            httpPost.setEntity(entity);
        }
        // 统一使用短连接
        httpPost.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        // 设置超时时间,防止连接卡住导致程序运行异常
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connTimeOut).setConnectionRequestTimeout(reqTimeOut)
                .setSocketTimeout(resTimeOut).build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result;
        try {
            response = httpClient.execute(httpPost);
            if (200 != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException(String.format("code is %s, " +
                                "%s", response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity())));
            }
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnect(response, httpClient);
        }
        return result;
    }

    /**
     * 概述：发送post请求(content)
     *
     * @param request 请求内容
     * @param url     请求地址
     * @return byte[]
     * @throws Exception
     */
    public byte[] sendPostReq(String url, Map<String, String> header, String request) {
        HttpPost httpPost = new HttpPost(url);
        if (header != null && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        if (request != null) {
            StringEntity stringEntity = new StringEntity(request, StandardCharsets.UTF_8);
            httpPost.setEntity(stringEntity);
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connTimeOut).setConnectionRequestTimeout(reqTimeOut)
                .setSocketTimeout(resTimeOut).build();
        httpPost.setConfig(requestConfig);
        return execute(httpPost);
    }

    /**
     * 概述：发送post请求(content)
     *
     * @param url     请求地址
     * @param header  请求头
     * @param request 字节数组body
     * @return byte[]
     */
    public byte[] sendPostByte(String url, Map<String, String> header, byte[] request) {
        HttpPost httpPost = new HttpPost(url);
        if (header != null && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        ByteArrayEntity entity = new ByteArrayEntity(request);
        httpPost.setEntity(entity);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connTimeOut).setConnectionRequestTimeout(reqTimeOut)
                .setSocketTimeout(resTimeOut).build();
        httpPost.setConfig(requestConfig);
        return execute(httpPost);
    }

    /**
     * 概述：发送get请求
     *
     * @param url 请求地址
     * @return byte[]
     * @throws Exception
     */
    public String sendGet(String url, Map<String, String> header) {
        HttpGet httpGet = null;
        try {
            URL url1 = new URL(url);
            URI uri = new URI(url1.getProtocol(),
                    url1.getUserInfo(),
                    url1.getHost(),
                    url1.getPort(),
                    url1.getPath(),
                    url1.getQuery(),
                    null);
            httpGet = new HttpGet(uri);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException("Url format exception", e);
        }

        if (header != null && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connTimeOut).setConnectionRequestTimeout(reqTimeOut)
                .setSocketTimeout(resTimeOut).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpGet);
            if (200 != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException(String.format("code is %s, " +
                                "%s", response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity())));
            }
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnect(response, httpClient);
        }
    }

    public byte[] sendGet(String url) {
        HttpGet httpGet = null;
        try {
            URL url1 = new URL(url);
            URI uri = new URI(url1.getProtocol(),
                    url1.getUserInfo(),
                    url1.getHost(),
                    url1.getPort(),
                    url1.getPath(),
                    url1.getQuery(),
                    null);
            httpGet = new HttpGet(uri);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException("Url format exception", e);
        }

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connTimeOut).setConnectionRequestTimeout(reqTimeOut)
                .setSocketTimeout(resTimeOut).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpGet);
            if (200 != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException(String.format("code is %s, " +
                                "%s", response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity())));
            }
            HttpEntity entity = response.getEntity();
            return EntityUtils.toByteArray(entity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnect(response, httpClient);
        }
    }

    public String sendPostByHttps(String url, String body) {

        CloseableHttpResponse response = null;
        // 处理请求路径
        url = UriComponentsBuilder.fromHttpUrl(url)
                .toUriString();
        //创建httpclient对象
        CloseableHttpClient client = null;
        String respBody;
        try {
            client = HttpClients.custom()
                    .setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom()
                            //忽略掉对服务器端证书的校验
                            .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                            .build(), NoopHostnameVerifier.INSTANCE))
                    .build();
            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            // 请求头设置
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("connection", "Keep-Alive");
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            // 情求体设置
            if (body != null) {
                httpPost.setEntity(new StringEntity(body, "utf-8"));
            }
            //执行请求操作，并拿到结果
            response = client.execute(httpPost);
            if (200 != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException(String.format("code is %s, " +
                                "%s", response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity())));
            }
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                respBody = EntityUtils.toString(entity);
                return respBody;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException ignored) {
            }
        }

        return null;
    }

    public String sendHttpsGet(String url, Map<String, String> header) {
        CloseableHttpResponse response = null;
        // 处理请求路径
        url = UriComponentsBuilder.fromHttpUrl(url)
                .toUriString();
        //创建httpclient对象
        CloseableHttpClient client = null;
        String respBody;

        try {
            client = HttpClients.custom()
                    .setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom()
                            //忽略掉对服务器端证书的校验
                            .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                            .build(), NoopHostnameVerifier.INSTANCE))
                    .build();
            //创建post方式请求对象
            HttpGet httpGet = new HttpGet(url);
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (200 != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException(String.format("code is %s, " +
                                "%s", response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity())));
            }
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnect(response, client);
        }
    }

    /**
     * 概述：发送http请求
     *
     * @param request
     * @return byte[]
     * @throws Exception
     */
    private byte[] execute(HttpUriRequest request) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(request);
            if (200 != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException(String.format("code is %s, " +
                                "%s", response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity())));
            }
            HttpEntity entity = response.getEntity();
            return EntityUtils.toByteArray(entity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            closeConnect(response, httpClient);
        }
    }

    /**
     * 关闭链接
     *
     * @param response   响应
     * @param httpClient 客户端
     */
    private void closeConnect(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        // 关闭响应链接
        HttpClientUtils.closeQuietly(response);
        // 关闭请求链接
        HttpClientUtils.closeQuietly(httpClient);
    }
}
