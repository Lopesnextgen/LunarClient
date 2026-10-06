package com.moonsworth.lunar.client.mod.skyblock.visitorprofit;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper$Extension3;
import com.moonsworth.lunar.bridge.ClickableTextExtension;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click12;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.GuiType3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
import com.moonsworth.lunar.client.event.render.HologramRenderEvent;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.event.mixin.gui.SlotUpdateEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.text.WordUtils;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockVisitorProfit extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^ (?<book>[\\w- ]+) Book$");
   private static final Pattern field9 = Pattern.compile("^ (?<item>[^+].+?)(?: ❤)?$");
   private static final Pattern field10 = Pattern.compile("^ \\+(?<copper>\\d+) Copper(?: ❤)?$");
   private static final Pattern field11 = Pattern.compile("^ \\+(?<item>\\d+x \\w+ Essence)(?: ❤)?$");
   private final GuiRewindhandlersHandler2 field12 = (GuiRewindhandlersHandler2)this.method11(GuiRewindhandlersHandler2.class);
   private final GuiRewindhandlersHandler22 field13 = (GuiRewindhandlersHandler22)this.method11(GuiRewindhandlersHandler22.class);
   private final ToggleOption field14 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showProfitInLore")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("highlightAcceptRefuse")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("preferAccept")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field17 = (ColorOption)((ColorOption.Data)OptionFactory.method8("highlightColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ColorOption field18 = (ColorOption)((ColorOption.Data)OptionFactory.method8("coinsColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method15()
      .method31();
   private final ColorOption field19 = (ColorOption)((ColorOption.Data)OptionFactory.method8("profitColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method15()
      .method31();
   private final ColorOption field20 = (ColorOption)((ColorOption.Data)OptionFactory.method8("lossColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method15()
      .method31();
   private final List<SkyblockVisitorProfit.Data> field21 = new ArrayList<>();
   private long field22;

   public SkyblockVisitorProfit(Skyblock var1) {
      super(true);
      this.method5(Framework.field16, Framework4.method3(var1));
      this.method5(Framework.field17, Framework2.method2(SettingsPage.FARMING));
      this.method5(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.GARDEN));
      this.handle(SlotUpdateEvent.class, this::method1);
      this.handle(TooltipRenderEvent.TooltipPreRenderEvent.class, this::method2);
      this.handle(HologramRenderEvent.HologramItemRenderEvent.class, this::method3);
      this.handle(ScreenOpenEvent.class, var1x -> this.field21.clear());
   }

   private void method1(SlotUpdateEvent var1) {
      if (this.field13.method7() != HighlightType.OFFER_PETS) {
         ItemStackBridge var2 = var1.method3();
         if (var2 != null && !var2.bridge$isEmpty() && var2.bridge$getDisplayName().endsWith("Accept Offer")) {
            this.field21.clear();
            List var3 = Gui3.method15(var2);
            boolean var4 = false;
            long var5 = 0L;
            long var7 = 0L;

            for (int var9 = 0; var9 < var3.size(); var9++) {
               String var10 = (String)var3.get(var9);
               if (!var4 && var10.equals("Rewards:")) {
                  var4 = true;
               } else if (var10.equals(" Farming Exp Boost")) {
                  String var11 = Gui3.method14(var2).get(var9);
                  GuiType3 var12 = GuiType3.fromCode(var11.charAt(2));
                  if (var12 != null) {
                     int var13 = this.method4("PET_ITEM_FARMING_SKILL_BOOST_" + var12.name());
                     this.field21
                        .add(
                           new SkyblockVisitorProfit.Data(
                              var10,
                              Component.text(
                                 "(" + this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("coinsAmount", new Object[]{ThreadModuleDump40.method10(var13)}) + ")",
                                 TextColor.color(this.field18.method14(0.0F))
                              )
                           )
                        );
                     if (var4) {
                        var7 += var13;
                     } else {
                        var5 += var13;
                     }
                  }
               } else {
                  Matcher var15 = field8.matcher(var10);
                  if (var15.matches()) {
                     int var19 = this.method4("Enchanted Book (" + var15.group("book") + ")");
                     this.field21
                        .add(
                           new SkyblockVisitorProfit.Data(
                              var10,
                              Component.text(
                                 "(" + this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("coinsAmount", new Object[]{ThreadModuleDump40.method10(var19)}) + ")",
                                 TextColor.color(this.field18.method14(0.0F))
                              )
                           )
                        );
                     if (var4) {
                        var7 += var19;
                     } else {
                        var5 += var19;
                     }
                  } else {
                     var15 = field11.matcher(var10);
                     if (var15.matches()) {
                        int var20 = this.method4(var15.group("item"));
                        this.field21
                           .add(
                              new SkyblockVisitorProfit.Data(
                                 var10,
                                 Component.text(
                                    "(" + this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("coinsAmount", new Object[]{ThreadModuleDump40.method10(var20)}) + ")",
                                    TextColor.color(this.field18.method14(0.0F))
                                 )
                              )
                           );
                        if (var4) {
                           var7 += var20;
                        } else {
                           var5 += var20;
                        }
                     } else {
                        var15 = field9.matcher(var10);
                        if (var15.matches()) {
                           int var21 = this.method4(var15.group("item"));
                           this.field21
                              .add(
                                 new SkyblockVisitorProfit.Data(
                                    var10,
                                    Component.text(
                                       "(" + this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("coinsAmount", new Object[]{ThreadModuleDump40.method10(var21)}) + ")",
                                       TextColor.color(this.field18.method14(0.0F))
                                    )
                                 )
                              );
                           if (var4) {
                              var7 += var21;
                           } else {
                              var5 += var21;
                           }
                        } else {
                           var15 = field10.matcher(var10);
                           if (var15.matches()) {
                              SkyblockVisitorProfit.Data2 var22 = this.method13();
                              if (var22 != null) {
                                 int var23 = (int)(Integer.parseInt(var15.group("copper")) * var22.method1());
                                 String var14 = this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(
                                    "copperAmount", new Object[]{ThreadModuleDump40.method10(var23), var22.displayName()}
                                 );
                                 this.field21
                                    .add(new SkyblockVisitorProfit.Data(var10, Component.text("(" + var14 + ")", TextColor.color(this.field18.method14(0.0F)))));
                                 var7 += var23;
                              }
                           }
                        }
                     }
                  }
               }
            }

            this.field22 = var7 - var5;
            this.field21
               .add(
                  new SkyblockVisitorProfit.Data(
                     null,
                     Click12.builder()
                        .method2(this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("profit", new Object[0]))
                        .method4(this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("coinsAmount", new Object[]{ThreadModuleDump40.method10(this.field22)}))
                        .method6(this.field18.method14(0.0F))
                        .method8(this.field22 > 0L ? this.field19.method14(0.0F) : this.field20.method14(0.0F))
                        .build()
                  )
               );
         }
      }
   }

   private void method2(TooltipRenderEvent.TooltipPreRenderEvent var1) {
      if (this.field14.get()) {
         if (this.field13.method7() != HighlightType.OFFER_PETS) {
            if (!this.field21.isEmpty()) {
               ItemStackBridge var2 = (ItemStackBridge)var1.method1().orElse(null);
               if (var2 != null && !var2.bridge$isEmpty() && var2.bridge$getDisplayName().endsWith("Accept Offer")) {
                  List var3 = var1.method3();
                  int var4 = 0;

                  for (SkyblockVisitorProfit.Data var6 : this.field21) {
                     String var7 = var6.method1();
                     TextComponent var8 = var6.method2();
                     if (var7 == null) {
                        var3.add(Click12.createClickableText(var8));
                     } else {
                        for (int var9 = var4; var9 < var3.size(); var9++) {
                           ClickableTextExtension var10 = (ClickableTextExtension)var3.get(var9);
                           if (var10 instanceof MixinHelper$Extension3 var11 && AdventureTextBridge.getTextContent(var11.bridge$getComponent()).contains(var7)) {
                              var3.set(var9, this.method5(var10, var8));
                              var4 = var9 + 1;
                              break;
                           }
                        }
                     }
                  }

                  var1.method2(var3);
               }
            }
         }
      }
   }

   private void method3(HologramRenderEvent.HologramItemRenderEvent var1) {
      if (this.field15.get()) {
         if (!this.field21.isEmpty()) {
            ItemStackBridge var2 = var1.method3().bridge$getItemStack();
            if (var2 != null && !var2.bridge$isEmpty()) {
               String var3 = var2.bridge$getDisplayName();
               boolean var4 = this.field22 == 0L ? this.field16.get() : this.field22 > 0L;
               if (!var4 || var3.endsWith("Accept Offer")) {
                  if (var4 || var3.endsWith("Refuse Offer")) {
                     var1.method1(this.field17.method14(0.0F));
                  }
               }
            }
         }
      }
   }

   private int method4(String var1) {
      if (var1.equals("Green Bandana")) {
         var1 = "GREEN_BANDANA";
      }

      return Math.max(this.field12.method1(var1).getValue(), 0);
   }

   private ClickableTextExtension method5(ClickableTextExtension var1, TextComponent var2) {
      return var1 instanceof MixinHelper$Extension3 var3 ? Click12.createClickableText(var3.bridge$getComponent().appendSpace().append(var2)) : var1;
   }

   private SkyblockVisitorProfit.Data2 method13() {
      Gui2_2 var1 = ((Framework4)this.method7(Framework.field16)).<Skyblock>method1().method15().method29();
      if (var1 == null) {
         return null;
      }

      HashMap var2 = new HashMap();
      var2.putAll(var1.method2());
      var2.putAll(var1.method3());
      var2.putAll(var1.method6());
      var2.putAll(var1.method5());
      var2.putAll(var1.method4());
      Gui3_2 var3 = Collections.max(var2.values(), Comparator.comparingDouble(this::method7));
      String var4 = var3.displayName();
      if (var4 == null) {
         var4 = Gui2.field4.getOrDefault(var3.method2(), WordUtils.capitalizeFully(var3.method2().replace('_', ' ')));
      }

      return new SkyblockVisitorProfit.Data2(var4, this.method7(var3));
   }

   private double method7(Gui3_2 var1) {
      GuiRewindhandlersHandler2.Data2 var2 = this.field12.method5(var1.method1());
      if (var2 == null) {
         return 0.0;
      }

      double var3 = var2.method4().method2();
      if (var3 <= 0.0) {
         return 0.0;
      }

      for (Gui5 var6 : var1.method7()) {
         var3 -= this.field12.method2(var6.id()).getValue() * var6.amount();
      }

      return var3 / var1.getCost();
   }

   @Override
   public String getId() {
      return "SKYBLOCK_VISITOR_PROFIT";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field14});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field15, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16, this.field17})
            );
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.COLOR, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field18, this.field19, this.field20})
      );
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   private class Data {
      private final String field1;
      private final TextComponent field2;

      private Data(String var1, TextComponent var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String method1() {
         return this.field1;
      }

      public TextComponent method2() {
         return this.field2;
      }
   }

   private class Data2 {
      private final String field1;
      private final double field2;

      private Data2(String var1, double var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String displayName() {
         return this.field1;
      }

      public double method1() {
         return this.field2;
      }
   }
}
