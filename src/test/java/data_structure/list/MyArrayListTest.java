package data_structure.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyArrayListTest {
    interface MyArrayList<E> {
        void add(E e);

        E get(int idx);

        void set(int idx, E e);

        void delete(E e);

        void delete(int idx);

        int size();
    }

    static class DefaultMyArrayList<E> implements MyArrayList<E> {
        private static final int DEFAULT_SIZE = 8;

        private Object[] values;
        private int idx;
        private int size;

        public DefaultMyArrayList() {
            values = new Object[DEFAULT_SIZE];
            idx = 0;
            size = 0;
        }

        @Override
        public void add(final E e) {
            if (size == values.length) {  // 배열이 꽉 찼을 때 1.5배 증분 TODO System.arrayCopy 이용 고려
                final Object[] newArr = new Object[values.length + (values.length >> 1)];
                System.arraycopy(values, 0, newArr, 0, values.length);
                values = newArr;
            }

            values[idx] = e;
            idx++;
            size++;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E get(final int idx) {
            return (E) values[idx];
        }

        @Override
        public void set(final int idx, final E e) {
            values[idx] = e;
        }

        @Override
        public void delete(final E e) {
            for (int i = 0; i < values.length; i++) {
                @SuppressWarnings("unchecked") final E value = (E) values[i];
                if (value.equals(e)) {
                    delete(i);
                }
            }
        }

        @Override
        public void delete(final int idx) {
            final Object[] newArr = new Object[values.length];
            System.arraycopy(values, 0, newArr, 0, idx);
            System.arraycopy(values, idx + 1, newArr, idx, values.length - idx - 1);

            values = newArr;
            size--;
        }

        @Override
        public int size() {
            return size;
        }
    }

    // 테스트
    public static final int DEFAULT_SIZE = 8;
    private DefaultMyArrayList<Integer> myArrayList;

    @BeforeEach
    void setUp() {
        myArrayList = new DefaultMyArrayList<>();
    }

    @DisplayName("기본 할당 배열 크기 테스트")
    @Test
    void defaultSizeTest() {
        assertThat(myArrayList.values.length).isEqualTo(DEFAULT_SIZE);
    }

    @DisplayName("값 추가 테스트. 값이 올바르게 추가된다.")
    @Test
    void addTest() {
        myArrayList.add(1);
        myArrayList.add(2);

        assertThat(myArrayList.values[0]).isEqualTo(1);
        assertThat(myArrayList.values[1]).isEqualTo(2);
        assertThat(myArrayList.size()).isEqualTo(2);
    }

    @DisplayName("사이즈 증분 테스트. 배열이 꽉 찼을 때 값을 추가할 경우, 배열의 사이즈가 1.5배 증가된다.")
    @Test
    void sizeGrowTest() {
        for (int i = 0; i < DEFAULT_SIZE + 1; i++) {
            myArrayList.add(1);
        }
        assertThat(myArrayList.values.length).isEqualTo(DEFAULT_SIZE + (DEFAULT_SIZE >> 1));
    }

    @DisplayName("값 가져오기 테스트")
    @Test
    void getTest() {
        myArrayList.add(1);
        myArrayList.add(2);

        assertThat(myArrayList.get(0)).isEqualTo(1);
        assertThat(myArrayList.get(1)).isEqualTo(2);
    }

    @DisplayName("값 수정 테스트")
    @Test
    void setTest() {
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.set(1, 100);

        assertThat(myArrayList.get(1)).isEqualTo(100);
    }

    @DisplayName("값 삭제 테스트")
    @Test
    void deleteTest() {
        myArrayList.add(1);
        myArrayList.add(2);

        myArrayList.delete(1);
        assertThat(myArrayList.size()).isEqualTo(1);
    }
}
