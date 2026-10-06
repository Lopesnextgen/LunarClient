package com.moonsworth.lunar.client.driver.core.holograms.mixin;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.Generated;
import org.joml.Vector4f;

public enum HologramCameraPresetLegacy {
   FRONT("FRONT", 3.8333333F, ImmutableList.of(), 0.5F, 0.0F, 1.0F),
   BACK("BACK", 3.8333333F, ImmutableList.of(new Vector4f(180.0F, 0.0F, 1.0F, 0.0F), new Vector4f(0.0F, 0.0F, 0.0F, 0.0F)), 0.5F, 0.0F, 1.0F),
   DEFAULT(
      "DEFAULT",
      3.8333333F,
      ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(-90.0F, 0.0F, 1.0F, 0.0F), new Vector4f(135.0F, 0.0F, 1.0F, 0.0F)),
      0.5F,
      0.0F,
      0.9F
   ),
   SUITS(
      "SUITS",
      3.8333333F,
      ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(-90.0F, 0.0F, 1.0F, 0.0F), new Vector4f(135.0F, 0.0F, 1.0F, 0.0F)),
      0.5F,
      0.0F,
      0.75F
   ),
   HAT(
      "HAT",
      3.8333333F,
      ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(-90.0F, 0.0F, 1.0F, 0.0F), new Vector4f(135.0F, 0.0F, 1.0F, 0.0F)),
      0.5F,
      0.4F,
      1.35F
   ),
   CLOAK("CLOAK", 3.8333333F, ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(135.0F, 0.0F, 1.0F, 0.0F)), 0.55F, -0.15F, 1.35F),
   WING("WING", 3.8333333F, ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(135.0F, 0.0F, 1.0F, 0.0F)), 0.6F, 0.0F, 0.8F),
   SHOES(
      "SHOES",
      3.8333333F,
      ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(-90.0F, 0.0F, 1.0F, 0.0F), new Vector4f(115.0F, 0.0F, 1.0F, 0.0F)),
      0.5F,
      -0.75F,
      1.6F
   ),
   BELT(
      "BELT",
      3.8333333F,
      ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(-90.0F, 0.0F, 1.0F, 0.0F), new Vector4f(115.0F, 0.0F, 1.0F, 0.0F)),
      0.5F,
      -0.275F,
      1.75F
   ),
   NECK("NECK", 3.8333333F, ImmutableList.of(), 0.5F, 0.0F, 1.0F),
   BUST(
      "BUST",
      3.8333333F,
      ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(-90.0F, 0.0F, 1.0F, 0.0F), new Vector4f(115.0F, 0.0F, 1.0F, 0.0F)),
      0.5F,
      0.2F,
      1.65F
   ),
   DUMMY(
      "DUMMY",
      3.8333333F,
      ImmutableList.of(new Vector4f(20.0F, 1.0F, 0.0F, 0.0F), new Vector4f(-180.0F, 0.0F, 1.0F, 0.0F), new Vector4f(145.0F, 0.0F, 1.0F, 0.0F)),
      0.5F,
      0.0F,
      0.9F
   ),
   WRIST("WRIST", 3.8333333F, ImmutableList.of(new Vector4f(70.5F, 0.0F, 1.0F, 0.0F)), 0.65F, -0.25F, 2.0F),
   ITEM("ITEM", 3.8333333F, ImmutableList.of(new Vector4f(70.5F, 0.0F, 1.0F, 0.0F)), 0.35F, 0.0F, 0.9F);

   final String id;
   final float scaleFactor;
   final List<Vector4f> rotation;
   final float xOffset;
   final float yOffset;
   final float zoom;

   @Override
   public String toString() {
      return this.id;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public float getScaleFactor() {
      return this.scaleFactor;
   }

   @Generated
   public List<Vector4f> getRotation() {
      return this.rotation;
   }

   @Generated
   public float getXOffset() {
      return this.xOffset;
   }

   @Generated
   public float getYOffset() {
      return this.yOffset;
   }

   @Generated
   public float getZoom() {
      return this.zoom;
   }

   @Generated
   HologramCameraPresetLegacy(String text, float value, List<Vector4f> list, float value2, float value3, float value4) {
      this.id = text;
      this.scaleFactor = value;
      this.rotation = list;
      this.xOffset = value2;
      this.yOffset = value3;
      this.zoom = value4;
   }
}
