package com.moonsworth.lunar.client.util;

import java.lang.annotation.Documented;
import org.intellij.lang.annotations.Pattern;

@Documented
@Pattern("^([a-z]+\\.)?[a-z0-9]+(([A-Z]{1,4}[a-z0-9]+)*[A-Z]{0,3}|([a-z0-9]+[A-Z]{1,3})*|[A-Z]{1,3})$")
public @interface Annotation3 {
}
