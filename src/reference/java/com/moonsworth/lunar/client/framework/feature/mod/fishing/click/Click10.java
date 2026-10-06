package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.mod.skyblock.endermanslayer.SkyblockEndermanSlayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump45;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class Click10 {
   private BridgeExtension field1;
   private BridgeExtension field2;
   private BridgeExtension field3;
   private String owner;
   private Click10.Type state;
   private List<BridgeExtension> shields;
   private List<BridgeExtension> spawnedEntities;
   private ThreadModuleDump45 radiationTimer;
   private Click10.Data glyphData;
   private Vector3iBridge lastBlockPos;

   public Click10(@NotNull BridgeExtension var1, @NotNull BridgeExtension var2, @NotNull BridgeExtension var3, @NotNull String var4) {
      this.entity = var1;
      this.nameEntity = var2;
      this.anchorEntity = var3;
      this.owner = var4;
      this.state = Click10.Type.SHIELD;
      this.shields = new ArrayList<>();
      this.spawnedEntities = new ArrayList<>();
      this.radiationTimer = null;
   }

   public boolean isValid() {
      if (this.anchorEntity.bridge$getPosX() - this.nameEntity.bridge$getPosX() > 0.1
         || this.anchorEntity.bridge$getPosY() - this.nameEntity.bridge$getPosY() > 0.5
         || this.anchorEntity.bridge$getPosZ() - this.nameEntity.bridge$getPosZ() > 0.1) {
         return false;
      } else {
         return Click14.distanceSquared(this.entity, this.anchorEntity) > 4.0F ? false : !(Click14.distanceSquared(this.entity, this.nameEntity) > 4.0F);
      }
   }

   public void update() {
      Component var1 = this.getNameEntity().bridge$getCustomName();
      if (var1 != null) {
         Click10.Type var2 = this.getState();
         String var3 = AdventureTextBridge.getTextContent(var1);
         Matcher var4 = SkyblockEndermanSlayer.field10.matcher(var3);
         if (var4.find()) {
            if (this.entity.bridge$isRiding()) {
               this.state = Click10.Type.RADIATION;
            } else {
               String var5 = var4.group("extraData");
               this.state = var5.contains("Hit") ? Click10.Type.SHIELD : Click10.Type.NONE;
            }
         }

         if (this.state == Click10.Type.RADIATION && var2 != Click10.Type.RADIATION && (this.radiationTimer == null || this.radiationTimer.get() < 0L)) {
            this.radiationTimer = new com.moonsworth.lunar.client.util.ThreadModuleDump45.Data().method2().method4().method5(8000L).method7().method2();
         }
      }
   }

   @Generated
   public BridgeExtension getEntity() {
      return this.entity;
   }

   @Generated
   public BridgeExtension getNameEntity() {
      return this.nameEntity;
   }

   @Generated
   public BridgeExtension getAnchorEntity() {
      return this.anchorEntity;
   }

   @Generated
   public String getOwner() {
      return this.owner;
   }

   @Generated
   public Click10.Type getState() {
      return this.state;
   }

   @Generated
   public List<BridgeExtension> getShields() {
      return this.shields;
   }

   @Generated
   public List<BridgeExtension> getSpawnedEntities() {
      return this.spawnedEntities;
   }

   @Generated
   public ThreadModuleDump45 getRadiationTimer() {
      return this.radiationTimer;
   }

   @Generated
   public Click10.Data getGlyphData() {
      return this.glyphData;
   }

   @Generated
   public Vector3iBridge getLastBlockPos() {
      return this.lastBlockPos;
   }

   @Generated
   public void setEntity(BridgeExtension var1) {
      this.entity = var1;
   }

   @Generated
   public void setNameEntity(BridgeExtension var1) {
      this.nameEntity = var1;
   }

   @Generated
   public void setAnchorEntity(BridgeExtension var1) {
      this.anchorEntity = var1;
   }

   @Generated
   public void setOwner(String var1) {
      this.owner = var1;
   }

   @Generated
   public void setState(Click10.Type var1) {
      this.state = var1;
   }

   @Generated
   public void setShields(List<BridgeExtension> var1) {
      this.shields = var1;
   }

   @Generated
   public void setSpawnedEntities(List<BridgeExtension> var1) {
      this.spawnedEntities = var1;
   }

   @Generated
   public void setRadiationTimer(ThreadModuleDump45 var1) {
      this.radiationTimer = var1;
   }

   @Generated
   public void setGlyphData(Click10.Data var1) {
      this.glyphData = var1;
   }

   @Generated
   public void setLastBlockPos(Vector3iBridge var1) {
      this.lastBlockPos = var1;
   }

   public static class Data {
      private BridgeExtension field1;
      private Vec3Bridge field2;
      private long field3;

      public Data(BridgeExtension var1) {
         this.entity = var1;
         this.nameEntity = null;
         this.anchorEntity = -1L;
      }

      @Generated
      public Data(BridgeExtension var1, Vec3Bridge var2, long var3) {
         this.entity = var1;
         this.nameEntity = var2;
         this.anchorEntity = var3;
      }

      @Generated
      public BridgeExtension update() {
         return this.entity;
      }

      @Generated
      public Vec3Bridge getEntity() {
         return this.nameEntity;
      }

      @Generated
      public long getNameEntity() {
         return this.anchorEntity;
      }

      @Generated
      public void getAnchorEntity(BridgeExtension var1) {
         this.entity = var1;
      }

      @Generated
      public void getState(Vec3Bridge var1) {
         this.nameEntity = var1;
      }

      @Generated
      public void getShields(long var1) {
         this.anchorEntity = var1;
      }
   }

   public enum Type {
      SHIELD,
      RADIATION,
      NONE;
   }
}
