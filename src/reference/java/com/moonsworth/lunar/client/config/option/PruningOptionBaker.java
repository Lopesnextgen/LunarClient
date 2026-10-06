package com.moonsworth.lunar.client.config.option;

import it.unimi.dsi.fastutil.Pair;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.ClientOption;

public abstract class PruningOptionBaker<OUT extends OptionTreeNode<?>, Child> implements SettingsTreeMapper<SettingsNode, OUT, Child> {
   public PruningOptionBaker() {
   }

   public abstract OUT method1(SettingsNode lightinghandler21, List<Child> list2, @Nullable BooleanSupplier booleansupplier3);

   public OUT method2(SettingsNode lightinghandler21) {
      return this.method1(lightinghandler21, this.method6(lightinghandler21), lightinghandler21.method1());
   }

   @Override
   public Pair<Map<ClientOption<?>, SettingsNode>, Collection<SettingsNode>> method4(Map<ClientOption<?>, SettingsNode> map1) {
      HashSet set2 = new HashSet();
      Iterator iterator3 = map1.entrySet().iterator();

      while (iterator3.hasNext()) {
         Entry entry4 = (Entry)iterator3.next();
         SettingsNode lightinghandler25 = (SettingsNode)entry4.getValue();
         if (lightinghandler25.method4() || this.method5(lightinghandler25, set2)) {
            iterator3.remove();
            set2.add(lightinghandler25);
         }

         this.method4(lightinghandler25, set2);
      }

      return Pair.of(map1, set2);
   }

   protected void method4(SettingsNode lightinghandler21, Set<SettingsNode> set2) {
      Iterator iterator3 = lightinghandler21.getChildren().iterator();

      while (iterator3.hasNext()) {
         SettingsNode lightinghandler24 = (SettingsNode)iterator3.next();
         if (lightinghandler24.method4() || this.method5(lightinghandler24, set2)) {
            iterator3.remove();
            set2.add(lightinghandler24);
         }

         this.method4(lightinghandler24, set2);
      }
   }

   boolean method5(SettingsNode lightinghandler21, Set<SettingsNode> set2) {
      if (lightinghandler21.method2().isEmpty()) {
         return false;
      }

      for (SettingsNode lightinghandler24 : lightinghandler21.method2()) {
         if (!lightinghandler24.method4() && !set2.contains(lightinghandler24)) {
            return false;
         }
      }

      return true;
   }

   public List<Child> method6(SettingsNode lightinghandler21) {
      LinkedList list2 = new LinkedList();

      for (SettingsNode lightinghandler24 : lightinghandler21.getChildren()) {
         list2.add(this.method4(lightinghandler24));
      }

      return list2;
   }
}
