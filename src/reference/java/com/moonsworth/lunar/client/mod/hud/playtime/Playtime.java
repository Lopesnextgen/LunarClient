package com.moonsworth.lunar.client.mod.hud.playtime;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.util.text.TimeFormatting.TimeFormat;

public class Playtime extends AbstractFeature {
   private final EnumOption<TimeFormat> field8 = (EnumOption<TimeFormat>)OptionFactory.method10("timeDisplayOption", TimeFormat.DEFAULT)
      .method31();

   public Playtime() {
      super(false);
      this.method2(
         ModTraits.field1,
         TypedHudRenderer.method23(
            0.0F,
            0.0F,
            HudAnchor.TOP_LEFT,
            HudSize.method1(10, 18, 22, 60, 80, 120),
            arg1 -> ((TimeFormat)this.field8.get())
               .format((Long)this.method1("time", System.currentTimeMillis() - LunarConstants.field5)),
            HudConditionSet.method5().method1(false).method8()
         )
      );
   }

   public String getId() {
      return "PLAYTIME";
   }

   protected String method18() {
      return "12:34:56";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.SETTINGS, arg1x -> arg1x.method9(new ClientOption[]{this.field8}));
   }
}
