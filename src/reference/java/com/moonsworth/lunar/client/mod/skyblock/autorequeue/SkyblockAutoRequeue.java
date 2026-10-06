package com.moonsworth.lunar.client.mod.skyblock.autorequeue;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.regex.Pattern;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockAutoRequeue extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^ {6}Click HERE to re-queue into .*!$");
   private static final Pattern field9 = Pattern.compile("^Party > .*: !(dt|downtime).*$");
   private static final Pattern field10 = Pattern.compile("^.*(?: has)? (left|been removed from) the party\\.$");
   private static final Pattern field11 = Pattern.compile("^The party was transferred to .* because .* left$");
   private static final Pattern field12 = Pattern.compile("^You have been kicked from the party by .*$");
   private static final String field13 = "The party was disbanded because all invites expired and the party was empty.";
   private final IntegerOption field14 = (IntegerOption)((Data)((Data)OptionFactory.method4("requeueWaitTime").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(10))
         .method7(1, 30))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("requeueDungeon").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("requeueKuudra").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("pauseOnPartyLeave").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("pauseOnDowntime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private boolean field19 = true;
   private int field20 = -1;

   public SkyblockAutoRequeue(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method1);
      this.handle(EventTick.class, this::method2);
      this.handle(EventWorldChange.class, this::method3);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (IslandUtils.isOnIsland()) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         if (this.field19 && (Boolean)this.field17.get() && this.method4(text2)) {
            this.field19 = false;
            this.field20 = -1;
         } else if (this.field19 && (Boolean)this.field18.get() && field9.matcher(text2).matches()) {
            this.field19 = false;
            this.field20 = -1;
         } else {
            if (this.field19 && this.method13() && field8.matcher(text2).matches()) {
               this.field20 = (Integer)this.field14.get() * 20;
            }
         }
      }
   }

   private void method2(EventTick highlightimpl21) {
      if (IslandUtils.isOnIsland()) {
         if (this.field19) {
            if (this.field20 > 0) {
               if (--this.field20 <= 0) {
                  ChatMessageQueue.method1("/instancerequeue");
               }
            }
         }
      }
   }

   private void method3(EventWorldChange data31) {
      this.field19 = true;
      this.field20 = -1;
   }

   private boolean method4(String text1) {
      if ("The party was disbanded because all invites expired and the party was empty.".equals(text1)) {
         return true;
      } else if (field10.matcher(text1).matches()) {
         return true;
      } else {
         return field11.matcher(text1).matches() ? true : field12.matcher(text1).matches();
      }
   }

   private boolean method13() {
      SkyblockIsland gui2extension31 = IslandUtils.getIsland();
      return (Boolean)this.field15.get() && gui2extension31 == SkyblockIsland.DUNGEON || (Boolean)this.field16.get() && gui2extension31 == SkyblockIsland.KUUDRA;
   }

   public String getId() {
      return "SKYBLOCK_AUTO_REQUEUE";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field14, this.field15, this.field16, this.field17, this.field18});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
