package com.moonsworth.lunar.client.framework.feature.pkg;

enum Pkg3$Type2 {
   HEAD(0.0F, new com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.Data2(8, 8, 8)),
   BODY(0.6F, new com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.Data2(8, 12, 4)),
   LEGS(-0.2F, new com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.Data2(4, 14, 4)),
   ARMS(0.4F, new com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.Data2(4, 14, 4)),
   ARMS_SLIM(0.4F, new com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.Data2(3, 14, 4));

   private final float yOffsetMagicValue;

   Pkg3$Type2(float value, com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg4.Data2 data) {
      this.yOffsetMagicValue = value;
   }
}
