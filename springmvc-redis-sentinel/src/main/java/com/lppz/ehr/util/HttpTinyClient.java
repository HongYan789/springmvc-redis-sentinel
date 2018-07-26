/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lppz.ehr.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HttpTinyClient {

	private static Logger logger = LoggerFactory.getLogger(HttpTinyClient.class);
	
    static final String DEFAULT_CHARSET = "UTF-8";

    static public HttpResult httpGet(String url, List<String> headers, List<Object> paramValues,
        long readTimeoutMs) throws IOException {
        String encodedContent = encodingParams(paramValues, DEFAULT_CHARSET);
        url += (null == encodedContent) ? "" : ("?" + encodedContent);

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout((int) readTimeoutMs);
            conn.setReadTimeout((int) readTimeoutMs);
            setHeaders(conn, headers, DEFAULT_CHARSET);

            conn.connect();
            int respCode = conn.getResponseCode();
            String resp = null;

            if (HttpURLConnection.HTTP_OK == respCode) {
                resp = IOTinyUtils.toString(conn.getInputStream(), DEFAULT_CHARSET);
            } else {
                resp = IOTinyUtils.toString(conn.getErrorStream(), DEFAULT_CHARSET);
            }
            return new HttpResult(respCode, resp);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return new HttpResult(400, "{}");
    }

    static private String encodingParams(List<Object> paramValues, String encoding)
        throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        if (null == paramValues) {
            return null;
        }

        for (Iterator<Object> iter = paramValues.iterator(); iter.hasNext(); ) {
            Object prefix = iter.next();
            Object after = iter.next();

            sb.append(prefix).append("=");
            if(prefix instanceof String && after instanceof String){
                sb.append(URLEncoder.encode(after.toString(), encoding));
            }else {
                sb.append(after);
            }
            if (iter.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    static private void setHeaders(HttpURLConnection conn, List<String> headers, String encoding) {
        if (null != headers) {
            for (Iterator<String> iter = headers.iterator(); iter.hasNext(); ) {
                conn.addRequestProperty(iter.next(), iter.next());
            }
        }
        //conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);

        //String ts = String.valueOf(System.currentTimeMillis());
        //conn.addRequestProperty("Metaq-Client-RequestTS", ts);
    }

    /**
     * @return the http response of given http post request
     */
    static public HttpResult httpPost(String url, List<String> headers, String encodedContent,
        long readTimeoutMs) throws IOException {
//        String encodedContent = encodingParams(paramValues, encoding);

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout((int) readTimeoutMs);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            setHeaders(conn, headers, DEFAULT_CHARSET);

            conn.getOutputStream().write(encodedContent.getBytes(DEFAULT_CHARSET));

            int respCode = conn.getResponseCode();
            String resp = null;

            if (HttpURLConnection.HTTP_OK == respCode) {
                resp = IOTinyUtils.toString(conn.getInputStream(), DEFAULT_CHARSET);
            } else {
                resp = IOTinyUtils.toString(conn.getErrorStream(), DEFAULT_CHARSET);
            }
            return new HttpResult(respCode, resp);
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
    }

    static public class HttpResult {
        final public int code;
        final public String content;

        public HttpResult(int code, String content) {
            this.code = code;
            this.content = content;
        }
    }


	/***
	 * basic验证方式的json post请求方法
	 * @param url
	 * @param postJson
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws IOException
	 */
  	public static boolean JsonPostFromJSON(String url, String postJson,String userName,String passWord) throws IOException{
  		int statuscode = 0;
  		String hostname =url.split("//")[1].split(":")[0];
  		String port=url.split(":")[2].split("/")[0];
  		HttpHost target = new HttpHost(hostname, Integer.parseInt(port), url.split(":")[0]);
  		CredentialsProvider credsProvider = new BasicCredentialsProvider();
  		credsProvider.setCredentials(
  				new AuthScope(target.getHostName(), target.getPort()),
  				new UsernamePasswordCredentials(userName, passWord));
  		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
  		// Create AuthCache instance
  		AuthCache authCache = new BasicAuthCache();
  		// Generate BASIC scheme object and add it to the local
  		// auth cache
  		BasicScheme basicAuth = new BasicScheme();
  		authCache.put(target, basicAuth);
  		
  		// Add AuthCache to the execution context
  		HttpClientContext localContext = HttpClientContext.create();
  		localContext.setAuthCache(authCache);
  		
  		HttpPost post = new HttpPost(url);
  		
  	
  		StringEntity myEntity = new StringEntity(postJson, ContentType.APPLICATION_JSON);// 构造请求数据
  		post.setEntity(myEntity);// 设置请求体
  		String responseContent = null; // 响应内容
  		CloseableHttpResponse response = null;
  		try {
  			logger.info("转化成http协议，最终发送的postJson:{}",  postJson);
  			logger.info("<oms_2fico> post to: url={}", url);
  			response = httpclient.execute(target, post, localContext);
//          	response = httpclient.execute(post);
  			statuscode = response.getStatusLine().getStatusCode();
  			if (statuscode == 200) {
  				HttpEntity entity = response.getEntity();
  				responseContent = EntityUtils.toString(entity, "UTF-8");
  			} else {
  				logger.info(String.format("url:%s,statuscode:%s", url, statuscode));
  			}
  		} catch (IOException e) {
  			String msg = "<oms_2fico> failed: url=" + url;
  			logger.error(msg, e);
  			return false;
  		} finally {
  			try {
  				if (response != null)
  					response.close();
  			} catch (IOException e) {
  				logger.error(e.getMessage(), e);
  			} finally {
  				try {
  					if (httpclient != null)
  						httpclient.close();
  				} catch (IOException e) {
  					logger.error(e.getMessage(), e);
  				}
  			}
  		}
  		return true;
  	}
  	
  	
  	
  	public static HttpResult httpPostWithJson(String url,String code,JSONObject jsonObj){
  	    boolean isSuccess = false;
  	    
  	    HttpPost post = null;
  	    String resp = null;
  	    try {
  	        HttpClient httpClient = new DefaultHttpClient();

  	        // 设置超时时间
  	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
  	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
  	            
  	        post = new HttpPost(url);
  	        // 构造消息头
  	        post.setHeader("Content-type", "application/json; charset=utf-8");
  	        post.setHeader("Connection", "Close");
  	        post.setHeader("code", code);
  	                    
  	        // 构建消息实体
  	        StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
  	        entity.setContentEncoding("UTF-8");
  	        // 发送Json格式的数据请求
  	        entity.setContentType("application/json");
  	        post.setEntity(entity);
  	            
  	        HttpResponse response = httpClient.execute(post);
  	            
  	        // 检验返回码
  	        int statusCode = response.getStatusLine().getStatusCode();
  	        if(statusCode != HttpStatus.SC_OK){
  	        	logger.info("请求出错: "+statusCode);
  	            isSuccess = false;
  	        }else{
  	        	HttpEntity resEntity = response.getEntity();
  	            if(resEntity != null){
  	            	resp = EntityUtils.toString(resEntity,DEFAULT_CHARSET);
  	            }
  	        }
  	      return new HttpResult(statusCode, resp);
  	    } catch (Exception e) {
  	        e.printStackTrace();
  	        isSuccess = false;
  	      return new HttpResult(400, resp);
  	    }finally{
  	        if(post != null){
  	            try {
  	                post.releaseConnection();
  	                Thread.sleep(500);
  	            } catch (InterruptedException e) {
  	                e.printStackTrace();
  	            }
  	        }
  	    }
  	    
  	}

  	public static void main(String[] args) throws Exception{
		String content = "{\n" +
				"    \"userid\": \"zhangsan\",\n" +
				"    \"name\": \"zhangsan\",\n" +
				"    \"orderInDepts\" : \"{65543073:176402076543962512}\",\n" +
				"    \"department\": [65543073],\n" +
				"    \"position\": \"产品经理\",\n" +
				"    \"mobile\": \"13720230122\",\n" +
				"    \"tel\" : \"010-123333\",\n" +
				"    \"workPlace\" :\"\",\n" +
				"    \"remark\" : \"\",\n" +
				"    \"email\": \"zhangsan@gzdev.com\",\n" +
				"    \"orgEmail\": \"qiye@gzdev.com\",\n" +
				"    \"jobnumber\": \"111111\",\n" +
				"    \"isHide\": false,\n" +
				"    \"isSenior\": false\n" +
				"}";

		System.out.println("content: " + content);

		List<String> headers = new ArrayList<String>();
		headers.add("Content-Type");
		headers.add("application/json");

		HttpTinyClient.HttpResult result = HttpTinyClient.httpPost("http://127.0.0.1:8082/ding/test", headers, content, 3000L);
		System.out.println("result: " + result.content);
	}
    
}
