package com.moonsworth.lunar.client.mod.hud.keystrokes;

import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeKey;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Locale;
import lombok.Generated;
import org.intellij.lang.annotations.Subst;

public class CustomKeystrokeKey extends KeystrokeKey {
   private final SimpleKeybindOption customKey = (SimpleKeybindOption)OptionFactory.method17("customKey").method11().method31();
   private final TextOption customText = (TextOption)OptionFactory.method12("customText").method31();
   @Subst("KEY_ID")
   private final int keyIndex;

   public CustomKeystrokeKey(Framework7Extension framework7extension1, int number2) {
      super(framework7extension1, false);
      this.keyIndex = number2;
   }

   protected ModDetails createDetails() {
      return ModDetails.method7().method4(this::getDisplayText).method11(this);
   }

   public String getId() {
      return "KEYSTROKE_KEY_" + this.keyIndex + "_CUSTOM";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.customKey});
      lightingextension231.method9(new ClientOption[]{this.customText});
      super.registerOptions(lightingextension231);
      lightingextension231.method9(
         new OptionProvider[]{
            OptionFactory.method14("remove")
               .method4(() -> ((Keystrokes)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method3(this))
         }
      );
   }

   protected String getDisplayText() {
      String text1 = (String)this.customText.get();
      if (text1 != null && !text1.trim().isEmpty()) {
         return text1;
      }

      KeyBindingBridge mixinhelper_152 = this.customKey.method19();
      return mixinhelper_152 == null ? "NONE" : mixinhelper_152.bridge$getKeyName().toUpperCase(Locale.ROOT);
   }

   protected String getStateKey() {
      return "custom:" + this.keyIndex;
   }

   protected KeyBindingBridge getKeyBinding() {
      return this.customKey.method19();
   }

   protected int getCps() {
      return 0;
   }

   protected void updateSize() {
      float value1 = (Float)this.CCORCRRICRIIHROIIOIRHIHRRHIICH.get();
      float value2 = Ref.method10().bridge$getStringWidth(this.getDisplayText());
      float value3;
      if (value2 + 4.0F < value1) {
         value3 = value1;
      } else {
         value3 = value2 + value1;
      }

      ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(value3, value1);
   }

   @Generated
   public int getKeyIndex() {
      return this.keyIndex;
   }
}
