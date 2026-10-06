package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.mod.render.staffxray.StaffXray;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.util.Set;
import lombok.Generated;

public class StaffModsSettings extends com.moonsworth.lunar.client.config.FavoriteColorsConfig<Framework7Extension> {
   private StaffXray field2;

   @Override
   protected Set<Framework7Extension> method1() {
      return this.method3(new Framework7Extension[]{this.field2 = new StaffXray()});
   }

   @Override
   public String method5() {
      return "staff_mods.json";
   }

   @Override
   public File method6() {
      return new File(ThreadModuleDump48.field25 + File.separator + ThreadModuleDump63.method4().method61().method14().getName(), this.method5());
   }

   @Generated
   public StaffXray method10() {
      return this.field2;
   }
}
