package com.moonsworth.lunar.client.config.option.trait;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMaps;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class TraitSnapshot {
   public static final TraitSnapshot field1 = new TraitSnapshot(Reference2ObjectMaps.emptyMap());
   final Reference2ObjectMap<TraitType<?>, Optional<?>> field2;

   TraitSnapshot(Reference2ObjectMap<TraitType<?>, Optional<?>> map) {
      this.field2 = map;
   }

   public static TraitSnapshot.Data method1() {
      return new TraitSnapshot.Data();
   }

   @Nullable
   public <T> Optional<T> method2(TraitType<T> lightoverlay91) {
      return (Optional<T>)this.field2.get(lightoverlay91);
   }

   public Set<Entry<TraitType<?>, Optional<?>>> entrySet() {
      return this.field2.entrySet();
   }

   public int size() {
      return this.field2.size();
   }

   public TraitSnapshot method3(Predicate<TraitType<?>> predicate1) {
      if (this.isEmpty()) {
         return field1;
      }

      Reference2ObjectArrayMap reference2objectarraymap2 = new Reference2ObjectArrayMap(this.field2);
      reference2objectarraymap2.keySet().removeIf(predicate1);
      return reference2objectarraymap2.isEmpty() ? field1 : new TraitSnapshot(reference2objectarraymap2);
   }

   public boolean isEmpty() {
      return this.field2.isEmpty();
   }

   @Override
   public boolean equals(Object object) {
      return this == object ? true : object instanceof TraitSnapshot lightoverlay42 && this.field2.equals(lightoverlay42.field2);
   }

   @Override
   public int hashCode() {
      return this.field2.hashCode();
   }

   @Override
   public String toString() {
      return method4(this.field2);
   }

   static String method4(Reference2ObjectMap<TraitType<?>, Optional<?>> map) {
      StringBuilder builder1 = new StringBuilder();
      builder1.append('{');
      boolean flag2 = true;
      ObjectIterator objectiterator3 = Reference2ObjectMaps.fastIterable(map).iterator();

      while (objectiterator3.hasNext()) {
         Entry entry4 = (Entry)objectiterator3.next();
         if (flag2) {
            flag2 = false;
         } else {
            builder1.append(", ");
         }

         Optional optional5 = (Optional)entry4.getValue();
         if (optional5.isPresent()) {
            builder1.append(entry4.getKey());
            builder1.append(">");
            builder1.append(optional5.get());
         } else {
            builder1.append("!");
            builder1.append(entry4.getKey());
         }
      }

      builder1.append('}');
      return builder1.toString();
   }

   public static class Data {
      private final Reference2ObjectMap<TraitType<?>, Optional<?>> field1 = new Reference2ObjectArrayMap();

      public <T> TraitSnapshot.Data method1(TraitType<T> lightoverlay91, T t) {
         this.field1.put(lightoverlay91, Optional.of(t));
         return this;
      }

      public <T> TraitSnapshot.Data method2(TraitType<T> lightoverlay91) {
         this.field1.put(lightoverlay91, Optional.empty());
         return this;
      }

      public TraitSnapshot method3() {
         return this.field1.isEmpty() ? TraitSnapshot.field1 : new TraitSnapshot(this.field1);
      }

      @Generated
      public Data() {
      }
   }
}
