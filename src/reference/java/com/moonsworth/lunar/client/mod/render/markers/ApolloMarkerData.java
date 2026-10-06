package com.moonsworth.lunar.client.mod.render.markers;

import com.moonsworth.lunar.client.mod.render.markers.Markers;
import javax.annotation.Nullable;
import lombok.Generated;

public class ApolloMarkerData {
   private final String field1;
   private final boolean field2;
   private final boolean field3;
   private final boolean field4;
   @Nullable
   private final Long field5;
   @Nullable
   private final Integer field6;
   @Nullable
   private final Float field7;
   @Nullable
   private final Boolean field8;
   @Nullable
   private final Boolean field9;
   @Nullable
   private final Boolean field10;
   @Nullable
   private final String field11;
   @Nullable
   private final Markers.Type3 field12;
   @Nullable
   private final Markers.Type field13;
   @Nullable
   private final Markers.Type field14;
   @Nullable
   private final Markers.Type field15;
   @Nullable
   private final Markers.Type field16;
   @Nullable
   private final Markers.Type2 field17;

   @Generated
   ApolloMarkerData(
      String text,
      boolean flag,
      boolean flag2,
      boolean flag3,
      @Nullable Long var5,
      @Nullable Integer var6,
      @Nullable Float var7,
      @Nullable Boolean var8,
      @Nullable Boolean var9,
      @Nullable Boolean var10,
      @Nullable String var11,
      @Nullable Markers.Type3 var12,
      @Nullable Markers.Type var13,
      @Nullable Markers.Type var14,
      @Nullable Markers.Type var15,
      @Nullable Markers.Type var16,
      @Nullable Markers.Type2 var17
   ) {
      this.field1 = text;
      this.field2 = flag;
      this.field3 = flag2;
      this.field4 = flag3;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
      this.field9 = var9;
      this.field10 = var10;
      this.field11 = var11;
      this.field12 = var12;
      this.field13 = var13;
      this.field14 = var14;
      this.field15 = var15;
      this.field16 = var16;
      this.field17 = var17;
   }

   @Generated
   public static MarkerData$Builder method1() {
      return new MarkerData$Builder();
   }

   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public boolean isChatNotify() {
      return this.field2;
   }

   @Generated
   public boolean isInGameNotification() {
      return this.field3;
   }

   @Generated
   public boolean isMiddleClickRemove() {
      return this.field4;
   }

   @Nullable
   @Generated
   public Long method2() {
      return this.field5;
   }

   @Nullable
   @Generated
   public Integer method3() {
      return this.field6;
   }

   @Nullable
   @Generated
   public Float method4() {
      return this.field7;
   }

   @Nullable
   @Generated
   public Boolean method5() {
      return this.field8;
   }

   @Nullable
   @Generated
   public Boolean method6() {
      return this.field9;
   }

   @Nullable
   @Generated
   public Boolean method7() {
      return this.field10;
   }

   @Nullable
   @Generated
   public String getOwnerSuffix() {
      return this.field11;
   }

   @Nullable
   @Generated
   public Markers.Type3 method8() {
      return this.field12;
   }

   @Nullable
   @Generated
   public Markers.Type method9() {
      return this.field13;
   }

   @Nullable
   @Generated
   public Markers.Type method10() {
      return this.field14;
   }

   @Nullable
   @Generated
   public Markers.Type method11() {
      return this.field15;
   }

   @Nullable
   @Generated
   public Markers.Type method12() {
      return this.field16;
   }

   @Nullable
   @Generated
   public Markers.Type2 method13() {
      return this.field17;
   }
}
