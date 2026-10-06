package com.moonsworth.lunar.client.mod.combat.totemcounter;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.InventoryPlayerBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.stream.Stream;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class TotemCounterHud extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("iconMode").method4(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPrefix").method4(true))
      .method31();
   private final EnumOption<TotemCounterHud.Type> field10 = (EnumOption<TotemCounterHud.Type>)OptionFactory.method10(
         "totemHudType", TotemCounterHud.Type.COUNT
      )
      .method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("flip").method31();
   public static final ItemStackBridge field12 = Bridge.method8().method38(Bridge.method28().method22("totem_of_undying"));

   protected TotemCounterHud(TotemCounter totemcounter1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method3(totemcounter1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method45(ModTraits.field1, new TotemCounterHud.Data());
   }

   public String getId() {
      return "TOTEM_COUNTER_HUD_CHILD";
   }

   private int method13() {
      if (Ref.method7() == null) {
         return 0;
      }

      InventoryPlayerBridge bridge_241 = Ref.method7().bridge$getInventory();
      return Stream.of(bridge_241.bridge$getMainInventory(), bridge_241.bridge$getArmorInventory(), bridge_241.bridge$getOffhandInventory())
         .flatMap(Collection::stream)
         .filter(arg0 -> arg0 != null && arg0.bridge$getItem() == field12.bridge$getItem())
         .mapToInt(ItemStackBridge::bridge$getStackSize)
         .sum();
   }

   private class Data extends TypedHudRenderer<TextComponent> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.BOTTOM_RIGHT);
      }

      public HudSize method15() {
         return new HudSize(10, 18, 22, 50, 64, 80);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (Ref.method7() != null && (Boolean)TotemCounterHud.this.field8.get()) {
            MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
            float value6 = 24.0F;
            float value7 = 24.0F;
            this.method58(value6, value7);
            if ((Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get()) {
               this.HROIRCHIHORCHCHCRICOOOIOOHIRCH.method11(mixinhelper_45, value2, value3, value6, value7);
            }

            if ((Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get() && (Boolean)this.OIRIICOHRIHHOCCCIICCRRCICOOCHI.get()) {
               this.CRROIIOHCOROIIOROHHCHIRRCORCRH.method11(mixinhelper_45, this, value2, value3, value6, value7, (Float)this.HCHIRRHHICRCCIOOHCOICHHIORICHH.get());
            }

            mixinhelper_45.method44(arg0 -> {
               arg0.method29().method22();
               Bridge.method14().method2();
            });
            mixinhelper_45.push();
            mixinhelper_45.method38(value2 + 4.0F, value3 + 3.5F, 0.0F);
            mixinhelper_45.method34(TotemCounterHud.field12, 0, 0, Ref.method3());
            mixinhelper_45.pop();
            mixinhelper_45.method44(arg0 -> {
               Bridge.method14().method3();
               arg0.method29().method23();
               arg0.method29().method15();
            });
            mixinhelper_45.push();
            mixinhelper_45.method38(0.0F, 0.0F, 500.0F);
            TotemCounter totemcounter8 = (TotemCounter)((ChildModBinding)TotemCounterHud.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
            boolean flag9 = TotemCounterHud.this.field10.get() == TotemCounterHud.Type.POPS;
            int number10 = flag9 ? totemcounter8.field18.getOrDefault(Ref.method7().bridge$getUniqueID(), 0) : TotemCounterHud.this.method13();
            TextComponent text11 = totemcounter8.method11(number10, flag9, false, false, (Boolean)TotemCounterHud.this.field11.get());
            String text12 = TextBridge.getTextContentForRendering(text11);
            float value13 = Ref.method10().bridge$getStringWidth(text12);
            this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR
               .HHRROIIHRRICIIHIIHICRHHRHOHHOO(
                  mixinhelper_45,
                  text12,
                  value2 + value6 - value13 - 2.0F,
                  value3 + value7 - Ref.method10().method19() - 1.0F,
                  (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get(),
                  null
               );
            mixinhelper_45.pop();
         } else {
            super.method3(highlightimpl1, value2, value3, flag4);
         }
      }

      protected void method3(
         MixinHelper_4 mixinhelper_41, TypedHudRenderer<TextComponent> mixincore82, TextComponent text3, float value4, float value5, boolean flag6, boolean flag7, boolean flag8
      ) {
         mixinhelper_41.method10(Ref.method10(), text3, (int)value4, (int)value5, -1, flag7);
      }

      public TextComponent method5(boolean flag1) {
         if (Ref.method7() == null) {
            return Component.text("Totems");
         }

         TotemCounter totemcounter2 = (TotemCounter)((ChildModBinding)TotemCounterHud.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         boolean flag3 = TotemCounterHud.this.field10.get() == TotemCounterHud.Type.POPS;
         int number4 = flag3 ? totemcounter2.field18.getOrDefault(Ref.method7().bridge$getUniqueID(), 0) : TotemCounterHud.this.method13();
         return totemcounter2.method11(number4, flag3, false, (Boolean)TotemCounterHud.this.field9.get(), (Boolean)TotemCounterHud.this.field11.get());
      }

      public void method1(RootSettingsBuilder lightingextension231) {
         lightingextension231.method7(
            new ClientOption[]{((MixinCore9Extension)TotemCounterHud.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1)).method9()}
         );
         lightingextension231.method7(
            SettingsPage.GENERAL,
            arg1x -> {
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{
                     TotemCounterHud.this.field10,
                     TotemCounterHud.this.field8,
                     TotemCounterHud.this.field9,
                     this.ORHRIHRICHICRCOCIIRIOICOIICHOI,
                     TotemCounterHud.this.field11
                  }
               );
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII})
                  .method3(() -> (Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get() || (Boolean)TotemCounterHud.this.field8.get());
               arg1x.method1(
                  this.HIOICORHOCCRCOIHCRIIROIOIOIRIC,
                  arg1xx -> arg1xx.method1(
                     this.OIRIICOHRIHHOCCCIICCRRCICOOCHI,
                     arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.HCHIRRHHICRCCIOOHCOICHHIORICHH})
                  )
               );
            }
         );
         lightingextension231.method7(
            SettingsPage.COLOR,
            arg1x -> {
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.OOOCCCRICCHOORCCRHHRHHCOOCORRC})
                  .method1(new ClientOption[]{this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII});
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.HROIRCHIHORCHCHCRICOOOIOOHIRCH})
                  .method1(new ClientOption[]{this.HIOICORHOCCRCOIHCRIIROIOIOIRIC});
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.CRROIIOHCOROIIOROHHCHIRRCORCRH})
                  .method1(new ClientOption[]{this.OIRIICOHRIHHOCCCIICCRRCICOOCHI});
            }
         );
      }
   }

   private enum Type implements OptionEnumValue {
      POPS("pops"),
      COUNT("count");

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
