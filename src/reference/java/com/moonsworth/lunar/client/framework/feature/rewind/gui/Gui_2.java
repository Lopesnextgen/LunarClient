package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HashMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.Function;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public abstract class Gui_2<T extends RewindIterator<T>> {
   @SerializedName("id")
   private final UUID field1 = UUID.randomUUID();
   @SerializedName("layers")
   private final Highlight<T> field2;
   @SerializedName("type")
   private final String field3 = this.type();
   @SerializedName("showKeyframes")
   private boolean field4 = true;
   @SerializedName("enabled")
   private boolean enabled = true;

   public Gui_2(Nameplate2 var1, HashMapImpl var2) {
      this.field2 = new Highlight<>(var1, var2);
      this.field2.method15(() -> this.method3(null));
   }

   public Entry<Range<Integer>, T> method1(int var1) {
      return this.field2.method7(var1);
   }

   public boolean method2(ThreadModuleDump6<Nameplate4> var1, int var2) {
      if (!this.enabled) {
         return false;
      } else {
         Entry var3 = this.method1(var2);
         if (var3 != null) {
            ((RewindIterator)var3.getValue()).method2(var1, (Range<Integer>)var3.getKey(), var2);
            return true;
         } else {
            return false;
         }
      }
   }

   public void method3(ThreadModuleDump6<Nameplate4> var1) {
      for (Entry var3 : new HashMap<>(this.field2.method11()).entrySet()) {
         Range var4 = (Range)var3.getKey();
         RewindIterator var5 = (RewindIterator)var3.getValue();
         int var6 = var5.method4(var4);
         if (Math.abs(var6) > 1) {
            Highlight_3 var7 = var1 == null ? null : ((Nameplate4)var1.get()).method4();
            int var8 = var7 == null ? 0 : var7.method15();
            var8 = this.method4(var4, (T)var5, var6, var8);
            if (var7 != null) {
               var7.method8(var8);
            }
         } else {
            int var9 = (Integer)var4.getMaximum() - (Integer)var4.getMinimum();
            var5.method26(Math.max(0, var9 - var5.method5()));
         }
      }
   }

   private int method4(Range<Integer> var1, T var2, int var3, int var4) {
      int var5 = (Integer)var1.getMinimum();
      int var6 = (Integer)var1.getMaximum() - var5;
      int var7 = (Integer)var1.getMaximum() + var3;
      int var8 = Math.max(1, var6 - var2.method20());
      int var9 = Math.max(1, var2.method5());
      double var10 = (double)var9 / var8;
      this.field2.method8(var1);
      this.field2.method10((Integer)var1.getMaximum(), var3, true);
      Range var12 = Range.between(var5, var7);
      this.field2.method1(var12, (T)var2);
      var2.method26(Math.max(0, var7 - var5 - var9));
      Function var13 = var2x -> (int)Math.round(var2x.intValue() * var10);
      Range var14 = Range.between(0, var6);
      var2.method18()
         .values()
         .stream()
         .map(Fishing2Iterator::method12)
         .map(Map::values)
         .flatMap(Collection::stream)
         .map(Fishing2Loader::method27)
         .forEach(var2x -> {
            HashMap var3x = new HashMap();

            for (Integer var5x : new ArrayList(var2x.keySet())) {
               if (var14.contains(var5x)) {
                  var3x.put((Integer)var13.apply(var5x), (Fishing2Loader.Data)var2x.remove(var5x));
               }
            }

            for (Entry var7x : var3x.entrySet()) {
               var2x.put((Integer)var7x.getKey(), (Fishing2Loader.Data)var7x.getValue());
            }
         });
      if (var1.contains(var4)) {
         var4 = var5 + (Integer)var13.apply(var4 - var5);
      }

      return var4;
   }

   public abstract String type();

   @Generated
   public UUID getId() {
      return this.field1;
   }

   @Generated
   public Highlight<T> method5() {
      return this.field2;
   }

   @Generated
   public String getType() {
      return this.field3;
   }

   @Generated
   public boolean method6() {
      return this.field4;
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public void method7(boolean var1) {
      this.field4 = var1;
   }

   @Generated
   public void setEnabled(boolean var1) {
      this.enabled = var1;
   }
}
