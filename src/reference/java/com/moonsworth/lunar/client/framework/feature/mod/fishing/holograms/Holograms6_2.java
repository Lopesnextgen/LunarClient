package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType2;
import com.moonsworth.lunar.ichor.util.Annotation2;
import java.util.Objects;
import lombok.Generated;

@Annotation2
public class Holograms6_2 {
   public static final Holograms6_2 NONE = new Holograms6_2(false, false, NameplateType2.NONE, false);
   private final boolean aote;
   private final boolean etherwarp;
   private final NameplateType2 pickaxeTier;
   private final boolean pearls;

   public Holograms6_2(boolean var1, boolean var2, boolean var3) {
      this(var1, var2, NameplateType2.NONE, var3);
   }

   public static Holograms6_2 max(Holograms6_2 holograms6_2, Holograms6_2 var1) {
      if (holograms6_2.pearls != var1.pearls) {
         return holograms6_2.pearls ? holograms6_2 : var1;
      } else if (holograms6_2.etherwarp != var1.etherwarp) {
         return holograms6_2.etherwarp ? holograms6_2 : var1;
      } else if (holograms6_2.aote != var1.aote) {
         return holograms6_2.aote ? holograms6_2 : var1;
      } else if (holograms6_2.getPickaxeTier().ordinal() != var1.getPickaxeTier().ordinal()) {
         return holograms6_2.getPickaxeTier().ordinal() > var1.getPickaxeTier().ordinal() ? holograms6_2 : var1;
      } else {
         return holograms6_2;
      }
   }

   public boolean isSubsetOf(Holograms6_2 var1) {
      if (this.pearls && !var1.pearls) {
         return false;
      } else {
         return this.aote && !var1.aote ? false : !this.etherwarp || var1.etherwarp;
      }
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof Holograms6_2 var2)
         ? false
         : var2.etherwarp == this.etherwarp && var2.aote == this.aote && var2.getPickaxeTier() == this.getPickaxeTier();
   }

   public Holograms6_2 setPickaxeTier(NameplateType2 var1) {
      return new Holograms6_2(this.aote, this.etherwarp, var1, this.pearls);
   }

   public Holograms6_2 setEtherwarpNeeded(boolean var1) {
      return new Holograms6_2(this.aote, var1, this.getPickaxeTier(), this.pearls);
   }

   public Holograms6_2 setAoteNeeded(boolean var1) {
      return new Holograms6_2(var1, this.etherwarp, this.getPickaxeTier(), this.pearls);
   }

   public Holograms6_2 setPearls(boolean var1) {
      return new Holograms6_2(this.aote, this.etherwarp, this.getPickaxeTier(), var1);
   }

   public boolean needsEtherwarp() {
      return this.etherwarp;
   }

   public boolean needsAote() {
      return this.aote;
   }

   public NameplateType2 getPickaxeTier() {
      return Objects.requireNonNullElse(this.pickaxeTier, NameplateType2.NONE);
   }

   @Generated
   public Holograms6_2(boolean var1, boolean var2, NameplateType2 var3, boolean flag) {
      this.aote = var1;
      this.etherwarp = var2;
      this.pickaxeTier = var3;
      this.pearls = flag;
   }

   @Generated
   public boolean isPearls() {
      return this.pearls;
   }
}
