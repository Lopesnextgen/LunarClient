package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.Annotation4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class TextHudComponent implements MixinCore5 {
   private final MixinCore6<Component> field1 = new MixinCore6<>(null, true, true);
   private final MixinCore6<RewindhandlersExtension> field2 = new MixinCore6<>(RewindhandlersExtension.method23(-1), false, true);
   private final MixinCore6<Boolean> field3 = new MixinCore6<>(true, false, true);
   private final MixinCore6<Double> field4 = new MixinCore6<>(1.0, false, true);
   private Float field5 = null;

   public TextHudComponent() {
   }

   public TextHudComponent(Component var1) {
      this.field1.method1(var1);
   }

   public TextHudComponent(
      @Annotation4(
   OOHROIOIOICORCRHHIIHHCROOHHRCH = Annotation.Type.MOD_INFO,
   RHCICCHIRIIRCHOHRCHCIIRRRORRRR = "getInfo",
   method3 = true

   }

   public TextHudComponent(
      @Annotation4(
   OOHROIOIOICORCRHHIIHHCROOHHRCH = Annotation.Type.MOD_INFO,
   RHCICCHIRIIRCHOHRCHCIIRRRORRRR = "getInfo",
   method3 = true
ar2
   ) {
      this.method1(var1);
      this.method9(var2);
   }

   public TextHudComponent(
      @Annotation4(
   OOHROIOIOICORCRHHIIHHCROOHHRCH = Annotation.Type.MOD_INFO,
   RHCICCHIRIIRCHOHRCHCIIRRRORRRR = "getInfo",
   method3 = true
   this.method1(var1);
      this.method11(var2);
   }

   public TextHudComponent(
      @Annotation4(
   OOHROIOIOICORCRHHIIHHCROOHHRCH = Annotation.Type.MOD_INFO,
   RHCICCHIRIIRCHOHRCHCIIRRRORRRR = "getInfo",
   method3 = true
ar2
   ) {
      this.method1(var1);
      this.method7(var2);
   }

   public TextHudComponent(Supplier<String> var1) {
      this.method2(var1);
   }

   public TextHudComponent(Supplier<String> var1, AdventureChatFormatting var2) {
      this.method2(var1);
      this.method9(var2);
   }

   public TextHudComponent(Supplier<String> var1, TextColor var2) {
      this.method2(var1);
      this.method11(var2);
   }

   public TextHudComponent(Supplier<String> var1, RewindhandlersExtension var2) {
      this.method2(var1);
      this.method7(var2);
   }

   public TextHudComponent method1(
      @Annotation4(
   OOHROIOIOICORCRHHIIHHCROOHHRCH = Annotation.Type.MOD_INFO,
   RHCICCHIRIIRCHOHRCHCIIRRRORRRR = "getInfo",
   method3 = true
(Component.text(var1));
      return this;
   }

   public TextHudComponent method2(Supplier<String> var1) {
      this.field1.method2(() -> Component.text((String)var1.get()));
      return this;
   }

   public TextHudComponent method3(Component var1) {
      this.field1.method1(var1);
      return this;
   }

   public TextHudComponent method4(Supplier<Component> var1) {
      this.field1.method2(var1);
      return this;
   }

   public TextHudComponent method5(int var1) {
      this.field2.method1(RewindhandlersExtension.method23(var1));
      return this;
   }

   public TextHudComponent method6(Supplier<Integer> var1) {
      this.field2.method2(() -> RewindhandlersExtension.method23((Integer)var1.get()));
      return this;
   }

   public TextHudComponent method7(RewindhandlersExtension var1) {
      this.field2.method1(var1);
      return this;
   }

   public TextHudComponent method8(Supplier<RewindhandlersExtension> var1) {
      this.field2.method2(var1);
      return this;
   }

   public TextHudComponent method9(AdventureChatFormatting var1) {
      this.method7(RewindhandlersExtension.method24(var1));
      return this;
   }

   public TextHudComponent method10(Supplier<AdventureChatFormatting> var1) {
      AtomicReference var2 = new AtomicReference();
      AtomicReference var3 = new AtomicReference();
      this.method8(() -> {
         AdventureChatFormatting var3x = (AdventureChatFormatting)var1.get();
         if (var3x != var2.get()) {
            var2.set(var3x);
            var3.set(RewindhandlersExtension.method24(var3x));
         }

         return (RewindhandlersExtension)var3.get();
      });
      return this;
   }

   public TextHudComponent method11(TextColor var1) {
      this.method7(RewindhandlersExtension.method25(var1));
      return this;
   }

   public TextHudComponent method12(Supplier<TextColor> var1) {
      AtomicReference var2 = new AtomicReference();
      AtomicReference var3 = new AtomicReference();
      this.method8(() -> {
         TextColor var3x = (TextColor)var1.get();
         if (var3x != var2.get()) {
            var2.set(var3x);
            var3.set(RewindhandlersExtension.method25(var3x));
         }

         return (RewindhandlersExtension)var3.get();
      });
      return this;
   }

   public TextHudComponent method13(boolean var1) {
      this.field3.method1(var1);
      return this;
   }

   public TextHudComponent method14(double var1) {
      this.field4.method1(var1);
      return this;
   }

   public TextHudComponent method15(Supplier<Double> var1) {
      this.field4.method2(var1);
      return this;
   }

   @Override
   public void method1(float var1, float var2, HudRenderContext var3) {
      Component var4 = this.field1.get();
      if (var4 != null) {
         float var5 = this.field4.get().floatValue();
         RewindhandlersExtension var6 = this.field2.get();
         var3.method6().push();
         var3.method6().scale(var5, var5, 1.0F);
         var6.method15(var3.method6(), var4, var1 / var5, var2 / var5, this.field3.get());
         var3.method6().pop();
      }
   }

   @Override
   public void clearCache() {
      this.field5 = null;
      this.field1.clearCache();
      this.field2.clearCache();
      this.field3.clearCache();
      this.field4.clearCache();
   }

   @Override
   public float getWidth() {
      if (this.field1.get() == null) {
         return 0.0F;
      }

      if (this.field5 == null) {
         float var1 = this.field4.get().floatValue();
         this.field5 = ThreadModuleDump63.method10().bridge$getStringWidth(this.field1.get()) * var1;
      }

      return this.field5;
   }

   @Override
   public float getHeight() {
      return ThreadModuleDump63.method10().method19() * this.field4.get().floatValue();
   }
}
