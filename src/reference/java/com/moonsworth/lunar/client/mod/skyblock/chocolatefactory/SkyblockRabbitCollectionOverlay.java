package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.RabbitCollection;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.NameplateComponent;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.mod.skyblock.chocolatefactory.SkyblockChocolateFactory;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.joml.Vector4f;

public class SkyblockRabbitCollectionOverlay extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ProfileIdListener field9 = (ProfileIdListener)this.method63(ProfileIdListener.class);
   private static final Pattern field10 = Pattern.compile("^HOPPITY'S HUNT You found (?<name>.+) \\((?<rarity>[A-Z]+)\\)!$");
   private Vector4f field11;
   private boolean field12 = false;
   private RabbitCollection field13;

   public SkyblockRabbitCollectionOverlay(SkyblockChocolateFactory skyblockchocolatefactory1, ToggleOption lightingextension4432) {
      super(true);
      this.method4(ModTraits.field16, ChildModBinding.method4(false, skyblockchocolatefactory1));
      this.method4(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method4(ModTraits.field1, HudVisibilityWrapper.method4(new NameplateComponent(this, this::method13)));
      this.handle(EventRenderContainerSlotPost.class, arg1x -> {
         this.method2(arg1x);
         this.method8(arg1x);
      });
      this.handle(EventSlotUpdate.class, this::method5);
      this.handle(TypedChatMessage.class, this::method6);
      this.handle(EventMarkerInput.class, this::method7);
   }

   public String getId() {
      return "SKYBLOCK_RABBIT_COLLECTION_OVERLAY";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventRenderContainerSlotPost data31) {
      this.field11 = null;
      if (this.field8.method7() == SkyblockMenuType.HOPPITTY_COLLECTION) {
         SkyblockChocolateFactory skyblockchocolatefactory2 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         Style style3 = TextComponentFactory.styleOf(skyblockchocolatefactory2.method51()).decorate(TextDecoration.BOLD);
         Style style4 = TextComponentFactory.styleOf(skyblockchocolatefactory2.method53());
         List list5 = skyblockchocolatefactory2.method55().method2(style3, style4, skyblockchocolatefactory2.method65(), skyblockchocolatefactory2.method69());
         float value6 = 0.0F;

         for (TextComponent text8 : list5) {
            float value9 = Ref.method10().bridge$getStringWidth(text8);
            if (value9 > value6) {
               value6 = value9;
            }
         }

         float value14 = Ref.method10().method19() * 1.5F;
         float value15 = list5.size() * value14;
         MixinCore9Extension mixincore9extension16 = (MixinCore9Extension)this.method7(ModTraits.field1);
         mixincore9extension16.method16(value6, value15);
         MixinHelper_4 mixinhelper_410 = data31.method5();
         float value11 = WorldRenderUtils.prepareComponentScale(mixinhelper_410, mixincore9extension16);

         for (TextComponent text13 : list5) {
            mixinhelper_410.method10(Ref.method10(), text13, 0, 0, -1, true);
            mixinhelper_410.method39(0.0F, value14);
         }

         mixinhelper_410.pop();
         float value17 = mixincore9extension16.method1() * value11;
         float value18 = mixincore9extension16.method2() * value11;
         this.field11 = new Vector4f(value17, value17 + value6 * value11, value18, value18 + value15 * value11);
      }
   }

   private List<TextComponent> method13() {
      if (this.field13 == null) {
         this.field13 = new RabbitCollection();
         this.method4(this.field13, ItemRarity.COMMON, 189, 181);
         this.method4(this.field13, ItemRarity.UNCOMMON, 97, 88);
         this.method4(this.field13, ItemRarity.RARE, 61, 47);
         this.method4(this.field13, ItemRarity.EPIC, 24, 15);
         this.method4(this.field13, ItemRarity.LEGENDARY, 17, 9);
         this.method4(this.field13, ItemRarity.MYTHIC, 7, 3);
         this.method4(this.field13, ItemRarity.DIVINE, 5, 2);
      }

      SkyblockChocolateFactory skyblockchocolatefactory1 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      Style style2 = TextComponentFactory.styleOf(skyblockchocolatefactory1.method51()).decorate(TextDecoration.BOLD);
      Style style3 = TextComponentFactory.styleOf(skyblockchocolatefactory1.method53());
      return this.field13.method3(style2, style3);
   }

   private void method4(RabbitCollection fishing41, ItemRarity guitype32, int number3, int number4) {
      for (int index5 = 0; index5 < number3; index5++) {
         fishing41.method1(guitype32.name() + "_" + index5, guitype32, index5 < number4);
      }
   }

   private void method5(EventSlotUpdate highlightimpl1) {
      if (this.field8.method7() == SkyblockMenuType.HOPPITTY_COLLECTION) {
         if (highlightimpl1.getSlot() >= 10 && highlightimpl1.getSlot() <= 43) {
            ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
            if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty()) {
               if (bridgeextension_42.bridge$getItem() != Bridge.method28().method42()) {
                  String text3 = bridgeextension_42.bridge$getDisplayName();
                  if (text3.length() > 3 && text3.charAt(0) == 167) {
                     ItemRarity guitype34 = ItemRarity.fromCode(text3.charAt(1));
                     if (guitype34 != null) {
                        String text5 = ChatFormatting.getTextWithoutFormattingCodes(text3);
                        boolean flag6 = bridgeextension_42.bridge$getItem() == Bridge.method28().method5();
                        SkyblockChocolateFactory skyblockchocolatefactory7 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16))
                           .method1();
                        skyblockchocolatefactory7.method55().method1(text5, guitype34, flag6);
                     }
                  }
               }
            }
         }
      }
   }

   private void method6(TypedChatMessage data1) {
      if (IslandUtils.isOnIsland()) {
         String text2 = ChatFormatting.getTextWithoutFormattingCodes(data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC());
         SkyblockChocolateFactory skyblockchocolatefactory3 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         if (text2.startsWith("Switching to profile ")) {
            skyblockchocolatefactory3.method66(new RabbitCollection());
         } else {
            Matcher matcher4 = field10.matcher(text2);
            if (matcher4.matches()) {
               String text5 = matcher4.group("name");
               ItemRarity guitype36 = ItemRarity.fromRarity(matcher4.group("rarity"));
               if (guitype36 != null) {
                  skyblockchocolatefactory3.method55().method1(text5, guitype36, true);
                  skyblockchocolatefactory3.method21().method2(this.field9.method5());
               }
            }
         }
      }
   }

   private void method7(EventMarkerInput highlightimpl141) {
      if (IslandUtils.isOnIsland()) {
         if (this.field11 != null && this.field12) {
            if (highlightimpl141.method3() == 0 && highlightimpl141.method4() == MouseInputType.CLICK) {
               StringBuilder builder2 = new StringBuilder();
               SkyblockChocolateFactory skyblockchocolatefactory3 = (SkyblockChocolateFactory)((ChildModBinding)this.method7(ModTraits.field16)).method1();

               for (TextComponent text6 : skyblockchocolatefactory3.method55().method3(Style.empty(), Style.empty())) {
                  builder2.append(TextBridge.getTextContent(text6)).append("\n");
               }

               ClipboardUtils.method2(builder2.toString());
               Ref.method4().method69().method3(NotificationManager.method15("copiedStats", new Object[0]));
            }
         }
      }
   }

   private void method8(EventRenderContainerSlotPost data31) {
      if (IslandUtils.isOnIsland()) {
         if (this.field11 != null) {
            Data4 data42 = data31.method1();
            float value3 = this.field11.x();
            float value4 = this.field11.y();
            float value5 = this.field11.z();
            float value6 = this.field11.w();
            if (!(data42.HHHCHORHIHRCOHIOICICICHCRRICCI() < value3)
               && !(data42.HHHCHORHIHRCOHIOICICICHCRRICCI() > value4)
               && !(data42.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < value5)
               && !(data42.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > value6)) {
               this.field12 = true;
               LcuiScreen.method85(
                  data31.method5(), Collections.singletonList("Copy to Clipboard"), data42.xi(), data42.RROCOHICOORRHCIHHHCHRCICHIIHCO()
               );
            } else {
               this.field12 = false;
            }
         }
      }
   }
}
