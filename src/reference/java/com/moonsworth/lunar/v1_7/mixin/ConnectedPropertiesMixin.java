package com.moonsworth.lunar.v1_7.mixin;

import com.moonsworth.lunar.bridge.optifine.ConnectedProperties;
import com.moonsworth.lunar.bridge.optifine.ConnectedTextureMethod;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Arrays;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(max = 0)
@Mixin(net.optifine.ConnectedProperties.class)
public class ConnectedPropertiesMixin implements ConnectedProperties {
   @Shadow
   public String[] matchTiles;
   @Shadow
   public int[] matchBlocks;
   @Shadow
   public String[] tiles;
   @Shadow
   public int method;

   public ConnectedPropertiesMixin() {
   }

   public String[] bridge$getMatchTiles() {
      return this.matchTiles;
   }

   public String[] bridge$parseMatchBlocks() {
      return this.matchBlocks != null
         ? Arrays.stream(this.matchBlocks)
            .filter(Objects::nonNull)
            .<Block>mapToObj(Block::getBlockById)
            .filter(Objects::nonNull)
            .filter(arg0 -> arg0 != Blocks.air)
            .map(Block::getTextureName)
            .toArray(String[]::new)
         : null;
   }

   public String[] bridge$getTiles() {
      return this.tiles;
   }

   public ConnectedTextureMethod bridge$getMethod() {
      int index1 = this.method;
      if (index1 >= 0 && index1 < ConnectedTextureMethod.VALUES.length) {
         return ConnectedTextureMethod.VALUES[index1];
      }

      com.moonsworth.lunar.client.util.LunarLogger.method5("[ConnectedPropertiesBridge] Unknown CTM method " + index1, new Object[0]);
      return ConnectedTextureMethod.NONE;
   }
}
