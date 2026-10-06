package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public class TrackedRoom {
   private int field1;
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState field2 = com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.OPENED;
   private DungeonPlayerTracker field3;
   private String field4;
   private double field5;
   private String displayName;
   private MapRoomType field6;
   private int field7 = -1;
   private int field8 = -1;
   Set<RoomSecret> field9 = new HashSet<>();

   public TrackedRoom() {
   }

   @Generated
   public int method1() {
      return this.field1;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState method2() {
      return this.field2;
   }

   @Generated
   public DungeonPlayerTracker method3() {
      return this.field3;
   }

   @Generated
   public String method4() {
      return this.field4;
   }

   @Generated
   public double method5() {
      return this.field5;
   }

   @Generated
   public String getDisplayName() {
      return this.displayName;
   }

   @Generated
   public MapRoomType method6() {
      return this.field6;
   }

   @Generated
   public int method7() {
      return this.field7;
   }

   @Generated
   public int method8() {
      return this.field8;
   }

   @Generated
   public Set<RoomSecret> method9() {
      return this.field9;
   }

   @Generated
   public void method10(int number1) {
      this.field1 = number1;
   }

   @Generated
   public void method11(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState state) {
      this.field2 = state;
   }

   @Generated
   public void method12(DungeonPlayerTracker holograms4updater1) {
      this.field3 = holograms4updater1;
   }

   @Generated
   public void method13(String text1) {
      this.field4 = text1;
   }

   @Generated
   public void method14(double value) {
      this.field5 = value;
   }

   @Generated
   public void setDisplayName(String text1) {
      this.displayName = text1;
   }

   @Generated
   public void method15(MapRoomType map) {
      this.field6 = map;
   }

   @Generated
   public void method16(int number1) {
      this.field7 = number1;
   }

   @Generated
   public void method17(int number1) {
      this.field8 = number1;
   }

   @Generated
   public void method18(Set<RoomSecret> set) {
      this.field9 = set;
   }
}
