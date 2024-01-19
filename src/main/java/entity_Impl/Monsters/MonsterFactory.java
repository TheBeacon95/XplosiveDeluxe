package entity_Impl.Monsters;

import common.Coordinates;
import entity_Impl.Monsters.MonsterEntities.*;
import entity_Interfaces.MonsterType;

/**
 * Is responsible for creating all kinds of Entities.
 * @author Yanick
 */
public class MonsterFactory {
    
    public MonsterAbs createMonster(MonsterType monsterType, Coordinates position) {
        return switch (monsterType) {
            case BrickEater -> null;
            case Muncher -> null;
            case Ghost -> new Ghost(position);
            case Phantom -> new Phantom(position);
            case Ninja -> new Ninja(position);
            case Bacteria -> new Bacteria(position);
            case Fuzzy -> new Fuzzy(position);
            case Spider -> null;
            case Reaper -> null;
            case Slime -> null;
            case Turtle -> null;
            case BrickMonster -> null;
            case BlockMonster -> null;
            case Wizard -> null;
            case Magma -> null;
            case LifeDrainer -> null;
            case BlackHole -> null;
        };
    }
}