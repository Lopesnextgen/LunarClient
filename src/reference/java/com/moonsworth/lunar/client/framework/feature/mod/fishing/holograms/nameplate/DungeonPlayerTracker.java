package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.lunarclient.minecraft.hypixel.HypixelPlayerUtil;
import com.lunarclient.player.PlayerResponse;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapPlayerStyle;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.StackedPlayersTooltip;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonMapOverlay;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonTrackable;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapTickIcon;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonClass;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapSettings;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonClassChangedEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

@VersionGate(min = 33)
public class DungeonPlayerTracker implements DungeonTrackable {
   public static final Pattern field1 = Pattern.compile(
      "^(?:§.)*\\[(?<classLetter>.)] (?<name>[\\w§]+) (?:§.)*?§(?<healthColor>.)(?<health>[\\d,]+)(§r)?(?:§c❤)?(§r)?$"
   );
   private final DungeonStateTracker field2;
   private final ArrayList<StackedPlayersTooltip> field3 = new ArrayList<>();
   private final WorldPosition field4;
   private final AnimatedValue field5;
   private final ArrayList<DungeonPlayerTracker.Data> field6 = new ArrayList<>(600);
   private PlayerInfoBridge field7;
   private WorldPosition field8 = null;
   private long field9;
   private int deaths;
   private int field10;
   private volatile int field11;
   private volatile int field12;
   private volatile boolean field13;
   private int field14;
   private int field15;
   @Nullable
   private DungeonClass field16;
   private int field17 = -1;
   @Nullable
   private UUID uuid;
   private String username;
   private boolean isDead;
   private boolean field18;
   private Bridge6_10 field19;
   private DungeonRoomTracker field20;
   private long field21;
   private long field22 = 0L;
   private ResourceLocationBridge field23;
   private int field24 = -1;
   private NamedTextColor field25 = NamedTextColor.GREEN;
   private Component field26;

   public DungeonPlayerTracker(PlayerInfoBridge bridge2_331, DungeonStateTracker holograms2_52) {
      this(bridge2_331, holograms2_52, null, null);
   }

   public DungeonPlayerTracker(PlayerInfoBridge bridge2_331, DungeonStateTracker holograms2_52, @Nullable UUID uuid3, @Nullable ResourceLocationBridge horsestats144) {
      this.field2 = holograms2_52;
      this.field7 = bridge2_331;
      this.uuid = uuid3;
      this.field23 = horsestats144;
      this.field4 = new WorldPosition(100000.0, 100000.0, holograms2_52);
      this.field5 = new AnimatedValue(0.0, AnimatedValue.Type.LINEAR);
      this.field11 = -1;
      this.field12 = 1;
      this.field21 = Ref.method3().bridge$getSystemTime();
   }

   public void tick() {
      this.method2();
      DungeonRoomTracker holograms4iterator1 = this.method6();
      if (holograms4iterator1 != this.field20) {
         this.field21 = Ref.method3().bridge$getSystemTime();
         if (holograms4iterator1 != null) {
            holograms4iterator1.method20(this);
         }
      }

      this.field20 = holograms4iterator1;
      int number2 = (int)(Ref.method3().bridge$getSystemTime() - this.field2.method47()) / 100;
      DungeonPlayerTracker.Data data3 = new DungeonPlayerTracker.Data(
         this.getYaw(), this.method30().method2(), this.field19 == null ? 0.0 : this.field19.bridge$getPosY(), this.method30().method3()
      );

      while (this.field6.size() < number2) {
         this.field6.add(data3);
      }
   }

   private void method2() {
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      if (itemcounter6extension1 != null && this.username != null) {
         if (this.method19()) {
            this.field19 = Ref.method7();
         } else {
            this.field19 = (Bridge6_10)itemcounter6extension1.bridge$getPlayerByName(this.username).orElse(null);
         }
      }

      if (this.field19 != null) {
         this.field23 = this.field19.bridge$getServerSkinTexture();
         if (this.uuid == null) {
            this.uuid = this.field19.bridge$getUniqueID();
         }

         this.field18 = this.field19.bridge$showHat();
         this.method5(this.field19.bridge$getRotationYaw() + 180.0, 50L);
         this.field4.method6(this.field19.bridge$getPosX(), 50L);
         this.field4.method7(this.field19.bridge$getPosZ(), 50L);
         this.field9 = Ref.method3().bridge$getSystemTime();
      }
   }

   public void method2(boolean flag1) {
      if (this.uuid != null) {
         HypixelPlayerUtil.getPlayerAsync(this.uuid.toString()).thenAccept(arg2 -> {
            if (arg2 == null) {
               this.field13 = true;
            } else {
               Integer number3 = this.method3(arg2);
               if (number3 == null) {
                  this.field13 = true;
               } else {
                  this.field13 = false;
                  if (flag1) {
                     this.field11 = number3;
                  }

                  this.field12 = number3;
               }
            }
         });
      }
   }

   @Nullable
   private Integer method3(PlayerResponse playerresponse1) {
      return (Integer)playerresponse1.player().achievements().get("skyblock_treasure_hunter");
   }

   public int method4() {
      return this.field12 - this.field11;
   }

   public void method5(double value1, long number3) {
      double value5 = this.field5.getValue();
      double value7 = value1 - value5;
      if (value7 > 180.0) {
         this.field5.animateTo(value5 + 360.0, 0L);
      }

      if (value7 < -180.0) {
         this.field5.animateTo(value5 - 360.0, 0L);
      }

      this.field5.animateTo(value1, number3);
   }

   private DungeonRoomTracker method6() {
      return this.field2.method13(this.field4.method8(), this.field4.method9());
   }

   public DungeonRoomTracker method7() {
      return this.field20;
   }

   public WorldPosition method8() {
      return this.field8 != null ? this.field8 : this.field4;
   }

   public void method9(String text2, String text4, String text3) {
      if (this.username == null || !this.username.equals(text2)) {
         this.field19 = null;
      }

      this.username = text2;
      if (text4 != null && !text4.equals("EMPTY")) {
         this.isDead = text4.equals("DEAD");
         if (!this.isDead) {
            DungeonClass hologramstype2_24 = this.field16;
            this.field16 = DungeonClass.fromDisplayName(text4);
            if (this.field16 != null) {
               this.field26 = Component.text(text2).color(this.field16.getColor());
            }

            if (text3 != null) {
               this.field17 = text3.matches("\\d+") ? Integer.parseInt(text3) : RomanNumeralParser.romanToInt(text3);
            }

            if (hologramstype2_24 != this.field16) {
               LunarEventBus.method29().method12(DungeonClassChangedEvent.class, () -> new DungeonClassChangedEvent(this, this.field16));
            }
         }
      }
   }

   public void method10(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, float value3, float value4, DungeonMapOverlay holograms3_25, boolean flag6) {
      this.method11(mixinhelper_41, holograms_92, value3, value4, holograms3_25, flag6, false);
   }

   public void method11(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, float value3, float value4, DungeonMapOverlay holograms3_25, boolean flag6, boolean flag) {
      if (!this.isDead && holograms_92.method36() != MapPlayerStyle.OFF) {
         float value8 = holograms_92.method37();
         float value9 = holograms_92.method38();
         if (holograms_92.method39() == MapPlayerStyle.ICONS || holograms_92.method39() == MapPlayerStyle.SELF_ICON && this.method19()) {
            holograms3_25.method20(
               mixinhelper_41, MapTickIcon.VANILLA_MAP_PLAYER, value3, value4, value8 * 5.0F / 2.0F, value8 * 7.0F / 2.0F, this.getYaw(), this.method19() ? -16711936 : -1
            );
         } else {
            mixinhelper_41.push();
            mixinhelper_41.method38(value3 * holograms3_25.field8, value4 * holograms3_25.field8, 0.0F);
            mixinhelper_41.method42(this.method14());
            mixinhelper_41.method38(-value3 * holograms3_25.field8, -value4 * holograms3_25.field8, 0.0F);
            if (holograms_92.method40() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HeadBorderStyle.NONE) {
               LineBatchRenderer.method5(mixinhelper_41);
               holograms3_25.method17(mixinhelper_41, value3 - value8 - value9, value4 - value8 - value9, value8 * 2.0F + value9 * 2.0F, value8 * 2.0F + value9 * 2.0F, holograms_92.method27(this));
               LineBatchRenderer.method6(mixinhelper_41);
            }

            ResourceLocationBridge horsestats1410 = this.method12();
            mixinhelper_41.method38(value3 * holograms3_25.field8, value4 * holograms3_25.field8, 0.0F);
            mixinhelper_41.scale(value8 * 2.0F / 8.0F * holograms3_25.field8, value8 * 2.0F / 8.0F * holograms3_25.field8, 1.0F);
            if (horsestats1410 != null) {
               LcuiScreen.method50(mixinhelper_41, horsestats1410, -4.0F, -4.0F, -1, this.field18);
            }

            mixinhelper_41.pop();
         }

         if (flag6 && holograms_92.method21()) {
            mixinhelper_41.push();
            mixinhelper_41.method38(value3 * holograms3_25.field8, value4 * holograms3_25.field8, 0.0F);
            if (holograms_92.method33()) {
               mixinhelper_41.method42(this.field2.method29().method14());
            }

            holograms3_25.method11(mixinhelper_41, (flag ? ChatFormatting.RED : "") + this.method20(true), 0.0F, value8 * 1.5F + value9 * 1.5F, 0.5F, HologramTextRenderer.Type.BORDER);
            mixinhelper_41.pop();
         }
      }
   }

   @Nullable
   private ResourceLocationBridge method12() {
      return this.field23 == null && this.field7 != null ? this.field7.bridge$getLocationSkin() : this.field23;
   }

   public double method13() {
      if (this.field22 != 0L) {
         int index1 = (int)(this.field22 - this.field2.method47()) / 100;
         double value2 = (this.field22 - this.field2.method47()) % 100.0 / 100.0;
         if (this.field6.size() <= index1) {
            return 0.0;
         } else {
            return this.field6.size() == index1 + 1
               ? this.field6.get(index1).field3
               : this.field6.get(index1).field3 * (1.0 - value2) + this.field6.get(index1 + 1).field3 * value2;
         }
      } else {
         return this.field19 != null ? this.field19.bridge$getPosY() : 0.0;
      }
   }

   public float method14() {
      if (this.field22 != 0L) {
         int index1 = (int)(this.field22 - this.field2.method47()) / 100;
         double value2 = (this.field22 - this.field2.method47()) % 100.0 / 100.0;
         if (this.field6.size() <= index1) {
            return 0.0F;
         } else {
            return this.field6.size() == index1 + 1
               ? (float)this.field6.get(index1).field1
               : (float)(this.field6.get(index1).field1 * (1.0 - value2) + this.field6.get(index1 + 1).field1 * value2);
         }
      } else {
         return this.getYaw();
      }
   }

   private float getYaw() {
      return this.field19 != null ? (float)this.field19.bridge$getRotationYaw() + 180.0F : (float)this.field5.getValue();
   }

   public long method15() {
      return this.field9;
   }

   @Override
   public List<Component> method1() {
      ArrayList list1 = new ArrayList();
      list1.add(Component.text(this.method20(true)));
      Component component2 = Component.text("Class: ").color(NamedTextColor.GRAY);
      if (this.field16 == null) {
         list1.add(component2.append(Component.text("???")));
      } else {
         String text3 = this.field17 == -1 ? "" : " " + this.field17;
         list1.add(component2.append(Component.text(text3)));
      }

      String text4 = String.format("%,d", this.field12);
      list1.add(((TextComponent)Component.text("Secrets: ").color(NamedTextColor.GRAY)).append(Component.text(text4)));
      return list1;
   }

   public double method17() {
      double value1 = 10.0;
      return value1 * value1 / 4.0;
   }

   public boolean method18() {
      return Ref.method3().bridge$getSystemTime() - this.field21 < 2000L;
   }

   public boolean method19() {
      return this.field2.method29() == this;
   }

   public String method20(boolean flag1) {
      if (flag1 && this.method19()) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         return bridge5extension_52 != null ? bridge5extension_52.bridge$getName() : Ref.method3().bridge$getSession().bridge$getUsername();
      } else {
         return this.username == null ? "???" : this.username;
      }
   }

   public void method21(long number1) {
      this.field22 = number1;
      if (number1 != 0L) {
         double value3 = 0.0;
         double value5 = 0.0;
         int index7 = (int)(number1 - this.field2.method47()) / 100;
         double value8 = (number1 - this.field2.method47()) % 100.0 / 100.0;
         if (this.field6.size() == index7 + 1) {
            DungeonPlayerTracker.Data data10 = this.field6.get(index7);
            value3 = data10.field2;
            value5 = data10.field4;
         } else if (this.field6.size() > index7 + 1) {
            DungeonPlayerTracker.Data data12 = this.field6.get(index7);
            DungeonPlayerTracker.Data data11 = this.field6.get(index7 + 1);
            value3 = data12.field2 * (1.0 - value8) + data11.field2 * value8;
            value5 = data12.field4 * (1.0 - value8) + data11.field4 * value8;
         }

         if (this.field8 == null) {
            this.field8 = new WorldPosition(value3, value5, this.field2);
         } else {
            this.field8.method6(value3, 50L);
            this.field8.method7(value5, 50L);
         }
      } else {
         this.field8 = null;
      }
   }

   public void method22(StackedPlayersTooltip holograms2_41) {
      this.field3.add(holograms2_41);
      this.field15++;
      if (holograms2_41.method2()) {
         this.field14++;
      }
   }

   public boolean method23() {
      return this.field25 == NamedTextColor.YELLOW;
   }

   public boolean method24() {
      return this.field25 == NamedTextColor.RED && this.field24 > 0;
   }

   public boolean method25() {
      return this.field24 == 0;
   }

   public void method26() {
      this.deaths++;
      this.isDead = true;
   }

   public Optional<Bridge6_10> method27() {
      return this.field19 != null && !this.field19.bridge$isRemoved() ? Optional.of(this.field19) : Optional.empty();
   }

   public DungeonRoomTracker method28() {
      Bridge6_10 bridge6_101 = this.method27().orElse(null);
      return bridge6_101 != null
         ? this.field2.method14(new WorldPosition(bridge6_101.bridge$getPosX(), bridge6_101.bridge$getPosZ(), this.field2))
         : this.field2.method13(this.field4.method8(), this.field4.method9());
   }

   @Generated
   public ArrayList<StackedPlayersTooltip> method29() {
      return this.field3;
   }

   @Generated
   public WorldPosition method30() {
      return this.field4;
   }

   @Generated
   public void method31(PlayerInfoBridge bridge2_331) {
      this.field7 = bridge2_331;
   }

   @Generated
   public int getDeaths() {
      return this.deaths;
   }

   @Generated
   public int method33() {
      return this.field10;
   }

   @Generated
   public void method34(int number1) {
      this.field10 = number1;
   }

   @Generated
   public int method35() {
      return this.field14;
   }

   @Generated
   public int method36() {
      return this.field15;
   }

   @Nullable
   @Generated
   public DungeonClass method37() {
      return this.field16;
   }

   @Generated
   public int method38() {
      return this.field17;
   }

   @Nullable
   @Generated
   public UUID getUuid() {
      return this.uuid;
   }

   @Generated
   public boolean isDead() {
      return this.isDead;
   }

   @Generated
   public int method40() {
      return this.field24;
   }

   @Generated
   public void method41(int number1) {
      this.field24 = number1;
   }

   @Generated
   public NamedTextColor method42() {
      return this.field25;
   }

   @Generated
   public void method43(NamedTextColor namedtextcolor1) {
      this.field25 = namedtextcolor1;
   }

   @Generated
   public Component method44() {
      return this.field26;
   }

   private class Data {
      private final double field1;
      private final double field2;
      private final double field3;
      private final double field4;

      private Data(double value1, double value3, double value5, double value7) {
         this.field1 = value1;
         this.field2 = value3;
         this.field3 = value5;
         this.field4 = value7;
      }

      public double method1() {
         return this.field1;
      }

      public double method2() {
         return this.field2;
      }

      public double method3() {
         return this.field3;
      }

      public double method4() {
         return this.field4;
      }
   }
}
