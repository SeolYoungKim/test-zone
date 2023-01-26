package data_structure.deque;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MyDequeTest {
    @DisplayName("addFirst()로 첫 번째 요소를 넣으면 first, last 모두 요소로 초기화됨")
    @Test
    void myDequeTest() {
        final MyDeque<Integer> myDeque = new MyDeque<>();
        myDeque.addFirst(1);

        assertThat(myDeque.first.value).isEqualTo(1);
        assertThat(myDeque.last.value).isEqualTo(1);
        assertThat(myDeque.size()).isEqualTo(1);
    }

    @DisplayName("addFirst()로 요소를 두 개 넣으면, 첫 번째로 들어간 요소가 last, 마지막으로 들어간 요소가 first에 존재함")
    @Test
    void myDequeTest2() {
        final MyDeque<Integer> myDeque = new MyDeque<>();
        myDeque.addFirst(1);
        myDeque.addFirst(2);
        myDeque.addFirst(3);

        assertThat(myDeque.first.value).isEqualTo(3);
        assertThat(myDeque.last.value).isEqualTo(1);
        assertThat(myDeque.size()).isEqualTo(3);
    }

    @DisplayName("addLast()로 첫 번째 요소를 넣으면 first, last 모두 요소로 초기화됨")
    @Test
    void myDequeTest3() {
        final MyDeque<Integer> myDeque = new MyDeque<>();
        myDeque.addLast(1);

        assertThat(myDeque.first.value).isEqualTo(1);
        assertThat(myDeque.last.value).isEqualTo(1);
        assertThat(myDeque.size()).isEqualTo(1);
    }

    @DisplayName("addLast()로 요소를 두 개 넣으면, 첫 번째로 들어간 요소가 first, 마지막으로 들어간 요소가 last에 존재함")
    @Test
    void myDequeTest4() {
        final MyDeque<Integer> myDeque = new MyDeque<>();
        myDeque.addLast(1);
        myDeque.addLast(2);
        myDeque.addLast(3);

        assertThat(myDeque.first.value).isEqualTo(1);
        assertThat(myDeque.last.value).isEqualTo(3);
        assertThat(myDeque.size()).isEqualTo(3);
    }

    @DisplayName("getXxx() 요청을 할 경우")
    @Nested
    class getTest {
        private MyDeque<Integer> myDeque;

        @BeforeEach
        void setUp() {
            myDeque = new MyDeque<>();
            myDeque.addLast(1);
            myDeque.addLast(2);
            myDeque.addLast(3);
        }

        @DisplayName("getFirst()로 요소를 가져오면, 첫 번째 참조했던 값이 사라짐")
        @Test
        void getFirst() {
            final Integer firstValue = myDeque.getFirst();

            assertThat(firstValue).isEqualTo(1);
            assertThat(myDeque.first.value).isEqualTo(2);
            assertThat(myDeque.size()).isEqualTo(2);
        }

        @DisplayName("getFirst()로 하나의 요소가 들어있는 MyDeque의 요소를 가져와도 문제 없다.")
        @Test
        void getFirstOnlyOneElement() {
            final MyDeque<Integer> myDequeOnlyOneElem = new MyDeque<>();
            myDequeOnlyOneElem.addFirst(1);

            final Integer firstValue = myDequeOnlyOneElem.getFirst();
            assertThat(firstValue).isEqualTo(1);
            assertThat(myDequeOnlyOneElem.first).isNull();
            assertThat(myDequeOnlyOneElem.last).isNull();
            assertThat(myDequeOnlyOneElem.size()).isEqualTo(0);
        }

        @DisplayName("getFirst()로 없는 것을 조회할 경우 NullPointerException을 던진다.")
        @Test
        void getFirstFail() {
            final MyDeque<Integer> emptyDeque = new MyDeque<>();
            assertThatThrownBy(emptyDeque::getFirst)
                    .isInstanceOf(NullPointerException.class);
        }

        @DisplayName("getLast()로 요소를 가져오면, 마지막에 참조했던 값이 사라짐")
        @Test
        void getLast() {
            final Integer lastValue = myDeque.getLast();

            assertThat(lastValue).isEqualTo(3);
            assertThat(myDeque.first.value).isEqualTo(1);
            assertThat(myDeque.last.value).isEqualTo(2);
            assertThat(myDeque.size()).isEqualTo(2);
        }

        @DisplayName("getFirst()로 하나의 요소가 들어있는 MyDeque의 요소를 가져와도 문제 없다.")
        @Test
        void getLastOnlyOneElement() {
            final MyDeque<Integer> myDequeOnlyOneElem = new MyDeque<>();
            myDequeOnlyOneElem.addFirst(1);

            final Integer lastValue = myDequeOnlyOneElem.getLast();
            assertThat(lastValue).isEqualTo(1);
            assertThat(myDequeOnlyOneElem.first).isNull();
            assertThat(myDequeOnlyOneElem.last).isNull();
            assertThat(myDequeOnlyOneElem.size()).isEqualTo(0);
        }

        @DisplayName("getLast()로 없는 것을 조회할 경우 NullPointerException을 던진다.")
        @Test
        void getLastFail() {
            final MyDeque<Integer> emptyDeque = new MyDeque<>();
            assertThatThrownBy(emptyDeque::getLast)
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("peekXxx() 요청을 할 경우")
    @Nested
    class peekTest {
        private MyDeque<Integer> myDeque;

        @BeforeEach
        void setUp() {
            myDeque = new MyDeque<>();
            myDeque.addLast(1);
            myDeque.addLast(2);
            myDeque.addLast(3);
        }

        @DisplayName("peekFirst()를 할 경우 가장 처음의 값을 조회하고, MyDeque에는 아무런 영향이 없다.")
        @Test
        void peekFirst() {
            final Integer peekFirst = myDeque.peekFirst();

            assertThat(peekFirst).isEqualTo(1);
            assertThat(myDeque.first.value).isEqualTo(1);
            assertThat(myDeque.last.value).isEqualTo(3);
            assertThat(myDeque.size()).isEqualTo(3);
        }

        @DisplayName("peekFirst()로 없는 것을 조회할 경우 NullPointerException을 던진다.")
        @Test
        void peekFirstFail() {
            final MyDeque<Integer> emptyDeque = new MyDeque<>();
            assertThatThrownBy(emptyDeque::peekFirst)
                    .isInstanceOf(NullPointerException.class);
        }

        @DisplayName("peekLast()를 할 경우 가장 마지막의 값을 조회하고, MyDeque에는 아무런 영향이 없다.")
        @Test
        void peekLast() {
            final Integer peekLast = myDeque.peekLast();

            assertThat(peekLast).isEqualTo(3);
            assertThat(myDeque.first.value).isEqualTo(1);
            assertThat(myDeque.last.value).isEqualTo(3);
            assertThat(myDeque.size()).isEqualTo(3);
        }

        @DisplayName("peekLast()로 없는 것을 조회할 경우 NullPointerException을 던진다.")
        @Test
        void peekLastFail() {
            final MyDeque<Integer> emptyDeque = new MyDeque<>();
            assertThatThrownBy(emptyDeque::peekLast)
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("MyDeque이 비어있을 경우 isEmpty()는 true를 반환한다")
    @Test
    void isEmptyTrue() {
        final MyDeque<Integer> emptyDeque = new MyDeque<>();
        assertThat(emptyDeque.isEmpty()).isTrue();
    }

    @DisplayName("MyDeque이 비어있지 않을 경우 isEmpty()는 false를 반환한다")
    @Test
    void isEmptyFalse() {
        final MyDeque<Integer> myDeque = new MyDeque<>();
        myDeque.addFirst(1);

        assertThat(myDeque.isEmpty()).isFalse();
    }

    static class MyDeque<E> {
        private Node<E> first;
        private Node<E> last;
        private int size = 0;

        static class Node<E> {
            private final E value;
            private Node<E> prev;
            private Node<E> next;

            public Node(E value) {
                this.value = value;
            }

            @Override
            public boolean equals(final Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                final Node<?> node = (Node<?>) o;
                return Objects.equals(value, node.value) && Objects.equals(prev, node.prev)
                        && Objects.equals(next, node.next);
            }
        }

        public void addFirst(E e) {
            Node<E> node = new Node<>(e);
            if (first == null) {
                // 첫번째 요소가 없으면 last도 null임
                first = node;
                last = node;
            } else {
                // 첫 번째 요소가 null이 아닌 경우, 기존의 첫 번째 요소 앞에 node 연결
                Node<E> next = first;
                next.prev = node;

                node.next = next;
                first = node;
            }

            size++;
        }

        public void addLast(E e) {
            Node<E> node = new Node<>(e);
            if (first == null) {
                // 첫 번째 요소가 없으면 last도 null임
                first = node;
                last = node;
            } else {
                Node<E> next = last;
                next.next = node;

                node.prev = next;
                last = node;
            }

            size++;
        }

        public E getFirst() {
            if (first == null) {
                throw new NullPointerException();
            }

            if (first.equals(last)) {
                return getValueForOnlyOneNode();
            }

            Node<E> firstNode = first;
            Node<E> nextNode = first.next;

            firstNode.next = null;
            nextNode.prev = null;
            first = nextNode;
            size--;

            return firstNode.value;
        }

        private E getValueForOnlyOneNode() {
            E value = first.value;
            first = null;
            last = null;
            size--;
            return value;
        }

        public E getLast() {
            if (last == null) {
                throw new NullPointerException();
            }

            if (first.equals(last)) {
                return getValueForOnlyOneNode();
            }

            Node<E> lastNode = last;
            Node<E> prevNode = last.prev;

            lastNode.prev = null;
            prevNode.next = null;
            last = prevNode;
            size--;

            return lastNode.value;
        }


        public E peekFirst() {
            if (first == null) {
                throw new NullPointerException();
            }

            return first.value;
        }

        public E peekLast() {
            if (last == null) {
                throw new NullPointerException();
            }

            return last.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }
}
