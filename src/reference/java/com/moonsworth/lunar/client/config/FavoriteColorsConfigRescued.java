package com.moonsworth.lunar.client.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.config.JsonFileConfig;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;

public class FavoriteColorsConfigRescued extends ItemSetHandler<ColorOption> implements JsonFileConfig {
   private final GuiIterator field2 = new GuiIterator();

   @Override
   protected Set<ColorOption> method3() {
      return new LinkedHashSet<>(12);
   }

   @Override
   public String method5() {
      return "favorite_colors.json";
   }

   @Override
   public void init() {
      super.init();
      this.HHIHOOIHCOHOIRORCHICOCHCORROCR();
   }

   public void load(JsonObject var1) {
      boolean var2 = ConfigMigrator.method3(this, var1) && this.HOHCCHOCHIRIRORCHCROIHHOHRIHIR();
      if (var1.has("colors")) {
         JsonObject var3 = var1.getAsJsonObject("colors");

         for (Entry var5 : var3.entrySet()) {
            try {
               String var6 = (String)var5.getKey();
               ColorOption var7 = (ColorOption)((Data)OptionFactory.method8(var6).ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
                  .method31();
               var7.load(var3);
               this.method3().add(var7);
            } catch (Exception var8) {
            }
         }

         if (var2) {
            this.OCCIOHICOOHRIIIOOHRCRHOIHOOCHH(true);
         }

         this.method10();
      }
   }

   public void method1(JsonObject var1) {
      var1.addProperty("version", ConfigMigrator.field2);
      JsonObject var2 = new JsonObject();

      for (ColorOption var4 : this.method3()) {
         try {
            var4.load(var2);
         } catch (Exception var6) {
         }
      }

      var1.add("colors", var2);
   }

   public void method4(ColorOption var1) {
      int var2 = this.method3().size();
      ColorOption var3 = (ColorOption)((Data)OptionFactory.method8("favColor" + var2).ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .method31();
      var3.method1(var1.method13());
      var3.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)var1.method19().get());
      var3.method21().method1((Integer)var1.method21().get());
      var3.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC((com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension)var1.method23().get());
      this.method3().add(var3);
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      this.method10();
   }

   public void method10() {
      JsonArray var1 = new JsonArray();

      for (ColorOption var3 : this.method3()) {
         OptionDataProvider var4 = (OptionDataProvider)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
         if (var4 != null) {
            var1.add(var4.provide());
         }
      }

      this.field2.method3("colors", var1);
   }

   @Generated
   public GuiIterator method11() {
      return this.field2;
   }
}
