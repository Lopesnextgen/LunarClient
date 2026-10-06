package com.moonsworth.lunar.client.mod.skyblock.sphinxhelper;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSphinxHelper extends AbstractFeature {
   private final HypixelLocationListener field8 = (HypixelLocationListener)this.method63(HypixelLocationListener.class);
   private static final Pattern field9 = Pattern.compile("^\\s+(?<letter>[ABC])\\) (?<answer>.+)$");
   private final ModifierKeybindOption field10 = (ModifierKeybindOption)((Data)OptionFactory.method18("answerSphinxQuestionKeybind")
         .method18(this))
      .method11()
      .method31();
   private boolean field11;
   private String field12;
   private String field13;

   public SkyblockSphinxHelper(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> this.field8.method9() == SkyblockIsland.HUB));
      this.method50(this::reset);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.handle(EventWorldChange.class, arg1x -> this.reset());
      this.field10.method3(() -> {
         if (this.field13 != null) {
            ChatMessageQueue.method1("/" + this.field13);
            this.reset();
         }
      });
   }

   public String getId() {
      return "SKYBLOCK_SPHINX_HELPER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10});
   }

   private void reset() {
      this.field11 = false;
      this.field12 = null;
      this.field13 = null;
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.HUB) {
         String text2 = data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
         if (text2.equals("Question")) {
            this.field11 = true;
         } else if (this.field11) {
            this.field12 = (String)((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method30().get(text2);
            this.field11 = false;
         } else {
            if (this.field12 != null) {
               Matcher matcher3 = field9.matcher(text2);
               if (matcher3.matches() && matcher3.group("answer").equals(this.field12)) {
                  String text4 = this.field10.method8() == KeyCode.KEY_NONE
                     ? this.method14("correctAnswer", new Object[0])
                     : this.method14("pressToAnswer", new Object[]{this.field10.method17()});
                  data1.OHROCHICOIOICHOCRROORRCIIICIHO(SkyBlockChat.method2(data1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI(), text4));
                  this.field12 = null;

                  this.field13 = switch (matcher3.group("letter")) {
                     case "A" -> "sphinxanswer 0";
                     case "B" -> "sphinxanswer 1";
                     case "C" -> "sphinxanswer 2";
                     default -> null;
                  };
               }
            }
         }
      }
   }
}
