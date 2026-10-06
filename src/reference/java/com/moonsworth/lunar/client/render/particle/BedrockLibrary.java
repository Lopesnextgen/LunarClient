package com.moonsworth.lunar.client.render.particle;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class BedrockLibrary {
   public static long lastUpdate;
   public Map<String, BedrockScheme> field1 = new HashMap<>();
   public Map<String, BedrockScheme> field2 = new HashMap<>();
   public File field3;

   public BedrockLibrary(File file1) {
      this.field3 = file1;
      this.field3.mkdirs();
      this.method6("default_fire");
      this.method6("default_magic");
      this.method6("default_rain");
      this.method6("default_snow");
   }

   public File method1(String text1) {
      return new File(this.field3, text1 + ".json");
   }

   public boolean method2(String text1) {
      return this.method1(text1).isFile();
   }

   public void reload() {
      this.field1.clear();
      this.field1.putAll(this.field2);

      for (File file4 : this.field3.listFiles()) {
         if (file4.isFile() && file4.getName().endsWith(".json")) {
            this.method4(file4);
         }
      }
   }

   public BedrockScheme method3(String text1) {
      BedrockScheme glintcolorizer3_22 = this.method5(this.method1(text1));
      return glintcolorizer3_22 != null ? glintcolorizer3_22 : this.method7(text1);
   }

   private void method4(File file1) {
      BedrockScheme glintcolorizer3_22 = this.method5(file1);
      if (glintcolorizer3_22 != null) {
         String text3 = file1.getName();
         this.field1.put(text3.substring(0, text3.indexOf(".json")), glintcolorizer3_22);
      }
   }

   public BedrockScheme method5(File file1) {
      if (!file1.exists()) {
         return null;
      }

      try {
         return BedrockScheme.method3(FileUtils.readFileToString(file1, Charset.defaultCharset()));
      } catch (Exception exception3) {
         exception3.printStackTrace();
         return null;
      }
   }

   private void method6(String text1) {
      BedrockScheme glintcolorizer3_22 = this.method7(text1);
      if (glintcolorizer3_22 != null) {
         this.field2.put(text1, glintcolorizer3_22);
      }
   }

   public BedrockScheme method7(String text1) {
      try {
         return BedrockScheme.method3(
               IOUtils.toString(
                  this.getClass().getClassLoader().getResourceAsStream("assets/blockbuster/particles/" + text1 + ".json"), Charset.defaultCharset()
               )
            )
            .method7(true);
      } catch (Exception exception3) {
         exception3.printStackTrace();
         return null;
      }
   }

   public void method8(String text1, BedrockScheme glintcolorizer3_22) {
      String text3 = JsonPrettyPrinter.jsonToPretty(BedrockScheme.method5(glintcolorizer3_22));
      File file4 = this.method1(text1);

      try {
         FileUtils.writeStringToFile(file4, text3, Charset.defaultCharset());
      } catch (Exception exception6) {
      }

      this.method4(file4);
      lastUpdate = System.currentTimeMillis();
   }
}
