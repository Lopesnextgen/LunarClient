package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Generated;

@KeepName
public class RouteSegment {
   public static final RouteSegment EMPTY = new RouteSegment();
   public String swapOnLocked;
   public List<TextHologram> textHolograms;
   private transient String swapOnLockedCache;
   private transient List<RouteLink> swapOnLockedCacheList;

   public RouteSegment(RouteSegment holograms10_21) {
      this.swapOnLocked = holograms10_21.swapOnLocked;
      this.textHolograms = holograms10_21.textHolograms.stream().map(TextHologram::new).collect(Collectors.toList());
   }

   public List<RouteLink> getSwapOnLocked() {
      if (!Objects.equals(this.swapOnLockedCache, this.swapOnLocked)) {
         this.swapOnLockedCacheList = RouteLink.method1(this.swapOnLocked);
         this.swapOnLockedCache = this.swapOnLocked;
      }

      return this.swapOnLockedCacheList;
   }

   public List<TextHologram> getTextHolograms() {
      if (this.textHolograms == null) {
         this.textHolograms = new ArrayList<>();
      }

      return this.textHolograms;
   }

   @Generated
   public RouteSegment() {
   }
}
