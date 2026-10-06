package com.moonsworth.lunar.client.mod.skyblock.kuudra;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension5_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.kuudra.SkyblockKuudraWaypoints;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.joml.Vector2d;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockKuudra extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^Your Fresh Tools Perk bonus doubles your building speed for the next 10 seconds!$");
   private static final Pattern field9 = Pattern.compile("^Used Extreme Focus! \\((?<mana>\\d+) Mana\\)$");
   private final String field10 = "ewogICJ0aW1lc3RhbXAiIDogMTU5NDAyOTYxNjQyNCwKICAicHJvZmlsZUlkIiA6ICJkZGVkNTZlMWVmOGI0MGZlOGFkMTYyOTIwZjdhZWNkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEaXNjb3JkQXBwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI0YmJmZDlkODRmNDI0NTZjZDAyYTRiYWE1Y2QwNTRiY2VkMGRkYjJkMWM4MzIxYzgzZTVkNjY3Y2Q4NTU3NWEiCiAgICB9CiAgfQp9";
   private final SkyblockKuudraWaypoints field11;
   private final SkyblockKuudraAlerts field12;
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraWaypoints").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("kuudraAlerts").method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraFreshMessage").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraManaDrainMessage").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraNoSupplySpawnMessage")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraSupplyWaypoints").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraSupplyPlaceWaypoints")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraBuildWaypoints").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("kuudraCellWaypoints").method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraPearlWaypoints").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field23 = (ToggleOption)OptionFactory.method7("kuudraSecondWaypoints").method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraStunWaypoints").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field25 = (ColorOption)((Data)OptionFactory.method8("kuudraSupplyColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1073807360))
      .method31();
   private final ColorOption field26 = (ColorOption)((Data)OptionFactory.method8("kuudraSupplyPlaceColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1090518785))
      .method31();
   private final ColorOption field27 = (ColorOption)((Data)OptionFactory.method8("kuudraCellColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1073807360))
      .method31();
   private final ColorOption field28 = (ColorOption)((Data)OptionFactory.method8("kuudraPearlWaypointColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711681))
      .method31();
   private final ColorOption field29 = (ColorOption)((Data)OptionFactory.method8("kuudraSecondWaypointColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-256))
      .method31();
   private final ColorOption field30 = (ColorOption)((Data)OptionFactory.method8("kuudraStunWaypointColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1090453760))
      .method31();
   private final ToggleOption field31 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraDropshipAlert").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> field32 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "kuudraDropshipAlertColor", NamedColorOption.AQUA
      )
      .method31();
   private final ToggleOption field33 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraStunAlert").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> field34 = (EnumOption<NamedColorOption>)OptionFactory.method10("kuudraStunAlertColor", NamedColorOption.RED)
      .method31();
   private final ToggleOption field35 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraBallistaBuiltAlert").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> field36 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "kuudraBallistaBuiltAlertColor", NamedColorOption.GREEN
      )
      .method31();
   private final ToggleOption field37 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraBallistaFueledAlert")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> field38 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "kuudraBallistaFueledAlertColor", NamedColorOption.GREEN
      )
      .method31();
   private final ToggleOption field39 = (ToggleOption)OptionFactory.method7("kuudraPeekAlert").method31();
   private final EnumOption<NamedColorOption> field40 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "kuudraPeekAlertColor", NamedColorOption.GREEN
      )
      .method31();
   private final ToggleOption field41 = (ToggleOption)OptionFactory.method7("kuudraPeekCompassDirection").method31();
   private final ToggleOption field42 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kuudraFirstPeekOnly").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final HashSet<SkyblockKuudra.Type> field43 = new HashSet<>();

   public SkyblockKuudra(Skyblock skyblock1) {
      super(false);
      this.field11 = new SkyblockKuudraWaypoints(this, this.field13);
      this.field12 = new SkyblockKuudraAlerts(this, this.field14);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.CRIMSON_ISLE));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, arg1x -> {
         if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
            String text2 = TextBridge.getTextContent(arg1x.OHCICHOROROOORHCRICORHRRCRCCHO()).trim();
            if ((Boolean)this.field15.get()) {
               this.method2(text2);
            }

            if ((Boolean)this.field16.get()) {
               this.method3(text2);
            }

            if ((Boolean)this.field17.get()) {
               this.method4(text2);
            }
         }
      });
      this.handle(EventEntitySpawn.class, this::method5);
      this.handle(EventWorldChange.class, this::method6);
   }

   public List<Framework7Extension> method9() {
      return List.of(this.field11, this.field12);
   }

   private void method2(String text1) {
      if (field8.matcher(text1).matches()) {
         ChatMessageQueue.method1("/pc FRESH!");
      }
   }

   private void method3(String text1) {
      Matcher matcher2 = field9.matcher(text1);
      if (matcher2.matches()) {
         String text3 = matcher2.group("mana");
         ChatMessageQueue.method1("/pc Drained " + text3 + " mana!");
      }
   }

   private void method4(String text1) {
      if (text1.equals("[NPC] Elle: Not again!")) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            for (SkyblockKuudra.Type type6 : SkyblockKuudra.Type.values()) {
               if (!this.field43.contains(type6) && type6.isClose(bridge5extension_52)) {
                  ChatMessageQueue.method1("/pc No " + type6 + "!");
                  return;
               }
            }
         }
      }
   }

   private void method5(EventEntitySpawn highlightimpl6_21) {
      if ((Boolean)this.field17.get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
            if (this.method7(highlightimpl6_21.field1)) {
               SkyblockKuudra.Type type2 = null;
               double value3 = Double.MAX_VALUE;

               for (SkyblockKuudra.Type type8 : SkyblockKuudra.Type.values()) {
                  Vector2d vector2d9 = type8.getPosition();
                  double value10 = highlightimpl6_21.field1.method18(vector2d9.x(), 63.0, vector2d9.y());
                  if (type2 == null || value10 < value3) {
                     type2 = type8;
                     value3 = value10;
                  }
               }

               this.field43.add(type2);
            }
         }
      }
   }

   private void method6(EventWorldChange data31) {
      this.field43.clear();
   }

   private boolean method7(BridgeExtension bridgeextension1) {
      if (bridgeextension1 instanceof Bridge5Extension5_2 bridge5extension5_22) {
         ItemStackBridge bridgeextension_43 = bridge5extension5_22.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.MAINHAND);
         if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
            String text4 = (String)SkyblockItemUtil.method11(bridgeextension_43).orElse(null);
            return text4 == null
               ? false
               : text4.equals(
                  "ewogICJ0aW1lc3RhbXAiIDogMTU5NDAyOTYxNjQyNCwKICAicHJvZmlsZUlkIiA6ICJkZGVkNTZlMWVmOGI0MGZlOGFkMTYyOTIwZjdhZWNkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEaXNjb3JkQXBwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI0YmJmZDlkODRmNDI0NTZjZDAyYTRiYWE1Y2QwNTRiY2VkMGRkYjJkMWM4MzIxYzgzZTVkNjY3Y2Q4NTU3NWEiCiAgICB9CiAgfQp9"
               );
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public String getId() {
      return "SKYBLOCK_KUUDRA";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(this.field13, arg1x -> {
         arg1x.method7(this.field18, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field25}));
         arg1x.method7(this.field19, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field26}));
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field20});
         arg1x.method7(this.field21, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field27}));
         arg1x.method7(this.field22, arg1xx -> {
            arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field28});
            arg1xx.method7(this.field23, arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29}));
         });
         arg1x.method7(this.field24, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field30}));
      });
      lightingextension231.method7(
         this.field14,
         arg1x -> {
            arg1x.method7(this.field31, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field32}));
            arg1x.method7(this.field33, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field34}));
            arg1x.method7(this.field35, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field36}));
            arg1x.method7(this.field37, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field38}));
            arg1x.method7(
               this.field39, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field40, this.field41, this.field42})
            );
         }
      );
      lightingextension231.method9(new ClientOption[]{this.field15, this.field16, this.field17});
   }

   @Generated
   public String method13() {
      return "ewogICJ0aW1lc3RhbXAiIDogMTU5NDAyOTYxNjQyNCwKICAicHJvZmlsZUlkIiA6ICJkZGVkNTZlMWVmOGI0MGZlOGFkMTYyOTIwZjdhZWNkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEaXNjb3JkQXBwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI0YmJmZDlkODRmNDI0NTZjZDAyYTRiYWE1Y2QwNTRiY2VkMGRkYjJkMWM4MzIxYzgzZTVkNjY3Y2Q4NTU3NWEiCiAgICB9CiAgfQp9";
   }

   @Generated
   public SkyblockKuudraWaypoints method14() {
      return this.field11;
   }

   @Generated
   public SkyblockKuudraAlerts method15() {
      return this.field12;
   }

   @Generated
   public ToggleOption method16() {
      return this.field13;
   }

   @Generated
   public ToggleOption method17() {
      return this.field14;
   }

   @Generated
   public ToggleOption method19() {
      return this.field15;
   }

   @Generated
   public ToggleOption method21() {
      return this.field16;
   }

   @Generated
   public ToggleOption method22() {
      return this.field17;
   }

   @Generated
   public ToggleOption method23() {
      return this.field18;
   }

   @Generated
   public ToggleOption method24() {
      return this.field19;
   }

   @Generated
   public ToggleOption method25() {
      return this.field20;
   }

   @Generated
   public ToggleOption method26() {
      return this.field21;
   }

   @Generated
   public ToggleOption method27() {
      return this.field22;
   }

   @Generated
   public ToggleOption method28() {
      return this.field23;
   }

   @Generated
   public ToggleOption method29() {
      return this.field24;
   }

   @Generated
   public ColorOption method30() {
      return this.field25;
   }

   @Generated
   public ColorOption method34() {
      return this.field26;
   }

   @Generated
   public ColorOption method35() {
      return this.field27;
   }

   @Generated
   public ColorOption method36() {
      return this.field28;
   }

   @Generated
   public ColorOption method37() {
      return this.field29;
   }

   @Generated
   public ColorOption method38() {
      return this.field30;
   }

   @Generated
   public ToggleOption method39() {
      return this.field31;
   }

   @Generated
   public EnumOption<NamedColorOption> method40() {
      return this.field32;
   }

   @Generated
   public ToggleOption method41() {
      return this.field33;
   }

   @Generated
   public EnumOption<NamedColorOption> method42() {
      return this.field34;
   }

   @Generated
   public ToggleOption method43() {
      return this.field35;
   }

   @Generated
   public EnumOption<NamedColorOption> method44() {
      return this.field36;
   }

   @Generated
   public ToggleOption method45() {
      return this.field37;
   }

   @Generated
   public EnumOption<NamedColorOption> method46() {
      return this.field38;
   }

   @Generated
   public ToggleOption method47() {
      return this.field39;
   }

   @Generated
   public EnumOption<NamedColorOption> method48() {
      return this.field40;
   }

   @Generated
   public ToggleOption method49() {
      return this.field41;
   }

   @Generated
   public ToggleOption method50() {
      return this.field42;
   }

   @Generated
   public HashSet<SkyblockKuudra.Type> method51() {
      return this.field43;
   }

   private enum Type {
      TRIANGLE(new Vector2d(-62.0, -124.0)),
      SHOP(new Vector2d(-75.0, -142.0)),
      X(new Vector2d(-136.0, -145.0)),
      X_CANNON(new Vector2d(-140.0, -123.0)),
      SQUARE(new Vector2d(-145.0, -86.0)),
      SLASH(new Vector2d(-114.0, -65.0)),
      EQUALS(new Vector2d(-61.0, -87.0));

      private final Vector2d position;

      public boolean isClose(Bridge5Extension_5 bridge5extension_51) {
         return bridge5extension_51.method15(this.position.x(), bridge5extension_51.bridge$getPosY(), this.position.y()) <= 2304.0;
      }

      @Override
      public String toString() {
         return WordUtils.capitalizeFully(this.name().replace('_', ' '));
      }

      @Generated
      public Vector2d getPosition() {
         return this.position;
      }

      @Generated
      Type(Vector2d vector2d3) {
         this.position = vector2d3;
      }
   }
}
