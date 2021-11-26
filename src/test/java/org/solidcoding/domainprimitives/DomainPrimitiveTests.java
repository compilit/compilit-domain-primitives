package org.solidcoding.domainprimitives;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.solidcoding.domainprimitives.testutil.TestValue.TEST_CONTENT;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.solidcoding.domainprimitives.testutil.SomePositiveNumber;

public final class DomainPrimitiveTests {

  @Test
  void equals_shouldReturnTrue() {
    var x = new TestDomainPrimitive<>(TEST_CONTENT);
    var y = new TestDomainPrimitive<>(TEST_CONTENT);
    assertEquals(x, y);
  }

  @Test
  void equals_notEqualObjects_shouldReturnTrue() {
    var x = new TestDomainPrimitive<>(TEST_CONTENT);
    var y = new TestDomainPrimitive<>("something else");
    assertNotEquals(x, y);
  }

  @Test
  void equals_sameIntAndInteger_shouldReturnTrue() {
    int primitive = 1;
    Integer object = 1;

    var actualDomainPrimitive = new SomePositiveNumber(object);
    var actualOtherDomainPrimitive = new SomePositiveNumber(primitive);

    Assertions.assertThat(primitive).isEqualTo(object);

    Assertions.assertThat(actualDomainPrimitive).isEqualTo(primitive);
    Assertions.assertThat(actualDomainPrimitive).isEqualTo(object);

    Assertions.assertThat(actualOtherDomainPrimitive).isEqualTo(primitive);
    Assertions.assertThat(actualOtherDomainPrimitive).isEqualTo(object);

    Assertions.assertThat(actualDomainPrimitive).isEqualTo(actualOtherDomainPrimitive);
  }

  @Test
  void getValue_shouldReturnDomainPrimitiveValue() {
    var domainPrimitive = new TestDomainPrimitive<>(TEST_CONTENT);
    assertEquals(domainPrimitive.getValue(), TEST_CONTENT);
  }
  @Test
  void getName_shouldReturnNameOfClass() {
    var domainPrimitive = new DomainPrimitive(TEST_CONTENT, DomainPrimitive.class) {
      @Override
      protected boolean isValid(Object value) {
        return true;
      }
    };
    assertEquals(DomainPrimitive.class.getSimpleName(), domainPrimitive.getName());
  }

  @Test
  void ctor_invalidValue_exceptionShouldHaveCorrectContent() {
    Assertions.assertThatThrownBy(() -> new TestExceptionDomainPrimitive<>(TestExceptionDomainPrimitive.class.getSimpleName()))
              .isInstanceOf(DomainPrimitiveException.class)
              .hasMessageContaining(TestExceptionDomainPrimitive.class.getSimpleName());
  }

  private static class TestDomainPrimitive<T> extends DomainPrimitive<T> {

    public TestDomainPrimitive(T value) {
      super(value, TestDomainPrimitive.class);
    }

    @Override
    public boolean isValid(T value) {
      return true;
    }
  }

  private static class TestExceptionDomainPrimitive<T> extends DomainPrimitive<T> {

    public TestExceptionDomainPrimitive(T value) {
      super(value, TestDomainPrimitive.class);
    }

    @Override
    public boolean isValid(T value) {
      return false;
    }
  }

}
