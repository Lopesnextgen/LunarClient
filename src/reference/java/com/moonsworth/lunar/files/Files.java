package com.moonsworth.lunar.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Files {
   public static final Gson field1 = new GsonBuilder().create();

   public static final class Data2 {
      public static final Files3 field1 = new Files3(
         "mojang", "version_manifest.json", "https://launchermeta.mojang.com/mc/game/version_manifest.json", true, false
      );
      public static final FilesImpl field2 = FilesImpl.method2("mx", field1);
      public static final String field3 = "5";
      public static final Files3 field4 = new Files3("lunar", "lunar_named_b5_${mcVer}.kin", null, false, false);
      public static final FilesImpl field5 = FilesImpl.method2("mx", field4);
      public static final Files3 field6 = new Files3("lunar", "optifine_b3_${mcVer}.csrg", null, false, true);
      public static final FilesImpl field7 = FilesImpl.method2("mx", field6);
      public static final Files3 field8 = new Files3("forge", "mcp_searge_${mcVer}.kin", null, false, true);
      public static final FilesImpl field9 = FilesImpl.method2("mx", field8);
      public static final Files3 field10 = new Files3("fabric", "intermediary_${mcVer}.kin", null, false, true);
      public static final FilesImpl field11 = FilesImpl.method2("mx", field10);
      public static final Files3 field12 = new Files3("lunar", "intermediary2lunar_${mcVer}.kin", null, false, true);
      public static final FilesImpl field13 = FilesImpl.method2("mx", field12);
      public static final Files3 field14 = new Files3("lunar", "lunar2intermediary_${mcVer}.kin", null, false, true);
      public static final FilesImpl field15 = FilesImpl.method2("mx", field14);
      public static final Files3 field16 = new Files3("lunar", "searge2lunar_${mcVer}.kin", null, false, true);
      public static final FilesImpl field17 = FilesImpl.method2("mx", field16);
      public static final Files3 field18 = new Files3("parchment", "parchment_${mcVer}.json", null, false, true);
      public static final FilesImpl field19 = FilesImpl.method2("mx", field18);
   }
}
