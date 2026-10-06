package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Locale;
import lombok.Generated;
import org.joml.Vector2i;
import org.joml.Vector3i;

public enum BossMapFloor {
   F1(1, new Vector3i(-65, 70, -3), new Vector3i(-19, 90, 45), 46, 48, new Vector2i(-65, -3), null, 480, 480),
   F2(2, new Vector3i(-34, 54, -35), new Vector3i(18, 100, 15), 52, 50, new Vector2i(-34, -35), null, 499, 480),
   F3(3, new Vector3i(-33, 64, -34), new Vector3i(35, 118, 37), 68, 73, new Vector2i(-33, -34), null, 480, 501),
   F4(4, new Vector3i(-37, 53, -37), new Vector3i(47, 114, 47), 84, 84, new Vector2i(-33, -34), null, 480, 480),
   F5(5, new Vector3i(-35, 53, -5), new Vector3i(45, 112, 82), 80, 80, new Vector2i(-35, 2), null, 480, 480),
   F6(6, new Vector3i(-31, 51, -5), new Vector3i(13, 110, 135), 44, 99, new Vector2i(-31, -5), null, 388, 854),
   F7_0(7, new Vector3i(14, 161, 115), new Vector3i(42, 189, 153), 28, 38, new Vector2i(14, 115), null, 480, 480),
   F7_1(7, new Vector3i(33, 213, 11), new Vector3i(113, 255, 86), 100, 75, new Vector2i(33, 11), null, 505, 480),
   F7_2(7, new Vector3i(19, 160, -1), new Vector3i(127, 212, 107), 108, 108, new Vector2i(19, -1), null, 480, 480),
   F7_3(7, new Vector3i(-3, 103, 29), new Vector3i(111, 159, 143), 114, 114, new Vector2i(-3, 29), 64.0F, 480, 480),
   F7_4(7, new Vector3i(-3, 54, 19), new Vector3i(111, 102, 133), 114, 94, new Vector2i(-3, 19), null, 480, 480),
   F7_5(7, new Vector3i(-5, 0, -5), new Vector3i(131, 53, 142), 136, 147, new Vector2i(-5, -5), null, 480, 523);

   private final int dungeonFloor;
   private final Vector3i topLeftBound;
   private final Vector3i bottomRightBound;
   private final int widthInWorld;
   private final int heightInWorld;
   private final Vector2i topLeft;
   private final Float renderSize;
   private final int textureWidth;
   private final int textureHeight;
   private final ResourceLocationBridge image;

   BossMapFloor(int value, Vector3i vector3i4, Vector3i vector3i5, int value2, int value3, Vector2i vector2i8, Float floatValue, int value4, int value5) {
      this.dungeonFloor = value;
      this.topLeftBound = vector3i4;
      this.bottomRightBound = vector3i5;
      this.widthInWorld = value2;
      this.heightInWorld = value3;
      this.topLeft = vector2i8;
      this.renderSize = floatValue;
      this.textureWidth = value4;
      this.textureHeight = value5;
      this.image = ResourceLocationBridge.create("lunar", "skyblock/dungeon/boss_minimap/" + this.name().toLowerCase(Locale.ROOT) + ".png");
   }

   @Generated
   public int getDungeonFloor() {
      return this.dungeonFloor;
   }

   @Generated
   public Vector3i getTopLeftBound() {
      return this.topLeftBound;
   }

   @Generated
   public Vector3i getBottomRightBound() {
      return this.bottomRightBound;
   }

   @Generated
   public int getWidthInWorld() {
      return this.widthInWorld;
   }

   @Generated
   public int getHeightInWorld() {
      return this.heightInWorld;
   }

   @Generated
   public Vector2i getTopLeft() {
      return this.topLeft;
   }

   @Generated
   public Float getRenderSize() {
      return this.renderSize;
   }

   @Generated
   public int getTextureWidth() {
      return this.textureWidth;
   }

   @Generated
   public int getTextureHeight() {
      return this.textureHeight;
   }

   @Generated
   public ResourceLocationBridge getImage() {
      return this.image;
   }
}
