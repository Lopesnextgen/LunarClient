package com.moonsworth.lunar.client.mod.skyblock.burrowsharing;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin.BurrowType;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockBurrowSharing extends AbstractFeature {
   private final HypixelLocationListener field8 = (HypixelLocationListener)this.method63(HypixelLocationListener.class);
   private static final Pattern field9 = Pattern.compile("^[A-Za-z ]+! You dug out (a )?(?<mob>[A-Za-z ]+)!$");
   private final MultiSelectOption field10 = (MultiSelectOption)((Data)OptionFactory.method27("skyblockBurrowSharing")
         .method2(BurrowType.getEnabledByDefault()))
      .method3(BurrowType.names())
      .method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("burrowSharingAllChat").method31();

   public SkyblockBurrowSharing(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> this.field8.method9() == SkyblockIsland.HUB));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10, this.field11});
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      Matcher matcher2 = field9.matcher(data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC());
      if (matcher2.matches()) {
         String text3 = matcher2.group("mob");
         if (this.field10.contains(text3)) {
            Bridge5Extension_5 bridge5extension_54 = Ref.method7();
            if (bridge5extension_54 == null) {
               return;
            }

            String text5 = this.field11.get() ? "/ac " : "/pc ";
            String text6 = "x: " + bridge5extension_54.bridge$getBlockX() + ", y: " + bridge5extension_54.bridge$getBlockY() + ", z: " + bridge5extension_54.bridge$getBlockZ() + " | " + text3;
            ChatMessageQueue.method1(text5 + text6);
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_BURROW_SHARING";
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"Diana", "Griffin", "Burrow"})
         .method11(this);
   }
}
