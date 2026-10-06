package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.alert.Alert;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import com.moonsworth.lunar.client.render.texture.Util2Handler;

public final class Util_2 {
   public static final Util_2 field1 = new Util_2();
   private static final int field2 = 33554432;
   private static final int field3 = 268435456;
   private final WeakHashMap<Util2<?>, Object> field4 = new WeakHashMap<>();
   private final Util$Data field5 = new Util$Data();
   private final Util$Data field6 = new Util$Data();
   private final WeakHashMap<Util2<?>, Alert> field7 = new WeakHashMap<>();

   private Util_2() {
      ClientEventBus.method29().method2(EventClientTick.class, var1 -> this.method7(true));
   }

   public void method1(List<String> var1) {
      var1.add("");
      this.method2((var1x, var2) -> var1.add(var1x + var2));
   }

   public void method2(BiConsumer<String, String> var1) {
      var1.accept("[LC Async Resources] Absent: ", this.field4.size() + " textures, 0 bytes");
      var1.accept("[LC Async Resources] Low Quality: ", this.field5.method3());
      var1.accept("[LC Async Resources] Full Quality: ", this.field6.method3());
      if (Bridge.getMinecraftVersion().method19()) {
         var1.accept("[LC Textures] Staging Buffer: ", method8(67108864L));
      }
   }

   private Util$Data method3(BridgeType2_5 var1) {
      return var1 == BridgeType2_5.LOW ? this.field5 : this.field6;
   }

   public void method4(Util2<?> var1, BridgeType2_5 var2, BridgeType2_5 var3, int var4) {
      Alert var5 = this.field7.computeIfAbsent(var1, var0 -> new Alert());
      int var6 = var5.getResidentSize();
      var5.setResidentSize(var4);
      if (var2 != null) {
         this.method3(var2).method2(var6);
      } else if (var6 > 0) {
         if (var1 instanceof Util2Handler var7) {
            throw new IllegalStateException(String.format("Non-zero resident size for texture %s\n", var7.field7));
         }

         throw new IllegalStateException("Non-zero resident size registered for a previously deleted texture?");
      }

      if (var3 != null) {
         this.method3(var3).method1(var4);
         this.field4.remove(var1);
      } else {
         if (var4 > 0) {
            throw new IllegalStateException("Transitioning to (deleted) with a non-zero resident size???");
         }

         this.field4.put(var1, null);
      }
   }

   public void method5(Util2<?> var1) {
      this.field7.computeIfAbsent(var1, var0 -> new Alert()).method1();
   }

   public void method6(Util2Handler var1) {
      Alert var2 = this.field7.remove(var1);
      this.field4.remove(var1);
      if (var1.method20() != null && var2 != null) {
         int var3 = var2.getResidentSize();
         this.method3(var1.method20()).method2(var3);
      }
   }

   public void method7(boolean var1) {
      if (!ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L)) {
         if (this.field6.field2 < 33554432L) {
            this.field7.forEach((var1x, var2x) -> var1x.method2(var1));
         } else {
            Bridge11_2 var2 = ThreadModuleDump63.method3().bridge$getResourceManager();
            ArrayList var3 = new ArrayList();
            ArrayList var4 = new ArrayList();
            int var5 = 0;
            long var6 = System.nanoTime();

            for (Entry var9 : this.field7.entrySet()) {
               Util2 var10 = (Util2)var9.getKey();
               Alert var11 = (Alert)var9.getValue();
               var10.method2(var1);
               if (var10.method8() != BridgeType2_5.LOW) {
                  if (var11.isIdle(var6)) {
                     var4.add(var10);
                     var5 += var11.getResidentSize();
                  } else if (var11.getIdleFactor(var6) > 0.0F && var10 instanceof Util2Handler) {
                     var3.add(var10);
                  }
               }
            }

            long var12 = this.field6.field2;
            if (var12 - var5 > 268435456L) {
               var3.sort(Comparator.<Object, Float>comparing(var3x -> this.field7.get(var3x).method3(var6)).reversed());

               while (!var3.isEmpty() && var12 - var5 > 268435456L) {
                  Util2 var13 = (Util2)var3.remove(var3.size() - 1);
                  Alert var15 = this.field7.get(var13);
                  var4.add(var13);
                  var5 += var15.getResidentSize();
               }
            }

            for (Util2 var16 : var4) {
               var16.method7(var2, BridgeType2_5.LOW);
            }
         }
      }
   }

   private static String method8(long var0) {
      long var2 = var0 == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(var0);
      if (var2 < 1024L) {
         return var0 + " B";
      }

      long var4 = var2;
      StringCharacterIterator var6 = new StringCharacterIterator("KMGTPE");

      for (byte var7 = 40; var7 >= 0 && var2 > 1152865209611504844L >> var7; var7 -= 10) {
         var4 >>= 10;
         var6.next();
      }

      var4 *= Long.signum(var0);
      return String.format("%.1f %ciB", var4 / 1024.0, var6.current());
   }
}
