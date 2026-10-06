package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ExtraCodecs;
import java.util.Arrays;
import java.util.List;
import lombok.Generated;

public enum TiertaggerSource implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   PVPTIERS_COM("pvptiers", new Tiertagger2Iterator_2()),
   MCTIERS_COM("mctiersCom", new Tiertagger2Iterator2()),
   SUBTIERS("subtiers", new com.moonsworth.lunar.client.framework.feature.tiertagger.nameplate.Tiertagger2Iterator()),
   TIERTESTS("tiertests", new com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers.Tiertagger2Iterator()),
   PVPHQ("pvphq", new Tiertagger2Iterator());

   public static final Codec<TiertaggerSource> CODEC = ExtraCodecs.method25(TiertaggerSource::values);
   public static final List<TiertaggerSource> VALUES = Ref.MC_VERSION == 1 ? List.of(TIERTESTS) : Arrays.asList(values());
   private final String id;
   private final Tiertagger2_2 tierProvider;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.name();
   }

   public String niceName() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   public boolean isTierTests() {
      return this == TIERTESTS;
   }

   @Generated
   TiertaggerSource(String text3, Tiertagger2_2 tiertagger2_24) {
      this.id = text3;
      this.tierProvider = tiertagger2_24;
   }

   @Generated
   public Tiertagger2_2 getTierProvider() {
      return this.tierProvider;
   }
}
