package com.moonsworth.lunar.client.mod.skyblock.skymarthelper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
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
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
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
import java.util.Map;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.text.WordUtils;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSkymartHelper extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final float field9 = 18.0F;
   private static final List<SkyblockSkymartHelper.Data> field10 = List.of(
      new SkyblockSkymartHelper.Data("Basic Gardening Hoe", 24000, Gui3$Type.TOOL),
      new SkyblockSkymartHelper.Data("Basic Gardening Axe", 18000, Gui3$Type.TOOL),
      new SkyblockSkymartHelper.Data("Garden Scythe", 14450, Gui3$Type.TOOL),
      new SkyblockSkymartHelper.Data("Advanced Gardening Axe", 10450, Gui3$Type.TOOL),
      new SkyblockSkymartHelper.Data("Peony Belt", 10000, Gui3$Type.EQUIPMENT),
      new SkyblockSkymartHelper.Data("Builder's Ruler", 10000, Gui3$Type.ITEM),
      new SkyblockSkymartHelper.Data("Advanced Gardening Hoe", 7500, Gui3$Type.TOOL),
      new SkyblockSkymartHelper.Data("Peony Necklace", 7450, Gui3$Type.EQUIPMENT),
      new SkyblockSkymartHelper.Data("Plant Diagnostics Tool", 7000, Gui3$Type.ITEM),
      new SkyblockSkymartHelper.Data("Peony Bracelet", 6800, Gui3$Type.EQUIPMENT),
      new SkyblockSkymartHelper.Data("HydroCan™ Ultra 3000", 4120, Gui3$Type.WATERING_CAN),
      new SkyblockSkymartHelper.Data("HydroCan™ Turbo 2000", 4050, Gui3$Type.WATERING_CAN)
   );
   private final GuiRewindhandlersHandler22 field11 = (GuiRewindhandlersHandler22)this.method11(GuiRewindhandlersHandler22.class);
   private final GuiRewindhandlersHandler2 field12 = (GuiRewindhandlersHandler2)this.method11(GuiRewindhandlersHandler2.class);
   private final ToggleOption field13 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showInTooltip")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showBestItemsList")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showEnchantments")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showEquipment")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showReforges")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showWateringCans")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showVacuums")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field20 = (ColorOption)((ColorOption.Data)OptionFactory.method8("titleColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field21 = (ColorOption)((ColorOption.Data)OptionFactory.method8("itemNameColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption field22 = (ColorOption)((ColorOption.Data)OptionFactory.method8("coinsPerCopperColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141121))
      .method31();
   private final List<Gui4_2> field23 = new ArrayList<>();
   private float field24 = 0.0F;
   private float field25 = 0.0F;

   public SkyblockSkymartHelper(Skyblock var1) {
      super(false);
      this.method3(Framework.field16, Framework4.method3(var1));
      this.method3(Framework.field17, Framework2.method2(SettingsPage.FARMING));
      this.method3(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.GARDEN));
      this.method3(Framework.field1, Nameplate.method4(new Nameplate2(this, this::method13)));
      this.handle(ScreenInitEvent.ScreenInitPostEvent.class, this::method1);
      this.handle(ContainerSlotRenderEvent.ContainerSlotPostEvent.class, this::method2);
      this.handle(TooltipRenderEvent.TooltipPreRenderEvent.class, this::method5);
   }

   private void method1(ScreenInitEvent.ScreenInitPostEvent var1) {
      if (this.field14.get()) {
         this.field23.clear();
         Map var2 = this.method7(this.field11.method7());
         if (var2 != null) {
            for (Gui3_2 var5 : var2.values()
               .stream()
               .filter(var1x -> this.method6(var1x) > 0.0 && this.method3(var1x.method4()))
               .sorted((var1x, var2x) -> Double.compare(this.method6(var2x), this.method6(var1x)))
               .limit(12L)
               .toList()) {
               String var6 = var5.method2();
               String var7 = var5.displayName();
               if (var7 == null) {
                  var7 = Gui2.field4.getOrDefault(var6, WordUtils.capitalizeFully(var6.replace('_', ' ')));
               }

               TextComponent var8 = AdventureTextBridge.asAdventure(var7 + ": ");
               TextComponent var9 = Component.text(field8.format(this.method6(var5)));
               ItemStackBridge var10;
               if (var5.method5() != null) {
                  String[] var11 = var5.method5().split(":");
                  Bridge6_4 var12 = Bridge.method28().method22(var11[0]);
                  var10 = Bridge.method8().method38(var12);
                  if (var11.length > 1) {
                     var10.bridge$setItemDamage(Integer.parseInt(var11[1]));
                  }
               } else if (var5.method6() != null) {
                  Gui_3 var13 = var5.method6();
                  var10 = Gui3.method12(var13.id(), var13.method1(), var13.signature());
               } else {
                  var10 = Gui2.method3(var6);
               }

               if (var10 != null) {
                  var10.bridge$setStackDisplayName(AdventureTextBridge.asAdventure(var7));
               }

               this.field23.add(new Gui4_2(var8, var9, var10));
               float var14 = ThreadModuleDump63.method10().bridge$getStringWidth(var8);
               float var15 = var14 + ThreadModuleDump63.method10().bridge$getStringWidth(var9) + 18.0F;
               if (var15 > this.field25) {
                  this.field24 = var14;
                  this.field25 = var15;
               }
            }
         }
      }
   }

   private void method2(ContainerSlotRenderEvent.ContainerSlotPostEvent var1) {
      if (this.field14.get()) {
         if (!this.field23.isEmpty()) {
            MixinHelper_4 var2 = var1.method5();
            MixinCore9Extension var3 = (MixinCore9Extension)this.method7(Framework.field1);
            float var4 = (1 + this.field23.size()) * 18.0F;
            var3.method16(this.field25, var4);
            float var5 = Math.abs(18.0F - ThreadModuleDump63.method10().method19()) / 2.0F;
            Click.pushHudTransform(var2, var3);
            var2.method10(
               ThreadModuleDump63.method10(),
               Component.text(this.method12("title", new Object[0])).decorate(TextDecoration.BOLD),
               0,
               0,
               this.field20.method14(0.0F),
               true
            );
            var2.method39(0.0F, 18.0F);

            for (Gui4_2 var7 : this.field23) {
               if (var7.method3() != null) {
                  var2.method34(var7.method3(), 0, 0, ThreadModuleDump63.method3());
               }

               var2.push();
               var2.method39(18.0F, var5);
               var2.method10(ThreadModuleDump63.method10(), var7.method1(), 0, 0, this.field21.method14(0.0F), true);
               var2.method39(this.field24, 0.0F);
               var2.method10(ThreadModuleDump63.method10(), var7.method2(), 0, 0, this.field22.method14(0.0F), true);
               var2.pop();
               var2.method39(0.0F, 18.0F);
            }

            var2.pop();
         }
      }
   }

   private boolean method3(Gui3$Type var1) {
      return switch (var1) {
         case ENCHANTMENT -> this.field15.get();
         case EQUIPMENT -> this.field16.get();
         case REFORGE -> this.field17.get();
         case WATERING_CAN -> this.field18.get();
         case VACUUM -> this.field19.get();
         default -> true;
      };
   }

   private List<TextComponent> method13() {
      if (!this.field14.get()) {
         return List.of();
      }

      ArrayList var1 = new ArrayList();

      for (SkyblockSkymartHelper.Data var3 : field10) {
         if (this.method3(var3.method2())) {
            var1.add(Files6_2.method1(var3.name(), field8.format(var3.method1())));
         }
      }

      return Click17.createLoreLines(this.method12("title", new Object[0]), this.field20.method13(), this.field21.method13(), this.field22.method13(), var1);
   }

   private void method5(TooltipRenderEvent.TooltipPreRenderEvent var1) {
      if (this.field13.get()) {
         Map var2 = this.method7(this.field11.method7());
         if (var2 != null) {
            ItemStackBridge var3 = (ItemStackBridge)var1.method1().orElse(null);
            String var4 = Gui3.method3(var3);
            Gui3_2 var5 = (Gui3_2)var2.get(var4);
            if (var5 != null) {
               double var6 = this.method6(var5);
               if (!(var6 <= 0.0)) {
                  TextComponent var8 = Click12.builder().method2(this.method12("coinsPerCopper", new Object[0])).method4(field8.format(var6)).build();
                  List var9 = var1.method3();
                  var9.add((ClickableTextExtension)Bridge.method8().method89(var8));
                  var1.method2(var9);
               }
            }
         }
      }
   }

   private double method6(Gui3_2 var1) {
      double var2 = this.field12.method3(var1.method1(), true).getValue();
      if (var2 <= 0.0) {
         return -1.0;
      }

      for (Gui5 var5 : var1.method7()) {
         var2 -= this.field12.method2(var5.id()).getValue() * var5.amount();
      }

      return var2 / var1.getCost();
   }

   private Map<String, Gui3_2> method7(HighlightType var1) {
      if (var1 == null) {
         return null;
      }

      Gui2_2 var2 = ((Framework4)this.method7(Framework.field16)).<Skyblock>method1().method15().method29();
      if (var2 == null) {
         return null;
      }

      return switch (var1) {
         case SKYMART_FARMING_ESSENTIALS -> var2.method2();
         case SKYMART_FARMING_TOOLS -> var2.method3();
         case SKYMART_BARN_SKINS -> var2.method4();
         case SKYMART_GREENHOUSE_SKINS -> var2.method5();
         case SKYMART_PESTS -> var2.method6();
         default -> null;
      };
   }

   @Override
   public String getId() {
      return "SKYBLOCK_SKYMART_HELPER";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.method9(new ClientOption[]{this.field13});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field14,
               var1xx -> var1xx.method9(
                  new ClientOption[]{this.field15, this.field16, this.field17, this.field18, this.field19, this.field20, this.field21, this.field22}
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
