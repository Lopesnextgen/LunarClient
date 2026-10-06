package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.ichor.util.Annotation2;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Generated;

@Annotation2
public class Holograms10_2 {
   public static final Holograms10_2 EMPTY = new Holograms10_2();
   public String swapOnLocked;
   public List<Holograms10$Data> textHolograms;
   private transient String swapOnLockedCache;
   private transient List<Holograms12> swapOnLockedCacheList;

   public Holograms10_2(Holograms10_2 holograms10_2) {
      this.swapOnLocked = holograms10_2.swapOnLocked;
      this.textHolograms = holograms10_2.textHolograms.stream().map(Holograms10$Data::new).collect(Collectors.toList());
   }

   public List<Holograms12> getSwapOnLocked() {
      if (!Objects.equals(this.swapOnLockedCache, this.swapOnLocked)) {
         this.swapOnLockedCacheList = Holograms12.method1(this.swapOnLocked);
         this.swapOnLockedCache = this.swapOnLocked;
      }

      return this.swapOnLockedCacheList;
   }

   public List<Holograms10$Data> getTextHolograms() {
      if (this.textHolograms == null) {
         this.textHolograms = new ArrayList<>();
      }

      return this.textHolograms;
   }

   @Generated
   public Holograms10_2() {
   }
}
