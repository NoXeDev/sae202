package fr.sae202;

import org.junit.jupiter.api.Test;

import fr.sae202.Controller.QuestParser;
import fr.sae202.Exceptions.QuestParseException;
import fr.sae202.Exceptions.ScenarioNotFoundException;
import fr.sae202.Models.Quest;
import fr.sae202.Utils.Vector2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

public class QuestParserTest {
    @Test 
    @Order(0)
    @DisplayName("QuestParser module test")
    public void parseScenarioTest() {
       QuestParser parser = new QuestParser("res");

       /**
        * Possibles cases :
            * - Quest precondition is null
            * - Quest precondition second Vector is null
            * - Quest precondition first Vector has a null value
            * - Quest precondition second Vector has a null value
            * - Quest has no null value
        */

       Quest preCondNull = new Quest(1, new Vector2<Integer>(4, 3), null, 2, 100, "explorer pic de Bhanborim");
       Quest preCondSecondValNull = new Quest(2, new Vector2<Integer>(3, 1), new Vector2<Vector2<Integer>>(new Vector2<Integer>(1, null), null), 1, 150, "dialoguer avec Kaela la chaman des esprits");
       Quest preCondFirstVecHasNullVal = new Quest(4, new Vector2<Integer>(6, 3), new Vector2<Vector2<Integer>>(new Vector2<Integer>(6, null), new Vector2<Integer>(7, 3)), 6, 200, "explorer pic de Nigh Todir");
       Quest preCondSecondVecHasNullVal = new Quest(0, new Vector2<Integer>(8, 9), new Vector2<Vector2<Integer>>(new Vector2<Integer>(9, null), new Vector2<Integer>(7, null)), 12, 900, "explorer forteresse de Bhenheim");
       Quest questHasNotNull = new Quest(3, new Vector2<Integer>(5, 6), new Vector2<Vector2<Integer>>(new Vector2<Integer>(5, 1), new Vector2<Integer>(4, 2)), 8, 300, "vaincre Nymphe de libellule géante");

       try {
            assertEquals(parser.parseScenario(0).questMap.get(1), preCondNull);
            assertEquals(parser.parseScenario(0).questMap.get(2), preCondSecondValNull);
            assertEquals(parser.parseScenario(3).questMap.get(4), preCondFirstVecHasNullVal);
            assertEquals(parser.parseScenario(4).questMap.get(0), preCondSecondVecHasNullVal);
            assertEquals(parser.parseScenario(5).questMap.get(3), questHasNotNull);
        } catch (ScenarioNotFoundException | QuestParseException e) {
            fail("Parser throw an error", e);
        }
    }

    @Test
    @Order(1)
    @DisplayName("QuestParser exceptions test")
    public void parseScenarioExceptionsTest()
    {
        QuestParser parser = new QuestParser("res/tests");

        /**
         * Possibles cases :
         * - Scenario not found
         * - Quest parse error
         */

        assertThrows(ScenarioNotFoundException.class, () -> parser.parseScenario(55));
        assertThrows(QuestParseException.class, () -> parser.parseScenario(0));
    }
}
