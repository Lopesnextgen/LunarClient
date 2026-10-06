package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.mod.skyblock.endermanslayer.SkyblockEndermanSlayer;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class VoidgloomBoss {
   private BridgeExtension field1;
   private BridgeExtension field2;
   private BridgeExtension field3;
   private String owner;
   private VoidgloomBoss.Type type;
   private List<BridgeExtension> skulls;
   private List<BridgeExtension> endermen;
   private HudTimer beaconTimer;
   private VoidgloomBoss.Data beaconData;
   private Vec3iBridge beaconBlockPos;

   public VoidgloomBoss(@NotNull BridgeExtension bridgeextension1, @NotNull BridgeExtension bridgeextension2, @NotNull BridgeExtension bridgeextension3, @NotNull String text4) {
      this.boss = bridgeextension1;
      this.nameTag = bridgeextension2;
      this.marker = bridgeextension3;
      this.owner = text4;
      this.type = VoidgloomBoss.Type.SHIELD;
      this.skulls = new ArrayList<>();
      this.endermen = new ArrayList<>();
      this.beaconTimer = null;
   }

   public boolean isValid() {
      if (this.marker.bridge$getPosX() - this.nameTag.bridge$getPosX() > 0.1
         || this.marker.bridge$getPosY() - this.nameTag.bridge$getPosY() > 0.5
         || this.marker.bridge$getPosZ() - this.nameTag.bridge$getPosZ() > 0.1) {
         return false;
      } else {
         return EntityFinder.distanceSquared(this.boss, this.marker) > 4.0F ? false : !(EntityFinder.distanceSquared(this.boss, this.nameTag) > 4.0F);
      }
   }

   public void update() {
      Component component1 = this.getNameTag().bridge$getCustomName();
      if (component1 != null) {
         VoidgloomBoss.Type type2 = this.getType();
         String text3 = TextBridge.getTextContent(component1);
         Matcher matcher4 = SkyblockEndermanSlayer.field10.matcher(text3);
         if (matcher4.find()) {
            if (this.boss.bridge$isRiding()) {
               this.type = VoidgloomBoss.Type.RADIATION;
            } else {
               String text5 = matcher4.group("extraData");
               this.type = text5.contains("Hit") ? VoidgloomBoss.Type.SHIELD : VoidgloomBoss.Type.NONE;
            }
         }

         if (this.type == VoidgloomBoss.Type.RADIATION && type2 != VoidgloomBoss.Type.RADIATION && (this.beaconTimer == null || this.beaconTimer.get() < 0L)) {
            this.beaconTimer = new com.moonsworth.lunar.client.framework.hud.HudTimer.Data().method2().method4().method5(8000L).method7().method2();
         }
      }
   }

   @Generated
   public BridgeExtension getBoss() {
      return this.boss;
   }

   @Generated
   public BridgeExtension getNameTag() {
      return this.nameTag;
   }

   @Generated
   public BridgeExtension getMarker() {
      return this.marker;
   }

   @Generated
   public String getOwner() {
      return this.owner;
   }

   @Generated
   public VoidgloomBoss.Type getType() {
      return this.type;
   }

   @Generated
   public List<BridgeExtension> getSkulls() {
      return this.skulls;
   }

   @Generated
   public List<BridgeExtension> getEndermen() {
      return this.endermen;
   }

   @Generated
   public HudTimer getBeaconTimer() {
      return this.beaconTimer;
   }

   @Generated
   public VoidgloomBoss.Data getBeaconData() {
      return this.beaconData;
   }

   @Generated
   public Vec3iBridge getBeaconBlockPos() {
      return this.beaconBlockPos;
   }

   @Generated
   public void setBoss(BridgeExtension bridgeextension1) {
      this.boss = bridgeextension1;
   }

   @Generated
   public void setNameTag(BridgeExtension bridgeextension1) {
      this.nameTag = bridgeextension1;
   }

   @Generated
   public void setMarker(BridgeExtension bridgeextension1) {
      this.marker = bridgeextension1;
   }

   @Generated
   public void setOwner(String text) {
      this.owner = text;
   }

   @Generated
   public void setType(VoidgloomBoss.Type type) {
      this.type = type;
   }

   @Generated
   public void setSkulls(List<BridgeExtension> list1) {
      this.skulls = list1;
   }

   @Generated
   public void setEndermen(List<BridgeExtension> list1) {
      this.endermen = list1;
   }

   @Generated
   public void setBeaconTimer(HudTimer threadmoduledump451) {
      this.beaconTimer = threadmoduledump451;
   }

   @Generated
   public void setBeaconData(VoidgloomBoss.Data data) {
      this.beaconData = data;
   }

   @Generated
   public void setBeaconBlockPos(Vec3iBridge horsestats201) {
      this.beaconBlockPos = horsestats201;
   }

   public static class Data {
      private BridgeExtension field1;
      private Vec3Bridge field2;
      private long field3;

      public Data(BridgeExtension bridgeextension1) {
         this.boss = bridgeextension1;
         this.nameTag = null;
         this.marker = -1L;
      }

      @Generated
      public Data(BridgeExtension bridgeextension1, Vec3Bridge horsestats152, long value) {
         this.boss = bridgeextension1;
         this.nameTag = horsestats152;
         this.marker = value;
      }

      @Generated
      public BridgeExtension update() {
         return this.boss;
      }

      @Generated
      public Vec3Bridge getBoss() {
         return this.nameTag;
      }

      @Generated
      public long getNameTag() {
         return this.marker;
      }

      @Generated
      public void getMarker(BridgeExtension bridgeextension1) {
         this.boss = bridgeextension1;
      }

      @Generated
      public void getType(Vec3Bridge horsestats151) {
         this.nameTag = horsestats151;
      }

      @Generated
      public void getSkulls(long value) {
         this.marker = value;
      }
   }

   public enum Type {
      SHIELD,
      RADIATION,
      NONE;

      Type() {
      }
   }
}
