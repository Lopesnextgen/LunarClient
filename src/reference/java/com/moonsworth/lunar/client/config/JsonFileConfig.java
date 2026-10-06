package com.moonsworth.lunar.client.config;

import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import io.sentry.Attachment;
import io.sentry.Hint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;

public interface JsonFileConfig extends JsonConfigurable {
   String method5();

   default File method6() {
      return new File(LunarConstants.field25, this.method5());
   }

   default boolean method4() {
      return false;
   }

   default boolean method7() {
      return false;
   }

   default boolean method8() {
      File file1 = this.method6();
      if (!file1.exists()) {
         return false;
      }

      try (BufferedReader reader2 = Files.newReader(file1, Charset.defaultCharset())) {
         JsonElement element3 = (JsonElement)LunarConstants.field22.fromJson(reader2, JsonElement.class);
         if (element3 != null && element3.isJsonObject()) {
            this.load(element3.getAsJsonObject());
            LunarLogger.method1("Loaded File: [%s]", new Object[]{this.method5()});
         }

         return true;
      } catch (Exception exception7) {
         LunarLogger.method7("Couldn't load file %s [%s]", new Object[]{this.method5(), exception7.getMessage()});
         this.method8(exception7, file1);
         return false;
      }
   }

   default void method9() {
      this.method7(false);
   }

   default void method7(boolean flag) {
      if (Ref.method4() == null
         || Ref.method4().method40() == null
         || !Ref.method4().method40().method85().method19()
         || this == Ref.method4().method90()) {
         File file2 = this.method6();
         boolean flag3 = file2.exists();
         JsonObject json4 = new JsonObject();

         try {
            if (!flag && this.method7() && flag3) {
               try (BufferedReader reader5 = Files.newReader(file2, Charset.defaultCharset())) {
                  JsonElement element6 = (JsonElement)LunarConstants.field22.fromJson(reader5, JsonElement.class);
                  if (element6 != null && element6.isJsonObject()) {
                     json4 = element6.getAsJsonObject();
                  }
               }
            }
         } catch (Exception exception13) {
            LunarLogger.method7("Couldn't load file %s [%s]", new Object[]{this.method5(), exception13.getMessage()});
            this.method8(exception13, file2);
         }

         try {
            if (!flag3 && !file2.createNewFile()) {
               return;
            }

            this.method1(json4);

            try (FileWriter filewriter14 = new FileWriter(file2)) {
               if (this.method4()) {
                  LunarConstants.field23.toJson(json4, filewriter14);
               } else {
                  LunarConstants.field22.toJson(json4, filewriter14);
               }

               LunarLogger.method1("Saved File: [%s]", new Object[]{this.method5()});
            }
         } catch (Exception exception10) {
            LunarLogger.method7("Couldn't save to " + this.method5() + " [%s]", new Object[]{exception10.getMessage()});
            this.method8(exception10, file2);
         }
      }
   }

   default void method8(Exception exception1, File file2) {
      CrashReporter.method6(exception1, "Savable", !LunarBuildData.field4, file2 == null ? null : Hint.withAttachment(new Attachment(file2.getAbsolutePath())));
   }
}
