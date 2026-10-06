package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.BakedQuadBridge;
import com.moonsworth.lunar.bridge.BlockPartFaceBridge;
import com.moonsworth.lunar.bridge.BakedQuadFactoryBridge;
import com.moonsworth.lunar.bridge.minecraft.ModelRotationBridge;
import com.moonsworth.lunar.bridge.minecraft.EnumFacingBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import org.lwjgl.util.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(FaceBakery.class)
public abstract class FaceBakeryMixin implements BakedQuadFactoryBridge {
   public FaceBakeryMixin() {
   }

   @Shadow
   public abstract BakedQuad makeBakedQuad(
      Vector3f vector3f1,
      Vector3f vector3f2,
      BlockPartFace blockpartface3,
      TextureAtlasSprite textureatlassprite4,
      EnumFacing facing5,
      ModelRotation modelrotation6,
      BlockPartRotation blockpartrotation7,
      boolean flag8,
      boolean flag9
   );

   public BakedQuadBridge bridge$makeBakedQuad(
      org.joml.Vector3f vector3f1, org.joml.Vector3f vector3f2, BlockPartFaceBridge mixinhelper5_23, Bridge4_8 bridge4_84, EnumFacingBridge horsestats255, ModelRotationBridge horsestats26
   ) {
      return (BakedQuadBridge)this.makeBakedQuad(
         new Vector3f(vector3f1.x, vector3f1.y, vector3f1.z),
         new Vector3f(vector3f2.x, vector3f2.y, vector3f2.z),
         (BlockPartFace)mixinhelper5_23,
         (TextureAtlasSprite)bridge4_84,
         (EnumFacing)horsestats255,
         (ModelRotation)horsestats26,
         new BlockPartRotation(new Vector3f(8.0F, 0.0F, 8.0F), Axis.Y, 0.0F, false),
         false,
         false
      );
   }
}
