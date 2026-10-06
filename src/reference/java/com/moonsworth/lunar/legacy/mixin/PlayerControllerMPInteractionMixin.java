package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.combat.CombatModule;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.lunarclient.apollo.module.packetenrichment.raytrace.BlockHitResult;
import com.lunarclient.apollo.module.packetenrichment.raytrace.Direction;
import com.lunarclient.apollo.module.packetenrichment.raytrace.EntityHitResult;
import com.lunarclient.apollo.module.packetenrichment.raytrace.MissResult;
import com.lunarclient.apollo.module.packetenrichment.raytrace.RayTraceResult;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.hitbox.Hitbox2;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.Highlight3Iterator31;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.player.GameModeChangeEvent;
import com.moonsworth.lunar.client.event.entity.EventInteractEntity;
import com.moonsworth.lunar.client.event.combat.PreAttackEntityEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdateNotify;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockPlacement;
import com.moonsworth.lunar.client.hitcolor.FogHandler;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.util.Bridge2Handler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Session;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.RayTraceResult.MovingObjectType;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public class PlayerControllerMPInteractionMixin {
   @Final
   @Shadow
   public Minecraft mc;
   @Shadow
   public GameType currentGameType$v1_12;
   @Shadow
   public net.minecraft.world.WorldSettings.GameType currentGameType;
   @Shadow
   public int currentPlayerItem;
   @Shadow
   public boolean isHittingBlock;

   @Annotation2(1)
   @WrapOperation(
      method = "onPlayerDamageBlock$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;isHittingPosition$v1_8(Lnet/minecraft/util/math/BlockPos;)Z")
   )
   private boolean apollo$restartDigWhenNotHitting$v1_8(PlayerControllerMP var1, BlockPos var2, Operation<Boolean> var3) {
      return !this.isHittingBlock && this.apollo$isDigAndUseAllowed() ? false : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @Unique
   private boolean apollo$isDigAndUseAllowed() {
      return ThreadModuleDump63.method4()
         .method84()
         .<ApolloModuleHandler>method3(CombatModule.class)
         .filter(var0 -> (Boolean)var0.getOptions().get(CombatModule.ALLOW_DIG_AND_USE))
         .isPresent();
   }

   @Inject(method = "attackEntity", at = @At("HEAD"))
   private void lunar$attackEntity(EntityPlayer var1, Entity var2, CallbackInfo var3) {
      FogHandler.method1((BridgeExtension)var1).method1(ThreadModuleDump63.method14());
   }

   @Annotation2(min = 5)
   @Inject(
      method = "onPlayerDamageBlock$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;onPlayerDestroyBlock$v1_12(Lnet/minecraft/util/math/BlockPos;)Z"
      )
   )
   private void lunar$onBlockDestroy$v1_12(BlockPos var1, EnumFacing var2, CallbackInfoReturnable<Boolean> var3, @Local Block var4) {
      this.lunar$onBlockDestroy(var4);
   }

   @Annotation2(1)
   @Inject(
      method = "onPlayerDamageBlock$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;onPlayerDestroyBlock(Lnet/minecraft/util/BlockPos;Lnet/minecraft/util/EnumFacing;)Z"
      )
   )
   private void lunar$onBlockDestroy(BlockPos var1, EnumFacing var2, CallbackInfoReturnable<Boolean> var3, @Local Block var4) {
      this.lunar$onBlockDestroy(var4);
   }

   @Annotation2(max = 0)
   @Inject(
      method = "onPlayerDamageBlock$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;onPlayerDestroyBlock$v1_7(IIII)Z")
   )
   private void lunar$onBlockDestroy(int var1, int var2, int var3, int var4, CallbackInfo var5, @Local Block var6) {
      this.lunar$onBlockDestroy(var6);
   }

   @Unique
   private void lunar$onBlockDestroy(Block var1) {
      StatBase var2 = ThreadModuleDump63.MC_VERSION >= 5 ? StatList.getBlockStats$v1_12(var1) : StatList.mineBlockStatArray[Block.getIdFromBlock(var1)];
      if (var2 != null) {
         ThreadModuleDump63.method7().bridge$getStatsCounter().bridge$increment(ThreadModuleDump63.method7(), (Hitbox2)var2, 1);
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "onPlayerRightClick$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$onRightClickBlock$v1_7(
      EntityPlayer var1, World var2, ItemStack var3, int var4, int var5, int var6, int var7, Vec3 var8, CallbackInfoReturnable<Boolean> var9
   ) {
      EventBlockUpdateNotify.Data var10 = ClientEventBus.method29()
         .method12(
            EventBlockUpdateNotify.Data.class,
            () -> new EventBlockUpdateNotify.Data((Vector3iBridge)(new Vector3i(var4, var5, var6)), (Bridge3_23)var2.getBlock(var4, var5, var6))
         );
      if (var10 != null && var10.isCancelled()) {
         var9.setReturnValue(false);
      }
   }

   @Annotation2(1)
   @Inject(method = "onPlayerRightClick$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$onRightClickBlock$v1_8(
      EntityPlayerSP var1, WorldClient var2, ItemStack var3, BlockPos var4, EnumFacing var5, Vec3 var6, CallbackInfoReturnable<Boolean> var7
   ) {
      EventBlockUpdateNotify.Data var8 = ClientEventBus.method29()
         .method12(EventBlockUpdateNotify.Data.class, () -> new EventBlockUpdateNotify.Data((Vector3iBridge)var4, (Bridge3_23)var2.getBlockState(var4).getBlock()));
      if (var8 != null && var8.isCancelled()) {
         var7.setReturnValue(false);
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "processRightClickBlock$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$onRightClickBlock$v1_12(
      EntityPlayerSP var1, WorldClient var2, BlockPos var3, EnumFacing var4, Vec3 var5, EnumHand var6, CallbackInfoReturnable<EnumActionResult> var7
   ) {
      EventBlockUpdateNotify.Data var8 = ClientEventBus.method29()
         .method12(EventBlockUpdateNotify.Data.class, () -> new EventBlockUpdateNotify.Data((Vector3iBridge)var3, (Bridge3_23)var2.getBlockState(var3).getBlock()));
      if (var8 != null && var8.isCancelled()) {
         var7.setReturnValue(EnumActionResult.FAIL);
      }
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "createPlayer$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getSession()Lnet/minecraft/util/Session;")
   )
   private Session lunar$rewindSetSession(Minecraft var1, Operation<Session> var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      if (var3.method19()) {
         Rewind3 var4 = var3.method35().method40().method35();
         if (var4 != null) {
            Rewind2 var5 = var4.method13();
            return new Session(var5.getPlayerName(), var5.method8().toString(), "", "mojang");
         }
      }

      return (Session)var2.call(new Object[]{var1});
   }

   @Annotation2(max = 1)
   @Inject(method = "interactWithEntitySendPacket$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$onRightClickEntity$v1_7(EntityPlayer var1, Entity var2, CallbackInfoReturnable<Boolean> var3) {
      EventInteractEntity.Data var4 = ClientEventBus.method29().method12(EventInteractEntity.Data.class, () -> new EventInteractEntity.Data((BridgeExtension)var2));
      if (var4 != null && var4.isCancelled()) {
         var3.setReturnValue(false);
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "interactWithEntity$v1_12(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/EnumHand;)Lnet/minecraft/util/EnumActionResult;",
      at = @At("HEAD"),
      cancellable = true
   )
   private void lunar$onRightClickEntity$v1_12(EntityPlayer var1, Entity var2, EnumHand var3, CallbackInfoReturnable<EnumActionResult> var4) {
      EventInteractEntity.Data var5 = ClientEventBus.method29().method12(EventInteractEntity.Data.class, () -> new EventInteractEntity.Data((BridgeExtension)var2));
      if (var5 != null && var5.isCancelled()) {
         var4.setReturnValue(EnumActionResult.PASS);
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "interactWithEntity$v1_12(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/RayTraceResult;Lnet/minecraft/util/EnumHand;)Lnet/minecraft/util/EnumActionResult;",
      at = @At("HEAD"),
      cancellable = true
   )
   private void lunar$onRightClickEntity$v1_12(
      EntityPlayer var1, Entity var2, MovingObjectPosition var3, EnumHand var4, CallbackInfoReturnable<EnumActionResult> var5
   ) {
      EventInteractEntity.Data var6 = ClientEventBus.method29().method12(EventInteractEntity.Data.class, () -> new EventInteractEntity.Data((BridgeExtension)var2));
      if (var6 != null && var6.isCancelled()) {
         var5.setReturnValue(EnumActionResult.PASS);
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "onPlayerDestroyBlock$v1_7", at = @At("HEAD"))
   private void lunar$onBlockBreak$getBlock$v1_7(
      int var1, int var2, int var3, int var4, CallbackInfoReturnable<Boolean> var5, @Share("blockState") LocalRef<Bridge2_17> var6
   ) {
      var6.set(new Bridge2Handler(this.mc.theWorld.getBlock(var1, var2, var3)));
   }

   @Annotation2(max = 0)
   @Inject(method = "onPlayerDestroyBlock$v1_7", at = @At("RETURN"))
   private void lunar$onBlockBreak$v1_7(
      int var1, int var2, int var3, int var4, CallbackInfoReturnable<Boolean> var5, @Share("blockState") LocalRef<Bridge2_17> var6
   ) {
      if ((Boolean)var5.getReturnValue()) {
         ClientEventBus.method29()
            .method12(
               EventBlockPlacement.class,
               () -> new EventBlockPlacement(((Bridge2_17)var6.get()).bridge$getBlock(), (Bridge2_17)var6.get(), (Vector3iBridge)(new Vector3i(var1, var2, var3)))
            );
      }
   }

   @Annotation2(1)
   @Inject(method = "onPlayerDestroyBlock$v1_8", at = @At("HEAD"))
   private void lunar$onBlockBreak$getBlock$v1_8(
      BlockPos var1, EnumFacing var2, CallbackInfoReturnable<Boolean> var3, @Share("blockState") LocalRef<Bridge2_17> var4
   ) {
      var4.set((Bridge2_17)this.mc.theWorld.getBlockState(var1));
   }

   @Annotation2(1)
   @Inject(method = "onPlayerDestroyBlock$v1_8", at = @At("RETURN"))
   private void lunar$onBlockBreak$v1_8(BlockPos var1, EnumFacing var2, CallbackInfoReturnable<Boolean> var3, @Share("blockState") LocalRef<Bridge2_17> var4) {
      if ((Boolean)var3.getReturnValue()) {
         ClientEventBus.method29()
            .method12(EventBlockPlacement.class, () -> new EventBlockPlacement(((Bridge2_17)var4.get()).bridge$getBlock(), (Bridge2_17)var4.get(), (Vector3iBridge)var1));
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "onPlayerDestroyBlock$v1_12", at = @At("HEAD"))
   private void lunar$onBlockBreak$getBlock$v1_12(BlockPos var1, CallbackInfoReturnable<Boolean> var2, @Share("blockState") LocalRef<Bridge2_17> var3) {
      var3.set((Bridge2_17)this.mc.theWorld.getBlockState(var1));
   }

   @Annotation2(min = 5)
   @Inject(method = "onPlayerDestroyBlock$v1_12", at = @At("RETURN"))
   private void lunar$onBlockBreak$v1_12(BlockPos var1, CallbackInfoReturnable<Boolean> var2, @Share("blockState") LocalRef<Bridge2_17> var3) {
      if ((Boolean)var2.getReturnValue()) {
         ClientEventBus.method29()
            .method12(EventBlockPlacement.class, () -> new EventBlockPlacement(((Bridge2_17)var3.get()).bridge$getBlock(), (Bridge2_17)var3.get(), (Vector3iBridge)var1));
      }
   }

   @Annotation2(max = 1)
   @Inject(method = "setGameType$v1_7", at = @At("HEAD"))
   private void lunar$onGameTypeChange(net.minecraft.world.WorldSettings.GameType var1, CallbackInfo var2) {
      if (this.currentGameType != null && this.currentGameType.id != -1 && this.currentGameType != var1) {
         ClientEventBus.method29()
            .method12(
               GameModeChangeEvent.class,
               () -> new GameModeChangeEvent(ItemcounterType2.getByID(this.currentGameType.id), ItemcounterType2.getByID(var1.id))
            );
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "setGameType$v1_12", at = @At("HEAD"))
   private void lunar$onGameTypeChange(GameType var1, CallbackInfo var2) {
      if (this.currentGameType$v1_12 != null && this.currentGameType$v1_12.id != -1 && this.currentGameType$v1_12 != var1) {
         ClientEventBus.method29()
            .method12(
               GameModeChangeEvent.class,
               () -> new GameModeChangeEvent(ItemcounterType2.getByID(this.currentGameType$v1_12.id), ItemcounterType2.getByID(var1.id))
            );
      }
   }

   @Inject(method = "attackEntity", at = @At("HEAD"))
   private void lunar$clickMouse$eventPreAttackEntityClient(EntityPlayer var1, Entity var2, CallbackInfo var3) {
      ClientEventBus.method29().method12(PreAttackEntityEvent.class, () -> {
         Minecraft var1x = Minecraft.getMinecraft();
         Vec3 var2x;
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            var2x = var1x.renderViewEntity.getPositionEyes(var1x.timer.renderPartialTicks);
         } else {
            var2x = var1x.renderViewEntity$v1_7.getPosition(var1x.timer.renderPartialTicks).addVector(0.0, var1x.renderViewEntity$v1_7.getEyeHeight(), 0.0);
         }

         return new PreAttackEntityEvent((BridgeExtension)var2, var2x.distanceTo(var1x.objectMouseOver.hitVec), (Vec3Bridge)var1x.objectMouseOver.hitVec);
      });
   }

   @Annotation2(max = 0)
   @Inject(
      method = "sendUseItem$v1_7",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/NetHandlerPlayClient;addToSendQueue$v1_7(Lnet/minecraft/network/Packet_v1_7;)V",
         ordinal = 0,
         shift = Shift.BEFORE
      )
   )
   private void apollo$sendRayTraceAirClick$v1_7(EntityPlayer var1, World var2, ItemStack var3, CallbackInfoReturnable<Boolean> var4) {
      this.apollo$forwardRayTracePosition(var1, var3, var2);
   }

   @Annotation2(max = 0)
   @Inject(
      method = "onPlayerRightClick$v1_7",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/NetHandlerPlayClient;addToSendQueue$v1_7(Lnet/minecraft/network/Packet_v1_7;)V",
         ordinal = 0,
         shift = Shift.BEFORE
      )
   )
   private void apollo$forwardRayTracePosition$v1_7(
      EntityPlayer var1, World var2, ItemStack var3, int var4, int var5, int var6, int var7, Vec3 var8, CallbackInfoReturnable<Boolean> var9
   ) {
      this.apollo$forwardRayTracePosition(var1, var3, var2);
   }

   @Annotation2(1)
   @Inject(
      method = "onPlayerRightClick$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/NetHandlerPlayClient;addToSendQueue$v1_8(Lnet/minecraft/network/Packet_v1_8;)V",
         ordinal = 0,
         shift = Shift.BEFORE
      )
   )
   private void apollo$forwardRayTracePosition$v1_8(
      EntityPlayerSP var1, WorldClient var2, ItemStack var3, BlockPos var4, EnumFacing var5, Vec3 var6, CallbackInfoReturnable<Boolean> var7
   ) {
      this.apollo$forwardRayTracePosition(var1, var3, var2);
   }

   @Annotation2(1)
   @Inject(
      method = "sendUseItem$v1_7",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/NetHandlerPlayClient;addToSendQueue$v1_8(Lnet/minecraft/network/Packet_v1_8;)V",
         ordinal = 0,
         shift = Shift.BEFORE
      )
   )
   private void apollo$sendRayTraceAirClick$v1_8(EntityPlayer var1, World var2, ItemStack var3, CallbackInfoReturnable<Boolean> var4) {
      this.apollo$forwardRayTracePosition(var1, var3, var2);
   }

   @Annotation2(max = 1)
   @Unique
   private void apollo$forwardRayTracePosition(EntityPlayer var1, ItemStack var2, World var3) {
      if (var2 != null) {
         if (var2.getItem() instanceof ItemBucket var4) {
            ThreadModuleDump63.method4()
               .method84()
               .<ApolloModuleHandler>method3(PacketEnrichmentModule.class)
               .filter(var0 -> (Boolean)var0.getOptions().get(PacketEnrichmentModule.PLAYER_USE_ITEM_BUCKET_PACKET))
               .ifPresent(var4x -> {
                  MovingObjectPosition var5 = var4.getMovingObjectPositionFromPlayer(var3, var1, var4.isFull == Blocks.air);
                  ((Highlight3Iterator31)var4x).method8(this.apollo$createRayTraceResult(var5));
               });
         }
      }
   }

   @Annotation2(max = 1)
   @Unique
   private RayTraceResult apollo$createRayTraceResult(MovingObjectPosition var1) {
      if (var1 == null) {
         return new MissResult();
      }

      MovingObjectType var2 = var1.typeOfHit;
      Vec3 var3 = var1.hitVec;
      String var4 = Client.method109().getWorld();
      ApolloLocation var5 = ApolloLocation.builder().world(var4).x(var3.xCoord).y(var3.yCoord).z(var3.zCoord).build();
      switch (var2) {
         case BLOCK:
            ApolloBlockLocation var7;
            int var9;
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               BlockPos var8 = var1.blockPos;
               var7 = ApolloBlockLocation.builder().world(var4).x(var8.x).y(var8.y).z(var8.z).build();
               var9 = var1.sideHit.getIndex();
            } else {
               var7 = ApolloBlockLocation.builder().world(var4).x(var1.blockX$v1_7).y(var1.blockY$v1_7).z(var1.blockZ$v1_7).build();
               var9 = var1.sideHit$v1_7;
            }

            return BlockHitResult.builder().hitLocation(var5).blockLocation(var7).direction(Direction.values()[var9]).build();
         case ENTITY:
            Entity var6 = var1.entityHit;
            return EntityHitResult.builder().hitLocation(var5).entityId(new ApolloEntity(var6.field_179775_c, var6.getUniqueID())).build();
         default:
            return new MissResult();
      }
   }
}
