package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Tiertagger6Impl_2 extends Tiertagger6 {
   private static Tiertagger6Impl_2 field4;
   private final List<Tiertagger_2> field5 = new ArrayList<>();

   private Tiertagger6Impl_2() {
      super("PvpHQ");
   }

   @Override
   protected boolean method1(JsonObject var1) {
      if (!var1.has("pvphq")) {
         Slayer.method5("No PvpHQ entry in tier tagger data!", new Object[0]);
         return false;
      } else {
         var1 = var1.getAsJsonObject("pvphq");
         this.method2(var1);
         this.method2(var1);
         Slayer.method3("[PvpHQ] Loaded %s ladders", new Object[]{this.field5.size()});
         return true;
      }
   }

   private void method2(JsonObject var1) {
      if (!var1.has("gameModes")) {
         Slayer.method5("No game modes for PvpHQ", new Object[0]);
      } else {
         this.field5.addAll(this.method5(var1.getAsJsonObject("gameModes")));
      }
   }

   public static Tiertagger6Impl_2 method3() {
      if (field4 == null) {
         field4 = new Tiertagger6Impl_2();
      }

      return field4;
   }

   @Generated
   public List<Tiertagger_2> method4() {
      return this.field5;
   }
}
