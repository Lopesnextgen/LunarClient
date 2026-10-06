package com.moonsworth.lunar.client.framework.feature.tiertagger.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger2_2;
import com.moonsworth.lunar.client.framework.feature.tiertagger.TierGameMode;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class TierSourceModeOption
   extends com.moonsworth.lunar.client.config.option.CyclingDropdownOption<com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource> {
   private final TierTagger field9;
   private final Map<com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource, String> field10 = new HashMap<>();
   private final Map<com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource, String> field11 = new HashMap<>();
   private boolean field12 = false;

   protected TierSourceModeOption(
      String text1,
      @Nullable Codec<TiertaggerShownStatistic> codec2,
      com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource gui2extension3,
      List<com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource> list4,
      @Nullable Function<TiertaggerShownStatistic, String> function5,
      TierTagger tiertagger6
   ) {
      super(text1, codec2, gui2extension3, list4, function5);
      this.field9 = tiertagger6;
   }

   public void method1(com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource gui2extension1, boolean flag2) {
      if (!this.field12) {
         super.method11(gui2extension1, flag2);
      } else {
         this.field10.put(this.method9(), ((TierGameMode)this.field9.field13.get()).apiName());
         this.field11.put(this.method9(), ((TierGameMode)this.field9.field14.get()).apiName());
         super.method11(gui2extension1, flag2);
         this.field9.field13.setOptions(gui2extension1.getTierProvider().method3());
         this.field9.field14.setOptions(gui2extension1.getTierProvider().method3());
         String text3 = this.field10.get(gui2extension1);
         if (text3 != null) {
            gui2extension1.getTierProvider().method9(text3).ifPresent(this.field9.field13::method10);
         }

         String text4 = this.field11.get(gui2extension1);
         if (text4 != null) {
            gui2extension1.getTierProvider().method9(text4).ifPresent(this.field9.field14::method10);
         }

         for (com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource gui2extension6 : com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource.VALUES) {
            gui2extension6.getTierProvider().method6();
         }

         this.field9.clearCaches();
      }
   }

   public Tiertagger2_2 getTierProvider() {
      return this.method9().getTierProvider();
   }

   public com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource method9() {
      return Ref.MC_VERSION == 1
         ? com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource.TIERTESTS
         : (com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource)super.get();
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      this.method4(json1, "modeBySource", this.field10);
      this.method4(json1, "secondaryModeBySource", this.field11);
   }

   public void load(JsonObject json1) {
      super.load(json1);
      this.method5(json1, "modeBySource", this.field10);
      this.method5(json1, "secondaryModeBySource", this.field11);
   }

   private void method4(JsonObject json1, String text2, Map<com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource, String> map3) {
      if (!map3.isEmpty()) {
         JsonObject json4 = new JsonObject();

         for (Entry entry6 : map3.entrySet()) {
            json4.addProperty(((com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource)entry6.getKey()).name(), (String)entry6.getValue());
         }

         json1.add(text2, json4);
      }
   }

   private void method5(JsonObject json1, String text2, Map<com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource, String> map3) {
      map3.clear();
      if (json1.has(text2)) {
         JsonElement element4 = json1.get(text2);
         if (!element4.isJsonNull() && element4.isJsonObject()) {
            JsonObject json5 = element4.getAsJsonObject();

            for (Entry entry7 : json5.entrySet()) {
               com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource gui2extension8 = com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource.valueOf(
                  (String)entry7.getKey()
               );
               if (com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource.VALUES.contains(gui2extension8)) {
                  JsonElement element9 = (JsonElement)entry7.getValue();
                  if (!element9.isJsonNull() && element9.isJsonPrimitive()) {
                     String text10 = ((JsonElement)entry7.getValue()).getAsString();
                     map3.put(gui2extension8, text10);
                  }
               }
            }
         }
      }
   }

   public static TierSourceModeOption method6(TierTagger tiertagger0) {
      return (TierSourceModeOption)new TierSourceModeOption.Data(tiertagger0).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   }

   @Generated
   public void method7(boolean flag1) {
      this.field12 = flag1;
   }

   public static class Data
      extends com.moonsworth.lunar.client.config.option.NamedDropdownOption.Data<com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource> {
      private final TierTagger field15;

      protected Data(TierTagger tiertagger1) {
         super("tierSource");
         this.field15 = tiertagger1;
      }

      protected Codec<com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource> method3() {
         return com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource.CODEC;
      }

      protected TierSourceModeOption method12() {
         return new TierSourceModeOption(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC,
            this.codec,
            this.defaultValue == null
               ? com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource.MCTIERS_COM
               : (com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource)this.defaultValue,
            this.options == null ? com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource.VALUES : this.options,
            this.HCIHIHRHROOCOHROOIROOIRHHOOHHC == null
               ? com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource::niceName
               : this.HCIHIHRHROOCOHROOIROOIRHHOOHHC,
            this.field15
         );
      }
   }
}
