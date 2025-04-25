package org.example.smarthomesystem.commands;

public interface Command {
    void execute();
    void undo();
}