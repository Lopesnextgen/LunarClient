package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

@KeepName
public class RouteCompletion {
   public boolean hidden;
   public String swapOnComplete;
   private transient String swapOnCompleteCache;
   private transient List<RouteLink> swapOnCompleteCacheList;

   public RouteCompletion(RouteCompletion holograms11_21) {
      this.hidden = holograms11_21.hidden;
      this.swapOnComplete = holograms11_21.swapOnComplete;
   }

   @Nullable
   public List<RouteLink> getSwapOnComplete() {
      if (!Objects.equals(this.swapOnCompleteCache, this.swapOnComplete)) {
         this.swapOnCompleteCacheList = RouteLink.method1(this.swapOnComplete);
         this.swapOnCompleteCache = this.swapOnComplete;
      }

      return this.swapOnCompleteCacheList;
   }

   @Generated
   public RouteCompletion() {
   }
}
