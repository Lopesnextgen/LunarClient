package com.moonsworth.lunar.client.ui.blog;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.LunarConstants;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import lombok.Generated;
import org.apache.commons.io.FileUtils;

public class BlogPostDownloadTask implements Runnable {
   private final List<BlogPost> field1;

   @Override
   public void run() {
      Path path1 = LunarConstants.field14;

      for (BlogPost gui2handler3 : this.field1) {
         File file4 = path1.resolve(gui2handler3.getHash() + "." + BlogPost.field1.subtype()).toFile();
         LunarLogger.method2("BlogPost", "Downloading http texture from %s to %s", new Object[]{gui2handler3.getImageUrl(), file4.getName()});
         HttpURLConnection httpurlconnection5 = null;

         try {
            httpurlconnection5 = (HttpURLConnection)new URL(gui2handler3.getImageUrl()).openConnection();
            httpurlconnection5.setDoInput(true);
            httpurlconnection5.setDoOutput(false);
            httpurlconnection5.connect();
            if (httpurlconnection5.getResponseCode() == 200) {
               FileUtils.copyInputStreamToFile(httpurlconnection5.getInputStream(), file4);
               gui2handler3.method3(file4.getName());
            }
         } catch (Exception exception10) {
            LunarLogger.method8("BlogPost", "Couldn't download http texture", new Object[]{exception10});
         } finally {
            if (httpurlconnection5 != null) {
               httpurlconnection5.disconnect();
            }
         }
      }

      Client.method109().method65().method6();
   }

   @Generated
   public BlogPostDownloadTask(List<BlogPost> list) {
      this.field1 = list;
   }
}
