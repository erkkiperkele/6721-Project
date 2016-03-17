public interface IStrategy {
    HeuristicNode calculateScore(HeuristicNode node, Disc currentTurn);
}
