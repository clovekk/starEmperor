package userinterface;

import game.World;

import java.util.concurrent.atomic.AtomicBoolean;

public interface UserInterface {
    public void startDisplaying(World world, AtomicBoolean end);
}
