package com.moonsworth.lunar.client.mod.render.scrollabletooltips;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.ScrollableTooltipsController;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.ScrollabletooltipsType;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScroll;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;

public class ScrollableTooltips extends AbstractFeature {
   private final ToggleOption startAtTop = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("startAtTop")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption verticalKeybind = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("verticalKeybind")
      .method31();
   private final ModifierKeybindOption horizontalScrollingKey = (ModifierKeybindOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "horizontalScrollingKey"
         )
         .method5(KeyCode.KEY_LSHIFT)
         .method18(this))
      .method11()
      .method31();
   private final ModifierKeybindOption scaleScrollingKey = (ModifierKeybindOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18("scaleScrollingKey")
         .method5(KeyCode.KEY_LCONTROL)
         .method18(this))
      .method11()
      .method31();
   private final FloatOption tooltipScale = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "tooltipScale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.25F, 2.5F))
      .method31();
   private final ScrollableTooltipsController tooltipController = new ScrollableTooltipsController();

   public ScrollableTooltips() {
      super(false);
      this.method2(com.moonsworth.lunar.client.event.render.EventRenderTooltip.TooltipRender.class, this::onTooltipRender, 99);
      this.handle(EventMouseScroll.class, this.tooltipController::method1);
      this.method2(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method1, 99);
   }

   private void method1(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      this.tooltipController.method7(data21);
      if ((Boolean)this.tooltipController.method11().get()) {
         List list2 = data21.method3();
         if (!list2.isEmpty()) {
            int index3 = ClampUtils.clamp(this.tooltipController.method18(), 0, list2.size() - 1);
            this.tooltipController.method19(index3);
            list2.subList(0, index3).clear();
            data21.method2(list2);
         }
      }
   }

   public ScrollabletooltipsType getScrollType() {
      if (this.scaleScrollingKey.isKeyDown()) {
         return ScrollabletooltipsType.SCALE;
      } else if ((Boolean)this.verticalKeybind.get()) {
         return this.horizontalScrollingKey.isKeyDown() ? ScrollabletooltipsType.VERTICAL : ScrollabletooltipsType.HORIZONTAL;
      } else {
         return this.horizontalScrollingKey.isKeyDown() ? ScrollabletooltipsType.HORIZONTAL : ScrollabletooltipsType.VERTICAL;
      }
   }

   public String getId() {
      return "SCROLLABLE_TOOLTIPS";
   }

   private void onTooltipRender(com.moonsworth.lunar.client.event.render.EventRenderTooltip.TooltipRender data1) {
      if (!this.tooltipController.method4()) {
         this.tooltipController.method2(this, data1);
         Scrollabletooltips scrollabletooltips2 = this.tooltipController.method12();
         data1.method1(data1.getX() + (int)scrollabletooltips2.method7().getValue(), data1.getY() + (int)scrollabletooltips2.method8().getValue(), (float)scrollabletooltips2.method9().getValue());
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(
            new ClientOption[]{this.startAtTop, this.verticalKeybind, this.horizontalScrollingKey, this.scaleScrollingKey, this.tooltipScale, this.tooltipController.method10(), this.tooltipController.method11()}
         )
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method3(new String[]{"Sk1er"}).method11(this);
   }

   public ItemStackBridge method14() {
      if (Ref.method3().bridge$getCurrentScreenOrRewind() instanceof GuiContainerBridge bridge5extension_32) {
         SlotBridge bridge3_183 = bridge5extension_32.bridge$getHoveredSlot();
         return bridge3_183 == null ? null : bridge3_183.bridge$getItemStack();
      } else {
         return null;
      }
   }

   @Generated
   public ToggleOption getStartAtTop() {
      return this.startAtTop;
   }

   @Generated
   public FloatOption getTooltipScale() {
      return this.tooltipScale;
   }

   @Generated
   public ScrollableTooltipsController getTooltipController() {
      return this.tooltipController;
   }
}
