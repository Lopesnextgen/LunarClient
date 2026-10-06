package com.moonsworth.lunar.client.mod.skyblock.visitorhud;

import com.google.common.collect.UnmodifiableIterator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.listener.TabListListener;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockVisitorHud extends AbstractFeature {
   private final TabListListener tabListListener = (TabListListener)this.method63(TabListListener.class);
   private static final Pattern VISITORS_PATTERN = Pattern.compile("^Visitors: \\((\\d)\\)$");
   private static final Pattern NEXT_VISITOR_PATTERN = Pattern.compile("^ Next Visitor: (.+)");
   private static final ResourceLocationBridge JERRY_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/jerry.png");
   private int visitorCount = 0;
   private String nextVisitor = "";

   public SkyblockVisitorHud(Skyblock skyblock1) {
      super(true);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockVisitorHud.Data()));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTabListUpdate.class, this::method1);
   }

   private void method1(EventTabListUpdate highlightimpl31) {
      if (IslandUtils.getIsland() == SkyblockIsland.GARDEN) {
         boolean flag2 = false;
         boolean flag3 = false;
         UnmodifiableIterator unmodifiableiterator4 = this.tabListListener.method6().iterator();

         while (unmodifiableiterator4.hasNext()) {
            String text5 = (String)unmodifiableiterator4.next();
            Matcher matcher6 = field9.matcher(text5);
            if (matcher6.find()) {
               this.visitorCount = Integer.parseInt(matcher6.group(1));
               flag2 = true;
            } else {
               matcher6 = field10.matcher(text5);
               if (matcher6.find()) {
                  this.nextVisitor = matcher6.group(1);
                  flag3 = true;
               }
            }
         }

         if (!flag2) {
            this.visitorCount = -1;
         }

         if (!flag3) {
            this.nextVisitor = "Unknown";
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_VISITOR_HUD";
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(20, 30, 60, 20, 100, 200);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            return this.buildLines(3, "12m 30s");
         } else {
            return IslandUtils.getIsland() != SkyblockIsland.GARDEN ? null : this.buildLines(SkyblockVisitorHud.this.visitorCount, SkyblockVisitorHud.this.nextVisitor);
         }
      }

      private List<HudLine> buildLines(int number1, String text2) {
         String text3 = number1 < 0 ? "Unknown" : String.valueOf(number1);
         ArrayList list4 = new ArrayList();
         list4.add(
            new HudLine(
               SkyblockVisitorHud.JERRY_TEXTURE,
               TextComponentFactory.builder()
                  .method2("Visitors")
                  .method4(text3)
                  .method5(NamedTextColor.GOLD)
                  .method7(text3.equals("0") ? NamedTextColor.GRAY : NamedTextColor.AQUA)
                  .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                  .build()
            )
         );
         if (text2.equals("Queue Full!")) {
            TextComponent text5 = Component.text(text2, NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD});
            if (this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get()) {
               text5 = TextComponentFactory.builder().method2("Next").method3(text5).method5(NamedTextColor.GOLD).method9(NamedTextColor.WHITE).build();
            }

            list4.add(new HudLine(Bridge.method28().method42(), text5));
         } else {
            list4.add(
               new HudLine(
                  Bridge.method28().method20(),
                  TextComponentFactory.builder()
                     .method2("Next")
                     .method4(text2)
                     .method5(NamedTextColor.GOLD)
                     .method7(NamedTextColor.AQUA)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         return list4;
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
