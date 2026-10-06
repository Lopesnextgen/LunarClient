package com.moonsworth.lunar.client.mod.combat.totemcounter;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.render.EventRenderTabListEntry;
import com.moonsworth.lunar.client.event.combat.EventTotemPop;
import com.moonsworth.lunar.client.event.player.EventLocalPlayerDeath;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public class TotemCounter extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showNametag").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTabList").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("separateNametag").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("renderFirst").method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("flip").method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("showPrefix").method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showForSelf").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("countColor").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field16 = (ColorOption)((Data)OptionFactory.method8("prefixColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final SimpleKeybindOption field17 = (SimpleKeybindOption)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)OptionFactory.method17(
            "resetCountersKeybind"
         )
         .method18(this))
      .method31();
   protected final Map<UUID, Integer> field18 = new HashMap<>();

   public TotemCounter() {
      super(false);
      this.method11(ModTraits.field18, arg0 -> arg0.method11(Config.field5));
      this.handle(EventTotemPop.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method6);
      this.handle(EventDisconnect.class, this::method7);
      this.handle(EventLocalPlayerDeath.class, this::method8);
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag.class, this::method9);
      this.handle(EventRenderTabListEntry.class, this::method10);
   }

   public String getId() {
      return "TOTEM_COUNTER";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method3(new String[]{"uku3lig"}).method11(this);
   }

   protected List<Framework7Extension> method9() {
      return List.of(new TotemCounterHud(this));
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field14, this.field9, this.field12, this.field15});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field8, arg1xx -> {
            arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field13, this.field10});
            arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11}).method3(() -> !(Boolean)this.field10.get());
            arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16}).method3(() -> !(Boolean)this.field13.get());
         });
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field17.method3(this::method13)});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionProvider[]{OptionFactory.method14("resetCounters").method4(() -> {
            this.field18.clear();
            if (Ref.method7() != null) {
               Ref.method7().bridge$addChatMessage(TextBridge.asBridge(Component.text("[Totem Counter] Successfully reset all totem pops.")));
            }
         })});
      });
   }

   private void method13() {
      this.field18.clear();
      if (Ref.method7() != null) {
         Ref.method7().bridge$addChatMessage(TextBridge.asBridge(Component.text("[Totem Counter] Successfully reset all totem pops.")));
      }
   }

   private void method5(EventTotemPop highlightimpl221) {
      UUID uuid2 = highlightimpl221.method1().bridge$getUniqueID();
      this.field18.put(uuid2, this.field18.getOrDefault(uuid2, 0) + 1);
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = ChatFormatting.getTextWithoutFormattingCodes(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
      if (text2.contains("Winners:") || text2.contains("has won the round.") || text2.contains("has won the game!") || text2.contains("Winner: NONE!")) {
         this.field18.clear();
      }
   }

   private void method7(EventDisconnect highlightimpl111) {
      this.field18.clear();
   }

   private void method8(EventLocalPlayerDeath highlightimpl61) {
      this.field18.clear();
   }

   private void method9(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag highlightimpl111) {
      if (!highlightimpl111.isCancelled() && !highlightimpl111.getLines().isEmpty() && (Boolean)this.field8.get()) {
         if (highlightimpl111.method2() instanceof EntityPlayerBridge bridgeextension2222) {
            UUID uuid8 = bridgeextension2222.bridge$getUniqueID();
            if (!(Boolean)this.field14.get() && uuid8.equals(Ref.method7().bridge$getUniqueID())) {
               return;
            }

            if (!this.field18.containsKey(uuid8)) {
               return;
            }

            int number4 = this.field18.getOrDefault(uuid8, 0);
            TextComponent text5 = this.method11(number4, true, !(Boolean)this.field10.get(), (Boolean)this.field13.get(), (Boolean)this.field12.get());
            if ((Boolean)this.field10.get()) {
               if ((Boolean)this.field11.get()) {
                  highlightimpl111.getLines().add(0, text5);
               } else {
                  highlightimpl111.getLines().add(text5);
               }
            } else {
               Component component6 = highlightimpl111.method3();
               if (component6 == null) {
                  return;
               }

               TextComponent text7 = Component.text("");
               Component component9;
               if ((Boolean)this.field12.get()) {
                  component9 = text7.append(component6).append(text5);
               } else {
                  component9 = text7.append(text5).append(component6);
               }

               highlightimpl111.method1(component9);
            }
         }
      }
   }

   private void method10(EventRenderTabListEntry highlightimpl41) {
      if ((Boolean)this.field9.get()) {
         UUID uuid2 = highlightimpl41.method2().bridge$getUniqueId();
         if ((Boolean)this.field14.get() || !uuid2.equals(Ref.method7().bridge$getUniqueID())) {
            if (this.field18.containsKey(uuid2)) {
               int number3 = this.field18.getOrDefault(uuid2, 0);
               TextComponent text4 = this.method11(number3, true, true, false, (Boolean)this.field12.get());
               Component component5 = highlightimpl41.getComponent();
               TextComponent text6 = Component.text("");
               Component component7;
               if ((Boolean)this.field12.get()) {
                  component7 = text6.append(component5).append(text4);
               } else {
                  component7 = text6.append(text4).append(component5);
               }

               highlightimpl41.method1(component7);
            }
         }
      }
   }

   @NotNull
   protected TextComponent method11(int number1, boolean flag2, boolean flag3, boolean flag4, boolean flag5) {
      TextComponent text6 = (TextComponent)Component.text((number1 != 0 && flag2 ? "-" : "") + number1).color(TextColor.color(this.method12(number1, flag2)));
      if (flag4) {
         String text7 = number1 != 0 && number1 <= 1 && flag5 ? "Totem" : "Totems";
         TextComponent text8 = (TextComponent)Component.text(flag5 ? " " + text7 : text7 + ": ").color(TextColor.color(this.field16.method13() & 16777215));
         if (flag5) {
            text6 = (TextComponent)text6.append(text8);
         } else {
            text6 = (TextComponent)text8.append(text6);
         }
      }

      if (!flag3) {
         return text6;
      }

      TextComponent text9 = (TextComponent)Component.text(" | ").color(TextColor.color(11184810));
      return flag5 ? (TextComponent)text9.append(text6) : (TextComponent)text6.append(text9);
   }

   private int method12(int number1, boolean flag2) {
      if (!(Boolean)this.field15.get()) {
         return 16777215;
      }

      if (flag2) {
         return switch (number1) {
            case 0, 1, 2 -> ChatFormatting.GREEN.getAdventureColor().value();
            case 3, 4 -> ChatFormatting.DARK_GREEN.getAdventureColor().value();
            case 5, 6 -> ChatFormatting.YELLOW.getAdventureColor().value();
            case 7, 8 -> ChatFormatting.GOLD.getAdventureColor().value();
            default -> ChatFormatting.RED.getAdventureColor().value();
         };
      } else {
         return switch (number1) {
            case 0, 1, 2 -> ChatFormatting.RED.getAdventureColor().value();
            case 3, 4 -> ChatFormatting.GOLD.getAdventureColor().value();
            case 5, 6 -> ChatFormatting.YELLOW.getAdventureColor().value();
            case 7, 8 -> ChatFormatting.DARK_GREEN.getAdventureColor().value();
            default -> ChatFormatting.GREEN.getAdventureColor().value();
         };
      }
   }
}
