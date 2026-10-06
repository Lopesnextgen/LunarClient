package com.moonsworth.lunar.client.framework.feature.tiertagger.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger2_2;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger_2;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class LightingExtension49522
   extends com.moonsworth.lunar.client.config.option.CyclingDropdownOption<com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension> {
   private final TierTagger field9;
   private final Map<com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension, String> field10 = new HashMap<>();
   private final Map<com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension, String> field11 = new HashMap<>();
   private boolean field12 = false;

   protected LightingExtension49522(
      String var1,
      @Nullable Codec<Gui2Extension> var2,
      com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension var3,
      List<com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension> var4,
      @Nullable Function<Gui2Extension, String> var5,
      TierTagger var6
   ) {
      super(var1, var2, var3, var4, var5);
      this.field9 = var6;
   }

   public void method1(com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension var1, boolean var2) {
      if (!this.field12) {
         super.method11(var1, var2);
      } else {
         this.field10.put(this.method9(), ((Tiertagger_2)this.field9.field13.get()).apiName());
         this.field11.put(this.method9(), ((Tiertagger_2)this.field9.field14.get()).apiName());
         super.method11(var1, var2);
         this.field9.field13.setOptions(var1.getTierProvider().method3());
         this.field9.field14.setOptions(var1.getTierProvider().method3());
         String var3 = this.field10.get(var1);
         if (var3 != null) {
            var1.getTierProvider().method9(var3).ifPresent(this.field9.field13::method10);
         }

         String var4 = this.field11.get(var1);
         if (var4 != null) {
            var1.getTierProvider().method9(var4).ifPresent(this.field9.field14::method10);
         }

         for (com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension var6 : com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.VALUES) {
            var6.getTierProvider().method6();
         }

         this.field9.clearCaches();
      }
   }

   public Tiertagger2_2 getTierProvider() {
      return this.method9().getTierProvider();
   }

   public com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension method9() {
      return ThreadModuleDump63.MC_VERSION == 1
         ? com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.TIERTESTS
         : (com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension)super.get();
   }

   public void method1(JsonObject var1) {
      super.load(var1);
      this.method4(var1, "modeBySource", this.field10);
      this.method4(var1, "secondaryModeBySource", this.field11);
   }

   public void load(JsonObject var1) {
      super.load(var1);
      this.method5(var1, "modeBySource", this.field10);
      this.method5(var1, "secondaryModeBySource", this.field11);
   }

   private void method4(JsonObject var1, String var2, Map<com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension, String> var3) {
      if (!var3.isEmpty()) {
         JsonObject var4 = new JsonObject();

         for (Entry var6 : var3.entrySet()) {
            var4.addProperty(((com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension)var6.getKey()).name(), (String)var6.getValue());
         }

         var1.add(var2, var4);
      }
   }

   private void method5(JsonObject var1, String var2, Map<com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension, String> var3) {
      var3.clear();
      if (var1.has(var2)) {
         JsonElement var4 = var1.get(var2);
         if (!var4.isJsonNull() && var4.isJsonObject()) {
            JsonObject var5 = var4.getAsJsonObject();

            for (Entry var7 : var5.entrySet()) {
               com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension var8 = com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.valueOf(
                  (String)var7.getKey()
               );
               if (com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.VALUES.contains(var8)) {
                  JsonElement var9 = (JsonElement)var7.getValue();
                  if (!var9.isJsonNull() && var9.isJsonPrimitive()) {
                     String var10 = ((JsonElement)var7.getValue()).getAsString();
                     var3.put(var8, var10);
                  }
               }
            }
         }
      }
   }

   public static LightingExtension49522 method6(TierTagger var0) {
      return (LightingExtension49522)new LightingExtension49522.Data(var0).method31();
   }

   @Generated
   public void method7(boolean var1) {
      this.field12 = var1;
   }

   public static class Data
      extends com.moonsworth.lunar.client.config.option.NamedDropdownOption.Data<com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension> {
      private final TierTagger field15;

      protected Data(TierTagger var1) {
         super("tierSource");
         this.field15 = var1;
      }

      protected Codec<com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension> method3() {
         return com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.CODEC;
      }

      protected LightingExtension49522 method12() {
         return new LightingExtension49522(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC,
            this.codec,
            this.defaultValue == null
               ? com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.MCTIERS_COM
               : (com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension)this.defaultValue,
            this.options == null ? com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.VALUES : this.options,
            this.HCIHIHRHROOCOHROOIROOIRHHOOHHC == null
               ? com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension::niceName
               : this.HCIHIHRHROOCOHROOIROOIRHHOOHHC,
            this.field15
         );
      }
   }
}
