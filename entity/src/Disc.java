import com.sun.javaws.exceptions.InvalidArgumentException;

public enum Disc {
    X,
    O,
    None,
    Occupied,
    Invalid,
    Valid,
    Tie;


    public Disc invert() {
        if (this == Disc.O || this == Disc.X) {
            return this == Disc.O
                    ? Disc.X
                    : Disc.O;
        }
        if (this == Disc.None || this == Disc.Occupied) {
            return this == Disc.None
                    ? Disc.Occupied
                    : Disc.None;
        }
        if (this == Disc.Invalid || this == Disc.Valid) {
            return this == Disc.Invalid
                    ? Disc.Valid
                    : Disc.Invalid;
        }
        return null;
    }
}
