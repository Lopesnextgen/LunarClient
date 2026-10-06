package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateRabbit;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.NameplateComponent;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.joml.Vector4f;

public class SkyblockChocolateStats extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getNumberInstance(Locale.ROOT);
   private final HighlightTypeListener field9 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private Vector4f field10;
   private boolean field11 = false;

   public SkyblockChocolateStats(SkyblockChocolateFactory skyblockchocolatefactory1, ToggleOption lightingextension4432) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method4(false, skyblockchocolatefactory1));
      this.method45(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method45(ModTraits.field1, HudVisibilityWrapper.method4(new NameplateComponent(this, this::method14)));
      this.handle(EventRenderContainerSlotPost.class, arg1x -> {
         this.method3(arg1x);
         this.method4(arg1x);
      });
      this.handle(EventMarkerInput.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_CHOCOLATE_STATS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventMarkerInput highlightimpl141) {
      if (this.method15()) {
         if (this.field10 != null && this.field11) {
            if (highlightimpl141.method3() == 0 && highlightimpl141.method4() == MouseInputType.CLICK) {
               StringBuilder builder2 = new StringBuilder();

               for (TextComponent text5 : this.method13()) {
                  builder2.append(TextBridge.getTextContent(text5)).append("\n");
               }

               ClipboardUtils.method2(builder2.toString());
               Ref.method4().method69().method3(NotificationManager.method15("copiedStats", new Object[0]));
            }
         }
      }
   }

   private void method3(EventRenderContainerSlotPost data31) {
      if (this.method15()) {
         this.field10 = WorldRenderUtils.renderTooltip(data31, this.method13(), (MixinCore9Extension)this.method7(ModTraits.field1));
      }
   }

   private void method4(EventRenderContainerSlotPost data31) {
      if (this.method15()) {
         if (this.field10 != null) {
            Data4 data42 = data31.OOCCRCHHRHRCRICOCORHROHCRCROHO();
            float value3 = this.field10.x();
            float value4 = this.field10.y();
            float value5 = this.field10.z();
            float value6 = this.field10.w();
            if (!(data42.HHHCHORHIHRCOHIOICICICHCRRICCI() < value3)
               && !(data42.HHHCHORHIHRCOHIOICICICHCRRICCI() > value4)
               && !(data42.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < value5)
               && !(data42.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > value6)) {
               this.field11 = true;
               LcuiScreen.method85(
                  data31.method5(), Collections.singletonList("Copy to Clipboard"), data42.xi(), data42.RROCOHICOORRHCIHHHCHRCICHIIHCO()
               );
            } else {
               this.field11 = false;
            }
         }
      }
   }

   private List<TextComponent> method13() {
      ArrayList list1 = new ArrayList();
      SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      Style style3 = TextComponentFactory.styleOf(skyblockchocolatefactory2.method51()).decorate(TextDecoration.BOLD);
      Style style4 = TextComponentFactory.styleOf(skyblockchocolatefactory2.method52());
      Style style5 = TextComponentFactory.styleOf(skyblockchocolatefactory2.method53());
      Style style6 = TextComponentFactory.styleOf(skyblockchocolatefactory2.method54());
      NumberFormat numberformat7 = NumberFormat.getIntegerInstance(Locale.ROOT);
      NumberFormat numberformat8 = NumberFormat.getNumberInstance(Locale.ROOT);
      numberformat8.setMaximumFractionDigits(2);
      String text9 = numberformat7.format(skyblockchocolatefactory2.method57() + skyblockchocolatefactory2.method74());
      String text10 = numberformat7.format(skyblockchocolatefactory2.method58() + skyblockchocolatefactory2.method74());
      String text11 = numberformat7.format(skyblockchocolatefactory2.method59() + skyblockchocolatefactory2.method74());
      String text12 = numberformat8.format(skyblockchocolatefactory2.method61());
      String text13 = numberformat8.format(skyblockchocolatefactory2.method61() * 60.0);
      String text14 = numberformat8.format(skyblockchocolatefactory2.method61() * 3600.0);
      String text15 = numberformat8.format(skyblockchocolatefactory2.method61() * 86400.0);
      String text16 = numberformat7.format(skyblockchocolatefactory2.method70());
      list1.add((TextComponent)Component.text("Chocolate Factory Stats").style(style3));
      list1.add((TextComponent)((TextComponent)Component.text("Current Chocolate: ").style(style4)).append(Component.text(text9).style(style5)));
      if (skyblockchocolatefactory2.method64() > 1) {
         list1.add((TextComponent)((TextComponent)Component.text("This Prestige: ").style(style4)).append(Component.text(text10).style(style5)));
      }

      list1.add((TextComponent)((TextComponent)Component.text("All-time: ").style(style4)).append(Component.text(text11).style(style5)));
      list1.add(Component.empty());
      list1.add((TextComponent)((TextComponent)Component.text("Per Second: ").style(style4)).append(Component.text(text12).style(style5)));
      list1.add((TextComponent)((TextComponent)Component.text("Per Minute: ").style(style4)).append(Component.text(text13).style(style5)));
      list1.add((TextComponent)((TextComponent)Component.text("Per Hour: ").style(style4)).append(Component.text(text14).style(style5)));
      list1.add((TextComponent)((TextComponent)Component.text("Per Day: ").style(style4)).append(Component.text(text15).style(style5)));
      list1.add(Component.empty());
      list1.add((TextComponent)((TextComponent)Component.text("Multiplier: ").style(style4)).append(Component.text(skyblockchocolatefactory2.method63() + "x").style(style5)));
      list1.add((TextComponent)((TextComponent)Component.text("Barn: ").style(style4)).append(Component.text(skyblockchocolatefactory2.method65() + "/" + skyblockchocolatefactory2.method66()).style(style5)));
      list1.add(
         (TextComponent)((TextComponent)Component.text("Hitman: ").style(style4)).append(Component.text(skyblockchocolatefactory2.method67() + "/" + skyblockchocolatefactory2.method68()).style(style5))
      );
      list1.add((TextComponent)((TextComponent)Component.text("Leaderboard Position: ").style(style4)).append(Component.text("#" + text16).style(style6)));
      list1.add((TextComponent)((TextComponent)Component.text("Time Tower: ").style(style4)).append(Component.text(skyblockchocolatefactory2.method72() + "/3 Charges").style(style5)));
      if (skyblockchocolatefactory2.method71() && skyblockchocolatefactory2.method73() != null) {
         long number17 = (skyblockchocolatefactory2.method73().getEpochSecond() - Instant.now().getEpochSecond()) * 1000L;
         if (number17 >= 1000L) {
            list1.add(
               (TextComponent)((TextComponent)Component.text("Time Tower Charge: ").style(style4))
                  .append(Component.text(TimeFormatting.method1(number17)).style(style6))
            );
         }
      }

      if (!skyblockchocolatefactory2.method56().isEmpty()) {
         ChocolateRabbit fishing719 = skyblockchocolatefactory2.method56().get(0);
         if (fishing719.method4() > 0L && fishing719.method5() > 0.0) {
            list1.add((TextComponent)((TextComponent)Component.text("Best Upgrade: ").style(style4)).append(skyblockchocolatefactory2.method21().method4(fishing719.method4())));
         }
      }

      if (skyblockchocolatefactory2.method64() <= SkyblockChocolateFactory.field11.length) {
         TextComponent text20 = skyblockchocolatefactory2.method21().method3(skyblockchocolatefactory2.method60(), skyblockchocolatefactory2.method58() + skyblockchocolatefactory2.method74());
         list1.add((TextComponent)((TextComponent)Component.text("Time To Prestige: ").style(style4)).append(text20));
      }

      return list1;
   }

   private List<TextComponent> method14() {
      SkyblockChocolateFactory skyblockchocolatefactory1 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      Style style2 = TextComponentFactory.styleOf(skyblockchocolatefactory1.method51()).decorate(TextDecoration.BOLD);
      Style style3 = TextComponentFactory.styleOf(skyblockchocolatefactory1.method52());
      Style style4 = TextComponentFactory.styleOf(skyblockchocolatefactory1.method53());
      Style style5 = TextComponentFactory.styleOf(skyblockchocolatefactory1.method54());
      double value6 = 350.5;
      ArrayList list8 = new ArrayList();
      list8.add((TextComponent)Component.text("Chocolate Factory Stats").style(style2));
      list8.add(
         (TextComponent)((TextComponent)Component.text("Current Chocolate: ").style(style3))
            .append(Component.text(NumberUtils.method12(4.5E7)).style(style4))
      );
      list8.add(
         (TextComponent)((TextComponent)Component.text("This Prestige: ").style(style3)).append(Component.text(NumberUtils.method12(1.2E8)).style(style4))
      );
      list8.add((TextComponent)((TextComponent)Component.text("All-time: ").style(style3)).append(Component.text(NumberUtils.method12(2.5E9)).style(style4)));
      list8.add(Component.empty());
      list8.add((TextComponent)((TextComponent)Component.text("Per Second: ").style(style3)).append(Component.text(field8.format(value6)).style(style4)));
      list8.add((TextComponent)((TextComponent)Component.text("Per Minute: ").style(style3)).append(Component.text(field8.format(value6 * 60.0)).style(style4)));
      list8.add((TextComponent)((TextComponent)Component.text("Per Hour: ").style(style3)).append(Component.text(field8.format(value6 * 3600.0)).style(style4)));
      list8.add((TextComponent)((TextComponent)Component.text("Per Day: ").style(style3)).append(Component.text(field8.format(value6 * 86400.0)).style(style4)));
      list8.add(Component.empty());
      list8.add((TextComponent)((TextComponent)Component.text("Multiplier: ").style(style3)).append(Component.text("3.42x").style(style4)));
      list8.add((TextComponent)((TextComponent)Component.text("Barn: ").style(style3)).append(Component.text("345/504").style(style4)));
      list8.add((TextComponent)((TextComponent)Component.text("Hitman: ").style(style3)).append(Component.text("5/28").style(style4)));
      list8.add(
         (TextComponent)((TextComponent)Component.text("Leaderboard Position: ").style(style3))
            .append(Component.text("#" + NumberUtils.method12(842.0)).style(style5))
      );
      list8.add((TextComponent)((TextComponent)Component.text("Time Tower: ").style(style3)).append(Component.text("2/3 Charges").style(style4)));
      list8.add((TextComponent)((TextComponent)Component.text("Time To Prestige: ").style(style3)).append(Component.text("2h 15m").style(style5)));
      return list8;
   }

   private boolean method15() {
      SkyblockMenuType highlighttype1 = this.field9.method7();
      if (highlighttype1 != null && highlighttype1.isChocolateFactoryGui()) {
         SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         return skyblockchocolatefactory2.method41().get() && highlighttype1 == SkyblockMenuType.HOPPITTY_COLLECTION
            ? false
            : !(Boolean)skyblockchocolatefactory2.method42().get() || highlighttype1 != SkyblockMenuType.CHOCOLATE_SHOP;
      } else {
         return false;
      }
   }

   static {
      field8.setMaximumFractionDigits(2);
   }
}
