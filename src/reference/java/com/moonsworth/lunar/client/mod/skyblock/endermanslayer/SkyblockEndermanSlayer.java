package com.moonsworth.lunar.client.mod.skyblock.endermanslayer;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge11_5;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.EntityEndermanMarkerBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.VoidgloomBoss;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.EntityFinder;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type;
import com.moonsworth.lunar.client.event.combat.EventEndermanTeleport;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockEndermanSlayer extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method21(AlertDisplayListener.class);
   public static final String field9 = "eyJ0aW1lc3RhbXAiOjE1MzQ5NjM0MzU5NjIsInByb2ZpbGVJZCI6ImQzNGFhMmI4MzFkYTRkMjY5NjU1ZTMzYzE0M2YwOTZjIiwicHJvZmlsZU5hbWUiOiJFbmRlckRyYWdvbiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWIwNzU5NGUyZGYyNzM5MjFhNzdjMTAxZDBiZmRmYTExMTVhYmVkNWI5YjIwMjllYjQ5NmNlYmE5YmRiYjRiMyJ9fX0=";
   public static final Pattern field10 = Pattern.compile("^(?<mobTypes>[^ ]+ )☠ Voidgloom Seraph (?<tier>\\w+) (?<extraData>.+)$");
   public static final Pattern field11 = Pattern.compile("^Spawned by: (.+)$");
   private static final ResourceLocationBridge field12 = ResourceLocationBridge.create("lunar", "mobs/enderman.png");
   private final HashMap<String, VoidgloomBoss> field13 = new HashMap<>();
   private final Set<BridgeExtension> field14 = new HashSet<>();
   private final Set<BridgeExtension> field15 = new HashSet<>();
   private final ListOption<String> field16 = (ListOption<String>)OptionFactory.method31("bossPriorityList", Codec.STRING.listOf())
      .method5(arg1x -> {
         if (this.field28 != null && this.field28.getOwner().equals(arg1x) && !this.field16.contains(arg1x)) {
            this.field28 = null;
         }
      })
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("colorVoidgloomPhase").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("pingGlyph").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("colorGlyph").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field20 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "glyphHighlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1442775040))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("highlightNukebibis").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field22 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "nukekubiHighlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(587137024))
      .method31();
   private final ToggleOption field23 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showVoidgloomHealth").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideOtherRadiation").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("cancelEnderManTeleport").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field26 = (ToggleOption)OptionFactory.method7("cancelEnderManTeleportEndOnly").method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("radiationTimer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private VoidgloomBoss field28;

   public SkyblockEndermanSlayer(Skyblock skyblock1) {
      super(false);
      this.method4(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method4(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockEndermanSlayer.Data()));
      this.method4(ModTraits.field17, ModCategories.method2(SettingsPage.SLAYER));
      this.method4(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method51(this::onDisable);
      this.handle(EventEntitySpawn.class, arg1x -> {
         this.method3(arg1x);
         this.method5(arg1x);
      });
      this.handle(EventDisconnect.class, arg1x -> {
         this.field28 = null;
         this.field13.clear();
      });
      this.handle(EventEntityRemove.class, this::method10);
      this.handle(EventEndermanTeleport.class, this::method2);
      this.handle(EventTick.class, this::method11);
      this.handle(EventBlockChange.class, this::method13);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::method16);
      this.handle(EventRenderEntity.class, this::method6);
   }

   private void onDisable() {
      this.field28 = null;
      this.field13.clear();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1("skyblockVoidgloom", arg1x -> {
         arg1x.method9(new ClientOption[]{this.field16, this.field17, this.field18});
         arg1x.method7(this.field19, arg1xx -> arg1xx.method9(new ClientOption[]{this.field20}));
         arg1x.method7(this.field21, arg1xx -> arg1xx.method9(new ClientOption[]{this.field22}));
         arg1x.method9(new ClientOption[]{this.field23, this.field25, this.field26, this.field24});
      });
   }

   private void method2(EventEndermanTeleport highlightimpl10_21) {
      if ((Boolean)this.field25.get()) {
         if (!(Boolean)this.field26.get()) {
            if (!IslandUtils.isOnIsland()) {
               return;
            }
         } else if (IslandUtils.getIsland() != SkyblockIsland.END) {
            return;
         }

         highlightimpl10_21.cancel();
      }
   }

   private void method3(EventEntitySpawn highlightimpl6_21) {
      if (IslandUtils.getIsland() == SkyblockIsland.END) {
         if (highlightimpl6_21.field1 instanceof EntityArmorStandBridge bridgeextension_22) {
            WorldBridgeExtension itemcounter6extension12 = Ref.method8();
            if (itemcounter6extension12 != null) {
               BridgeExtension bridgeextension4 = highlightimpl6_21.field1;
               if (bridgeextension4.bridge$getCustomName() instanceof TextComponent text5) {
                  String text14 = TextBridge.getTextContent(text5);
                  Triple triple7 = null;
                  Matcher matcher8 = field11.matcher(text14);
                  if (matcher8.find()) {
                     triple7 = this.method4(bridgeextension4, itemcounter6extension12.bridge$getEntities(), matcher8.group(1).toLowerCase());
                  }

                  matcher8 = field10.matcher(text14);
                  if (matcher8.find()) {
                     triple7 = this.method4(bridgeextension4, itemcounter6extension12.bridge$getEntities(), null);
                  }

                  if (triple7 != null) {
                     BridgeExtension bridgeextension9 = (BridgeExtension)triple7.getLeft();
                     BridgeExtension bridgeextension10 = (BridgeExtension)triple7.getMiddle();
                     String text11 = (String)triple7.getRight();
                     if (!text11.isEmpty()) {
                        if (this.field13.containsKey(text11) && !this.field13.get(text11).isValid()) {
                           this.method7(text11, bridgeextension10, bridgeextension9);
                        } else if (!this.field13.containsKey(text11)) {
                           this.method7(text11, bridgeextension10, bridgeextension9);
                        }
                     }
                  }
               }

               ItemStackBridge bridgeextension_413 = bridgeextension_22.bridge$getHelmet();
               if (bridgeextension_413 != null && bridgeextension_413.bridge$getItem().bridge$isItemSkull()) {
                  Optional optional15 = SkyblockItemUtil.method11(bridgeextension_413);
                  if (optional15.isPresent()
                     && ((String)optional15.get())
                        .equals(
                           "eyJ0aW1lc3RhbXAiOjE1MzQ5NjM0MzU5NjIsInByb2ZpbGVJZCI6ImQzNGFhMmI4MzFkYTRkMjY5NjU1ZTMzYzE0M2YwOTZjIiwicHJvZmlsZU5hbWUiOiJFbmRlckRyYWdvbiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWIwNzU5NGUyZGYyNzM5MjFhNzdjMTAxZDBiZmRmYTExMTVhYmVkNWI5YjIwMjllYjQ5NmNlYmE5YmRiYjRiMyJ9fX0="
                        )) {
                     this.method8(bridgeextension4);
                  }
               }

               if (bridgeextension_413 != null && bridgeextension_413.bridge$getItem().bridge$isItemBeacon()) {
                  this.method9(bridgeextension4);
               }
            }
         }
      }
   }

   private Triple<BridgeExtension, BridgeExtension, String> method4(BridgeExtension bridgeextension1, List<BridgeExtension> list2, @Nullable String text3) {
      String text4 = text3 == null ? "" : text3;

      for (BridgeExtension bridgeextension6 : list2) {
         if (bridgeextension6 instanceof EntityArmorStandBridge
            && !(Math.abs(bridgeextension1.bridge$getPosX() - bridgeextension6.bridge$getPosX()) > 0.1)
            && !(Math.abs(bridgeextension1.bridge$getPosY() - bridgeextension6.bridge$getPosY()) > 0.5)
            && !(Math.abs(bridgeextension1.bridge$getPosZ() - bridgeextension6.bridge$getPosZ()) > 0.1)) {
            Component component7 = bridgeextension6.bridge$getCustomName();
            if (component7 != null) {
               String text8 = TextBridge.getTextContent(component7);
               Pattern pattern9 = text3 != null ? field10 : field11;
               Matcher matcher10 = pattern9.matcher(text8);
               if (matcher10.find()) {
                  if (text3 == null) {
                     text4 = matcher10.group(1).toLowerCase();
                  }

                  return text3 != null ? Triple.of(bridgeextension6, bridgeextension1, text4) : Triple.of(bridgeextension1, bridgeextension6, text4);
               }
            }
         }
      }

      return null;
   }

   private void method5(EventEntitySpawn highlightimpl6_21) {
      if (IslandUtils.getIsland() == SkyblockIsland.END) {
         if (highlightimpl6_21.field1 instanceof Bridge11_5) {
            for (VoidgloomBoss click103 : this.field13.values()) {
               if (click103.method7().contains(highlightimpl6_21.field1)) {
                  return;
               }
            }

            VoidgloomBoss click104 = this.method12(highlightimpl6_21.field1);
            if (click104 != null) {
               click104.getEndermen().add(highlightimpl6_21.field1);
            }
         }
      }
   }

   private void method6(EventRenderEntity data81) {
      if (IslandUtils.getIsland() == SkyblockIsland.END) {
         if ((Boolean)this.field24.get()) {
            if (data81.method1() instanceof Bridge11_5 bridge11_53) {
               if (this.field28 == null || !this.field28.getEndermen().contains(bridge11_53)) {
                  data81.cancel();
               }
            }
         }
      }
   }

   private void method7(String text1, BridgeExtension bridgeextension2, BridgeExtension bridgeextension3) {
      WorldBridgeExtension itemcounter6extension4 = Ref.method8();
      if (itemcounter6extension4 != null) {
         BridgeExtension bridgeextension5 = EntityFinder.findNearest(bridgeextension2, itemcounter6extension4.bridge$getEntities(), arg0 -> arg0 instanceof EntityEndermanMarkerBridge);
         VoidgloomBoss click106 = new VoidgloomBoss(bridgeextension5, bridgeextension3, bridgeextension2, text1);
         this.field13.put(text1, click106);
         boolean flag7 = false;

         for (String text9 : (List)this.field16.get()) {
            if (text9.equals(text1)) {
               this.field28 = click106;
               flag7 = true;
               break;
            }

            if (this.field28 != null && text9.equals(this.field28.getOwner())) {
               flag7 = true;
               break;
            }
         }

         if (!flag7 && Objects.equals(click106.getOwner(), Ref.method4().method31().getName().toLowerCase())) {
            this.field28 = click106;
         }
      }
   }

   private void method8(BridgeExtension bridgeextension1) {
      if (!this.field15.contains(bridgeextension1)) {
         this.field15.add(bridgeextension1);
         VoidgloomBoss click102 = this.method12(bridgeextension1);
         if (click102 != null) {
            click102.getSkulls().add(bridgeextension1);
         }
      }
   }

   private void method9(BridgeExtension bridgeextension1) {
      if (!this.field14.contains(bridgeextension1)) {
         this.field14.add(bridgeextension1);
         VoidgloomBoss click102 = this.method12(bridgeextension1);
         if (click102 != null) {
            click102.setBeaconData(new com.moonsworth.lunar.client.framework.feature.mod.fishing.click.VoidgloomBoss.Data(bridgeextension1));
            if ((Boolean)this.field18.get() && click102 == this.field28) {
               this.field8
                  .method2(
                     ComparableImpl.method2().method1("YANG_GLYPH").method2(Component.text("Yang Glyph!", NamedTextColor.RED)).method4(Type.HIGH).method6()
                  );
            }
         }
      }
   }

   private void method10(EventEntityRemove highlightimpl121) {
      this.field15.remove(highlightimpl121.method1());
      this.field14.remove(highlightimpl121.method1());
      VoidgloomBoss click102 = null;

      for (VoidgloomBoss click104 : this.field13.values()) {
         if (click104.getBoss() == highlightimpl121.method1() || click104.getNameTag() == highlightimpl121.method1()) {
            click102 = this.field13.remove(click104.getOwner());
            break;
         }

         click104.getSkulls().remove(highlightimpl121.method1());
         if (click104.getBeaconData() != null && click104.getBeaconData().method1() == highlightimpl121.method1()) {
            click104.getBeaconData().method4(null);
            click104.getBeaconData().method5(Vec3Bridge.method2(highlightimpl121.method1().bridge$getPosX(), highlightimpl121.method1().bridge$getPosY(), highlightimpl121.method1().bridge$getPosZ()));
            click104.getBeaconData().method6(Ref.method3().bridge$getSystemTime());
         }

         click104.getEndermen().remove(highlightimpl121.method1());
      }

      if (click102 == this.field28) {
         this.field28 = null;

         for (String text7 : (List)this.field16.get()) {
            VoidgloomBoss click105 = this.field13.get(text7);
            if (click105 != null) {
               this.field28 = click105;
               break;
            }
         }
      }
   }

   private void method11(EventTick highlightimpl21) {
      for (VoidgloomBoss click103 : this.field13.values()) {
         click103.method1();
      }
   }

   private VoidgloomBoss method12(BridgeExtension bridgeextension1) {
      VoidgloomBoss click102 = null;
      double value3 = 225.0;

      for (VoidgloomBoss click106 : this.field13.values()) {
         double value7 = bridgeextension1.method13(click106.getBoss());
         if (value7 < value3) {
            click102 = click106;
            value3 = value7;
         }
      }

      return click102;
   }

   private void method13(EventBlockChange highlightimpl91) {
      BlocksBridge bridge_562 = Bridge.method34();
      Vec3iBridge horsestats203 = highlightimpl91.method1();
      if (highlightimpl91.method3().bridge$getBlock() == bridge_562.method12()) {
         this.method14(highlightimpl91, horsestats203);
      } else if (highlightimpl91.method2().bridge$getBlock() == bridge_562.method12()) {
         this.method15(horsestats203);
      }
   }

   private void method14(EventBlockChange highlightimpl91, Vec3iBridge horsestats202) {
      VoidgloomBoss click103 = null;
      double value4 = 100.0;

      for (VoidgloomBoss click107 : this.field13.values()) {
         if (click107.method9() != null
            && (click107.method9().method3() == -1L || Ref.method3().bridge$getSystemTime() - click107.method9().method3() <= 2000L)) {
            BridgeExtension bridgeextension9 = click107.method9().method1();
            Vec3Bridge horsestats158;
            if (bridgeextension9 != null) {
               horsestats158 = Vec3Bridge.method2(bridgeextension9.bridge$getPosX(), bridgeextension9.bridge$getPosY(), bridgeextension9.bridge$getPosZ());
            } else {
               horsestats158 = click107.method9().method2();
            }

            if (horsestats158 != null) {
               float value10 = (float)(horsestats202.bridge$getX() - horsestats158.bridge$xCoord());
               float value11 = (float)(horsestats202.bridge$getY() - horsestats158.bridge$yCoord());
               float value12 = (float)(horsestats202.bridge$getZ() - horsestats158.bridge$zCoord());
               float value13 = value10 * value10 + value11 * value11 + value12 * value12;
               if (value13 < value4) {
                  click103 = click107;
                  value4 = value13;
               }
            }
         }
      }

      if (click103 != null) {
         click103.setBeaconBlockPos(highlightimpl91.method1());
         click103.setBeaconData(null);
      }
   }

   private void method15(Vec3iBridge horsestats201) {
      for (VoidgloomBoss click103 : this.field13.values()) {
         Vec3iBridge horsestats204 = click103.getBeaconBlockPos();
         if (horsestats204 != null && horsestats204.bridge$getX() == horsestats201.bridge$getX() && horsestats204.bridge$getY() == horsestats201.bridge$getY() && horsestats204.bridge$getZ() == horsestats201.bridge$getZ()) {
            click103.setBeaconBlockPos(null);
            break;
         }
      }
   }

   private void method16(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      if (this.isEnabled() && this.field28 != null) {
         WorldBridgeExtension itemcounter6extension2 = Ref.method8();
         if (itemcounter6extension2 != null) {
            if ((Boolean)this.field17.get()
               || (Boolean)this.field21.get() && this.field28.getSkulls() != null
               || (Boolean)this.field19.get() && this.field28.getBeaconBlockPos() != null) {
               AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
               float value4 = highlightimpl21.method5();
               EntityRenderDispatcherBridge bridge2_435 = Ref.method13();
               bridgeextension_93.push();
               bridgeextension_93.translate(-bridge2_435.bridge$renderPosX(), -bridge2_435.bridge$renderPosY(), -bridge2_435.bridge$renderPosZ());
               DrawBufferBridge bridge2_326 = bridgeextension_93.method10(LunarRenderTypes.field15);
               bridge2_326.method1();
               if ((Boolean)this.field17.get()) {
                  AxisAlignedBBBridge horsestats127 = this.field28.getBoss().method11(highlightimpl21.method5());

                  WorldRenderUtils.fillBox(
                     bridge2_326,
                     horsestats127.bridge$getMinX(),
                     horsestats127.bridge$getMinY(),
                     horsestats127.bridge$getMinZ(),
                     horsestats127.bridge$getMaxX(),
                     horsestats127.bridge$getMaxY(),
                     horsestats127.bridge$getMaxZ(),
                     switch (this.field28.getType()) {
                        case SHIELD -> 587159039;
                        case RADIATION -> 570469034;
                        default -> 587158869;
                     }
                  );
               }

               if ((Boolean)this.field21.get()) {
                  for (BridgeExtension bridgeextension22 : this.field28.getSkulls()) {
                     double value9 = bridgeextension22.method8(value4);
                     double value11 = bridgeextension22.method9(value4);
                     double value13 = bridgeextension22.method10(value4);
                     AxisAlignedBBBridge horsestats1215 = AxisAlignedBBBridge.method2(value9 - 0.5, value11 + 0.6, value13 - 0.5, value9 + 0.5, value11 + 1.6, value13 + 0.5);
                     WorldRenderUtils.fillBox(
                        bridge2_326,
                        horsestats1215.bridge$getMinX(),
                        horsestats1215.bridge$getMinY(),
                        horsestats1215.bridge$getMinZ(),
                        horsestats1215.bridge$getMaxX(),
                        horsestats1215.bridge$getMaxY(),
                        horsestats1215.bridge$getMaxZ(),
                        this.field22.method1(0.0F)
                     );
                  }
               }

               if ((Boolean)this.field19.get() && this.field28.getBeaconBlockPos() != null) {
                  Vec3iBridge horsestats2020 = this.field28.getBeaconBlockPos();
                  AxisAlignedBBBridge horsestats1223 = itemcounter6extension2.method4(horsestats2020).bridge$getAABB(itemcounter6extension2, horsestats2020);
                  if (horsestats1223 == null) {
                     return;
                  }

                  horsestats1223 = horsestats1223.method5(horsestats2020).method11(0.01);
                  WorldRenderUtils.fillBox(
                     bridge2_326,
                     horsestats1223.bridge$getMinX(),
                     horsestats1223.bridge$getMinY(),
                     horsestats1223.bridge$getMinZ(),
                     horsestats1223.bridge$getMaxX(),
                     horsestats1223.bridge$getMaxY(),
                     horsestats1223.bridge$getMaxZ(),
                     this.field20.method1(0.0F)
                  );
               }

               bridge2_326.method17(BufferMode.BATCHED);
               BufferBuilderBridge bridge_2821 = bridgeextension_93.method11(1.0F);
               if ((Boolean)this.field17.get()) {
                  AxisAlignedBBBridge horsestats1225 = this.field28.getBoss().method11(highlightimpl21.method5());

                  WorldRenderUtils.drawBoxOutline(
                     bridge_2821,
                     horsestats1225.bridge$getMinX(),
                     horsestats1225.bridge$getMinY(),
                     horsestats1225.bridge$getMinZ(),
                     horsestats1225.bridge$getMaxX(),
                     horsestats1225.bridge$getMaxY(),
                     horsestats1225.bridge$getMaxZ(),
                     switch (this.field28.getType()) {
                        case SHIELD -> -43521;
                        case RADIATION -> -16733526;
                        default -> -43691;
                     }
                  );
               }

               if ((Boolean)this.field21.get()) {
                  for (BridgeExtension bridgeextension30 : this.field28.getSkulls()) {
                     double value10 = bridgeextension30.method8(value4);
                     double value12 = bridgeextension30.method9(value4);
                     double value14 = bridgeextension30.method10(value4);
                     AxisAlignedBBBridge horsestats1216 = AxisAlignedBBBridge.method2(value10 - 0.5, value12 + 0.6, value14 - 0.5, value10 + 0.5, value12 + 1.6, value14 + 0.5);
                     WorldRenderUtils.drawBoxOutline(
                        bridge_2821,
                        horsestats1216.bridge$getMinX(),
                        horsestats1216.bridge$getMinY(),
                        horsestats1216.bridge$getMinZ(),
                        horsestats1216.bridge$getMaxX(),
                        horsestats1216.bridge$getMaxY(),
                        horsestats1216.bridge$getMaxZ(),
                        ColorUtils.method31(this.field22.method1(0.0F))
                     );
                  }
               }

               if ((Boolean)this.field19.get() && this.field28.getBeaconBlockPos() != null) {
                  Vec3iBridge horsestats2027 = this.field28.getBeaconBlockPos();
                  AxisAlignedBBBridge horsestats1231 = itemcounter6extension2.method4(horsestats2027).bridge$getAABB(itemcounter6extension2, horsestats2027).method5(horsestats2027).method11(0.01);
                  WorldRenderUtils.drawBoxOutline(
                     bridge_2821,
                     horsestats1231.bridge$getMinX(),
                     horsestats1231.bridge$getMinY(),
                     horsestats1231.bridge$getMinZ(),
                     horsestats1231.bridge$getMaxX(),
                     horsestats1231.bridge$getMaxY(),
                     horsestats1231.bridge$getMaxZ(),
                     ColorUtils.method31(this.field20.method1(0.0F))
                  );
               }

               bridge_2821.end();
               bridgeextension_93.pop();
               if (this.field28.getType() == com.moonsworth.lunar.client.framework.feature.mod.fishing.click.VoidgloomBoss.Type.RADIATION
                  && (Boolean)this.field27.get()) {
                  Component component28 = this.method17(this.field28.getBeaconTimer());
                  if (component28 == null) {
                     return;
                  }

                  Bridge5Extension_5 bridge5extension_532 = Ref.method7();
                  if (bridge5extension_532 == null) {
                     return;
                  }

                  BridgeExtension bridgeextension33 = this.field28.getBoss();
                  double value34 = bridgeextension33.method8(value4);
                  double value35 = bridgeextension33.method9(value4);
                  double value36 = bridgeextension33.method10(value4);
                  Vector3d vector3d17 = new Vector3d(bridge2_435.bridge$renderPosX(), bridge2_435.bridge$renderPosY(), bridge2_435.bridge$renderPosZ())
                     .sub(value34, value35, value36)
                     .normalize();
                  Vector3d vector3d18 = new Vector3d(value34, value35, value36).add(vector3d17);
                  WorldRenderUtils.drawComponent(bridgeextension_93, component28, vector3d18.x(), vector3d18.y(), vector3d18.z(), true);
               }
            }
         }
      }
   }

   private Component method17(HudTimer threadmoduledump451) {
      if (threadmoduledump451 == null) {
         return null;
      }

      long number3 = threadmoduledump451.get();
      if (number3 <= 0L) {
         return null;
      }

      NamedTextColor namedtextcolor2;
      if (number3 > 5000L) {
         namedtextcolor2 = NamedTextColor.RED;
      } else if (number3 > 1000L) {
         namedtextcolor2 = NamedTextColor.GOLD;
      } else {
         namedtextcolor2 = NamedTextColor.YELLOW;
      }

      return Component.text(threadmoduledump451.method1()).color(namedtextcolor2);
   }

   public String getId() {
      return "SKYBLOCK_ENDERMAN_SLAYER";
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 50, 150, 300);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         if (flag1) {
            return this.method3("5.2M❤", NamedTextColor.RED);
         }

         if (SkyblockEndermanSlayer.this.field28 == null) {
            return null;
         }

         if (SkyblockEndermanSlayer.this.field28.method3().bridge$getCustomName() instanceof TextComponent text2) {
            Matcher matcher5 = SkyblockEndermanSlayer.field10.matcher(TextBridge.getTextContent(text2));
            if (matcher5.find()) {
               NamedTextColor namedtextcolor4 = switch (SkyblockEndermanSlayer.this.field28.getType()) {
                  case SHIELD -> NamedTextColor.LIGHT_PURPLE;
                  case RADIATION -> NamedTextColor.DARK_AQUA;
                  default -> NamedTextColor.RED;
               };
               return this.method3(matcher5.group("extraData"), namedtextcolor4);
            } else {
               return null;
            }
         } else {
            return null;
         }
      }

      private HudLine method3(String text1, NamedTextColor namedtextcolor2) {
         return new HudLine(
            SkyblockEndermanSlayer.field12,
            TextComponentFactory.builder()
               .method2("Voidgloom")
               .method4(text1)
               .method5(NamedTextColor.GOLD)
               .method7(namedtextcolor2)
               .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
               .build()
         );
      }

      public boolean method4(boolean flag1) {
         if (!(Boolean)SkyblockEndermanSlayer.this.field23.get()) {
            this.method58(0.0F, 0.0F);
            return false;
         } else {
            return super.method4(flag1);
         }
      }

      public boolean method30() {
         return !SkyblockEndermanSlayer.this.field23.get() ? false : super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI();
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
