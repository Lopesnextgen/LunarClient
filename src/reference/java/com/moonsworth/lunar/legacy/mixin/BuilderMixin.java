package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.BakedQuadExtension;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.bridge.ModelBuilderExtension;
import com.moonsworth.lunar.bridge.horsestats.FacingIndexBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.SimpleBakedModel.Builder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(1)
@Mixin(Builder.class)
public abstract class BuilderMixin implements ModelBuilderExtension {
   @Shadow
   public abstract Builder setTexture(TextureAtlasSprite var1);

   @Annotation2(max = 1)
   @Shadow
   public abstract void addFaceBreakingFours(IBakedModel var1, TextureAtlasSprite var2, EnumFacing var3);

   @Shadow
   public abstract Builder addGeneralQuad(BakedQuad var1);

   @Shadow
   public abstract Builder addFaceQuad(EnumFacing var1, BakedQuad var2);

   @Shadow
   public abstract IBakedModel makeBakedModel();

   @Override
   public void bridge$setParticleTexture(Bridge4_8 var1) {
      this.setTexture((TextureAtlasSprite)var1);
   }

   @Override
   public void impl$addFaceBreakingFours(BakedModelExtension var1, Bridge4_8 var2) {
      for (EnumFacing var6 : EnumFacing.values()) {
         this.addFaceBreakingFours((IBakedModel)var1, (TextureAtlasSprite)var2, var6);
      }
   }

   @Override
   public void bridge$addGeneralQuad(BakedQuadExtension var1) {
      this.addGeneralQuad((BakedQuad)var1);
   }

   @Override
   public void bridge$addFaceQuad(FacingIndexBridge var1, BakedQuadExtension var2) {
      this.addFaceQuad((EnumFacing)var1, (BakedQuad)var2);
   }

   @Override
   public BakedModelExtension bridge$makeBakedModel() {
      return (BakedModelExtension)this.makeBakedModel();
   }
}
