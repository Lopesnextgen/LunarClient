package com.moonsworth.lunar.client.cosmetics.skin;

enum BodyPartSpec {
   HEAD(0.0F, new com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.VoxelDimensions(8, 8, 8)),
   BODY(0.6F, new com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.VoxelDimensions(8, 12, 4)),
   LEGS(-0.2F, new com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.VoxelDimensions(4, 14, 4)),
   ARMS(0.4F, new com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.VoxelDimensions(4, 14, 4)),
   ARMS_SLIM(0.4F, new com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.VoxelDimensions(3, 14, 4));

   private final float yOffsetMagicValue;

   BodyPartSpec(float value, com.moonsworth.lunar.client.cosmetics.skin.SolidPixelWrapper.VoxelDimensions voxelDimensions) {
      this.yOffsetMagicValue = value;
   }
}
