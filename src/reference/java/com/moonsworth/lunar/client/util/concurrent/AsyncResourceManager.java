package com.moonsworth.lunar.client.util.concurrent;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.TextureQuality;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.util.concurrent.ResourceUsageTracker;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.texture.Util2Handler;

public final class AsyncResourceManager {
   public static final AsyncResourceManager field1 = new AsyncResourceManager();
   private static final int field2 = 33554432;
   private static final int field3 = 268435456;
   private final WeakHashMap<AsyncResource<?>, Object> field4 = new WeakHashMap<>();
   private final ResourceUsageCounter field5 = new ResourceUsageCounter();
   private final ResourceUsageCounter field6 = new ResourceUsageCounter();
   private final WeakHashMap<AsyncResource<?>, ResourceUsageTracker> field7 = new WeakHashMap<>();

   private AsyncResourceManager() {
      LunarEventBus.method29().method2(EventTick.class, arg1 -> this.method7(true));
   }

   public void method1(List<String> list1) {
      list1.add("");
      this.method2((arg1x, arg2) -> list1.add(arg1x + arg2));
   }

   public void method2(BiConsumer<String, String> biconsumer1) {
      biconsumer1.accept("[LC Async Resources] Absent: ", this.field4.size() + " textures, 0 bytes");
      biconsumer1.accept("[LC Async Resources] Low Quality: ", this.field5.method3());
      biconsumer1.accept("[LC Async Resources] Full Quality: ", this.field6.method3());
      if (Bridge.getMinecraftVersion().method19()) {
         biconsumer1.accept("[LC Textures] Staging Buffer: ", method8(67108864L));
      }
   }

   private ResourceUsageCounter method3(TextureQuality bridgetype2_51) {
      return bridgetype2_51 == TextureQuality.LOW ? this.field5 : this.field6;
   }

   public void method4(AsyncResource<?> util21, TextureQuality bridgetype2_52, TextureQuality bridgetype2_53, int number4) {
      ResourceUsageTracker alert5 = this.field7.computeIfAbsent(util21, arg0 -> new ResourceUsageTracker());
      int number6 = alert5.method5();
      alert5.method4(number4);
      if (bridgetype2_52 != null) {
         this.method3(bridgetype2_52).method2(number6);
      } else if (number6 > 0) {
         if (util21 instanceof Util2Handler util2handler7) {
            throw new IllegalStateException(String.format("Non-zero resident size for texture %s\n", util2handler7.field7));
         }

         throw new IllegalStateException("Non-zero resident size registered for a previously deleted texture?");
      }

      if (bridgetype2_53 != null) {
         this.method3(bridgetype2_53).method1(number4);
         this.field4.remove(util21);
      } else {
         if (number4 > 0) {
            throw new IllegalStateException("Transitioning to (deleted) with a non-zero resident size???");
         }

         this.field4.put(util21, null);
      }
   }

   public void method5(AsyncResource<?> util21) {
      this.field7.computeIfAbsent(util21, arg0 -> new ResourceUsageTracker()).method1();
   }

   public void method6(Util2Handler util2handler1) {
      ResourceUsageTracker alert2 = this.field7.remove(util2handler1);
      this.field4.remove(util2handler1);
      if (util2handler1.method20() != null && alert2 != null) {
         int number3 = alert2.method5();
         this.method3(util2handler1.method20()).method2(number3);
      }
   }

   public void method7(boolean flag1) {
      if (!Ref.method4().method40().method85().method17(arg0 -> arg0.method41().method18() / 50L > 20L)) {
         if (this.field6.field2 < 33554432L) {
            this.field7.forEach((arg1x, arg2x) -> arg1x.method2(flag1));
         } else {
            Bridge11_2 bridge11_22 = Ref.method3().bridge$getResourceManager();
            ArrayList list3 = new ArrayList();
            ArrayList list4 = new ArrayList();
            int number5 = 0;
            long number6 = System.nanoTime();

            for (Entry entry9 : this.field7.entrySet()) {
               AsyncResource util210 = (AsyncResource)entry9.getKey();
               ResourceUsageTracker alert11 = (ResourceUsageTracker)entry9.getValue();
               util210.method2(flag1);
               if (util210.method8() != TextureQuality.LOW) {
                  if (alert11.method2(number6)) {
                     list4.add(util210);
                     number5 += alert11.method5();
                  } else if (alert11.method3(number6) > 0.0F && util210 instanceof Util2Handler) {
                     list3.add(util210);
                  }
               }
            }

            long number12 = this.field6.field2;
            if (number12 - number5 > 268435456L) {
               list3.sort(Comparator.<Object, Float>comparing(arg3x -> this.field7.get(arg3x).method3(number6)).reversed());

               while (!list3.isEmpty() && number12 - number5 > 268435456L) {
                  AsyncResource util213 = (AsyncResource)list3.remove(list3.size() - 1);
                  ResourceUsageTracker alert15 = this.field7.get(util213);
                  list4.add(util213);
                  number5 += alert15.method5();
               }
            }

            for (AsyncResource util216 : list4) {
               util216.method7(bridge11_22, TextureQuality.LOW);
            }
         }
      }
   }

   private static String method8(long number0) {
      long number2 = number0 == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(number0);
      if (number2 < 1024L) {
         return number0 + " B";
      }

      long number4 = number2;
      StringCharacterIterator stringcharacteriterator6 = new StringCharacterIterator("KMGTPE");

      for (byte index7 = 40; index7 >= 0 && number2 > 1152865209611504844L >> index7; index7 -= 10) {
         number4 >>= 10;
         stringcharacteriterator6.next();
      }

      number4 *= Long.signum(number0);
      return String.format("%.1f %ciB", number4 / 1024.0, stringcharacteriterator6.current());
   }
}
