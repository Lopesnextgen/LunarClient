package com.moonsworth.lunar.client.config.option.trait;

import com.google.common.collect.Maps;
import com.moonsworth.lunar.client.util.ThreadModuleDump44;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.BuilderTraitType;

public class TraitRegistry {
   private final Map<String, TraitType<?>> field1 = new HashMap<>();
   private final Int2ObjectMap<Map<LightoverlayType, int[]>> field2 = new Int2ObjectOpenHashMap();

   public int size() {
      return this.field1.size();
   }

   public Set<Entry<String, TraitType<?>>> entrySet() {
      return this.field1.entrySet();
   }

   public <T> TraitType<T> method1(String var1) {
      if (this.field1.containsKey(var1)) {
         throw new IllegalArgumentException("Trait type with id " + var1 + " already exists!");
      }

      TraitType var2 = new TraitType(this.field1.size());
      this.field1.put(var1, var2);
      return var2;
   }

   public <T> TraitType<T> method2(String var1, Consumer<TraitRegistry.Data> var2) {
      if (this.field1.containsKey(var1)) {
         throw new IllegalArgumentException("Trait type with id " + var1 + " already exists!");
      }

      TraitRegistry.Data var3 = new TraitRegistry.Data();
      var2.accept(var3);
      TraitType var4 = new TraitType(this.field1.size());
      this.field2.put(var4.getId(), var3.method5());
      this.field1.put(var1, var4);
      return var4;
   }

   public <T, B extends ThreadModuleDump44<T>> BuilderTraitType<T, B> method3(String var1, Supplier<B> var2) {
      if (this.field1.containsKey(var1)) {
         throw new IllegalArgumentException("Trait type with id " + var1 + " already exists!");
      }

      BuilderTraitType var3 = new BuilderTraitType(this.field1.size(), var2);
      this.field1.put(var1, var3);
      return var3;
   }

   public <T, B extends ThreadModuleDump44<T>> BuilderTraitType<T, B> method4(String var1, Supplier<B> var2, Consumer<TraitRegistry.Data> var3) {
      if (this.field1.containsKey(var1)) {
         throw new IllegalArgumentException("Trait type with id " + var1 + " already exists!");
      }

      TraitRegistry.Data var4 = new TraitRegistry.Data();
      var3.accept(var4);
      BuilderTraitType var5 = new BuilderTraitType(this.field1.size(), var2);
      this.field2.put(var5.getId(), var4.method5());
      this.field1.put(var1, var5);
      return var5;
   }

   @Generated
   public Int2ObjectMap<Map<LightoverlayType, int[]>> method5() {
      return this.field2;
   }

   public static class Data {
      private final EnumMap<LightoverlayType, List<TraitType<?>>> field1 = new EnumMap<>(LightoverlayType.class);

      @Contract("_ -> this")
      public TraitRegistry.Data method1(TraitType<?>... var1) {
         this.method4(LightoverlayType.CONFLICTS, var1);
         return this;
      }

      @Contract("_ -> this")
      public TraitRegistry.Data method2(TraitType<?>... var1) {
         this.method4(LightoverlayType.REQUIRES, var1);
         return this;
      }

      @Contract("_ -> this")
      public TraitRegistry.Data method3(TraitType<?>... var1) {
         this.method4(LightoverlayType.REMOVE, var1);
         return this;
      }

      private void method4(LightoverlayType var1, TraitType<?>... var2) {
         this.field1.computeIfAbsent(var1, var0 -> new ArrayList<>()).addAll(List.of(var2));
      }

      final Map<LightoverlayType, int[]> method5() {
         EnumMap var1 = new EnumMap<>(LightoverlayType.class);

         for (Entry var3 : this.field1.entrySet()) {
            var1.put((LightoverlayType)var3.getKey(), ((List)var3.getValue()).stream().mapToInt(TraitType::getId).toArray());
         }

         return Maps.immutableEnumMap(var1);
      }
   }
}
