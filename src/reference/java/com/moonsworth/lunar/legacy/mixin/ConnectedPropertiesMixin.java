package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.optifine.ConnectedProperties;
import com.moonsworth.lunar.bridge.optifine.ConnectedTextureMethod;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import net.optifine.config.MatchBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(net.optifine.ConnectedProperties.class)
public class ConnectedPropertiesMixin implements ConnectedProperties {
   @Shadow
   public String[] matchTiles;
   @Shadow
   public MatchBlock[] matchBlocks;
   @Shadow
   public String[] tiles;
   @Shadow
   public int method;
   @Shadow
   public int[] metadatas;

   public ConnectedPropertiesMixin() {
   }

   public String[] bridge$getMatchTiles() {
      return this.matchTiles;
   }

   public String[] bridge$parseMatchBlocks() {
      if (this.matchBlocks == null) {
         return null;
      }

      HashSet set1 = new HashSet();

      for (MatchBlock matchblock5 : this.matchBlocks) {
         if (matchblock5 != null) {
            Block block6 = Block.getBlockById(matchblock5.getBlockId());
            if (block6 != null && block6 != Blocks.air) {
               ResourceLocation location7 = (ResourceLocation)Block.blockRegistry.getNameForObject(block6);
               if (location7 != null) {
                  set1.add(location7.toString());
               }

               ModelManager modelmanager8 = Minecraft.getMinecraft().modelManager;
               if (modelmanager8 != null && modelmanager8.getBlockModelShapes() != null) {
                  HashSet set9 = new HashSet();
                  if (matchblock5.getMetadatas() != null) {
                     for (int index13 : matchblock5.getMetadatas()) {
                        set9.add(index13);
                     }
                  }

                  if (this.metadatas != null) {
                     for (int index21 : this.metadatas) {
                        set9.add(index21);
                     }
                  }

                  for (int index18 : set9) {
                     TextureAtlasSprite textureatlassprite20 = null;

                     try {
                        IBlockState state22 = block6.getStateFromMeta(index18);
                        if (state22 != null) {
                           textureatlassprite20 = modelmanager8.getBlockModelShapes().getTexture(state22);
                        }
                     } catch (Throwable exception14) {
                     }

                     if (textureatlassprite20 != null && !textureatlassprite20.iconName.equals("missingno")) {
                        set1.add(textureatlassprite20.iconName);
                     }
                  }
               }
            }
         }
      }

      return set1.toArray(new String[0]);
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
