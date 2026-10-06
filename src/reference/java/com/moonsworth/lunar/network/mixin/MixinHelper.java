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

public class MixinHelper {
   private String basePath = "https://analytics.lunarclientprod.com";
   protected List<MixinHelper5> servers = new ArrayList<>(
      Arrays.asList(
         new MixinHelper5("https://analytics.lunarclientprod.com", "Production", new HashMap<>()),
         new MixinHelper5("https://analytics.lunarclientdev.com", "Development", new HashMap<>())
      )
   );
   protected Integer field1 = 0;
   protected Map<String, String> field2 = null;
   private boolean field3 = false;
   private Map<String, String> field4 = new HashMap<>();
   private Map<String, String> field5 = new HashMap<>();
   private String field6 = null;
   private Map<String, MixinHelper10> field7;
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

   public MixinHelper() {
      this.init();
      this.method1();
      this.field7 = Collections.unmodifiableMap(this.field7);
   }

   public MixinHelper(OkHttpClient var1) {
      this.init();
      this.field15 = var1;
      this.field7 = Collections.unmodifiableMap(this.field7);
   }

   private void method1() {
      this.method2(Collections.emptyList());
   }

   private void method2(List<Interceptor> var1) {
      Builder var2 = new Builder();
      var2.addNetworkInterceptor(this.method74());

      for (Interceptor var4 : var1) {
         var2.addInterceptor(var4);
      }

      this.field15 = var2.build();
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

   public MixinHelper method3(String var1) {
      this.basePath = var1;
      this.field1 = null;
      return this;
   }

   public List<MixinHelper5> getServers() {
      return this.servers;
   }

   public MixinHelper method5(List<MixinHelper5> var1) {
      this.servers = var1;
      return this;
   }

   public Integer method6() {
      return this.field1;
   }

   public MixinHelper method7(Integer var1) {
      this.field1 = var1;
      return this;
   }

   public Map<String, String> method8() {
      return this.field2;
   }

   public MixinHelper method9(Map<String, String> var1) {
      this.field2 = var1;
      return this;
   }

   public OkHttpClient method10() {
      return this.field15;
   }

   public MixinHelper method11(OkHttpClient var1) {
      this.field15 = Objects.requireNonNull(var1, "HttpClient must not be null!");
      return this;
   }

   public MixinHelper7 method12() {
      return this.field16;
   }

   public MixinHelper method13(MixinHelper7 var1) {
      this.field16 = var1;
      return this;
   }

   public boolean method14() {
      return this.field13;
   }

   public MixinHelper method15(boolean var1) {
      this.field13 = var1;
      this.method75();
      return this;
   }

   public InputStream method16() {
      return this.field12;
   }

   public MixinHelper method17(InputStream var1) {
      this.field12 = var1;
      this.method75();
      return this;
   }

   public KeyManager[] method18() {
      return this.field14;
   }

   public MixinHelper method19(KeyManager[] var1) {
      this.field14 = var1;
      this.method75();
      return this;
   }

   public DateFormat getDateFormat() {
      return this.field8;
   }

   public MixinHelper method20(DateFormat var1) {
      MixinHelper7.method11(var1);
      return this;
   }

   public MixinHelper method21(DateFormat var1) {
      MixinHelper7.method12(var1);
      return this;
   }

   public MixinHelper method22(DateTimeFormatter var1) {
      MixinHelper7.method9(var1);
      return this;
   }

   public MixinHelper method23(DateTimeFormatter var1) {
      MixinHelper7.method10(var1);
      return this;
   }

   public MixinHelper method24(boolean var1) {
      MixinHelper7.method6(var1);
      return this;
   }

   public Map<String, MixinHelper10> method25() {
      return this.field7;
   }

   public MixinHelper10 method26(String var1) {
      return this.field7.get(var1);
   }

   public void setUsername(String var1) {
      for (MixinHelper10 var3 : this.field7.values()) {
         if (var3 instanceof MixinHelper103) {
            ((MixinHelper103)var3).setUsername(var1);
            return;
         }
      }

      throw new RuntimeException("No HTTP basic authentication configured!");
   }

   public void method27(String var1) {
      for (MixinHelper10 var3 : this.field7.values()) {
         if (var3 instanceof MixinHelper103) {
            ((MixinHelper103)var3).setPassword(var1);
            return;
         }
      }

      throw new RuntimeException("No HTTP basic authentication configured!");
   }

   public void method28(String var1) {
      for (MixinHelper10 var3 : this.field7.values()) {
         if (var3 instanceof MixinHelper102) {
            ((MixinHelper102)var3).method3(var1);
            return;
         }
      }

      throw new RuntimeException("No API key authentication configured!");
   }

   public void method29(String var1) {
      for (MixinHelper10 var3 : this.field7.values()) {
         if (var3 instanceof MixinHelper102) {
            ((MixinHelper102)var3).method5(var1);
            return;
         }
      }

      throw new RuntimeException("No API key authentication configured!");
   }

   public void method30(String var1) {
      throw new RuntimeException("No OAuth2 authentication configured!");
   }

   public void method31(String var1, String var2, String var3, String var4) {
      throw new RuntimeException("No AWS4 authentication configured!");
   }

   public void method32(String var1, String var2, String var3, String var4, String var5) {
      throw new RuntimeException("No AWS4 authentication configured!");
   }

   public MixinHelper method33(String var1) {
      this.method34("User-Agent", var1);
      return this;
   }

   public MixinHelper method34(String var1, String var2) {
      this.field4.put(var1, var2);
      return this;
   }

   public MixinHelper method35(String var1, String var2) {
      this.field5.put(var1, var2);
      return this;
   }

   public boolean method36() {
      return this.field3;
   }

   public MixinHelper method37(boolean var1) {
      if (var1 != this.field3) {
         if (var1) {
            this.field17 = new HttpLoggingInterceptor();
            this.field17.setLevel(Level.BODY);
            this.field15 = this.field15.newBuilder().addInterceptor(this.field17).build();
         } else {
            Builder var2 = this.field15.newBuilder();
            var2.interceptors().remove(this.field17);
            this.field15 = var2.build();
            this.field17 = null;
         }
      }

      this.field3 = var1;
      return this;
   }

   public String method38() {
      return this.field6;
   }

   public MixinHelper method39(String var1) {
      this.field6 = var1;
      return this;
   }

   public int method40() {
      return this.field15.connectTimeoutMillis();
   }

   public MixinHelper method41(int var1) {
      this.field15 = this.field15.newBuilder().connectTimeout(var1, TimeUnit.MILLISECONDS).build();
      return this;
   }

   public int method42() {
      return this.field15.readTimeoutMillis();
   }

   public MixinHelper method43(int var1) {
      this.field15 = this.field15.newBuilder().readTimeout(var1, TimeUnit.MILLISECONDS).build();
      return this;
   }

   public int method44() {
      return this.field15.writeTimeoutMillis();
   }

   public MixinHelper method45(int var1) {
      this.field15 = this.field15.newBuilder().writeTimeout(var1, TimeUnit.MILLISECONDS).build();
      return this;
   }

   public String method46(Object var1) {
      if (var1 == null) {
         return "";
      }

      if (!(var1 instanceof Date) && !(var1 instanceof OffsetDateTime) && !(var1 instanceof LocalDate)) {
         if (var1 instanceof Collection) {
            StringBuilder var5 = new StringBuilder();

            for (Object var4 : (Collection)var1) {
               if (var5.length() > 0) {
                  var5.append(",");
               }

               var5.append(var4);
            }

            return var5.toString();
         } else {
            return String.valueOf(var1);
         }
      } else {
         String var2 = MixinHelper7.method7(var1);
         return var2.substring(1, var2.length() - 1);
      }
   }

   public List<MixinHelper8> method47(String var1, Object var2) {
      ArrayList var3 = new ArrayList();
      if (var1 != null && !var1.isEmpty() && var2 != null && !(var2 instanceof Collection)) {
         var3.add(new MixinHelper8(var1, this.method46(var2)));
         return var3;
      } else {
         return var3;
      }
   }

   public List<MixinHelper8> method48(String var1, String var2, Collection var3) {
      ArrayList var4 = new ArrayList();
      if (var2 == null || var2.isEmpty() || var3 == null || var3.isEmpty()) {
         return var4;
      }

      if ("multi".equals(var1)) {
         for (Object var10 : var3) {
            var4.add(new MixinHelper8(var2, this.escapeString(this.method46(var10))));
         }

         return var4;
      } else {
         String var5 = ",";
         if ("ssv".equals(var1)) {
            var5 = this.escapeString(" ");
         } else if ("tsv".equals(var1)) {
            var5 = this.escapeString("\t");
         } else if ("pipes".equals(var1)) {
            var5 = this.escapeString("|");
         }

         StringBuilder var6 = new StringBuilder();

         for (Object var8 : var3) {
            var6.append(var5);
            var6.append(this.escapeString(this.method46(var8)));
         }

         var4.add(new MixinHelper8(var2, var6.substring(var5.length())));
         return var4;
      }
   }

   public String method49(String var1, Collection var2) {
      if ("multi".equals(var1)) {
         return this.method46(var2);
      }

      String var3 = ",";
      if ("ssv".equals(var1)) {
         var3 = " ";
      } else if ("tsv".equals(var1)) {
         var3 = "\t";
      } else if ("pipes".equals(var1)) {
         var3 = "|";
      }

      StringBuilder var4 = new StringBuilder();

      for (Object var6 : var2) {
         var4.append(var3);
         var4.append(this.method46(var6));
      }

      return var4.substring(var3.length());
   }

   public String method50(String var1) {
      return var1.replaceAll(".*[/\\\\]", "");
   }

   public boolean method51(String var1) {
      String var2 = "(?i)^(application/json|[^;/ \t]+/[^;/ \t]+[+]json)[ \t]*(;.*)?$";
      return var1 != null && (var1.matches(var2) || var1.equals("*/*"));
   }

   public String method52(String[] var1) {
      if (var1.length == 0) {
         return null;
      }

      for (String var5 : var1) {
         if (this.method51(var5)) {
            return var5;
         }
      }

      return MixinHelper2.join(var1, ",");
   }

   public String method53(String[] var1) {
      if (var1.length == 0) {
         return null;
      }

      if (var1[0].equals("*/*")) {
         return "application/json";
      }

      for (String var5 : var1) {
         if (this.method51(var5)) {
            return var5;
         }
      }

      return var1[0];
   }

   public String escapeString(String var1) {
      try {
         return URLEncoder.encode(var1, "utf8").replaceAll("\\+", "%20");
      } catch (UnsupportedEncodingException var3) {
         return var1;
      }
   }

   public <T> T method54(Response var1, Type var2) {
      if (var1 == null || var2 == null) {
         return null;
      }

      if ("byte[]".equals(var2.toString())) {
         try {
            return (T)var1.body().bytes();
         } catch (IOException var5) {
            throw new MixinHelperException(var5);
         }
      } else {
         if (var2.equals(File.class)) {
            return (T)this.method56(var1);
         }

         String var3;
         try {
            if (var1.body() != null) {
               var3 = var1.body().string();
            } else {
               var3 = null;
            }
         } catch (IOException var6) {
            throw new MixinHelperException(var6);
         }

         if (var3 != null && !"".equals(var3)) {
            String var4 = var1.headers().get("Content-Type");
            if (var4 == null) {
               var4 = "application/json";
            }

            if (this.method51(var4)) {
               return MixinHelper7.method8(var3, var2);
            } else if (var2.equals(String.class)) {
               return (T)var3;
            } else {
               throw new MixinHelperException(
                  "Content type \"" + var4 + "\" is not supported for type: " + var2, var1.code(), var1.headers().toMultimap(), var3
               );
            }
         } else {
            return null;
         }
      }
   }

   public RequestBody method55(Object var1, String var2) {
      if (var1 instanceof byte[]) {
         return RequestBody.create(MediaType.parse(var2), (byte[])var1);
      }

      if (var1 instanceof File) {
         return RequestBody.create(MediaType.parse(var2), (File)var1);
      }

      if ("text/plain".equals(var2) && var1 instanceof String) {
         return RequestBody.create(MediaType.parse(var2), (String)var1);
      }

      if (this.method51(var2)) {
         String var3;
         if (var1 != null) {
            var3 = MixinHelper7.method7(var1);
         } else {
            var3 = "";
         }

         return RequestBody.create(MediaType.parse(var2), var3);
      } else if (var1 instanceof String) {
         return RequestBody.create(MediaType.parse(var2), (String)var1);
      } else {
         throw new MixinHelperException("Content type \"" + var2 + "\" is not supported");
      }
   }

   public File method56(Response var1) {
      try {
         File var2 = this.method57(var1);
         BufferedSink var3 = Okio.buffer(Okio.sink(var2));
         var3.writeAll(var1.body().source());
         var3.close();
         return var2;
      } catch (IOException var4) {
         throw new MixinHelperException(var4);
      }
   }

   public File method57(Response var1) {
      String var2 = null;
      String var3 = var1.header("Content-Disposition");
      if (var3 != null && !"".equals(var3)) {
         Pattern var4 = Pattern.compile("filename=['\"]?([^'\"\\s]+)['\"]?");
         Matcher var5 = var4.matcher(var3);
         if (var5.find()) {
            var2 = this.method50(var5.group(1));
         }
      }

      String var7 = null;
      String var9 = null;
      if (var2 == null) {
         var7 = "download-";
         var9 = "";
      } else {
         int var6 = var2.lastIndexOf(".");
         if (var6 == -1) {
            var7 = var2 + "-";
         } else {
            var7 = var2.substring(0, var6) + "-";
            var9 = var2.substring(var6);
         }

         if (var7.length() < 3) {
            var7 = "download-";
         }
      }

      return this.field6 == null ? Files.createTempFile(var7, var9).toFile() : Files.createTempFile(Paths.get(this.field6), var7, var9).toFile();
   }

   public <T> MixinHelper4<T> method58(Call var1) {
      return this.method59(var1, null);
   }

   public <T> MixinHelper4<T> method59(Call var1, Type var2) {
      try {
         Response var3 = var1.execute();
         Object var4 = this.method62(var3, var2);
         return new MixinHelper4<>(var3.code(), var3.headers().toMultimap(), (T)var4);
      } catch (IOException var5) {
         throw new MixinHelperException(var5);
      }
   }

   public <T> void method60(Call var1, MixinHelper3<T> var2) {
      this.method61(var1, null, var2);
   }

   public <T> void method61(Call var1, final Type var2, final MixinHelper3<T> var3) {
      var1.enqueue(new Callback() {
         public void onFailure(Call var1, IOException var2x) {
            var3.method1(new MixinHelperException(var2x), 0, null);
         }

         public void onResponse(Call var1, Response var2x) {
            Object var3x;
            try {
               var3x = MixinHelper.this.method62(var2x, var2);
            } catch (MixinHelperException var5) {
               var3.method1(var5, var2x.code(), var2x.headers().toMultimap());
               return;
            } catch (Exception var6) {
               var3.method1(new MixinHelperException(var6), var2x.code(), var2x.headers().toMultimap());
               return;
            }

            var3.method2(var3x, var2x.code(), var2x.headers().toMultimap());
         }
      });
   }

   public <T> T method62(Response var1, Type var2) {
      if (var1.isSuccessful()) {
         if (var2 != null && var1.code() != 204) {
            return this.method54(var1, var2);
         }

         if (var1.body() != null) {
            try {
               var1.body().close();
            } catch (Exception var5) {
               throw new MixinHelperException(var1.message(), var5, var1.code(), var1.headers().toMultimap());
            }
         }

         return null;
      } else {
         String var3 = null;
         if (var1.body() != null) {
            try {
               var3 = var1.body().string();
            } catch (IOException var6) {
               throw new MixinHelperException(var1.message(), var6, var1.code(), var1.headers().toMultimap());
            }
         }

         throw new MixinHelperException(var1.message(), var1.code(), var1.headers().toMultimap(), var3);
      }
   }

   public Call method63(
      String var1,
      String var2,
      String var3,
      List<MixinHelper8> var4,
      List<MixinHelper8> var5,
      Object var6,
      Map<String, String> var7,
      Map<String, String> var8,
      Map<String, Object> var9,
      String[] var10,
      MixinHelper3 var11
   ) {
      Request var12 = this.method64(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
      return this.field15.newCall(var12);
   }

   public Request method64(
      String var1,
      String var2,
      String var3,
      List<MixinHelper8> var4,
      List<MixinHelper8> var5,
      Object var6,
      Map<String, String> var7,
      Map<String, String> var8,
      Map<String, Object> var9,
      String[] var10,
      MixinHelper3 var11
   ) {
      ArrayList var12 = new ArrayList(var4);
      var12.addAll(var5);
      String var13 = this.method65(var1, var2, var4, var5);
      String var15 = (String)var7.get("Content-Type");
      String var16 = var15;
      if (var16 != null && var16.contains(";")) {
         var16 = var15.substring(0, var15.indexOf(";"));
      }

      RequestBody var14;
      if (!HttpMethod.permitsRequestBody(var3)) {
         var14 = null;
      } else if ("application/x-www-form-urlencoded".equals(var16)) {
         var14 = this.method69(var9);
      } else if ("multipart/form-data".equals(var16)) {
         var14 = this.method70(var9);
      } else if (var6 == null) {
         if ("DELETE".equals(var3)) {
            var14 = null;
         } else {
            var14 = RequestBody.create(var15 == null ? null : MediaType.parse(var15), "");
         }
      } else {
         var14 = this.method55(var6, var15);
      }

      this.method68(var10, var12, var7, var8, this.method77(var14), var3, URI.create(var13));
      okhttp3.Request.Builder var17 = new okhttp3.Request.Builder().url(var13);
      this.method66(var7, var17);
      this.method67(var8, var17);
      var17.tag(var11);
      Request var18 = null;
      if (var11 != null && var14 != null) {
         RequestBodyImpl var19 = new RequestBodyImpl(var14, var11);
         var18 = var17.method(var3, var19).build();
      } else {
         var18 = var17.method(var3, var14).build();
      }

      return var18;
   }

   public String method65(String var1, String var2, List<MixinHelper8> var3, List<MixinHelper8> var4) {
      StringBuilder var5 = new StringBuilder();
      if (var1 != null) {
         var5.append(var1).append(var2);
      } else {
         String var6;
         if (this.field1 != null) {
            if (this.field1 < 0 || this.field1 >= this.servers.size()) {
               throw new ArrayIndexOutOfBoundsException(
                  String.format("Invalid index %d when selecting the host settings. Must be less than %d", this.field1, this.servers.size())
               );
            }

            var6 = this.servers.get(this.field1).method1(this.field2);
         } else {
            var6 = this.basePath;
         }

         var5.append(var6).append(var2);
      }

      if (var3 != null && !var3.isEmpty()) {
         String var10 = var2.contains("?") ? "&" : "?";

         for (MixinHelper8 var8 : var3) {
            if (var8.getValue() != null) {
               if (var10 != null) {
                  var5.append(var10);
                  var10 = null;
               } else {
                  var5.append("&");
               }

               String var9 = this.method46(var8.getValue());
               var5.append(this.escapeString(var8.getName())).append("=").append(this.escapeString(var9));
            }
         }
      }

      if (var4 != null && !var4.isEmpty()) {
         String var11 = var5.toString().contains("?") ? "&" : "?";

         for (MixinHelper8 var13 : var4) {
            if (var13.getValue() != null) {
               if (var11 != null) {
                  var5.append(var11);
                  var11 = null;
               } else {
                  var5.append("&");
               }

               String var14 = this.method46(var13.getValue());
               var5.append(this.escapeString(var13.getName())).append("=").append(var14);
            }
         }
      }

      return var5.toString();
   }

   public void method66(Map<String, String> var1, okhttp3.Request.Builder var2) {
      for (Entry var4 : var1.entrySet()) {
         var2.header((String)var4.getKey(), this.method46(var4.getValue()));
      }

      for (Entry var6 : this.field4.entrySet()) {
         if (!var1.containsKey(var6.getKey())) {
            var2.header((String)var6.getKey(), this.method46(var6.getValue()));
         }
      }
   }

   public void method67(Map<String, String> var1, okhttp3.Request.Builder var2) {
      for (Entry var4 : var1.entrySet()) {
         var2.addHeader("Cookie", String.format("%s=%s", var4.getKey(), var4.getValue()));
      }

      for (Entry var6 : this.field5.entrySet()) {
         if (!var1.containsKey(var6.getKey())) {
            var2.addHeader("Cookie", String.format("%s=%s", var6.getKey(), var6.getValue()));
         }
      }
   }

   public void method68(String[] var1, List<MixinHelper8> var2, Map<String, String> var3, Map<String, String> var4, String var5, String var6, URI var7) {
      for (String var11 : var1) {
         MixinHelper10 var12 = this.field7.get(var11);
         if (var12 == null) {
            throw new RuntimeException("Authentication undefined: " + var11);
         }

         var12.method1(var2, var3, var4, var5, var6, var7);
      }
   }

   public RequestBody method69(Map<String, Object> var1) {
      okhttp3.FormBody.Builder var2 = new okhttp3.FormBody.Builder();

      for (Entry var4 : var1.entrySet()) {
         var2.add((String)var4.getKey(), this.method46(var4.getValue()));
      }

      return var2.build();
   }

   public RequestBody method70(Map<String, Object> var1) {
      okhttp3.MultipartBody.Builder var2 = new okhttp3.MultipartBody.Builder().setType(MultipartBody.FORM);

      for (Entry var4 : var1.entrySet()) {
         if (var4.getValue() instanceof File) {
            File var8 = (File)var4.getValue();
            this.method72(var2, (String)var4.getKey(), var8);
         } else if (var4.getValue() instanceof List) {
            for (Object var7 : (List)var4.getValue()) {
               if (var7 instanceof File) {
                  this.method72(var2, (String)var4.getKey(), (File)var7);
               } else {
                  this.method73(var2, (String)var4.getKey(), var4.getValue());
               }
            }
         } else {
            this.method73(var2, (String)var4.getKey(), var4.getValue());
         }
      }

      return var2.build();
   }

   public String method71(File var1) {
      String var2 = URLConnection.guessContentTypeFromName(var1.getName());
      return var2 == null ? "application/octet-stream" : var2;
   }

   private void method72(okhttp3.MultipartBody.Builder var1, String var2, File var3) {
      Headers var4 = Headers.of(new String[]{"Content-Disposition", "form-data; name=\"" + var2 + "\"; filename=\"" + var3.getName() + "\""});
      MediaType var5 = MediaType.parse(this.method71(var3));
      var1.addPart(var4, RequestBody.create(var5, var3));
   }

   private void method73(okhttp3.MultipartBody.Builder var1, String var2, Object var3) {
      RequestBody var4;
      if (var3 instanceof String) {
         var4 = RequestBody.create(MediaType.parse("text/plain"), (String)var3);
      } else {
         String var5;
         if (var3 != null) {
            var5 = MixinHelper7.method7(var3);
         } else {
            var5 = null;
         }

         var4 = RequestBody.create(MediaType.parse("application/json"), var5);
      }

      Headers var6 = Headers.of(new String[]{"Content-Disposition", "form-data; name=\"" + var2 + "\""});
      var1.addPart(var6, var4);
   }

   private Interceptor method74() {
      return new Interceptor() {
         public Response intercept(Chain var1) {
            Request var2 = var1.request();
            Response var3 = var1.proceed(var2);
            if (var2.tag() instanceof MixinHelper3) {
               MixinHelper3 var4 = (MixinHelper3)var2.tag();
               return var3.newBuilder().body(new ResponseBodyImpl(var3.body(), var4)).build();
            } else {
               return var3;
            }
         }
      };
   }

   private void method75() {
      try {
         TrustManager[] var1;
         Object var2;
         if (!this.field13) {
            var1 = new TrustManager[]{new X509TrustManager() {
               @Override
               public void checkClientTrusted(X509Certificate[] var1, String var2x) {
               }

               @Override
               public void checkServerTrusted(X509Certificate[] var1, String var2x) {
               }

               @Override
               public X509Certificate[] getAcceptedIssuers() {
                  return new X509Certificate[0];
               }
            }};
            var2 = new HostnameVerifier() {
               @Override
               public boolean verify(String var1, SSLSession var2x) {
                  return true;
               }
            };
         } else {
            TrustManagerFactory var3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            if (this.field12 == null) {
               var3.init((KeyStore)null);
            } else {
               Object var4 = null;
               CertificateFactory var5 = CertificateFactory.getInstance("X.509");
               Collection var6 = var5.generateCertificates(this.field12);
               if (var6.isEmpty()) {
                  throw new IllegalArgumentException("expected non-empty set of trusted certificates");
               }

               KeyStore var7 = this.method76((char[])var4);
               int var8 = 0;

               for (Certificate var10 : var6) {
                  String var11 = "ca" + var8++;
                  var7.setCertificateEntry(var11, var10);
               }

               var3.init(var7);
            }

            var1 = var3.getTrustManagers();
            var2 = OkHostnameVerifier.INSTANCE;
         }

         SSLContext var13 = SSLContext.getInstance("TLS");
         var13.init(this.field14, var1, new SecureRandom());
         this.field15 = this.field15
            .newBuilder()
            .sslSocketFactory(var13.getSocketFactory(), (X509TrustManager)var1[0])
            .hostnameVerifier((HostnameVerifier)var2)
            .build();
      } catch (GeneralSecurityException var12) {
         throw new RuntimeException(var12);
      }
   }

   private KeyStore method76(char[] var1) {
      try {
         KeyStore var2 = KeyStore.getInstance(KeyStore.getDefaultType());
         var2.load(null, var1);
         return var2;
      } catch (IOException var3) {
         throw new AssertionError(var3);
      }
   }

   private String method77(RequestBody var1) {
      if (var1 != null) {
         try {
            Buffer var2 = new Buffer();
            var1.writeTo(var2);
            return var2.readUtf8();
         } catch (IOException var3) {
            throw new MixinHelperException(var3);
         }
      } else {
         return "";
      }
   }
}
