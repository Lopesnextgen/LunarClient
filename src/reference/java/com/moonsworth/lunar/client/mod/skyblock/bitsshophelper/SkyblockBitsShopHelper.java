package com.moonsworth.lunar.client.mod.skyblock.bitsshophelper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickableTextExtension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click12;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click17;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3$Type;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui4_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui_3;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.event.render.ScreenInitEvent;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.text.WordUtils;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockBitsShopHelper extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final float field9 = 18.0F;
   private static final List<SkyblockBitsShopHelper.Data> field10 = List.of(
      new SkyblockBitsShopHelper.Data("Speed Enrichment", 1080, Gui3$Type.ENRICHMENT),
      new SkyblockBitsShopHelper.Data("God Potion", 1020, Gui3$Type.ITEM),
      new SkyblockBitsShopHelper.Data("Kismet Feather", 960, Gui3$Type.ITEM)
   );
   private final GuiRewindhandlersHandler22 field11 = (GuiRewindhandlersHandler22)this.method11(GuiRewindhandlersHandler22.class);
   private final GuiRewindhandlersHandler2 field12 = (GuiRewindhandlersHandler2)this.method11(GuiRewindhandlersHandler2.class);
   private final GuiRewindhandlersHandler2_2 field13 = (GuiRewindhandlersHandler2_2)this.method11(GuiRewindhandlersHandler2_2.class);
   private final ToggleOption field14 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showInTooltip")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showBestItemsList")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showDyes")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showEnchantments")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showEnrichments")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showAbicases")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field20 = (ColorOption)((ColorOption.Data)OptionFactory.method8("titleColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field21 = (ColorOption)((ColorOption.Data)OptionFactory.method8("itemNameColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption field22 = (ColorOption)((ColorOption.Data)OptionFactory.method8("coinsPerBitColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141121))
      .method31();
   private final List<Gui4_2> field23 = new ArrayList<>();
   private float field24 = 0.0F;
   private float field25 = 0.0F;

   public SkyblockBitsShopHelper(Skyblock var1) {
      super(false);
      this.method5(Framework.field16, Framework4.method3(var1));
      this.method5(Framework.field17, Framework2.method2(SettingsPage.INVENTORY));
      this.method5(Framework.field19, Framework11.method1(this, Click3::hasIsland));
      this.method5(Framework.field1, Nameplate.method4(new Nameplate2(this, this::method13)));
      this.handle(ScreenInitEvent.ScreenInitPostEvent.class, this::method1);
      this.handle(ContainerSlotRenderEvent.ContainerSlotPostEvent.class, this::method2);
      this.handle(TooltipRenderEvent.TooltipPreRenderEvent.class, this::method4);
   }

   private void method1(ScreenInitEvent.ScreenInitPostEvent var1) {
      if (this.field15.get()) {
         this.field23.clear();
         HighlightType var2 = this.field11.method7();
         if (var2 != null && var2.isBitsShopGui()) {
            Gui2_2 var3 = ((Framework4)this.method7(Framework.field16)).<Skyblock>method1().method15().method29();
            if (var3 != null) {
               for (Gui3_2 var6 : var3.method1()
                  .values()
                  .stream()
                  .filter(var1x -> this.method7(var1x) > 0.0 && this.method5(var1x.method4()))
                  .sorted((var1x, var2x) -> Double.compare(this.method7(var2x), this.method7(var1x)))
                  .limit(12L)
                  .toList()) {
                  String var7 = var6.method2();
                  String var8 = var6.displayName();
                  if (var8 == null) {
                     var8 = Gui2.field4.getOrDefault(var7, WordUtils.capitalizeFully(var7.replace('_', ' ')));
                  }

                  TextComponent var9 = AdventureTextBridge.asAdventure(var8 + ": ");
                  TextComponent var10 = Component.text(field8.format(this.method7(var6)));
                  ItemStackBridge var11;
                  if (var6.method5() != null) {
                     String[] var12 = var6.method5().split(":");
                     Bridge6_4 var13 = Bridge.method28().method22(var12[0]);
                     var11 = Bridge.method8().method38(var13);
                     if (var12.length > 1) {
                        var11.bridge$setItemDamage(Integer.parseInt(var12[1]));
                     }
                  } else if (var6.method6() != null) {
                     Gui_3 var14 = var6.method6();
                     var11 = Gui3.method12(var14.id(), var14.method1(), var14.signature());
                  } else {
                     var11 = Gui2.method3(var7);
                  }

                  if (var11 != null) {
                     var11.bridge$setStackDisplayName(AdventureTextBridge.asAdventure(var8));
                  }

                  this.field23.add(new Gui4_2(var9, var10, var11));
                  float var15 = ThreadModuleDump63.method10().bridge$getStringWidth(var9);
                  float var16 = var15 + ThreadModuleDump63.method10().bridge$getStringWidth(var10) + 18.0F;
                  if (var16 > this.field25) {
                     this.field24 = var15;
                     this.field25 = var16;
                  }
               }
            }
         }
      }
   }

   private void method2(ContainerSlotRenderEvent.ContainerSlotPostEvent var1) {
      if (this.field15.get()) {
         if (!this.field23.isEmpty()) {
            Bridge5Extension_3 var2 = this.field13.method6();
            if (this.field11.method7() == HighlightType.COMMUNITY_SHOP) {
               ItemStackBridge var3 = var2.bridge$inventorySlots().get(13).bridge$getItemStack();
               if (Gui3.method5(var3) != Gui3.Type2.LIME) {
                  return;
               }
            }

            this.method3(var1.method5());
         }
      }
   }

   private void method3(MixinHelper_4 var1) {
      MixinCore9Extension var2 = (MixinCore9Extension)this.method7(Framework.field1);
      float var3 = (1 + this.field23.size()) * 18.0F;
      var2.method16(this.field25, var3);
      float var4 = Math.abs(18.0F - ThreadModuleDump63.method10().method19()) / 2.0F;
      Click.pushHudTransform(var1, var2);
      var1.method10(
         ThreadModuleDump63.method10(),
         Component.text(this.method14("title", new Object[0])).decorate(TextDecoration.BOLD),
         0,
         0,
         this.field20.method14(0.0F),
         true
      );
      var1.method39(0.0F, 18.0F);

      for (Gui4_2 var6 : this.field23) {
         if (var6.method3() != null) {
            var1.method34(var6.method3(), 0, 0, ThreadModuleDump63.method3());
         }

         var1.push();
         var1.method39(18.0F, var4);
         var1.method10(ThreadModuleDump63.method10(), var6.method1(), 0, 0, this.field21.method14(0.0F), true);
         var1.method39(this.field24, 0.0F);
         var1.method10(ThreadModuleDump63.method10(), var6.method2(), 0, 0, this.field22.method14(0.0F), true);
         var1.pop();
         var1.method39(0.0F, 18.0F);
      }

      var1.pop();
   }

   private void method4(TooltipRenderEvent.TooltipPreRenderEvent var1) {
      if (this.field14.get()) {
         HighlightType var2 = this.field11.method7();
         if (var2 != null && var2.isBitsShopGui()) {
            Gui2_2 var3 = ((Framework4)this.method7(Framework.field16)).<Skyblock>method1().method15().method29();
            if (var3 != null) {
               ItemStackBridge var4 = (ItemStackBridge)var1.method1().orElse(null);
               String var5 = Gui3.method3(var4);
               Gui3_2 var6 = var3.method1().get(var5);
               if (var6 != null) {
                  double var7 = this.method7(var6);
                  if (!(var7 <= 0.0)) {
                     TextComponent var9 = Click12.builder().method2(this.method14("coinsPerBit", new Object[0])).method4(field8.format(var7)).build();
                     List var10 = var1.method3();
                     var10.add((ClickableTextExtension)Bridge.method8().method89(var9));
                     var1.method2(var10);
                  }
               }
            }
         }
      }
   }

   private boolean method5(Gui3$Type var1) {
      return switch (var1) {
         case DYE -> this.field16.get();
         case ENCHANTMENT -> this.field17.get();
         case ENRICHMENT -> this.field18.get();
         case ABICASE -> this.field19.get();
         default -> true;
      };
   }

   private List<TextComponent> method13() {
      if (!this.field15.get()) {
         return List.of();
      }

      ArrayList var1 = new ArrayList();

      for (SkyblockBitsShopHelper.Data var3 : field10) {
         if (this.method5(var3.method2())) {
            var1.add(Files6_2.method1(var3.name(), field8.format(var3.method1())));
         }
      }

      return Click17.createLoreLines(this.method14("title", new Object[0]), this.field20.method14(0.0F), this.field21.method14(0.0F), this.field22.method14(0.0F), var1);
   }

   private double method7(Gui3_2 var1) {
      double var2 = this.method8(var1.method1());
      if (var2 <= 0.0) {
         return -1.0;
      }

      for (Gui5 var5 : var1.method7()) {
         var2 -= this.method8(var5.id()) * var5.amount();
      }

      return var2 / var1.getCost();
   }

   private double method8(String var1) {
      GuiRewindhandlersHandler2.Data3 var2 = this.field12.method4(var1);
      if (var2 != null) {
         return var2.method1();
      }

      GuiRewindhandlersHandler2.Data2 var3 = this.field12.method5(var1);
      return var3 != null ? var3.method4().method6() : -1.0;
   }

   @Override
   public String getId() {
      return "SKYBLOCK_BITS_SHOP_HELPER";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.method9(new ClientOption[]{this.field14});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field15,
               var1xx -> var1xx.method9(
                  new ClientOption[]{this.field16, this.field17, this.field18, this.field19, this.field20, this.field21, this.field22}
               )
            );
         }
      );
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   private class Data {
      private final String field1;
      private final int field2;
      private final Gui3$Type field3;

      private Data(String var1, int var2, Gui3$Type var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public String name() {
         return this.field1;
      }

      public int method1() {
         return this.field2;
      }

      public Gui3$Type method2() {
         return this.field3;
      }
   }
}
