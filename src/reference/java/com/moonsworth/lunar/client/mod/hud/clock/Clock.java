package com.moonsworth.lunar.client.mod.hud.clock;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

public class Clock extends AbstractFeature {
   private final SimpleDateFormat field8 = new SimpleDateFormat("h:mm");
   private final SimpleDateFormat field9 = new SimpleDateFormat("h:mm a");
   private final SimpleDateFormat field10 = new SimpleDateFormat("HH:mm");
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("militaryTime").method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showAmPm").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private String field13;
   private Date field14;

   public Clock() {
      super(false);
      this.method2(
         ModTraits.field1,
         TypedHudRenderer.method22(0.0F, 0.0F, HudAnchor.TOP_RIGHT, HudSize.method1(10, 18, 22, 46, 56, 62), arg1 -> this.field13 == null ? "" : this.field13)
      );
      this.handle(EventSecond.class, this::method1);
   }

   public String getId() {
      return "CLOCK";
   }

   private void method1(@Nullable EventSecond highlightimpl41) {
      if (this.field14 == null) {
         this.field14 = new Date();
      }

      this.field14.setTime((Long)this.method1("time", System.currentTimeMillis()));
      if ((Boolean)this.field11.get()) {
         this.field13 = this.field10.format(this.field14);
      } else if ((Boolean)this.field12.get()) {
         this.field13 = this.field9.format(this.field14);
      } else {
         this.field13 = this.field8.format(this.field14);
      }
   }

   protected String method18() {
      return "[09:45 AM]";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.SETTINGS, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field11});
         arg1x.method9(new ClientOption[]{this.field12}).method3(this.field11::get);
      });
      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method1(null));
   }
}
