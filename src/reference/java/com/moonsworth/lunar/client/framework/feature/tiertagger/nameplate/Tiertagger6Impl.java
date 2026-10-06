package com.moonsworth.lunar.client.framework.feature.tiertagger.nameplate;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger6;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger_2;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Tiertagger6Impl extends Tiertagger6 {
   private static Tiertagger6Impl field4;
   private final List<Tiertagger_2> field5 = new ArrayList<>();

   private Tiertagger6Impl() {
      super("SubTiers");
   }

   @Override
   protected boolean method1(JsonObject var1) {
      if (!var1.has("subtiers")) {
         Slayer.method5("No SubTiers entry in tier tagger data!", new Object[0]);
         return false;
      } else {
         var1 = var1.getAsJsonObject("subtiers");
         this.method2(var1);
         this.method2(var1);
         Slayer.method3("[SubTiers] Loaded %s fallback modes", new Object[]{this.field5.size()});
         return true;
      }
   }

   private void method2(JsonObject var1) {
      if (var1.has("fallbackGameModes")) {
         JsonObject var2 = var1.getAsJsonObject("fallbackGameModes");
         this.field5.addAll(this.method5(var2));
      } else {
         Slayer.method5("No fallback game modes for subtiers.net", new Object[0]);
      }
   }

   public static Tiertagger6Impl method3() {
      if (field4 == null) {
         field4 = new Tiertagger6Impl();
      }

      return field4;
   }

   @Generated
   public List<Tiertagger_2> method4() {
      return this.field5;
   }
}
