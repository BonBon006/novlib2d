package novlib2d.base;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import novlib2d.helper.AssetHelper;
import novlib2d.helper.GameHelper;

public abstract class Game extends ApplicationAdapter {

	protected GameHelper gameHelper;

	/**Called before load(). Initialize everything you need here.
	 * <p></p>
	 * IMPORTANT: if you're going to use GameHelper, call gameHelper.enable() on the first line, and then
	 * gameHelper.apply() on the last line. This will enable the gameHelper methods and apply the initializations
	 * before apply() is called. If not enabled, gameHelper will not do anything.
	 * Note that GameHelper has already been instantiated with access "protected" in this class's parent class.
	 * @see Game
	 * @see GameHelper
	 * @author Raevon
	 */
	public abstract void initialize();

	/**Called after initialize(), not necessarily any different from its preceding method. But to keep
	 * your code clean, it is recommended to put codes related to loading assets here. If gameHelper is enabled,
	 * It is recommended to use gameHelper.load() to load assets instead of an AssetManager as GameHelper
	 * already has an AssetHelper (extended AssetManager) instantiated.
	 * @see AssetHelper
	 * @see GameHelper
	 * @author Raevon
	 */
	public abstract void load();

	/**Called after initialize() and load() methods. Game updates should be put here. If GameHelper is
	 * enabled, gameHelper.update() must be called.
	 * @param deltaTime Helps in updating animation frames.
	 *                     By default, set to 16.666666667f which works best with 60fps.
	 * @see GameCore
	 * @see GameHelper
	 * @author Raevon
	 */
	public abstract void update(float deltaTime);

	/**Called after update(). Entities to be drawn in the game screen should be drawn here. If GameHelper is
	 * enabled, gameHelper.draw() must be called. Otherwise, I recommend putting the draw() method of SpriteBatch
	 * in between SpriteBatch's begin() and end() methods.
	 * @see SpriteBatch
	 * @see GameHelper
	 * @author Raevon
	 */
	public abstract void draw();

	@Override
	public void create() {
		gameHelper = new GameHelper(); // instantiated the GameHelper here
		initialize();
		load();
	}

	@Override
	public void render() {
		update(gameHelper.core.getDeltaTime());
		draw();
		if (gameHelper != null && !gameHelper.isEnabled()) {
			gameHelper.dispose(); // disposes GameHelper's SpriteBatch and AssetHelper
		}
	}

	/**Libgdx's dispose() method, overridden in the GameTest class. Dispose instances of SpriteBatch and
	 * AssetManager here. If GameHelper is not enabled, gameHelper.dispose() will be called in the render() loop of
	 * Game class once.
	 * @see Game
	 * @see GameHelper
	 * @author Raevon
	 */
	@Override
	public abstract void dispose();
}
