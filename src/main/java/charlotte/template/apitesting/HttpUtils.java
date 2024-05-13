package charlotte.template.apitesting;

import org.slf4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.*;

import java.io.*;
import java.util.*;

public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static final String URL_PARAM_CONNECT_FLAG = "&";
    private static final String EMPTY = "";

    private static final CloseableHttpClient httpClient;
    private static final BasicCookieStore cookieStore;

    static {
        cookieStore = new BasicCookieStore();
        httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    }

    public static String httpGet(String url, Map<String, String> params, String enc) throws IOException {
        String totalUrl = url + "?" + getUrl(params, enc);
        return httpGet(totalUrl);
    }

    public static String buildGetUrl(String host, String path, Map<String, String> params, String enc) {
        String totalUrl = host + path + "?" + getUrl(params, enc);
        return totalUrl;
    }

    public static String httpGet(String url) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000)
                .setConnectTimeout(3000).setSocketTimeout(3000).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent", "Mozilla/5.0");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("Http req failed : " + statusCode);
            } else {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        }
    }

    public static String httpGetWithHeader(String url, Map<String, String> headers) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000)
                .setConnectTimeout(3000).setSocketTimeout(3000).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        headers.forEach(httpGet::setHeader);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("Http req failed : " + statusCode);
            } else {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        }
    }

    /**
     * 下载文件
     *
     * @param url
     * @param file
     * @return
     * @throws IOException
     */
    public static void downLoadFile(String url, String file) throws IOException
    {

        CloseableHttpClient httpclient = createHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();// 获取返回的状态值
        if (statusCode != HttpStatus.SC_OK)
        {
            throw new RuntimeException("Http req failed : " + statusCode);
        }

        byte[] result = EntityUtils.toByteArray(response.getEntity());

        FileUtils.writeByteArrayToFile(new File(file),result);
    }

    /**
     * POST方式提交数据
     *
     * @param url
     *            待请求的URL
     * @param params
     *            要提交的数据
     * @return 响应结果
     * @throws IOException
     *             IO异常
     */
    public static HttpResponse httpPost(String url, Map<String, String> params) throws Exception
    {

        // _httpClient
        HttpClient httpClient = createHttpClient();
        // get method
        HttpPost httpPost = new HttpPost(url);
        // set header
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // set params
        List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet())
        {
            httpParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try
        {
            httpPost.setEntity(new UrlEncodedFormEntity(httpParams));
        } catch (Exception e)
        {
            throw e;
        }

        // response
        HttpResponse response = null;
        try
        {
            response = httpClient.execute(httpPost);
        } catch (Exception e)
        {
            throw e;
        }
        return response;
    }


    /**
     * POST方式提交数据
     *
     * @param url
     *            待请求的URL
     * @param params
     *            要提交的数据
     * @return 响应结果
     * @throws IOException
     *             IO异常
     */
    public static String httpPostStringResp(String url, Map<String, String> params) throws Exception
    {
        // response
        HttpResponse response = httpPost(url, params);
        String temp = "";
        try
        {
            HttpEntity entity = response.getEntity();
            temp = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e)
        {
            throw e;
        }

        return temp;
    }

    /**
     * post请求，参数为json字符串
     *
     * @param url
     *            请求地址
     * @param jsonString
     *            json字符串
     * @return 响应
     */
    public static String postJson(String url, String jsonString,Map<String,String> headerMap)
            throws UnsupportedEncodingException, ClientProtocolException, IOException
    {
        String result = null;
        CloseableHttpClient httpClient = createHttpClient();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try
        {
//			 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            headerMap.entrySet().stream().forEach(entry -> post.setHeader(entry.getKey(),entry.getValue()));
            post.setEntity(new ByteArrayEntity(jsonString.getBytes("UTF-8")));
            response = httpClient.execute(post);
            if (response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException | ClientProtocolException e)
        {
            throw e;
        } finally
        {
            try
            {
                httpClient.close();
                if (response != null)
                {
                    response.close();
                }
            } catch (IOException e)
            {
                throw e;
            }
        }
    }



    private static String entityToString(HttpEntity entity) throws IOException
    {
        String result = null;
        if (entity != null)
        {
            long lenth = entity.getContentLength();
            if (lenth != -1 && lenth < 2048)
            {
                result = EntityUtils.toString(entity, "UTF-8");
            } else
            {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while ((l = reader1.read(tmp)) != -1)
                {
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }

    /**
     * 据Map生成URL字符串
     *
     * @param map
     *            Map
     * @param valueEnc
     *            URL编码
     * @return URL
     */
    private static String getUrl(Map<String, String> map, String valueEnc) {
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return URLEncodedUtils.format(params, valueEnc);
    }

    private static CloseableHttpClient createHttpClient() {
        return HttpClients.createDefault();
    }
}

