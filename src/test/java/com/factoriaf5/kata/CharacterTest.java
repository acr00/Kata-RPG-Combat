package com.factoriaf5.kata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CharacterTest {
    
@Test
public void createCharacterWithCorrectInitialValues() {

    Character character = new Character(Character.CharacterType.MELEE);
    assertEquals(1000, character.getHealth());
    assertEquals(1, character.getLevel());
    assertTrue(character.isAlive());
    assertEquals(Character.CharacterType.MELEE, character.getType());
    assertEquals(2, character.getAttackRange());

}

@Test
public void createMeleeCharacterWithAttackRangeOfTwo() {

    Character meleeCharacter = new Character(Character.CharacterType.MELEE);
    assertEquals(2, meleeCharacter.getAttackRange());
    assertEquals(Character.CharacterType.MELEE, meleeCharacter.getType());

}

@Test
public void createRangedCharacterWithAttackRangeOf20() {

    Character rangedCharacter = new Character(Character.CharacterType.RANGED);
    assertEquals(20, rangedCharacter.getAttackRange());
    assertEquals(Character.CharacterType.RANGED, rangedCharacter.getType());

}

@Test
public void decreaseHealthWhenReceivingDamage() {

    Character character = new Character(Character.CharacterType.MELEE);
    long initialHealth = character.getHealth();
    int damageAmount = 100;
    
    character.receiveDamage(damageAmount);
    
    assertEquals(initialHealth - damageAmount, character.getHealth());
}

@Test
public void setAliveStatusToFalseWhenHealthReachesZero() {

    Character character = new Character(Character.CharacterType.MELEE);
    character.receiveDamage(1000);
    assertEquals(0, character.getHealth());
    assertFalse(character.isAlive());

}

@Test
public void throwExceptionWhenReceivingNegativeDamage() {

    Character character = new Character(Character.CharacterType.MELEE);
    assertThrows(IllegalArgumentException.class, () -> character.receiveDamage(-50));
    
}

@Test
public void increaseHealthWhenHealedButNotExceed1000() {

    Character character = new Character(Character.CharacterType.MELEE);
    character.receiveDamage(500);
    assertEquals(500, character.getHealth());
    
    character.heal(300);
    assertEquals(800, character.getHealth());
    
    character.heal(300);
    assertEquals(1000, character.getHealth());

}

@Test
public void notHealWhenCharacterIsNotAlive() {

    Character character = new Character(Character.CharacterType.MELEE);
    character.receiveDamage(1000); // Kill the character
    assertFalse(character.isAlive());
    
    character.heal(100);
    assertEquals(0, character.getHealth());
    assertFalse(character.isAlive());

}

@Test
public void throwExceptionWhenHealingAmountIsNegative() {

    Character character = new Character(Character.CharacterType.MELEE);
    assertThrows(IllegalArgumentException.class, () -> character.heal(-50));

}

@Test
public void adjustDamageBasedOnLevelDifference() {

    Character attacker = new Character(Character.CharacterType.MELEE);
    Character target = new Character(Character.CharacterType.MELEE);
    
    for (int i = 0; i < 5; i++) {
        target.levelUp();
    }
    
    int initialHealth = (int) target.getHealth();
    int damage = 100;
    attacker.dealDamage(target, damage, 1);
    
    
    int expectedDamage = damage / 2;
    int expectedHealth = initialHealth - expectedDamage;
    
    assertEquals(expectedHealth, target.getHealth());
}

}
