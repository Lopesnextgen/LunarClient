package com.moonsworth.lunar.client.mod.misc.debug;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import java.util.List;

public class GraphDebugMod extends AbstractFeature {
   protected final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showChildModNames").method4(true))
      .method31();

   public GraphDebugMod() {
      super(false);
   }

   public String getId() {
      return "GRAPH_DEBUG_MOD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method3(new String[]{"FX"}).method11(this);
   }

   protected List<Framework7Extension> method9() {
      return ImmutableList.of(new MemoryDebug(this), new DirectMemoryDebug(this), new OffHeapMemoryDebug(this));
   }

   public boolean method13() {
      return (Boolean)this.field8.get();
   }
}
