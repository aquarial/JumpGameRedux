package mainmenu.game.model;

public class Pair<A, B> {

    private A item1;
    private B item2;

    public Pair(A a, B b) {
        item1 = a;
        item2 = b;
    }

    /**
     * @return the item1
     */
    public A getItem1() {
        return item1;
    }

    /**
     * @return the item2
     */
    public B getItem2() {
        return item2;
    }
}
