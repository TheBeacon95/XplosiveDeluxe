package entity_Interfaces;

/**
 *
 * @author Yanick
 */
public enum Speed {
    SlowMonster(1),
    NormalMonster(2),
    FastMonster(4),
    DefaultPlayer(8),
    Fast(16),
    VeryFast(32),
    UltraFast(64);
    
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