package com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Generated;
import org.joml.Vector3d;

public enum BurrowType3 {
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

   public static BurrowType3 fromName(String var0) {
      for (BurrowType3 var4 : values()) {
         if (var0.equals(var4.getName())) {
            return var4;
         }
      }

      return null;
   }

   public static List<String> names() {
      ArrayList var0 = new ArrayList();

      for (BurrowType3 var4 : values()) {
         var0.add(var4.getName());
      }

      return var0;
   }

   public static Set<String> getEnabledByDefault() {
      HashSet var0 = new HashSet();

      for (BurrowType3 var4 : values()) {
         if (var4.enabledByDefault) {
            var0.add(var4.getName());
         }
      }

      return var0;
   }

   public double getDistanceSq(double value, double var3, double var5) {
      return this.getPosition().distanceSquared(value, var3, var5);
   }

   @Generated
   BurrowType3(String var3, String var4, boolean var5, Vector3d vector3d) {
      this.name = var3;
      this.warp = var4;
      this.enabledByDefault = var5;
      this.position = vector3d;
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
