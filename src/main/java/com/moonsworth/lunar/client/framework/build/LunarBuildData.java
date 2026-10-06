package com.moonsworth.lunar.client.framework.build;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import lombok.Generated;

public final class LunarBuildData {
   public static final String field1;
   public static final String field2;
   public static final String field3;
   public static final boolean field4;
   public static final String field5;
   public static final String field6;
   public static final String field7;
   public static final String field8;

   public LunarBuildData() {
   }

   static {
      LunarBuildData.BuildDataKey[] items0 = LunarBuildData.BuildDataKey.values();
      HashMap map1 = new HashMap();
      InputStream input2 = LunarBuildData.class.getClassLoader().getResourceAsStream("lunarBuildData.txt");
      if (input2 != null) {
         try (input2) {
            String[] items4 = new String(input2.readAllBytes()).split("\n");

            for (String text8 : items4) {
               text8 = text8.trim();
               int index9 = text8.indexOf(61);
               if (index9 != -1) {
                  String text10 = text8.substring(0, index9);
                  String text11 = text8.substring(index9 + 1);

                  for (LunarBuildData.BuildDataKey type215 : items0) {
                     if (type215.getId().equals(text10)) {
                        map1.put(type215, text11);
                        break;
                     }
                  }
               }
            }
         } catch (IOException exception18) {
            throw new RuntimeException("Couldn't find lunarBuildData.txt??", exception18);
         }

         for (LunarBuildData.BuildDataKey type222 : items0) {
            if (!map1.containsKey(type222)) {
               throw new RuntimeException("\"" + type222.getId() + "\" couldn't be found in lunarBuildData.txt");
            }
         }

         field1 = (String)map1.get(LunarBuildData.BuildDataKey.BRANCH);
         field2 = (String)map1.get(LunarBuildData.BuildDataKey.GIT_HASH);
         field3 = (String)map1.get(LunarBuildData.BuildDataKey.FULL_GIT_HASH);
         field4 = Boolean.parseBoolean((String)map1.get(LunarBuildData.BuildDataKey.PRODUCTION));
         field5 = (String)map1.get(LunarBuildData.BuildDataKey.PROGUARD_UUID);
         field6 = (String)map1.get(LunarBuildData.BuildDataKey.LUNAR_VERSION);
         field7 = (String)map1.get(LunarBuildData.BuildDataKey.UI_BRANCH);
         field8 = (String)map1.get(LunarBuildData.BuildDataKey.UI_GIT_HASH);
      } else {
         throw new RuntimeException("Couldn't find stream for lunarBuildData.txt??");
      }
   }

   private enum BuildDataKey {
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
      BuildDataKey(String text) {
         this.id = text;
      }
   }
}
