package com.moonsworth.lunar.genesis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import lombok.Generated;

public final class Genesis3 {
   public static final String field1;
   public static final String field2;
   public static final String field3;
   public static final boolean field4;
   public static final String field5;
   public static final String field6;
   public static final String field7;
   public static final String field8;

   static {
      Genesis3.Type[] var0 = Genesis3.Type.values();
      HashMap var1 = new HashMap();
      InputStream var2 = Genesis.class.getClassLoader().getResourceAsStream("lunarBuildData.txt");
      if (var2 != null) {
         try (var2) {
            String[] var4 = new String(var2.readAllBytes()).split("\n");

            for (String var8 : var4) {
               var8 = var8.trim();
               int var9 = var8.indexOf(61);
               if (var9 != -1) {
                  String var10 = var8.substring(0, var9);
                  String var11 = var8.substring(var9 + 1);

                  for (Genesis3.Type var15 : var0) {
                     if (var15.getId().equals(var10)) {
                        var1.put(var15, var11);
                        break;
                     }
                  }
               }
            }
         } catch (IOException var18) {
            throw new RuntimeException("Couldn't find lunarBuildData.txt??", var18);
         }

         for (Genesis3.Type var22 : var0) {
            if (!var1.containsKey(var22)) {
               throw new RuntimeException("\"" + var22.getId() + "\" couldn't be found in lunarBuildData.txt");
            }
         }

         field1 = (String)var1.get(Genesis3.Type.BRANCH);
         field2 = (String)var1.get(Genesis3.Type.GIT_HASH);
         field3 = (String)var1.get(Genesis3.Type.FULL_GIT_HASH);
         field4 = Boolean.parseBoolean((String)var1.get(Genesis3.Type.PRODUCTION));
         field5 = (String)var1.get(Genesis3.Type.PROGUARD_UUID);
         field6 = (String)var1.get(Genesis3.Type.LUNAR_VERSION);
         field7 = (String)var1.get(Genesis3.Type.UI_BRANCH);
         field8 = (String)var1.get(Genesis3.Type.UI_GIT_HASH);
      } else {
         Genesis.LOGGER.warn("Couldn't find stream for lunarBuildData.txt??");
         field1 = "unknown";
         field2 = "unknown";
         field3 = "unknown";
         field4 = false;
         field5 = "unknown";
         field6 = "unknown";
         field7 = "unknown";
         field8 = "unknown";
      }
   }

   private enum Type {
      BRANCH("gitBranch"),
      GIT_HASH("gitHash"),
      FULL_GIT_HASH("fullGitHash"),
      PRODUCTION("production"),
      PROGUARD_UUID("proguardUuid"),
      LUNAR_VERSION("lunarVersion"),
      UI_BRANCH("uiBranch"),
      UI_GIT_HASH("uiGitHash");

      private final String id;

      @Generated
      public String getId() {
         return this.id;
      }

      @Generated
      Type(String var3) {
         this.id = var3;
      }
   }
}
