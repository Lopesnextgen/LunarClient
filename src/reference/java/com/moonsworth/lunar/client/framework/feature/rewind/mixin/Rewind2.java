package com.moonsworth.lunar.client.framework.feature.rewind.mixin;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import lombok.Generated;

public class Rewind2 {
   @SerializedName("id")
   final UUID field1 = UUID.randomUUID();
   @SerializedName("recorderVersion")
   int field2 = 0;
   @SerializedName("rewindProtocolVersion")
   final int field3 = 0;
   @SerializedName("minecraftVersion")
   String field4 = "Unknown";
   @SerializedName("minecraftProtocolVersion")
   int field5 = -1;
   @SerializedName("duration")
   long duration = -1L;
   @SerializedName("timestamp")
   long timestamp = -1L;
   @SerializedName("markers")
   List<Long> markers = new ArrayList<>();
   @SerializedName("snapshots")
   TreeMap<Long, Rewind2.Data> field6 = new TreeMap<>();
   @SerializedName("playerName")
   String playerName = "Unknown";
   @SerializedName("playerUUID")
   UUID field7 = null;
   @SerializedName("locations")
   Map<String, String> field8 = new LinkedHashMap<>();
   @SerializedName("shadow")
   boolean shadow = false;
   @SerializedName("externalMods")
   Map<String, String> field9 = new HashMap<>();
   @SerializedName("mainCompression")
   RewindType field10 = RewindType.ZLIB;
   @SerializedName("snapshotsCompression")
   RewindType field11 = RewindType.ZLIB;

   public long method1() {
      return this.field6.isEmpty() ? this.duration : this.duration - this.field6.firstKey();
   }

   @Generated
   public UUID getId() {
      return this.field1;
   }

   @Generated
   public int method2() {
      return this.field2;
   }

   @Generated
   public int method3() {
      return 0;
   }

   @Generated
   public String method4() {
      return this.field4;
   }

   @Generated
   public int method5() {
      return this.field5;
   }

   @Generated
   public long getDuration() {
      return this.duration;
   }

   @Generated
   public long getTimestamp() {
      return this.timestamp;
   }

   @Generated
   public List<Long> getMarkers() {
      return this.markers;
   }

   @Generated
   public TreeMap<Long, Rewind2.Data> method7() {
      return this.field6;
   }

   @Generated
   public String getPlayerName() {
      return this.playerName;
   }

   @Generated
   public UUID method8() {
      return this.field7;
   }

   @Generated
   public Map<String, String> method9() {
      return this.field8;
   }

   @Generated
   public boolean isShadow() {
      return this.shadow;
   }

   @Generated
   public Map<String, String> method11() {
      return this.field9;
   }

   @Generated
   public RewindType method12() {
      return this.field10;
   }

   @Generated
   public RewindType method13() {
      return this.field11;
   }

   @Generated
   public void method14(int var1) {
      this.field2 = var1;
   }

   @Generated
   public void method15(String var1) {
      this.field4 = var1;
   }

   @Generated
   public void method16(int var1) {
      this.field5 = var1;
   }

   @Generated
   public void setDuration(long var1) {
      this.duration = var1;
   }

   @Generated
   public void setTimestamp(long var1) {
      this.timestamp = var1;
   }

   @Generated
   public void setMarkers(List<Long> var1) {
      this.markers = var1;
   }

   @Generated
   public void method19(TreeMap<Long, Rewind2.Data> var1) {
      this.field6 = var1;
   }

   @Generated
   public void setPlayerName(String var1) {
      this.playerName = var1;
   }

   @Generated
   public void method20(UUID var1) {
      this.field7 = var1;
   }

   @Generated
   public void method21(Map<String, String> var1) {
      this.field8 = var1;
   }

   @Generated
   public void setShadow(boolean var1) {
      this.shadow = var1;
   }

   @Generated
   public void method22(Map<String, String> var1) {
      this.field9 = var1;
   }

   @Generated
   public void method23(RewindType var1) {
      this.field10 = var1;
   }

   @Generated
   public void method24(RewindType var1) {
      this.field11 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Rewind2 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.method2() != var2.method2()) {
            return false;
         }

         if (this.method3() != var2.method3()) {
            return false;
         }

         if (this.method5() != var2.method5()) {
            return false;
         }

         if (this.getDuration() != var2.getDuration()) {
            return false;
         }

         if (this.getTimestamp() != var2.getTimestamp()) {
            return false;
         }

         if (this.isShadow() != var2.isShadow()) {
            return false;
         }

         UUID var3 = this.getId();
         UUID var4 = var2.getId();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            String var5 = this.method4();
            String var6 = var2.method4();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               List var7 = this.getMarkers();
               List var8 = var2.getMarkers();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  TreeMap var9 = this.method7();
                  TreeMap var10 = var2.method7();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     String var11 = this.getPlayerName();
                     String var12 = var2.getPlayerName();
                     if (var11 == null ? var12 == null : var11.equals(var12)) {
                        UUID var13 = this.method8();
                        UUID var14 = var2.method8();
                        if (var13 == null ? var14 == null : var13.equals(var14)) {
                           Map var15 = this.method9();
                           Map var16 = var2.method9();
                           if (var15 == null ? var16 == null : var15.equals(var16)) {
                              Map var17 = this.method11();
                              Map var18 = var2.method11();
                              if (var17 == null ? var18 == null : var17.equals(var18)) {
                                 RewindType var19 = this.method12();
                                 RewindType var20 = var2.method12();
                                 if (var19 == null ? var20 == null : var19.equals(var20)) {
                                    RewindType var21 = this.method13();
                                    RewindType var22 = var2.method13();
                                    return var21 == null ? var22 == null : var21.equals(var22);
                                 } else {
                                    return false;
                                 }
                              } else {
                                 return false;
                              }
                           } else {
                              return false;
                           }
                        } else {
                           return false;
                        }
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Rewind2;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.method2();
      var2 = var2 * 59 + this.method3();
      var2 = var2 * 59 + this.method5();
      long var3 = this.getDuration();
      var2 = var2 * 59 + (int)(var3 >>> 32 ^ var3);
      long var5 = this.getTimestamp();
      var2 = var2 * 59 + (int)(var5 >>> 32 ^ var5);
      var2 = var2 * 59 + (this.isShadow() ? 79 : 97);
      UUID var7 = this.getId();
      var2 = var2 * 59 + (var7 == null ? 43 : var7.hashCode());
      String var8 = this.method4();
      var2 = var2 * 59 + (var8 == null ? 43 : var8.hashCode());
      List var9 = this.getMarkers();
      var2 = var2 * 59 + (var9 == null ? 43 : var9.hashCode());
      TreeMap var10 = this.method7();
      var2 = var2 * 59 + (var10 == null ? 43 : var10.hashCode());
      String var11 = this.getPlayerName();
      var2 = var2 * 59 + (var11 == null ? 43 : var11.hashCode());
      UUID var12 = this.method8();
      var2 = var2 * 59 + (var12 == null ? 43 : var12.hashCode());
      Map var13 = this.method9();
      var2 = var2 * 59 + (var13 == null ? 43 : var13.hashCode());
      Map var14 = this.method11();
      var2 = var2 * 59 + (var14 == null ? 43 : var14.hashCode());
      RewindType var15 = this.method12();
      var2 = var2 * 59 + (var15 == null ? 43 : var15.hashCode());
      RewindType var16 = this.method13();
      return var2 * 59 + (var16 == null ? 43 : var16.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "RewindMetadata(id="
         + this.getId()
         + ", recorderVersion="
         + this.method2()
         + ", rewindProtocolVersion="
         + this.method3()
         + ", minecraftVersion="
         + this.method4()
         + ", minecraftProtocolVersion="
         + this.method5()
         + ", duration="
         + this.getDuration()
         + ", timestamp="
         + this.getTimestamp()
         + ", markers="
         + this.getMarkers()
         + ", snapshots="
         + this.method7()
         + ", playerName="
         + this.getPlayerName()
         + ", playerUUID="
         + this.method8()
         + ", locations="
         + this.method9()
         + ", shadow="
         + this.isShadow()
         + ", externalMods="
         + this.method11()
         + ", mainCompression="
         + this.method12()
         + ", snapshotsCompression="
         + this.method13()
         + ")";
   }

   public class Data {
      @SerializedName("position")
      private final long field1;
      @SerializedName("uuid")
      private final String field2;

      public Data(long var1, String var3) {
         this.field1 = var1;
         this.field2 = var3;
      }

      @SerializedName("position")
      public long position() {
         return this.field1;
      }

      @SerializedName("uuid")
      public String uuid() {
         return this.field2;
      }
   }
}
