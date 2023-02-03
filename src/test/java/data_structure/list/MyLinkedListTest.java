package data_structure.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyLinkedListTest {
    interface MyLinkedList<E> {
        void add(E e);  // 끝에 추가

        E get(int idx);

        void set(int idx, E e);

        void remove(E e);

        int size();
    }

    static class DefaultMyLinkedList<E> implements MyLinkedList<E> {
        private Node<E> first;
        private Node<E> last;
        private int size;

        private static class Node<E> {
            private E value;
            private Node<E> prev;
            private Node<E> next;

            public Node(final E value) {
                this.value = value;
            }
        }

        @Override
        public void add(final E e) {
            final Node<E> node = new Node<>(e);

            if (first == null) {
                first = node;
                last = node;
                size++;
                return;
            }

            last.next = node;
            node.prev = last;
            last = node;
            size++;
        }

        @Override
        public E get(final int idx) {
            if (size < idx) {
                throw new IndexOutOfBoundsException();
            }

            Node<E> x = getNodeByIndex(idx);
            return x.value;
        }

        private Node<E> getNodeByIndex(final int idx) {
            if (idx == 0) {
                return first;
            }

            if (idx == size - 1) {
                return last;
            }

            // size의 절반만 순회한다.
            Node<E> node;
            if (idx < (size >> 1)) {
                // 앞에서부터 순회
                node = first;
                for (int i = 0; i < idx; i++) {  // idx 횟수만큼 수행
                    node = node.next;
                }

            } else {
                // 뒤에서부터 순회
                node = last;
                for (int i = size - 1; i > idx; i--) {
                    node = node.prev;
                }

            }
            return node;
        }

        @Override
        public void set(final int idx, final E e) {
            final Node<E> before = getNodeByIndex(idx);
            before.value = e;
        }

        @Override
        public void remove(final E e) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (e.equals(node.value)) {
                    final Node<E> prev = node.prev;
                    final Node<E> next = node.next;

                    prev.next = next;

                    if (next != null) {
                        next.prev = prev;
                    }

                    node.prev = null;
                    node.next = null;

                    break;
                }
            }

            size--;
        }

        @Override
        public int size() {
            return size;
        }
    }

    // 테스트
    public static DefaultMyLinkedList<Integer> myLinkedList;

    @BeforeEach
    void setUp() {
        myLinkedList = new DefaultMyLinkedList<>();
    }

    @DisplayName("값 추가 테스트")
    @Test
    void addTest() {
        myLinkedList.add(1);
        myLinkedList.add(2);

        assertThat(myLinkedList.size()).isEqualTo(2);
        assertThat(myLinkedList.first.value).isEqualTo(1);
        assertThat(myLinkedList.first.next.value).isEqualTo(2);
    }

    @DisplayName("값 가져오기 테스트")
    @Test
    void getTest() {
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);

        assertThat(myLinkedList.get(0)).isEqualTo(1);
        assertThat(myLinkedList.get(1)).isEqualTo(2);
        assertThat(myLinkedList.get(2)).isEqualTo(3);
    }

    @DisplayName("값 수정 테스트")
    @Test
    void setTest() {
        myLinkedList.add(1);
        myLinkedList.add(2);

        myLinkedList.set(0, 100);
        myLinkedList.set(1, 200);

        assertThat(myLinkedList.first.value).isEqualTo(100);
        assertThat(myLinkedList.first.next.value).isEqualTo(200);
    }

    @DisplayName("값 삭제 테스트")
    @Test
    void removeTest() {
        myLinkedList.add(1);
        myLinkedList.add(2);

        myLinkedList.remove(2);

        assertThat(myLinkedList.size()).isEqualTo(1);
        assertThat(myLinkedList.first.value).isEqualTo(1);
    }
}
