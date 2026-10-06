package com.moonsworth.lunar.legacy;

import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moonsworth.lunar.bridge.Bridge5;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.MixinHelper5;
import com.moonsworth.lunar.bridge.MixinHelper_13;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.MixinHelper.Extension2;
import com.moonsworth.lunar.bridge.MixinHelper.Extension3;
import com.moonsworth.lunar.client.highlight.Highlight2;
import com.moonsworth.lunar.client.highlight.HighlightImpl17.Data;
import com.moonsworth.lunar.client.highlight.HighlightImpl17.Data2;
import com.moonsworth.lunar.client.mod.render.ScrollableTooltips;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.click.Click2Base;
import java.util.List;
import java.util.stream.Collectors;
import net.kyori.adventure.text.TextComponent;

public class MixinCore3 {
   public static boolean method1(List<String> var0, int var1, int var2, LocalRef<BridgeExtension_9> var3, LocalRef<MixinHelper_4> var4, LocalRef<Data2> var5) {
      var3.set(BridgeExtension_9.method32());
      var4.set(new MixinHelper5((BridgeExtension_9)var3.get()));
      var5.set((Data2)Highlight2.method29().method12(Data2.class, () -> {
         List var4x = var0.stream().<TextComponent>map(Bridge5::asAdventure).map(Click2Base.Data::new).collect(Collectors.toList());
         return new Data2((MixinHelper_4)var4.get(), var4x, var1, var2, ThreadModuleDump63.method4().method40().method46().method14());
      }));
      return var5.get() != null && ((Data2)var5.get()).isCancelled();
   }

   public static void method2(LocalIntRef var0, LocalIntRef var1, LocalRef<Data2> var2) {
      var1.set(var0.get());
      method4(var1, var2);
      var0.set(var1.get());
      method6(var0);
   }

   public static void method3(LocalIntRef var0, LocalIntRef var1, LocalRef<Data2> var2) {
      var1.set(var0.get());
      method5(var1, var2);
      var0.set(var1.get());
      method6(var0);
   }

   private static void method4(LocalIntRef var0, LocalRef<Data2> var1) {
      Data2 var2 = (Data2)var1.get();
      if (var2 != null && var2.isModified()) {
         var0.set(0);

         for (MixinHelper_13 var4 : var2.method5()) {
            int var5 = var4.bridge$getWidth(ThreadModuleDump63.method10());
            if (var5 > var0.get()) {
               var0.set(var5);
            }
         }
      }
   }

   private static void method5(LocalIntRef var0, LocalRef<Data2> var1) {
      Data2 var2 = (Data2)var1.get();
      if (var2 != null && var2.isModified()) {
         var0.set(0);

         for (MixinHelper_13 var4 : var2.method5()) {
            var0.set(var0.get() + var4.bridge$getHeight(ThreadModuleDump63.method10()));
         }
      }
   }

   private static void method6(LocalIntRef var0) {
      ScrollableTooltips var1 = ThreadModuleDump63.method4().method40().method46();
      float var2 = var1.method16().get();
      if (var1.isEnabled()) {
         var0.set((int)(var0.get() * var2));
      }
   }

   public static void method7(
      LocalIntRef var0,
      LocalIntRef var1,
      LocalIntRef var2,
      LocalIntRef var3,
      LocalIntRef var4,
      LocalIntRef var5,
      LocalRef<BridgeExtension_9> var6,
      LocalRef<MixinHelper_4> var7
   ) {
      method8(var0, var0, var1, var2, var3, var4, var5, var6, var7);
   }

   public static void method8(
      LocalIntRef var0,
      LocalIntRef var1,
      LocalIntRef var2,
      LocalIntRef var3,
      LocalIntRef var4,
      LocalIntRef var5,
      LocalIntRef var6,
      LocalRef<BridgeExtension_9> var7,
      LocalRef<MixinHelper_4> var8
   ) {
      var0.set(var3.get());
      var1.set(var3.get());
      var2.set(var4.get());
      Data var9 = (Data)Highlight2.method29().method12(Data.class, () -> new Data(var5.get(), var6.get(), var0.get(), var2.get()));
      if (var9 != null && var9.isModified()) {
         int var10 = var9.method4();
         int var11 = var9.method5();
         ((MixinHelper_4)var8.get()).method39(var10, var11);
         ScrollableTooltips var12 = ThreadModuleDump63.method4().method40().method46();
         float var13 = var12.method16().get();
         if (!var12.isEnabled()) {
            var13 = 1.0F;
         }

         ((MixinHelper_4)var8.get()).method40(var13 * var9.getScale(), var13 * var9.getScale());
         var5.set(0);
         var6.set(0);
      }

      if (var9 != null) {
         var9.updateCache();
      }
   }

   public static void method9(int var0, int var1, LocalRef<Data2> var2, LocalRef<BridgeExtension_9> var3, LocalRef<MixinHelper_4> var4) {
      Data2 var5 = (Data2)var2.get();
      if (var5 != null && var5.isModified()) {
         BridgeExtension_9 var6 = (BridgeExtension_9)var3.get();
         MixinHelper_4 var7 = (MixinHelper_4)var4.get();
         if (var6 == null || var7 == null) {
            return;
         }

         int var8 = var1;

         for (MixinHelper_13 var11 : var5.method5()) {
            if (var11 instanceof Extension2 var12) {
               var6.push();
               var6.translate(var0, var8, 0.0);
               var12.method1().render(var6, var7);
               var6.pop();
            } else if (var11 instanceof Extension3 var13) {
               ((MixinHelper_4)var4.get()).method10(ThreadModuleDump63.method10(), var13.bridge$getComponent(), var0, var8, -1, true);
            }

            var8 += var11.bridge$getHeight(ThreadModuleDump63.method10());
         }
      }
   }
}
