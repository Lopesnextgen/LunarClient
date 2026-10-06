package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.Annotation4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class MixinCore5Task extends MixinCore5Handler_2<MixinCore5Task> {
   private static int field2 = -1;
   private static boolean field3 = false;
   private static double field4 = 0.0;
   private static boolean field5 = false;
   private final MixinCore6<List<Component>> field6 = new MixinCore6<>(null, true, true);
   private final List<Consumer<Integer>> field7 = new ArrayList<>();
   private final List<Consumer<Double>> field8 = new ArrayList<>();
   private final List<Runnable> field9 = new ArrayList<>();
   private final List<Runnable> field10 = new ArrayList<>();
   private boolean field11;

   public MixinCore5Task() {
   }

   public MixinCore5Task(MixinCore5 var1) {
      super(var1);
   }

   public MixinCore5Task method1(
      @Annotation4(
   OOHROIOIOICORCRHHIIHHCROOHHRCH = Annotation.Type.MOD_INFO,
   RHCICCHIRIIRCHOHRCHCIIRRRORRRR = "getInfo",
   method3 = true
(List.of(Component.text(var1)));
      return this;
   }

   public MixinCore5Task method2(Supplier<String> var1) {
      this.field6.method2(() -> List.of(Component.text((String)var1.get())));
      return this;
   }

   public MixinCore5Task method3(List<String> var1) {
      this.field6.method1(var1.stream().map(var0 -> Component.text(var0)).toList());
      return this;
   }

   public MixinCore5Task method4(Supplier<List<String>> var1) {
      this.field6.method2(() -> ((List)var1.get()).stream().map(var0x -> Component.text(var0x)).toList());
      return this;
   }

   public MixinCore5Task method5(Component var1) {
      this.field6.method1(List.of(var1));
      return this;
   }

   public MixinCore5Task method6(Supplier<Component> var1) {
      this.field6.method2(() -> List.of((Component)var1.get()));
      return this;
   }

   public MixinCore5Task method7(List<Component> var1) {
      this.field6.method1(var1);
      return this;
   }

   public MixinCore5Task method8(Supplier<List<Component>> var1) {
      this.field6.method2(var1);
      return this;
   }

   public MixinCore5Task method9(Consumer<Integer> var1) {
      this.field7.add(var1);
      return this;
   }

   public MixinCore5Task method10(Runnable var1) {
      this.field7.add(var1x -> {
         if (var1x == 0) {
            var1.run();
         }
      });
      return this;
   }

   public MixinCore5Task method11(Runnable var1) {
      this.field7.add(var1x -> {
         if (var1x == 1) {
            var1.run();
         }
      });
      return this;
   }

   public MixinCore5Task method12(Consumer<Double> var1) {
      this.field8.add(var1);
      return this;
   }

   public MixinCore5Task method13(Runnable var1) {
      this.field9.add(var1);
      return this;
   }

   public MixinCore5Task method14(Runnable var1) {
      this.field10.add(var1);
      return this;
   }

   @Override
   public void method1(float var1, float var2, HudRenderContext var3) {
      boolean var4 = this.field11;
      this.field11 = var3.method8().HHHCHORHIHRCOHIOICICICHCRRICCI() >= var1
         && var3.method8().IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= var2
         && var3.method8().HHHCHORHIHRCOHIOICICICHCRRICCI() <= var1 + this.getWidth()
         && var3.method8().IHRCCHHROHIRCOOOHRRIHOORRHIOHO() <= var2 + this.getHeight();
      if (this.field11 && !var4) {
         this.field9.forEach(Runnable::run);
      } else if (!this.field11 && var4) {
         this.field10.forEach(Runnable::run);
      }

      if (this.field11) {
         List var5 = this.field6.get();
         if (var5 != null && !var5.isEmpty()) {
            var3.method9(var5);
         }
      }

      super.method1(var1, var2, var3);
      if (field2 != -1 && this.field11 && !this.field7.isEmpty()) {
         this.field7.forEach(var0 -> var0.accept(field2));
         field2 = -1;
      }

      if (field4 != 0.0 && this.field11 && !this.field8.isEmpty()) {
         double var7 = field4;
         field4 = 0.0;
         this.field8.forEach(var2x -> var2x.accept(var7));
      }
   }

   @Override
   public void clearCache() {
      this.field6.clearCache();
      super.clearCache();
   }

   public static boolean method16() {
      return ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen();
   }

   public static boolean method17() {
      return !method16();
   }

   @Generated
   public boolean method18() {
      return this.field11;
   }

   static {
      ClientEventBus.method29().method2(MarkerInputEvent.class, var0 -> {
         if (ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            if (var0.method4() == MouseInputTypeLegacy.CLICK) {
               field2 = var0.method3();
               field3 = false;
            } else if (var0.method4() == MouseInputTypeLegacy.SCROLL) {
               field4 = field4 + (ThreadModuleDump63.MC_VERSION > 6 ? var0.method9() : var0.method9() / 120.0);
               field5 = false;
            }
         }
      });
      ClientEventBus.method29().method4(HudBaseRenderEvent.class, var0 -> {
         if (field2 != -1) {
            if (field3) {
               field2 = -1;
            }

            field3 = !field3;
         }

         if (field4 != 0.0) {
            if (field5) {
               field4 = 0.0;
            }

            field5 = !field5;
         }
      }, 1000);
   }
}
