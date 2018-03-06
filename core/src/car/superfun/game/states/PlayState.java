package car.superfun.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import car.superfun.game.CarControls.CarController;
import car.superfun.game.physicalObjects.LocalCar;


public class PlayState extends State{

    private Sprite map;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    private OrthographicCamera camera;

    private CarController carController;
    private LocalCar localCar;

    public PlayState(OrthographicCamera  camera) {
        this.camera = camera;

        carController = new CarController();
        localCar = new LocalCar(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        tiledMap = new TmxMapLoader().load("testMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void handleInput() {
    
    }

    @Override
    public void update(float dt) {
        Vector2 position = new Vector2(0,0);
        camera.translate(position);
        carController.update();
        localCar.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        carController.render(sb);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public void dispose() {

    }
}
