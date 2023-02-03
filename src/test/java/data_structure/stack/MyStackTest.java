package data_structure.stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyStackTest {
    interface MyStack<E> {
        void push(E e);

        E pop();

        E peek();

        int size();

        boolean isEmpty();
    }

    static class DefaultMyStack<E> implements MyStack<E> {
        private final static int DEFAULT_CAPACITY = 10;
        private Object[] array;
        private int pointer;
        private int size;

        public DefaultMyStack() {
            array = new Object[DEFAULT_CAPACITY];
            pointer = -1;
            size = 0;
        }

        @Override
        public void push(final E e) {
            if (size == array.length) {
                grow();
            }

            array[++pointer] = e;
            size++;
        }

        private void grow() {
            final Object[] newArr = new Object[array.length + (array.length >> 1)];
            System.arraycopy(array, 0, newArr, 0, array.length);
            array = newArr;
        }

        @Override
        public E pop() {
            if (size <= 0) {
                throw new NullPointerException();
            }

            @SuppressWarnings("unchecked") final E e = (E) array[pointer--];
            size--;

            return e;
        }

        @Override
        public E peek() {
            if (size <= 0) {
                throw new NullPointerException();
            }

            @SuppressWarnings("unchecked") final E e = (E) array[pointer];
            return e;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }
    }

    private DefaultMyStack<Integer> defaultMyStack;

    @BeforeEach
    void setUp() {
        defaultMyStack = new DefaultMyStack<>();
    }

    @DisplayName("push 테스트")
    @Test
    void pushTest() {
        defaultMyStack.push(1);
        defaultMyStack.push(2);

        assertThat(defaultMyStack.pointer).isEqualTo(1);
        assertThat(defaultMyStack.size()).isEqualTo(2);
    }

    @DisplayName("sizeGrow 테스트")
    @Test
    void sizeGrow() {
        for (int i = 0; i < 11; i++) {
            defaultMyStack.push(i);
        }

        assertThat(defaultMyStack.array.length).isEqualTo(15);
    }

    @DisplayName("pop 테스트")
    @Test
    void popTest() {
        defaultMyStack.push(1);
        defaultMyStack.push(2);

        assertThat(defaultMyStack.pop()).isEqualTo(2);
        assertThat(defaultMyStack.pop()).isEqualTo(1);
        assertThat(defaultMyStack.pointer).isEqualTo(-1);
        assertThat(defaultMyStack.size()).isEqualTo(0);
    }

    @DisplayName("아무것도 없는데 pop()을 사용하면 NullPointerException 발생")
    @Test
    void popFailTest() {
        assertThatThrownBy(() -> defaultMyStack.pop())
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("peek 테스트")
    @Test
    void peekTest() {
        defaultMyStack.push(1);
        defaultMyStack.push(2);

        assertThat(defaultMyStack.peek()).isEqualTo(2);
        assertThat(defaultMyStack.pointer).isEqualTo(1);
        assertThat(defaultMyStack.size()).isEqualTo(2);
    }

    @DisplayName("아무것도 없는데 peek()을 사용하면 NullPointerException 발생")
    @Test
    void peekFailTest() {
        assertThatThrownBy(() -> defaultMyStack.peek())
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("empty true")
    @Test
    void isEmptyTestTrue() {
        assertThat(defaultMyStack.isEmpty()).isTrue();
    }

    @DisplayName("empty false")
    @Test
    void isEmptyTestFalse() {
        defaultMyStack.push(1);
        defaultMyStack.push(2);

        assertThat(defaultMyStack.isEmpty()).isFalse();
    }
}
