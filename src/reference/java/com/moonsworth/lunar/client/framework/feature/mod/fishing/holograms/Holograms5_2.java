package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

@Annotation2(min = 33)
public class Holograms5_2 {
   private final Holograms_6 field1;
   protected final Holograms3_2 field2;

   public Holograms5_2(Holograms3_2 var1, Holograms_6 var2) {
      this.field1 = var2;
      this.field2 = var1;
   }

   public void method1(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      Component var7;
      Component var8;
      Component var9;
      if (var3.method39()) {
         var7 = this.method8(279, false);
         var8 = this.method4(4, true, false);
         var9 = this.method5(true, var2.method44());
      } else {
         var7 = this.method8(this.field1.method2(), false);
         var8 = this.method4(this.field1.method10(), this.field1.method13(), false);
         var9 = this.method5(this.field1.method12(), var2.method44());
      }

      boolean var10 = var3.method34() >= 6;
      this.field2.method12(var1, var7, var4 + (var10 ? 20 : 25), var5 + 6.5F, 1.0F, Nameplate.Type.SHADOW);
      this.field2.method12(var1, var8, var4 + (var10 ? 43 : 75), var5 + 6.5F, 1.0F, Nameplate.Type.SHADOW);
      if (var10) {
         this.field2.method12(var1, var9, var4 + 75.0F, var5 + 6.5F, 1.0F, Nameplate.Type.SHADOW);
      }

      if (var6.method13() >= var5 && var6.method13() <= var5 + 15.0F && var6.method12() >= var4 && var6.method12() <= var4 + 100.0F) {
         this.field2.method26(this.method9(var3));
      }
   }

   public void method2(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      TextComponent var9 = Component.text("    ");
      Component var7;
      Component var8;
      if (var3.method39()) {
         Component var10 = this.method3(20, 9, 29);
         Component var11 = this.method4(4, true, true);
         Component var12 = this.method5(true, true);
         Component var13 = this.method6(26, false);
         Component var14 = this.method7(0);
         Component var15 = this.method8(279, true);
         var7 = var10.append(var9).append(var11).append(var9).append(var12);
         var8 = var13.append(var9).append(var14).append(var9).append(var15);
      } else {
         int var17 = this.field1.method11() * 2;
         if (this.field1.method14()) {
            var17--;
         }

         Component var18 = this.method3(this.field1.method7(), this.field1.method8() - this.field1.method7(), this.field1.method8());
         Component var19 = this.method4(this.field1.method10(), this.field1.method13(), true);
         Object var20 = var3.method34() >= 6 ? this.method5(this.field1.method12(), true) : Component.empty();
         Component var21 = this.field1.method7() == 0 ? this.method6(-1, false) : this.method6(this.field1.method9(), this.field1.method2() >= 300);
         Component var22 = this.method7(var17);
         Component var16 = this.method8(this.field1.method2(), true);
         var7 = var18.append(var9).append(var19).append(var9).append((Component)var20);
         var8 = var21.append(var9).append(var22).append(var9).append(var16);
      }

      this.field2.method12(var1, var7, var4 + 50.0F, var5 + 5.0F, 0.4F, Nameplate.Type.SHADOW);
      this.field2.method12(var1, var8, var4 + 50.0F, var5 + 10.0F, 0.4F, Nameplate.Type.SHADOW);
      if (var6.method13() >= var5 && var6.method13() <= var5 + 15.0F && var6.method12() >= var4 && var6.method12() <= var4 + 100.0F) {
         this.field2.method26(this.method9(var3));
      }
   }

   private Component method3(int var1, int var2, int var3) {
      return var1 == 0
         ? ((TextComponent)Component.text("Secrets: ").color(NamedTextColor.GRAY)).append(Component.text("?").color(NamedTextColor.AQUA))
         : ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text("Secrets: ").color(NamedTextColor.GRAY))
                        .append(Component.text(var1).color(NamedTextColor.AQUA)))
                     .append(Component.text("-").color(NamedTextColor.DARK_GRAY)))
                  .append(Component.text(var2).color(NamedTextColor.YELLOW)))
               .append(Component.text("-").color(NamedTextColor.GRAY)))
            .append(Component.text(var3).color(NamedTextColor.RED));
   }

   private Component method4(int var1, boolean var2, boolean var3) {
      NamedTextColor var4;
      if (var1 >= 5) {
         var4 = NamedTextColor.GREEN;
      } else if (var1 >= 1) {
         var4 = NamedTextColor.YELLOW;
      } else {
         var4 = NamedTextColor.RED;
      }

      Component var5 = var3
         ? ((TextComponent)Component.text("Crypts: ").color(NamedTextColor.GRAY)).append(Component.text(var1).color(var4))
         : ((TextComponent)Component.text(var1).color(var4)).append(Component.text("c"));
      return var2 ? var5.append(Component.text('♕').color(NamedTextColor.GOLD)) : var5;
   }

   private Component method5(boolean var1, boolean var2) {
      Component var3 = Component.text("Mimic: ").color(NamedTextColor.GRAY);
      Component var4 = var1 ? Component.text('✔').color(NamedTextColor.GREEN) : Component.text('✖').color(NamedTextColor.RED);
      return var2 ? var3.append(var4) : var4;
   }

   private Component method6(int var1, boolean var2) {
      Component var3 = Component.text("Min Secrets: ").color(NamedTextColor.GRAY);
      if (var1 == -1) {
         return var3.append(Component.text("?").color(NamedTextColor.AQUA));
      }

      NamedTextColor var4 = var2 ? NamedTextColor.GREEN : NamedTextColor.YELLOW;
      return var3.append(Component.text(var1).color(var4));
   }

   private Component method7(int var1) {
      Component var2 = Component.text("Deaths: ").color(NamedTextColor.GRAY);
      return var1 > 0
         ? var2.append(Component.text("-").color(NamedTextColor.RED)).append(Component.text(var1).color(NamedTextColor.RED))
         : var2.append(Component.text(var1).color(NamedTextColor.GREEN));
   }

   private Component method8(int var1, boolean var2) {
      NamedTextColor var3;
      if (var1 >= 300) {
         var3 = NamedTextColor.GREEN;
      } else if (var1 >= 270) {
         var3 = NamedTextColor.YELLOW;
      } else {
         var3 = NamedTextColor.RED;
      }

      Component var4 = Component.text(var1).color(var3);
      return var2 ? ((TextComponent)Component.text("Score: ").color(NamedTextColor.GRAY)).append(var4) : var4;
   }

   private List<Component> method9(Holograms2_5 var1) {
      if (var1.method39()) {
         return this.method10(var1, 279, 100, 84, 84, 7, 20, 9, 29, 4, true, true, 26, 0);
      }

      int var2 = this.field1.method11() * 2;
      if (this.field1.method14()) {
         var2--;
      }

      return this.method10(
         var1,
         this.field1.method2(),
         this.field1.method3(),
         this.field1.method4(),
         this.field1.method5(),
         this.field1.method6(),
         this.field1.method7(),
         this.field1.method8() - this.field1.method7(),
         this.field1.method8(),
         this.field1.method10(),
         this.field1.method12(),
         this.field1.method13(),
         this.field1.method9(),
         var2
      );
   }

   private List<Component> method10(
      Holograms2_5 var1,
      int var2,
      int var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      int var10,
      boolean var11,
      boolean var12,
      int var13,
      int var14
   ) {
      Component var15 = Component.text("?").color(NamedTextColor.AQUA);
      ArrayList var16 = new ArrayList();
      var16.add(Component.text("Total Score: ").append(Component.text(var2).color(NamedTextColor.AQUA)));
      var16.add(Component.text("Skill Score: ").append(Component.text(var3).color(NamedTextColor.GREEN)));
      var16.add(Component.text("Exploration Score: ").append(Component.text(var4).color(NamedTextColor.GREEN)));
      var16.add(Component.text("Time Score: ").append(Component.text(var5).color(NamedTextColor.GREEN)));
      var16.add(Component.text("Bonus Score: ").append(Component.text(var6).color(NamedTextColor.GREEN)));
      var16.add(Component.empty());
      var16.add(Component.text("Secrets Found: ").append(Component.text(var7).color(NamedTextColor.AQUA)));
      TextComponent var17 = Component.text("Secrets Left: ");
      TextComponent var18 = Component.text("Total Secrets: ");
      if (var7 == 0) {
         var16.add(var17.append(var15));
         var16.add(var18.append(var15));
      } else {
         var16.add(var17.append(Component.text(var8).color(NamedTextColor.YELLOW)));
         var16.add(var18.append(Component.text(var9).color(NamedTextColor.RED)));
      }

      var16.add(Component.empty());
      var16.add(Component.text("Crypts: ").append(Component.text(var10).color(NamedTextColor.GREEN)));
      if (var1.method34() >= 6) {
         Component var19 = var11 ? Component.text('✔').color(NamedTextColor.GREEN) : Component.text('✖').color(NamedTextColor.RED);
         var16.add(Component.text("Mimic: ").append(var19));
      }

      if (var12) {
         String var20 = ThreadModuleDump63.method4().method67().method2("features.SKYBLOCK.info", "dungeonPrince", new Object[0]);
         var16.add(Component.text(var20 + ": ").append(Component.text('✔').color(NamedTextColor.GREEN)));
      }

      var16.add(Component.empty());
      TextComponent var21 = Component.text("Min Secrets (S+): ");
      if (var7 == 0) {
         var16.add(var21.append(var15));
      } else {
         var16.add(var21.append(Component.text(var13).color(NamedTextColor.GREEN)));
      }

      var16.add(Component.text("Death Penalty: ").append(Component.text(var14).color(NamedTextColor.RED)));
      return var16;
   }
}
