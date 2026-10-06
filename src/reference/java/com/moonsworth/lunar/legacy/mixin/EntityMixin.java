package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.glow.GlowModule;
import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DataWatcherBridge;
import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge7_7;
import com.moonsworth.lunar.bridge.MovementInputMarker;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.EntityRegistryBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.network.apollo.GlowApolloHandler;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityMinecart.EnumMinecartType;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public abstract class EntityMixin implements MovementInputMarker {
   @VersionGate(max = 1)
   @Unique
   public boolean lunar$isGlowing;
   @Unique
   private int lunar$glowingColor = -1;
   @Shadow
   public World world;
   @Shadow
   public double posX;
   @Shadow
   public double posY;
   @Shadow
   public double posZ;
   @Shadow
   public double prevPosX;
   @Shadow
   public double prevPosY;
   @Shadow
   public double motionX;
   @Shadow
   public double motionY;
   @Shadow
   public double motionZ;
   @Shadow
   public float rotationYaw;
   @Shadow
   public float rotationPitch;
   @Shadow
   public float prevRotationYaw;
   @Shadow
   public float prevRotationPitch;
   @Shadow
   public UUID entityUniqueID;
   @Shadow
   public AxisAlignedBB boundingBox;
   @Shadow
   public boolean onGround;
   @Shadow
   public int dimension;
   @Shadow
   public double lastTickPosX;
   @Shadow
   public double lastTickPosY;
   @Shadow
   public double lastTickPosZ;
   @Shadow
   public Entity ridingEntity;
   @Shadow
   public float fallDistance;
   @Shadow
   public double prevPosZ;
   @Shadow
   public float width;
   @Shadow
   public float height;
   @Shadow
   public boolean collidedHorizontally;
   @Shadow
   public boolean isDead;
   @Shadow
   public int entityId;
   @Shadow
   public boolean ignoreFrustumCheck;
   @Shadow
   public int chunkCoordX;
   @Shadow
   public int chunkCoordZ;
   @Shadow
   public int chunkCoordY;
   @Shadow
   public float stepHeight;
   @Shadow
   public Entity riddenByEntity;

   public EntityMixin() {
   }

   @Shadow
   public abstract boolean isInvisibleToPlayer(EntityPlayer player1);

   @Shadow
   public abstract int getEntityId();

   @Shadow
   public abstract Vec3 getLookVec();

   @Shadow
   public abstract float getEyeHeight();

   @Shadow
   public abstract boolean hitByEntity(Entity entity1);

   @Shadow
   public abstract boolean isInvisible();

   @Shadow
   public abstract boolean isInWater();

   @Shadow
   public abstract boolean isBurning();

   @Shadow
   public abstract DataWatcher getDataWatcher();

   @Shadow
   public abstract DataWatcher getDataManager$v1_12();

   @Shadow
   public abstract String getEntityString();

   @Shadow
   public abstract boolean hasCustomName();

   @Shadow
   public abstract String getCustomNameTag();

   @Shadow
   public abstract void setAlwaysRenderNameTag(boolean flag1);

   @Shadow
   public abstract void setCustomNameTag(String text1);

   @Shadow
   public abstract boolean isGlowing$v1_12();

   @Shadow
   public abstract void setGlowing$v1_12(boolean flag1);

   @Shadow
   public abstract Vec3 getPositionEyes(float value1);

   @Shadow
   public abstract void setPosition(double value1, double value3, double value5);

   @Shadow
   public abstract void setRotation(float value1, float value2);

   @Shadow
   public abstract void setAngles(float value1, float value2);

   @Shadow
   public abstract void turn$v1_12(float value1, float value2);

   @Shadow
   public abstract Vec3 getLook(float value1);

   @Shadow
   public abstract AxisAlignedBB getBoundingBox();

   @Shadow
   public abstract AxisAlignedBB getEntityBoundingBox();

   @Shadow
   public abstract AxisAlignedBB getRenderBoundingBox$v1_12();

   @Shadow
   public abstract boolean isInRangeToRender3d(double value1, double value3, double value5);

   @Shadow
   public abstract boolean isEntityAlive();

   @Shadow
   public abstract boolean getAlwaysRenderNameTag();

   @Shadow
   public abstract double getMountedYOffset();

   @Shadow
   public abstract int getMaxFallHeight();

   @Shadow
   public abstract boolean canBeCollidedWith();

   @Shadow
   public abstract Team getTeam();

   @Shadow
   public abstract UUID getUniqueID();

   @Shadow
   public abstract List<Entity> getPassengers$v1_12();

   @Overwrite
   public boolean isWet() {
      return Ref.MC_VERSION >= 1
         ? this.isInWater()
            || this.world.isRaining()
               && (
                  this.world.isRainingAt(new BlockPos(this.posX, this.posY, this.posZ))
                     || this.world.isRainingAt(new BlockPos(this.posX, this.posY + this.height, this.posZ))
               )
         : this.isInWater()
            || this.world.isRaining()
               && (
                  this.world.isRainingAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))
                     || this.world
                        .isRainingAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + this.height), MathHelper.floor_double(this.posZ))
               );
   }

   public void bridge$lerpTo(double value1, double value3, double value5, float value7, float value8) {
      this.setPosition(value1, value3, value5);
      this.setRotation(value7, value8);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
   }

   public void bridge$setOnGround(boolean flag1) {
      this.onGround = flag1;
   }

   public void bridge$setPosX(double value1) {
      this.posX = value1;
   }

   public void bridge$setPosY(double value1) {
      this.posY = value1;
   }

   public void bridge$setPosZ(double value1) {
      this.posZ = value1;
   }

   public void bridge$setPreviousPosX(double value1) {
      this.prevPosX = value1;
   }

   public void bridge$setPreviousPosY(double value1) {
      this.prevPosY = value1;
   }

   public void bridge$setPreviousPosZ(double value1) {
      this.prevPosZ = value1;
   }

   public void bridge$setChunkX(int number1) {
      this.chunkCoordX = number1;
   }

   public void bridge$setChunkY(int number1) {
      this.chunkCoordY = number1;
   }

   public void bridge$setChunkZ(int number1) {
      this.chunkCoordZ = number1;
   }

   public Horsestats20Extension2 bridge$getBlockPos() {
      return Ref.MC_VERSION <= 0
         ? (Horsestats20Extension2)(new Vector3i((int)Math.floor(this.posX), (int)Math.floor(this.posY), (int)Math.floor(this.posZ)))
         : (Horsestats20Extension2)(new BlockPos(this.posX, this.posY, this.posZ));
   }

   public double bridge$getMotionX() {
      return this.motionX;
   }

   public double bridge$getMotionY() {
      return this.motionY;
   }

   public void bridge$setMotionY(double value1) {
      this.motionY = value1;
   }

   public double bridge$getMotionZ() {
      return this.motionZ;
   }

   public double bridge$getRotationYaw() {
      return this.rotationYaw;
   }

   public double bridge$getRotationPitch() {
      return this.rotationPitch;
   }

   public void bridge$setRotationPitch(double value1) {
      this.rotationPitch = (float)value1;
   }

   public void bridge$setRotationYaw(double value1) {
      this.rotationYaw = (float)value1;
   }

   public double bridge$getPreviousRotationYaw() {
      return this.prevRotationYaw;
   }

   public double bridge$getPreviousRotationPitch() {
      return this.prevRotationPitch;
   }

   public UUID bridge$getUniqueID() {
      return this.entityUniqueID;
   }

   public void bridge$setUniqueID(UUID uuid1) {
      this.entityUniqueID = uuid1;
   }

   public AxisAlignedBBBridge bridge$getBoundingBox() {
      return (AxisAlignedBBBridge)this.boundingBox;
   }

   public AxisAlignedBBBridge bridge$getBoundingBox(float value1) {
      double value2 = this.prevPosX + (this.posX - this.prevPosX) * value1;
      double value4 = this.prevPosY + (this.posY - this.prevPosY) * value1;
      double value6 = this.prevPosZ + (this.posZ - this.prevPosZ) * value1;
      return (AxisAlignedBBBridge)(
         new AxisAlignedBB(
            this.boundingBox.minX - this.posX + value2,
            this.boundingBox.minY - this.posY + value4,
            this.boundingBox.minZ - this.posZ + value6,
            this.boundingBox.maxX - this.posX + value2,
            this.boundingBox.maxY - this.posY + value4,
            this.boundingBox.maxZ - this.posZ + value6
         )
      );
   }

   public AxisAlignedBBBridge bridge$getBoundingBoxForCulling() {
      return Ref.MC_VERSION >= 5 ? (AxisAlignedBBBridge)this.getRenderBoundingBox$v1_12() : (AxisAlignedBBBridge)this.boundingBox;
   }

   public boolean bridge$isOnGround() {
      return this.onGround;
   }

   public boolean bridge$isInvisibleTo(Bridge6_10 bridge6_101) {
      return this.isInvisibleToPlayer((EntityPlayer)bridge6_101);
   }

   public int bridge$getEntityId() {
      return this.getEntityId();
   }

   public Vec3Bridge bridge$getViewVector(float value1) {
      if (Ref.MC_VERSION >= 1) {
         return (Vec3Bridge)this.getLook(value1);
      }

      if (value1 == 1.0F) {
         return (Vec3Bridge)this.getLookVec();
      }

      float value2 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * value1;
      float value3 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * value1;
      float value4 = (float)Math.toRadians(-value3) - (float) Math.PI;
      float value5 = (float)Math.toRadians(-value2);
      float value6 = MathHelper.cos(value4);
      float value7 = MathHelper.sin(value4);
      float value8 = -MathHelper.cos(value5);
      float value9 = MathHelper.sin(value5);
      return (Vec3Bridge)Vec3.createVectorHelper$v1_7(value7 * value8, value9, value6 * value8);
   }

   public int bridge$getDimension() {
      return this.dimension;
   }

   public void bridge$setDimension(int number1) {
      this.dimension = number1;
   }

   public String bridge$getDimensionName() {
      return this.world.worldInfo.levelName;
   }

   public Vec3Bridge bridge$getEyePosition() {
      return this.bridge$getEyePosition(0.0F);
   }

   public Vec3Bridge bridge$getEyePosition(float value1) {
      if (Ref.MC_VERSION >= 1) {
         return (Vec3Bridge)this.getPositionEyes(value1);
      }

      if (value1 == 1.0F) {
         return (Vec3Bridge)(new Vec3(this.posX, this.posY + this.getEyeHeight(), this.posZ));
      }

      double value2 = this.prevPosX + (this.posX - this.prevPosX) * value1;
      double value4 = this.prevPosY + (this.posY - this.prevPosY) * value1 + this.getEyeHeight();
      double value6 = this.prevPosZ + (this.posZ - this.prevPosZ) * value1;
      return (Vec3Bridge)(new Vec3(value2, value4, value6));
   }

   public float bridge$getEyeHeight() {
      return this.getEyeHeight();
   }

   public MovementInputMarker bridge$getRidingEntity() {
      return (MovementInputMarker)this.ridingEntity;
   }

   public double bridge$getFallDistance() {
      return this.fallDistance;
   }

   public void bridge$resetFallDistance() {
      this.fallDistance = 0.0F;
   }

   public boolean bridge$hitByEntity(MovementInputMarker bridgeextension1) {
      return this.hitByEntity((Entity)bridgeextension1);
   }

   public double bridge$lastTickX() {
      return this.lastTickPosX;
   }

   public void bridge$setLastTickX(double value1) {
      this.lastTickPosX = value1;
   }

   public double bridge$lastTickY() {
      return this.lastTickPosY;
   }

   public void bridge$setLastTickY(double value1) {
      this.lastTickPosY = value1;
   }

   public double bridge$lastTickZ() {
      return this.lastTickPosZ;
   }

   public void bridge$setLastTickZ(double value1) {
      this.lastTickPosZ = value1;
   }

   public DataWatcherBridge bridge$getDataWatcher() {
      return (DataWatcherBridge)(Ref.MC_VERSION >= 5 ? this.getDataManager$v1_12() : this.getDataWatcher());
   }

   public boolean bridge$isOnFire() {
      return this.isBurning();
   }

   public void bridge$setPreviousRotationPitch(double value1) {
      this.prevRotationPitch = (float)value1;
   }

   public void bridge$setPreviousRotationYaw(double value1) {
      this.prevRotationYaw = (float)value1;
   }

   public boolean bridge$isInvisible() {
      return this.isInvisible();
   }

   public float bridge$getWidth() {
      return this.width;
   }

   public float bridge$getHeight() {
      return this.height;
   }

   public Vec3Bridge bridge$getPassengerOffset(MovementInputMarker bridgeextension1) {
      return Vec3Bridge.method2(0.0, this.getMountedYOffset(), 0.0);
   }

   public boolean bridge$isCollidedHorizontally() {
      return this.collidedHorizontally;
   }

   public boolean bridge$isRemoved() {
      return this.isDead;
   }

   public boolean bridge$isVisiblyCrouching() {
      return this.bridge$isSneaking();
   }

   public boolean bridge$isDiscrete() {
      return this.bridge$isSneaking();
   }

   public Component bridge$getTypeName() {
      return TextBridge.asAdventure(this.getEntityString());
   }

   public String bridge$getEntityString() {
      if (Ref.MC_VERSION == 5) {
         Optional optional1 = Bridge.method6();
         if (optional1.isPresent()) {
            return ((EntityRegistryBridge)optional1.get()).bridge$getEntityString(this);
         }

         int index2 = EntityList.REGISTRY$v1_12.getIDForObject(((Entity)this).getClass());
         return index2 != -1 ? Objects.requireNonNullElse((String)EntityList.OLD_NAMES$v1_12.get(index2), "") : "";
      } else {
         return Objects.requireNonNullElse(this.getEntityString(), "");
      }
   }

   public ItemStackBridge bridge$getPickResult() {
      Entity entity1 = (Entity)this;
      ItemStack stack2;
      if (entity1 instanceof EntityPainting) {
         stack2 = new ItemStack(Items.painting);
      } else if (entity1 instanceof EntityLeashKnot) {
         stack2 = new ItemStack(Items.lead);
      } else if (entity1 instanceof EntityItemFrame) {
         stack2 = new ItemStack(Items.item_frame);
      } else if (entity1 instanceof EntityMinecart entityminecart3) {
         stack2 = Ref.MC_VERSION >= 1 ? bridge$correspondingItem$v1_8(entityminecart3) : bridge$correspondingItem$v1_7(entityminecart3);
      } else if (entity1 instanceof EntityBoat entityboat4) {
         if (Ref.MC_VERSION >= 5) {
            stack2 = new ItemStack(entityboat4.getItemBoat$v1_12());
         } else {
            stack2 = new ItemStack(Items.boat);
         }
      } else if (Ref.MC_VERSION >= 1 && entity1 instanceof EntityArmorStand) {
         stack2 = new ItemStack(Items.armor_stand);
      } else if (Ref.MC_VERSION >= 5 && entity1 instanceof EntityEnderCrystal) {
         stack2 = new ItemStack(Items.END_CRYSTAL$v1_12);
      } else if (Ref.MC_VERSION >= 5) {
         ResourceLocation location5 = EntityList.getKey(entity1);
         if (location5 == null || !EntityList.ENTITY_EGGS$v1_12.containsKey(location5)) {
            return null;
         }

         stack2 = new ItemStack(Items.spawn_egg);
         ItemMonsterPlacer.applyEntityIdToItemStack$v1_12(stack2, location5);
      } else if (Ref.MC_VERSION == 1) {
         int number6 = EntityList.getEntityID(entity1);
         if (!EntityList.entityEggs.containsKey(number6)) {
            return null;
         }

         stack2 = new ItemStack(Items.spawn_egg);
      } else {
         int number7 = EntityList.getEntityID(entity1);
         if (!EntityList.entityEggs$v1_7.containsKey(number7)) {
            return null;
         }

         stack2 = new ItemStack(Items.spawn_egg);
      }

      return (ItemStackBridge)stack2;
   }

   @VersionGate(min = 1)
   @NotNull
   private static ItemStack bridge$correspondingItem$v1_8(EntityMinecart entityminecart0) {
      EnumMinecartType enumminecarttype1;
      if (Ref.MC_VERSION >= 5) {
         enumminecarttype1 = entityminecart0.getType();
      } else {
         enumminecarttype1 = entityminecart0.getMinecartType();
      }
      return new ItemStack(switch (enumminecarttype1) {
         case furnace -> Items.furnace_minecart;
         case chest -> Items.chest_minecart;
         case TNT -> Items.tnt_minecart;
         case hopper -> Items.hopper_minecart;
         case command_block -> Items.command_block_minecart;
         default -> Items.minecart;
      });
   }

   @VersionGate(max = 0)
   private static ItemStack bridge$correspondingItem$v1_7(EntityMinecart entityminecart0) {
      return new ItemStack(switch (entityminecart0.getMinecartType()) {
         case 1 -> Items.chest_minecart;
         case 2 -> Items.furnace_minecart;
         case 3 -> Items.tnt_minecart;
         default -> Items.minecart;
         case 5 -> Items.hopper_minecart;
         case 6 -> Items.command_block_minecart;
      });
   }

   public int bridge$getSpawnEggColor(int number1) {
      EntityEggInfo entityegginfo2;
      if (Ref.MC_VERSION >= 5) {
         entityegginfo2 = (EntityEggInfo)EntityList.ENTITY_EGGS$v1_12.get(EntityList.getKey((Entity)this));
      } else if (Ref.MC_VERSION == 1) {
         entityegginfo2 = (EntityEggInfo)EntityList.entityEggs.get(EntityList.getEntityID((Entity)this));
      } else {
         entityegginfo2 = (EntityEggInfo)EntityList.entityEggs$v1_7.get(EntityList.getEntityID((Entity)this));
      }

      if (entityegginfo2 == null) {
         return -1;
      } else {
         return number1 == 0 ? entityegginfo2.primaryColor : entityegginfo2.secondaryColor;
      }
   }

   public String bridge$getType() {
      String text1 = EntityList.getEntityString((Entity)this);
      return text1 == null ? null : text1.toLowerCase();
   }

   public boolean bridge$isRidingBoat() {
      return this.ridingEntity instanceof EntityBoat;
   }

   public boolean bridge$isGlowing() {
      Optional optional1 = Ref.method4().method84().method3(GlowModule.class);
      if (optional1.isPresent() && ((GlowApolloHandler)optional1.get()).method3(this)) {
         return true;
      } else if (Ref.MC_VERSION == 5) {
         return this.isGlowing$v1_12();
      } else {
         return Ref.method4().method40().method85().method17(arg1x -> arg1x.method50().method14() == this) ? true : this.lunar$isGlowing;
      }
   }

   public void bridge$setGlowing(boolean flag1) {
      if (Ref.MC_VERSION == 5) {
         this.setGlowing$v1_12(flag1);
      } else {
         this.lunar$isGlowing = flag1;
      }
   }

   public void bridge$setGlowingColor(int number1) {
      this.lunar$glowingColor = number1;
   }

   public int bridge$getGlowingColor() {
      return this.lunar$glowingColor;
   }

   public Component bridge$getCustomName() {
      if (Ref.MC_VERSION >= 1) {
         return this.hasCustomName() ? TextBridge.asAdventure(this.getCustomNameTag()) : null;
      } else {
         return null;
      }
   }

   public Vec3Bridge bridge$getNameTagAttachment() {
      return Vec3Bridge.method2(0.0, this.height + 0.5F, 0.0);
   }

   public void bridge$setCustomNameVisible(boolean flag1) {
      if (Ref.MC_VERSION >= 1) {
         this.setAlwaysRenderNameTag(flag1);
      }
   }

   public boolean bridge$shouldShowName() {
      if (Ref.MC_VERSION >= 1) {
         return this.getAlwaysRenderNameTag();
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   public void bridge$setCustomName(Component component1) {
      if (Ref.MC_VERSION >= 1) {
         this.setCustomNameTag(TextBridge.asLegacyString(component1));
      }
   }

   public void bridge$turn(float value1, float value2) {
      if (Ref.MC_VERSION <= 1) {
         this.setAngles(value1, value2);
      } else {
         this.turn$v1_12(value1, value2);
      }
   }

   public void bridge$setId(int number1) {
      this.entityId = number1;
   }

   public boolean bridge$shouldRenderAtSqrDistance(double value1) {
      double value3;
      if (Ref.MC_VERSION <= 0) {
         AxisAlignedBB box5 = this.getBoundingBox();
         if (box5 == null) {
            value3 = 1.0;
         } else {
            value3 = box5.getAverageEdgeLength();
         }
      } else if (Ref.MC_VERSION <= 1) {
         value3 = this.getEntityBoundingBox().getAverageEdgeLength();
      } else {
         value3 = this.getRenderBoundingBox$v1_12().getAverageEdgeLength();
      }

      if (Double.isNaN(value3)) {
         value3 = 1.0;
      }

      value3 *= 64.0;
      return value1 < value3 * value3;
   }

   public void bridge$sendPairingData(Consumer<PacketBridge> consumer1) {
      EntityTrackerEntry entitytrackerentry2;
      if (Ref.MC_VERSION <= 1) {
         entitytrackerentry2 = new EntityTrackerEntry((Entity)this, Integer.MAX_VALUE, 1, true);
      } else {
         entitytrackerentry2 = new EntityTrackerEntry((Entity)this, 0, 0, 1, true);
      }

      ArrayList list3 = new ArrayList();
      list3.add(null);
      ((Bridge7_7)entitytrackerentry2).bridge$setPacketsConsumer(consumer1);
      if (Ref.MC_VERSION <= 0) {
         entitytrackerentry2.updatePlayerList(list3);
      } else {
         entitytrackerentry2.updatePlayerList(list3);
      }

      ((Bridge7_7)entitytrackerentry2).bridge$setPacketsConsumer(null);
   }

   public boolean bridge$isAlive() {
      return this.isEntityAlive();
   }

   public boolean bridge$getNoCulling() {
      return this.ignoreFrustumCheck;
   }

   public boolean bridge$isInWaterOrBubble() {
      return this.isInWater();
   }

   public boolean bridge$canBeCollidedWith() {
      return this.canBeCollidedWith();
   }

   public double bridge$distanceToCameraSq() {
      return this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Ref.method7());
   }

   public boolean bridge$isItemFrame() {
      return (Entity)this instanceof EntityItemFrame;
   }

   public boolean bridge$isFirework() {
      return (Entity)this instanceof EntityFireworkRocket;
   }

   public boolean bridge$isWitherSkull() {
      return (Entity)this instanceof EntityWitherSkull;
   }

   public boolean bridge$isSnowball() {
      return (Entity)this instanceof EntitySnowball;
   }

   public boolean bridge$isFireball() {
      return (Entity)this instanceof EntityFireball;
   }

   public boolean bridge$isMonster() {
      Entity entity1 = (Entity)this;
      return entity1 instanceof EntityMob || entity1 instanceof EntitySlime || entity1 instanceof EntityGhast || entity1 instanceof EntityDragon;
   }

   public boolean bridge$isMob() {
      return (Entity)this instanceof EntityMob;
   }

   public boolean bridge$isLeashed() {
      return this instanceof EntityMob entitymob1 ? entitymob1.isLeashed : false;
   }

   public MovementInputMarker bridge$getLeashedToEntity() {
      return this instanceof EntityMob entitymob1 ? (MovementInputMarker)entitymob1.leashedToEntity : null;
   }

   public boolean bridge$isInWater() {
      return this.isInWater();
   }

   public float bridge$maxUpStep() {
      return this.stepHeight;
   }

   public int bridge$getMaxFallDistance() {
      return this.getMaxFallHeight();
   }

   public Optional<String> bridge$getTeamName() {
      return Ref.MC_VERSION >= 5
         ? Optional.ofNullable(this.getTeam()).map(Team::getName)
         : Optional.ofNullable(this.world.getScoreboard().getPlayersTeam(this.getUniqueID().toString())).map(Team::getRegisteredName);
   }

   public Optional<String> bridge$getBoatType() {
      if (Ref.MC_VERSION != 5) {
         return Optional.empty();
      } else {
         return this instanceof EntityBoat entityboat1 ? Optional.of(entityboat1.getBoatType$v1_12().name) : Optional.empty();
      }
   }

   public boolean bridge$isBoat() {
      return this instanceof EntityBoat;
   }

   public boolean bridge$isMinecart() {
      return this instanceof EntityMinecart;
   }

   public Vector3d bridge$getLookAngle() {
      return ((Vec3Bridge)this.getLookVec()).method6();
   }

   public boolean bridge$shouldRender(double value1, double value3, double value5) {
      return this.isInRangeToRender3d(value1, value3, value5);
   }

   public List<MovementInputMarker> bridge$getPassengers() {
      if (Ref.MC_VERSION >= 5) {
         return this.getPassengers$v1_12();
      } else {
         return this.riddenByEntity == null ? Collections.emptyList() : List.of(this.riddenByEntity);
      }
   }
}
