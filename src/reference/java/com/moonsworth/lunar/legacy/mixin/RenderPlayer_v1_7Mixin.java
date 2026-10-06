package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.moonsworth.lunar.bridge.Bridge2_10;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.BipedModelBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.bridge.SExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.LimbApolloHandler;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.cosmetics.CosmeticModelRenderer;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.ModelRenderEvent;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.driver.core.holograms.HologramRendererLegacy;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.Generated;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.skin_n_bones.api.animation.AnimationMesh;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJArmature;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJBone;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer_v1_7;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Annotation2(max = 0)
@Mixin(RenderPlayer_v1_7.class)
public abstract class RenderPlayer_v1_7Mixin implements SExtension<Bridge5_11, EntityPlayerBridge> {
   @Shadow
   public ModelBiped modelBipedMain;
   @Shadow
   public ModelBiped modelArmorChestplate;
   @Shadow
   public ModelBiped modelArmor;
   private List<MExtension<Bridge5_11, EntityPlayerBridge>> layers = new ArrayList<>();
   @Unique
   private final ItemStack lunar$dummyItemStack = new ItemStack(Items.diamond_sword);

   @Inject(method = "shouldRenderPass(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I", at = @At("HEAD"), cancellable = true)
   private void lunar$shouldRenderPass(AbstractClientPlayer var1, int var2, float var3, CallbackInfoReturnable<Integer> var4) {
      ThreadModuleDump63.method4().method84().<ApolloModuleHandler>method3(LimbModule.class).ifPresent(var3x -> {
         LimbApolloHandler var4x = (LimbApolloHandler)var3x;
         Set var5x = var4x.method6().get(var1.getUniqueID());
         if (var5x != null) {
            if (var2 == 0 && var5x.contains(ArmorPiece.HELMET)) {
               var4.setReturnValue(-1);
            } else if (var2 == 1 && var5x.contains(ArmorPiece.CHESTPLATE)) {
               var4.setReturnValue(-1);
            } else if (var2 == 2 && var5x.contains(ArmorPiece.LEGGINGS)) {
               var4.setReturnValue(-1);
            } else if (var2 == 3 && var5x.contains(ArmorPiece.BOOTS)) {
               var4.setReturnValue(-1);
            }
         }
      });
      OverlayMod var5 = ThreadModuleDump63.method4().method40().method84();
      Bridge5_11 var6 = (Bridge5_11)var1;
      if (var2 == 0 && var5.method41(var6)) {
         var4.setReturnValue(-1);
      } else if (var2 == 1 && var5.isHideChestEnabled(var6)) {
         var4.setReturnValue(-1);
      } else if (var2 == 2 && var5.isHideLeggingsEnabled(var6)) {
         var4.setReturnValue(-1);
      } else if (var2 == 3 && var5.isHideBootsEnabled(var6)) {
         var4.setReturnValue(-1);
      }
   }

   @ModifyExpressionValue(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/InventoryPlayer;armorItemInSlot(I)Lnet/minecraft/item/ItemStack;")
   )
   private ItemStack lunar$getItemOnHead(ItemStack var1, @Local(argsOnly = true) AbstractClientPlayer var2) {
      Optional var3 = ThreadModuleDump63.method4().method84().method3(LimbModule.class);
      if (var3.isPresent()) {
         Collection var4 = ((LimbApolloHandler)var3.get()).method6().get(var2.getUniqueID());
         if (var4 != null && var4.contains(ArmorPiece.HELMET)) {
            return this.lunar$dummyItemStack;
         }
      }

      return ThreadModuleDump63.method4().method40().method84().method41((Bridge5_11)var2) ? this.lunar$dummyItemStack : var1;
   }

   @Redirect(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_7;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   private void lunar$bindTexture(RenderPlayer_v1_7 var1, ResourceLocation var2) {
      if (var2 != null) {
         var1.bindTexture(var2);
      }
   }

   @Redirect(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getLocationCape()Lnet/minecraft/util/ResourceLocation;")
   )
   @Nullable
   private ResourceLocation lunar$getLocationCape(AbstractClientPlayer var1) {
      if (!ThreadModuleDump63.method4().method41().method6().method6((EntityPlayerBridge)var1)) {
         return var1.getLocationCape();
      } else {
         Optional var2 = ThreadModuleDump63.method4().method53().method13(var1.getUniqueID()).map(CosmeticMetadata::method4);
         if (var2.isPresent() && CosmeticCategoryType.CLOAK.canShowCosmetic()) {
            CosmeticManager var3 = ThreadModuleDump63.method4().method53();
            return var3.method36((OwnedCosmetic)var2.get(), var1.getUniqueID()).isEmpty()
               ? null
               : (ResourceLocation)((OwnedCosmetic)var2.get()).method4((Bridge6_10)var1);
         } else {
            return var1.getLocationCape();
         }
      }
   }

   @Redirect(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;hasCape$v1_7()Z")
   )
   private boolean lunar$hasCape(AbstractClientPlayer var1) {
      boolean var2 = false;
      if (ThreadModuleDump63.method4().method41().method6().method6((EntityPlayerBridge)var1)) {
         Optional var3 = ThreadModuleDump63.method4().method53().method13(var1.getUniqueID()).map(CosmeticMetadata::method4);
         var2 = var3.isPresent() && CosmeticCategoryType.CLOAK.canShowCosmetic();
      }

      List var4 = Client.method109().method53().method14(var1.getUniqueID(), CosmeticCategoryType.BACKPACK);
      return var4.isEmpty() && (var1.hasCape$v1_7() || var2);
   }

   @Redirect(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getHideCape()Z")
   )
   public boolean lunar$getHideCape(AbstractClientPlayer var1) {
      Optional var2 = ThreadModuleDump63.method4().method53().method13(var1.getUniqueID()).map(CosmeticMetadata::method4);
      return var2.isPresent() && CosmeticCategoryType.CLOAK.canShowCosmetic() ? false : var1.getHideCape$v1_7();
   }

   @WrapWithCondition(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBiped;renderCloak$v1_7(F)V")
   )
   private boolean lunar$renderCloak(ModelBiped var1, float var2, AbstractClientPlayer var3) {
      if (ThreadModuleDump63.method4().method41().method6().method6((EntityPlayerBridge)var3) && CosmeticCategoryType.CLOAK.canShowCosmetic()) {
         CosmeticManager var4 = ThreadModuleDump63.method4().method53();
         CosmeticMetadata var5 = var4.getProvider(((Bridge5_11)var3).bridge$getUniqueID(), CosmeticCategoryType.CLOAK);
         if (var4.method34(var5)) {
            return false;
         }

         if (((Bridge5_11)var3).bridge$isDummyMannequin() && var5 == null) {
            return false;
         }

         this.lunar$renderCloak(var3, var2);
         if (var5 != null && var5.method4().method22()) {
            DriverViewLegacy.method21().method14().method3(AbstractRenderContext.method32(), var5.method4(), () -> this.lunar$renderCloak(var3, var2));
         }

         return false;
      } else {
         return true;
      }
   }

   @Unique
   private void lunar$renderCloak(AbstractClientPlayer var1, float var2) {
      ResourceLocationBridge var3 = (ResourceLocationBridge)this.lunar$getLocationCape(var1);
      if (var3 != null) {
         if (var1 != null && ((Bridge5_11)var1).bridge$isDummySelf() && HologramRendererLegacy.field2) {
            var3 = CosmeticManager.field2;
         }

         CosmeticModelRenderer.method5(
            AbstractRenderContext.method32(),
            null,
            (Bridge5_11)var1,
            ThreadModuleDump63.method3().bridge$getTextureManager().method1(var3),
            var3,
            () -> this.modelBipedMain.renderCloak$v1_7(var2)
         );
      }
   }

   @Redirect(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;isSneaking()Z")
   )
   private boolean lunar$isSneaking(AbstractClientPlayer var1) {
      return var1.isSneaking() && !ThreadModuleDump63.method4().method45().method9((Bridge6_10)var1);
   }

   @Inject(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal = 2, shift = Shift.AFTER),
      cancellable = true
   )
   private void lunar$setupEmoteTransform(AbstractClientPlayer var1, float var2, CallbackInfo var3) {
      EmoteController var4 = (EmoteController)EmoteController.get((BridgeExtension)var1);
      if (var4 != null && var4.isEmoting()) {
         if (!var4.shouldRenderCape()) {
            var3.cancel();
            GL11.glPopMatrix();
         } else {
            BOBJArmature var5 = ((AnimationMesh)var4.animator.animation.meshes.get(0)).armature;
            var4.animator.setupMatrix((BOBJBone)var5.bones.get("low_body"));
            GL11.glTranslatef(0.0F, 0.375F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }
   }

   @Inject(method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V", at = @At("RETURN"))
   private void lunar$renderHat(AbstractClientPlayer var1, float var2, CallbackInfo var3) {
      BridgeExtension3_5 var4 = AbstractRenderContext.method32();
      float var5 = var1.prevRotationYawHead + (var1.rotationYawHead - var1.prevRotationYawHead) * var2;
      float var6 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var2;
      float var7 = 0.0625F;

      for (MExtension var9 : this.getLayers()) {
         if (var9.method9((EntityPlayerBridge)var1)) {
            GL11.glPushMatrix();
            boolean var10 = var4.HOROHHCOICRHROHOROICIHHHICCOIC();
            boolean var11 = var4.HCHHOIRHOCOHHIHHHOHIIRHIRIHCOO();
            var4.ROHRIRCCIHOOHOCRCIIHICHHICROCI();
            var4.OIICIOHOCCIRCIRCIIIHHRRCIHIIRO();
            var9.method1(var4, (Bridge5_11)var1, (BridgeExtension2_7)this.modelBipedMain, 0.0F, 0.0F, var2, var1.getAge(), var5, var6, var7);
            GL11.glPopMatrix();
            if (!var10) {
               var4.OCCIOHICOOHRIIIOOHRCRHOIHOOCHH();
            }

            if (!var11) {
               var4.ICRCRICCCORRHICIHHIHORROOHIROO();
            }
         }
      }
   }

   @Inject(method = "doRender(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", at = @At("TAIL"))
   private void lunar$onSetModelVisibilities(CallbackInfo var1) {
      this.modelArmorChestplate.isRiding = this.modelArmor.isRiding = this.modelBipedMain.isRiding = false;
   }

   @Inject(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;hasCape$v1_7()Z")
   )
   private void lunar$onModelBipedRenderPost(AbstractClientPlayer var1, float var2, CallbackInfo var3) {
      ClientEventBus.method29()
         .method12(ModelRenderEvent.ModelPlayerRenderEvent.class, () -> new ModelRenderEvent.ModelPlayerRenderEvent((Bridge5_11)var1, (BipedModelBridge)this.modelBipedMain, var2));
   }

   @Redirect(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/InventoryPlayer;armorItemInSlot(I)Lnet/minecraft/item/ItemStack;")
   )
   private ItemStack lunar$helmet(InventoryPlayer var1, int var2) {
      ItemStack var3 = var1.armorItemInSlot(var2);
      if (var3 == null) {
         return null;
      } else {
         return var3.getItem() == Items.skull && ThreadModuleDump63.method4().method45().method9((Bridge6_10)var1.player) ? null : var3;
      }
   }

   @Redirect(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/InventoryPlayer;getCurrentItem()Lnet/minecraft/item/ItemStack;")
   )
   private ItemStack lunar$getCurrentItem(InventoryPlayer var1) {
      return var1.player != null && ThreadModuleDump63.method4().method45().method9((Bridge6_10)var1.player) ? null : var1.getCurrentItem();
   }

   @Override
   public void bridge$addLayer(MExtension var1) {
      if (!this.layers.contains(var1)) {
         this.layers.add(var1);
      }
   }

   @Override
   public void bridge$addLayer(MExtension<Bridge5_11, EntityPlayerBridge> var1, boolean var2) {
      if (!this.layers.contains(var1)) {
         this.layers.add(var1);
      }
   }

   @Override
   public Bridge2_10 bridge$getLayerCape() {
      throw new UnsupportedOperationException();
   }

   @Inject(method = "<init>", at = @At("TAIL"))
   public void copyLayers(CallbackInfo var1) {
      if (ThreadModuleDump63.method2() && ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher() != null) {
         this.layers = ((RenderPlayer_v1_7Mixin)ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher().bridge$defaultPlayerRenderer()).layers;
      }
   }

   @Inject(
      method = "renderEquippedItems(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem$v1_7(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
         shift = Shift.AFTER
      )
   )
   private void lunar$resetColorTintAfterItemRendering(AbstractClientPlayer var1, float var2, CallbackInfo var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @Generated
   @Override
   public List<MExtension<Bridge5_11, EntityPlayerBridge>> getLayers() {
      return this.layers;
   }
}
