package entity_Interfaces;

/**
 *
 * @author Yanick
 */
public enum Speed {
    SlowMonster(0),
    NormalMonster(1),
    FastMonster(2),
    DefaultPlayer(3),
    Fast(4),
    VeryFast(5),
    UltraFast(6);
    
    private Speed(int value) {
        m_value = value;
    }
    
    public int toInt() {
        return m_value;
    }
    
    private int m_value;

    public Speed increase() {
        return switch (this) {
            case SlowMonster -> NormalMonster;
            case NormalMonster -> FastMonster;
            case FastMonster -> DefaultPlayer;
            case DefaultPlayer -> Fast;
            case Fast -> VeryFast;
            case VeryFast -> UltraFast;
            case UltraFast -> UltraFast;
        };
    }
    
    public final Speed maxSpeed() {
        return UltraFast;
    }
}