package com.moonsworth.lunar.client.mod.skyblock.autocopyraredrops;

import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.util.regex.Pattern;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockAutoCopyRareDrops extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^((((VERY|CRAZY) )?RARE)|INSANE) DROP!.*$");

   public SkyblockAutoCopyRareDrops(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.CHAT));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(TypedChatMessage.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_AUTO_COPY_RARE_DROPS";
   }

   private void method1(TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if (text2.startsWith("PET DROP!") || field8.matcher(text2).matches()) {
         ClipboardUtils.method2(text2);
         Ref.method4().method69().method2(NotificationManager.method15("copiedMessage", new Object[0]), text2);
         SkyBlockChat.method1("Copied rare drop to clipboard.");
      }
   }
}
