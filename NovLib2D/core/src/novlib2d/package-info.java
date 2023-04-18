package novlib2d;

/*
  Introduction:
    As you can see, the methods of the BasicGame class template are similar to that of MonoGame's. I'm sorry for making
    it that way, but I have limited programming experience and I was coding MonoGame before Libgdx, so I tried to make
    it look familiar to me. Anyway, the following sequence will happen when running the game:

initialize() -> load() -> loop start -> update() -> draw() -> repeat loop until user wants to exit -> dispose() -> exit

    I've added more helpful features to it such as the GameHelper, which typically does half of the workload for you,
    AssetHelper, EntityManager, and some utility classes such as Point and Pair. All the features are optional, though
    I would highly recommend on using them.

  Features:
  GameHelper
        -> uses an AssetHelper, SpriteBatch, CachedThreadPool, and several GameManagers(i.e. EntityManager, etc.)
        -> helps you skip some coding steps for using an AssetHelper/AssetManager, though you can still use a separate
           AssetManager/AssetHelper if you want or use the protected field AssetHelper of this class's parent class.
           (see Game class for more information)
        -> used for loading assets via AssetHelper. Needs to know if what you're loading is a priority asset
           (an asset essential for the startup i.e. player assets, menu assets, etc.) or not. If set to a
           priority, the AssetHelper will block the loop progress until the asset or assets are fully loaded. Loaded
           assets will automatically be put into respective ArrayLists and stored for future use.
        -> loaded texture assets will be sorted out and used by the EntityManager.
        -> has an update method that has a fixed delta time based on Config class which is 16.666666667f for stable
           updating of entities on screen.
        -> has a draw method that calls the EntityManager's draw method, which draws every entity that is labeled
           "isActive = true"
  **/