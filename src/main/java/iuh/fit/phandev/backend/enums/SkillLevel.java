package iuh.fit.phandev.backend.enums;

public enum SkillLevel {
    BEGINNER(0),
    JUNIOR(1),
    MID_LEVEL(2),
    SENIOR(3),
    EXPERT(4);
    private final int level;
    private SkillLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    public static SkillLevel getLevel(int level) {
        return switch (level){
            case 0 -> BEGINNER;
            case 1 -> JUNIOR;
            case 2 -> MID_LEVEL;
            case 3 -> SENIOR;
            case 4 -> EXPERT;
            default -> BEGINNER;
        };
    }
}
