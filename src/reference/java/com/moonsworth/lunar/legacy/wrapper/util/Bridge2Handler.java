package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.MixinHelper2_10;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.FacingIndexBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_3;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_4;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.ichor.Annotation2;
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

@Annotation2(max = 0)
public class Bridge2Handler implements Bridge2_17 {
   private final Block field1;

   public Bridge2Handler(Block var1) {
      this.field1 = var1;
   }

   public Bridge3_23 bridge$getBlock() {
      return (Bridge3_23)this.field1;
   }

   public MissResult bridge$clip(Itemcounter6 var1, Vector3iBridge var2, Vec3Bridge var3, Vec3Bridge var4) {
      return ((Bridge3_23)this.field1).bridge$clip(var1, var2, var3, var4);
   }

   public double bridge$getCollisionHeight(Itemcounter6 var1, Horsestats20Extension2 var2) {
      return this.field1.maxY;
   }

   public double bridge$getCollisionShapeMaxY(Itemcounter6 var1, Vector3iBridge var2) {
      int var3 = var2.bridge$getX();
      int var4 = var2.bridge$getY();
      int var5 = var2.bridge$getZ();
      this.field1.setBlockBoundsBasedOnState((World)var1, var3, var4, var5);
      AxisAlignedBB var6 = this.field1.getCollisionBoundingBoxFromPool$v1_7((World)var1, var3, var4, var5);
      return var6 == null ? 0.0 : var6.maxY - var4;
   }

   public FacingIndexBridge bridge$getFacingValue() {
      throw new AbstractMethodErrorImpl();
   }

   public MixinHelper2_10 bridge$getBedPartValue() {
      throw new AbstractMethodErrorImpl();
   }

   public boolean bridge$isFluid() {
      return this.field1 == Blocks.water$v1_7 || this.field1 == Blocks.lava$v1_7;
   }

   public boolean bridge$isSolid() {
      return this.field1.blockMaterial.isSolid();
   }

   public ItemcounterType_4 bridge$getRenderShape() {
      return this.field1.getRenderType() == -1 ? ItemcounterType_4.INVISIBLE : ItemcounterType_4.MODEL;
   }

   public int bridge$getMapColor(Itemcounter2 var1, int var2, int var3, int var4) {
      Chunk var5 = (Chunk)var1;
      int var6 = (var1.bridge$getX() << 4) + var2;
      int var7 = (var1.bridge$getZ() << 4) + var4;
      if (this.field1 instanceof BlockGrass var8) {
         return 0xFF000000 | var8.colorMultiplier(var5.worldObj, var6, var3, var7);
      } else {
         return this.field1 instanceof BlockLeaves var9
            ? 0xFF000000 | var9.colorMultiplier(var5.worldObj, var6, var3, var7)
            : this.field1.getMapColor(var5.getBlockMetadata(var2, var3, var4)).colorValue;
      }
   }

   public float bridge$getHardness(Itemcounter6Extension var1, Vector3iBridge var2) {
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

   public boolean bridge$isPathfindable(Itemcounter6 var1, Vector3iBridge var2, ItemcounterType_3 var3) {
      return OpenALNative3.method1(var1, var2, this, var3);
   }

   public boolean bridge$isCollisionShapeFullBlock(Itemcounter6 var1, Horsestats20Extension2 var2) {
      return this.field1.isFullBlock();
   }

   public boolean bridge$isCollisionFaceFull(Itemcounter6 var1, Horsestats20Extension2 var2, HorsestatsType_2 var3) {
      AxisAlignedBB var4 = this.field1.getCollisionBoundingBoxFromPool$v1_7((World)var1, var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ());
      if (var4 == null) {
         return false;
      } else {
         var4 = var4.getOffsetBoundingBox$v1_7(-var2.bridge$getX(), -var2.bridge$getY(), -var2.bridge$getZ());
         if (var3 == HorsestatsType_2.DOWN) {
            return var4.minY == 0.0 && var4.minZ <= 0.0 && var4.maxZ >= 1.0 && var4.minX <= 0.0 && var4.maxX >= 1.0;
         } else if (var3 == HorsestatsType_2.UP) {
            return var4.maxY == 1.0 && var4.minZ <= 0.0 && var4.maxZ >= 1.0 && var4.minX <= 0.0 && var4.maxX >= 1.0;
         } else if (var3 == HorsestatsType_2.NORTH) {
            return var4.minZ == 0.0 && var4.minY <= 0.0 && var4.maxY >= 1.0 && var4.minX <= 0.0 && var4.maxX >= 1.0;
         } else if (var3 == HorsestatsType_2.SOUTH) {
            return var4.maxZ == 1.0 && var4.minY <= 0.0 && var4.maxY >= 1.0 && var4.minX <= 0.0 && var4.maxX >= 1.0;
         } else if (var3 == HorsestatsType_2.WEST) {
            return var4.minX == 0.0 && var4.minY <= 0.0 && var4.maxY >= 1.0 && var4.minZ <= 0.0 && var4.maxZ >= 1.0;
         } else {
            return var3 != HorsestatsType_2.EAST ? false : var4.maxX == 1.0 && var4.minY <= 0.0 && var4.maxY >= 1.0 && var4.minZ <= 0.0 && var4.maxZ >= 1.0;
         }
      }
   }

   public boolean bridge$isViewBlocking(Itemcounter6 var1, Horsestats20Extension2 var2) {
      return this.field1.blockMaterial.isOpaque();
   }

   public boolean bridge$isOcclusionFaceFull(Itemcounter6 var1, Horsestats20Extension2 var2, HorsestatsType_2 var3) {
      return this.field1.isOpaqueCube();
   }

   public boolean bridge$isSmoothDiorite() {
      throw new IllegalStateException("Not implemented");
   }

   public boolean bridge$isCubeBlock() {
      if (!(this.field1 instanceof BlockSlab) && !(this.field1 instanceof BlockStairs) && !(this.field1 instanceof BlockChest)) {
         Material var1 = this.field1.blockMaterial;
         return Material.grass == var1
            || Material.ground == var1
            || Material.wood == var1 && this.field1.isOpaqueCube()
            || Material.rock == var1
            || Material.iron == var1 && this.field1.isOpaqueCube()
            || Material.anvil == var1
            || Material.leaves == var1
            || Material.sponge == var1
            || Material.cloth == var1 && !(this.field1 instanceof BlockBed)
            || Material.sand == var1
            || Material.glass == var1 && !(this.field1 instanceof BlockPane)
            || Material.tnt == var1
            || Material.ice == var1
            || Material.packedIce == var1
            || Material.craftedSnow == var1
            || Material.cactus == var1
            || Material.clay == var1
            || Material.gourd == var1
            || Material.redstoneLight == var1;
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
