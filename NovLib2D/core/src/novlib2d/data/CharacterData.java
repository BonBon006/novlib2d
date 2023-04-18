package novlib2d.data;

import novlib2d.algorithm.Logic;

public record CharacterData(Logic logic, String name, int width, int height, int actionCount, int frameCount,
                            float health, float mana, float stamina, float speed, boolean skillsEnabled,
                            boolean cameraEnabled) { }
