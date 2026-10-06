package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.ItemCameraTransformsBridge;
import com.moonsworth.lunar.bridge.BlockPartFaceExtension;
import com.moonsworth.lunar.bridge.ModelBuilderExtension;
import com.moonsworth.lunar.bridge.horsestats.BlockModelRotationBridge;
import com.moonsworth.lunar.bridge.horsestats.FacingIndexBridge;
import com.moonsworth.lunar.bridge.horsestats.ModelFaceBakeryBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.SimpleBakedModel.Builder;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.EnumFacing;

@Annotation2(min = 1)
public class Horsestats7Handler implements ModelFaceBakeryBridge {
   private final FaceBakery field1 = new FaceBakery();

   @Override
   public com.moonsworth.lunar.bridge.QuadFactoryExtension method1() {
      return (com.moonsworth.lunar.bridge.QuadFactoryExtension)this.field1;
   }

   @Annotation2(1)
   @Override
   public ModelBuilderExtension method2(boolean var1, boolean var2, ItemCameraTransformsBridge var3) {
      return (ModelBuilderExtension)(new Builder(var1, var2, (ItemCameraTransforms)var3));
   }

   @Override
   public BlockPartFaceExtension method4(FacingIndexBridge var1, int var2, String var3, float[] var4, int var5) {
      return (BlockPartFaceExtension)(new BlockPartFace((EnumFacing)var1, var2, var3, new BlockFaceUV(var4, var5)));
   }

   @Override
   public BlockModelRotationBridge method3(int var1, int var2) {
      return (BlockModelRotationBridge)ModelRotation.getModelRotation(var1, var2);
   }

   @Override
   public FacingIndexBridge method5() {
      return (FacingIndexBridge)EnumFacing.UP;
   }

   @Override
   public FacingIndexBridge method6() {
      return (FacingIndexBridge)EnumFacing.DOWN;
   }

   @Override
   public FacingIndexBridge method7() {
      return (FacingIndexBridge)EnumFacing.NORTH;
   }

   @Override
   public FacingIndexBridge method8() {
      return (FacingIndexBridge)EnumFacing.SOUTH;
   }

   @Override
   public FacingIndexBridge method9() {
      return (FacingIndexBridge)EnumFacing.WEST;
   }

   @Override
   public FacingIndexBridge method10() {
      return (FacingIndexBridge)EnumFacing.EAST;
   }
}
