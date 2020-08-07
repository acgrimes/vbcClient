package com.oracle.svm.core.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When used to annotate a method, it indicates that a method declaration is intended to be a
 * substitute for a method declaration in another class. A substitute method must be declared in a
 * class annotated with {@link TargetClass} as the {@link TargetClass#value() value} element of that
 * annotation specifies the class containing the method to be substituted (the <i>substitutee</i>
 * class).
 * <p>
 * The method to be substituted is determined based on a name and a list of parameter types. The
 * name is specified by an optional {@link TargetElement#name()} element of this annotation. If this
 * element is not specified, then the name of the substitution method is used. The parameter types
 * are those of the substitution method.
 * <p>
 * There must never be an explicit call to a non-static method annotated with {@link Substitute}
 * unless it is from another non-static method in the same class.
 * <p>
 * When used to annotate a class, it indicates that the class is intended to be a substitute for the
 * class specified via {@link TargetClass}. All methods in the target class that are not substituted
 * in the annotated class are implicitly treated as {@link Delete}d.
 * <p>
 * See {@link TargetClass} for an overview of the annotation system.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE, ElementType.FIELD})
public @interface Substitute {
}
