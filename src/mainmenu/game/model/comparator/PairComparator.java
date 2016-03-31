package mainmenu.game.model.comparator;

import java.util.Comparator;

import mainmenu.game.model.Pair;
import mainmenu.game.model.block.Block;
import mainmenu.game.model.level.Point;

public class PairComparator implements Comparator<Pair<Block, Point>> {

    @Override
    public int compare(Pair<Block, Point> o1, Pair<Block, Point> o2) {
        // Notice the simularity to PointComparator
        return Double.compare(Math.pow(o1.getItem2().getX(), 2) + Math.pow(o1.getItem2().getY(), 2),
                Math.pow(o2.getItem2().getX(), 2) + Math.pow(o2.getItem2().getY(), 2));
    }

}
