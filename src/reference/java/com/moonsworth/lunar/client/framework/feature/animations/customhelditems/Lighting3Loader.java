package com.moonsworth.lunar.client.framework.feature.animations.customhelditems;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.event.mixin.highlight.GlintTransformEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import org.intellij.lang.annotations.Subst;

class Lighting3Loader implements JsonPersistable {
   private final Lighting3Loader2 field1;
   private final CustomhelditemsType field2;
   private final ToggleOption field3;
   public final FloatOption field4 = (FloatOption)((Data)((Data)OptionFactory.method2("scale").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 2.0F))
      .method31();
   public final FloatOption field5 = (FloatOption)((Data)((Data)OptionFactory.method2("x").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(-1.0F, 1.0F))
      .method31();
   public final FloatOption field6 = (FloatOption)((Data)((Data)OptionFactory.method2("y").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(-1.0F, 1.0F))
      .method31();
   public final FloatOption field7 = (FloatOption)((Data)((Data)OptionFactory.method2("z").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(-1.0F, 1.0F))
      .method31();
   public final FloatOption field8 = (FloatOption)((Data)((Data)OptionFactory.method2("xRotation").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(-180.0F, 180.0F))
      .method31();
   public final FloatOption field9 = (FloatOption)((Data)((Data)OptionFactory.method2("yRotation").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(-180.0F, 180.0F))
      .method31();
   public final FloatOption field10 = (FloatOption)((Data)((Data)OptionFactory.method2("zRotation").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
         .method8(-180.0F, 180.0F))
      .method31();

   Lighting3Loader(Lighting3Loader2 var1, @Subst("optionId") CustomhelditemsType var2) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(var2.id).OOOIROIIOCOOHICRIRHHHRROHHHHIO(var2.enabledByDefault))
         .method31();
   }

   public void load(JsonObject var1) {
      ThreadModuleDump9.findJsonObject(var1, this.field3.getId()).ifPresent(var1x -> {
         this.field3.load(var1x);
         this.field4.load(var1x);
         this.field5.load(var1x);
         this.field6.load(var1x);
         this.field7.load(var1x);
         this.field8.load(var1x);
         this.field9.load(var1x);
         this.field10.load(var1x);
      });
   }

   public void method1(JsonObject var1) {
      JsonObject var2 = new JsonObject();
      this.field3.load(var2);
      this.field4.load(var2);
      this.field5.load(var2);
      this.field6.load(var2);
      this.field7.load(var2);
      this.field8.load(var2);
      this.field9.load(var2);
      this.field10.load(var2);
      if (!var2.isEmpty()) {
         var1.add(this.field3.getId(), var2);
      }
   }

   public void method2(GlintTransformEvent var1) {
      var1.method1((Float)this.field5.get(), (Float)this.field6.get(), (Float)this.field7.get());
      float var2 = ThreadModuleDump63.MC_VERSION > 5 ? (Float)this.field8.get() : (Float)this.field10.get();
      float var3 = (Float)this.field9.get();
      float var4 = ThreadModuleDump63.MC_VERSION > 5 ? (Float)this.field10.get() : (Float)this.field8.get();
      if (var2 != 0.0F) {
         var1.method2(var2);
      }

      if (var3 != 0.0F) {
         var1.method3(var3);
      }

      if (var4 != 0.0F) {
         var1.method4(var4);
      }

      float var5 = (Float)this.field4.get();
      if (var5 != 1.0F) {
         var1.scale(var5, var5, var5);
      }
   }

   public void method3(com.moonsworth.lunar.client.config.option.RootSettingsAssembler.Data var1) {
      if (this.isActive()) {
         if (this.field1.method5()) {
            var1.method7(this.field3, this::method4);
            var1.ROOOCICROROOHCIRRHHHCRCOROOHHH().method3(this.field3::method9);
         } else {
            this.method4(var1);
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.config.option.RootSettingsAssembler.Data var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field4});
      var1.method13("position");
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field5, this.field6, this.field7});
      var1.method13("rotation");
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field8, this.field9, this.field10});
      if (this.field1.method5()) {
         var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionSupplier[]{OptionFactory.method14("copyFromOtherHand").method4(() -> {
            Lighting3Loader var1x = this.field1.method4(this.field2.opposite());
            if (var1x != null) {
               this.field3.method19(var1x.field3);
               this.field4.method19(var1x.field4);
               this.field5.method19(var1x.field5);
               this.field6.method19(var1x.field6);
               this.field7.method19(var1x.field7);
               this.field8.method19(var1x.field8);
               this.field9.method19(var1x.field9);
               this.field10.method19(var1x.field10);
            }
         })});
      }
   }

   public boolean isEnabled() {
      return this.isActive() && (!this.field1.method5() || (Boolean)this.field3.get());
   }

   private boolean isActive() {
      return !this.field1.method5() ? this.field2 == CustomhelditemsType.RIGHT : true;
   }
}
