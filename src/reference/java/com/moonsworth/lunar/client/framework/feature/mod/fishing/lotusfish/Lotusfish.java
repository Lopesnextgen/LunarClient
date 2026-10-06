package com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish;

import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.LunarConstants;
import io.leangen.geantyref.TypeToken;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Lotusfish {
   private static final Set<com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish> field1 = method3();
   private static final HashMap<String, com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish> field2 = new HashMap<>();

   public Lotusfish() {
   }

   public static com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish method1(String text0) {
      for (com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish lotusfish2 : field1) {
         if (lotusfish2.id().equals(text0)) {
            return lotusfish2;
         }
      }

      return null;
   }

   public static com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish method2(String text0) {
      return field2.get(text0);
   }

   public static Set<String> ids() {
      HashSet set0 = new HashSet();

      for (com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish lotusfish2 : field1) {
         set0.add(lotusfish2.id());
      }

      return set0;
   }

   private static Set<com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish> method3() {
      String text0 = "tab-widgets.json";
      Path path1 = LunarConstants.field13.resolve(text0);
      if (!path1.toFile().exists()) {
         return Set.of();
      }

      try {
         Type type2 = (new TypeToken<Set<com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish>>() {}).getType();
         String text3 = Files.readString(path1);
         return (Set<com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish>)LunarConstants.field22.fromJson(text3, type2);
      } catch (Exception exception4) {
         CrashReporter.method5(exception4, "Loading " + text0);
         return Set.of();
      }
   }

   static {
      for (com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish lotusfish1 : field1) {
         field2.put(lotusfish1.title(), lotusfish1);
      }
   }
}
