package com.moonsworth.lunar.legacy.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
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
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.util.OpenALNative3;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockProperties;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Annotation2(min = 1)
@Mixin(BlockStateBase.class)
public abstract class BlockStateBaseMixin implements Bridge2_17, IBlockState {
   @Unique
   private final AtomicReference<Object> lunar$axeBlocks = new AtomicReference<>();

   @Override
   public Bridge3_23 bridge$getBlock() {
      return (Bridge3_23)this.getBlock();
   }

   @Override
   public MissResult bridge$clip(Itemcounter6 var1, Vector3iBridge var2, Vec3Bridge var3, Vec3Bridge var4) {
      return ((Bridge3_23)this.getBlock()).bridge$clip(var1, var2, var3, var4);
   }

   @Override
   public double bridge$getCollisionHeight(Itemcounter6 var1, Horsestats20Extension2 var2) {
      if (ThreadModuleDump63.MC_VERSION == 1) {
         return this.getBlock().maxY;
      }

      AxisAlignedBB var3 = ((IBlockProperties)this).getCollisionBoundingBox((IBlockAccess)var1, (BlockPos)var2);
      return var3 == null ? 0.0 : var3.maxY;
   }

   @Override
   public double bridge$getCollisionShapeMaxY(Itemcounter6 var1, Vector3iBridge var2) {
      if (ThreadModuleDump63.MC_VERSION == 1) {
         this.getBlock().setBlockBoundsBasedOnState((IBlockAccess)var1, (BlockPos)var2);
         AxisAlignedBB var4 = this.getBlock().getCollisionBoundingBox((World)var1, (BlockPos)var2, this);
         return var4 == null ? 0.0 : var4.maxY - var2.bridge$getY();
      } else {
         AxisAlignedBB var3 = ((IBlockProperties)this).getCollisionBoundingBox((IBlockAccess)var1, (BlockPos)var2);
         return var3 == null ? 0.0 : var3.maxY;
      }
   }

   @Override
   public FacingIndexBridge bridge$getFacingValue() {
      return (FacingIndexBridge)this.getValue(BlockDirectional.FACING);
   }

   @Override
   public MixinHelper2_10 bridge$getBedPartValue() {
      return (MixinHelper2_10)this.getValue(BlockBed.PART);
   }

   @Override
   public int bridge$getMapColor(Itemcounter2 var1, int var2, int var3, int var4) {
      Chunk var5 = (Chunk)var1;
      BlockPos var6 = new BlockPos((var1.bridge$getX() << 4) + var2, var3, (var1.bridge$getZ() << 4) + var4);
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         int var10 = Minecraft.getMinecraft().blockColors$v1_12.getColor(this, (World)var1.bridge$getWorld(), var6);
         if (var10 != -1) {
            return var10;
         }

         IBlockProperties var11 = (IBlockProperties)this;
         return var11.getMapColor((World)var1.bridge$getWorld(), var6).colorValue;
      } else if (this.getBlock() instanceof BlockGrass var7) {
         return 0xFF000000 | var7.colorMultiplier(var5.worldObj, var6);
      } else {
         return this.getBlock() instanceof BlockLeaves var8
            ? 0xFF000000 | var8.colorMultiplier(var5.worldObj, var6)
            : this.getBlock().getMapColor(this).getMapColor(2);
      }
   }

   @Override
   public boolean bridge$isFluid() {
      return this.getBlock() == Blocks.water || this.getBlock() == Blocks.lava;
   }

   @Override
   public boolean bridge$isSolid() {
      return this.getBlock().blockMaterial.isSolid();
   }

   @Override
   public boolean bridge$isPathfindable(Itemcounter6 var1, Vector3iBridge var2, ItemcounterType_3 var3) {
      return OpenALNative3.method1(var1, var2, this, var3);
   }

   @Override
   public int bridge$getBites() {
      return (Integer)this.getValue(BlockCake.BITES);
   }

   @Override
   public int bridge$getLayersValue() {
      return (Integer)this.getValue(BlockSnow.LAYERS);
   }

   @Override
   public boolean bridge$getOpenValue() {
      return (Boolean)this.getValue(BlockTrapDoor.OPEN);
   }

   @Override
   public float bridge$getHardness(Itemcounter6Extension var1, Vector3iBridge var2) {
      if (ThreadModuleDump63.MC_VERSION == 0) {
         return this.getBlock().getBlockHardness((World)var1, var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ());
      } else {
         return ThreadModuleDump63.MC_VERSION == 1
            ? this.getBlock().getBlockHardness((World)var1, (BlockPos)var2)
            : this.getBlock().getBlockHardness(this, (World)var1, (BlockPos)var2);
      }
   }

   @Override
   public boolean bridge$breaksWithPickaxe() {
      ItemTool var1 = (ItemTool)Items.wooden_pickaxe;
      ItemStack var2 = new ItemStack(var1);
      return ThreadModuleDump63.MC_VERSION <= 1 ? var1.getStrVsBlock(var2, this.getBlock()) != 1.0F : var1.getDestroySpeed$v1_12(var2, this) != 1.0F;
   }

   @Override
   public boolean bridge$breaksWithAxe() {
      ItemTool var1 = (ItemTool)Items.wooden_axe;
      ItemStack var2 = new ItemStack(var1);
      boolean var3;
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         var3 = var1.getStrVsBlock(var2, this.getBlock()) != 1.0F;
      } else {
         var3 = var1.getDestroySpeed$v1_12(var2, this) != 1.0F;
      }

      boolean var4 = this.getLunar$axeBlocks().contains(this.getBlock());
      return var3 || var4;
   }

   @Override
   public boolean bridge$breaksWithShovel() {
      ItemTool var1 = (ItemTool)Items.wooden_shovel;
      ItemStack var2 = new ItemStack(var1);
      return ThreadModuleDump63.MC_VERSION <= 1 ? var1.getStrVsBlock(var2, this.getBlock()) != 1.0F : var1.getDestroySpeed$v1_12(var2, this) != 1.0F;
   }

   @Override
   public ItemcounterType_4 bridge$getRenderShape() {
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         return this.getBlock().getRenderType() == -1 ? ItemcounterType_4.INVISIBLE : ItemcounterType_4.MODEL;
      }

      return switch (this.getBlock().getRenderType(this)) {
         case INVISIBLE -> ItemcounterType_4.INVISIBLE;
         case ENTITYBLOCK_ANIMATED -> ItemcounterType_4.ENTITYBLOCK_ANIMATED;
         case LIQUID, MODEL -> ItemcounterType_4.MODEL;
         default -> throw new IncompatibleClassChangeError();
      };
   }

   @Override
   public boolean bridge$isCollisionShapeFullBlock(Itemcounter6 var1, Horsestats20Extension2 var2) {
      return ThreadModuleDump63.MC_VERSION <= 1 ? this.getBlock().isFullBlock() : this.getBlock().isFullBlock(this);
   }

   @Override
   public boolean bridge$isCollisionFaceFull(Itemcounter6 var1, Horsestats20Extension2 var2, HorsestatsType_2 var3) {
      Block var4 = this.getBlock();
      AxisAlignedBB var5;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var5 = var4.getCollisionBoundingBox(this, (World)var1, (BlockPos)var2);
      } else {
         var5 = var4.getCollisionBoundingBox((World)var1, new BlockPos(0, 0, 0), this);
      }

      if (var5 == null) {
         return false;
      } else if (var3 == HorsestatsType_2.DOWN) {
         return var5.minY == 0.0 && var5.minZ <= 0.0 && var5.maxZ >= 1.0 && var5.minX <= 0.0 && var5.maxX >= 1.0;
      } else if (var3 == HorsestatsType_2.UP) {
         return var5.maxY == 1.0 && var5.minZ <= 0.0 && var5.maxZ >= 1.0 && var5.minX <= 0.0 && var5.maxX >= 1.0;
      } else if (var3 == HorsestatsType_2.NORTH) {
         return var5.minZ == 0.0 && var5.minY <= 0.0 && var5.maxY >= 1.0 && var5.minX <= 0.0 && var5.maxX >= 1.0;
      } else if (var3 == HorsestatsType_2.SOUTH) {
         return var5.maxZ == 1.0 && var5.minY <= 0.0 && var5.maxY >= 1.0 && var5.minX <= 0.0 && var5.maxX >= 1.0;
      } else if (var3 == HorsestatsType_2.WEST) {
         return var5.minX == 0.0 && var5.minY <= 0.0 && var5.maxY >= 1.0 && var5.minZ <= 0.0 && var5.maxZ >= 1.0;
      } else {
         return var3 != HorsestatsType_2.EAST ? false : var5.maxX == 1.0 && var5.minY <= 0.0 && var5.maxY >= 1.0 && var5.minZ <= 0.0 && var5.maxZ >= 1.0;
      }
   }

   @Override
   public boolean bridge$isViewBlocking(Itemcounter6 var1, Horsestats20Extension2 var2) {
      return this.getBlock().blockMaterial.isOpaque();
   }

   @Override
   public boolean bridge$isOcclusionFaceFull(Itemcounter6 var1, Horsestats20Extension2 var2, HorsestatsType_2 var3) {
      return ThreadModuleDump63.MC_VERSION >= 5
         ? this.getBlock().getBlockFaceShape$v1_12((IBlockAccess)var1, this, (BlockPos)var2, EnumFacing.byIndex$v1_12(var3.getId())) == BlockFaceShape.SOLID
         : this.getBlock().isVisuallyOpaque();
   }

   @Override
   public boolean bridge$isSmoothDiorite() {
      return this.getBlock() instanceof BlockStone && this.getValue(BlockStone.VARIANT) == EnumType.DIORITE_SMOOTH;
   }

   @Override
   public boolean bridge$isFlowerPot() {
      return this.getBlock() == Blocks.flower_pot;
   }

   @Override
   public boolean bridge$isSign() {
      return this.getBlock() instanceof BlockSign;
   }

   @Override
   public boolean bridge$isCubeBlock() {
      Block var1 = this.getBlock();
      if (!(var1 instanceof BlockSlab) && !(var1 instanceof BlockStairs) && !(var1 instanceof BlockChest) && !(var1 instanceof BlockDaylightDetector)) {
         Material var2 = var1.blockMaterial;
         return Material.grass == var2
            || Material.ground == var2
            || Material.wood == var2 && this.lunar$isOpaqueCube(var1)
            || Material.rock == var2
            || Material.iron == var2 && this.lunar$isOpaqueCube(var1)
            || Material.anvil == var2
            || Material.leaves == var2
            || Material.sponge == var2
            || Material.cloth == var2 && !(var1 instanceof BlockBed)
            || Material.sand == var2
            || Material.glass == var2 && !(var1 instanceof BlockPane)
            || Material.tnt == var2
            || Material.ice == var2
            || Material.packedIce == var2
            || Material.craftedSnow == var2
            || Material.cactus == var2
            || Material.clay == var2
            || Material.gourd == var2
            || Material.redstoneLight == var2;
      } else {
         return true;
      }
   }

   @Unique
   private boolean lunar$isOpaqueCube(Block var1) {
      return ThreadModuleDump63.MC_VERSION == 5 ? var1.isOpaqueCube(var1.getDefaultState()) : var1.isOpaqueCube();
   }

   @Override
   public ImmutableMap<String, Comparable<?>> bridge$getStringProperties() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.getProperties().entrySet().stream().collect(ImmutableMap.toImmutableMap(var0 -> ((IProperty)var0.getKey()).getName(), Entry::getValue));
      }

      Builder var1 = ImmutableMap.builder();
      this.getProperties().forEach((var1x, var2) -> var1.put(var1x.getName(), var2));
      return var1.build();
   }

   @Override
   public boolean bridge$hasBlockEntity() {
      return this.getBlock().hasTileEntity();
   }

   @Generated
   public Set<Block> getLunar$axeBlocks() {
      Object var1 = this.lunar$axeBlocks.get();
      if (var1 == null) {
         synchronized (this.lunar$axeBlocks) {
            var1 = this.lunar$axeBlocks.get();
            if (var1 == null) {
               Set var3 = Set.of(
                  Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder
               );
               var1 = var3 == null ? this.lunar$axeBlocks : var3;
               this.lunar$axeBlocks.set(var1);
            }
         }
      }

      return (Set<Block>)(var1 == this.lunar$axeBlocks ? null : var1);
   }
}
