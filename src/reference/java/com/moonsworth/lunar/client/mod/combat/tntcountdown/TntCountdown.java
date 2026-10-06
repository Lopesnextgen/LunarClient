package com.moonsworth.lunar.client.mod.combat.tntcountdown;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityTNTPrimedBridge;
import com.moonsworth.lunar.bridge.SulfurCubeTntBridge;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.mod.render.serverholograms.Serverholograms;
import com.moonsworth.lunar.client.event.entity.EventEntityJoinWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.net.ServerUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextColor;

public class TntCountdown extends AbstractFeature {
   private static final DecimalFormat field8 = new DecimalFormat("0.00");
   private final Map<String, EntityTNTPrimedBridge> field9 = new HashMap<>();
   private final Map<String, SulfurCubeTntBridge> field10 = new HashMap<>();
   private final TextOption field11 = (TextOption)OptionFactory.method12("textPrefix").method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("textShadow").method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("staticCountdownColor").method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field15 = (ColorOption)((Data)OptionFactory.method8("color").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ColorOption field16 = (ColorOption)((Data)OptionFactory.method8("prefixColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final IntegerOption field17 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "adjustFuseTime"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .method7(-80, 80))
      .method31();
   private final EnumOption<TntCountdown.Type> field18 = (EnumOption<TntCountdown.Type>)OptionFactory.method10(
         "adjustForBedwars", TntCountdown.Type.HYPIXEL_ONLY
      )
      .method31();
   private int field19 = 80;
   private int field20 = 0;
   private boolean field21;

   public TntCountdown() {
      super(false);
      this.handle(EventEntityJoinWorld.class, this::method1);
      this.handle(EventServerChange.class, this::method2);
      this.handle(EventTick.class, this::method3);
   }

   public String getId() {
      return "TNT_COUNTDOWN";
   }

   private void method1(EventEntityJoinWorld highlightimpl201) {
      if (highlightimpl201.field1.equals(Ref.method7())) {
         this.cleanup();
      }

      if (highlightimpl201.field1 instanceof EntityTNTPrimedBridge) {
         ((EntityTNTPrimedBridge)highlightimpl201.field1).bridge$setMaximumFuse(this.field19);
         ((EntityTNTPrimedBridge)highlightimpl201.field1).method1(this.field19);
         Serverholograms serverholograms3 = this.method10((EntityTNTPrimedBridge)highlightimpl201.field1, UUID.randomUUID().toString(), 0.0F);
         this.field9.put(serverholograms3.getId(), (EntityTNTPrimedBridge)highlightimpl201.field1);
         Ref.method4().method57().method3().put(serverholograms3.getId(), serverholograms3);
      } else if (Ref.MC_VERSION >= 39 && highlightimpl201.field1 instanceof SulfurCubeTntBridge bridgeextension_112) {
         this.method4(bridgeextension_112);
      }
   }

   private void method2(EventServerChange highlightimpl101) {
      this.cleanup();
   }

   private void method3(EventTick highlightimpl21) {
      ArrayList list2 = new ArrayList();
      this.field9.forEach((arg2x, arg3) -> {
         int number4x = arg3.bridge$getFuse();
         int number5 = Math.max(1, arg3.bridge$getMaximumFuse() + this.field20);
         if (number4x > 0 && !arg3.bridge$isRemoved() && number4x <= number5) {
            Serverholograms serverholograms6 = this.method10(arg3, arg2x, 0.0F);
            Ref.method4().method57().method3().put(arg2x, serverholograms6);
         } else {
            list2.add(arg2x);
         }
      });

      for (String text4 : list2) {
         this.field9.remove(text4);
         Ref.method4().method57().method3().remove(text4);
      }

      if (Ref.MC_VERSION >= 39) {
         this.method13();
      }
   }

   private void method4(SulfurCubeTntBridge bridgeextension_111) {
      this.field10.put("sulfur-cube-tnt-" + bridgeextension_111.bridge$getUniqueID(), bridgeextension_111);
   }

   private void method13() {
      if (this.field21 && Ref.method8() != null) {
         for (BridgeExtension bridgeextension2 : Ref.method8().bridge$getEntities()) {
            if (bridgeextension2 instanceof SulfurCubeTntBridge bridgeextension_113) {
               this.method4(bridgeextension_113);
            }
         }

         this.field21 = false;
      }

      Map map4 = Ref.method4().method57().method3();
      this.field10.entrySet().removeIf(arg2x -> {
         String text3x = arg2x.getKey();
         SulfurCubeTntBridge bridgeextension_114x = arg2x.getValue();
         if (bridgeextension_114x.bridge$isRemoved()) {
            map4.remove(text3x);
            return true;
         }

         int number5 = bridgeextension_114x.bridge$getFuse();
         int number6 = bridgeextension_114x.bridge$getMaximumFuse();
         if (number5 > 0 && number5 <= Math.max(1, number6 + this.field20)) {
            map4.put(text3x, this.method11(bridgeextension_114x, text3x, 0.0F, number5, number6));
         } else {
            map4.remove(text3x);
         }

         return false;
      });
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.cleanup();
      }

      this.field21 = flag1;
   }

   public void method7(int number1) {
      this.field19 = Math.max(1, number1);
   }

   public void method8(UUID uuid1, int number2) {
      for (EntityTNTPrimedBridge bridgeextension44 : this.field9.values()) {
         if (bridgeextension44.bridge$getUniqueID().equals(uuid1)) {
            bridgeextension44.bridge$setMaximumFuse(number2);
            bridgeextension44.method1(number2);
            return;
         }
      }
   }

   public void method9(int number1, int number2) {
      for (EntityTNTPrimedBridge bridgeextension44 : this.field9.values()) {
         if (bridgeextension44.bridge$getEntityId() == number1) {
            bridgeextension44.bridge$setMaximumFuse(number2);
            bridgeextension44.method1(number2);
            return;
         }
      }
   }

   private void cleanup() {
      this.field19 = 80;
      if (this.field9 != null) {
         for (String text2 : this.field9.keySet()) {
            Ref.method4().method57().method3().remove(text2);
         }

         for (String text4 : this.field10.keySet()) {
            Ref.method4().method57().method3().remove(text4);
         }

         this.field9.clear();
         this.field10.clear();
         this.field21 = true;
      }
   }

   private Serverholograms method10(EntityTNTPrimedBridge bridgeextension41, String text2, float value3) {
      int number4 = bridgeextension41.bridge$getFuse();
      if (Ref.MC_VERSION == 1 && this.field18.get() != TntCountdown.Type.NEVER) {
         String text5 = ServerUtils.getServer();
         if (text5 != null
            && text5.contains("Bed Wars")
            && (this.field18.get() == TntCountdown.Type.ALWAYS || ServerBrandWatcher.method8(KeystrokesType.HYPIXEL))) {
            number4 -= 30;
         }
      }

      return this.method11(bridgeextension41, text2, value3, number4, bridgeextension41.bridge$getMaximumFuse());
   }

   private Serverholograms method11(BridgeExtension bridgeextension1, String text2, float value3, int number4, int number5) {
      return new Serverholograms(
         text2,
         this.method12(Math.max(1.0E-4F, number4), value3, number5),
         bridgeextension1.bridge$getPosX(),
         bridgeextension1.bridge$getPosY() + 0.5,
         bridgeextension1.bridge$getPosZ(),
         false,
         (Boolean)this.field12.get(),
         (Boolean)this.field14.get(),
         false
      );
   }

   private Component[] method12(float value1, float value2, int number3) {
      float value4 = (value1 - value2) / 20.0F;
      int number5 = Math.max(1, number3 + this.field20);
      float value6 = Math.min(value1 / number5, 1.0F);
      int number7 = this.field13.get()
         ? this.field15.method14(value1)
         : 0xFF000000 | ((int)((1.0F - value6) * 255.0F) & 0xFF) << 16 | ((int)(value6 * 255.0F) & 0xFF) << 8 | 0;
      return new TextComponent[]{
         (TextComponent)((Builder)((Builder)Component.text().color(TextColor.color(this.field16.method14(value1))))
               .content((String)this.field11.get())
               .append(((Builder)Component.text().color(TextColor.color(number7))).content(field8.format(value4))))
            .build()
      };
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field17, this.field18})
      );
      lightingextension231.method1(
         "extraRenderOptions", arg1x -> arg1x.method9(new ClientOption[]{this.field14, this.field11, this.field12})
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, arg1xx -> arg1xx.method9(new ClientOption[]{this.field15}));
         arg1x.method9(new ClientOption[]{this.field16});
      });
      this.field17.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.field20 = arg1x);
   }

   private enum Type implements OptionEnumValue {
      NEVER("never"),
      HYPIXEL_ONLY("hypixelOnly"),
      ALWAYS("always");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
