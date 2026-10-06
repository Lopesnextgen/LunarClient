package com.moonsworth.lunar.client.network.mumble;

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
   public void setPlayerName(String text1) {
      this.playerName = text1;
   }

   @Generated
   public void setContext(String text1) {
      this.context = text1;
   }

   @Generated
   public void setAvatarFront(MumbleVec mumblevec1) {
      this.avatarFront = mumblevec1;
   }

   @Generated
   public void setAvatarTop(MumbleVec mumblevec1) {
      this.avatarTop = mumblevec1;
   }

   @Generated
   public void setAvatarPosition(MumbleVec mumblevec1) {
      this.avatarPosition = mumblevec1;
   }

   @Generated
   public void setCameraPosition(MumbleVec mumblevec1) {
      this.cameraPosition = mumblevec1;
   }

   @Generated
   public void setCameraFront(MumbleVec mumblevec1) {
      this.cameraFront = mumblevec1;
   }

   @Generated
   public void setCameraTop(MumbleVec mumblevec1) {
      this.cameraTop = mumblevec1;
   }

   @Generated
   public LinkData(String text1, String text, MumbleVec mumblevec3, MumbleVec mumblevec4, MumbleVec mumblevec5, MumbleVec mumblevec6, MumbleVec mumblevec7, MumbleVec mumblevec8) {
      this.playerName = text1;
      this.context = text;
      this.avatarFront = mumblevec3;
      this.avatarTop = mumblevec4;
      this.avatarPosition = mumblevec5;
      this.cameraPosition = mumblevec6;
      this.cameraFront = mumblevec7;
      this.cameraTop = mumblevec8;
   }
}
