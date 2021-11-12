package world.solidcoding.domainprimitives;

import java.util.Objects;

public abstract class DomainPrimitive<T> {
  private final T value;
  private final String name = this.getClass().getSimpleName();

  protected DomainPrimitive(T value) {
    if (!isValid(value)) {
      throw new DomainPrimitiveException(ExceptionMessage.invalidDomainPrimitive(getName(), value));
    }
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    try {
      if (obj instanceof DomainPrimitive) {
        var otherValue = ((DomainPrimitive<T>) obj).value;
        return value.equals(otherValue);
      }
      return this.value.equals(obj);
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
