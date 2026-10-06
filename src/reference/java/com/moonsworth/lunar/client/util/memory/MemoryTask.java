package com.moonsworth.lunar.client.util.memory;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.framework.LunarConstants;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import lombok.Generated;
import org.apache.commons.io.FileUtils;

public class MemoryTask implements Runnable {
   private final Memory field1;

   @Override
   public void run() {
      Path path1 = LunarConstants.field15;
      String text2 = this.field1.method10().toString() + ".png";
      String text3 = ServiceEndpoints.method3() + "/face/" + this.field1.method10();
      File file4 = path1.resolve(text2).toFile();
      LunarLogger.method2("Avatar", "Downloading http texture from %s to %s", new Object[]{text3, file4.getName()});
      HttpURLConnection httpurlconnection5 = null;

      try {
         httpurlconnection5 = (HttpURLConnection)new URL(text3).openConnection();
         httpurlconnection5.setDoInput(true);
         httpurlconnection5.setDoOutput(false);
         httpurlconnection5.connect();
         if (httpurlconnection5.getResponseCode() == 200) {
            LunarLogger.method2("Avatar", "Updated user avatar successfully", new Object[0]);
            FileUtils.copyInputStreamToFile(httpurlconnection5.getInputStream(), file4);
            this.field1.method52(file4.getName());
         }
      } catch (Exception exception10) {
         LunarLogger.method8("Avatar", "Couldn't download http texture", new Object[]{exception10});
      } finally {
         if (httpurlconnection5 != null) {
            httpurlconnection5.disconnect();
         }
      }

      Client.method109().method50().method9();
   }

   @Generated
   public MemoryTask(Memory memory1) {
      this.field1 = memory1;
   }
}
