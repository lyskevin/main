package com.typee.model.engagement;

import java.time.LocalDateTime;
import java.util.List;

import com.typee.model.person.Person;

/**
 * Represents a generalization of meetings, interviews and appointments.
 */
public abstract class Engagement {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected AttendeeList attendees;
    protected Location location;
    protected String description;
    protected Priority priority;

    protected Engagement(LocalDateTime start, LocalDateTime end,
                         AttendeeList attendees, Location location, String description, Priority priority) {
        this.startTime = start;
        this.endTime = end;
        this.attendees = attendees;
        this.location = location;
        this.description = description;
        this.priority = priority;
    }

    public static Engagement of(EngagementType type,
                                LocalDateTime start, LocalDateTime end,
                                AttendeeList attendees, Location location, String description,
                                Priority priority) {
        if (type.name().equalsIgnoreCase("meeting")) {
            return new Meeting(start, end, attendees, location, description, priority);
        } else if (type.name().equalsIgnoreCase("interview")) {
            return new Interview(start, end, attendees, location, description, priority);
        } else {
            return new Appointment(start, end, attendees, location, description, priority);
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public AttendeeList getAttendees() {
        return attendees;
    }

    public void setAttendees(AttendeeList attendees) {
        this.attendees = attendees;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isSameEngagement(Engagement engagement) {
        return engagement.endTime.equals(endTime)
                && engagement.startTime.equals(startTime)
                && engagement.location.equals(location);
    }
}
