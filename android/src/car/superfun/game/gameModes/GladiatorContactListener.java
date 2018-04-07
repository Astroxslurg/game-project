package car.superfun.game.gameModes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import car.superfun.game.GlobalVariables;
import car.superfun.game.physicalObjects.LocalGladiatorCar;

/**
 * Created by matss on 22-Mar-18.
 */

public class GladiatorContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (isWalls(fixtureA, fixtureB)) {
            return;
        }

        // Set localCar to user.
        Fixture user = fixtureA.getFilterData().categoryBits == GlobalVariables.PLAYER_ENTITY ? fixtureA : fixtureB;

        // Set the other fixture.
        Fixture other = fixtureA.getDensity() == 1f ? fixtureB : fixtureA;

        if (user.getUserData() instanceof LocalGladiatorCar) {
            if (other.getDensity() == 1f) {
                ((LocalGladiatorCar) user.getUserData()).hitByCar();
            } else {
                ((LocalGladiatorCar) user.getUserData()).hitDeathWalls();
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold){

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse){

    }

    public boolean isWalls(Fixture fixtureA, Fixture fixtureB) {
        if (fixtureA.getDensity() == 0.9f || fixtureB.getDensity() == 0.9f) {
            return true;
        }
        return false;
    }
}
