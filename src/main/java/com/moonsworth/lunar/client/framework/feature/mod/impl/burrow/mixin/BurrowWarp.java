package com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Generated;
import org.joml.Vector3d;

public enum BurrowWarp {
   SPAWN("Spawn", "hub", true, new Vector3d(0.5, 77.0, -0.5)),
   MUSEUM("Museum", "museum", false, new Vector3d(29.5, 72.0, 1.5)),
   DARK_AUCTION("Dark Auction", "da", false, new Vector3d(91.5, 75.0, 173.5)),
   CASTLE("Castle", "castle", true, new Vector3d(-250.0, 130.0, 45.0)),
   CRYPTS("Crypts", "crypt", false, new Vector3d(-160.5, 62.0, -106.5)),
   STONKS("Stonk Auction", "stonks", true, new Vector3d(-36.5, 70.0, -81.5)),
   WIZARD("Wizard Tower", "wizard", false, new Vector3d(44.5, 119.0, 93.5));

   private final String name;
   private final String warp;
   private final boolean enabledByDefault;
   private final Vector3d position;

   public static BurrowWarp fromName(String text) {
      for (BurrowWarp burrowtype34 : values()) {
         if (text.equals(burrowtype34.getName())) {
            return burrowtype34;
         }
      }

      return null;
   }

   public static List<String> names() {
      ArrayList list0 = new ArrayList();

      for (BurrowWarp burrowtype34 : values()) {
         list0.add(burrowtype34.getName());
      }

      return list0;
   }

   public static Set<String> getEnabledByDefault() {
      HashSet set0 = new HashSet();

      for (BurrowWarp burrowtype34 : values()) {
         if (burrowtype34.enabledByDefault) {
            set0.add(burrowtype34.getName());
         }
      }

      return set0;
   }

   public double getDistanceSq(double value, double value2, double value3) {
      return this.getPosition().distanceSquared(value, value2, value3);
   }

   @Generated
   BurrowWarp(String text, String text2, boolean flag, Vector3d vector3d6) {
      this.name = text;
      this.warp = text2;
      this.enabledByDefault = flag;
      this.position = vector3d6;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getWarp() {
      return this.warp;
   }

   @Generated
   public Vector3d getPosition() {
      return this.position;
   }
}
