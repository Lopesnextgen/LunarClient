package com.moonsworth.lunar.files;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

public class MappingDownloader {
   public static final int field1 = 7;
   public static final Duration field2 = Duration.ofMillis(180000L);
   public static final Duration field3 = field2;
   public static final HttpClient field4 = HttpClient.newBuilder()
      .connectTimeout(field2)
      .executor(Executors.newSingleThreadExecutor())
      .followRedirects(Redirect.NORMAL)
      .build();

   public MappingDownloader() {
   }

   public static boolean method1(Path path0) {
      try {
         if (Files.notExists(path0)) {
            return true;
         }

         String text1 = path0.toString();
         if (!text1.endsWith(".zip") && !text1.endsWith(".jar")) {
            return true;
         }

         try {
            new ZipFile(path0.toFile()).close();
            return false;
         } catch (Exception exception3) {
            Files.delete(path0);
            return true;
         }
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   @Nullable
   public static String method2(String text0) {
      try {
         byte[] items1 = method3(text0);
         return items1 == null ? null : new String(items1, StandardCharsets.UTF_8);
      } catch (Throwable exception2) {
         throw exception2;
      }
   }

   public static byte[] method3(String text0) {
      try {
         return method4(new URL(text0), 0, null);
      } catch (MalformedURLException malformedurlexception2) {
         throw new MappingException("Bad URL", malformedurlexception2);
      }
   }

   public static byte[] method4(URL url0, int number1, @Nullable Exception exception2) {
      if (number1 >= 7) {
         throw new MappingException("Maxed out retries in downloading " + url0, exception2);
      }

      try {
         HttpRequest httprequest3 = HttpRequest.newBuilder(url0.toURI()).timeout(field3).build();
         HttpResponse httpresponse4 = field4.send(httprequest3, BodyHandlers.ofByteArray());
         int number5 = httpresponse4.statusCode();
         byte[] items6 = (byte[])httpresponse4.body();
         if (number5 >= 200 && number5 < 300 && items6 != null && items6.length != 0) {
            return items6;
         } else {
            MappingException filesexception7 = new MappingException("Failed to download " + url0 + ": " + number5 + " body: " + (items6 == null ? "null" : new String(items6)), exception2);
            if (number5 != 404 && number1 + 1 < 7) {
               return method4(url0, number1 + 1, filesexception7);
            } else {
               throw filesexception7;
            }
         }
      } catch (IOException exception8) {
         return method4(url0, number1 + 1, exception8);
      } catch (URISyntaxException | InterruptedException urisyntaxexception9) {
         throw new MappingException("Failed request to " + url0, urisyntaxexception9);
      }
   }

   public static void method5(List<String> list0, String text1, Predicate<byte[]> predicate2) {
      int index3 = 0;
      ArrayList list4 = new ArrayList();

      for (String text6 : list0) {
         try {
            list4.add(HttpRequest.newBuilder(new URI(text6 + text1)).timeout(field3).build());
         } catch (URISyntaxException urisyntaxexception13) {
            throw new RuntimeException(urisyntaxexception13);
         }
      }

      BodyHandler bodyhandler15 = BodyHandlers.ofByteArray();
      ArrayList list16 = new ArrayList();

      do {
         for (HttpRequest httprequest8 : list4) {
            int index9 = index3;

            do {
               try {
                  HttpResponse httpresponse10 = field4.send(httprequest8, bodyhandler15);
                  int index11 = httpresponse10.statusCode();
                  if (index11 == 404) {
                     list16.add(httprequest8.uri() + " - Skipping, HTTP 404");
                     break;
                  }

                  byte[] items12 = (byte[])httpresponse10.body();
                  if (index11 >= 200 && index11 < 300 && items12 != null && items12.length != 0) {
                     if (predicate2.test(items12)) {
                        return;
                     }

                     list16.add(httprequest8.uri() + " - Byte predicate failed");
                  } else {
                     list16.add(httprequest8.uri() + " - HTTP " + index11);
                  }
               } catch (IOException | InterruptedException exception14) {
                  list16.add(httprequest8.uri() + " - Exception: " + exception14);
               }
            } while (index9++ <= 7);
         }
      } while (index3++ <= 7);

      throw new IllegalStateException("Unable to download path: " + text1 + ". Attempt failure reasons: " + list16);
   }

   public static long copy(InputStream input0, OutputStream output1) {
      byte[] items2 = new byte[4096];
      long number3 = 0L;

      while (true) {
         int number5 = input0.read(items2);
         if (number5 == -1) {
            return number3;
         }

         output1.write(items2, 0, number5);
         number3 += number5;
      }
   }

   public static byte[] toByteArray(InputStream input0) {
      ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
      copy(input0, bytearrayoutputstream1);
      input0.close();
      return bytearrayoutputstream1.toByteArray();
   }
}
