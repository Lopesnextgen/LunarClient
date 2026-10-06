package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.PickaxeTier;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.Objects;
import lombok.Generated;

@KeepName
public class RouteConditions {
   public static final RouteConditions NONE = new RouteConditions(false, false, PickaxeTier.NONE, false);
   private final boolean aote;
   private final boolean etherwarp;
   private final PickaxeTier pickaxeTier;
   private final boolean pearls;

   public RouteConditions(boolean flag1, boolean flag2, boolean flag) {
      this(flag1, flag2, PickaxeTier.NONE, flag);
   }

   public static RouteConditions max(RouteConditions holograms6_20, RouteConditions holograms6_21) {
      if (holograms6_20.pearls != holograms6_21.pearls) {
         return holograms6_20.pearls ? holograms6_20 : holograms6_21;
      } else if (holograms6_20.etherwarp != holograms6_21.etherwarp) {
         return holograms6_20.etherwarp ? holograms6_20 : holograms6_21;
      } else if (holograms6_20.aote != holograms6_21.aote) {
         return holograms6_20.aote ? holograms6_20 : holograms6_21;
      } else if (holograms6_20.getPickaxeTier().ordinal() != holograms6_21.getPickaxeTier().ordinal()) {
         return holograms6_20.getPickaxeTier().ordinal() > holograms6_21.getPickaxeTier().ordinal() ? holograms6_20 : holograms6_21;
      } else {
         return holograms6_20;
      }
   }

   public boolean isSubsetOf(RouteConditions holograms6_21) {
      if (this.pearls && !holograms6_21.pearls) {
         return false;
      } else {
         return this.aote && !holograms6_21.aote ? false : !this.etherwarp || holograms6_21.etherwarp;
      }
   }

   @Override
   public boolean equals(Object object) {
      return !(object instanceof RouteConditions holograms6_22)
         ? false
         : holograms6_22.etherwarp == this.etherwarp && holograms6_22.aote == this.aote && holograms6_22.getPickaxeTier() == this.getPickaxeTier();
   }

   public RouteConditions setPickaxeTier(PickaxeTier pickaxeTier) {
      return new RouteConditions(this.aote, this.etherwarp, pickaxeTier, this.pearls);
   }

   public RouteConditions setEtherwarpNeeded(boolean flag1) {
      return new RouteConditions(this.aote, flag1, this.getPickaxeTier(), this.pearls);
   }

   public RouteConditions setAoteNeeded(boolean flag1) {
      return new RouteConditions(flag1, this.etherwarp, this.getPickaxeTier(), this.pearls);
   }

   public RouteConditions setPearls(boolean flag1) {
      return new RouteConditions(this.aote, this.etherwarp, this.getPickaxeTier(), flag1);
   }

   public boolean needsEtherwarp() {
      return this.etherwarp;
   }

   public boolean needsAote() {
      return this.aote;
   }

   public PickaxeTier getPickaxeTier() {
      return Objects.requireNonNullElse(this.pickaxeTier, PickaxeTier.DUNGEONBREAKER);
   }

   @Generated
   public RouteConditions(boolean flag1, boolean flag2, PickaxeTier pickaxeTier2, boolean flag) {
      this.aote = flag1;
      this.etherwarp = flag2;
      this.pickaxeTier = pickaxeTier2;
      this.pearls = flag;
   }

   @Generated
   public boolean isPearls() {
      return this.pearls;
   }
}
