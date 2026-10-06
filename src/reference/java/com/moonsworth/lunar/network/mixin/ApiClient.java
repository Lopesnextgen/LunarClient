package com.moonsworth.lunar.network.mixin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient.Builder;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;

public class ApiClient {
   private String basePath = "https://analytics.lunarclientprod.com";
   protected List<ServerConfiguration> servers = new ArrayList<>(
      Arrays.asList(
         new ServerConfiguration("https://analytics.lunarclientprod.com", "Production", new HashMap<>()),
         new ServerConfiguration("https://analytics.lunarclientdev.com", "Development", new HashMap<>())
      )
   );
   protected Integer field1 = 0;
   protected Map<String, String> field2 = null;
   private boolean field3 = false;
   private Map<String, String> field4 = new HashMap<>();
   private Map<String, String> field5 = new HashMap<>();
   private String field6 = null;
   private Map<String, Authentication> field7;
   private DateFormat field8;
   private DateFormat field9;
   private boolean field10;
   private int field11;
   private InputStream field12;
   private boolean field13;
   private KeyManager[] field14;
   private OkHttpClient field15;
   private MixinHelper7 field16;
   private HttpLoggingInterceptor field17;

   public ApiClient() {
      this.init();
      this.method1();
      this.field7 = Collections.unmodifiableMap(this.field7);
   }

   public ApiClient(OkHttpClient okhttpclient1) {
      this.init();
      this.field15 = okhttpclient1;
      this.field7 = Collections.unmodifiableMap(this.field7);
   }

   private void method1() {
      this.method2(Collections.emptyList());
   }

   private void method2(List<Interceptor> list1) {
      Builder builder2 = new Builder();
      builder2.addNetworkInterceptor(this.method74());

      for (Interceptor interceptor4 : list1) {
         builder2.addInterceptor(interceptor4);
      }

      this.field15 = builder2.build();
   }

   private void init() {
      this.field13 = true;
      this.field16 = new MixinHelper7();
      this.method33("OpenAPI-Generator/0.1.0-SNAPSHOT/java");
      this.field7 = new HashMap<>();
   }

   public String getBasePath() {
      return this.basePath;
   }

   public ApiClient method3(String text1) {
      this.basePath = text1;
      this.field1 = null;
      return this;
   }

   public List<ServerConfiguration> getServers() {
      return this.servers;
   }

   public ApiClient method5(List<ServerConfiguration> list1) {
      this.servers = list1;
      return this;
   }

   public Integer method6() {
      return this.field1;
   }

   public ApiClient method7(Integer number1) {
      this.field1 = number1;
      return this;
   }

   public Map<String, String> method8() {
      return this.field2;
   }

   public ApiClient method9(Map<String, String> map1) {
      this.field2 = map1;
      return this;
   }

   public OkHttpClient method10() {
      return this.field15;
   }

   public ApiClient method11(OkHttpClient okhttpclient1) {
      this.field15 = Objects.requireNonNull(okhttpclient1, "HttpClient must not be null!");
      return this;
   }

   public MixinHelper7 method12() {
      return this.field16;
   }

   public ApiClient method13(MixinHelper7 mixinhelper71) {
      this.field16 = mixinhelper71;
      return this;
   }

   public boolean method14() {
      return this.field13;
   }

   public ApiClient method15(boolean flag1) {
      this.field13 = flag1;
      this.method75();
      return this;
   }

   public InputStream method16() {
      return this.field12;
   }

   public ApiClient method17(InputStream input1) {
      this.field12 = input1;
      this.method75();
      return this;
   }

   public KeyManager[] method18() {
      return this.field14;
   }

   public ApiClient method19(KeyManager[] items1) {
      this.field14 = items1;
      this.method75();
      return this;
   }

   public DateFormat getDateFormat() {
      return this.field8;
   }

   public ApiClient method20(DateFormat dateformat1) {
      MixinHelper7.method11(dateformat1);
      return this;
   }

   public ApiClient method21(DateFormat dateformat1) {
      MixinHelper7.method12(dateformat1);
      return this;
   }

   public ApiClient method22(DateTimeFormatter datetimeformatter1) {
      MixinHelper7.method9(datetimeformatter1);
      return this;
   }

   public ApiClient method23(DateTimeFormatter datetimeformatter1) {
      MixinHelper7.method10(datetimeformatter1);
      return this;
   }

   public ApiClient method24(boolean flag1) {
      MixinHelper7.method6(flag1);
      return this;
   }

   public Map<String, Authentication> method25() {
      return this.field7;
   }

   public Authentication method26(String text1) {
      return this.field7.get(text1);
   }

   public void setUsername(String text1) {
      for (Authentication mixinhelper103 : this.field7.values()) {
         if (mixinhelper103 instanceof HttpBasicAuth) {
            ((HttpBasicAuth)mixinhelper103).setUsername(text1);
            return;
         }
      }

      throw new RuntimeException("No HTTP basic authentication configured!");
   }

   public void method27(String text1) {
      for (Authentication mixinhelper103 : this.field7.values()) {
         if (mixinhelper103 instanceof HttpBasicAuth) {
            ((HttpBasicAuth)mixinhelper103).setPassword(text1);
            return;
         }
      }

      throw new RuntimeException("No HTTP basic authentication configured!");
   }

   public void method28(String text1) {
      for (Authentication mixinhelper103 : this.field7.values()) {
         if (mixinhelper103 instanceof ApiKeyAuth) {
            ((ApiKeyAuth)mixinhelper103).method3(text1);
            return;
         }
      }

      throw new RuntimeException("No API key authentication configured!");
   }

   public void method29(String text1) {
      for (Authentication mixinhelper103 : this.field7.values()) {
         if (mixinhelper103 instanceof ApiKeyAuth) {
            ((ApiKeyAuth)mixinhelper103).method5(text1);
            return;
         }
      }

      throw new RuntimeException("No API key authentication configured!");
   }

   public void method30(String text1) {
      throw new RuntimeException("No OAuth2 authentication configured!");
   }

   public void method31(String text1, String text2, String text3, String text4) {
      throw new RuntimeException("No AWS4 authentication configured!");
   }

   public void method32(String text1, String text2, String text3, String text4, String text5) {
      throw new RuntimeException("No AWS4 authentication configured!");
   }

   public ApiClient method33(String text1) {
      this.method34("User-Agent", text1);
      return this;
   }

   public ApiClient method34(String text1, String text2) {
      this.field4.put(text1, text2);
      return this;
   }

   public ApiClient method35(String text1, String text2) {
      this.field5.put(text1, text2);
      return this;
   }

   public boolean method36() {
      return this.field3;
   }

   public ApiClient method37(boolean flag1) {
      if (flag1 != this.field3) {
         if (flag1) {
            this.field17 = new HttpLoggingInterceptor();
            this.field17.setLevel(Level.BODY);
            this.field15 = this.field15.newBuilder().addInterceptor(this.field17).build();
         } else {
            Builder builder2 = this.field15.newBuilder();
            builder2.interceptors().remove(this.field17);
            this.field15 = builder2.build();
            this.field17 = null;
         }
      }

      this.field3 = flag1;
      return this;
   }

   public String method38() {
      return this.field6;
   }

   public ApiClient method39(String text1) {
      this.field6 = text1;
      return this;
   }

   public int method40() {
      return this.field15.connectTimeoutMillis();
   }

   public ApiClient method41(int number1) {
      this.field15 = this.field15.newBuilder().connectTimeout(number1, TimeUnit.MILLISECONDS).build();
      return this;
   }

   public int method42() {
      return this.field15.readTimeoutMillis();
   }

   public ApiClient method43(int number1) {
      this.field15 = this.field15.newBuilder().readTimeout(number1, TimeUnit.MILLISECONDS).build();
      return this;
   }

   public int method44() {
      return this.field15.writeTimeoutMillis();
   }

   public ApiClient method45(int number1) {
      this.field15 = this.field15.newBuilder().writeTimeout(number1, TimeUnit.MILLISECONDS).build();
      return this;
   }

   public String method46(Object obj1) {
      if (obj1 == null) {
         return "";
      }

      if (!(obj1 instanceof Date) && !(obj1 instanceof OffsetDateTime) && !(obj1 instanceof LocalDate)) {
         if (obj1 instanceof Collection) {
            StringBuilder builder5 = new StringBuilder();

            for (Object obj4 : (Collection)obj1) {
               if (builder5.length() > 0) {
                  builder5.append(",");
               }

               builder5.append(obj4);
            }

            return builder5.toString();
         } else {
            return String.valueOf(obj1);
         }
      } else {
         String text2 = MixinHelper7.method7(obj1);
         return text2.substring(1, text2.length() - 1);
      }
   }

   public List<Pair> method47(String text1, Object obj2) {
      ArrayList list3 = new ArrayList();
      if (text1 != null && !text1.isEmpty() && obj2 != null && !(obj2 instanceof Collection)) {
         list3.add(new Pair(text1, this.method46(obj2)));
         return list3;
      } else {
         return list3;
      }
   }

   public List<Pair> method48(String text1, String text2, Collection list3) {
      ArrayList list4 = new ArrayList();
      if (text2 == null || text2.isEmpty() || list3 == null || list3.isEmpty()) {
         return list4;
      }

      if ("multi".equals(text1)) {
         for (Object obj10 : list3) {
            list4.add(new Pair(text2, this.escapeString(this.method46(obj10))));
         }

         return list4;
      } else {
         String text5 = ",";
         if ("ssv".equals(text1)) {
            text5 = this.escapeString(" ");
         } else if ("tsv".equals(text1)) {
            text5 = this.escapeString("\t");
         } else if ("pipes".equals(text1)) {
            text5 = this.escapeString("|");
         }

         StringBuilder builder6 = new StringBuilder();

         for (Object obj8 : list3) {
            builder6.append(text5);
            builder6.append(this.escapeString(this.method46(obj8)));
         }

         list4.add(new Pair(text2, builder6.substring(text5.length())));
         return list4;
      }
   }

   public String method49(String text1, Collection list2) {
      if ("multi".equals(text1)) {
         return this.method46(list2);
      }

      String text3 = ",";
      if ("ssv".equals(text1)) {
         text3 = " ";
      } else if ("tsv".equals(text1)) {
         text3 = "\t";
      } else if ("pipes".equals(text1)) {
         text3 = "|";
      }

      StringBuilder builder4 = new StringBuilder();

      for (Object obj6 : list2) {
         builder4.append(text3);
         builder4.append(this.method46(obj6));
      }

      return builder4.substring(text3.length());
   }

   public String method50(String text1) {
      return text1.replaceAll(".*[/\\\\]", "");
   }

   public boolean method51(String text1) {
      String text2 = "(?i)^(application/json|[^;/ \t]+/[^;/ \t]+[+]json)[ \t]*(;.*)?$";
      return text1 != null && (text1.matches(text2) || text1.equals("*/*"));
   }

   public String method52(String[] items1) {
      if (items1.length == 0) {
         return null;
      }

      for (String text5 : items1) {
         if (this.method51(text5)) {
            return text5;
         }
      }

      return StringUtil.join(items1, ",");
   }

   public String method53(String[] items1) {
      if (items1.length == 0) {
         return null;
      }

      if (items1[0].equals("*/*")) {
         return "application/json";
      }

      for (String text5 : items1) {
         if (this.method51(text5)) {
            return text5;
         }
      }

      return items1[0];
   }

   public String escapeString(String text1) {
      try {
         return URLEncoder.encode(text1, "utf8").replaceAll("\\+", "%20");
      } catch (UnsupportedEncodingException unsupportedencodingexception3) {
         return text1;
      }
   }

   public <T> T method54(Response response1, Type type2) {
      if (response1 == null || type2 == null) {
         return null;
      }

      if ("byte[]".equals(type2.toString())) {
         try {
            return (T)response1.body().bytes();
         } catch (IOException exception5) {
            throw new ApiException(exception5);
         }
      } else {
         if (type2.equals(File.class)) {
            return (T)this.method56(response1);
         }

         String text3;
         try {
            if (response1.body() != null) {
               text3 = response1.body().string();
            } else {
               text3 = null;
            }
         } catch (IOException exception6) {
            throw new ApiException(exception6);
         }

         if (text3 != null && !"".equals(text3)) {
            String text4 = response1.headers().get("Content-Type");
            if (text4 == null) {
               text4 = "application/json";
            }

            if (this.method51(text4)) {
               return MixinHelper7.method8(text3, type2);
            } else if (type2.equals(String.class)) {
               return (T)text3;
            } else {
               throw new ApiException(
                  "Content type \"" + text4 + "\" is not supported for type: " + type2, response1.code(), response1.headers().toMultimap(), text3
               );
            }
         } else {
            return null;
         }
      }
   }

   public RequestBody method55(Object obj1, String text2) {
      if (obj1 instanceof byte[]) {
         return RequestBody.create(MediaType.parse(text2), (byte[])obj1);
      }

      if (obj1 instanceof File) {
         return RequestBody.create(MediaType.parse(text2), (File)obj1);
      }

      if ("text/plain".equals(text2) && obj1 instanceof String) {
         return RequestBody.create(MediaType.parse(text2), (String)obj1);
      }

      if (this.method51(text2)) {
         String text3;
         if (obj1 != null) {
            text3 = MixinHelper7.method7(obj1);
         } else {
            text3 = "";
         }

         return RequestBody.create(MediaType.parse(text2), text3);
      } else if (obj1 instanceof String) {
         return RequestBody.create(MediaType.parse(text2), (String)obj1);
      } else {
         throw new ApiException("Content type \"" + text2 + "\" is not supported");
      }
   }

   public File method56(Response response1) {
      try {
         File file2 = this.method57(response1);
         BufferedSink bufferedsink3 = Okio.buffer(Okio.sink(file2));
         bufferedsink3.writeAll(response1.body().source());
         bufferedsink3.close();
         return file2;
      } catch (IOException exception4) {
         throw new ApiException(exception4);
      }
   }

   public File method57(Response response1) {
      String text2 = null;
      String text3 = response1.header("Content-Disposition");
      if (text3 != null && !"".equals(text3)) {
         Pattern pattern4 = Pattern.compile("filename=['\"]?([^'\"\\s]+)['\"]?");
         Matcher matcher5 = pattern4.matcher(text3);
         if (matcher5.find()) {
            text2 = this.method50(matcher5.group(1));
         }
      }

      String text7 = null;
      String text9 = null;
      if (text2 == null) {
         text7 = "download-";
         text9 = "";
      } else {
         int index6 = text2.lastIndexOf(".");
         if (index6 == -1) {
            text7 = text2 + "-";
         } else {
            text7 = text2.substring(0, index6) + "-";
            text9 = text2.substring(index6);
         }

         if (text7.length() < 3) {
            text7 = "download-";
         }
      }

      return this.field6 == null ? Files.createTempFile(text7, text9).toFile() : Files.createTempFile(Paths.get(this.field6), text7, text9).toFile();
   }

   public <T> ApiResponse<T> method58(Call call1) {
      return this.method59(call1, null);
   }

   public <T> ApiResponse<T> method59(Call call1, Type type2) {
      try {
         Response response3 = call1.execute();
         Object obj4 = this.method62(response3, type2);
         return new ApiResponse<>(response3.code(), response3.headers().toMultimap(), (T)obj4);
      } catch (IOException exception5) {
         throw new ApiException(exception5);
      }
   }

   public <T> void method60(Call call1, ApiCallback<T> mixinhelper32) {
      this.method61(call1, null, mixinhelper32);
   }

   public <T> void method61(Call call1, final Type type2, final ApiCallback<T> mixinhelper33) {
      call1.enqueue(new Callback() {
         public void onFailure(Call call1_, IOException exception2x) {
            mixinhelper33.method1(new ApiException(exception2x), 0, null);
         }

         public void onResponse(Call call1_, Response response2x) {
            Object obj3x;
            try {
               obj3x = ApiClient.this.method62(response2x, type2);
            } catch (ApiException mixinhelperexception5) {
               mixinhelper33.method1(mixinhelperexception5, response2x.code(), response2x.headers().toMultimap());
               return;
            } catch (Exception exception6) {
               mixinhelper33.method1(new ApiException(exception6), response2x.code(), response2x.headers().toMultimap());
               return;
            }

            mixinhelper33.method2(obj3x, response2x.code(), response2x.headers().toMultimap());
         }
      });
   }

   public <T> T method62(Response response1, Type type2) {
      if (response1.isSuccessful()) {
         if (type2 != null && response1.code() != 204) {
            return this.method54(response1, type2);
         }

         if (response1.body() != null) {
            try {
               response1.body().close();
            } catch (Exception exception5) {
               throw new ApiException(response1.message(), exception5, response1.code(), response1.headers().toMultimap());
            }
         }

         return null;
      } else {
         String text3 = null;
         if (response1.body() != null) {
            try {
               text3 = response1.body().string();
            } catch (IOException exception6) {
               throw new ApiException(response1.message(), exception6, response1.code(), response1.headers().toMultimap());
            }
         }

         throw new ApiException(response1.message(), response1.code(), response1.headers().toMultimap(), text3);
      }
   }

   public Call method63(
      String text1,
      String text2,
      String text3,
      List<Pair> list4,
      List<Pair> list5,
      Object obj6,
      Map<String, String> map7,
      Map<String, String> map8,
      Map<String, Object> map9,
      String[] items10,
      ApiCallback mixinhelper311
   ) {
      Request request12 = this.method64(text1, text2, text3, list4, list5, obj6, map7, map8, map9, items10, mixinhelper311);
      return this.field15.newCall(request12);
   }

   public Request method64(
      String text1,
      String text2,
      String text3,
      List<Pair> list4,
      List<Pair> list5,
      Object obj6,
      Map<String, String> map7,
      Map<String, String> map8,
      Map<String, Object> map9,
      String[] items10,
      ApiCallback mixinhelper311
   ) {
      ArrayList list12 = new ArrayList(list4);
      list12.addAll(list5);
      String text13 = this.method65(text1, text2, list4, list5);
      String text15 = (String)map7.get("Content-Type");
      String text16 = text15;
      if (text16 != null && text16.contains(";")) {
         text16 = text15.substring(0, text15.indexOf(";"));
      }

      RequestBody requestbody14;
      if (!HttpMethod.permitsRequestBody(text3)) {
         requestbody14 = null;
      } else if ("application/x-www-form-urlencoded".equals(text16)) {
         requestbody14 = this.method69(map9);
      } else if ("multipart/form-data".equals(text16)) {
         requestbody14 = this.method70(map9);
      } else if (obj6 == null) {
         if ("DELETE".equals(text3)) {
            requestbody14 = null;
         } else {
            requestbody14 = RequestBody.create(text15 == null ? null : MediaType.parse(text15), "");
         }
      } else {
         requestbody14 = this.method55(obj6, text15);
      }

      this.method68(items10, list12, map7, map8, this.method77(requestbody14), text3, URI.create(text13));
      okhttp3.Request.Builder builder17 = new okhttp3.Request.Builder().url(text13);
      this.method66(map7, builder17);
      this.method67(map8, builder17);
      builder17.tag(mixinhelper311);
      Request request18 = null;
      if (mixinhelper311 != null && requestbody14 != null) {
         ProgressRequestBody requestbodyimpl19 = new ProgressRequestBody(requestbody14, mixinhelper311);
         request18 = builder17.method(text3, requestbodyimpl19).build();
      } else {
         request18 = builder17.method(text3, requestbody14).build();
      }

      return request18;
   }

   public String method65(String text1, String text2, List<Pair> list3, List<Pair> list4) {
      StringBuilder builder5 = new StringBuilder();
      if (text1 != null) {
         builder5.append(text1).append(text2);
      } else {
         String text6;
         if (this.field1 != null) {
            if (this.field1 < 0 || this.field1 >= this.servers.size()) {
               throw new ArrayIndexOutOfBoundsException(
                  String.format("Invalid index %d when selecting the host settings. Must be less than %d", this.field1, this.servers.size())
               );
            }

            text6 = this.servers.get(this.field1).method1(this.field2);
         } else {
            text6 = this.basePath;
         }

         builder5.append(text6).append(text2);
      }

      if (list3 != null && !list3.isEmpty()) {
         String text10 = text2.contains("?") ? "&" : "?";

         for (Pair mixinhelper88 : list3) {
            if (mixinhelper88.getValue() != null) {
               if (text10 != null) {
                  builder5.append(text10);
                  text10 = null;
               } else {
                  builder5.append("&");
               }

               String text9 = this.method46(mixinhelper88.getValue());
               builder5.append(this.escapeString(mixinhelper88.getName())).append("=").append(this.escapeString(text9));
            }
         }
      }

      if (list4 != null && !list4.isEmpty()) {
         String text11 = builder5.toString().contains("?") ? "&" : "?";

         for (Pair mixinhelper813 : list4) {
            if (mixinhelper813.getValue() != null) {
               if (text11 != null) {
                  builder5.append(text11);
                  text11 = null;
               } else {
                  builder5.append("&");
               }

               String text14 = this.method46(mixinhelper813.getValue());
               builder5.append(this.escapeString(mixinhelper813.getName())).append("=").append(text14);
            }
         }
      }

      return builder5.toString();
   }

   public void method66(Map<String, String> map1, okhttp3.Request.Builder builder2) {
      for (Entry entry4 : map1.entrySet()) {
         builder2.header((String)entry4.getKey(), this.method46(entry4.getValue()));
      }

      for (Entry entry6 : this.field4.entrySet()) {
         if (!map1.containsKey(entry6.getKey())) {
            builder2.header((String)entry6.getKey(), this.method46(entry6.getValue()));
         }
      }
   }

   public void method67(Map<String, String> map1, okhttp3.Request.Builder builder2) {
      for (Entry entry4 : map1.entrySet()) {
         builder2.addHeader("Cookie", String.format("%s=%s", entry4.getKey(), entry4.getValue()));
      }

      for (Entry entry6 : this.field5.entrySet()) {
         if (!map1.containsKey(entry6.getKey())) {
            builder2.addHeader("Cookie", String.format("%s=%s", entry6.getKey(), entry6.getValue()));
         }
      }
   }

   public void method68(String[] items1, List<Pair> list2, Map<String, String> map3, Map<String, String> map4, String text5, String text6, URI uri7) {
      for (String text11 : items1) {
         Authentication mixinhelper1012 = this.field7.get(text11);
         if (mixinhelper1012 == null) {
            throw new RuntimeException("Authentication undefined: " + text11);
         }

         mixinhelper1012.method1(list2, map3, map4, text5, text6, uri7);
      }
   }

   public RequestBody method69(Map<String, Object> map1) {
      okhttp3.FormBody.Builder builder2 = new okhttp3.FormBody.Builder();

      for (Entry entry4 : map1.entrySet()) {
         builder2.add((String)entry4.getKey(), this.method46(entry4.getValue()));
      }

      return builder2.build();
   }

   public RequestBody method70(Map<String, Object> map1) {
      okhttp3.MultipartBody.Builder builder2 = new okhttp3.MultipartBody.Builder().setType(MultipartBody.FORM);

      for (Entry entry4 : map1.entrySet()) {
         if (entry4.getValue() instanceof File) {
            File file8 = (File)entry4.getValue();
            this.method72(builder2, (String)entry4.getKey(), file8);
         } else if (entry4.getValue() instanceof List) {
            for (Object obj7 : (List)entry4.getValue()) {
               if (obj7 instanceof File) {
                  this.method72(builder2, (String)entry4.getKey(), (File)obj7);
               } else {
                  this.method73(builder2, (String)entry4.getKey(), entry4.getValue());
               }
            }
         } else {
            this.method73(builder2, (String)entry4.getKey(), entry4.getValue());
         }
      }

      return builder2.build();
   }

   public String method71(File file1) {
      String text2 = URLConnection.guessContentTypeFromName(file1.getName());
      return text2 == null ? "application/octet-stream" : text2;
   }

   private void method72(okhttp3.MultipartBody.Builder builder1, String text2, File file3) {
      Headers headers4 = Headers.of(new String[]{"Content-Disposition", "form-data; name=\"" + text2 + "\"; filename=\"" + file3.getName() + "\""});
      MediaType mediatype5 = MediaType.parse(this.method71(file3));
      builder1.addPart(headers4, RequestBody.create(mediatype5, file3));
   }

   private void method73(okhttp3.MultipartBody.Builder builder1, String text2, Object obj3) {
      RequestBody requestbody4;
      if (obj3 instanceof String) {
         requestbody4 = RequestBody.create(MediaType.parse("text/plain"), (String)obj3);
      } else {
         String text5;
         if (obj3 != null) {
            text5 = MixinHelper7.method7(obj3);
         } else {
            text5 = null;
         }

         requestbody4 = RequestBody.create(MediaType.parse("application/json"), text5);
      }

      Headers headers6 = Headers.of(new String[]{"Content-Disposition", "form-data; name=\"" + text2 + "\""});
      builder1.addPart(headers6, requestbody4);
   }

   private Interceptor method74() {
      return new Interceptor() {
         public Response intercept(Chain chain1) {
            Request request2 = chain1.request();
            Response response3 = chain1.proceed(request2);
            if (request2.tag() instanceof ApiCallback) {
               ApiCallback mixinhelper34 = (ApiCallback)request2.tag();
               return response3.newBuilder().body(new ProgressResponseBody(response3.body(), mixinhelper34)).build();
            } else {
               return response3;
            }
         }
      };
   }

   private void method75() {
      try {
         TrustManager[] items1;
         Object obj2;
         if (!this.field13) {
            items1 = new TrustManager[]{new X509TrustManager() {
               @Override
               public void checkClientTrusted(X509Certificate[] items1_, String text2x) {
               }

               @Override
               public void checkServerTrusted(X509Certificate[] items1_, String text2x) {
               }

               @Override
               public X509Certificate[] getAcceptedIssuers() {
                  return new X509Certificate[0];
               }
            }};
            obj2 = new HostnameVerifier() {
               @Override
               public boolean verify(String text1, SSLSession sslsession2x) {
                  return true;
               }
            };
         } else {
            TrustManagerFactory trustmanagerfactory3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            if (this.field12 == null) {
               trustmanagerfactory3.init((KeyStore)null);
            } else {
               Object obj4 = null;
               CertificateFactory certificatefactory5 = CertificateFactory.getInstance("X.509");
               Collection list6 = certificatefactory5.generateCertificates(this.field12);
               if (list6.isEmpty()) {
                  throw new IllegalArgumentException("expected non-empty set of trusted certificates");
               }

               KeyStore keystore7 = this.method76((char[])obj4);
               int index8 = 0;

               for (Certificate certificate10 : list6) {
                  String text11 = "ca" + index8++;
                  keystore7.setCertificateEntry(text11, certificate10);
               }

               trustmanagerfactory3.init(keystore7);
            }

            items1 = trustmanagerfactory3.getTrustManagers();
            obj2 = OkHostnameVerifier.INSTANCE;
         }

         SSLContext sslcontext13 = SSLContext.getInstance("TLS");
         sslcontext13.init(this.field14, items1, new SecureRandom());
         this.field15 = this.field15
            .newBuilder()
            .sslSocketFactory(sslcontext13.getSocketFactory(), (X509TrustManager)items1[0])
            .hostnameVerifier((HostnameVerifier)obj2)
            .build();
      } catch (GeneralSecurityException generalsecurityexception12) {
         throw new RuntimeException(generalsecurityexception12);
      }
   }

   private KeyStore method76(char[] items1) {
      try {
         KeyStore keystore2 = KeyStore.getInstance(KeyStore.getDefaultType());
         keystore2.load(null, items1);
         return keystore2;
      } catch (IOException exception3) {
         throw new AssertionError(exception3);
      }
   }

   private String method77(RequestBody requestbody1) {
      if (requestbody1 != null) {
         try {
            Buffer buffer2 = new Buffer();
            requestbody1.writeTo(buffer2);
            return buffer2.readUtf8();
         } catch (IOException exception3) {
            throw new ApiException(exception3);
         }
      } else {
         return "";
      }
   }
}
