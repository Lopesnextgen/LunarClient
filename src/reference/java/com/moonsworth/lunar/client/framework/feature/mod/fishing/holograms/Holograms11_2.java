package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.ichor.util.Annotation2;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

@Annotation2
public class Holograms11_2 {
   public boolean hidden;
   public String swapOnComplete;
   private transient String swapOnCompleteCache;
   private transient List<Holograms12> swapOnCompleteCacheList;

   public Holograms11_2(Holograms11_2 holograms11_2) {
      this.hidden = holograms11_2.hidden;
      this.swapOnComplete = holograms11_2.swapOnComplete;
   }

   @Nullable
   public List<Holograms12> getSwapOnComplete() {
      if (!Objects.equals(this.swapOnCompleteCache, this.swapOnComplete)) {
         this.swapOnCompleteCacheList = Holograms12.method1(this.swapOnComplete);
         this.swapOnCompleteCache = this.swapOnComplete;
      }

      return this.swapOnCompleteCacheList;
   }

   @Generated
   public Holograms11_2() {
   }
}
