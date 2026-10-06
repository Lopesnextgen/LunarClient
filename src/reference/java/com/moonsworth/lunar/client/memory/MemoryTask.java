package com.moonsworth.lunar.client.memory;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
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
      Path var1 = ThreadModuleDump48.field15;
      String var2 = this.field1.method10().toString() + ".png";
      String var3 = ServiceEndpoints.method3() + "/face/" + this.field1.method10();
      File var4 = var1.resolve(var2).toFile();
      Slayer.method2("Avatar", "Downloading http texture from %s to %s", var3, var4.getName());
      HttpURLConnection var5 = null;

      try {
         var5 = (HttpURLConnection)new URL(var3).openConnection();
         var5.setDoInput(true);
         var5.setDoOutput(false);
         var5.connect();
         if (var5.getResponseCode() == 200) {
            Slayer.method2("Avatar", "Updated user avatar successfully");
            FileUtils.copyInputStreamToFile(var5.getInputStream(), var4);
            this.field1.method52(var4.getName());
         }
      } catch (Exception var10) {
         Slayer.method8("Avatar", "Couldn't download http texture", var10);
      } finally {
         if (var5 != null) {
            var5.disconnect();
         }
      }

      Client.method109().method50().method9();
   }

   @Generated
   public MemoryTask(Memory var1) {
      this.field1 = var1;
   }
}
