package com.moonsworth.lunar.client.config.option.trait;

import com.google.common.collect.Iterators;
import com.moonsworth.lunar.client.util.ThreadModuleDump44;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntConsumer;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntPredicate;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMaps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterators;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.TraitReader;
import com.moonsworth.lunar.client.config.option.trait.Trait;
import com.moonsworth.lunar.client.config.option.trait.MutableTraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitSnapshot;
import com.moonsworth.lunar.client.config.option.trait.BuilderTraitType;

public interface TraitContainer extends TraitReader, Iterable<Trait<?>> {
   Lightoverlay method1();

   IntSet method2();

   Collection<?> values();

   @Override
   default boolean method2(TraitType<?> var1) {
      return this.method1(var1) != null;
   }

   @Nullable
   <T> T get(int var1);

   @Nullable
   @Override
   default <T> T method1(TraitType<T> var1) {
      return this.get(var1.getId());
   }

   @Nullable
   default <T> Trait<T> method5(int var1) {
      Object var2 = this.get(var1);
      return var2 != null ? new Trait<>(var1, (T)var2) : null;
   }

   @NotNull
   @Override
   default <T> T method4(TraitType<T> var1) {
      return this.method1(var1);
   }

   @NotNull
   @Override
   default Iterator<Trait<?>> iterator() {
      return Iterators.transform(this.method2().iterator(), var1 -> Objects.requireNonNull(this.method5(var1)));
   }

   default Stream<Trait<?>> stream() {
      return StreamSupport.stream(Spliterators.spliterator(this.iterator(), this.size(), 1345), false);
   }

   default int size() {
      return this.method2().size();
   }

   default boolean isEmpty() {
      return this.size() == 0;
   }

   default TraitContainer method7(final IntPredicate var1) {
      return new TraitContainer() {
         @Nullable
         @Override
         public <T> T get(int var1x) {
            return var1.test(var1x) ? TraitContainer.this.get(var1x) : null;
         }

         @Override
         public Lightoverlay method1() {
            return TraitContainer.this.method1();
         }

         @Override
         public IntSet method2() {
            IntArraySet var1x = new IntArraySet();
            IntIterator var2 = TraitContainer.this.method2().iterator();

            while (var2.hasNext()) {
               int var3 = (Integer)var2.next();
               if (var1.test(var3)) {
                  var1x.add(var3);
               }
            }

            return var1x;
         }

         @Override
         public Collection<?> values() {
            ArrayList var1x = new ArrayList();
            Iterator var2 = TraitContainer.this.iterator();
            if (var2.hasNext()) {
               Trait var3 = (Trait)var2.next();
               if (var1.test(var3.type())) {
                  var1x.add(var3.value());
               }
            }

            return var1x;
         }
      };
   }

   static void method8(Lightoverlay var0, int var1, IntPredicate var2, IntConsumer var3, boolean var4, boolean var5, int var6) {
      try {
         Map var7 = var0.method4(var1);
         if (var7 != null) {
            for (Entry var9 : var7.entrySet()) {
               switch ((LightoverlayType)var9.getKey()) {
                  case CONFLICTS:
                     for (int var22 : (int[])var9.getValue()) {
                        if (var2.test(var22)) {
                           throw new LightoverlayException.Data2(var0.method2(var1), var0.method2(var22));
                        }
                     }
                     break;
                  case REQUIRES:
                     if (var4 || !var5 || var6 != -1 && var1 == var6) {
                        for (int var21 : (int[])var9.getValue()) {
                           if (!var2.test(var21)) {
                              throw new LightoverlayException.Data(var0.method2(var1), var0.method2(var21));
                           }
                        }
                     }
                     break;
                  case REMOVE:
                     if (var4 || var5) {
                        for (int var13 : (int[])var9.getValue()) {
                           var3.accept(var13);
                        }
                     }
               }
            }
         }
      } catch (Throwable var14) {
         throw var14;
      }
   }

   static void method9(Lightoverlay var0, Int2ObjectMap<?> var1) {
      try {
         IntIterator var2 = new IntArraySet(var1.keySet()).iterator();

         while (var2.hasNext()) {
            int var3 = (Integer)var2.next();
            method8(var0, var3, var1::containsKey, var1::remove, true, true, -1);
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   static TraitContainer.Data method10() {
      return new TraitContainer.Data();
   }

   class Data {
      private final Int2ObjectMap<Supplier<?>> field1 = new Int2ObjectOpenHashMap();

      public <T> TraitContainer.Data method1(TraitType<T> var1, @Nullable Supplier<T> var2) {
         this.method4(var1, var2);
         return this;
      }

      public <T> TraitContainer.Data method2(TraitType<T> var1, ThreadModuleDump44<T> var2) {
         this.method4(var1, var2::build);
         return this;
      }

      public <T, B extends ThreadModuleDump44<T>> TraitContainer.Data method3(BuilderTraitType<T, B> var1, Consumer<B> var2) {
         ThreadModuleDump44 var3 = var1.method1();
         var2.accept(var3);
         this.method4(var1, var3::build);
         return this;
      }

      <T> void method4(TraitType<T> var1, @Nullable Supplier<?> var2) {
         this.method5(var1.getId(), var2);
      }

      <T> void method5(int var1, @Nullable Supplier<?> var2) {
         if (var2 != null) {
            this.field1.put(var1, var2);
         } else {
            this.field1.remove(var1);
         }
      }

      public TraitContainer.Data method6(TraitContainer var1) {
         for (Trait var3 : var1) {
            this.field1.put(var3.type(), var3::value);
         }

         return this;
      }

      public TraitContainer.Data method7(TraitSnapshot var1) {
         ObjectIterator var2 = Reference2ObjectMaps.fastIterable(var1.field2).iterator();

         while (var2.hasNext()) {
            it.unimi.dsi.fastutil.objects.Reference2ObjectMap.Entry var3 = (it.unimi.dsi.fastutil.objects.Reference2ObjectMap.Entry)var2.next();
            ((Optional)var3.getValue()).ifPresent(var2x -> this.field1.put(((TraitType)var3.getKey()).getId(), (Supplier<>)() -> var2x));
         }

         return this;
      }

      public TraitContainer method8(Lightoverlay var1) {
         Int2ObjectMap var2 = this.method10(var1);
         Object var3 = var2.isEmpty() ? Int2ObjectMaps.emptyMap() : (var2.size() < 8 ? new Int2ObjectArrayMap(var2) : new Int2ObjectOpenHashMap(var2));
         return new TraitContainer.Data.Data(var1, (Int2ObjectMap<Object>)var3);
      }

      public MutableTraitHost method9(Lightoverlay var1) {
         Int2ObjectMap var2 = this.method10(var1);
         Object var3 = var2.isEmpty() ? new Int2ObjectArrayMap(2) : (var2.size() < 8 ? new Int2ObjectArrayMap(var2) : new Int2ObjectOpenHashMap(var2));
         return new MutableTraitContainer(var1, (Int2ObjectMap<Object>)var3);
      }

      private Int2ObjectMap<Object> method10(Lightoverlay var1) {
         TraitContainer.method9(var1, this.field1);
         Int2ObjectArrayMap var2 = new Int2ObjectArrayMap(this.field1.size());
         ObjectIterator var3 = this.field1.int2ObjectEntrySet().iterator();

         while (var3.hasNext()) {
            it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry var4 = (it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry)var3.next();
            var2.put(var4.getIntKey(), ((Supplier)var4.getValue()).get());
         }

         return var2;
      }

      @Generated
      private Data() {
      }

      class Data implements TraitContainer {
         private final Lightoverlay field1;
         private final Int2ObjectMap<Object> field2;

         Data(Lightoverlay var1, Int2ObjectMap<Object> var2) {
            this.field1 = var1;
            this.field2 = var2;
         }

         @Nullable
         @Override
         public <T> T get(int var1) {
            return (T)this.field2.get(var1);
         }

         @Override
         public boolean method2(TraitType<?> var1) {
            return this.field2.containsKey(var1.getId());
         }

         @Override
         public IntSet method2() {
            return this.field2.keySet();
         }

         @Override
         public Collection<?> values() {
            return this.field2.values();
         }

         @NotNull
         @Override
         public Iterator<Trait<?>> iterator() {
            return Iterators.transform(Int2ObjectMaps.fastIterator(this.field2), Trait::method2);
         }

         @Override
         public int size() {
            return this.field2.size();
         }

         @NotNull
         @Override
         public String toString() {
            return this.field2.toString();
         }

         @Override
         public Lightoverlay method1() {
            return this.field1;
         }

         public Int2ObjectMap<Object> method4() {
            return this.field2;
         }
      }
   }
}
