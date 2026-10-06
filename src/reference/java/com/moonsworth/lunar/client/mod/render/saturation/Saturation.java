package com.moonsworth.lunar.client.mod.render.saturation;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_30;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper$Extension2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.saturation.Saturation2;
import com.moonsworth.lunar.client.framework.feature.saturation.Saturation3;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent;
import com.moonsworth.lunar.client.event.render.PlayerStatsRenderEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.highlight.AlertUpdateEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.render.saturation.SaturationHud;
import com.moonsworth.lunar.client.util.ThreadModuleDump2;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.client.util.ThreadModuleDump90;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Saturation extends AbstractFeature {
   private final ToggleOption showSaturationOverlay = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showSaturationOverlay"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showHeldItemHunger = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showHeldItemHunger"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showHeldItemSaturation = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showHeldItemSaturation"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showAppleskinTooltip = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showAppleskinTooltip"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption useInverseColor = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "useInverseColor"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ColorOption saturationColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "saturationColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-14848))
      .method31();
   private final Saturation2 iconRenderer = new Saturation2();
   private final Saturation3 tooltipProvider = new Saturation3(this, this.iconRenderer);
   private float flashProgress;
   private float flashAlpha;
   private byte flashDirection = 1;

   public Saturation() {
      super(true);
      this.handle(PlayerStatsRenderEvent.class, this::onHudRender);
      this.handle(TooltipRenderEvent.TooltipPreRenderEvent.class, this::addAppleskinTooltipLine);
      this.handle(EventClientTick.class, this::updateFlash);
      ClientEventBus.method29().method2(AlertUpdateEvent.class, var1 -> this.iconRenderer.reset());
   }

   @Override
   public String getId() {
      return "SATURATION";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field4).method3("squeek502 (AppleSkin)").method11(this);
   }

   @Override
   protected List<Framework7Extension> renderHeldItemFoodIcons() {
      return List.of(new SaturationHud(this));
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method1("appleSkinOptions", var1x -> {
         var1x.method9(new ClientOption[]{this.showSaturationOverlay, this.showHeldItemHunger, this.showHeldItemSaturation, this.showAppleskinTooltip, this.useInverseColor});
         var1x.method9(new ClientOption[]{this.saturationColor}).method3(this.useInverseColor::get);
      });
   }

   private void updateFlash(EventClientTick var1) {
      this.flashProgress = this.flashProgress + this.flashDirection * 0.125F;
      if (this.flashProgress >= 1.5F) {
         this.flashDirection = -1;
      } else if (this.flashProgress <= -0.5F) {
         this.flashDirection = 1;
      }

      this.flashAlpha = ClampUtils.clamp(this.flashProgress, 0.0F, 1.0F) * 0.85F;
   }

   private void resetFlash() {
      this.flashProgress = 0.0F;
      this.flashAlpha = 0.0F;
      this.flashDirection = 1;
   }

   private void addAppleskinTooltipLine(TooltipRenderEvent.TooltipPreRenderEvent var1) {
      if (this.showAppleskinTooltip.get()) {
         ItemStackBridge var2 = (ItemStackBridge)var1.HHCOCHOIIIOOROCORRRRORORIOOHRC().orElse(null);
         List var3 = var1.method3();
         if (var2 != null && !var3.isEmpty()) {
            MixinHelper$Extension2 var4 = this.tooltipProvider.method1(var2);
            if (var4 != null) {
               var3.add(1, var4);
               var1.method2(var3);
            }
         }
      }
   }

   private void onHudRender(PlayerStatsRenderEvent var1) {
      if (!var1.method5()) {
         this.resetFlash();
      } else {
         Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
         MixinHelper_4 var3 = var1.method2();
         ThreadModuleDump71 var4 = LcuiScreen.method151();
         int var5 = var4.getScaledHeight() - 39;
         int var6 = var4.getScaledWidth() / 2 + 91;
         if (this.showSaturationOverlay.get()) {
            this.renderFoodOverlay(var3, var2, 0.0F, var6, var5, 1.0F);
         }

         ItemStackBridge var7 = var2.bridge$getHeldItem();
         if (var7 != null && !var7.bridge$isEmpty() && ThreadModuleDump2.canEat(var7, var2)) {
            ThreadModuleDump90 var8 = ThreadModuleDump2.getPlayerFoodValues(var7, var2);
            if (this.showHeldItemHunger.get()) {
               this.renderHeldItemFoodIcons(var3, var2, var8.method1(), var6, var5, this.flashAlpha, ThreadModuleDump2.givesBadEffect(var7));
            }

            if (this.showHeldItemSaturation.get()) {
               Bridge2_30 var9 = var2.bridge$getFoodStats();
               int var10 = (int)var9.bridge$getFoodLevel() + var8.method1();
               float var11 = var9.bridge$getSaturationLevel() + var8.method2();
               float var12 = var11 > var10 ? var10 - var9.bridge$getSaturationLevel() : var8.method2();
               this.renderFoodOverlay(var3, var2, var12, var6, var5, this.flashAlpha);
            }
         } else {
            this.resetFlash();
         }
      }
   }

   private void renderFoodOverlay(MixinHelper_4 var1, Bridge5_11 var2, float var3, int var4, int var5, float var6) {
      Bridge2_30 var7 = var2.bridge$getFoodStats();
      float var8 = var7.bridge$getSaturationLevel();
      if (!(var8 + var3 < 0.0F)) {
         float var9 = ClampUtils.clamp(var8 + var3, 0.0F, 20.0F);
         int var10 = var3 != 0.0F ? (int)Math.max(var8 / 2.0F, 0.0F) : 0;
         int var11 = (int)Math.ceil(var9 / 2.0F);
         if (var10 < var11) {
            Fog2 var12 = Bridge.method36().method8(17);
            boolean var13 = var12 != null && var2.bridge$isPotionActive(var12);

            for (int var14 = var10; var14 < var11; var14++) {
               float var15 = var4 - var14 * 8 - 9;
               float var16 = this.applyHungerJitter(var5, var7);
               int var17 = ThreadModuleDump23.method18(this.getSaturationColor(var13, var15 + var16), var6);
               this.iconRenderer.method1(var1, var13, var15, var16, var9 / 2.0F - var14, var17);
            }
         }
      }
   }

   private void renderHeldItemFoodIcons(MixinHelper_4 var1, Bridge5_11 var2, int var3, int var4, int var5, float var6, boolean var7) {
      if (var3 > 0) {
         Bridge2_30 var8 = var2.bridge$getFoodStats();
         int var9 = (int)var8.bridge$getFoodLevel();
         int var10 = ClampUtils.clamp(var9 + var3, 0, 20);
         int var11 = Math.max(0, var9 / 2);
         int var12 = (int)Math.ceil(var10 / 2.0F);

         for (int var13 = var11; var13 < var12; var13++) {
            float var14 = var4 - var13 * 8 - 9;
            float var15 = this.applyHungerJitter(var5, var8);
            boolean var16 = var13 * 2 + 1 == var10;
            this.drawIcon(
               com.moonsworth.lunar.client.framework.feature.saturation.Saturation.method5(var7, var16),
               var1,
               var14,
               var15,
               ThreadModuleDump23.method26(-1, var6 * 0.25F)
            );
            this.drawIcon(
               var16
                  ? com.moonsworth.lunar.client.framework.feature.saturation.Saturation.method2(var7)
                  : com.moonsworth.lunar.client.framework.feature.saturation.Saturation.method1(var7),
               var1,
               var14,
               var15,
               ThreadModuleDump23.method26(-1, var6)
            );
         }
      }
   }

   private void drawIcon(com.moonsworth.lunar.client.framework.feature.saturation.Saturation.Data var1, MixinHelper_4 var2, float var3, float var4, int var5) {
      LcuiScreen.method37(
         var2, var1.method1(), var3, var4, var1.method3(), var1.method3(), var1.method2(), var1.v(), var1.method2() + 9, var1.v() + 9, var5
      );
   }

   private float applyHungerJitter(float var1, Bridge2_30 var2) {
      if (var2.bridge$getSaturationLevel() <= 0.0F && this.mc.bridge$getGuiIngame().bridge$getTicks() % (var2.bridge$getFoodLevel() * 3.0F + 1.0F) == 0.0F) {
         var1 += ThreadLocalRandom.current().nextInt(3) - 1;
      }

      return var1;
   }

   public int getSaturationColor(boolean var1, float var2) {
      return this.useInverseColor.get() ? this.iconRenderer.method4(var1) : this.saturationColor.method14(var2);
   }
}
