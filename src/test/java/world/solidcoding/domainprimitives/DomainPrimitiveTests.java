package world.solidcoding.domainprimitives;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static world.solidcoding.domainprimitives.testutil.TestValue.TEST_CONTENT;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public final class DomainPrimitiveTests {

  @Test
  void equals_ShouldReturnTrue() {
    var x = new TestDomainPrimitive<>(TEST_CONTENT);
    var y = new TestDomainPrimitive<>(TEST_CONTENT);
    assertEquals(x, y);
  }

  @Test
  void equals_UnequalObjects_ShouldReturnTrue() {
    var x = new TestDomainPrimitive<>(TEST_CONTENT);
    var y = new TestDomainPrimitive<>("something else");
    assertNotEquals(x, y);
  }

  @Test
  void getValue_ShouldReturnDomainPrimitiveValue() {
    var domainPrimitive = new TestDomainPrimitive<>(TEST_CONTENT);
    assertEquals(domainPrimitive.getValue(), TEST_CONTENT);
  }
  @Test
  void getName_ShouldReturnNameOfClass() {
    var domainPrimitive = new DomainPrimitive(TEST_CONTENT) {
      @Override
      protected boolean isValid(Object value) {
        return true;
      }
    };
    assertEquals(DomainPrimitive.class.getSimpleName(), domainPrimitive.getName());
  }

  @Test
  void throwDefaultDomainPrimitiveException_ExceptionShouldHaveCorrectContent() {
    Assertions.assertThatThrownBy(() -> new TestExceptionDomainPrimitive<>(TEST_CONTENT))
              .isInstanceOf(DomainPrimitiveException.class)
              .hasMessageContaining("TestExceptionDomainPrimitive");
  }

  private static class TestDomainPrimitive<T> extends DomainPrimitive<T> {

    public TestDomainPrimitive(T value) {
      super(value);
    }

    @Override
    public boolean isValid(T value) {
      return true;
    }
  }

  private static class TestExceptionDomainPrimitive<T> extends DomainPrimitive<T> {

    public TestExceptionDomainPrimitive(T value) {
      super(value);
    }

    @Override
    public boolean isValid(T value) {
      return false;
    }
  }
}
