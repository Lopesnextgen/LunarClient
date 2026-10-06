package com.moonsworth.lunar.client.mod.skyblock.visitoralert;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockScoreboardParser;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockVisitorAlert extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private final SkyblockScoreboardParser field9 = (SkyblockScoreboardParser)this.method63(SkyblockScoreboardParser.class);
   private static final String field10 = "MAX_VISITORS";
   private final FloatOption field11 = (FloatOption)((Data)((Data)OptionFactory.method2("maxVisitorAlertDuration")
            .method4(5.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("persistentVisitorAlert").method31();
   private final Pattern field13 = Pattern.compile("^ Next Visitor: (?<time>.+)$");
   private final TextComponent field14 = Component.text("Max Visitors", NamedTextColor.GREEN);
   private boolean field15;

   public SkyblockVisitorAlert(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTabListUpdate.class, this::method1);
   }

   private void method1(EventTabListUpdate highlightimpl31) {
      if (IslandUtils.getIsland() != SkyblockIsland.GARDEN) {
         this.field15 = false;
      } else {
         ScoreboardSection lotusfish32 = (ScoreboardSection)this.field9.method6().get("visitors");
         if (lotusfish32 != null) {
            for (String text4 : lotusfish32.method3()) {
               Matcher matcher5 = this.field13.matcher(text4);
               if (matcher5.find()) {
                  if (!matcher5.group("time").equals("Queue Full!")) {
                     this.field15 = false;
                     this.field8.method3("MAX_VISITORS");
                     return;
                  }

                  if (!this.field15 || (Boolean)this.field12.get()) {
                     this.field8
                        .method2(
                           ComparableImpl.method2()
                              .method1("MAX_VISITORS")
                              .method2(this.field14)
                              .method3(this.field12.get() ? 5000L : ((Float)this.field11.get()).longValue() * 1000L)
                              .method6()
                        );
                  }

                  this.field15 = true;
                  return;
               }
            }

            this.field15 = false;
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_VISITOR_ALERT";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field12});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field11})).method2(this.field12::get);
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
