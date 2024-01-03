package enum_;

public enum EnumBody {
    A {
        @Override
        public void print() {
            System.out.println("A");
        }
    },
    B {
        @Override
        public void print() {
            System.out.println("B");
        }
    },
    C {
        @Override
        public void print() {
            System.out.println("C");
        }
    };

    public abstract void print();
}
