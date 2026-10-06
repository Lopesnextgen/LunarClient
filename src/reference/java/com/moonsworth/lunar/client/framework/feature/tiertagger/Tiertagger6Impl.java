package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Tiertagger6Impl extends Tiertagger6 {
   private static Tiertagger6Impl field4;
   private final List<Tiertagger_2> field5 = new ArrayList<>();
   private final List<Tiertagger_2> field6 = new ArrayList<>();

   private Tiertagger6Impl() {
      super("MCTiers");
   }

   @Override
   protected boolean method1(JsonObject var1) {
      if (!var1.has("mctiers")) {
         Slayer.method5("No MCTiers entry in tier tagger data!", new Object[0]);
         return false;
      } else {
         var1 = var1.getAsJsonObject("mctiers");
         this.method2(var1);
         this.method2(var1);
         Slayer.method3("[MCTiers] Loaded %s (.com) and %s (.io) fallback modes", new Object[]{this.field5.size(), this.field6.size()});
         return true;
      }
   }

   private void method2(JsonObject var1) {
      if (var1.has("fallbackGameModes_com")) {
         JsonObject var2 = var1.getAsJsonObject("fallbackGameModes_com");
         this.field5.addAll(this.method5(var2));
      } else {
         Slayer.method5("No fallback game modes for mctiers.com", new Object[0]);
      }

      if (var1.has("fallbackGameModes_io")) {
         JsonObject var3 = var1.getAsJsonObject("fallbackGameModes_io");
         this.field6.addAll(this.method5(var3));
      } else {
         Slayer.method5("No fallback game modes for mctiers.io", new Object[0]);
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

   @Generated
   public List<Tiertagger_2> method5() {
      return this.field6;
   }
}
