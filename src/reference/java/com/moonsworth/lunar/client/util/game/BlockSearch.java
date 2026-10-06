package com.moonsworth.lunar.client.util.game;

import com.google.common.collect.AbstractIterator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge.Extension;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class BlockSearch {
   public BlockSearch() {
   }

   @Nullable
   public static Vec3iBridge method1(Vec3iBridge horsestats200, int number1, int number2, Predicate<Vec3iBridge> predicate3) {
      Extension extension4 = Bridge.method8().method9(0, 0, 0);

      for (byte index5 = 0; index5 <= number1; index5 = (byte)(index5 > 0 ? -index5 : 1 - index5)) {
         for (int index6 = 0; index6 < number2; index6++) {
            for (byte index7 = 0; index7 <= index6; index7 = (byte)(index7 > 0 ? -index7 : 1 - index7)) {
               for (byte index8 = (byte)(index7 < index6 && index7 > -index6 ? index6 : 0); index8 <= index6; index8 = (byte)(index8 > 0 ? -index8 : 1 - index8)) {
                  extension4.method2(horsestats200, index7, index5 - 1, index8);
                  if (predicate3.test(extension4)) {
                     return extension4.method3();
                  }
               }
            }
         }
      }

      return null;
   }

   public static Vec3iBridge method2(Vec3iBridge horsestats200, int number1, int number2, Predicate<Vec3iBridge> predicate3) {
      LinkedList list4 = new LinkedList();
      list4.add(horsestats200);

      while (!list4.isEmpty()) {
         Vec3iBridge horsestats205 = (Vec3iBridge)list4.poll();
         if (predicate3.test(horsestats205)) {
            return horsestats205;
         }

         for (HorsestatsType_2 horsestatstype_29 : HorsestatsType_2.values()) {
            int number10 = horsestats205.bridge$getX() + horsestatstype_29.getOffsetX();
            int number11 = horsestats205.bridge$getY() + horsestatstype_29.getOffsetY();
            int number12 = horsestats205.bridge$getZ() + horsestatstype_29.getOffsetX();
            if (Math.abs(number10 - horsestats200.bridge$getX()) <= number2 && Math.abs(number11 - horsestats200.bridge$getX()) <= number1 && Math.abs(number12 - horsestats200.bridge$getX()) <= number2) {
               list4.add(Bridge.method8().method4(number10, number11, number12));
            }
         }
      }

      return null;
   }

   public static int method3(
      Horsestats20Extension2 horsestats20extension20,
      int number1,
      int number2,
      Predicate<Horsestats20Extension2> predicate3,
      BiConsumer<Horsestats20Extension2, Consumer<Horsestats20Extension2>> biconsumer4
   ) {
      ArrayDeque arraydeque5 = new ArrayDeque();
      LongOpenHashSet longopenhashset6 = new LongOpenHashSet();
      arraydeque5.add(ObjectIntPair.of(horsestats20extension20, 0));
      int number7 = 0;

      while (!arraydeque5.isEmpty()) {
         ObjectIntPair objectintpair8 = (ObjectIntPair)arraydeque5.poll();
         Horsestats20Extension2 horsestats20extension29 = (Horsestats20Extension2)objectintpair8.key();
         int number10 = objectintpair8.valueInt();
         if (longopenhashset6.add(horsestats20extension29.bridge$asLong()) && number10 < number1 && predicate3.test(horsestats20extension29)) {
            if (++number7 >= number2) {
               return number7;
            }

            biconsumer4.accept(horsestats20extension29, arg2x -> arraydeque5.add(ObjectIntPair.of(arg2x, number10 + 1)));
         }
      }

      return number7;
   }

   public static Iterable<Horsestats20Extension2> method4(Random random0, int number1, int number2, int number3, int number4, int number5, int number6, int number7) {
      int number8 = number5 - number2 + 1;
      int number9 = number6 - number3 + 1;
      int number10 = number7 - number4 + 1;
      return () -> new AbstractIterator<Horsestats20Extension2>() {
         final Extension field1 = Bridge.method8().method9(0, 0, 0);
         int counter = number1;

         protected Horsestats20Extension2 method1() {
            if (this.counter <= 0) {
               return (Horsestats20Extension2)this.endOfData();
            }

            this.field1.bridge$setPos(number2 + random0.nextInt(number8), number3 + random0.nextInt(number9), number4 + random0.nextInt(number10));
            this.counter--;
            return this.field1.method3();
         }
      };
   }
}
