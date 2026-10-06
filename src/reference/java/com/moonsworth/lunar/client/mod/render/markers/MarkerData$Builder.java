package com.moonsworth.lunar.client.mod.render.markers;

import com.moonsworth.lunar.client.mod.render.markers.Markers;
import javax.annotation.Nullable;
import lombok.Generated;

@Generated
public class MarkerData$Builder {
   @Generated
   private String id;
   @Generated
   private boolean chatNotify;
   @Generated
   private boolean inGameNotification;
   @Generated
   private boolean middleClickRemove;
   @Generated
   private Long field1;
   @Generated
   private Integer field2;
   @Generated
   private Float field3;
   @Generated
   private Boolean field4;
   @Generated
   private Boolean field5;
   @Generated
   private Boolean field6;
   @Generated
   private String ownerSuffix;
   @Generated
   private Markers.Type3 field7;
   @Generated
   private Markers.Type field8;
   @Generated
   private Markers.Type field9;
   @Generated
   private Markers.Type field10;
   @Generated
   private Markers.Type field11;
   @Generated
   private Markers.Type2 field12;

   @Generated
   MarkerData$Builder() {
   }

   @Generated
   public MarkerData$Builder method1(String var1) {
      this.id = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method2(boolean var1) {
      this.chatNotify = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method3(boolean var1) {
      this.inGameNotification = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method4(boolean var1) {
      this.middleClickRemove = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method5(@Nullable Long var1) {
      this.field1 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method6(@Nullable Integer var1) {
      this.field2 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method7(@Nullable Float var1) {
      this.field3 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method8(@Nullable Boolean var1) {
      this.field4 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method9(@Nullable Boolean var1) {
      this.field5 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method10(@Nullable Boolean var1) {
      this.field6 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method11(@Nullable String var1) {
      this.ownerSuffix = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method12(@Nullable Markers.Type3 var1) {
      this.field7 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method13(@Nullable Markers.Type var1) {
      this.field8 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method14(@Nullable Markers.Type var1) {
      this.field9 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method15(@Nullable Markers.Type var1) {
      this.field10 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method16(@Nullable Markers.Type var1) {
      this.field11 = var1;
      return this;
   }

   @Generated
   public MarkerData$Builder method17(@Nullable Markers.Type2 var1) {
      this.field12 = var1;
      return this;
   }

   @Generated
   public ApolloMarkerData method18() {
      return new ApolloMarkerData(
         this.id,
         this.chatNotify,
         this.inGameNotification,
         this.middleClickRemove,
         this.field1,
         this.field2,
         this.field3,
         this.field4,
         this.field5,
         this.field6,
         this.ownerSuffix,
         this.field7,
         this.field8,
         this.field9,
         this.field10,
         this.field11,
         this.field12
      );
   }

   @Generated
   @Override
   public String toString() {
      return "ApolloMarkerData.ApolloMarkerDataBuilder(id="
         + this.id
         + ", chatNotify="
         + this.chatNotify
         + ", inGameNotification="
         + this.inGameNotification
         + ", middleClickRemove="
         + this.middleClickRemove
         + ", durationMillis="
         + this.field1
         + ", color="
         + this.field2
         + ", scale="
         + this.field3
         + ", animate="
         + this.field4
         + ", compactMode="
         + this.field5
         + ", textShadow="
         + this.field6
         + ", ownerSuffix="
         + this.ownerSuffix
         + ", ownerDisplay="
         + this.field7
         + ", showOwner="
         + this.field8
         + ", showCoordinates="
         + this.field9
         + ", showDistance="
         + this.field10
         + ", showDescription="
         + this.field11
         + ", descriptionDisplay="
         + this.field12
         + ")";
   }
}
