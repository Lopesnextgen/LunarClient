package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.util.Bridge2Handler;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBanner;
import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockPressurePlateWeighted;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.RayTraceResult.MovingObjectType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class BlockMixin implements Bridge3_23 {
   @Final
   @Shadow
   public Material material;
   @Shadow
   public boolean hasTileEntity;
   @Annotation2(min = 1)
   @Final
   @Shadow
   public BlockState blockState;

   @Annotation2(max = 0)
   @Shadow
   public abstract Item getItem(World var1, int var2, int var3, int var4);

   @Annotation2(min = 1, max = 4)
   @Shadow
   public abstract Item getItem(World var1, BlockPos var2);

   @Annotation2(min = 5)
   @Shadow
   public abstract ItemStack getItem(World var1, BlockPos var2, IBlockState var3);

   @Annotation2(max = 0)
   @Shadow
   public abstract int getDamageValue(World var1, int var2, int var3, int var4);

   @Annotation2(1)
   @Shadow
   public abstract int getDamageValue(World var1, BlockPos var2);

   @Annotation2(max = 1)
   @Shadow
   public abstract boolean isFlowerPot();

   @Shadow
   public abstract String getLocalizedName();

   @Shadow
   public abstract MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6);

   @Shadow
   public abstract MovingObjectPosition collisionRayTrace(IBlockState var1, World var2, BlockPos var3, Vec3 var4, Vec3 var5);

   @Shadow
   public abstract MovingObjectPosition collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4);

   @Shadow
   public abstract AxisAlignedBB getSelectedBoundingBoxFromPool$v1_7(World var1, int var2, int var3, int var4);

   @Shadow
   public abstract AxisAlignedBB getSelectedBoundingBox(World var1, BlockPos var2);

   @Shadow
   public abstract AxisAlignedBB getSelectedBoundingBox(IBlockState var1, World var2, BlockPos var3);

   @Annotation2(min = 1)
   @Shadow
   public abstract int damageDropped(IBlockState var1);

   @Shadow
   public abstract IBlockState getDefaultState();

   @Shadow
   public abstract Material getMaterial();

   @Shadow
   public abstract Material getMaterial(IBlockState var1);

   @Shadow
   public abstract void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2);

   @Shadow
   public abstract void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4);

   @Override
   public List<Bridge2_17> bridge$getValidStates() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return (List<Bridge2_17>)this.blockState.validStates;
      } else {
         throw new RuntimeException("bridge$blockstate not supported on this version!");
      }
   }

   @Override
   public boolean bridge$isSkull() {
      return (Block)this instanceof BlockSkull;
   }

   @Override
   public boolean bridge$isFoliage() {
      Block var1 = (Block)this;
      return var1 instanceof BlockDoublePlant || var1 instanceof BlockTallGrass;
   }

   @Override
   public boolean bridge$isFlower() {
      Block var1 = (Block)this;
      return var1 instanceof BlockFlower;
   }

   @Override
   public ItemStackBridge bridge$getStack(Vector3iBridge var1) {
      if (ThreadModuleDump63.MC_VERSION <= 1 && this.isFlowerPot()) {
         return (ItemStackBridge)(new ItemStack(Items.flower_pot));
      }

      WorldClient var2 = Minecraft.getMinecraft().theWorld;
      if (this.hasTileEntity && ThreadModuleDump63.MC_VERSION >= 1) {
         BlockPos var7 = (BlockPos)var1;
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            ItemStack var11 = this.getItem(var2, var7, var2.getBlockState(var7));
            TileEntity var13 = var2.getTileEntity(var7);
            return var13 == null ? (ItemStackBridge)var11 : (ItemStackBridge)Minecraft.getMinecraft().storeTEInStack$v1_12(var11, var13);
         } else {
            Item var10 = this.getItem(var2, var7);
            TileEntity var12 = var2.getTileEntity(var7);
            int var14 = this.getDamageValue(var2, var7);
            return var12 == null
               ? (ItemStackBridge)(new ItemStack(var10, 1, var14))
               : (ItemStackBridge)Minecraft.getMinecraft().pickBlockWithNBT(var10, var14, var12);
         }
      } else {
         ItemStack var3;
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            BlockPos var4 = (BlockPos)var1;
            var3 = this.getItem(var2, var4, var2.getBlockState(var4));
         } else if (ThreadModuleDump63.MC_VERSION >= 1) {
            BlockPos var8 = (BlockPos)var1;
            var3 = new ItemStack(this.getItem(var2, var8));
            if (var3.getItem() != null) {
               var3.setItemDamage(this.getDamageValue(var2, var8));
            }
         } else {
            int var9 = var1.bridge$getX();
            int var5 = var1.bridge$getY();
            int var6 = var1.bridge$getZ();
            var3 = new ItemStack(this.getItem(var2, var9, var5, var6));
            if (var3.getItem() != null) {
               var3.setMetadata$v1_7(this.getDamageValue(var2, var9, var5, var6));
            }
         }

         return (ItemStackBridge)var3;
      }
   }

   @Override
   public boolean bridge$isWater() {
      return this.material == Material.water;
   }

   @Override
   public boolean bridge$isAir() {
      return this.material == Material.air;
   }

   @Override
   public boolean bridge$isFire() {
      return this.material == Material.fire;
   }

   @Override
   public boolean bridge$isSign() {
      return (Block)this instanceof BlockSign;
   }

   @Override
   public boolean bridge$isPressurePlate() {
      return (Block)this instanceof BlockBasePressurePlate;
   }

   @Override
   public boolean bridge$isCarpet() {
      return (Block)this instanceof BlockCarpet;
   }

   @Override
   public boolean bridge$isSmallPot() {
      return (Block)this instanceof BlockFlowerPot;
   }

   @Override
   public boolean bridge$isBanner() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (Block)this instanceof BlockBanner : false;
   }

   @Override
   public boolean bridge$isHandOpenableTrapDoor() {
      Block var2 = (Block)this;
      return var2 instanceof BlockTrapDoor var1 ? var1.blockMaterial != Material.iron : false;
   }

   @Override
   public boolean bridge$isFenceGate() {
      return (Block)this instanceof BlockFenceGate;
   }

   @Override
   public boolean bridge$isFlippedLever(int var1, int var2, int var3) {
      Block var4 = (Block)this;
      if (var4 instanceof BlockLever var5) {
         WorldClient var6 = Minecraft.getMinecraft().theWorld;
         if (var6 == null) {
            return false;
         } else {
            return ThreadModuleDump63.MC_VERSION >= 1
               ? (Boolean)var6.getBlockState(new BlockPos(var1, var2, var3)).getValue(BlockLever.POWERED)
               : var5.isProvidingWeakPower$v1_7(var6, var1, var2, var3, 0) == 15;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean bridge$isDepressedPlate(int var1, int var2, int var3) {
      Block var4 = (Block)this;
      if (var4 instanceof BlockBasePressurePlate var5) {
         WorldClient var6 = Minecraft.getMinecraft().theWorld;
         if (var6 == null) {
            return false;
         }

         if (ThreadModuleDump63.MC_VERSION < 1) {
            return var5.isProvidingWeakPower$v1_7(var6, var1, var2, var3, 0) == 15;
         }

         if (var4 instanceof BlockPressurePlate) {
            return (Boolean)var6.getBlockState(new BlockPos(var1, var2, var3)).getValue(BlockPressurePlate.POWERED);
         }

         if (var4 instanceof BlockPressurePlateWeighted) {
            return (Integer)var6.getBlockState(new BlockPos(var1, var2, var3)).getValue(BlockPressurePlateWeighted.POWER) != 0;
         }
      }

      return false;
   }

   @Override
   public boolean bridge$isCauldron() {
      return (Block)this instanceof BlockCauldron;
   }

   @Override
   public boolean bridge$isCake() {
      return (Block)this instanceof BlockCake;
   }

   @Override
   public boolean bridge$isAnyChest() {
      return (Block)this instanceof BlockChest;
   }

   @Override
   public boolean bridge$isMushroom() {
      return (Block)this instanceof BlockMushroom;
   }

   @Override
   public boolean bridge$isFlowerPot() {
      return (Block)this instanceof BlockFlowerPot;
   }

   @Override
   public Component bridge$getName() {
      return AdventureTextBridge.asAdventure(this.getLocalizedName());
   }

   @Override
   public boolean bridge$isExcludedFromMinimap() {
      Block var1 = (Block)this;
      return var1 == Blocks.tallgrass;
   }

   @Override
   public MissResult bridge$clip(Itemcounter6 var1, Vector3iBridge var2, Vec3Bridge var3, Vec3Bridge var4) {
      MovingObjectPosition var5;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var5 = this.collisionRayTrace((IBlockState)var1.method2(var2), (World)var1, (BlockPos)var2, (Vec3)var3, (Vec3)var4);
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         var5 = this.collisionRayTrace((World)var1, (BlockPos)var2, (Vec3)var3, (Vec3)var4);
      } else {
         var5 = this.collisionRayTrace((World)var1, var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ(), (Vec3)var3, (Vec3)var4);
      }

      return var5 != null && var5.typeOfHit != MovingObjectType.MISS
         ? MissResult.method9(
            (Vec3Bridge)var5.hitVec, var2, HorsestatsType_2.byId(ThreadModuleDump63.MC_VERSION >= 1 ? var5.sideHit.index : var5.sideHit$v1_7)
         )
         : MissResult.method9();
   }

   @Override
   public AxisAlignedBBBridge bridge$getAABB(Itemcounter6 var1, Vector3iBridge var2) {
      int var3 = var2.bridge$getX();
      int var4 = var2.bridge$getY();
      int var5 = var2.bridge$getZ();
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         this.setBlockBoundsBasedOnState((World)var1, var3, var4, var5);
         return (AxisAlignedBBBridge)this.getSelectedBoundingBoxFromPool$v1_7((World)var1, var3, var4, var5).offset(-var3, -var4, -var5);
      } else if (ThreadModuleDump63.MC_VERSION <= 1) {
         this.setBlockBoundsBasedOnState((World)var1, (BlockPos)var2);
         return (AxisAlignedBBBridge)this.getSelectedBoundingBox((World)var1, (BlockPos)var2).offset(-var3, -var4, -var5);
      } else {
         return (AxisAlignedBBBridge)this.getSelectedBoundingBox((IBlockState)var1.method2(var2), (World)var1, (BlockPos)var2).offset(-var3, -var4, -var5);
      }
   }

   @Override
   public boolean bridge$hasCollision(Itemcounter6 var1, Vector3iBridge var2) {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         int var6 = var2.bridge$getX();
         int var7 = var2.bridge$getY();
         int var5 = var2.bridge$getZ();
         return ((Block)var1.bridge$getBlockAt(var6, var7, var5)).getCollisionBoundingBoxFromPool$v1_7((World)var1, var6, var7, var5) != null;
      } else {
         IBlockState var3 = (IBlockState)var1.method2(var2);
         Block var4 = (Block)var1.method4(var2);
         return ThreadModuleDump63.MC_VERSION >= 5
            ? var4.getCollisionBoundingBox(var3, (World)var1, (BlockPos)var2) != null
            : var4.getCollisionBoundingBox((World)var1, (BlockPos)var2, var3) != null;
      }
   }

   @Override
   public boolean bridge$entityCanStandOn(Itemcounter6 var1, Vector3iBridge var2, BridgeExtension var3) {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         int var8 = var2.bridge$getX();
         int var9 = var2.bridge$getY();
         int var6 = var2.bridge$getZ();
         Block var7 = (Block)var1.bridge$getBlockAt(var8, var9, var6);
         return var7.isOpaqueCube() && var7.fullBlock;
      } else {
         IBlockState var4 = (IBlockState)var1.method2(var2);
         Block var5 = var4.getBlock();
         return ThreadModuleDump63.MC_VERSION >= 5 ? var5.isTopSolid$v1_12(var4) : var5.isOpaqueCube() && var5.fullBlock;
      }
   }

   @Override
   public int bridge$getDamageValue(Itemcounter6 var1, Vector3iBridge var2) {
      return ThreadModuleDump63.MC_VERSION <= 0
         ? this.getDamageValue((World)var1, var2.bridge$getX(), var2.bridge$getY(), var2.bridge$getZ())
         : this.damageDropped(((World)var1).getBlockState((BlockPos)var2));
   }

   @Override
   public int bridge$getPreFlatteningID() {
      return Block.getIdFromBlock((Block)this);
   }

   @Override
   public String bridge$getRegistryName() {
      if (ThreadModuleDump63.MC_VERSION == 0) {
         String var2 = Block.blockRegistry$v1_7.getNameForObject((Block)this);
         return var2 != null ? var2 : Item.itemRegistry$v1_7.getNameForObject(Item.getItemFromBlock((Block)this));
      } else {
         ResourceLocation var1 = (ResourceLocation)Block.blockRegistry.getNameForObject((Block)this);
         return var1 != null ? var1.toString() : ((ResourceLocation)Item.itemRegistry.getNameForObject(Item.getItemFromBlock((Block)this))).toString();
      }
   }

   @Override
   public float bridge$getDestroySpeed() {
      Object var1;
      if (ThreadModuleDump63.MC_VERSION == 0) {
         var1 = Minecraft.getMinecraft().thePlayer$v1_7;
      } else {
         var1 = Minecraft.getMinecraft().thePlayer;
      }

      float var2 = this.calculateSpeedFactor((EntityPlayer)var1);
      Block var3 = (Block)this;
      float var4 = 0.0F;
      boolean var5 = false;
      if (ThreadModuleDump63.MC_VERSION == 0) {
         var4 = var3.getBlockHardness(null, 0, 0, 0);
         var5 = var1.canHarvestBlock(var3);
      } else if (ThreadModuleDump63.MC_VERSION == 1) {
         var4 = var3.getBlockHardness(null, null);
         var5 = var1.canHarvestBlock(var3);
      } else if (ThreadModuleDump63.MC_VERSION == 5) {
         var4 = var3.getBlockHardness(null, null, null);
         var5 = var1.canHarvestBlock(var3.getDefaultState());
      }

      float var6 = (float)(var4 * (var5 ? 1.5 : 5.0));
      return var6 / var2;
   }

   private float calculateSpeedFactor(EntityPlayer var1) {
      Block var2 = (Block)this;
      ItemStack var3 = ThreadModuleDump63.MC_VERSION == 5 ? var1.getHeldItem(EnumHand.MAIN_HAND) : var1.getCurrentEquippedItem();
      float var4 = 1.0F;
      if (var3 != null) {
         var4 = ThreadModuleDump63.MC_VERSION == 5 ? var3.getDestroySpeed$v1_12(var2.getDefaultState()) : var3.getStrVsBlock(var2);
      }

      if (var4 > 1.0F) {
         int var5 = EnchantmentHelper.getEfficiencyModifier(var1);
         if (var5 > 0 && var3 != null) {
            var4 += var5 * var5 + 1;
         }
      }

      if (var1.isPotionActive(ThreadModuleDump63.MC_VERSION == 5 ? MobEffects.HASTE : Potion.digSpeed)) {
         PotionEffect var6 = var1.getActivePotionEffect(ThreadModuleDump63.MC_VERSION == 5 ? MobEffects.HASTE : Potion.digSpeed);
         var4 *= 1.0F + (var6.getAmplifier() + 1) * 0.2F;
      }

      if (var1.isPotionActive(ThreadModuleDump63.MC_VERSION == 5 ? MobEffects.MINING_FATIGUE : Potion.digSlowdown)) {
         var4 *= switch (var1.getActivePotionEffect(ThreadModuleDump63.MC_VERSION == 5 ? MobEffects.MINING_FATIGUE : Potion.digSlowdown).getAmplifier()) {
            case 0 -> 0.3F;
            case 1 -> 0.09F;
            case 2 -> 0.0027F;
            default -> 8.1E-4F;
         };
      }

      if (var1.isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(var1)) {
         var4 /= 5.0F;
      }

      if (!var1.onGround) {
         var4 /= 5.0F;
      }

      return var4;
   }

   @Override
   public boolean bridge$breaksByPickaxe() {
      ItemTool var1 = (ItemTool)Items.wooden_pickaxe;
      ItemStack var2 = new ItemStack(var1);
      return (ThreadModuleDump63.MC_VERSION == 5 ? var1.getDestroySpeed$v1_12(var2, this.getDefaultState()) : var1.getStrVsBlock(var2, (Block)this)) != 1.0F;
   }

   @Override
   public boolean bridge$breaksByAxe() {
      ItemTool var1 = (ItemTool)Items.wooden_axe;
      ItemStack var2 = new ItemStack(var1);
      return (ThreadModuleDump63.MC_VERSION == 5 ? var1.getDestroySpeed$v1_12(var2, this.getDefaultState()) : var1.getStrVsBlock(var2, (Block)this)) != 1.0F
         && this.bridge$getDestroySpeed() > 0.0F;
   }

   @Override
   public boolean bridge$breaksByShovel() {
      ItemTool var1 = (ItemTool)Items.wooden_shovel;
      ItemStack var2 = new ItemStack(var1);
      return (ThreadModuleDump63.MC_VERSION == 5 ? var1.getDestroySpeed$v1_12(var2, this.getDefaultState()) : var1.getStrVsBlock(var2, (Block)this)) != 1.0F;
   }

   @Override
   public boolean bridge$breaksByHoe() {
      return false;
   }

   @Override
   public boolean bridge$breaksByShears() {
      BlockMixin var1 = this;
      boolean var2 = (ThreadModuleDump63.MC_VERSION == 5 ? this.getMaterial(null) : this.getMaterial()) == Material.leaves;
      return var2
         || var1 == Blocks.web
         || var1 instanceof BlockDoublePlant
         || var1 instanceof BlockBush && !(var1 instanceof BlockFlower) && var1 != Blocks.sapling
         || var1 == Blocks.vine
         || var1 == Blocks.tripwire
         || var1 == Blocks.wool;
   }

   @Override
   public boolean bridge$isCubeBlock() {
      return ((Bridge2_17)(ThreadModuleDump63.MC_VERSION == 0 ? new Bridge2Handler((Block)this) : this.getDefaultState())).bridge$isCubeBlock();
   }

   @Override
   public boolean bridge$isSpawner() {
      return this == Blocks.mob_spawner;
   }

   @Override
   public boolean bridge$isGlass() {
      return this == Blocks.glass || this == Blocks.stained_glass;
   }
}
