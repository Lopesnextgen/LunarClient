package com.moonsworth.lunar.mixin.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.moonsworth.lunar.bridge.EntityLivingStateBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.network.apollo.LimbApolloHandler;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderBiped.class)
public abstract class RenderBipedMixin {
   @Unique
   private static final ResourceLocation[] lunar$armorResources = new ResourceLocation[]{
      new ResourceLocation("textures/models/armor/leather_layer_1.png"),
      new ResourceLocation("textures/models/armor/leather_layer_2.png"),
      new ResourceLocation("textures/models/armor/chainmail_layer_1.png"),
      new ResourceLocation("textures/models/armor/chainmail_layer_2.png"),
      new ResourceLocation("textures/models/armor/iron_layer_1.png"),
      new ResourceLocation("textures/models/armor/iron_layer_2.png"),
      new ResourceLocation("textures/models/armor/diamond_layer_1.png"),
      new ResourceLocation("textures/models/armor/diamond_layer_2.png"),
      new ResourceLocation("textures/models/armor/gold_layer_1.png"),
      new ResourceLocation("textures/models/armor/gold_layer_2.png"),
      new ResourceLocation("textures/models/armor/leather_layer_1_overlay.png"),
      new ResourceLocation("textures/models/armor/leather_layer_2_overlay.png")
   };
   @Unique
   private static final ItemStack lunar$dummyItemStack = new ItemStack(Items.diamond_sword);

   public RenderBipedMixin() {
   }

   @Overwrite
   public static ResourceLocation func_110857_a(ItemArmor itemarmor0, int number1) {
      return lunar$armorResources[(itemarmor0.renderIndex << 1) + (number1 == 2 ? 1 : 0)];
   }

   @Overwrite
   public static ResourceLocation func_110858_a(ItemArmor itemarmor0, int number1, String text2) {
      return lunar$armorResources[(itemarmor0.renderIndex << 1) + (number1 == 2 ? 11 : 10)];
   }

   @Redirect(method = "renderEquippedItems", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScalef(FFF)V", ordinal = 1))
   public void impl$headFixBabyZombie(float value1, float value2, float value3, EntityLiving entity4) {
      boolean flag5 = entity4 instanceof EntityVillager || entity4 instanceof EntityZombie && ((EntityZombie)entity4).isVillager();
      if (!flag5 && entity4.isChild()) {
         float value8 = 2.0F;
         float value7 = 1.65F;
         GL11.glScalef(value7 / value8, value7 / value8, value7 / value8);
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, -0.936F, 0.0F);
      } else {
         float value6 = 1.0625F;
         GL11.glScalef(value6, -value6, -value6);
      }
   }

   @Inject(method = "shouldRenderPass(Lnet/minecraft/entity/EntityLiving;IF)I", at = @At("HEAD"), cancellable = true)
   private void lunar$shouldRenderPass(EntityLiving entity1, int number2, float value3, CallbackInfoReturnable<Integer> callbackinforeturnable4) {
      Ref.method4().method84().method3(LimbModule.class).ifPresent(arg3x -> {
         LimbApolloHandler highlight3iterator164x = (LimbApolloHandler)arg3x;
         Set set5x = (Set)highlight3iterator164x.method6().get(entity1.getUniqueID());
         if (set5x != null) {
            if (number2 == 0 && set5x.contains(ArmorPiece.HELMET)) {
               callbackinforeturnable4.setReturnValue(-1);
            } else if (number2 == 1 && set5x.contains(ArmorPiece.CHESTPLATE)) {
               callbackinforeturnable4.setReturnValue(-1);
            } else if (number2 == 2 && set5x.contains(ArmorPiece.LEGGINGS)) {
               callbackinforeturnable4.setReturnValue(-1);
            } else if (number2 == 3 && set5x.contains(ArmorPiece.BOOTS)) {
               callbackinforeturnable4.setReturnValue(-1);
            }
         }
      });
      OverlayMod overlaymod5 = Ref.method4().method40().method84();
      if (number2 == 0 && overlaymod5.method41((EntityLivingStateBridge)entity1)) {
         callbackinforeturnable4.setReturnValue(-1);
      } else if (number2 == 1 && overlaymod5.isHideChestEnabled((EntityLivingStateBridge)entity1)) {
         callbackinforeturnable4.setReturnValue(-1);
      } else if (number2 == 2 && overlaymod5.isHideLeggingsEnabled((EntityLivingStateBridge)entity1)) {
         callbackinforeturnable4.setReturnValue(-1);
      } else if (number2 == 3 && overlaymod5.isHideBootsEnabled((EntityLivingStateBridge)entity1)) {
         callbackinforeturnable4.setReturnValue(-1);
      }
   }

   @VersionGate(max = 0)
   @ModifyExpressionValue(
      method = "renderEquippedItems(Lnet/minecraft/entity/EntityLiving;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLiving;func_130225_q(I)Lnet/minecraft/item/ItemStack;")
   )
   private ItemStack lunar$RenderEquipped(ItemStack stack1, @Local(argsOnly = true) EntityLiving entity2) {
      Optional optional3 = Ref.method4().method84().method3(LimbModule.class);
      if (optional3.isPresent()) {
         Collection list4 = (Collection)((LimbApolloHandler)optional3.get()).method6().get(entity2.getUniqueID());
         if (list4 != null && list4.contains(ArmorPiece.HELMET)) {
            return lunar$dummyItemStack;
         }
      }

      return Ref.method4().method40().method84().method41((EntityLivingBridge)entity2) ? lunar$dummyItemStack : stack1;
   }
}
