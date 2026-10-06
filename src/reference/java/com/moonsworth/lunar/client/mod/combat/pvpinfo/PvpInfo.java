package com.moonsworth.lunar.client.mod.combat.pvpinfo;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.network.server.ServerAddressBook;
import com.moonsworth.lunar.client.network.server.PingEntryConfig;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.pvpinfo.PvpInfoTimePeriod;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;

public class PvpInfo extends AbstractFeature {
   private final PingEntryConfig field8 = Ref.method4().method80();
   private final EnumOption<PvpInfoTimePeriod> field9 = (EnumOption<PvpInfoTimePeriod>)OptionFactory.method10("timePeriod", PvpInfoTimePeriod.SESSION)
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("resetOnWorldChange").method31();

   public PvpInfo() {
      super(false);
      this.handle(EventServerJoin.class, this::method1);
   }

   public String getId() {
      return "PVP_INFO";
   }

   private void method1(EventServerJoin highlightimpl161) {
      if ((Boolean)this.field10.get()) {
         this.field8.method14();
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new OptionProvider[]{this.field9, OptionFactory.method14("resetSession").method4(() -> this.field8.method14())});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field10}))
         .method2(() -> this.field9.get() != PvpInfoTimePeriod.SESSION);
   }

   public List<Framework7Extension> method9() {
      return ImmutableList.of(new PvpInfoMeleeStats(this), new PvpInfoHealthStats(this), new PvpInfoProjectileStats(this));
   }

   public ServerAddressBook method13() {
      return (ServerAddressBook)(switch ((PvpInfoTimePeriod)this.field9.get()) {
         case SESSION -> this.field8.method15();
         case DAY -> this.field8.method3();
         case WEEK -> this.field8.method12();
         case MONTH -> this.field8.method10();
         case YEAR -> this.field8.method11();
         case ALL_TIME -> this.field8.method13();
         default -> this.field8.method15();
      });
   }

   @Generated
   public PingEntryConfig method14() {
      return this.field8;
   }

   @Generated
   public EnumOption<PvpInfoTimePeriod> method15() {
      return this.field9;
   }

   @Generated
   public ToggleOption method16() {
      return this.field10;
   }
}
