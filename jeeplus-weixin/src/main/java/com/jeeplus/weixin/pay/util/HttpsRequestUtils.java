package com.jeeplus.weixin.pay.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * SSL层的Http请求工具类
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-05 19:42.
 */
public class HttpsRequestUtils {

    public interface ResultListener {

         void onConnectionPoolTimeoutError();

    }

    private static Logger log = LoggerFactory.getLogger(HttpsRequestUtils.class);

    // 表示请求器是否已经做了初始化工作
    private boolean hasInit = false;

    // 连接超时时间，默认10秒
    private int socketTimeout = 10000;

    // 传输超时时间，默认30秒
    private int connectTimeout = 30000;

    // 请求器的配置
    private RequestConfig requestConfig;

    // HTTP请求器
    private CloseableHttpClient httpClient;

    public HttpsRequestUtils() throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, IOException {
        init();
    }

    /**
     * 对安全性有要求的网站一般使用https来加密传输的请求和响应。https离不开证书，关于证书不在多说。Apache的HttpClient支持https，
     * 面是官方的样例程序，程序中使用了my.store这个文件，
     * 这个文件不是网站的证书，而是一份包含自己密码的自己的证书库。这个文件是需要自己生成的，使用jdk中的keytool命令可以很方便的生成my.store文件。步骤如下（以支付宝为例）：
     * 浏览器（以chrome为例）访问https://www.alipay.com/，点击域名左侧的小锁，可以查看支付宝的证书信息
     *
     *
     * 将支付包的证书信息导出，证书格式有很多中，der、cer等。随便选择即可。 命令行或者shell执行 keytool -import -alias
     * "my alipay cert" -file www.alipay.com.cert -keystore my.store,
     * 如果keytool命令不识别，去检查一下jdk的环境变量是否设置正确。”my alipay
     * cert”是个别名，随便取。“www.alipay.com.cert”这个文件就是从浏览器中导出的支付宝的证书。
     *
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private void init() throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyManagementException {
        if (ConfigureUtils.getCertLocalPath() == null || ConfigureUtils.getCertLocalPath().length() < 1) {

        }
        KeyStore keyStore  = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(new File(ConfigureUtils.getCertLocalPath()));// 加载本地的证书进行https加密传输
        try {
            keyStore.load(instream, ConfigureUtils.getCertPassword().toCharArray());// 设置证书密码
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, ConfigureUtils.getCertPassword().toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        // 根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
                .build();

        hasInit = true;
    }

    /**
     * 通过Https往API post xml数据
     *
     * @param url
     *            API地址
     * @param postDataXML
     *            要提交的XML数据对象
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */

    public String sendPost(String url, String postDataXML) throws IOException, KeyStoreException,
            UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {

        if (!hasInit) {
            init();
        }

        String result = null;

        HttpPost httpPost = new HttpPost(url);

        WeiXinPayUtils.log("API，POST过去的数据是：");
        WeiXinPayUtils.log(postDataXML);

        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        // 设置请求器的配置
        httpPost.setConfig(requestConfig);

        WeiXinPayUtils.log("executing request" + httpPost.getRequestLine());

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            log.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);

        } catch (ConnectTimeoutException e) {
            log.error("http get throw ConnectTimeoutException", e);

        } catch (SocketTimeoutException e) {
            log.error("http get throw SocketTimeoutException", e);

        } catch (Exception e) {
            log.error("http get throw Exception", e);

        } finally {
            httpPost.abort();
        }

        return result;
    }

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout
     *            连接时长，默认10秒
     */
    public void setSocketTimeout(int socketTimeout) {
        socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout
     *            传输时长，默认30秒
     */
    public void setConnectTimeout(int connectTimeout) {
        connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig() {
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
                .build();
    }

    /**
     * 允许商户自己做更高级更复杂的请求器配置
     *
     * @param requestConfig
     *            设置HttpsRequest的请求器配置
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        requestConfig = requestConfig;
    }

}
