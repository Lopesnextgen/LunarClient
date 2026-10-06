package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator23;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.TreeMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.TreeMapImpl2;
import com.moonsworth.lunar.client.framework.feature.rewind.holograms.Holograms3;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class Highlight<V extends Fishing2<Integer, V> & Fishing<UUID>> {
   private final TreeMapImpl<Range<Integer>, V> field1;
   private Map field2;
   private Runnable field3;

   public Highlight(Nameplate2 var1, Map var2) {
      this.field2 = var2;
      this.field1 = new TreeMapImpl2<>(var1, Comparator.comparing(Range::getMinimum), this);
   }

   public void method1(Range<Integer> var1, V var2) {
      this.field1.method1(() -> {
         this.method2(var1);
         this.field1.put(var1, (V)var2);
      });
   }

   public void method2(Range<Integer> var1) {
      this.field1.method1(() -> {
         this.field1.method5().method4(this::method3, this::method3);
         HashMap var2 = new HashMap();
         HashMap var3 = new HashMap();

         for (Entry var5 : this.field1.entrySet()) {
            Range var6 = (Range)var5.getKey();
            Range var7 = Range.between((Integer)var6.getMinimum(), (Integer)var6.getMaximum() - 1);
            if (var7.isOverlappedBy(var1)) {
               var2.put((Range)var5.getKey(), (Fishing2)var5.getValue());
               if (var1.isAfter((Integer)var6.getMinimum())) {
                  Range var8 = Range.between((Integer)var6.getMinimum(), (Integer)var1.getMinimum());
                  Fishing2 var9 = (Fishing2)var5.getValue();
                  if (!var8.equals(var6)) {
                     var9 = (Fishing2)((Fishing2)var5.getValue()).method1(this.field1.method5(), var6, var8);
                  }

                  var3.put(var8, var9);
                  this.method4((V)var9, var8);
               }

               if (var1.isBefore((Integer)var6.getMaximum())) {
                  Range var10 = Range.between((Integer)var1.getMaximum(), (Integer)var6.getMaximum());
                  Fishing2 var11 = (Fishing2)var5.getValue();
                  if (!var10.equals(var6)) {
                     var11 = (Fishing2)((Fishing2)var5.getValue()).method1(this.field1.method5(), var6, var10);
                  }

                  var3.put(var10, var11);
                  this.method4((V)var11, var10);
               }
            }
         }

         this.field1.method5().method4(() -> var2.forEach(this.field1::method3), () -> this.field1.keySet().removeAll(var2.keySet()));
         this.field1.keySet().removeAll(var2.keySet());
         this.field1.putAll(var3);
         this.method3();
         this.field1.method5().method4(this::method3, this::method3);
      });
   }

   private void method3() {
      if (this.field3 != null) {
         this.field3.run();
      }
   }

   private void method4(V var1, Range<Integer> var2) {
      if (var1 instanceof RewindIterator23 var3) {
         var3.method11().method5(new Highlight2_2((RewindIterator23)var1, var2, var3.method10(), var3.method8()));
      }
   }

   public void method5(Integer var1) {
      this.method2(Range.is(var1));
   }

   public V method6(Integer var1) {
      Entry var2 = this.method7(var1);
      return (V)(var2 == null ? null : var2.getValue());
   }

   public Entry<Range<Integer>, V> method7(Integer var1) {
      Entry var2 = this.field1.floorEntry(Range.is(var1));
      return var2 != null && ((Range)var2.getKey()).contains(var1) ? var2 : null;
   }

   public V method8(Range<Integer> var1) {
      return this.field1.remove(var1);
   }

   public void method9(Range<Integer> var1) {
      boolean var2 = !this.field1.method5().method3();
      if (var2) {
         this.field1.method5().method1();
      }

      this.method2(var1);
      int var3 = (Integer)var1.getMaximum() - (Integer)var1.getMinimum();
      this.method10((Integer)var1.getMinimum(), -var3, true);
      if (var2) {
         this.field1.method5().endBatch();
      }
   }

   public void method10(int var1, int var2, boolean var3) {
      this.field1.method1(() -> {
         HashMap var4 = new HashMap();
         SortedMap var5 = var3 ? this.field1.tailMap(Range.is(var1)) : this.field1.headMap(Range.is(var1));
         var5.forEach((var2xx, var3xx) -> var4.put(Range.between((Integer)var2xx.getMinimum() + var2, (Integer)var2xx.getMaximum() + var2), var3xx));
         HashMap var6 = new HashMap<>(var5);
         this.field1.method5().method4(() -> this.field1.putAll(var6), () -> this.field1.keySet().removeAll(var6.keySet()));
         var5.clear();
         this.field1.putAll(var4);
      });
   }

   @Generated
   public TreeMapImpl<Range<Integer>, V> method11() {
      return this.field1;
   }

   @Generated
   public Map method12() {
      return this.field2;
   }

   @Generated
   public Runnable method13() {
      return this.field3;
   }

   @Generated
   public void method14(Map var1) {
      this.field2 = var1;
   }

   @Generated
   public void method15(Runnable var1) {
      this.field3 = var1;
   }

   public static class Data2 implements JsonDeserializer<Highlight<?>>, JsonSerializer<Highlight<?>> {
      private final Rewind2_3 field1;

      public JsonElement method1(Highlight var1, Type var2, JsonSerializationContext var3) {
         return var3.serialize(var1.field1);
      }

      public Highlight<?> method2(JsonElement var1, Type var2, JsonDeserializationContext var3) {
         Highlight var4 = new Highlight(this.field1.method40(), new HashMap());

         for (Entry var6 : var1.getAsJsonObject().entrySet()) {
            Range var7 = (Range)var3.deserialize(new JsonPrimitive((String)var6.getKey()), Range.class);
            String var8 = ((JsonElement)var6.getValue()).getAsJsonObject().get("type").getAsString();
            Object var9 = var3.deserialize((JsonElement)var6.getValue(), Holograms3.getType(var8));
            if (var9 instanceof RewindIterator var10) {
               var10.setId(UUID.randomUUID());
               var10.method1(var7, this.field1.method38(), this.field1.method40());
            }

            if (var9 instanceof RewindIterator23 var11) {
               var11.method1(var7, this.field1.method41());
            }

            var4.method1(var7, (V)var9);
         }

         return var4;
      }

      @Generated
      public Data2(Rewind2_3 var1) {
         this.field1 = var1;
      }
   }
}
