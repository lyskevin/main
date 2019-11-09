package com.typee.logic.interactive.parser.state;

import static com.typee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.typee.logic.interactive.parser.ArgumentMultimap;
import com.typee.logic.interactive.parser.CliSyntax;
import com.typee.logic.interactive.parser.Prefix;
import com.typee.logic.interactive.parser.state.exceptions.StateTransitionException;
import com.typee.logic.interactive.parser.state.sortmachine.PropertyState;

public class OrderStateTest {
    private State postTransitionState;

    @BeforeEach
    public void setup() {
        try {
            postTransitionState = new PropertyState(new ArgumentMultimap());
            ArgumentMultimap transitionArgumentMultimap = new ArgumentMultimap();
            transitionArgumentMultimap.put(CliSyntax.PREFIX_PROPERTY, "start");
            postTransitionState = postTransitionState.transition(transitionArgumentMultimap);
        } catch (StateTransitionException e) {
            // StateTransitionException should not be thrown here.
        }
    }

    @Test
    public void transition_validArgumentMultimap_returnsPostTransitionState() {
        ArgumentMultimap validArgumentMultimap = new ArgumentMultimap();
        validArgumentMultimap.put(CliSyntax.PREFIX_PROPERTY, "start");
        validArgumentMultimap.put(CliSyntax.PREFIX_ORDER, "ascending");

        ArgumentMultimap transitionArgumentMultimap = new ArgumentMultimap();
        transitionArgumentMultimap.put(CliSyntax.PREFIX_ORDER, "ascending");
        try {
            postTransitionState = postTransitionState.transition(transitionArgumentMultimap);
        } catch (StateTransitionException e) {
            // StateTransitionException should not be thrown here.
        }

        assertEquals(postTransitionState, new PropertyState(validArgumentMultimap));
    }

    @Test
    public void transition_invalidOrder_throwsStateTransitionException() {
        try {
            State postTransitionState = new PropertyState(new ArgumentMultimap());
            ArgumentMultimap transitionArgumentMultimap = new ArgumentMultimap();
            transitionArgumentMultimap.put(CliSyntax.PREFIX_PROPERTY, "start");
            transitionArgumentMultimap.put(CliSyntax.PREFIX_ORDER, "aschendung");
            postTransitionState = postTransitionState.transition(transitionArgumentMultimap);
            State finalPostTransitionState = postTransitionState;
            assertThrows(StateTransitionException.class, ()
                -> finalPostTransitionState.transition(transitionArgumentMultimap));
        } catch (StateTransitionException e) {
            // StateTransitionException should be handled in assertThrows.

        }
    }

    @Test
    public void getStateConstraints() {
        assertEquals(postTransitionState.getStateConstraints(), "Please enter the ordering to be followed,"
                + " prefixed by \"o/\". Accepted orderings are \"ascending\" and \"descending\".");
    }

    @Test
    public void isEndState() {
        assertFalse(postTransitionState.isEndState());
    }

    @Test
    public void getPrefix() {
        assertEquals(postTransitionState.getPrefix(), new Prefix("o/"));
    }

}
