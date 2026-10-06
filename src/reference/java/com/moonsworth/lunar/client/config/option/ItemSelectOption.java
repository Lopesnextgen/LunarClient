package com.moonsworth.lunar.client.config.option;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.itemcounter.Itemcounter2;
import com.moonsworth.lunar.client.framework.feature.itemcounter.Itemcounter3;
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemcounterImpl;
import com.moonsworth.lunar.client.framework.feature.itemcounter.Itemcounter_2;
import com.moonsworth.lunar.client.framework.feature.itemcounter.mixin.Itemcounter;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump33;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class ItemSelectOption extends MultiSelectOption {
   private static final List<Itemcounter_2> field10 = method9();
   private static final List<String> field11 = new ArrayList<>();
   private static final Map<String, Itemcounter_2> field12 = new HashMap<>();
   private final AtomicReference<Map<String, Itemcounter_2>> field13 = new AtomicReference<>(new Object2ObjectArrayMap());

   public ItemSelectOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<Set<String>> var2,
      Collection<String> var3,
      Set<String> var4,
      @Nullable Consumer<String> var5,
      Function<String, String> var6
   ) {
      super(var1, var2, var3, var4, var5, var6);
      if (!var4.isEmpty()) {
         this.method3(var4);
      }
   }

   private static List<Itemcounter_2> method9() {
      Builder var0 = ImmutableList.builder();

      for (Bridge6_4 var2 : Bridge.method28().method99()) {
         String var3 = var2.bridge$getRegistryName();
         if (!var3.endsWith("potion") && !var3.equals("minecraft:water")) {
            if (Bridge.getMinecraftVersion().method19()) {
               var0.add(new Itemcounter_2(var3));
            } else {
               List var4 = Itemcounter.method1(var3);
               if (var4.isEmpty()) {
                  var0.add(new Itemcounter_2(var3));
               } else {
                  for (Itemcounter.Data var6 : var4) {
                     var0.add(new ItemcounterImpl(var3, var6.method2(), var6.method1()));
                  }
               }
            }
         }
      }

      if (Bridge.getMinecraftVersion().method22()) {
         for (int var13 : ThreadModuleDump33.field2) {
            var0.add(new Itemcounter2(var13));
            if (var13 != 0 && var13 != 16 && var13 != 32 && var13 != 64) {
               var0.add(new Itemcounter2(ThreadModuleDump33.method7(var13)));
            }
         }
      } else {
         List var8 = List.of("minecraft:potion", "minecraft:splash_potion", "minecraft:lingering_potion");

         for (String var12 : Bridge.method36().method15()) {
            for (String var15 : var8) {
               var0.add(new Itemcounter3(var15, var12));
            }
         }
      }

      return var0.build();
   }

   @Override
   public void method1(Set<String> var1) {
      super.method1(var1);
      this.method3(var1);
   }

   private void method3(Collection<String> var1) {
      Object2ObjectArrayMap var2 = new Object2ObjectArrayMap(var1.size());

      for (String var4 : var1) {
         Itemcounter_2 var5 = this.method24(var4);
         if (var5 != null) {
            var2.put(var4, var5);
         }
      }

      this.field13.set(var2);
   }

   @Override
   public boolean method10(String var1) {
      if (super.method10(var1)) {
         Itemcounter_2 var2 = this.method24(var1);
         if (var2 != null) {
            this.field13.getAndUpdate(var2x -> {
               Object2ObjectArrayMap var3 = new Object2ObjectArrayMap(var2x);
               var3.put(var1, var2);
               return var3;
            });
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean remove(String var1) {
      if (super.remove(var1)) {
         this.field13.getAndUpdate(var1x -> {
            Object2ObjectArrayMap var2 = new Object2ObjectArrayMap(var1x);
            var2.remove(var1);
            return var2;
         });
         return true;
      } else {
         return false;
      }
   }

   public boolean method5(ItemStackBridge var1) {
      for (Itemcounter_2 var4 : this.field13.get().values()) {
         if (var4.method2(var1)) {
            return true;
         }
      }

      return false;
   }

   private @Nullable Itemcounter_2 method24(String var1) {
      return method11().get(var1);
   }

   @Generated
   public static List<String> method10() {
      return field11;
   }

   @Generated
   public static Map<String, Itemcounter_2> method11() {
      return field12;
   }

   static {
      for (Itemcounter_2 var1 : field10) {
         field12.put(var1.method1(), var1);
         field11.add(var1.method1());
      }
   }

   public static class Data extends MultiSelectOption.Data<ItemSelectOption, ItemSelectOption.Data> {
      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      protected ItemSelectOption method12() {
         return new ItemSelectOption(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC,
            this.codec,
            this.CCHCIICROHOHHRICIHHHORRRIRHRII == null ? ItemSelectOption.field11 : this.CCHCIICROHOHHRICIHHHORRRIRHRII,
            this.defaultValue == null ? new LinkedHashSet<>() : this.defaultValue,
            this.field8,
            this.ICCRRIRRHHICCRCRRRRRCHROICORHR == null ? var0 -> ItemSelectOption.field12.get(var0).toString() : this.ICCRRIRRHHICCRCRRRRRCHROICORHR
         );
      }
   }
}
