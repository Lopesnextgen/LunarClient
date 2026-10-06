package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.lunarclient.minecraft.hypixel.HypixelPlayerUtil;
import com.lunarclient.player.PlayerResponse;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Gui2Extension7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms4_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_9;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data12;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump83;
import com.moonsworth.lunar.ichor.Annotation2;
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

@Annotation2(min = 33)
public class Holograms4Updater implements Holograms4_2 {
   public static final Pattern field1 = Pattern.compile(
      "^(?:§.)*\\[(?<classLetter>.)] (?<name>[\\w§]+) (?:§.)*?§(?<healthColor>.)(?<health>[\\d,]+)(§r)?(?:§c❤)?(§r)?$"
   );
   private final Holograms2_5 field2;
   private final ArrayList<Holograms2_4> field3 = new ArrayList<>();
   private final Nameplate4 field4;
   private final Click4 field5;
   private final ArrayList<Holograms4Updater.Data> field6 = new ArrayList<>(600);
   private Bridge2_33 field7;
   private Nameplate4 field8 = null;
   private long field9;
   private int deaths;
   private int field10;
   private volatile int field11;
   private volatile int field12;
   private volatile boolean field13;
   private int field14;
   private int field15;
   @Nullable
   private HologramsType2_2 field16;
   private int field17 = -1;
   @Nullable
   private UUID uuid;
   private String username;
   private boolean isDead;
   private boolean field18;
   private Bridge6_10 field19;
   private Holograms4Iterator field20;
   private long field21;
   private long field22 = 0L;
   private ResourceLocationBridge field23;
   private int field24 = -1;
   private NamedTextColor field25 = NamedTextColor.GREEN;
   private Component field26;

   public Holograms4Updater(Bridge2_33 var1, Holograms2_5 var2) {
      this(var1, var2, null, null);
   }

   public Holograms4Updater(Bridge2_33 var1, Holograms2_5 var2, @Nullable UUID var3, @Nullable ResourceLocationBridge var4) {
      this.field2 = var2;
      this.field7 = var1;
      this.uuid = var3;
      this.field23 = var4;
      this.field4 = new Nameplate4(100000.0, 100000.0, var2);
      this.field5 = new Click4(0.0, Click4.Type.LINEAR);
      this.field11 = -1;
      this.field12 = 1;
      this.field21 = ThreadModuleDump63.method3().bridge$getSystemTime();
   }

   public void tick() {
      this.method2();
      Holograms4Iterator var1 = this.method6();
      if (var1 != this.field20) {
         this.field21 = ThreadModuleDump63.method3().bridge$getSystemTime();
         if (var1 != null) {
            var1.method20(this);
         }
      }

      this.field20 = var1;
      int var2 = (int)(ThreadModuleDump63.method3().bridge$getSystemTime() - this.field2.method47()) / 100;
      Holograms4Updater.Data var3 = new Holograms4Updater.Data(
         this.getYaw(), this.method30().method2(), this.field19 == null ? 0.0 : this.field19.bridge$getPosY(), this.method30().method3()
      );

      while (this.field6.size() < var2) {
         this.field6.add(var3);
      }
   }

   private void method2() {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 != null && this.username != null) {
         if (this.method19()) {
            this.field19 = ThreadModuleDump63.method7();
         } else {
            this.field19 = var1.bridge$getPlayerByName(this.username).orElse(null);
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
         this.field9 = ThreadModuleDump63.method3().bridge$getSystemTime();
      }
   }

   public void method2(boolean var1) {
      if (this.uuid != null) {
         HypixelPlayerUtil.getPlayerAsync(this.uuid.toString()).thenAccept(var2 -> {
            if (var2 == null) {
               this.field13 = true;
            } else {
               Integer var3 = this.method3(var2);
               if (var3 == null) {
                  this.field13 = true;
               } else {
                  this.field13 = false;
                  if (var1) {
                     this.field11 = var3;
                  }

                  this.field12 = var3;
               }
            }
         });
      }
   }

   @Nullable
   private Integer method3(PlayerResponse var1) {
      return (Integer)var1.player().achievements().get("skyblock_treasure_hunter");
   }

   public int method4() {
      return this.field12 - this.field11;
   }

   public void method5(double var1, long var3) {
      double var5 = this.field5.getValue();
      double var7 = var1 - var5;
      if (var7 > 180.0) {
         this.field5.animateTo(var5 + 360.0, 0L);
      }

      if (var7 < -180.0) {
         this.field5.animateTo(var5 - 360.0, 0L);
      }

      this.field5.animateTo(var1, var3);
   }

   private Holograms4Iterator method6() {
      return this.field2.method13(this.field4.method8(), this.field4.method9());
   }

   public Holograms4Iterator method7() {
      return this.field20;
   }

   public Nameplate4 method8() {
      return this.field8 != null ? this.field8 : this.field4;
   }

   public void method9(String var1, String var2, String var3) {
      if (this.username == null || !this.username.equals(var1)) {
         this.field19 = null;
      }

      this.username = var1;
      if (var2 != null && !var2.equals("EMPTY")) {
         this.isDead = var2.equals("DEAD");
         if (!this.isDead) {
            HologramsType2_2 var4 = this.field16;
            this.field16 = HologramsType2_2.fromDisplayName(var2);
            if (this.field16 != null) {
               this.field26 = Component.text(var1).color(this.field16.getColor());
            }

            if (var3 != null) {
               this.field17 = var3.matches("\\d+") ? Integer.parseInt(var3) : ThreadModuleDump83.parseRoman(var3);
            }

            if (var4 != this.field16) {
               ClientEventBus.method29().method12(Rewindhandlers$Data12.class, () -> new Rewindhandlers$Data12(this, this.field16));
            }
         }
      }
   }

   public void method10(MixinHelper_4 var1, Holograms_9 var2, float var3, float var4, Holograms3_2 var5, boolean var6) {
      this.method11(var1, var2, var3, var4, var5, var6, false);
   }

   public void method11(MixinHelper_4 var1, Holograms_9 var2, float var3, float var4, Holograms3_2 var5, boolean var6, boolean var7) {
      if (!this.isDead && var2.method36() != Gui2Extension7.OFF) {
         float var8 = var2.method37();
         float var9 = var2.method38();
         if (var2.method39() == Gui2Extension7.ICONS || var2.method39() == Gui2Extension7.SELF_ICON && this.method19()) {
            var5.method20(
               var1, HologramsType2.VANILLA_MAP_PLAYER, var3, var4, var8 * 5.0F / 2.0F, var8 * 7.0F / 2.0F, this.getYaw(), this.method19() ? -16711936 : -1
            );
         } else {
            var1.push();
            var1.method38(var3 * var5.field8, var4 * var5.field8, 0.0F);
            var1.method42(this.method14());
            var1.method38(-var3 * var5.field8, -var4 * var5.field8, 0.0F);
            if (var2.method40() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Gui2Extension.NONE) {
               Nameplate2.method5(var1);
               var5.method17(var1, var3 - var8 - var9, var4 - var8 - var9, var8 * 2.0F + var9 * 2.0F, var8 * 2.0F + var9 * 2.0F, var2.method27(this));
               Nameplate2.method6(var1);
            }

            ResourceLocationBridge var10 = this.method12();
            var1.method38(var3 * var5.field8, var4 * var5.field8, 0.0F);
            var1.scale(var8 * 2.0F / 8.0F * var5.field8, var8 * 2.0F / 8.0F * var5.field8, 1.0F);
            if (var10 != null) {
               LcuiScreen.method50(var1, var10, -4.0F, -4.0F, -1, this.field18);
            }

            var1.pop();
         }

         if (var6 && var2.method21()) {
            var1.push();
            var1.method38(var3 * var5.field8, var4 * var5.field8, 0.0F);
            if (var2.method33()) {
               var1.method42(this.field2.method29().method14());
            }

            var5.method11(var1, (var7 ? AdventureChatFormatting.RED : "") + this.method20(true), 0.0F, var8 * 1.5F + var9 * 1.5F, 0.5F, Nameplate.Type.BORDER);
            var1.pop();
         }
      }
   }

   @Nullable
   private ResourceLocationBridge method12() {
      return this.field23 == null && this.field7 != null ? this.field7.bridge$getLocationSkin() : this.field23;
   }

   public double method13() {
      if (this.field22 != 0L) {
         int var1 = (int)(this.field22 - this.field2.method47()) / 100;
         double var2 = (this.field22 - this.field2.method47()) % 100.0 / 100.0;
         if (this.field6.size() <= var1) {
            return 0.0;
         } else {
            return this.field6.size() == var1 + 1
               ? this.field6.get(var1).field3
               : this.field6.get(var1).field3 * (1.0 - var2) + this.field6.get(var1 + 1).field3 * var2;
         }
      } else {
         return this.field19 != null ? this.field19.bridge$getPosY() : 0.0;
      }
   }

   public float method14() {
      if (this.field22 != 0L) {
         int var1 = (int)(this.field22 - this.field2.method47()) / 100;
         double var2 = (this.field22 - this.field2.method47()) % 100.0 / 100.0;
         if (this.field6.size() <= var1) {
            return 0.0F;
         } else {
            return this.field6.size() == var1 + 1
               ? (float)this.field6.get(var1).field1
               : (float)(this.field6.get(var1).field1 * (1.0 - var2) + this.field6.get(var1 + 1).field1 * var2);
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
      ArrayList var1 = new ArrayList();
      var1.add(Component.text(this.method20(true)));
      Component var2 = Component.text("Class: ").color(NamedTextColor.GRAY);
      if (this.field16 == null) {
         var1.add(var2.append(Component.text("???")));
      } else {
         String var3 = this.field17 == -1 ? "" : " " + this.field17;
         var1.add(var2.append(Component.text(var3)));
      }

      String var4 = String.format("%,d", this.field12);
      var1.add(((TextComponent)Component.text("Secrets: ").color(NamedTextColor.GRAY)).append(Component.text(var4)));
      return var1;
   }

   public double method17() {
      double var1 = 10.0;
      return var1 * var1 / 4.0;
   }

   public boolean method18() {
      return ThreadModuleDump63.method3().bridge$getSystemTime() - this.field21 < 2000L;
   }

   public boolean method19() {
      return this.field2.method29() == this;
   }

   public String method20(boolean var1) {
      if (var1 && this.method19()) {
         Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
         return var2 != null ? var2.bridge$getName() : ThreadModuleDump63.method3().bridge$getSession().bridge$getUsername();
      } else {
         return this.username == null ? "???" : this.username;
      }
   }

   public void method21(long var1) {
      this.field22 = var1;
      if (var1 != 0L) {
         double var3 = 0.0;
         double var5 = 0.0;
         int var7 = (int)(var1 - this.field2.method47()) / 100;
         double var8 = (var1 - this.field2.method47()) % 100.0 / 100.0;
         if (this.field6.size() == var7 + 1) {
            Holograms4Updater.Data var10 = this.field6.get(var7);
            var3 = var10.field2;
            var5 = var10.field4;
         } else if (this.field6.size() > var7 + 1) {
            Holograms4Updater.Data var12 = this.field6.get(var7);
            Holograms4Updater.Data var11 = this.field6.get(var7 + 1);
            var3 = var12.field2 * (1.0 - var8) + var11.field2 * var8;
            var5 = var12.field4 * (1.0 - var8) + var11.field4 * var8;
         }

         if (this.field8 == null) {
            this.field8 = new Nameplate4(var3, var5, this.field2);
         } else {
            this.field8.method6(var3, 50L);
            this.field8.method7(var5, 50L);
         }
      } else {
         this.field8 = null;
      }
   }

   public void method22(Holograms2_4 var1) {
      this.field3.add(var1);
      this.field15++;
      if (var1.method2()) {
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

   public Holograms4Iterator method28() {
      Bridge6_10 var1 = this.method27().orElse(null);
      return var1 != null
         ? this.field2.method14(new Nameplate4(var1.bridge$getPosX(), var1.bridge$getPosZ(), this.field2))
         : this.field2.method13(this.field4.method8(), this.field4.method9());
   }

   @Generated
   public ArrayList<Holograms2_4> method29() {
      return this.field3;
   }

   @Generated
   public Nameplate4 method30() {
      return this.field4;
   }

   @Generated
   public void method31(Bridge2_33 var1) {
      this.field7 = var1;
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
   public void method34(int var1) {
      this.field10 = var1;
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
   public HologramsType2_2 method37() {
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
   public void method41(int var1) {
      this.field24 = var1;
   }

   @Generated
   public NamedTextColor method42() {
      return this.field25;
   }

   @Generated
   public void method43(NamedTextColor var1) {
      this.field25 = var1;
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

      private Data(double var1, double var3, double var5, double var7) {
         this.field1 = var1;
         this.field2 = var3;
         this.field3 = var5;
         this.field4 = var7;
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
