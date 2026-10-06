package com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.listener.TabListListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;

public class ScoreboardSection {
   private final List<Component> field1;
   private final List<String> field2;
   private static final Pattern field3 = Pattern.compile("^ [^ ].+");

   public ScoreboardSection(List<Component> list, List<String> list2) {
      this.field1 = list;
      this.field2 = list2;
   }

   public static ScoreboardSection method1(TabListListener listener, int index1) {
      ImmutableList list2 = listener.method5();
      ImmutableList list3 = listener.method6();
      ArrayList list4 = new ArrayList();
      ArrayList list5 = new ArrayList();
      list4.add((Component)list2.get(index1));
      list5.add((String)list3.get(index1));

      for (int index6 = index1 + 1; index6 < list3.size() && index6 < list2.size(); index6++) {
         Component component7 = (Component)list2.get(index6);
         String text8 = (String)list3.get(index6);
         if (!text8.equals("               Info")) {
            if (!field3.matcher(text8).matches()) {
               break;
            }

            list4.add(component7);
            list5.add(text8);
         }
      }

      return new ScoreboardSection(list4, list5);
   }

   public List<Component> method2() {
      return this.field1;
   }

   public List<String> method3() {
      return this.field2;
   }
}
