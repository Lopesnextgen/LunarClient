package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_37;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.GroundItemTransformEvent;
import com.moonsworth.lunar.client.mod.render.items2d.Items2d;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFireworkCharge;
import net.minecraft.item.ItemLingeringPotion;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTippedArrow;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@Annotation2(min = 1)
public class ItemEntityRendererBridgeImpl implements Bridge_37 {
   private static final Random field1 = new Random();
   private static final ResourceLocation field2 = new ResourceLocation("minecraft", "items/potion_bottle_splash");
   private static final ResourceLocation field3 = new ResourceLocation("minecraft", "items/potion_bottle_drinkable");
   private static final ResourceLocation field4 = new ResourceLocation("minecraft", "items/potion_bottle_lingering");
   private static final ResourceLocation field5 = new ResourceLocation("minecraft", "items/spawn_egg_overlay");
   private static final ResourceLocation field6 = new ResourceLocation("minecraft", "items/fireworks_charge_overlay");
   @Annotation2(min = 5)
   private static ResourceLocation field7;
   private static final List<ResourceLocation> field8;

   private void method1(EntityItem var1, double var2, double var4, double var6, float var8) {
      field1.setSeed(187L);
      ItemStack var9 = ThreadModuleDump63.MC_VERSION >= 5 ? var1.getItem() : var1.getEntityItem();
      IBakedModel var10 = Minecraft.getMinecraft().renderItem.itemModelMesher.getItemModel(var9);
      int var11 = this.method4(var9.stackSize);
      GlStateManager.enableRescaleNormal();
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         GlStateManager.enableColorMaterial();
      }

      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.pushMatrix();
      Render var12 = Minecraft.getMinecraft().renderManager.getEntityRenderObject(var1);
      var12.bindEntityTexture(var1);
      float var13 = var10.getItemCameraTransforms().ground.scale.y;
      float var14 = MathHelper.sin((var1.age + var8) / 10.0F + var1.hoverStart) * 0.1F + 0.1F;
      GL11.glTranslated(var2, var4 + var14 + 0.25 * var13, var6);

      for (int var15 = 0; var15 < var11; var15++) {
         GlStateManager.pushMatrix();
         this.method5(var15);
         float var16 = this.method9((ItemEntityBridge)var1);
         if (var16 > 1.0F) {
            GL11.glScalef(var16, var16, var16);
         }

         if (ThreadModuleDump63.MC_VERSION >= 5) {
            if (var9.getItem().getTranslationKey$v1_12().contains("Fence")) {
               GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            } else if (var9.getItem() == Items.SHIELD$v1_12) {
               GlStateManager.translate(-0.115F, 0.0F, 0.0F);
            }
         } else {
            if (var10.isBuiltInRenderer() && !(var9.getItem() instanceof ItemSkull)) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            } else if (var9.getItem().getUnlocalizedName().contains("Fence")) {
               GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var10.isGui3d()) {
               GlStateManager.scale(0.5, 0.5, 0.5);
            }
         }

         var10.getItemCameraTransforms().applyTransform(TransformType.ground);
         Minecraft.getMinecraft().renderItem.renderItem(var9, var10);
         GlStateManager.popMatrix();
      }

      GlStateManager.popMatrix();
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         GlStateManager.disableRescaleNormal();
         GlStateManager.disableBlend();
      }
   }

   @Override
   public void method1(ItemEntityBridge var1, double var2, double var4, double var6, float var8) {
      field1.setSeed(187L);
      ItemStack var9 = (ItemStack)var1.bridge$getItemState();
      Item var10 = var9.getItem();
      EntityItem var11 = (EntityItem)var1;
      if (!(var10 instanceof ItemBlock)
         && !(var10 instanceof ItemSkull)
         && !(var10 instanceof ItemBed)
         && (ThreadModuleDump63.MC_VERSION < 5 || !(var10 instanceof ItemShield))
         && ThreadModuleDump63.method4().method40().method29().method13().get() != Items2d.Type.MODEL) {
         TextureAtlasSprite var12 = Minecraft.getMinecraft().renderItem.itemModelMesher.getParticleIcon(var10, var9.getItemDamage());
         boolean var13 = false;
         int var14 = -16777216;
         if (var10 instanceof ItemArmor
            || var10 instanceof ItemPotion
            || var10 instanceof ItemMonsterPlacer
            || ThreadModuleDump63.MC_VERSION >= 5 && var10 instanceof ItemTippedArrow) {
            var13 = true;
            var14 = ThreadModuleDump63.MC_VERSION >= 5
               ? Minecraft.getMinecraft().itemColors$v1_12.colorMultiplier(var9, 0)
               : var10.getColorFromItemStack(var9, 0);
         }

         int var15 = this.method4(var9.stackSize);
         Tessellator var16 = Tessellator.getInstance();
         WorldRenderer var17 = ThreadModuleDump63.MC_VERSION >= 5 ? var16.getBuffer$v1_12() : var16.getWorldRenderer();
         Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableColorMaterial();
         GlStateManager.enableDepth();
         if (ThreadModuleDump63.MC_VERSION <= 1) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         }

         RenderHelper.enableStandardItemLighting();
         float var18 = MathHelper.sin((var11.age + var8) / 10.0F + var11.hoverStart) * 0.1F + 0.1F;
         GL11.glTranslated(var2, var4 + var18 + 0.1, var6);
         int var19 = var14 >> 16 & 0xFF;
         int var20 = var14 >> 8 & 0xFF;
         int var21 = var14 & 0xFF;

         for (int var22 = 0; var22 < var15; var22++) {
            GlStateManager.pushMatrix();
            this.method5(var22);
            float var23 = this.method9(var1);
            GL11.glScalef(var23, var23, var23);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            if (this.method6(var9)) {
               TextureAtlasSprite var24 = Minecraft.getMinecraft().textureMapBlocks.getAtlasSprite(Objects.requireNonNull(this.method7(var9)).toString());
               int var25 = ThreadModuleDump63.MC_VERSION >= 5
                  ? Minecraft.getMinecraft().itemColors$v1_12.colorMultiplier(var9, 1)
                  : var10.getColorFromItemStack(var9, 1);
               int var26 = var25 >> 16 & 0xFF;
               int var27 = var25 >> 8 & 0xFF;
               int var28 = var25 & 0xFF;
               this.method3(var17, var24, var26, var27, var28, true);
            }

            this.method3(var17, var12, var19, var20, var21, var13);
            GlStateManager.popMatrix();
         }

         GlStateManager.disableRescaleNormal();
         GlStateManager.disableBlend();
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            GlStateManager.disableColorMaterial();
         }
      } else {
         this.method1(var11, var2, var4, var6, var8);
      }
   }

   private void method3(WorldRenderer var1, TextureAtlasSprite var2, int var3, int var4, int var5, boolean var6) {
      var1.begin(7, var6 ? DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL : DefaultVertexFormats.POSITION_TEX_NORMAL);
      this.method8(var1.pos(-0.5, -0.25, 0.0).tex(var2.getMinU(), var2.getMaxV()), var3, var4, var5, var6).normal(0.0F, 1.0F, 0.0F).endVertex();
      this.method8(var1.pos(0.5, -0.25, 0.0).tex(var2.getMaxU(), var2.getMaxV()), var3, var4, var5, var6).normal(0.0F, 1.0F, 0.0F).endVertex();
      this.method8(var1.pos(0.5, 0.75, 0.0).tex(var2.getMaxU(), var2.getMinV()), var3, var4, var5, var6).normal(0.0F, 1.0F, 0.0F).endVertex();
      this.method8(var1.pos(-0.5, 0.75, 0.0).tex(var2.getMinU(), var2.getMinV()), var3, var4, var5, var6).normal(0.0F, 1.0F, 0.0F).endVertex();
      Tessellator.getInstance().draw();
   }

   private int method4(int var1) {
      byte var2 = 1;
      if (var1 > 1) {
         var2 = 2;
      }

      if (var1 > 5) {
         var2 = 3;
      }

      if (var1 > 20) {
         var2 = 4;
      }

      if (var1 > 40) {
         var2 = 5;
      }

      return var2;
   }

   private void method5(int var1) {
      if (var1 > 0) {
         float var2 = (field1.nextFloat() * 2.0F - 1.0F) * 0.3F;
         float var3 = (field1.nextFloat() * 2.0F - 1.0F) * 0.3F;
         float var4 = (field1.nextFloat() * 2.0F - 1.0F) * 0.3F;
         GL11.glTranslatef(var2, var3, var4);
      }

      float var5 = -Minecraft.getMinecraft().renderManager.playerViewX;
      float var6 = 180.0F - Minecraft.getMinecraft().renderManager.playerViewY;
      GL11.glRotatef(var6, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(var5, 1.0F, 0.0F, 0.0F);
   }

   private boolean method6(ItemStack var1) {
      Item var2 = var1.getItem();
      return var2 instanceof ItemPotion
         || var2 instanceof ItemArmor && ((ItemArmor)var1.getItem()).blockMaterial == ArmorMaterial.leather
         || var2 instanceof ItemMonsterPlacer
         || var2 instanceof ItemFireworkCharge
         || ThreadModuleDump63.MC_VERSION >= 5 && var2 instanceof ItemTippedArrow;
   }

   private ResourceLocation method7(ItemStack var1) {
      if (this.method6(var1)) {
         Item var2 = var1.getItem();
         if (var2 instanceof ItemPotion) {
            if (ThreadModuleDump63.MC_VERSION >= 5) {
               if (var2 instanceof ItemSplashPotion) {
                  return field2;
               }

               if (var2 instanceof ItemLingeringPotion) {
                  return field4;
               }
            }

            return field3;
         } else if (var2 instanceof ItemMonsterPlacer) {
            return field5;
         } else if (var2 instanceof ItemFireworkCharge) {
            return field6;
         } else {
            return ThreadModuleDump63.MC_VERSION >= 5 && var2 instanceof ItemTippedArrow
               ? field7
               : field8.get(ThreadModuleDump63.MC_VERSION >= 5 ? ((ItemArmor)var2).armorType$v1_12.index : ((ItemArmor)var2).armorType);
         }
      } else {
         return null;
      }
   }

   private WorldRenderer method8(WorldRenderer var1, int var2, int var3, int var4, boolean var5) {
      if (var5) {
         var1.color(var2, var3, var4, 255);
      }

      return var1;
   }

   private float method9(ItemEntityBridge var1) {
      GroundItemTransformEvent var2 = ClientEventBus.method29()
         .method12(GroundItemTransformEvent.class, () -> new GroundItemTransformEvent(AbstractRenderContext.method32(), var1.bridge$getItemState(), true, false));
      return var2 == null ? 1.0F : var2.getScale();
   }

   static {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         field7 = new ResourceLocation("minecraft", "items/tipped_arrow_base");
      }

      field8 = ImmutableList.of(
            new ResourceLocation("minecraft", "items/leather_helmet_overlay"),
            new ResourceLocation("minecraft", "items/leather_chestplate_overlay"),
            new ResourceLocation("minecraft", "items/leather_leggings_overlay"),
            new ResourceLocation("minecraft", "items/leather_boots_overlay")
         )
         .reverse();
   }
}
