public final class BoardWeights {

    public static final Board getBoardWeights() {

        //                            2
        //                        3   3   3
        //                    4   6   6   6   4
        //                4   7   8   8   8   7   4
        //            4   7   8   9   X   9   8   7   4
        //        2   5   6   7   8   8   8   7   6   5   2
        //    1   2   2   3   4   4   4   4   4   3   2   2   1

        IPosition[] weights = new IPosition[]{
                new ScoredPosition(new Position(1,7, Disc.None), 2),
                new ScoredPosition(new Position(2,6, Disc.None), 3),
                new ScoredPosition(new Position(2,7, Disc.None), 3),
                new ScoredPosition(new Position(2,8, Disc.None), 3),
                new ScoredPosition(new Position(3,5, Disc.None), 4),
                new ScoredPosition(new Position(3,6, Disc.None), 6),
                new ScoredPosition(new Position(3,7, Disc.None), 6),
                new ScoredPosition(new Position(3,8, Disc.None), 6),
                new ScoredPosition(new Position(3,9, Disc.None), 4),
                new ScoredPosition(new Position(4,4, Disc.None), 4),
                new ScoredPosition(new Position(4,5, Disc.None), 7),
                new ScoredPosition(new Position(4,6, Disc.None), 8),
                new ScoredPosition(new Position(4,7, Disc.None), 8),
                new ScoredPosition(new Position(4,8, Disc.None), 8),
                new ScoredPosition(new Position(4,9, Disc.None), 7),
                new ScoredPosition(new Position(4,10, Disc.None), 4),
                new ScoredPosition(new Position(5,3, Disc.None), 4),
                new ScoredPosition(new Position(5,4, Disc.None), 7),
                new ScoredPosition(new Position(5,5, Disc.None), 8),
                new ScoredPosition(new Position(5,6, Disc.None), 9),
                new ScoredPosition(new Position(5,7, Disc.None), 10),
                new ScoredPosition(new Position(5,8, Disc.None), 9),
                new ScoredPosition(new Position(5,9, Disc.None), 8),
                new ScoredPosition(new Position(5,10, Disc.None), 7),
                new ScoredPosition(new Position(5,11, Disc.None), 4),
                new ScoredPosition(new Position(6,2, Disc.None), 2),
                new ScoredPosition(new Position(6,3, Disc.None), 5),
                new ScoredPosition(new Position(6,4, Disc.None), 6),
                new ScoredPosition(new Position(6,5, Disc.None), 7),
                new ScoredPosition(new Position(6,6, Disc.None), 8),
                new ScoredPosition(new Position(6,7, Disc.None), 8),
                new ScoredPosition(new Position(6,8, Disc.None), 8),
                new ScoredPosition(new Position(6,9, Disc.None), 7),
                new ScoredPosition(new Position(6,10, Disc.None), 6),
                new ScoredPosition(new Position(6,11, Disc.None), 5),
                new ScoredPosition(new Position(6,12, Disc.None), 2),
                new ScoredPosition(new Position(7,1, Disc.None), 1),
                new ScoredPosition(new Position(7,2, Disc.None), 2),
                new ScoredPosition(new Position(7,3, Disc.None), 2),
                new ScoredPosition(new Position(7,4, Disc.None), 3),
                new ScoredPosition(new Position(7,5, Disc.None), 4),
                new ScoredPosition(new Position(7,6, Disc.None), 4),
                new ScoredPosition(new Position(7,7, Disc.None), 4),
                new ScoredPosition(new Position(7,8, Disc.None), 4),
                new ScoredPosition(new Position(7,9, Disc.None), 4),
                new ScoredPosition(new Position(7,10, Disc.None),3),
                new ScoredPosition(new Position(7,11, Disc.None), 2),
                new ScoredPosition(new Position(7,12, Disc.None), 2),
                new ScoredPosition(new Position(7,13, Disc.None), 1),
        };

        return new Board(weights);
    }

    public static final Board getBoardCounterWeights() {

        //                            0
        //                        1   0   1
        //                    1   1   4   1   1
        //                1   1   4   4   4   1   1
        //            1   1   4   4   4   4   4   1   1
        //        0   0   2   2   2   2   2   2   2   0   0
        //    0   0   2   2   2   2   2   2   2   2   2   0   0

        IPosition[] weights = new IPosition[]{
                new ScoredPosition(new Position(1,7, Disc.None), 0),
                new ScoredPosition(new Position(2,6, Disc.None), 1),
                new ScoredPosition(new Position(2,7, Disc.None), 0),
                new ScoredPosition(new Position(2,8, Disc.None), 1),
                new ScoredPosition(new Position(3,5, Disc.None), 1),
                new ScoredPosition(new Position(3,6, Disc.None), 1),
                new ScoredPosition(new Position(3,7, Disc.None), 4),
                new ScoredPosition(new Position(3,8, Disc.None), 1),
                new ScoredPosition(new Position(3,9, Disc.None), 1),
                new ScoredPosition(new Position(4,4, Disc.None), 1),
                new ScoredPosition(new Position(4,5, Disc.None), 1),
                new ScoredPosition(new Position(4,6, Disc.None), 4),
                new ScoredPosition(new Position(4,7, Disc.None), 4),
                new ScoredPosition(new Position(4,8, Disc.None), 4),
                new ScoredPosition(new Position(4,9, Disc.None), 1),
                new ScoredPosition(new Position(4,10, Disc.None), 1),
                new ScoredPosition(new Position(5,3, Disc.None), 1),
                new ScoredPosition(new Position(5,4, Disc.None), 1),
                new ScoredPosition(new Position(5,5, Disc.None), 4),
                new ScoredPosition(new Position(5,6, Disc.None), 4),
                new ScoredPosition(new Position(5,7, Disc.None), 4),
                new ScoredPosition(new Position(5,8, Disc.None), 4),
                new ScoredPosition(new Position(5,9, Disc.None), 4),
                new ScoredPosition(new Position(5,10, Disc.None), 1),
                new ScoredPosition(new Position(5,11, Disc.None), 1),
                new ScoredPosition(new Position(6,2, Disc.None), 0),
                new ScoredPosition(new Position(6,3, Disc.None), 0),
                new ScoredPosition(new Position(6,4, Disc.None), 2),
                new ScoredPosition(new Position(6,5, Disc.None), 2),
                new ScoredPosition(new Position(6,6, Disc.None), 2),
                new ScoredPosition(new Position(6,7, Disc.None), 2),
                new ScoredPosition(new Position(6,8, Disc.None), 2),
                new ScoredPosition(new Position(6,9, Disc.None), 2),
                new ScoredPosition(new Position(6,10, Disc.None), 2),
                new ScoredPosition(new Position(6,11, Disc.None), 0),
                new ScoredPosition(new Position(6,12, Disc.None), 0),
                new ScoredPosition(new Position(7,1, Disc.None), 0),
                new ScoredPosition(new Position(7,2, Disc.None), 0),
                new ScoredPosition(new Position(7,3, Disc.None), 2),
                new ScoredPosition(new Position(7,4, Disc.None), 2),
                new ScoredPosition(new Position(7,5, Disc.None), 2),
                new ScoredPosition(new Position(7,6, Disc.None), 2),
                new ScoredPosition(new Position(7,7, Disc.None), 2),
                new ScoredPosition(new Position(7,8, Disc.None), 2),
                new ScoredPosition(new Position(7,9, Disc.None), 2),
                new ScoredPosition(new Position(7,10, Disc.None),2),
                new ScoredPosition(new Position(7,11, Disc.None), 2),
                new ScoredPosition(new Position(7,12, Disc.None), 0),
                new ScoredPosition(new Position(7,13, Disc.None), 0),
        };

        return new Board(weights);
    }
}
