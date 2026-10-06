package com.moonsworth.lunar.client.config.option.trait;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.Trait;
import com.moonsworth.lunar.client.config.option.trait.MutableTraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitListener;
import com.moonsworth.lunar.client.config.option.trait.TraitSnapshot;

public class MutableTraitContainer implements MutableTraitHost {
   private static final int field1 = 3;
   private final Lightoverlay field2;
   private final Int2ObjectMap<Object> field3;
   private Int2ObjectMap<List<TraitListener<?>>> field4;

   public MutableTraitContainer(Lightoverlay var1) {
      this.field2 = var1;
      this.field3 = new Int2ObjectOpenHashMap(3, 0.75F);
   }

   MutableTraitContainer(Lightoverlay var1, Int2ObjectMap<Object> var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   private MutableTraitContainer(Lightoverlay var1, Int2ObjectMap<Object> var2, Int2ObjectMap<List<TraitListener<?>>> var3) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
   }

   private MutableTraitContainer(Lightoverlay var1, TraitContainer var2, Int2ObjectMap<List<TraitListener<?>>> var3) {
      this.field2 = var1;
      this.field4 = var3;
      int var4 = var2.size();
      this.field3 = (Int2ObjectMap<Object>)(var4 < 8 ? new Int2ObjectArrayMap(var4) : new Int2ObjectOpenHashMap(var4));
      IntIterator var5 = var2.method2().iterator();

      while (var5.hasNext()) {
         int var6 = (Integer)var5.next();
         this.field3.put(var6, var2.get(var6));
      }
   }

   @Override
   public Lightoverlay method1() {
      return this.field2;
   }

   private void method2(boolean var1, TraitType<?> var2) {
      try {
         this.method3(var1, var2 == null ? -1 : var2.getId());
      } catch (Throwable var4) {
         throw var4;
      }
   }

   private void method3(boolean var1, int var2) {
      try {
         IntIterator var3 = new IntArraySet(this.method2()).iterator();

         while (var3.hasNext()) {
            int var4 = var3.nextInt();
            TraitContainer.method8(this.field2, var4, var1x -> this.get(var1x) != null, var2x -> {
               Object var3x = this.field3.remove(var2x);
               if (var3x != null) {
                  this.method14(var4, var3x, null, TraitListener.Type.TRAIT_REMOVED_CONDITIONALLY);
               }
            }, false, var1, var2);
         }
      } catch (Throwable var5) {
         throw var5;
      }
   }

   @Nullable
   @Override
   public <T> T get(int var1) {
      return (T)this.field3.get(var1);
   }

   @Nullable
   @Override
   public <T> T method1(TraitType<T> var1, @Nullable T var2) {
      Object var3 = this.field3.put(var1.getId(), var2);
      this.method13(var1.getId(), var3, var2);
      if (var3 == null == (var2 != null)) {
         this.method2(var3 == null, var1);
      }

      return (T)var3;
   }

   @Nullable
   @Override
   public <T> T set(int var1, @Nullable T var2) {
      Object var3 = this.field3.put(var1, var2);
      this.method13(var1, var3, var2);
      if (var3 == null == (var2 != null)) {
         this.method3(var3 == null, var1);
      }

      return (T)var3;
   }

   @Nullable
   @Override
   public <T> T method4(TraitType<T> var1, Function<TraitType<T>, ? extends @NotNull T> var2) {
      Object var3 = this.field3.get(var1.getId());
      return (T)this.field3.computeIfPresent(var1.getId(), (var4, var5) -> {
         Object var6 = var2.apply(var1);
         this.method14(var1.getId(), var3, var6, TraitListener.Type.TRAIT_SET);
         return var6;
      });
   }

   @Override
   public <T> T method5(TraitType<T> var1, Function<TraitType<T>, ? extends @NotNull T> var2) {
      Object var3 = this.field3.computeIfAbsent(var1.getId(), var3x -> {
         Object var4 = var2.apply(var1);
         this.method14(var1.getId(), null, var4, TraitListener.Type.TRAIT_SET);
         return var4;
      });
      this.method2(true, var1);
      return (T)var3;
   }

   @Nullable
   @Override
   public <T> T method6(TraitType<T> var1, BiFunction<TraitType<T>, ? super @Nullable T, ? extends @Nullable T> var2) {
      Object var3 = this.field3.compute(var1.getId(), (var2x, var3x) -> var2.apply(var1, var3x));
      this.method2(var3 != null, var1);
      return (T)var3;
   }

   @Nullable
   @Override
   public <T> T method7(TraitType<? extends T> var1) {
      Object var2 = this.field3.remove(var1.getId());
      if (var2 != null) {
         this.method14(var1.getId(), var2, null, TraitListener.Type.TRAIT_REMOVED);
         this.method2(false, var1);
      }

      return (T)var2;
   }

   @Override
   public boolean isEmpty() {
      return this.field3.isEmpty();
   }

   @Override
   public void method8(TraitSnapshot var1) {
      ObjectIterator var2 = Reference2ObjectMaps.fastIterable(var1.field2).iterator();

      while (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         Object var4 = ((Optional)var3.getValue()).orElse(null);
         TraitType var5 = (TraitType)var3.getKey();
         if (var4 == null) {
            Object var6 = this.field3.remove(var5.getId());
            if (var6 != null) {
               this.method14(var5.getId(), var6, null, TraitListener.Type.TRAIT_REMOVED);
               this.method2(false, var5);
            }
         } else if (this.field3.put(var5.getId(), var4) == null) {
            this.method14(var5.getId(), null, var4, TraitListener.Type.TRAIT_SET);
            this.method2(true, var5);
         }
      }
   }

   @Override
   public void method9(TraitContainer var1) {
      for (Trait var3 : var1) {
         var3.method1(this);
      }
   }

   @Override
   public <T> void method1(TraitType<T> var1, TraitListener<T> var2) {
      if (this.field4 == null) {
         this.field4 = new Int2ObjectOpenHashMap(3, 0.75F);
      }

      ((List)this.field4.computeIfAbsent(var1.getId(), var0 -> new ArrayList())).add(var2);
   }

   @Override
   public <T> void method2(TraitType<T> var1, TraitListener<T> var2) {
      if (this.field4 != null) {
         List var3 = (List)this.field4.get(var1.getId());
         if (var3 != null) {
            var3.remove(var2);
         }
      }
   }

   private <T> void method13(int var1, T var2, T var3) {
      if (this.field4 != null) {
         TraitListener.Type var4 = var2 == null && var3 != null
            ? TraitListener.Type.TRAIT_SET
            : (var2 != null && var3 != null ? TraitListener.Type.TRAIT_SET : (var2 != null && var3 == null ? TraitListener.Type.TRAIT_REMOVED : null));
         if (var4 != null) {
            this.method14(var1, var2, var3, var4);
         }
      }
   }

   private void method14(int var1, Object var2, Object var3, TraitListener.Type var4) {
      if (this.field4 != null) {
         List var5 = (List)this.field4.get(var1);
         if (var5 != null) {
            for (int var6 = var5.size() - 1; var6 >= 0; var6--) {
               ((TraitListener)var5.get(var6)).invoke(var4, var2, var3);
            }
         }
      }
   }

   public <T> int method15(TraitType<T> var1) {
      if (this.field4 == null) {
         return 0;
      }

      List var2 = (List)this.field4.get(var1.getId());
      return var2 == null ? 0 : var2.size();
   }

   @Override
   public IntSet method2() {
      return this.field3.keySet();
   }

   @Override
   public Collection<?> values() {
      return this.field3.values();
   }

   @NotNull
   @Override
   public Iterator<Trait<?>> iterator() {
      ArrayList var1 = new ArrayList(this.field3.size());
      ObjectIterator var2 = Int2ObjectMaps.fastIterable(this.field3).iterator();

      while (var2.hasNext()) {
         it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry var3 = (it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry)var2.next();
         var1.add(Trait.method3(var3.getIntKey(), var3.getValue()));
      }

      return var1.iterator();
   }

   @Override
   public int size() {
      return this.field3.size();
   }

   @Override
   public MutableTraitHost method3() {
      Object var1 = this.field3.size() < 8 ? new Int2ObjectArrayMap(this.field3) : new Int2ObjectOpenHashMap(this.field3);
      Object var2 = this.field4 == null ? null : (this.field4.size() < 8 ? new Int2ObjectArrayMap(this.field4) : new Int2ObjectOpenHashMap(this.field4));
      return new MutableTraitContainer(this.field2, (Int2ObjectMap<Object>)var1, (Int2ObjectMap<List<TraitListener<?>>>)var2);
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1 ? true : var1 instanceof MutableTraitContainer var2 && this.field3.equals(var2.field3);
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode();
   }

   @Override
   public String toString() {
      return "{" + this.stream().map(Trait::toString).collect(Collectors.joining(", ")) + "}";
   }
}
