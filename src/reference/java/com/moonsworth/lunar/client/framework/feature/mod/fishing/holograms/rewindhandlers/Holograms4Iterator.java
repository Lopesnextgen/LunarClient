package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3Impl;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3Impl2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3Impl3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3Impl4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3Impl5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3Impl6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3Impl7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms4_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class Holograms4Iterator implements Holograms4_2 {
   private static final Pattern field1 = Pattern.compile("(-?\\d+),(-?\\d+)");
   private List<Holograms3_5> field2 = new ArrayList<>();
   private int field3 = -1;
   private final Holograms2_5 field4;
   private final List<Nameplate4> field5 = new ArrayList<>();
   private final List<Rewindhandlers> field6 = new ArrayList<>();
   private Holograms3 field7;
   private final Holograms2_3 field8 = new Holograms2_3();
   private Holograms4 field9 = null;
   private long field10 = -1L;

   public Holograms4Iterator(Holograms2_5 var1, HologramsType5 var2) {
      this.field4 = var1;
      this.method16(var2);
   }

   public void method1(Holograms3 var1) {
      if (this.field7 != var1) {
         this.field2.add(new Holograms3Impl5(this.field7, var1));
         this.field7 = var1;
         if (var1.method26() != null) {
            this.field8.method18(var1.method26().secrets());
            this.field2.add(new Holograms3Impl6(this.field8.getDisplayName(), var1.method26().communityName()));
            this.field8.setDisplayName(var1.method26().communityName());
            int var2 = this.method14();
            int var3 = this.field8.method1();
            int var4 = var1.method26().getSecretCount();
            this.field8.method17(var4);
            if (this.field8.method2() == HologramsType2.COMPLETED) {
               this.field8.method10(this.field8.method8());
            }

            this.field2.add(new Holograms3Impl(var2, this.method14(), var3, this.field8.method1()));
         }
      }
   }

   public void method2(Holograms3 var1) {
      this.field7 = var1;
      if (var1 != null && var1.method26() != null) {
         this.field8.method18(var1.method26().secrets());
      }
   }

   public void method3(Nameplate4 var1) {
      this.field5.add(var1);
   }

   public void method4() {
      this.field5.clear();
   }

   public void method5(Nameplate4 var1) {
      this.field2.add(new Holograms3Impl4(var1));
      this.field5.add(var1);
      this.field4.method20(this, var1);
   }

   public void method6(Nameplate4 var1) {
      this.field2.add(new Holograms3Impl7(var1));
      this.method28().remove(var1);
      this.field4.method21(var1);
   }

   public List<Rewindhandlers> method7() {
      return this.field4.method19().stream().filter(var1 -> this.method24(var1.method6()) || this.method24(var1.method7())).toList();
   }

   public List<Holograms4Updater> method8() {
      ArrayList var1 = new ArrayList();

      for (Holograms4Updater var3 : this.field4.getPlayers()) {
         Holograms4Iterator var4 = var3.method7();
         if (this.equals(var4)) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List<Component> method1() {
      ArrayList var1 = new ArrayList();
      NamedTextColor var2 = NamedTextColor.WHITE;
      if (this.field8.method1() == this.method14()) {
         var2 = NamedTextColor.GREEN;
      }

      var1.add(Component.text(this.method11(false)));
      var1.add(
         ((TextComponent)((TextComponent)((TextComponent)Component.text("Type: ").color(NamedTextColor.GRAY))
                  .append(Component.text(this.field8.method6().toString())))
               .append(Component.text(" ")))
            .append(Component.text(this.method12().name))
      );
      if (this.method14() != 0) {
         if (this.method14() == -1) {
            var1.add(Component.text("Secrets: ").append(Component.text("? / ?").color(NamedTextColor.GRAY)));
         } else {
            var1.add(
               ((TextComponent)((TextComponent)Component.text("Secrets: ").append(Component.text(this.field8.method1()).color(var2)))
                     .append(Component.text(" / ").color(NamedTextColor.GRAY)))
                  .append(Component.text(this.method14()).color(var2))
            );
         }
      }

      if (this.field8.method4() != null) {
         String var3 = String.format("%.2fs", this.field8.method5());
         var1.add(
            ((TextComponent)((TextComponent)Component.text("Cleared by: ").append(Component.text(this.field8.method4()).color(NamedTextColor.YELLOW)))
                  .append(Component.text(" in ").color(NamedTextColor.GRAY)))
               .append(Component.text(var3).color(NamedTextColor.YELLOW))
         );
      }

      if (this.field8.method3() != null) {
         var1.add(Component.text("Last in room: ").append(Component.text(this.field8.method3().method20(true)).color(NamedTextColor.YELLOW)));
      }

      return var1;
   }

   public void method10(HologramsType2 var1) {
      if (var1 != this.field8.method2()) {
         if (var1 == HologramsType2.OPENED) {
            this.field10 = ThreadModuleDump63.method3().bridge$getSystemTime();
         }

         if (var1 == HologramsType2.COMPLETED && this.field8.method7() != -1) {
            this.field2
               .add(
                  new Holograms3Impl(
                     Math.max(this.field8.method8(), this.field8.method7()),
                     Math.max(this.field8.method8(), this.field8.method7()),
                     this.field8.method1(),
                     this.field8.method7()
                  )
               );
            this.field8.method10(this.field8.method7());
         }

         if ((var1 == HologramsType2.COMPLETED || var1 == HologramsType2.CLEARED && this.field8.method6() != HologramsType5.BLOOD) && this.field10 > 0L) {
            double var2 = (ThreadModuleDump63.method3().bridge$getSystemTime() - this.field10) / 1000.0;
            if (this.field8.method3() != null) {
               String var4 = this.field8.method3().method20(true);
               List var5 = this.method8();
               if (!var5.isEmpty()) {
                  StringBuilder var6 = new StringBuilder();
                  boolean var7 = true;

                  for (Holograms4Updater var9 : var5) {
                     if (!var7) {
                        var6.append(", ");
                     }

                     var6.append(var9.method20(true));
                     var7 = false;
                  }

                  var4 = var6.toString();
               }

               this.field8.method13(var4);
               this.field8.method14(var2);
            }

            this.field10 = -1L;
         }

         if ((this.field8.method2() == HologramsType2.OPENED || this.field8.method2() == HologramsType2.ADJACENT)
            && (var1 == HologramsType2.CLEARED || var1 == HologramsType2.COMPLETED)
            && this.field8.method6() != HologramsType5.FAIRY
            && this.field8.method6() != HologramsType5.SPAWN) {
            List var10 = this.method8();
            Holograms2_4 var3 = new Holograms2_4(var10, this);

            for (Holograms4Updater var12 : var10) {
               var12.method22(var3);
            }
         }

         this.field2.add(new Holograms3Impl2(this.field8.method2(), var1));
         this.field8.method11(var1);
      }
   }

   public String method11(boolean var1) {
      if (this.field8.method6() == HologramsType5.MINIBOSS) {
         return "Miniboss";
      } else if (this.field8.method6() == HologramsType5.BLOOD) {
         return "Blood";
      } else if (this.field8.method6() == HologramsType5.FAIRY) {
         return "Fairy";
      } else if (this.field8.method6() == HologramsType5.SPAWN) {
         return "Spawn";
      } else {
         String var2 = this.field8.getDisplayName();
         if (var2 == null && !var1) {
            return this.field8.method6() != HologramsType5.CLEAR
               ? "Unknown (" + this.field8.method6().toString() + ")"
               : "Unknown (" + this.method12().name + ")";
         } else {
            return var2;
         }
      }
   }

   public HologramsType method12() {
      if (this.method28().size() == 1) {
         return HologramsType.ONE_BY_ONE;
      }

      if (this.method28().size() == 2) {
         return HologramsType.ONE_BY_TWO;
      }

      HashSet var1 = new HashSet();

      for (Nameplate4 var3 : this.method28()) {
         var1.add(var3.method8());
      }

      if (this.method28().size() == 3) {
         return var1.size() != 3 && var1.size() != 1 ? HologramsType.L_SHAPE : HologramsType.ONE_BY_THREE;
      } else if (this.method28().size() == 4) {
         return var1.size() != 4 && var1.size() != 1 ? HologramsType.TWO_BY_TWO : HologramsType.ONE_BY_FOUR;
      } else {
         return HologramsType.NONE;
      }
   }

   public void method13(int var1) {
      if (var1 != this.field8.method1()) {
         this.field2
            .add(
               new Holograms3Impl(
                  Math.max(this.field8.method8(), this.field8.method7()), Math.max(this.field8.method8(), this.field8.method7()), this.field8.method1(), var1
               )
            );
         this.field8.method10(var1);
      }
   }

   public int method14() {
      return this.field8.method8() == -1 ? this.field8.method7() : this.field8.method8();
   }

   public void method15(int var1) {
      if (var1 != this.field8.method7()) {
         this.field2
            .add(
               new Holograms3Impl(
                  Math.max(this.field8.method8(), this.field8.method7()), Math.max(this.field8.method8(), var1), this.field8.method1(), this.field8.method1()
               )
            );
         this.field8.method16(var1);
         if (this.field8.method2() == HologramsType2.COMPLETED) {
            this.field2
               .add(
                  new Holograms3Impl(
                     Math.max(this.field8.method8(), this.field8.method7()),
                     Math.max(this.field8.method8(), this.field8.method7()),
                     this.field8.method1(),
                     this.field8.method7()
                  )
               );
            this.field8.method10(this.field8.method7());
         }
      }
   }

   public void method16(HologramsType5 var1) {
      if (this.field8.method6() != var1) {
         if (this.field8.method2() != HologramsType2.ADJACENT) {
            this.field10 = ThreadModuleDump63.method3().bridge$getSystemTime();
         }

         this.field2.add(new Holograms3Impl3(this.field8.method6(), var1));
         this.field8.method15(var1);
         if (!var1.hasSecrets()) {
            this.field8.method10(0);
            this.field8.method16(0);
            this.field8.method17(0);
         }
      }
   }

   public void method17() {
      for (Nameplate4 var2 : this.method28()) {
         this.field4.method21(var2);
         this.field2.add(new Holograms3Impl7(var2));
      }

      this.field5.clear();
   }

   public boolean method18() {
      return this.field9 != null;
   }

   public void method19(Holograms4 var1) {
      this.field9 = var1;
      this.field2.add(new Holograms3Impl6(this.field8.getDisplayName(), var1.getName()));
      this.field8.setDisplayName(var1.getName());
   }

   public void method20(Holograms4Updater var1) {
      this.field8.method12(var1);
   }

   public void method21(long var1) {
      while (this.method22(var1)) {
      }
   }

   private boolean method22(long var1) {
      if (this.field3 >= 0) {
         Holograms3_5 var3 = this.field2.get(this.field3);
         if (var3.getTimestamp() > var1) {
            this.field3--;
            var3.method2(this);
            return true;
         }
      }

      if (this.field2.size() <= this.field3 + 1) {
         return false;
      } else {
         Holograms3_5 var4 = this.field2.get(this.field3 + 1);
         if (var4.getTimestamp() < var1) {
            this.field3++;
            var4.method1(this);
            return true;
         } else {
            return false;
         }
      }
   }

   public Optional<Holograms3> method23() {
      return Optional.ofNullable(this.field7);
   }

   public boolean contains(double var1, double var3) {
      return this.method24(new Nameplate4(var1, var3, this.field4));
   }

   public boolean method24(Nameplate4 var1) {
      for (Nameplate4 var3 : this.method28()) {
         if (var1.method8() == var3.method8() && var1.method9() == var3.method9()) {
            return true;
         }
      }

      return false;
   }

   public HologramsType8 method25() {
      if (this.method30().method6() != HologramsType5.PUZZLE) {
         return null;
      } else {
         Holograms3 var1 = this.method23().orElse(null);
         if (var1 != null && var1.method26() != null) {
            Matcher var2 = field1.matcher(var1.method26().ids()[0]);
            return var2.find() ? HologramsType8.getByCoords(Integer.parseInt(var2.group(1)), Integer.parseInt(var2.group(2))) : null;
         } else {
            return null;
         }
      }
   }

   @Generated
   public List<Holograms3_5> getEvents() {
      return this.field2;
   }

   @Generated
   public void method26(List<Holograms3_5> var1) {
      this.field2 = var1;
   }

   @Generated
   public Holograms2_5 method27() {
      return this.field4;
   }

   @Generated
   public List<Nameplate4> method28() {
      return this.field5;
   }

   @Generated
   public List<Rewindhandlers> method29() {
      return this.field6;
   }

   @Generated
   public Holograms2_3 method30() {
      return this.field8;
   }
}
