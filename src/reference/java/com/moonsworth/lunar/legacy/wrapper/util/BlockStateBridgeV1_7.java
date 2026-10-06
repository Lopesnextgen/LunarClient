package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.BedPartTypeBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.minecraft.EnumFacingBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.world.PathTypeBridge;
import com.moonsworth.lunar.bridge.world.BlockRenderTypeBridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

@VersionGate(max = 0)
public class BlockStateBridgeV1_7 implements BlockStateBridge {
   private final Block field1;

   public BlockStateBridgeV1_7(Block block1) {
      this.field1 = block1;
   }

   public Bridge3_23 bridge$getBlock() {
      return (Bridge3_23)this.field1;
   }

   public MissResult bridge$clip(Itemcounter6 itemcounter61, Vec3iBridge horsestats202, Vec3Bridge horsestats153, Vec3Bridge horsestats154) {
      return ((Bridge3_23)this.field1).bridge$clip(itemcounter61, horsestats202, horsestats153, horsestats154);
   }

   public double bridge$getCollisionHeight(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22) {
      return this.field1.maxY;
   }

   public double bridge$getCollisionShapeMaxY(Itemcounter6 itemcounter61, Vec3iBridge horsestats202) {
      int number3 = horsestats202.bridge$getX();
      int number4 = horsestats202.bridge$getY();
      int number5 = horsestats202.bridge$getZ();
      this.field1.setBlockBoundsBasedOnState((World)itemcounter61, number3, number4, number5);
      AxisAlignedBB box6 = this.field1.getCollisionBoundingBoxFromPool$v1_7((World)itemcounter61, number3, number4, number5);
      return box6 == null ? 0.0 : box6.maxY - number4;
   }

   public EnumFacingBridge bridge$getFacingValue() {
      throw new AbstractMethodErrorImpl();
   }

   public BedPartTypeBridge bridge$getBedPartValue() {
      throw new AbstractMethodErrorImpl();
   }

   public boolean bridge$isFluid() {
      return this.field1 == Blocks.water$v1_7 || this.field1 == Blocks.lava$v1_7;
   }

   public boolean bridge$isSolid() {
      return this.field1.blockMaterial.isSolid();
   }

   public BlockRenderTypeBridge bridge$getRenderShape() {
      return this.field1.getRenderType() == -1 ? BlockRenderTypeBridge.INVISIBLE : BlockRenderTypeBridge.MODEL;
   }

   public int bridge$getMapColor(ChunkBridge itemcounter21, int number2, int number3, int number4) {
      Chunk chunk5 = (Chunk)itemcounter21;
      int number6 = (itemcounter21.bridge$getX() << 4) + number2;
      int number7 = (itemcounter21.bridge$getZ() << 4) + number4;
      if (this.field1 instanceof BlockGrass blockgrass8) {
         return 0xFF000000 | blockgrass8.colorMultiplier(chunk5.worldObj, number6, number3, number7);
      } else {
         return this.field1 instanceof BlockLeaves blockleaves9
            ? 0xFF000000 | blockleaves9.colorMultiplier(chunk5.worldObj, number6, number3, number7)
            : this.field1.getMapColor(chunk5.getBlockMetadata(number2, number3, number4)).colorValue;
      }
   }

   public float bridge$getHardness(WorldBridgeExtension itemcounter6extension1, Vec3iBridge horsestats202) {
      throw new AbstractMethodErrorImpl();
   }

   public boolean bridge$breaksWithPickaxe() {
      throw new AbstractMethodErrorImpl();
   }

   public boolean bridge$breaksWithAxe() {
      throw new AbstractMethodErrorImpl();
   }

   public boolean bridge$breaksWithShovel() {
      throw new AbstractMethodErrorImpl();
   }

   public int bridge$getBites() {
      throw new AbstractMethodErrorImpl();
   }

   public int bridge$getLayersValue() {
      return 5;
   }

   public boolean bridge$getOpenValue() {
      return false;
   }

   public boolean bridge$isPathfindable(Itemcounter6 itemcounter61, Vec3iBridge horsestats202, PathTypeBridge itemcountertype_33) {
      return BlockPathEvaluator.method1(itemcounter61, horsestats202, this, itemcountertype_33);
   }

   public boolean bridge$isCollisionShapeFullBlock(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22) {
      return this.field1.isFullBlock();
   }

   public boolean bridge$isCollisionFaceFull(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22, HorsestatsType_2 horsestatstype_23) {
      AxisAlignedBB box4 = this.field1.getCollisionBoundingBoxFromPool$v1_7((World)itemcounter61, horsestats20extension22.bridge$getX(), horsestats20extension22.bridge$getY(), horsestats20extension22.bridge$getZ());
      if (box4 == null) {
         return false;
      } else {
         box4 = box4.getOffsetBoundingBox$v1_7(-horsestats20extension22.bridge$getX(), -horsestats20extension22.bridge$getY(), -horsestats20extension22.bridge$getZ());
         if (horsestatstype_23 == HorsestatsType_2.DOWN) {
            return box4.minY == 0.0 && box4.minZ <= 0.0 && box4.maxZ >= 1.0 && box4.minX <= 0.0 && box4.maxX >= 1.0;
         } else if (horsestatstype_23 == HorsestatsType_2.UP) {
            return box4.maxY == 1.0 && box4.minZ <= 0.0 && box4.maxZ >= 1.0 && box4.minX <= 0.0 && box4.maxX >= 1.0;
         } else if (horsestatstype_23 == HorsestatsType_2.NORTH) {
            return box4.minZ == 0.0 && box4.minY <= 0.0 && box4.maxY >= 1.0 && box4.minX <= 0.0 && box4.maxX >= 1.0;
         } else if (horsestatstype_23 == HorsestatsType_2.SOUTH) {
            return box4.maxZ == 1.0 && box4.minY <= 0.0 && box4.maxY >= 1.0 && box4.minX <= 0.0 && box4.maxX >= 1.0;
         } else if (horsestatstype_23 == HorsestatsType_2.WEST) {
            return box4.minX == 0.0 && box4.minY <= 0.0 && box4.maxY >= 1.0 && box4.minZ <= 0.0 && box4.maxZ >= 1.0;
         } else {
            return horsestatstype_23 != HorsestatsType_2.EAST ? false : box4.maxX == 1.0 && box4.minY <= 0.0 && box4.maxY >= 1.0 && box4.minZ <= 0.0 && box4.maxZ >= 1.0;
         }
      }
   }

   public boolean bridge$isViewBlocking(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22) {
      return this.field1.blockMaterial.isOpaque();
   }

   public boolean bridge$isOcclusionFaceFull(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22, HorsestatsType_2 horsestatstype_23) {
      return this.field1.isOpaqueCube();
   }

   public boolean bridge$isSmoothDiorite() {
      throw new IllegalStateException("Not implemented");
   }

   public boolean bridge$isCubeBlock() {
      if (!(this.field1 instanceof BlockSlab) && !(this.field1 instanceof BlockStairs) && !(this.field1 instanceof BlockChest)) {
         Material material1 = this.field1.blockMaterial;
         return Material.grass == material1
            || Material.ground == material1
            || Material.wood == material1 && this.field1.isOpaqueCube()
            || Material.rock == material1
            || Material.iron == material1 && this.field1.isOpaqueCube()
            || Material.anvil == material1
            || Material.leaves == material1
            || Material.sponge == material1
            || Material.cloth == material1 && !(this.field1 instanceof BlockBed)
            || Material.sand == material1
            || Material.glass == material1 && !(this.field1 instanceof BlockPane)
            || Material.tnt == material1
            || Material.ice == material1
            || Material.packedIce == material1
            || Material.craftedSnow == material1
            || Material.cactus == material1
            || Material.clay == material1
            || Material.gourd == material1
            || Material.redstoneLight == material1;
      } else {
         return true;
      }
   }

   public boolean bridge$hasBlockEntity() {
      return this.field1.hasTileEntity();
   }

   public boolean bridge$isFlowerPot() {
      return this.field1 == Blocks.flower_pot;
   }

   public boolean bridge$isSign() {
      return this.field1 instanceof BlockSign;
   }
}
