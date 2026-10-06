package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_20;
import com.moonsworth.lunar.bridge.Bridge4_12;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_40;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsHandler;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleChangerBlood;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.EntityDiggingFXImpl;
import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EffectRenderer.class)
public abstract class EffectRendererMixin implements Bridge_40 {
   @Shadow
   public World world;
   @Final
   @Shadow
   public ArrayDeque<EntityFX>[][] fxLayers$v1_12;
   @Shadow
   public List[] fxLayers$v1_7;
   @Shadow
   public List<EntityFX>[][] fxLayers;

   @Shadow
   public abstract void addEffect(EntityFX var1);

   @Shadow
   public abstract void emitParticleAtEntity(Entity var1, EnumParticleTypes var2, int var3);

   @Shadow
   public abstract void emitParticleAtEntity(Entity var1, EnumParticleTypes var2);

   @Annotation2(max = 0)
   @Inject(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/client/renderer/texture/TextureManager;)V", at = @At("TAIL"))
   private void bridge$init(CallbackInfo var1) {
      String[] var2 = new String[]{
         "hugeexplosion",
         "largeexplode",
         "fireworksSpark",
         "bubble",
         "suspended",
         "depthsuspend",
         "townaura",
         "crit",
         "magicCrit",
         "smoke",
         "mobSpell",
         "mobSpellAmbient",
         "spell",
         "instantSpell",
         "witchMagic",
         "note",
         "portal",
         "enchantmenttable",
         "explode",
         "flame",
         "lava",
         "footstep",
         "splash",
         "wake",
         "largesmoke",
         "cloud",
         "reddust",
         "snowballpoof",
         "dripWater",
         "dripLava",
         "snowshovel",
         "slime",
         "heart",
         "angryVillager",
         "happyVillager",
         "iconcrack_",
         "blockcrack_",
         "blockdust_"
      };

      for (String var6 : var2) {
         HorsestatsType var7 = HorsestatsType.getParticleFromName(var6);
         HorsestatsType2 var8 = var7.asModernParticle();
         var8.setDataProvider(new HorsestatsHandler(var7.getParticleName(), var7.getParticleID()));
      }

      HorsestatsType2.populateRegistry();
   }

   @Override
   public void bridge$addEffect(Bridge3_20 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         if (var1 instanceof EntityDiggingFX) {
            ((Bridge4_12)var1).bridge$setParticleType(HorsestatsType2.getParticleFromId(EnumParticleTypes.BLOCK_CRACK.getParticleID()));
         }

         this.addEffect((EntityFX)var1);
      } else {
         if (var1 instanceof EntityDiggingFX) {
            ((Bridge4_12)var1).bridge$setParticleType(HorsestatsType2.getParticleFromId(HorsestatsType.BLOCK_CRACK.getParticleID()));
         }

         this.addEffect((EntityFX)var1);
      }
   }

   @Override
   public void bridge$emitParticleAtEntity(BridgeExtension var1, HorsestatsType2 var2) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.emitParticleAtEntity((Entity)var1, EnumParticleTypes.getParticleFromId(var2.getDataProvider().getId()), 3);
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.emitParticleAtEntity((Entity)var1, EnumParticleTypes.getParticleFromId(var2.getDataProvider().getId()));
      } else if (var2 == HorsestatsType2.CRIT) {
         Minecraft.getMinecraft().field_92047_az.addEffect(new EntityCrit2FX(Minecraft.getMinecraft().theWorld, (Entity)var1));
      } else if (var2 == HorsestatsType2.ENCHANTED_HIT) {
         Minecraft.getMinecraft().field_92047_az.addEffect(new EntityCrit2FX(Minecraft.getMinecraft().theWorld, (Entity)var1, "magicCrit"));
      } else {
         this.world.spawnParticle(var2.getDataProvider().getName(), var1.bridge$getPosX(), var1.bridge$getPosY(), var1.bridge$getPosZ(), 0.0, 0.0, 0.0);
      }
   }

   @Override
   public int bridge$countParticles() {
      if (ThreadModuleDump63.MC_VERSION < 1) {
         return this.fxLayers$v1_7[0].size() + this.fxLayers$v1_7[1].size() + this.fxLayers$v1_7[2].size();
      }

      int var1 = 0;

      for (int var2 = 0; var2 < 4; var2++) {
         for (int var3 = 0; var3 < 2; var3++) {
            var1 += ((Object[])((Object[])(ThreadModuleDump63.MC_VERSION >= 5 ? this.fxLayers$v1_12 : this.fxLayers))[var2])[var3].size();
         }
      }

      return var1;
   }

   @Override
   public void bridge$spawnBloodParticles(Itemcounter6 var1, double var2, double var4, double var6) {
      ParticleChangerBlood var8 = ThreadModuleDump63.method4().method40().method22().method15();
      if (!var8.isPlayBloodSoundEnabled()) {
         Block var9 = Blocks.redstone_block;
         World var10 = (World)var1;
         if (var8.isPlayBloodSoundEnabled()) {
            if (ThreadModuleDump63.MC_VERSION == 5) {
               SoundType var11 = var9.getSoundType$v1_12();
               var10.playSound(var2, var4, var6, var11.breakSound, SoundCategory.BLOCKS, (var11.getVolume() + 1.0F) / 2.0F, var11.getPitch() * 0.8F, false);
            } else {
               net.minecraft.block.Block.SoundType var22 = var9.stepSound;
               ResourceLocation var12 = new ResourceLocation(ThreadModuleDump63.MC_VERSION == 0 ? var22.getDigResourcePath$v1_7() : var22.getBreakSound());
               Minecraft.getMinecraft()
                  .getSoundHandler()
                  .playSound(
                     new PositionedSoundRecord(var12, (var22.getVolume() + 1.0F) / 2.0F, var22.getFrequency() * 0.8F, (float)var2, (float)var4, (float)var6)
                  );
            }
         }

         ThreadLocalRandom var23 = ThreadLocalRandom.current();

         for (int var24 = 0; var24 < 27; var24++) {
            int var13 = var8.create(var23);

            for (int var14 = 0; var14 < var13; var14++) {
               double var15 = var23.nextFloat() * 2.0F - 1.0F;
               double var17 = var23.nextFloat() * 2.0F - 1.0F;
               double var19 = var23.nextFloat() * 2.0F - 1.0F;
               EntityDiggingFXImpl var21;
               if (ThreadModuleDump63.MC_VERSION > 0) {
                  var21 = new EntityDiggingFXImpl(var10, var2, var4, var6, var15, var17, var19, var9.defaultBlockState);
                  var21.setBlockPos(new BlockPos(var2, var4, var6));
               } else {
                  var21 = new EntityDiggingFXImpl(var10, var2, var4, var6, var15, var17, var19, var9, 0);
               }

               Minecraft.getMinecraft().field_92047_az.addEffect(var21);
            }
         }
      }
   }

   @Inject(
      method = {"addBlockHitEffects$v1_7", "addBlockHitEffects$v1_8", "addBlockDestroyEffects$v1_7", "addBlockDestroyEffects$v1_8"},
      at = @At("HEAD"),
      cancellable = true
   )
   private void lunar$cancelDigParticles(CallbackInfo var1) {
      if (ThreadModuleDump63.method4().method40().method22().method14()) {
         var1.cancel();
      }
   }
}
