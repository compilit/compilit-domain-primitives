package org.solidcoding.domainprimitives;

import java.util.Objects;

public abstract class DomainPrimitive<T> {
  private final T value;
  private final String name;

  /**
   * @param value the value of the domain primitive.
   * @param extendingClass the class that extends DomainPrimitive. Used to resolve the name.
   */
  protected DomainPrimitive(T value, Class<?> extendingClass) {
    this.name = extendingClass.getSimpleName();
    if (!isValid(value)) {
      throw new DomainPrimitiveException(ExceptionMessage.invalidDomainPrimitive(getName(), value));
    }
    this.value = value;
  }

  /**
   * @param value the value of the domain primitive
   * @param name the actual name of the domain primitive. Usually the name of the extending class.
   */
  protected DomainPrimitive(T value, String name) {
    this.name = name;
    if (!isValid(value)) {
      throw new DomainPrimitiveException(ExceptionMessage.invalidDomainPrimitive(getName(), value));
    }
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    try {
      if (obj instanceof DomainPrimitive) {
        var otherValue = ((DomainPrimitive<T>) obj).getValue();
        return Objects.equals(getValue(), otherValue);
      }
      return Objects.equals(this.value, obj);
    } catch (Exception exception) {
      return false;
    }
  }

  public T getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  protected abstract boolean isValid(T value);
}
