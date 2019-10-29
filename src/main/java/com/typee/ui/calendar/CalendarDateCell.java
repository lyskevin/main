package com.typee.ui.calendar;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.typee.commons.core.LogsCenter;
import com.typee.model.engagement.Engagement;
import com.typee.ui.UiPart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Represents a calendar date cell in the calendar window.
 */
public class CalendarDateCell extends UiPart<Region> {

    private static final String FXML = "CalendarDateCell.fxml";

    @FXML
    private StackPane calendarDatePane;

    private ObservableList<Engagement> engagements;
    private SingleDayEngagementsDisplayWindow engagementsDisplayWindow;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Constructs a calendar date cell.
     */
    public CalendarDateCell() {
        super(FXML);
        engagements = FXCollections.observableList(new ArrayList<>());
        engagementsDisplayWindow = new SingleDayEngagementsDisplayWindow(engagements);
    }

    /**
     * Returns the {@code StackPane} that is used to mount this {@code CalendarDateCell}.
     * @return The {@code StackPane} that is used to mount this {@code CalendarDateCell}.
     */
    public StackPane getCalendarDatePane() {
        return calendarDatePane;
    }

    /**
     * Adds the specified engagement to this {@code CalendarDateCell}.
     * @param engagement The specified engagement.
     */
    public void addEngagement(Engagement engagement) {
        engagements.add(engagement);
    }

    /**
     * Clears all engagements from this {@code CalendarDateCell}.
     */
    public void clearEngagements() {
        engagements.clear();
    }

    /**
     * Returns the number of engagements in this {@code CalendarDateCell}.
     * @return The number of engagements in this {@code CalendarDateCell}.
     */
    public int getNumberOfEngagements() {
        return engagements.size();
    }

    /**
     * Displays the engagements for the day represented by this {@code CalendarDateCell}.
     */
    @FXML
    private void displayEngagements() {
        if (!engagementsDisplayWindow.isShowing()) {
            engagementsDisplayWindow.show();
        } else {
            engagementsDisplayWindow.focus();
        }
    }

}
