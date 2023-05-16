package com.example.demo.config;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.http.HttpHeaders;
import nl.altindag.ssl.SSLFactory;

/**
 * @author 黄晓峰
 * @since 2022/3/8 16:38
 */


@Configuration
@ConditionalOnProperty(prefix = "es.client", name = "enabled", havingValue = "true")
public class ElasticsearchRestTemplateConfig extends ElasticsearchConfiguration {
//  @Value("${spring.elasticsearch.rest.uris}")
//  private String uris;
//  @Value("${spring.elasticsearch.rest.username}")
//  private String username;
//  @Value("${spring.elasticsearch.rest.password}")
//  private String password;

    /**
     * 已经在elastic autoconfig 中创建，可以直接使用
     */
//    @Resource
//    ElasticsearchConverter elasticsearchConverter;
//  @Override
//  public RestHighLevelClient elasticsearchClient() {
//    HttpHeaders defaultHeaders = new HttpHeaders();
//    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//    credentialsProvider.setCredentials(AuthScope.ANY,
//        new UsernamePasswordCredentials(username, password));
//    final TrustManager[] trustAllCerts = new TrustManager[]{
//        new X509TrustManager() {
//          @Override
//          public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
//              String authType) {
//          }
//          @Override
//          public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
//              String authType) {
//          }
//          @Override
//          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//            return new java.security.cert.X509Certificate[]{};
//          }
//        }
//    };
////            final SSLContext sslContext = SSLContext.getInstance(SSL);
//    SSLContext sslContext = null;
//    try {
//      sslContext = SSLContext.getInstance("SSL");
//      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//
//    } catch (NoSuchAlgorithmException | KeyManagementException e) {
//      e.printStackTrace();
//    }
//    final ClientConfiguration clientConfiguration = ClientConfiguration
//        .builder()
//        .connectedTo(uris)
//        .usingSsl(sslContext, new HostnameVerifier() {
//          @Override
//          public boolean verify(String hostname, SSLSession session) {
//            return true;
//          }
//        })
//        .withDefaultHeaders(defaultHeaders)
//        .withHttpClientConfigurer(new RestClientBuilder.HttpClientConfigCallback() {
//          public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//            httpClientBuilder.disableAuthCaching();
//            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//          }
//        })
//        .withConnectTimeout(5000*1000)
//        .withSocketTimeout(6000*1000)
//        .withHeaders(() -> {
//          HttpHeaders headers = new HttpHeaders();
//          headers.add("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//          return headers;
//        })
//        .build();
//    return RestClients.create(clientConfiguration).rest();
//  }
    @Autowired
    private EsClientProperties esClientProperties;
    @Override
    public ClientConfiguration clientConfiguration() {
        //FIX 修改SSL证书校验失败的问题
        SSLFactory sslFactory = SSLFactory.builder()
                .withUnsafeTrustMaterial()
                .withUnsafeHostnameVerifier()
                .build();
        System.out.println("es port"+esClientProperties.getHost());
        return ClientConfiguration.builder()
                .connectedTo(esClientProperties.getHost(), esClientProperties.getPort())
                .usingSsl(sslFactory.getSslContext(), sslFactory.getHostnameVerifier())
                .withBasicAuth(esClientProperties.getUsername(), esClientProperties.getPassword())
                .withConnectTimeout(Duration.ofSeconds(30))
                .withSocketTimeout(Duration.ofSeconds(30))
                .build();
    }

}