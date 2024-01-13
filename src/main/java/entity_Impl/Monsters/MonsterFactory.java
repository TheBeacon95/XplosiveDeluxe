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
        MonsterAbs monster = null;
        switch (monsterType) {
            case BrickEater -> {
            }
            case Muncher -> {
            }
            case Ghost -> {
                monster = new Ghost(position);
            }
            case Phantom -> {
            }
            case Ninja -> {
                monster = new Ninja(position);
            }
            case Bacteria -> {
            }
            case Fuzzy -> {
            }
            case Spider -> {
            }
            case Reaper -> {
            }
            case Slime -> {
            }
            case Turtle -> {
            }
            case BrickMonster -> {
            }
            case BlockMonster -> {
            }
            case Wizard -> {
            }
            case Magma -> {
            }
            case LifeDrainer -> {
            }
            case BlackHole -> {
            }
        }
        return monster;
    }
}
