package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.TreeMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump18;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump73Type;
import java.util.Objects;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public abstract class Fishing2Loader<T extends ClientOption, V> implements Fishing2<Integer, Fishing2Loader<T, V>> {
   protected final T field1;
   protected final T field2;
   private final V field3;
   @SerializedName("values")
   private final TreeMapImpl<Integer, Fishing2Loader.Data<V>> field4;
   @SerializedName("showInTimeline")
   private boolean field5 = this.method24();
   private Entry<Integer, Fishing2Loader.Data<V>> field6 = null;
   private Entry<Integer, Fishing2Loader.Data<V>> field7 = null;
   private Entry<Integer, Fishing2Loader.Data<V>> field8 = null;
   private Entry<Integer, Fishing2Loader.Data<V>> field9 = null;
   private Integer field10 = null;
   private Range<Integer> field11 = null;
   private boolean field12 = false;

   public Fishing2Loader(Nameplate2 var1, T var2) {
      this.field2 = (T)var2;
      this.field1 = (T)var2.method20();
      this.field3 = this.method20((T)var2);
      this.field4 = new TreeMapImpl<>(var1);
      this.method5(null, Integer.MIN_VALUE, true);
      this.field1.method9(this::method1);
   }

   protected void method1() {
      if (this.field10 != null && this.field11 != null) {
         this.method5(this.field11, this.field10, false);
         this.method22(this.field2, this.method21(this.field1));
         ThreadModuleDump63.method3().bridge$schedule(() -> ClientEventBus.method29().method12(EventEverySecond.class, EventEverySecond::new));
      }
   }

   public Fishing2Loader<T, V> method2() {
      this.field12 = true;
      return this;
   }

   public void reset() {
      this.field4.method1(() -> {
         this.field4.clear();
         this.field1.reset();
         this.method3();
         this.method5(null, Integer.MIN_VALUE, true);
         this.method8();
      });
   }

   public void method3() {
      this.method22(this.field2, this.field3);
   }

   public boolean method4() {
      return !this.field4.isEmpty() && !this.field4.containsKey(Integer.MIN_VALUE) || !this.field1.isDefault();
   }

   public void method5(Range<Integer> var1, int var2, boolean var3) {
      this.field4.method1(() -> {
         if (!this.field4.isEmpty() && (this.field4.size() != 1 || !this.field4.containsKey(Integer.MIN_VALUE))) {
            if (!var3) {
               if (var2 < 0 || var2 > (Integer)var1.getMaximum() - (Integer)var1.getMinimum()) {
                  return;
               }

               this.method7(var2, this.method21(this.field1));
            }
         } else {
            this.method7(Integer.MIN_VALUE, this.method21(this.field1));
         }

         this.method8();
      });
   }

   public void method6(Range<Integer> var1, int var2) {
      if (var2 == Integer.MIN_VALUE || var2 >= 0 && var2 <= (Integer)var1.getMaximum() - (Integer)var1.getMinimum()) {
         this.field4.method1(() -> {
            if (this.method9(var2)) {
               this.field4.remove(var2);
            } else {
               this.field4.remove(Integer.MIN_VALUE);
               this.method7(var2, this.method21(this.field1));
            }

            this.method8();
         });
      }
   }

   private void method7(int var1, V var2) {
      Object var3 = this.method23((V)var2);
      this.field4.method1(() -> {
         this.field4.method5().method4(this::method8, this::method8);
         if (var1 != Integer.MIN_VALUE) {
            this.field4.remove(Integer.MIN_VALUE);
         }

         Fishing2Loader.Data var3x = this.field4.get(var1);
         if (var3x != null) {
            Object var4 = var3x.value;
            this.field4.method5().method4(() -> var3x.value = (V)var4, () -> var3x.value = (V)var3);
            var3x.value = (V)var3;
         } else {
            Fishing2Loader.Data var6 = new Fishing2Loader.Data(this.method25());
            Object var5 = this.method23(this.method21(this.field1));
            this.field4.method5().method4(() -> var6.value = (V)var5, () -> var6.value = (V)var3);
            var6.value = (V)var3;
            this.field4.put(var1, var6);
         }

         this.field4.method5().method4(this::method8, this::method8);
      });
   }

   public void method8() {
      this.field6 = null;
      this.field7 = null;
      this.field8 = null;
      this.field9 = null;
      this.field10 = null;
   }

   public boolean method9(int var1) {
      return this.field4.containsKey(var1);
   }

   public abstract V method10(ThreadModuleDump18 var1, V var2, V var3, V var4, V var5, float var6);

   public void method11(int var1) {
      if (this.field4.isEmpty()) {
         Fishing2Loader.Data var2 = new Fishing2Loader.Data(this.method25());
         var2.value = this.method23(this.method21(this.field1));
         this.field4.method3(Integer.MIN_VALUE, var2);
      } else if (this.field4.size() > 1 && this.field4.containsKey(Integer.MIN_VALUE)) {
         this.field4.method5(Integer.MIN_VALUE);
      }

      if (this.field7 == null || this.field7.getKey() > var1 || this.field8 == null || this.field8.getKey() < var1) {
         this.field7 = this.field4.floorEntry(var1);
         this.field8 = this.field4.ceilingEntry(var1);
         if (this.field7 != null) {
            this.field6 = this.field4.lowerEntry(this.field7.getKey());
            if (this.field6 == null) {
               this.field6 = this.field7;
            }
         } else {
            this.field6 = null;
         }

         if (this.field8 != null) {
            this.field9 = this.field4.higherEntry(this.field8.getKey());
            if (this.field9 == null) {
               this.field9 = this.field8;
            }
         } else {
            this.field9 = null;
         }
      }
   }

   public void method12(ThreadModuleDump6<Nameplate4> var1, int var2) {
      Object var3 = this.getAt(var2);
      if (var3 != null) {
         boolean var4 = this.field10 == null || var2 != this.field10;
         this.method15((V)var3, var4);
      }
   }

   public V getAt(int var1) {
      this.method11(var1);
      if (this.field7 == null && this.field8 == null) {
         return null;
      }

      if (this.field7 != null && (this.field7.getKey() != Integer.MIN_VALUE || this.field8 == null)) {
         if (this.field8 == null) {
            return this.method23(this.field7.getValue().value);
         }

         float var2;
         if (this.field7.getKey().equals(this.field8.getKey())) {
            var2 = 1.0F;
         } else {
            var2 = (float)(var1 - this.field7.getKey()) / (this.field8.getKey() - this.field7.getKey());
         }

         return this.method10(
            this.field8.getValue().method1(this.field7.getValue().field3),
            this.field6.getValue().value,
            this.field7.getValue().value,
            this.field8.getValue().value,
            this.field9.getValue().value,
            var2
         );
      } else {
         return this.method23(this.field8.getValue().value);
      }
   }

   public Integer method13(int var1) {
      if (this.field7 == null || this.field7.getKey() == Integer.MIN_VALUE) {
         return null;
      } else {
         return var1 > this.field7.getKey() ? this.field7.getKey() : this.field4.lowerKey(var1);
      }
   }

   public Integer method14(int var1) {
      if (this.field8 == null) {
         return null;
      } else {
         return var1 < this.field8.getKey() ? this.field8.getKey() : this.field4.higherKey(var1);
      }
   }

   private void method15(V var1, boolean var2) {
      this.field10 = null;
      this.field11 = null;
      if (!Objects.deepEquals(var1, this.field2.get()) || !Objects.deepEquals(var1, this.field1.get())) {
         if (var2) {
            this.method22(this.field1, (V)var1);
         }

         this.method22(this.field2, (V)var1);
      }
   }

   public Fishing2Loader<T, V> method16(Fishing2Loader<T, V> var1, Nameplate2 var2, Range<Integer> var3, Range<Integer> var4) {
      var1.field12 = this.field12;
      var1.field5 = this.field5;
      Range var5 = Range.between((Integer)var4.getMinimum() - (Integer)var3.getMinimum(), (Integer)var4.getMaximum() - (Integer)var3.getMinimum());

      for (Entry var7 : this.field4.entrySet()) {
         if (var5.contains((Integer)var7.getKey())) {
            Fishing2Loader.Data var8 = new Fishing2Loader.Data(((Fishing2Loader.Data)var7.getValue()).field1);
            var8.field2 = ((Fishing2Loader.Data)var7.getValue()).field2;
            var8.field3 = ((Fishing2Loader.Data)var7.getValue()).field3;
            var8.value = this.method23(((Fishing2Loader.Data)var7.getValue()).value);
            var1.method27().put((Integer)var7.getKey() - (Integer)var5.getMinimum(), var8);
         }
      }

      return var1;
   }

   public void method17(JsonObject var1) {
      if (this.method4()) {
         com.moonsworth.lunar.client.config.option.OptionDisplay var2 = (com.moonsworth.lunar.client.config.option.OptionDisplay)this.field1
            .method1(OptionTraits.field2);
         if (var2 != null && var2.method1() != DriverFieldTypeLegacy.CATEGORY) {
            JsonObject var3 = new JsonObject();
            var1.add("values", var3);
            var1.addProperty("showInTimeline", this.field5);

            for (Entry var5 : this.field4.entrySet()) {
               JsonObject var6 = new JsonObject();
               this.method18(var6, (Fishing2Loader.Data<V>)var5.getValue());
               var3.add(var5.getKey() == Integer.MIN_VALUE ? "all" : ((Integer)var5.getKey()).toString(), var6);
            }
         }
      }
   }

   public void method18(JsonObject var1, Fishing2Loader.Data<V> var2) {
      this.field10 = null;
      this.field11 = null;
      this.method22(this.field1, var2.value);
      this.field1.method1(var1);
      if (!(this instanceof Fishing2Loader2)) {
         if (var2.field1 != this.method25()) {
            var1.addProperty("interpolation", var2.field1.getId());
         }

         if (!var2.field2) {
            var1.addProperty("in", false);
         }

         if (!var2.field3) {
            var1.addProperty("out", false);
         }
      }
   }

   public void load(JsonObject var1) {
      JsonObject var2 = var1.getAsJsonObject("values");
      this.field5 = var1.get("showInTimeline").getAsBoolean();

      for (Entry var4 : var2.entrySet()) {
         int var5 = ((String)var4.getKey()).equals("all") ? Integer.MIN_VALUE : Integer.parseInt((String)var4.getKey());
         JsonObject var6 = (JsonObject)var4.getValue();
         Fishing2Loader.Data var7 = new Fishing2Loader.Data(this.method25());
         this.method19(var6, var7);
         this.field4.method3(var5, var7);
      }

      if (this.field4.size() > 1) {
         this.field4.method5(Integer.MIN_VALUE);
      }
   }

   public void method19(JsonObject var1, Fishing2Loader.Data<V> var2) {
      this.field10 = null;
      this.field11 = null;
      this.field1.load(var1);
      var2.value = this.method23(this.method21(this.field1));
      if (!(this instanceof Fishing2Loader2)) {
         var2.field1 = var1.has("interpolation") ? ThreadModuleDump73Type.get(var1.get("interpolation").getAsString()) : this.method25();
         var2.field2 = !var1.has("in") || var1.get("in").getAsBoolean();
         var2.field3 = !var1.has("out") || var1.get("out").getAsBoolean();
         if (this.field12 && !var2.field1.isSupportsAverage()) {
            var2.field1 = this.method25();
         }
      }
   }

   protected V method20(T var1) {
      return (V)var1.getDefaultValue();
   }

   public V method21(T var1) {
      return (V)var1.get();
   }

   public void method22(T var1, V var2) {
      var1.method10(var2);
   }

   protected V method23(V var1) {
      return (V)var1;
   }

   protected boolean method24() {
      return true;
   }

   protected ThreadModuleDump73Type method25() {
      return ThreadModuleDump73Type.CUBIC;
   }

   public abstract String type();

   @Generated
   public T getOption() {
      return this.field1;
   }

   @Generated
   public T method26() {
      return this.field2;
   }

   @Generated
   public TreeMapImpl<Integer, Fishing2Loader.Data<V>> method27() {
      return this.field4;
   }

   @Generated
   public boolean method28() {
      return this.field5;
   }

   @Generated
   public void method29(boolean var1) {
      this.field5 = var1;
   }

   @Generated
   public void method30(Integer var1) {
      this.field10 = var1;
   }

   @Generated
   public Integer method31() {
      return this.field10;
   }

   @Generated
   public void method32(Range<Integer> var1) {
      this.field11 = var1;
   }

   @Annotation7
   public static class Data<V> {
      @SerializedName("interpolation")
      private ThreadModuleDump73Type field1;
      @SerializedName("in")
      private boolean field2 = true;
      @SerializedName("out")
      private boolean field3 = true;
      private V value;

      public Data(ThreadModuleDump73Type var1) {
         this.field1 = var1;
      }

      public ThreadModuleDump18 method1(boolean var1) {
         return this.field1.get(var1, this.field3);
      }

      @Generated
      public ThreadModuleDump73Type method2() {
         return this.field1;
      }

      @Generated
      public boolean method3() {
         return this.field2;
      }

      @Generated
      public boolean method4() {
         return this.field3;
      }

      @Generated
      public V getValue() {
         return this.value;
      }

      @Generated
      public void method5(ThreadModuleDump73Type var1) {
         this.field1 = var1;
      }

      @Generated
      public void method6(boolean var1) {
         this.field2 = var1;
      }

      @Generated
      public void method7(boolean var1) {
         this.field3 = var1;
      }

      @Generated
      public void setValue(V var1) {
         this.value = (V)var1;
      }
   }
}
