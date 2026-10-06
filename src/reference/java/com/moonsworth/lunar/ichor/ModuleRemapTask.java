package com.moonsworth.lunar.ichor;

import lombok.Generated;
import org.objectweb.asm.tree.ModuleExportNode;
import org.objectweb.asm.tree.ModuleNode;
import org.objectweb.asm.tree.ModuleOpenNode;
import org.objectweb.asm.tree.ModuleProvideNode;
import org.objectweb.asm.tree.ModuleRequireNode;

public class ModuleRemapTask {
   protected final ModuleNode field1;

   public void run() {
      this.field1.name = this.field2.field3.mapModuleName(this.field1.name);
      this.field1.mainClass = this.field2.field3.mapType(this.field1.mainClass);
      if (this.field1.packages != null) {
         for (int index1 = 0; index1 < this.field1.packages.size(); index1++) {
            this.field1.packages.set(index1, this.field2.field3.mapPackageName((String)this.field1.packages.get(index1)));
         }
      }

      if (this.field1.requires != null) {
         for (ModuleRequireNode modulerequirenode2 : this.field1.requires) {
            modulerequirenode2.module = this.field2.field3.mapModuleName(modulerequirenode2.module);
         }
      }

      if (this.field1.exports != null) {
         for (ModuleExportNode moduleexportnode9 : this.field1.exports) {
            moduleexportnode9.packaze = this.field2.field3.mapPackageName(moduleexportnode9.packaze);

            for (int index3 = 0; index3 < moduleexportnode9.modules.size(); index3++) {
               moduleexportnode9.modules.set(index3, this.field2.field3.mapModuleName((String)moduleexportnode9.modules.get(index3)));
            }
         }
      }

      if (this.field1.opens != null) {
         for (ModuleOpenNode moduleopennode10 : this.field1.opens) {
            moduleopennode10.packaze = this.field2.field3.mapPackageName(moduleopennode10.packaze);

            for (int index12 = 0; index12 < moduleopennode10.modules.size(); index12++) {
               moduleopennode10.modules.set(index12, this.field2.field3.mapModuleName((String)moduleopennode10.modules.get(index12)));
            }
         }
      }

      if (this.field1.uses != null) {
         for (int index7 = 0; index7 < this.field1.uses.size(); index7++) {
            this.field1.uses.set(index7, this.field2.field3.mapType((String)this.field1.uses.get(index7)));
         }
      }

      if (this.field1.provides != null) {
         for (ModuleProvideNode moduleprovidenode11 : this.field1.provides) {
            moduleprovidenode11.service = this.field2.field3.mapType(moduleprovidenode11.service);

            for (int index13 = 0; index13 < moduleprovidenode11.providers.size(); index13++) {
               moduleprovidenode11.providers.set(index13, this.field2.field3.mapType((String)moduleprovidenode11.providers.get(index13)));
            }
         }
      }
   }

   @Generated
   public ModuleRemapTask(ClassNodeRemapper mixinmisc31, ModuleNode modulenode2) {
      this.field2 = mixinmisc31;
      this.field1 = modulenode2;
   }
}
