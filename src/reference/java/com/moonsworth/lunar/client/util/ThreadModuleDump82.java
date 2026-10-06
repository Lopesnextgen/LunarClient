package com.moonsworth.lunar.client.util;

import com.google.common.collect.AbstractIterator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge.Extension;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class ThreadModuleDump82 {
   @Nullable
   public static Vector3iBridge spiralSearch(Vector3iBridge var0, int var1, int var2, Predicate<Vector3iBridge> var3) {
      Extension var4 = Bridge.method8().method9(0, 0, 0);

      for (byte var5 = 0; var5 <= var1; var5 = (byte)(var5 > 0 ? -var5 : 1 - var5)) {
         for (int var6 = 0; var6 < var2; var6++) {
            for (byte var7 = 0; var7 <= var6; var7 = (byte)(var7 > 0 ? -var7 : 1 - var7)) {
               for (byte var8 = (byte)(var7 < var6 && var7 > -var6 ? var6 : 0); var8 <= var6; var8 = (byte)(var8 > 0 ? -var8 : 1 - var8)) {
                  var4.method2(var0, var7, var5 - 1, var8);
                  if (var3.test(var4)) {
                     return var4.method3();
                  }
               }
            }
         }
      }

      return null;
   }

   public static Vector3iBridge breadthFirstSearch(Vector3iBridge var0, int var1, int var2, Predicate<Vector3iBridge> var3) {
      LinkedList var4 = new LinkedList();
      var4.add(var0);

      while (!var4.isEmpty()) {
         Vector3iBridge var5 = (Vector3iBridge)var4.poll();
         if (var3.test(var5)) {
            return var5;
         }

         for (HorsestatsType_2 var9 : HorsestatsType_2.values()) {
            int var10 = var5.bridge$getX() + var9.getOffsetX();
            int var11 = var5.bridge$getY() + var9.getOffsetY();
            int var12 = var5.bridge$getZ() + var9.getOffsetX();
            if (Math.abs(var10 - var0.bridge$getX()) <= var2 && Math.abs(var11 - var0.bridge$getX()) <= var1 && Math.abs(var12 - var0.bridge$getX()) <= var2) {
               var4.add(Bridge.method8().method4(var10, var11, var12));
            }
         }
      }

      return null;
   }

   public static int breadthFirstCount(
      Horsestats20Extension2 var0,
      int var1,
      int var2,
      Predicate<Horsestats20Extension2> var3,
      BiConsumer<Horsestats20Extension2, Consumer<Horsestats20Extension2>> var4
   ) {
      ArrayDeque var5 = new ArrayDeque();
      LongOpenHashSet var6 = new LongOpenHashSet();
      var5.add(ObjectIntPair.of(var0, 0));
      int var7 = 0;

      while (!var5.isEmpty()) {
         ObjectIntPair var8 = (ObjectIntPair)var5.poll();
         Horsestats20Extension2 var9 = (Horsestats20Extension2)var8.key();
         int var10 = var8.valueInt();
         if (var6.add(var9.bridge$asLong()) && var10 < var1 && var3.test(var9)) {
            if (++var7 >= var2) {
               return var7;
            }

            var4.accept(var9, var2x -> var5.add(ObjectIntPair.of(var2x, var10 + 1)));
         }
      }

      return var7;
   }

   public static Iterable<Horsestats20Extension2> randomPositions(Random var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = var5 - var2 + 1;
      int var9 = var6 - var3 + 1;
      int var10 = var7 - var4 + 1;
      return () -> new AbstractIterator<Horsestats20Extension2>() {
         final Extension field1 = Bridge.method8().method9(0, 0, 0);
         int counter = var1;

         protected Horsestats20Extension2 spiralSearch() {
            if (this.counter <= 0) {
               return (Horsestats20Extension2)this.endOfData();
            }

            this.field1.bridge$setPos(var2 + var0.nextInt(var8), var3 + var0.nextInt(var9), var4 + var0.nextInt(var10));
            this.counter--;
            return this.field1.method3();
         }
      };
   }
}
