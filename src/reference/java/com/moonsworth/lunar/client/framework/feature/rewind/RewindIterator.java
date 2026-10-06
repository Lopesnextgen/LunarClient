package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.LinkedHashMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.holograms.Holograms;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public abstract class RewindIterator<T extends RewindIterator<T>> implements Fishing<UUID>, Fishing2<Integer, T> {
   private UUID id = UUID.randomUUID();
   @SerializedName("link")
   private UUID field1 = null;
   @SerializedName("properties")
   private LinkedHashMapImpl field2;
   @SerializedName("type")
   private final String field3 = this.type();
   @SerializedName("initialDuration")
   private int field4 = -1;
   @SerializedName("freezeDuration")
   private int field5 = 0;
   private Double field6 = null;

   public RewindIterator(Nameplate2 var1) {
      this.field2 = new LinkedHashMapImpl(var1);
   }

   public void method1(Range<Integer> var1, Holograms var2, Nameplate2 var3) {
      if (this.field4 < 0) {
         this.field4 = (Integer)var1.getMaximum() - (Integer)var1.getMinimum();
      }

      for (String var5 : this.method15()) {
         this.field2.putIfAbsent(var5, var2.method2(var3, var5));
      }
   }

   public void method2(ThreadModuleDump6<Nameplate4> var1, Range<Integer> var2, int var3) {
      if (this.field4 < 0) {
         this.field4 = (int)(((Integer)var2.getMaximum() - (Integer)var2.getMinimum()) * this.method7());
      }

      for (Fishing2Iterator var5 : this.field2.values()) {
         var5.method6(var1, var3 - (Integer)var2.getMinimum());
      }
   }

   protected T method3(T var1, Nameplate2 var2, Range<Integer> var3, Range<Integer> var4) {
      Highlight_3 var5 = Coordinates.getTimeline();
      if (var5 != null) {
         Highlight2 var6 = var5.method11().method6();
         long var7 = (Integer)var3.getMinimum() + (Integer)var4.getMinimum();
         if (this.field1 != null) {
            var7 += this.field1.getMostSignificantBits() + this.field1.getLeastSignificantBits();
         }

         Random var9 = new Random(var7);
         byte[] var10 = new byte[16];
         var9.nextBytes(var10);
         var10[6] = (byte)(var10[6] & 15);
         var10[6] = (byte)(var10[6] | 64);
         var10[8] = (byte)(var10[8] & 63);
         var10[8] = (byte)(var10[8] | -128);
         long var11 = 0L;
         long var13 = 0L;

         for (int var15 = 0; var15 < 8; var15++) {
            var11 = var11 << 8 | var10[var15] & 0xFF;
         }

         for (int var20 = 8; var20 < 16; var20++) {
            var13 = var13 << 8 | var10[var20] & 0xFF;
         }

         var1.method23(new UUID(var11, var13));
         var6.method7(var1.method17(), var1.getId());
      }

      LinkedHashMapImpl var16 = new LinkedHashMapImpl(var2);

      for (Entry var8 : this.field2.entrySet()) {
         var16.put((String)var8.getKey(), (Fishing2Iterator)((Fishing2Iterator)var8.getValue()).method1(var2, var3, var4));
      }

      var1.method24(var16);
      int var18 = (Integer)var4.getMaximum() - (Integer)var4.getMinimum();
      int var19 = var1.method6(var18);
      var1.method25((int)((var18 - var19) * this.method7()));
      var1.method26(var19);
      return (T)var1;
   }

   public int method4(Range<Integer> var1) {
      int var2 = (Integer)var1.getMaximum() - (Integer)var1.getMinimum();
      return this.method5() + this.method6(var2) - var2;
   }

   public int method5() {
      return (int)(this.field4 / this.method7());
   }

   public int method6(int var1) {
      return this.method12(var1, true);
   }

   public double method7() {
      return this.method8(this.field4);
   }

   protected double method8(int var1) {
      double var2 = this.method9(Range.between(0, var1));
      double var4 = var2 / var1;
      return var4 < 0.01 ? 1.0 : var4;
   }

   protected double method9(Range<Integer> var1) {
      return this.method11(var1, false);
   }

   protected double method10(Range<Integer> var1) {
      return this.method11(var1, true);
   }

   protected double method11(Range<Integer> var1, boolean var2) {
      Fishing2Iterator var3 = this.field2.get("speed");
      if (var3 == null) {
         var3 = this.method18().get("audio");
      }

      if (var3 != null && var3.isEnabled()) {
         Fishing2Loader3 var4 = (Fishing2Loader3)var3.method12().get("speed");
         if (var4.method27().isEmpty()) {
            this.field6 = 1.0;
            return (Integer)var1.getMaximum() - (Integer)var1.getMinimum();
         }

         Rewind2_3 var5 = Coordinates.getProject();
         if (var2 && var5 != null && var5.method40().method3() && this.field6 != null) {
            return ((Integer)var1.getMaximum() - (Integer)var1.getMinimum()) * this.field6;
         }

         int var6 = (Integer)var1.getMaximum() - (Integer)var1.getMinimum();
         double var7 = 0.0;
         Integer var9 = null;
         Double var10 = null;
         boolean var11 = false;

         for (Entry var13 : var4.method27().entrySet()) {
            int var14 = (Integer)var13.getKey();
            if (var14 >= var6) {
               break;
            }

            double var15 = (Double)((Fishing2Loader.Data)var13.getValue()).getValue();
            if (var14 == Integer.MIN_VALUE) {
               var14 = 0;
            }

            if (var14 >= 0) {
               if (var10 == null) {
                  var7 += var15 * Math.min(var14, var6);
               } else if (var6 > var14) {
                  double var17 = ((Fishing2Loader.Data)var13.getValue()).method1(var11).method6();
                  var7 += (var10 + (var15 - var10) * var17) * (var14 - var9);
               } else {
                  for (int var19 = var9; var19 < var6; var19++) {
                     var7 += ((Fishing2Loader.Data)var13.getValue()).method1(var11).method4(0.0, var10, var15, 0.0, (double)(var19 - var9) / (var14 - var9));
                  }
               }

               var10 = var15;
               var9 = var14;
               var11 = ((Fishing2Loader.Data)var13.getValue()).method4();
            }
         }

         if (var9 != null && var9 < var6) {
            var7 += var10 * (var6 - var9);
         }

         this.field6 = ((Integer)var1.getMaximum() - (Integer)var1.getMinimum()) / Math.max(0.001, var7);
         return var7;
      } else {
         this.field6 = 1.0;
         return (Integer)var1.getMaximum() - (Integer)var1.getMinimum();
      }
   }

   protected int method12(int var1, boolean var2) {
      Fishing2Iterator var3 = this.field2.get("speed");
      if (var3 == null) {
         var3 = this.field2.get("audio");
      }

      if (var3 != null && var3.isEnabled()) {
         Fishing2Loader var4 = var3.method12().get("freeze");
         if (!(var4 instanceof Fishing2Loader2 var5)) {
            return 0;
         } else {
            int var6 = 0;
            Integer var7 = null;

            for (Entry var9 : var5.method27().entrySet()) {
               int var10 = var9.getKey() == Integer.MIN_VALUE ? 0 : Math.max(0, (Integer)var9.getKey());
               boolean var11 = Boolean.TRUE.equals(((Fishing2Loader.Data)var9.getValue()).getValue());
               if (var11) {
                  if (var7 == null) {
                     var7 = var10;
                  }
               } else if (var7 != null) {
                  var6 += this.method13(var7, var10, var1);
                  var7 = null;
               }
            }

            if (var7 != null && !var2) {
               var6 += this.method13(var7, var1, var1);
            }

            return var6;
         }
      } else {
         return 0;
      }
   }

   private int method13(int var1, int var2, int var3) {
      int var4 = Math.min(Math.max(var1, 0), var3);
      int var5 = Math.min(Math.max(var2, 0), var3);
      return Math.max(0, var5 - var4);
   }

   public UUID getId2() {
      return this.id;
   }

   public abstract String type();

   public List<String> method15() {
      return List.of();
   }

   public JsonElement method16(Highlight_3 var1, Range<Integer> var2, RewindHandlers var3, Runnable var4) {
      JsonObject var5 = new JsonObject();
      var5.addProperty("min", (Number)var2.getMinimum());
      var5.addProperty("max", (Number)var2.getMaximum());
      var5.addProperty("type", this.getType());
      var5.addProperty("id", this.getId().toString());
      var5.addProperty("trimLeft", 1000000);
      var5.addProperty("trimRight", 1000000);
      return var5;
   }

   @Generated
   public UUID getId() {
      return this.id;
   }

   @Generated
   public UUID method17() {
      return this.field1;
   }

   @Generated
   public LinkedHashMapImpl method18() {
      return this.field2;
   }

   @Generated
   public String getType() {
      return this.field3;
   }

   @Generated
   public int method19() {
      return this.field4;
   }

   @Generated
   public int method20() {
      return this.field5;
   }

   @Generated
   public Double method21() {
      return this.field6;
   }

   @Generated
   public void setId(UUID var1) {
      this.id = var1;
   }

   @Generated
   public void method23(UUID var1) {
      this.field1 = var1;
   }

   @Generated
   public void method24(LinkedHashMapImpl var1) {
      this.field2 = var1;
   }

   @Generated
   public void method25(int var1) {
      this.field4 = var1;
   }

   @Generated
   public void method26(int var1) {
      this.field5 = var1;
   }

   @Generated
   public void method27(Double var1) {
      this.field6 = var1;
   }
}
