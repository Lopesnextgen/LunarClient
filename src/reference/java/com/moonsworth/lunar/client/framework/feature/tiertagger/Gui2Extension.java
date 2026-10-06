package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.nameplate.Nameplate;
import java.util.Arrays;
import java.util.List;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   PVPTIERS_COM("pvptiers", new Tiertagger2Iterator_2()),
   MCTIERS_COM("mctiersCom", new Tiertagger2Iterator2()),
   SUBTIERS("subtiers", new com.moonsworth.lunar.client.framework.feature.tiertagger.nameplate.Tiertagger2Iterator()),
   TIERTESTS("tiertests", new com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers.Tiertagger2Iterator()),
   PVPHQ("pvphq", new Tiertagger2Iterator());

   public static final Codec<Gui2Extension> CODEC = Nameplate.method25(Gui2Extension::values);
   public static final List<Gui2Extension> VALUES = ThreadModuleDump63.MC_VERSION == 1 ? List.of(TIERTESTS) : Arrays.asList(values());
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
      return this.method1(this.id, new Object[0]);
   }

   public boolean isTierTests() {
      return this == TIERTESTS;
   }

   @Generated
   Gui2Extension(String text, Tiertagger2_2 tiertagger2_2) {
      this.id = text;
      this.tierProvider = tiertagger2_2;
   }

   @Generated
   public Tiertagger2_2 getTierProvider() {
      return this.tierProvider;
   }
}
