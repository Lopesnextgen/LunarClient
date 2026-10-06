package com.moonsworth.lunar.client.mumble;

import lombok.Generated;

public final class LinkData {
   private String playerName;
   private String context;
   private MumbleVec avatarFront;
   private MumbleVec avatarTop;
   private MumbleVec avatarPosition;
   private MumbleVec cameraPosition;
   private MumbleVec cameraFront;
   private MumbleVec cameraTop;

   @Generated
   public String getPlayerName() {
      return this.playerName;
   }

   @Generated
   public String getContext() {
      return this.context;
   }

   @Generated
   public MumbleVec getAvatarFront() {
      return this.avatarFront;
   }

   @Generated
   public MumbleVec getAvatarTop() {
      return this.avatarTop;
   }

   @Generated
   public MumbleVec getAvatarPosition() {
      return this.avatarPosition;
   }

   @Generated
   public MumbleVec getCameraPosition() {
      return this.cameraPosition;
   }

   @Generated
   public MumbleVec getCameraFront() {
      return this.cameraFront;
   }

   @Generated
   public MumbleVec getCameraTop() {
      return this.cameraTop;
   }

   @Generated
   public void setPlayerName(String var1) {
      this.playerName = var1;
   }

   @Generated
   public void setContext(String var1) {
      this.context = var1;
   }

   @Generated
   public void setAvatarFront(MumbleVec var1) {
      this.avatarFront = var1;
   }

   @Generated
   public void setAvatarTop(MumbleVec var1) {
      this.avatarTop = var1;
   }

   @Generated
   public void setAvatarPosition(MumbleVec var1) {
      this.avatarPosition = var1;
   }

   @Generated
   public void setCameraPosition(MumbleVec var1) {
      this.cameraPosition = var1;
   }

   @Generated
   public void setCameraFront(MumbleVec var1) {
      this.cameraFront = var1;
   }

   @Generated
   public void setCameraTop(MumbleVec var1) {
      this.cameraTop = var1;
   }

   @Generated
   public LinkData(String var1, String text, MumbleVec mumbleVec, MumbleVec mumbleVec2, MumbleVec mumbleVec3, MumbleVec mumbleVec4, MumbleVec mumbleVec5, MumbleVec mumbleVec6) {
      this.playerName = var1;
      this.context = text;
      this.avatarFront = mumbleVec;
      this.avatarTop = mumbleVec2;
      this.avatarPosition = mumbleVec3;
      this.cameraPosition = mumbleVec4;
      this.cameraFront = mumbleVec5;
      this.cameraTop = mumbleVec6;
   }
}
